package server.controller.network;

import server.controller.network.communication.JoinResponse;
import server.controller.network.communication.MapObjects;
import server.controller.network.communication.RequestInterface;
import server.controller.network.communication.JoinResponseInterface;
import server.controller.network.communication.JoinAcknowledgmentInterface;
import server.controller.network.communication.ResponseInterface;
import server.controller.network.communication.Response;
import java.net.*;
import java.io.*;
import server.controller.Core;
import java.util.logging.Level;
import java.util.logging.Logger;

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
     * Returns the server's core intance.
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
     * which contains the player's cursor's datas to move. 
     * If the server is full the JoinResponse object with the status of STATUS_JOIN_REJECTED send back and the communication will be closed.
     */
    @Override
    public void run() {
        try {
            JoinResponse joinResponse;
            if (this.core.canPlayerJoin()) {
                int id = this.core.getUniqueId();
                joinResponse = new JoinResponse(id, JoinResponseInterface.STATUS_JOIN_ACCEPTED, this.core.getMapSize());
                this.oos.writeObject(joinResponse);
                this.oos.flush();

                JoinAcknowledgmentInterface joinAcknowledgement = (JoinAcknowledgmentInterface) ois.readObject();
                String name = joinAcknowledgement.getName();
                this.core.addPlayer(id, this, name);
                RequestInterface request;
                while (this.connectionAlive) {
                    request = (RequestInterface) ois.readObject();
                    switch (request.getStatus()) {
                        case RequestInterface.STATUS_QUIT:
                            this.connectionAlive = false;
                            break;
                        case RequestInterface.STATUS_IN_GAME:
                            this.core.updateCell(id, id, id);
                            break;
                        case RequestInterface.STATUS_MENU:
                            this.core.updateCell(id, 0, 0);
                            break;
                        default:
                            break;
                    }
                }
                this.core.removePlayer(id);
                this.closeResources();
            } else {
                joinResponse = new JoinResponse(0, JoinResponseInterface.STATUS_JOIN_REJECTED, 0);
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
     * Sends the actual state of the game to the client. The status represents wether the player is alive or not.
     * 
     * @param mapObjects The MapObjects object containing every information of the game.
     * @param status
     * @throws IOException 
     */
    public void sendResponse(MapObjects mapObjects, int status) throws IOException {
        Response response = new Response(status, mapObjects);
        this.oos.writeObject(response);
        this.oos.flush();
    }

    /**
     * Sends a last packet to the client telling him to close the connection.
     * 
     * @throws IOException 
     */
    public void abortConnection() throws IOException {
        Response response = new Response(ResponseInterface.STATUS_QUIT, null);
        this.oos.writeObject(response);
        this.oos.flush();
        this.connectionAlive = false;
    }

    /**
     * Closes all resources of the communication.
     * 
     * @throws IOException 
     */
    public void closeResources() throws IOException {
        this.oos.close();
        this.ois.close();
        this.socket.close();
    }

}
