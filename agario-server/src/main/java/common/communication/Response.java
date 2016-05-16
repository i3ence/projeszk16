package common.communication;

/**
 *
 * @author zoli-
 */
public interface Response {

    public final static int STATUS_PLAYING = 0;
    public final static int STATUS_DEAD = 2;
    public final static int STATUS_QUIT = 3;

    abstract public int getStatus();  
    
    abstract public MapObjects getMapObjects();
}
