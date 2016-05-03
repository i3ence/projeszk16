package communication;

import client.model.Map;
import client.model.object.Cell;
import java.util.List;

/**
 *
 * @author zoli-
 */
public interface RequestInterface {
    
    public final static int STATUS_MENU = 0;
    public final static int STATUS_IN_GAME = 1;
    public final static int STATUS_QUIT = 2;
    
    // returns map status, to be handled by the GL library
    public Map getCurrentMap();
    
// return true on success, can be void
    public boolean sendCellLocation(Cell cell);
    
}
