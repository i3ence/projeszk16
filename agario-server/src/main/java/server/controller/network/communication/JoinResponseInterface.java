/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.controller.network.communication;

/**
 *
 * @author zoli-
 */
public interface JoinResponseInterface {

    public final static int STATUS_JOIN_ACCEPTED = 0;
    public final static int STATUS_JOIN_REJECTED = 1;

    abstract public void setId(int id);

    abstract public void setStatus(int status);

    abstract public int getStatus();

    abstract public int getId();
    
    abstract public int getMapSize();

    abstract public void setMapSize(int mapSize);

}
