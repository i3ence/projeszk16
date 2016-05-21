package server.controller.network;

import common.communication.JoinResponse;
import common.communication.SimpleResponse;
import common.communication.Request;
import common.communication.SimpleResponseImpl;
import common.communication.JoinResponse;
import common.communication.JoinRequest;
import java.net.*;
import java.io.*;
import java.util.List;
import server.controller.Core;
import java.util.logging.Level;
import java.util.logging.Logger;
import common.model.SimpleMapObject;

/**
 *
 * @author zoli-
 */
public class ClientHandler extends Thread {

    private final Socket socket;
    private final Core core;
    private boolean connectionAlive;
    private final ObjectOutputStream oos;
    private final ObjectInputStream ois;

    /**
     * Initializes the components which needed for the communication with the client.
     * 
     * @param socket The socket instance to communicate with the client.
     * @param core The instance of the core.
     * @throws IOException 
     */
    public ClientHandler(Socket socket, Core core) throws IOException {
        this.socket = socket;
        this.core = core;
        this.connectionAlive = true;
        this.oos = new ObjectOutputStream(socket.getOutputStream());
        this.ois = new ObjectInputStream(socket.getInputStream());
    }

    /**
     * Returns the server's core instance.
     * 
     * @return The core instance of the server.
     */
    public Core getCore() {
        return this.core;
    }

    /**
     * Sets the connection with the client alive or not.
     * 
     * @param alive The boolean which determines if the connection is alive with the client.
     */
    public void setConnectionAlive(boolean alive) {
        this.connectionAlive = alive;
    }

    /**
     * Returns the state of the connection with the client.
     * 
     * @return State of the connection with the client.
     */
    public boolean getConnectionAlive() {
        return this.connectionAlive;
    }

    /**
     * The main method of this class. The communication with the individual clients are made here. If a player can join the client
     * gets a JoinResponse object with the status of STATUS_JOIN_ACCEPTED, the id of the player and the size of the map. Then the client
     * sends it's name and the player is added to the game. Until the client quits the game the server continously listening for the Request objects
     * which contains the player's cursor's data to move. 
     * If the server is full the JoinResponse object with the status of STATUS_JOIN_REJECTED send back and the communication will be closed.
     */
    @Override
    public void run() {
        try {
            JoinResponse joinResponse;
            if (this.core.canPlayerJoin()) {
                int id = this.core.getUniqueId();
                
                JoinRequest joinRequest = (JoinRequest) ois.readObject();
                String name = joinRequest.getName();
                this.core.addPlayer(id, this, name);
                
                joinResponse = new JoinResponse(id, JoinResponse.Status.ACCEPTED, this.core.getMapSize());
                this.oos.writeObject(joinResponse);
                this.oos.flush();
                
                Request request = null;
                while (this.connectionAlive) {
                    try { request = (Request) ois.readObject(); }
                    catch (SocketException e) { connectionAlive = false; }
                    switch (request.getStatus()) {
                        case Request.STATUS_QUIT:
                            this.connectionAlive = false;
                            break;
                        case Request.STATUS_IN_GAME:
                            this.core.updateCell(id, request.getAngle());
                            break;
                        case Request.STATUS_MENU:
                            this.core.updateCell(id, 0);
                            break;
                        case Request.STATUS_REANIMATE:
                            this.core.updateCell(id, request.getAngle());
                            this.core.reAnimateCell(id, name);
                            break;
                        default:
                            break;
                    }
                }
                this.core.removePlayer(id);
                this.closeResources();
            } else {
                joinResponse = new JoinResponse(0, JoinResponse.Status.REJECTED, 0);
                this.oos.writeObject(joinResponse);
                this.oos.flush();
                this.closeResources();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    /**
     * Sends the actual state of the game to the client. The status represents whether the player is alive or not.
     * 
     * @param simpleMapObjects a SimpleMapObjects object which contains only the information the players need.
     * @param status represents whether the player is alive or not.
     * @throws IOException 
     */
    public void sendSimpleResponse(List<? super SimpleMapObject> simpleMapObjects, int status) throws IOException {
        SimpleResponse simpleResponse = new SimpleResponseImpl(status, simpleMapObjects);
        this.oos.writeObject(simpleResponse);
        this.oos.flush();
    }
    
    

    /**
     * Sends a last packet to the client telling him to close the connection.
     * 
     * @throws IOException 
     */
    public void abortConnection() throws IOException {
        SimpleResponse response = new SimpleResponseImpl(SimpleResponse.STATUS_QUIT, null);
        this.oos.writeObject(response);
        this.oos.flush();
        this.connectionAlive = false;
    }

    /**
     * Closes all resources of the communication.
     * 
     * @throws IOException 
     * @throws java.net.SocketException 
     */
    public void closeResources() throws IOException, SocketException {
            this.oos.close();
            this.ois.close();
            this.socket.close();
    }

}
