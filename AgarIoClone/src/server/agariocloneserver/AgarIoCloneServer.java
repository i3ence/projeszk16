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
    
    public AgarIoCloneServer(int port) throws IOException  {
        super();
        core = new Core(45000);
                
		java.util.Timer timer = new java.util.Timer();
		timer.schedule(new TimerTask() {
		  @Override
		  public void run() {
                     
                          tick();
                      
		  }
		}, STEP_TIME,STEP_TIME);
    }
    
    
    public void tick(){	 	
         core.tick();
    }
    
    public static void main(String[] args) throws Exception{
        int port = Integer.parseInt(args[0]);
        new AgarIoCloneServer(port);
    }
}
