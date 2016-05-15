package server.controller.network.communication;

/**
 *
 * @author zoli-
 */
public class Request implements RequestInterface {
    
    private final float angle;
    private final int status;
    
    /**
     * Sets the angle of the cursor according to the x axis and the status of the client.
     * 
     * @param status
     * @param angle 
     */
    public Request(int status, float angle) {
        this.status = status;
        this.angle = angle;
    }

    /**
     * Returns the angle of the cursor according to the x axis.
     * 
     * @return The angle of the cursor.
     */
    @Override
    public float getAngle() {
        return this.angle;
    }

    /**
     * Returns the status of the client.
     * 
     * @return The status of the client.
     */
    @Override
    public int getStatus() {
        return this.status;
    }
}
