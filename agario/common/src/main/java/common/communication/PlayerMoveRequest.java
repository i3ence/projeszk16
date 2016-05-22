
package common.communication;

import java.io.Serializable;

/**
 * Request sending player move data to the server.
 */
public class PlayerMoveRequest implements Request, Serializable {
    
    private final float angle;
    private final float multiplier;

    /**
     * Constructor.
     * @param angle Angle of cursor.
     * @param multiplier Velocity multiplier.
     */
    public PlayerMoveRequest(float angle, float multiplier) {
        this.angle = angle;
        this.multiplier = multiplier; // TODO: throw if multiplier not in [0, 1]
    }
    
    /**
     * Returns the cursor angle.
     * @return Cursor angle.
     */
    public float getAngle() {
        return angle;
    }
    
    /**
     * Returns the velocity multiplier.
     * @return Velocity multiplier.
     */
    public float getMultiplier() {
        return multiplier;
    }

}

