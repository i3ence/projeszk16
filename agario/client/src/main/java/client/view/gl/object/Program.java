
package client.view.gl.object;

import client.Util;
import client.view.gl.GlException;
import java.awt.Color;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

import java.nio.IntBuffer;
import java.util.HashMap;
import java.util.Map;

import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector4f;

import org.lwjgl.BufferUtils;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL43.GL_PROGRAM;

/**
 * Thin wrapper around an OpenGL program object.
 * @author yzsolt
 */
public class Program extends GlObject {
    
    private Map<String, Integer> m_uniform_locations;
    
    private int getUniformLocation(String uniform) throws GlException {
        
        if (!m_uniform_locations.containsKey(uniform)) {

            int location = glGetUniformLocation(m_id, uniform);

            if (location < 0) {
                throw new GlException(String.format("Couldn't find location for uniform '%s'.", uniform));
            }

            m_uniform_locations.put(uniform, location);

        }

        return m_uniform_locations.get(uniform);
        
    }
    
    public Program() throws GlException {
        
        super(GL_PROGRAM);
         
        m_id = glCreateProgram();
        
        if (m_id == 0) {
            throw new GlException("OpenGL program creation has failed.");
        }
        
        m_uniform_locations = new HashMap<>();
        
    }
    
    public void attachShader(Shader shader) {
        glAttachShader(m_id, shader.getId());
    }
    
    public void link() throws GlException {
        
        glLinkProgram(m_id);

        IntBuffer result = BufferUtils.createIntBuffer(1);
        glGetProgramiv(m_id, GL_LINK_STATUS, result);

        if (result.get() == GL_FALSE) {

            result.clear();
            glGetProgramiv(m_id, GL_INFO_LOG_LENGTH, result);

            ByteBuffer info_log = BufferUtils.createByteBuffer(result.get());
            result.clear();
            glGetProgramInfoLog(m_id, result, info_log);

            throw new GlException(String.format("Program linking has failed with the following message:\n%s.", Util.byteBufferToString(info_log)));

        }
 
    }
    
    public void use() {
        glUseProgram(m_id);
    }
    
    public void setUniform(String name, Vector3f value) throws GlException {
        
        FloatBuffer float_buffer = BufferUtils.createFloatBuffer(3);
        value.get(float_buffer);
        glUniform3fv(getUniformLocation(name), float_buffer);

    }
    
    public void setUniform(String name, Vector4f value) throws GlException {
        
        FloatBuffer float_buffer = BufferUtils.createFloatBuffer(3);
        value.get(float_buffer);
        glUniform4fv(getUniformLocation(name), float_buffer);

    }
    
    public void setUniform(String name, Matrix4f value) throws GlException {
        
        FloatBuffer float_buffer = BufferUtils.createFloatBuffer(16);
        value.get(float_buffer);
        glUniformMatrix4fv(getUniformLocation(name), false, float_buffer);

    }
    
    public void setUniform(String name, Color color) throws GlException {
        
        FloatBuffer float_buffer = BufferUtils.createFloatBuffer(3);
        float_buffer.put(color.getRGBColorComponents(null));
        float_buffer.flip();
        glUniform3fv(getUniformLocation(name), float_buffer);

    }
    
    @Override
    public void delete() {
        glDeleteProgram(m_id);
    }
    
}
