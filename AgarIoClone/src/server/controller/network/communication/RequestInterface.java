package server.controller.network.communication;

import server.model.helper.Coords;

/**
 *
 * @author zoli-
 */
public interface RequestInterface {
    
    public final static int STATUS_MENU = 0;
    public final static int STATUS_IN_GAME = 1;
    public final static int STATUS_QUIT = 2;
    
    abstract public void setCursorPosition(Coords coords);
    
    abstract public Coords getCursorPosition();
    
    abstract public void setStatus(int status);
    
    abstract public int getStatus();
    
}
