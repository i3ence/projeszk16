
package common;

/**
 * Various utilities for the model class
 */
public class Util {
 
    public static float clamp(float value, float min, float max) {
        
        if (value < min) {
            return min;
        } else if (value > max) {
            return max;
        } else {
            return value;
        }
        
    }
    
    public static float clampWithRadius(float value, float min, float max, float radius) {
        
        if (value < min + radius) {
            return min + radius;
        } else if (value > max - radius) {
            return max - radius;
        } else {
            return value;
        }
        
    }
    
}
