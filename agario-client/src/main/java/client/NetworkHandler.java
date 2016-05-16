/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import client.model.object.MapObject;
import communication.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
//import sun.reflect.generics.reflectiveObjects.NotImplementedException;

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
     * @return State of the connection. 0 - Success, 1-3 - Connection error, 4 - Full server.
     */
    public static int initConnection(String ipAddress, int portNumber, String name) {
        try { client = new Socket(ipAddress, portNumber); }
        catch (IOException e) { return CONNECTION_FAIL; }

        // init streams
        try {
            outStream = client.getOutputStream();
            inStream = client.getInputStream();
            objectOutStream = new ObjectOutputStream(outStream);
            objectInStream = new ObjectInputStream(inStream);
        } catch (IOException e) { return IOSTREAM_FAIL; }

        // acknowledgment from server
        try {
            JoinResponseInterface response = (JoinResponseInterface) objectInStream.readObject();
            if (response.getStatus() == 0) {
                // accepted
                mapSize = response.getMapSize();
                id = response.getId();
                JoinAcknowledgment joinAck = new JoinAcknowledgment(name);
                objectOutStream.writeObject(joinAck);
                objectOutStream.flush();
            }
            else {
                // rejected
                return SERVER_FULL;
            }
        } catch (ClassNotFoundException | IOException e) {
            //Logger.getLogger(AgarioGame.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println(e);
            return ACKNOWLEDGMENT_FAIL;
        }
        
        return SUCCESS;
    }
    
    /**
     * Send request to server, call this as much as possible.
     * @param angle the angle where player cell is moving
     * @param status Current status of the player.
     */
    public static void sendRequest(float angle, int status) {
        Request request = new Request(angle, status);
        try {
            objectOutStream.writeObject(request);
            objectOutStream.flush();
        } catch (IOException ex) {
            Logger.getLogger(AgarioGame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Receive the response from server
     * !! if response is not received, this will throw NPE. Need to investigate!
     * @return server response
     */
    public static MapObjects handleResponse() {
        ResponseInterface response = null;
        try {
            response = (Response) objectInStream.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(AgarioGame.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response.getMapObjects();
    }
}
