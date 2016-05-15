package server.controller.network.communication;

/**
 *
 * @author zoli-
 */
public interface RequestInterface {
    
    public final static int STATUS_MENU = 0;
    public final static int STATUS_IN_GAME = 1;
    public final static int STATUS_QUIT = 2;  
    public final static int STATUS_REANIMATE = 3;
    
    abstract public float getAngle();
    
    abstract public int getStatus(); 
}
