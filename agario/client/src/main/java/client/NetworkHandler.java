package client;

import common.communication.ConnectionError;
import common.communication.StatusChangeRequest;
import common.communication.JoinRequest;
import common.communication.StatusChangeRequest;
import common.model.MapObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import common.communication.JoinResponse;
import common.communication.MapDataResponse;
import common.communication.Request;
import common.communication.Response;

/**
 *
 * @author hegym
 */
public class NetworkHandler {
    
    // communication
    private static Socket client;
    private static OutputStream outStream;
    private static ObjectOutputStream objectOutStream;
    private static InputStream inStream;
    private static ObjectInputStream objectInStream;

    // response
    private static int id;
    private static float mapSize;
    
    //init return values
    public final static int SUCCESS = 0;
    public final static int CONNECTION_FAIL = 1;
    public final static int IOSTREAM_FAIL = 2;
    public final static int ACKNOWLEDGMENT_FAIL = 3;
    public final static int SERVER_FULL = 4;
    
    //singleton
    private static NetworkHandler nethandler = new NetworkHandler();
    
    private NetworkHandler() {  }
    
    public static NetworkHandler getInstance( ) {
      return nethandler;
   }
    
    /**
     * Initialize connection to the server.
     * @param ipAddress IP Address of the server.
     * @param portNumber Port to connect to.
     * @param name Name of the player.
     * @return JoinResponse
     * @throws common.communication.ConnectionError
     */
    public static JoinResponse initConnection(String ipAddress, int portNumber, String name) throws ConnectionError {
        
        // Init socket
        
        try { 
            client = new Socket(ipAddress, portNumber); 
        } catch (IOException e) { 
            throw new ConnectionError(ConnectionError.Type.CONNECTION_FAIL); 
        }

        // Init streams
        
        try {
            outStream = client.getOutputStream();
            inStream = client.getInputStream();
            objectOutStream = new ObjectOutputStream(outStream);
            objectInStream = new ObjectInputStream(inStream);
        } catch (IOException e) { 
            throw new ConnectionError(ConnectionError.Type.IOSTREAM_FAIL); 
        }

        // Try to join
        
        try {
            
            JoinRequest joinRequest = new JoinRequest(name);
            objectOutStream.writeObject(joinRequest);
            objectOutStream.flush();
            
            JoinResponse response = (JoinResponse)objectInStream.readObject();
            
            if (response.getStatus() == JoinResponse.Status.ACCEPTED) {
                return response;
            } else {
                throw new ConnectionError(ConnectionError.Type.SERVER_FULL); 
            }
            
        } catch (ClassNotFoundException | IOException e) {
            //Logger.getLogger(AgarioGame.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println(e);
            throw new ConnectionError(ConnectionError.Type.ACKNOWLEDGMENT_FAIL); 
        }

    }
    
    /**
     * 
     * @param request
     */
    public static void sendRequest(Request request) {

        try {
            objectOutStream.writeObject(request);
            objectOutStream.flush();
        } catch (IOException ex) {
            Logger.getLogger(AgarioGame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    /**
     * Receive the simple response from server
     * !! if response is not received, this will throw NPE. Need to investigate!
     * @return server response
     */
    public static Response waitForResponse() {
        
        Response response = null;
        
        try {
            response = (Response)objectInStream.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(AgarioGame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return response;
        
    }
}
