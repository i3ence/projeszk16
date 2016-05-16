package communication;

import java.io.Serializable;

/**
 *
 * @author zsiga
 */
public class Request implements Serializable, RequestInterface {

    private final float angle;
    private final int status;

    /**
     * Sets the angle of the cursor according to the x axis and the status of the client.
     * 
     * @param status
     * @param angle 
     */
    public Request(float angle, int status) {
        this.angle = angle;
        this.status = status;
    }
    
    /**
     * Returns the angle of the cursor according to the x axis.
     * 
     * @return The angle of the cursor.
     */
    @Override
    public float getAngle() {
        return angle;
    }

    /**
     * Returns the status of the client.
     * 
     * @return The status of the client.
     */
    @Override
    public int getStatus() {
        return status;
    }

}
