package communication;

/**
 *
 * @author zoli-
 */
public interface Request{
    
    public final static int STATUS_MENU = 0;
    public final static int STATUS_IN_GAME = 1;
    public final static int STATUS_QUIT = 2;
    
    /**
     * 
     * @return angle, where player is headed 
     */
    public float getAngle();
    
}
