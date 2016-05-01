/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.controller.network;

import java.net.*;
import java.io.*;
import server.controller.Core;
import communication.*;
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

    public ClientHandler(Socket socket, Core core) throws IOException {
        this.socket = socket;
        this.core = core;
        this.connectionAlive = true;
        this.oos = new ObjectOutputStream(socket.getOutputStream());
        this.ois = new ObjectInputStream(socket.getInputStream());
    }

    public Core getCore() {
        return this.core;
    }

    public void setConnectionAlive(boolean alive) {
        this.connectionAlive = alive;
    }

    public boolean getConnectionAlive() {
        return this.connectionAlive;
    }

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
    
    public void sendResponse(MapObjects mapObjects, int status) throws IOException {
        Response response = new Response();
        response.setStatus(status);
        response.setMapObjects(mapObjects);
        this.oos.writeObject(response);
        this.oos.flush();
    }

    public void abortConnection() throws IOException {
        Response response = new Response();
        response.setStatus(ResponseInterface.STATUS_QUIT);
        this.oos.writeObject(response);
        this.oos.flush();
        this.connectionAlive = false;
    }

    public void closeResources() throws IOException {
        this.oos.close();
        this.ois.close();
        this.socket.close();
    }

}
