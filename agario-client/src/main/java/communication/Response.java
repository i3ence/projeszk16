package communication;

import java.util.List;
/**
 *
 * @author zoli-
 */
public interface Response {

    public final static int STATUS_PLAYING = 0;
    public final static int STATUS_DEAD = 2;

    public int getStatus();
    
    public List getMapObjects();
}
