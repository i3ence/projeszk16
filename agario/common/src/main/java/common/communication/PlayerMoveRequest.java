
package common.communication;

import java.io.Serializable;

/**
 *
 */
public class PlayerMoveRequest implements Request, Serializable {
    
    private final float angle;
    private final float speed;

    /**
     *
     */
    public PlayerMoveRequest(float angle, float speed) {
        this.angle = angle;
        this.speed = speed;
    }
    
    /**
     * 
     */
    public float getAngle() {
        return angle;
    }
    
    /**
     * 
     */
    public float getSpeed() {
        return speed;
    }

}

