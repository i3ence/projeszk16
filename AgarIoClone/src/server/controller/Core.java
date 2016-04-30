package server.controller;

/**
 *
 * @author zoli-
 */
import communication.*;
import java.io.IOException;
import server.model.Map;
import java.net.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import server.controller.network.ClientHandler;

public class Core {

    private Map map;
    private java.util.Map<Integer, ClientHandler> clients;
    private Random randomIdGenerator;
    private int maxPlayer;
    private final int port;
    private ServerSocket server;
    private boolean serverAlive;
    
    public Core(int port) throws IOException {
        this.port = port;
        this.map = new Map();
        this.clients = new Hashtable<Integer, ClientHandler>();
        this.randomIdGenerator = new Random();
        this.maxPlayer = 20;
        this.server = new ServerSocket(port);
        this.serverAlive = true;
        this.acceptClients(this);
    }
    
    public void acceptClients(Core core) {
        new Thread() {
            @Override
            public void run() {
                    while (serverAlive) {
                        try {
                            new ClientHandler(server.accept(), core).start();
                        } catch (IOException ex) {
                            Logger.getLogger(Core.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
            }
        }.start(); 
    }

    //may it should wait until tick finished
    public synchronized void updateCell(int id, float angle, float length) {
        this.map.updateCell(id, angle, length);
    }

    public void tick() {
        this.map.tick();
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public Map getMap() {
        return this.map;
    }

    public synchronized int getUniqueId() {
        int id;
        do {
            id = this.randomIdGenerator.nextInt();
        } while (this.clients.containsKey(id));
        return id;
    }

    public int getMapSize() {
        return this.map.getSize();
    }
    
    public synchronized boolean canPlayerJoin() {
        return this.clients.size() < this.maxPlayer;
    }
    
    public synchronized void addPlayer(int id, ClientHandler client, String name) {
        this.clients.put(id, client);
        this.map.addCell(id, name);
    }
    
    public synchronized void removePlayer(int id) {
        this.clients.remove(id);
        this.map.removeCell(id);
    }
    
    public void updateClients(MapObjects mapObjects) {
        
    }

}
