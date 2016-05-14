package communication;

/**
 *
 * @author zsiga
 */
public class RequestImpl implements Request{

    private float angle;

    public RequestImpl(float angle) {
        this.angle = angle;
    }
    
    @Override
    public float getAngle() {
        return angle;
    }

}
