package server.agariocloneserver;

/**
 *
 * @author zoli-
 */
import java.io.IOException;
import server.controller.Core;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AgarIoCloneServer {

    private final Core core;
    private final long STEP_TIME = 30;
    
    /**
     * Creates the core of the server and ticking the game every STEP_TIME milliseconds.
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

              try {
                  tick();
              } catch (IOException ex) {
                  Logger.getLogger(AgarIoCloneServer.class.getName()).log(Level.SEVERE, null, ex);
              }

          }
        }, STEP_TIME,STEP_TIME);
    }
    
    /**
     * Ticks the core of the server.
     * 
     * @throws IOException 
     */
    public void tick() throws IOException{	 	
         core.tick();
    }
    
    /**
     * Creates the server instance.
     * 
     * @param args The port the server will listen on.
     * @throws Exception 
     */
    public static void main(String[] args) throws Exception{
        int port = 12345;
        new AgarIoCloneServer(port);
    }
}
