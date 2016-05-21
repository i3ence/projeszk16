
package client.view.renderer;

import client.view.VertexFormatDescriptor;
import client.view.gl.GlException;
import client.view.gl.object.Program;
import client.view.gl.object.VertexArray;
import client.view.gl.object.VertexBuffer;

import java.nio.FloatBuffer;
import org.joml.Matrix4f;
import org.joml.Vector2i;

import org.joml.Vector3f;

import org.lwjgl.BufferUtils;
import static org.lwjgl.opengl.GL11.*;

/**
 * A singleton helper class to render the background grid.
 * @author yzsolt
 */
public class GridRenderer {
    
    private static GridRenderer s_instance;
    
    private final VertexArray m_vertex_array;
    private final VertexBuffer m_vertex_buffer;
    
    private Vector3f m_color;
    
    private Matrix4f m_transformation;
    
    private void buildAndUploadGeometry() {

        FloatBuffer float_buffer = BufferUtils.createFloatBuffer(6 * 3);

        float z = -1.f;

        // The four points of an origo centered unit square in counter-clockwise order
        
        float[] bl = new float[] { -0.5f, -0.5f, z };
        float[] br = new float[] {  0.5f, -0.5f, z };
        float[] tr = new float[] {  0.5f,  0.5f, z };
        float[] tl = new float[] { -0.5f,  0.5f, z };

        float_buffer.put(bl);
        float_buffer.put(br);
        float_buffer.put(tl);
        
        float_buffer.put(tl);
        float_buffer.put(br);
        float_buffer.put(tr);

        float_buffer.position(0);

        m_vertex_buffer.bind();
        m_vertex_buffer.upload(float_buffer);

    }

    private GridRenderer() throws GlException {
        
        m_vertex_array = new VertexArray();
        m_vertex_array.bind();
        
        VertexFormatDescriptor vertex_format = new VertexFormatDescriptor();
        vertex_format.addAttribute(GL_FLOAT, 3, 0);

        m_vertex_buffer = new VertexBuffer();
        buildAndUploadGeometry();
                
        m_vertex_array.setVertexFormat(vertex_format);
        
        m_color = new Vector3f(0.6f, 0.6f, 0.6f);
        
        m_transformation = new Matrix4f().identity();
        
    }
    
    public static GridRenderer getInstance() throws GlException {
        
        if (s_instance == null) {
            s_instance = new GridRenderer();
        }
        
        return s_instance;
        
    }
    
    public void prepareRendering() {
        m_vertex_array.bind();
        m_vertex_buffer.bind();
    }
    
    public void render(Program program, Vector2i area, int row_count, int column_count, float line_weight) throws GlException {
        
        program.setUniform("u_color", m_color);
        
        float spacing_x = (float)area.x / column_count;
        float spacing_y = (float)area.y / row_count;
        
        int half_width = area.x / 2;
        int half_height = area.y / 2;
        
        // Draw vertical lines
        
        for (int x = 0; x < column_count + 1; x++) {
            
            m_transformation.translation(x * spacing_x, half_width, 0);
            m_transformation.scale(line_weight, area.y, 1);
            
            program.setUniform("u_world", m_transformation);
            
            m_vertex_array.draw(GL_TRIANGLES, m_vertex_buffer.getVertexCount());
            
            
        }
        
        // Draw horizontal lines
        
        for (int y = 0; y < row_count + 1; y++) {
                
            m_transformation.translation(half_height, y * spacing_y, 0);
            m_transformation.scale(area.x, line_weight, 1);
            
            program.setUniform("u_world", m_transformation);
            
            m_vertex_array.draw(GL_TRIANGLES, m_vertex_buffer.getVertexCount());

        }
        
    }
    
}
