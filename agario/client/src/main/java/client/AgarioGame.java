package client;

import client.model.Map;
import client.model.TestMap;
import client.model.object.Cell;
import common.model.SimpleMapObject;
import client.view.Window;
import client.view.Renderer;
import client.view.gl.GlException;
import common.communication.ConnectionError;
import common.model.SimpleCell;
import java.awt.GridLayout;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.List;
import javax.swing.*;
import org.joml.Vector2f;
import org.joml.Vector2i;

import org.lwjgl.glfw.GLFWKeyCallback;
import static org.lwjgl.glfw.GLFW.*;
import org.lwjgl.glfw.GLFWErrorCallback;
import common.communication.JoinResponse;

public class AgarioGame {
    
    private Window window;
    private Renderer renderer;
    
    private Map map;
    private Cell player;
    private int playerID;
    
    // networking
    Socket client;
    OutputStream outStream;
    ObjectOutputStream objectOutStream;
    InputStream inStream;
    ObjectInputStream objectInStream;
    private int id;
    private NetworkHandler networkHandler;
    
    // to be used by GL
    private float mapSize;
    
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

            movement.y *= -1;

        } else {
            movement.zero();
        }

        return movement;
        
    }
    
    public AgarioGame(String[] args) {
        
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
        
        networkHandler = NetworkHandler.getInstance();
        JoinResponse join_response = null;
        
        try {
            join_response = NetworkHandler.initConnection(ipAddress, portNumber, userName);
        } catch (ConnectionError connection_error) {
            JOptionPane.showMessageDialog(null, "Failed to connect to the server. Error message: " + connection_error, "Connection Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
        
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
        
        //
        
        map = new Map(join_response.getMapSize());
        playerID = join_response.getId();
        
    }
    
    /**
     * Method to be ran on game close.
     * @throws IOException 
     */
    public void close() throws IOException {
        glfwTerminate();
        client.close();
    }
    
    /**
     * Main program loop
     */
    public void run() {
        
        try {
        
            renderer.initialize();

            window.setVsync(true);
            window.show();
            
            // Get initial map data from server
            
            List<? super SimpleMapObject> mapObjects = NetworkHandler.handleSimpleResponse();
            map.updateWithSimpleMapObjects(mapObjects);
            
            for (Object mapObject : mapObjects) {
                
                if (mapObject instanceof SimpleCell) {
                    
                    SimpleCell cell = (SimpleCell)mapObject;
                    
                    if (cell.getId() == playerID) {
                        //player = cell; TODO
                        break;
                    }
                }
                
            }
            
            // The main loop

            while (!window.shouldClose()) {
                
                Vector2i cursor_position = window.getCursorPosition();
                Vector2f movement = calculatePlayerMovement(cursor_position);
                player.move(movement);
                
                // send request to server
                Vector2f axisX = new Vector2f(1f, 0f).normalize();
                float angle = movement.angle(axisX);
                NetworkHandler.sendRequest(angle, 1);
                
                // get map data from server
                mapObjects = NetworkHandler.handleSimpleResponse();
                map.updateWithSimpleMapObjects(mapObjects);
                
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
