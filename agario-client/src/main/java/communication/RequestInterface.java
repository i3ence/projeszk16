package communication;

/**
 *
 * @author zoli-
 */
public interface RequestInterface{
    
    public final static int STATUS_MENU = 0;
    public final static int STATUS_IN_GAME = 1;
    public final static int STATUS_QUIT = 2;
    public final static int STATUS_REANIMATE = 3;
    
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
