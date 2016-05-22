
package common.communication;

import java.io.Serializable;

/**
 *
 */
public class PlayerMoveRequest implements Request, Serializable {
    
    private final float angle;
    private final float multiplier;

    /**
     *
     */
    public PlayerMoveRequest(float angle, float multiplier) {
        this.angle = angle;
        this.multiplier = multiplier; // TODO: throw if multiplier not in [0, 1]
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
    public float getMultiplier() {
        return multiplier;
    }

}

