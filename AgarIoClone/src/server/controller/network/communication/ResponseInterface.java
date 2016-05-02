/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.controller.network.communication;

import java.util.List;
/**
 *
 * @author zoli-
 */
public interface ResponseInterface {

    public final static int STATUS_PLAYING = 0;
    public final static int STATUS_DEAD = 2;
    public final static int STATUS_QUIT = 3;

    abstract public void setStatus(int status);

    abstract public int getStatus();
    
    abstract public void setMapObjects(MapObjects mapObjects);
    
    abstract public MapObjects getMapObjects();
}
