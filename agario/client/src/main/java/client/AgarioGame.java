package client;

import client.model.Map;
import client.view.Window;
import client.view.Renderer;
import client.view.gl.GlException;
import common.communication.ConnectionError;
import common.model.Cell;
import java.awt.GridLayout;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import javax.swing.*;
import org.joml.Vector2f;
import org.joml.Vector2i;

import org.lwjgl.glfw.GLFWKeyCallback;
import static org.lwjgl.glfw.GLFW.*;
import org.lwjgl.glfw.GLFWErrorCallback;
import common.communication.JoinResponse;
import common.communication.MapDataResponse;
import common.communication.PlayerMoveRequest;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AgarioGame {
    
    private Window window;
    private Renderer renderer;
    
    private Map map;
    private Cell player;
    private int playerId;
    
    private final Logger logger;
    
    // networking
    OutputStream outStream;
    ObjectOutputStream objectOutStream;
    InputStream inStream;
    ObjectInputStream objectInStream;
    
    // seems unneeded with server-side calculations
    private Vector2f calculatePlayerMovement(Vector2i cursor_position) {
        
        Vector2i window_size = window.getSize();

        Vector2f movement = new Vector2f(
            cursor_position.x - window_size.x / 2, 
            cursor_position.y - window_size.y / 2
        );
        
        float length = movement.length();
        float treshold = 60;
        
        if (length >= treshold) {
            Vector2f s = new Vector2f().set(movement);
            float ratio = length / treshold;
            s.mul(1 / length * treshold);
            movement.sub(s);
            
            float divider = 30;

            // No div() in JOML, are you kidding me?
            movement.mul(1 / divider);

        } else {
            movement.zero();
        }

        return movement;
        
    }
    
    public AgarioGame(String[] args) {
        
        this.logger = Logger.getLogger(AgarioGame.class.getName());
        
        String ipAddress = "";
        int portNumber = 0;
        String userName = "";
        
        // startup dialog, asks for server/user data
        JTextField ip = new JTextField("localhost");
        JTextField port = new JTextField("12345");
        JTextField name = new JTextField();
        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("IP Address"));
        panel.add(ip);
        panel.add(new JLabel("Port Number"));
        panel.add(port);
        panel.add(new JLabel("Username"));
        panel.add(name);
        
        boolean done = false;
        while(!done) {
            
            int result = JOptionPane.showConfirmDialog(null, panel, "Welcome", JOptionPane.PLAIN_MESSAGE);
            
            switch (result) {
                
                case JOptionPane.CLOSED_OPTION:
                    done = true;
                    break;
                    
                case JOptionPane.OK_OPTION:

                    try {
                        
                        ipAddress = ip.getText();
                        portNumber = Integer.parseInt(port.getText());
                        userName = name.getText();
                     
                        if (ipAddress.isEmpty() || userName.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "IP address and player name are required.", "Empty fields", JOptionPane.ERROR_MESSAGE);
                        } else {
                            done = true;
                        }
                        
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "The port number is invalid!", "Invalid port number", JOptionPane.ERROR_MESSAGE);
                    }
                    
                    break;
                    
            }

        }
        
        // Connect to server
        
        NetworkHandler networkHandler = NetworkHandler.getInstance();
        JoinResponse join_response = null;
        
        try {
            join_response = NetworkHandler.initConnection(ipAddress, portNumber, userName);
        } catch (ConnectionError connection_error) {
            JOptionPane.showMessageDialog(null, "Failed to connect to the server. Error message: " + connection_error, "Connection Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
        
        logger.log(Level.INFO, "Connected to the server.");
        
        // Initialize renderer
        
        GLFWErrorCallback.createPrint(System.err).set();
 
        if (glfwInit() != GLFW_TRUE) {
            throw new IllegalStateException("Unable to initialize GLFW.");
        }
        
        // TODO: read window size from config
        window = new Window(1280, 720, "AgarioGame");
        renderer = new Renderer(window);
        
        GLFWKeyCallback key_callback; // This is needed otherwise the closure gets garbage collected
        
        glfwSetKeyCallback(window.getHandle(), new GLFWKeyCallback() {
            
            @Override
            public void invoke(long window, int key, int scancode, int action, int mods) {
                if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE) {
                    glfwSetWindowShouldClose(window, GLFW_TRUE);
                }
            }
            
        });
        
        logger.log(Level.INFO, "Initialized the renderer.");
        
        //
        
        map = new Map(join_response.getMapSize());
        playerId = join_response.getId();
        
    }
    
    /**
     * Method to be ran on game close.
     * @throws IOException 
     */
    public void close() throws IOException {
        glfwTerminate();
    }
    
    /**
     * Main program loop.
     */
    public void run() {
        
        try {
        
            renderer.initialize();

            window.setVsync(true);
            window.show();
            
            // Get initial map data from server
            
            MapDataResponse mapDataResponse  = (MapDataResponse)NetworkHandler.waitForResponse();
            map.resetObjects(mapDataResponse.getMapObjects());
            
            logger.log(Level.INFO, "Map is initialized: {0}.", map);
            
            player = (Cell)map.getObject(playerId);
            
            if (player == null) {
                // TODO
                return;
            }
            
            logger.log(Level.INFO, "Player is at {0}.", player.getPosition());
            
            // The main loop

            while (!window.shouldClose()) {
                
                Vector2i cursor_position = window.getCursorPosition();
                Vector2f movement = calculatePlayerMovement(cursor_position);
                
                // Send player move request to server
                
                Vector2f axisX = new Vector2f(1f, 0f).normalize();
                float angle = movement.angle(axisX);
                float multiplier = common.Util.clamp(movement.length() / 100, 0, 1);
                NetworkHandler.sendRequest(new PlayerMoveRequest(angle, multiplier));
                
                // Get map data from server
                
                mapDataResponse = (MapDataResponse)NetworkHandler.waitForResponse();
                map.updateObjects(mapDataResponse.getMapObjects());
            
                // Render the map and handle window events
                
                renderer.render(map, player);
                window.pollEvents();
                
            }

            window.close();
            close();
        
        } catch (IOException e) {
            System.out.println("An IOException has occured.");
            System.out.println(e.getMessage());
        } catch (GlException e) {
            System.out.println("An OpenGL error has occured.");
            System.out.println(e.getMessage());
        }
        
    }

    public static void main(String[] args) {
        new AgarioGame(args).run();
    }
    
}
