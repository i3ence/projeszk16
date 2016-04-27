
package client;

import client.model.map.Map;
import client.view.Window;
import client.view.Renderer;

import org.lwjgl.glfw.GLFWKeyCallback;
import static org.lwjgl.glfw.GLFW.*;
import org.lwjgl.glfw.GLFWErrorCallback;

public class AgarioGame {
    
    private Window m_window;
    private Renderer m_renderer;
    
    private Map m_map;
    
    public AgarioGame(String[] args) {
        
        GLFWErrorCallback.createPrint(System.err).set();
 
        if (glfwInit() != GLFW_TRUE) {
            throw new IllegalStateException("Unable to initialize GLFW.");
        }
        
        //
        
        m_window = new Window(1280, 720, "AgarioGame");
        m_renderer = new Renderer(m_window);
        
        //
        
        GLFWKeyCallback keyCallback;
        
        glfwSetKeyCallback(m_window.getHandle(), keyCallback = new GLFWKeyCallback() {
            
            @Override
            public void invoke(long window, int key, int scancode, int action, int mods) {
                if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE) {
                    glfwSetWindowShouldClose(window, GLFW_TRUE);
                }
            }
            
        });
        
    }
    
    public void close() {
        glfwTerminate();
    }
    
    public void run() {
        
        m_renderer.initialize();

        m_window.setVsync(true);
        m_window.show();
        
        // The main loop
        
        while (!m_window.shouldClose()) {
            m_renderer.render(m_map);
            m_window.pollEvents();
        }
        
        m_window.close();
        close();
        
    }

    public static void main(String[] args) {
        new AgarioGame(args).run();
    }
    
}
