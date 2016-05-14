package communication;

import java.io.Serializable;

/**
 *
 * @author zoli-
 */
public class JoinResponseImpl implements Serializable, JoinResponse {

    private int id;
    private int status;
    private float mapSize;

    public JoinResponseImpl(int id, int status) {
        this.id = id;
        this.status = status;
    }

    @Override
    public int getStatus() {
        return this.status;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public float getMapSize() {
        return mapSize;
    }

}
