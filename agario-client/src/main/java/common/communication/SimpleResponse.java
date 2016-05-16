package common.communication;

import common.model.SimpleMapObject;
import java.util.List;

/**
 *
 * @author zsiga
 */
public interface SimpleResponse {

    public final static int STATUS_PLAYING = 0;
    public final static int STATUS_DEAD = 2;
    public final static int STATUS_QUIT = 3;

    public int getStatus();  
    
    public List<? super SimpleMapObject> getMapObjects();
}
