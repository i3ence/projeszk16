
package client.view.gl.object;

import client.view.gl.GlException;
import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL43.*;

/**
 * Thin wrapper around an OpenGL vertex buffer object.
 * @author yzsolt
 */
public class VertexBuffer extends GlObject {
    
    private int m_vertex_count;
    
    public VertexBuffer() throws GlException {
        
        super(GL_BUFFER);
        
        m_id = glGenBuffers();
        
        if (m_id == 0) {
            throw new GlException("OpenGL vertex buffer object creation has failed.");
        }
        
        m_vertex_count = 0;
        
    }
    
    public void upload(FloatBuffer float_buffer) {
        glBufferData(GL_ARRAY_BUFFER, float_buffer, GL_STATIC_DRAW);
        m_vertex_count = float_buffer.limit();
    }
    
    public int getVertexCount() {
        return m_vertex_count;
    }
    
    public void bind() {
        glBindBuffer(GL_ARRAY_BUFFER, m_id);
    }
    
    @Override
    public void delete() {
        glDeleteBuffers(m_id);
    }
    
}
