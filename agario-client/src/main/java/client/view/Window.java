
package client.view;

import org.joml.Vector2i;

import org.lwjgl.glfw.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.*;

/**
 * A thin wrapper around a GLFW window.
 * @author yzsolt
 */
public class Window {
    
    /** The native GLFW window handle. */
    private final long m_handle;
    
    private final Vector2i m_size;
    
    public Window(int width, int height, String title) {
        
        m_size = new Vector2i(width, height);
        
        glfwDefaultWindowHints();
        
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE);
        
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
        
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);

        m_handle = glfwCreateWindow(width, height, title, NULL, NULL);
        
        if (m_handle == NULL) {
            throw new RuntimeException("Failed to create the GLFW window.");
        }
        
        GLFWVidMode video_mode = glfwGetVideoMode(glfwGetPrimaryMonitor());

        glfwSetWindowPos(m_handle,
            (video_mode.width() - width) / 2,
            (video_mode.height() - height) / 2
        );
        
    }
    
    public long getHandle() {
        return m_handle;
    }
    
    public void close() {
        glfwDestroyWindow(m_handle);
    }
    
    public void makeContextCurrent() {
        glfwMakeContextCurrent(m_handle);
    }
    
    public void show() {
        glfwShowWindow(m_handle);
    }
    
    public void setVsync(boolean vsyncOn) {
        glfwSwapInterval(vsyncOn ? 1 : 0);
    }
    
    public boolean shouldClose() {
        return glfwWindowShouldClose(m_handle) == GLFW_TRUE;
    }
    
    public void pollEvents() {
        glfwPollEvents();
    }
    
    public void swapBuffers() {
        glfwSwapBuffers(m_handle);
    }
    
    public Vector2i getSize() {
        return m_size;
    }
    
    public void setCursorLocked(boolean locked) {
        glfwSetInputMode(m_handle, GLFW_CURSOR, locked ? GLFW_CURSOR_DISABLED : GLFW_CURSOR_NORMAL);
    }
    
}
