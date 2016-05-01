/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package communication;

import java.io.Serializable;

/**
 *
 * @author zoli-
 */
public class JoinResponse implements Serializable, JoinResponseInterface {

    private int id;
    private int status;
    private int mapSize;

    public JoinResponse(int id, int status, int mapSize) {
        this.id = id;
        this.status = status;
        this.mapSize = mapSize;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public void setStatus(int status) {
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
    public int getMapSize() {
        return this.mapSize;
    }

    @Override
    public void setMapSize(int mapSize) {
       this.mapSize = mapSize;
    }

}
