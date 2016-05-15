package communication;

import client.model.object.MapObject;
import java.util.List;

public class ResponseImpl implements Response {

    private final int status;
    private final List<MapObject> mapObjects;
    
    /**
     * Sets the status and the mapObjects of the response.
     * 
     * @param status
     * @param mapObjects 
     */
    public ResponseImpl(int status, List<MapObject> mapObjects) {
        this.status = status;
        this.mapObjects = mapObjects;
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
     * Returns the mapObjects object which contains the informations about the game.
     * 
     * @return The mapObjects object.
     */
    @Override
    public List<MapObject> getMapObjects() {
        return this.mapObjects;
    }
    
}
