
package client.view;

import client.view.gl.GlException;
import client.view.gl.object.Program;
import client.view.settings.CameraSettings;

import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector2i;
import org.joml.Vector3f;

/**
 * A classic perspective camera with adjustable parameters.
 * @author yzsolt
 */
public class Camera {
    
    private CameraSettings m_settings;
    
    private Matrix4f m_view;
    private Matrix4f m_projection;
    
    private Vector3f m_position;
    private Vector3f m_direction;
    private Vector3f m_up;
    
    private void updateView() {
        
        Vector3f look_at = new Vector3f();
        m_position.add(m_direction, look_at);
        
        m_view.setLookAt(m_position, look_at, m_up);
        
    }
    
    public Camera(CameraSettings settings, Vector2i screen_size) {
        
        m_settings = settings;
        
        float aspect_ratio = (float)screen_size.x / screen_size.y;
        
        m_projection = new Matrix4f().perspective(settings.fieldOfView, aspect_ratio, settings.near, settings.far);
        
        m_position = new Vector3f(0, 0, 0);
        m_direction = new Vector3f(0, 0, -1);
        m_up = new Vector3f(0, 1, 0);

        m_view = new Matrix4f();
        updateView();
        
    }
    
    public Vector3f getPosition() {
        return m_position;
    }
    
    public void setPosition(Vector3f position) {
        m_position = position;
        updateView();
    }
    
    public void setPosition(Vector2f position) {
        m_position.x = position.x;
        m_position.y = position.y;
        updateView();
    }
    
    public void setHeight(float height) {
        m_position.z = height;
        updateView();
    }
    
    public void rotate(float rotation_delta) {
        
        float current_rotation = (float)Math.atan2(m_up.y, m_up.x);
        current_rotation += rotation_delta;
        
        m_up.x = (float)Math.cos(current_rotation);
        m_up.y = (float)Math.sin(current_rotation);
        
        updateView();
        
    }
    
    public void setUniforms(Program program) throws GlException {
        
        program.setUniform("u_view", m_view);
        program.setUniform("u_projection", m_projection);
        
    }
    
}
