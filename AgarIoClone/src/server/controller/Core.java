package server.controller;

/**
 *
 * @author zoli-
 */
import server.model.Map;
import java.net.*;
import java.util.*;
import server.controller.network.ClientHandler;

public class Core {

    private Map map;
    private java.util.Map<Integer, ClientHandler> clients;
    private Random randomIdGenerator;
    private int maxPlayer;

    public Core() {
        this.map = new Map();
        this.clients = new Hashtable<Integer, ClientHandler>();
        this.randomIdGenerator = new Random();
        this.maxPlayer = 20;

        Thread handleClients = new Thread() {

        };
    }

    public void updateCell(int id, float angle, float length) {
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

    public int getUniqueId() {
        int id;
        do {
            id = this.randomIdGenerator.nextInt();
        } while (this.clients.containsKey(id));
        return id;
    }

    public int getMapSize() {
        return this.map.getSize();
    }
    
    public boolean canPlayerJoin() {
        return this.clients.size() < this.maxPlayer;
    }

}
