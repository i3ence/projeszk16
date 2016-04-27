
package client.view;

import client.model.map.Map;

import org.lwjgl.opengl.*; 
import static org.lwjgl.opengl.GL11.*;

/**
 * This class renders the map and the objects.
 * @author yzsolt
 */
public class Renderer {
    
    private final Window m_window;
    
    public Renderer(Window window) {
        m_window = window;
    }
    
    public void initialize() {
        
        m_window.makeContextCurrent();
        
        GL.createCapabilities();
        
    }
    
    public void render(Map map) {
        
        glClearColor(0.7f, 0.7f, 0.7f, 1.0f);
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        
        // TODO
 
        m_window.swapBuffers();
        
    }
    
}
