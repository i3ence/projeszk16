package client;

import client.model.Map;
import client.model.TestMap;
import client.model.object.Cell;
import client.model.object.MapObject;
import client.view.Window;
import client.view.Renderer;
import client.view.gl.GlException;
import communication.JoinAcknowledgmentImpl;
import communication.JoinAcknowledgment;
import communication.JoinResponse;
import communication.Request;
import communication.RequestImpl;
import communication.Response;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.joml.Vector2f;
import org.joml.Vector2i;

import org.lwjgl.glfw.GLFWKeyCallback;
import static org.lwjgl.glfw.GLFW.*;
import org.lwjgl.glfw.GLFWErrorCallback;

public class AgarioGame {
    
    private Window m_window;
    private Renderer m_renderer;
    
    private Map m_map;
    private Cell m_player;
    
    // networking
    Socket client;
    OutputStream outStream;
    ObjectOutputStream objectOutStream;
    InputStream inStream;
    ObjectInputStream objectInStream;
    private int id;
    
    
    // these could be configured through UI.
    private String ipAddress;
    private int portNumber;
    private String name;

    
    
    // to be used by GL
    private float mapSize;
    
    /**
     * Send request to server, call this as much as possible.
     * @param angle the angle where player cell is moving
     */
    public void sendRequest(float angle) {
        Request request = new RequestImpl(angle);
        try {
            objectOutStream.writeObject(request);
            objectOutStream.close();
        } catch (IOException ex) {
            Logger.getLogger(AgarioGame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Receive the response from server
     * !! if response is not received, this will throw NPE. Need to investigate!
     * @return server response
     */
    public List<MapObject> handleResponse() {
        Response response = null;
        try {
            response = (Response) objectInStream.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(AgarioGame.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response.getMapObjects();
    }
    
    public void initConnection() {
        try {
            client = new Socket(ipAddress, portNumber);
            boolean connected = false;
            
            // init streams
            outStream = client.getOutputStream();
            inStream = client.getInputStream();
            objectOutStream = new ObjectOutputStream(outStream);
            objectInStream = new ObjectInputStream(inStream);
            
            // receive ack
            try {
                JoinResponse response = (JoinResponse) objectInStream.readObject();
                if (response.getStatus() == 0) {
                    // accepted
                    mapSize = response.getMapSize();
                    id = response.getId();
                    connected = true;
                }
                else {
                    // rejected
                    // TODO: Show toast: SERVER FULL
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(AgarioGame.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            // send name if connection is accepted
            if (connected) {
                JoinAcknowledgment joinAck = new JoinAcknowledgmentImpl(name);
                objectOutStream.writeObject(joinAck);    
            }
        }
        catch (IOException e) {
            System.out.println(e);
        }
    }
    
    private Vector2f calculatePlayerMovement(Vector2i cursor_position) {
        
        Vector2i window_size = m_window.getSize();

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
        
        // debug
//        ipAddress = "localhost";
//        portNumber = 12345;
//        name = "KisGomboc";
        
        ipAddress = args[0];
        portNumber = Integer.parseInt(args[1]);
        name = args[2];
        
        initConnection();
        
        GLFWErrorCallback.createPrint(System.err).set();
 
        if (glfwInit() != GLFW_TRUE) {
            throw new IllegalStateException("Unable to initialize GLFW.");
        }
        
        //
        
        m_window = new Window(1280, 720, "AgarioGame");
        m_renderer = new Renderer(m_window);
        
        //
        
        GLFWKeyCallback key_callback; // This is needed otherwise the closure gets garbage collected
        
        glfwSetKeyCallback(m_window.getHandle(), key_callback = new GLFWKeyCallback() {
            
            @Override
            public void invoke(long window, int key, int scancode, int action, int mods) {
                if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE) {
                    glfwSetWindowShouldClose(window, GLFW_TRUE);
                }
            }
            
        });
        
        //
        
        m_map = new TestMap();
        
        TestMap test_map = (TestMap)m_map;
        test_map.fillWithTestObjects();
        
        m_player = new Cell(m_map, new Vector2f(30, 22), 5, 123456789, "yzsolt", 1);
        m_map.getObjects().add(m_player);
        
    }
    
    public void close() throws IOException {
        glfwTerminate();
        client.close();
    }
    
    public void run() {
        
        try {
        
            m_renderer.initialize();

            m_window.setVsync(true);
            m_window.show();

            // The main loop

            while (!m_window.shouldClose()) {
                
                Vector2i cursor_position = m_window.getCursorPosition();
                Vector2f movement = calculatePlayerMovement(cursor_position);
                m_player.move(movement);
                
                // send request to server
                Vector2f axisX = new Vector2f(1f, 0f).normalize();
                float angle = movement.angle(axisX);
                sendRequest(angle);
                
                // TODO: use response from handler to draw map
                List<MapObject> updatedMap = handleResponse();

                m_renderer.render(m_map, m_player);
                m_window.pollEvents();
                
            }

            m_window.close();
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
