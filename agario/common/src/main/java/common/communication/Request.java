package common.communication;

/**
 *
 * @author zoli-
 */
public interface Request{
  
//    old statuses
    public final static int STATUS_MENU = 4;
    public final static int STATUS_IN_GAME = 5;
//    public final static int STATUS_QUIT = 6;
    public final static int STATUS_REANIMATE = 7;
    
//    statuses used in response
    public final static int STATUS_PLAYING = 0;
    public final static int STATUS_DEAD = 2;
    public final static int STATUS_QUIT = 3;
    
    /**
     * 
     * @return angle, where player is headed 
     */
    abstract public float getAngle();
    
    /**
     * 
     * @return player status (in menu, in game or quit)
     */
    abstract public int getStatus();
    
}
