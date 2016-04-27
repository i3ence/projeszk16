
package client.view;

import org.lwjgl.glfw.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.*;

/**
 * A thin wrapper around a GLFW window.
 * @author yzsolt
 */
public class Window {
    
    // The GLFW window handle
    private final long m_handle;
    
    public Window(int width, int height, String title) {
        
        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);

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
    
}
