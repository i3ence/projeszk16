package communication;

/**
 *
 * @author zsiga
 */
public class RequestImpl implements Request{

    private float angle;
    private int status;

    public RequestImpl(float angle, int status) {
        this.angle = angle;
        this.status = status;
    }
    
    @Override
    public float getAngle() {
        return angle;
    }

    @Override
    public float getStatus() {
        return status;
    }

}
