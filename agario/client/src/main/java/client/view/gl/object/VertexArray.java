
package client.view.gl.object;

import client.view.VertexFormatDescriptor;
import client.view.gl.GlException;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

/**
 * Thin wrapper around an OpenGL vertex array object.
 * @author yzsolt
 */
public class VertexArray extends GlObject {
    
    private VertexFormatDescriptor m_vertex_format;
    
    public VertexArray() throws GlException {
        
        super(GL_VERTEX_ARRAY);
        
        m_id = glGenVertexArrays();
        
        if (m_id == 0) {
            throw new GlException("OpenGL vertex array object creation has failed.");
        }
        
    }
    
    public void setVertexFormat(VertexFormatDescriptor vertex_format) {
        	
        int location = 0;

        for (VertexFormatDescriptor.Attribute attribute : vertex_format.getAttributes()) {

            glEnableVertexAttribArray(location);
            glVertexAttribPointer(location, attribute.componentCount, attribute.type, attribute.isNormalized, (int)vertex_format.getSize(), attribute.offset);
            
            location++;

        }

        m_vertex_format = vertex_format;
        
    }
    
    public void draw(int mode, int vertexCount) {
        glDrawArrays(mode, 0, vertexCount);
    }
    
    public void bind() {
        glBindVertexArray(m_id);
    }
    
    @Override
    public void delete() {
        glDeleteVertexArrays(m_id);
    }
    
}
