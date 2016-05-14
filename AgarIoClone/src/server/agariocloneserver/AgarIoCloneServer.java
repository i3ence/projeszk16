package server.agariocloneserver;

/**
 *
 * @author zoli-
 */
import java.io.IOException;
import server.controller.Core;
import java.util.*;

public class AgarIoCloneServer {

    private final Core core;
    private final long STEP_TIME = 30;
    
    /**
     * Creates the core of the server and ticking the game every STEP_TIME milisecundum.
     * 
     * @param port The server will listen on this given port number.
     * @throws IOException 
     */
    public AgarIoCloneServer(int port) throws IOException  {
        super();
        core = new Core(port);
                
		java.util.Timer timer = new java.util.Timer();
		timer.schedule(new TimerTask() {
		  @Override
		  public void run() {
                     
                          tick();
                      
		  }
		}, STEP_TIME,STEP_TIME);
    }
    
    /**
     * Ticks the core of the server.
     */
    public void tick(){	 	
         core.tick();
    }
    
    /**
     * Creates the server instance.
     * 
     * @param args The port the server will listen on.
     * @throws Exception 
     */
    public static void main(String[] args) throws Exception{
        int port = Integer.parseInt(args[0]);
        new AgarIoCloneServer(port);
    }
}
