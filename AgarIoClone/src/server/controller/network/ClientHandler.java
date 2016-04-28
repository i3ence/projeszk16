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

    private Socket socket;
    private final Core core;

    ClientHandler(Socket socket, Core core) {
        this.socket = socket;
        this.core = core;
    }

    public Core getCore() {
        return this.core;
    }

    @Override
    public void run() {
        try (
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());) 
        {
            JoinResponse joinResponse;
            if (this.core.canPlayerJoin()) {
                joinResponse = new JoinResponse(this.core.getUniqueId(), JoinResponseInterface.STATUS_JOIN_ACCEPTED, this.core.getMapSize());
                oos.writeObject(joinResponse);
                oos.flush();
            
                JoinAcknowledgmentInterface joinAcknowledgement = (JoinAcknowledgmentInterface) ois.readObject();
                
//                while(true) {
//                    //Communication here
//                }  


            } else {
                joinResponse = new JoinResponse(0, JoinResponseInterface.STATUS_JOIN_REJECTED, 0);
                oos.writeObject(joinResponse);
                oos.flush();
                socket.close();
            }          
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
