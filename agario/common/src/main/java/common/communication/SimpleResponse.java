package common.communication;

import common.model.SimpleMapObject;
import java.util.List;

/**
 *
 * @author zsiga
 */
public interface SimpleResponse {

//    old statuses
    public final static int STATUS_MENU = 4;
    public final static int STATUS_IN_GAME = 5;
//    public final static int STATUS_QUIT = 6;
    public final static int STATUS_REANIMATE = 7;
    
//    statuses used in response
    public final static int STATUS_PLAYING = 0;
    public final static int STATUS_DEAD = 2;
    public final static int STATUS_QUIT = 3;

    public int getStatus();  
    
    public List<? super SimpleMapObject> getMapObjects();
}
