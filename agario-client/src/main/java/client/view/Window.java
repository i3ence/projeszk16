
package client.view;

import java.nio.DoubleBuffer;
import java.nio.IntBuffer;
import org.joml.Vector2i;
import org.lwjgl.BufferUtils;

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
    
    // This is pretty ugly, just as Java itself
    private DoubleBuffer m_cursor_position_x;
    private DoubleBuffer m_cursor_position_y;
    private Vector2i m_cursor_position;
    
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
        
        m_cursor_position_x = BufferUtils.createDoubleBuffer(1);
        m_cursor_position_y = BufferUtils.createDoubleBuffer(1);
        m_cursor_position = new Vector2i().zero();
        
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
    
    public Vector2i getCursorPosition() {
        
        glfwGetCursorPos(m_handle, m_cursor_position_x, m_cursor_position_y);
        m_cursor_position.set((int)m_cursor_position_x.get(), (int)m_cursor_position_y.get());
        
        m_cursor_position_x.position(0);
        m_cursor_position_y.position(0);
        
        return m_cursor_position;
        
    }
    
    public void setCursorLocked(boolean locked) {
        glfwSetInputMode(m_handle, GLFW_CURSOR, locked ? GLFW_CURSOR_DISABLED : GLFW_CURSOR_NORMAL);
    }
    
}
