package communication;

/**
 *
 * @author zoli-
 */
public interface JoinResponse {

    public final static int STATUS_JOIN_ACCEPTED = 0;
    public final static int STATUS_JOIN_REJECTED = 1;

    public int getStatus();

    public int getId();

    public float getMapSize();
    
}
