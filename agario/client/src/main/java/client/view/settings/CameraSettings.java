
package client.view.settings;

/**
 * Settings for the camera.
 * @author yzsolt
 */
public class CameraSettings {
    
    /** Distance to the near clipping plane. */
    public float near;
    
    /** Distance to the far clipping plane. */
    public float far;
    
    /** The vertical field of view, in radians. */
    public float fieldOfView;
    
    /** This constructor sets some reasonable default values. */
    public CameraSettings() {
        near = 10.f;
        far = 1000.f;
        fieldOfView = (float)Math.toRadians(45);
    }
    
}
