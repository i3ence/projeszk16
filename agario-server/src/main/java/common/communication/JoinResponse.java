package common.communication;

/**
 *
 * @author zoli-
 */
public interface JoinResponse {

    public final static int STATUS_JOIN_ACCEPTED = 0;
    public final static int STATUS_JOIN_REJECTED = 1;

    abstract public int getStatus();

    abstract public int getId();
    
    abstract public int getMapSize();

}