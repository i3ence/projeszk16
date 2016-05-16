package common.communication;

import java.io.Serializable;

/**
 *
 * @author zoli-
 */
public class ResponseImpl implements Serializable, Response {
    
    private final int status;
    private final MapObjectsImpl mapObjects;

    /**
     * Sets the status and the mapObjects of the response.
     * 
     * @param status
     * @param mapObjects 
     */
    public ResponseImpl(int status, MapObjectsImpl mapObjects) {
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
    public MapObjectsImpl getMapObjects() {
        return this.mapObjects;
    }
    
}
