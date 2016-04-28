/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.controller.network;

import java.net.*;
import java.io.*;

/**
 *
 * @author zoli-
 */
public class ClientHandler extends Thread {

    Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());) {

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
