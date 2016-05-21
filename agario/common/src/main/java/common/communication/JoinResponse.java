
package common.communication;

import java.io.Serializable;

/**
 * Response to JoinRequest, from the server to the client.
 */
public class JoinResponse implements Serializable {

    public enum Status {
        ACCEPTED,
        REJECTED
    }
    
    private final int playerId;
    private final Status status;
    private final int mapSize;

    /**
     * Sets the id of the player, the size of the map and the status of the response.
     * 
     * @param playerId The id of the player or 0
     * @param status The status of the join: Status.ACCEPTED if player can join or Status.REJECTED if not.
     * @param mapSize The size of the map.
     */
    public JoinResponse(int playerId, Status status, int mapSize) {
        this.playerId = playerId;
        this.status = status;
        this.mapSize = mapSize;
    }

    /**
     * Returns the status of the response.
     * 
     * @return The status of the response.
     */
    public Status getStatus() {
        return this.status;
    }

    /**
     * Returns the id of the player.
     * 
     * @return The id of the player.
     */
    public int getId() {
        return this.playerId;
    }

    /**
     * Returns the size of the map.
     * 
     * @return The size of the map.
     */
    public int getMapSize() {
        return this.mapSize;
    }
}
