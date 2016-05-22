
package common.communication;

import common.model.MapObject;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author zsiga
 */
public class MapDataResponse implements Response, Serializable {

    private final List<MapObject> mapObjects;

    /**
     * Sets the status and the mapObjects of the response.
     * 
     * @param mapObjects List of simple map objects to be sent
     */
    public MapDataResponse(List<MapObject> mapObjects) {
        this.mapObjects = mapObjects;
    }

    /**
     * Returns Simple Map Objects which contain the needed data about the game.
     * 
     * @return A list containing all objects on the map
     */
    public List<MapObject> getMapObjects() {
        return this.mapObjects;
    }
    
}
