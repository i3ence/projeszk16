package common.communication;

import common.model.SimpleMapObject;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author zsiga
 */
public class SimpleResponseImpl implements SimpleResponse, Serializable{

    private final int status;
    private final List<? super SimpleMapObject> simpleMapObjects;

    /**
     * Sets the status and the mapObjects of the response.
     * 
     * @param status Player status
     * @param simpleMapObjects List of simple map objects to be sent
     */
    public SimpleResponseImpl(int status, List<? super SimpleMapObject> simpleMapObjects) {
        this.status = status;
        this.simpleMapObjects = simpleMapObjects;
    }
    
    /**
     * Returns the status of the player.
     * 
     * @return The status of the player.
     */
    @Override
    public int getStatus() {
        return this.status;
    }

    /**
     * Returns Simple Map Objects which contain the needed data about the game.
     * 
     * @return A list containing all objects on the map
     */
    @Override
    public List<? super SimpleMapObject> getMapObjects() {
        return this.simpleMapObjects;
    }
    
}
