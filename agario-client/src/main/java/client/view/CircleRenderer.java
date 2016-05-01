
package client.view;

import client.model.map.object.Cell;
import client.model.map.object.MapObject;
import client.view.gl.GlException;
import client.view.gl.object.Program;
import client.view.gl.object.VertexArray;
import client.view.gl.object.VertexBuffer;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import org.joml.Vector3f;
import org.lwjgl.BufferUtils;
import static org.lwjgl.opengl.GL11.*;

/**
 * A singleton helper class to render circles.
 * @author yzsolt
 */
public class CircleRenderer {
    
    private static CircleRenderer s_instance;
    private static int s_step_count = 32;
    
    private final VertexArray m_vertex_array;
    private final VertexBuffer m_vertex_buffer;
    
    private void buildAndUploadGeometry() {

        FloatBuffer float_buffer = BufferUtils.createFloatBuffer(s_step_count * 3 * 3);

        float rotation = 0.f;

        float[] center = new float[] { 0, 0, 0 };
        float[] f1 = new float[] { 1, 0, 0 };

        for (int i = 0; i < s_step_count; i++) {

            float_buffer.put(center);
            float_buffer.put(f1);

            rotation += Math.PI * 2 / s_step_count;

            f1[0] = (float)Math.cos(rotation);
            f1[1] = (float)Math.sin(rotation);

            float_buffer.put(f1);

        }

        float_buffer.position(0);

        m_vertex_buffer.bind();
        m_vertex_buffer.upload(float_buffer);

    }

    private CircleRenderer() throws GlException {
        
        m_vertex_array = new VertexArray();
        m_vertex_array.bind();
        
        VertexFormatDescriptor vertex_format = new VertexFormatDescriptor();
        vertex_format.addAttribute(GL_FLOAT, 3, 0);

        m_vertex_buffer = new VertexBuffer();
        buildAndUploadGeometry();
                
        m_vertex_array.setVertexFormat(vertex_format);
        
        
    }
    
    // TODO: read this from config
    public void setStepCount(int step_count) {
        
        if (step_count < 3) {
            throw new IllegalArgumentException("Step count for CircleRenderer must be at least 3.");
        }
        
        s_step_count = step_count;
        
        buildAndUploadGeometry();
        
    }
    
    public static CircleRenderer getInstance() throws GlException {
        
        if (s_instance == null) {
            s_instance = new CircleRenderer();
        }
        
        return s_instance;
        
    }
    
    public void prepareRendering() {
        m_vertex_array.bind();
        m_vertex_buffer.bind();
    }
    
    public void render(MapObject map_object, Program program) throws GlException {
        
        program.setUniform("u_color", map_object.getAttributes().getColor());
        
        m_vertex_array.draw(GL_TRIANGLES, m_vertex_buffer.getVertexCount());
        
    }
    
}
