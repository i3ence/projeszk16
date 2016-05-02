
package client.view.gl.object;

import client.Util;
import client.view.gl.GlException;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL43.GL_SHADER;

/**
 * Thin wrapper around an OpenGL shader object.
 * @author yzsolt
 */
public class Shader extends GlObject {
    
    private final Type m_type;
    private boolean m_is_compiled;
    
    private static int typeToGlInt(Type type) {
        
        switch (type) {
            case VERTEX:
                return GL_VERTEX_SHADER;
            case FRAGMENT:
                return GL_FRAGMENT_SHADER;
        }
        
        throw new UnsupportedOperationException("Currently only vertex and fragment shaders are supported.");
        
    }
    
    public static enum Type {
        VERTEX, FRAGMENT
    };
    
    public Shader(Type type, String source) throws GlException {
        
        super(GL_SHADER);
        
        m_type = type;
        m_is_compiled = false;
     
        m_id = glCreateShader(typeToGlInt(type));
        
        if (m_id == 0) {
            throw new GlException("OpenGL shader creation has failed.");
        }
        
        glShaderSource(m_id, source);
        
    }
    
    public void compile() throws GlException {
        
        glCompileShader(m_id);

        IntBuffer result = BufferUtils.createIntBuffer(1);
        glGetShaderiv(m_id, GL_COMPILE_STATUS, result);

        if (result.get() == GL_FALSE) {

            result.clear();
            glGetShaderiv(m_id, GL_INFO_LOG_LENGTH, result);

            ByteBuffer info_log = BufferUtils.createByteBuffer(result.get());
            result.clear();
            glGetShaderInfoLog(m_id, result, info_log);

            throw new GlException(String.format("Shader compilation failed with the following message:\n%s.", Util.byteBufferToString(info_log)));
            
        }

        m_is_compiled = true;
        
    }
    
    public boolean isCompiled() {
        return m_is_compiled;
    }
    
    @Override
    public void delete() {
        glDeleteShader(m_id);
    }
    
}
