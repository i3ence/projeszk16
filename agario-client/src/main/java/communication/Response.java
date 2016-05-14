package communication;

import java.util.List;
/**
 *
 * @author zoli-
 */
public interface Response {

    public final static int STATUS_PLAYING = 0;
    public final static int STATUS_DEAD = 2;

    abstract public void setStatus(int status);

    abstract public int getStatus();
    
    abstract public void setMapObjects(List mapObjects);
    
    abstract public List getMapObjects();
}
