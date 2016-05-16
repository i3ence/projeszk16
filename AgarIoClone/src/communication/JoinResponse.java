package communication;

import java.io.Serializable;

/**
 *
 * @author zoli-
 */
public class JoinResponse implements Serializable, JoinResponseInterface {

    private final int id;
    private final int status;
    private final int mapSize;

    /**
     * Sets the id of the player, the size of the map and the status of the response.
     * 
     * @param id The id of the player or 0
     * @param status The status of the join: STATUS_JOIN_ACCEPTED if player can join and STATUS_JOIN_REJECTED if not.
     * @param mapSize The size of the map.
     */
    public JoinResponse(int id, int status, int mapSize) {
        this.id = id;
        this.status = status;
        this.mapSize = mapSize;
    }

    /**
     * Returns the status of the response.
     * 
     * @return The status of the response.
     */
    @Override
    public int getStatus() {
        return this.status;
    }

    /**
     * Returns the id of the player.
     * 
     * @return The id of the player.
     */
    @Override
    public int getId() {
        return this.id;
    }

    /**
     * Returns the size of the map.
     * 
     * @return The size of the map.
     */
    @Override
    public int getMapSize() {
        return this.mapSize;
    }
}
