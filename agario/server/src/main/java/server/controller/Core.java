package server.controller;

/**
 *
 * @author zoli-
 */
import java.io.IOException;
import server.model.Map;
import java.net.*;
import java.util.*;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;
import server.controller.network.ClientHandler;
import common.model.MapObject;

public final class Core {

    private Map map;
    private final java.util.Map<Integer, ClientHandler> clients;
    private final Random randomIdGenerator;
    private final int maxPlayer;
    private final int port;
    private final ServerSocket server;
    private boolean serverAlive;
    
    /**
     * Initializes core components of the server such as Map which is the engine of the game and creates the server socket with the given port.
     * The clients will be contained in the clients hash map. 
     * 
     * @param port The server will listen on this port.
     * @throws IOException 
     */
    public Core(int port) throws IOException {
        this.port = port;
        this.map = new Map(this);
        this.clients = new Hashtable<>();
        this.randomIdGenerator = new Random();
        this.maxPlayer = 20;
        this.server = new ServerSocket(this.port);
        this.serverAlive = true;
        this.acceptClients(this);
    }
    
    /**
     * This method is watching for new client connections and as soon as a client connects creates a new client handler.
     * 
     * @param core The core instance of the server.
     */
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

    /**
     * If a client sends a new packet then update the cell of the client.
     * 
     * @param id Id of the cell (player).
     * @param angle The angle of the player's cursor according to the x axis.
     * @param multiplier
     */
    public synchronized void updateCell(int id, float angle, float multiplier) {
        this.map.updateCell(id, angle, multiplier);
    }

    /**
     * Triggers tick on the map object.
     * 
     * @throws IOException
     */
    public void tick() throws IOException  {
        this.map.tick();
    }

    /**
     * Sets the map object.
     * 
     * @param map The map object which is the game engine.
     */
    public void setMap(Map map) {
        this.map = map;
    }

    /**
     * Returns the map object which is the game engine.
     * 
     * @return The map object which is the game engine.
     */
    public Map getMap() {
        return this.map;
    }

    /**
     * Generates a random integer which is unique among the id-s of the clients.
     * 
     * @return A unique integer which will identify the player.
     */
    public synchronized int getUniqueId() {
        int id;
        do {
            id = this.randomIdGenerator.nextInt();
        } while (this.clients.containsKey(id));
        return id;
    }

    /**
     * Returns the size of the map.
     * 
     * @return The size of the map.
     */
    public int getMapSize() {
        return this.map.getSize();
    }
    
    /**
     * Determines if a player can join the game or not.
     * 
     * @return True if a player can join the game, false otherwise.
     */
    public synchronized boolean canPlayerJoin() {
        return this.clients.size() < this.maxPlayer;
    }
    
    /**
     * Adds a new player to the game. It adds to the clients hashmap of the core and to the map.
     * 
     * @param id The id of the player.
     * @param client The client handler of the player.
     * @param name The name of the player.
     */
    public synchronized void addPlayer(int id, ClientHandler client, String name) {
        this.clients.put(id, client);
        this.map.addCell(id, name);
    }
    
    /**
     * Removes a player from the game. Removes from the core and from the map as well.
     * 
     * @param id The id of the player.
     */
    public synchronized void removePlayer(int id) {
        this.clients.remove(id);
        this.map.removeCell(id);
    }
    
    
    /**
     * Sends the actual state of the game and the status of the player itself to every player in a form of simplified map objects. 
     * If a client cannot be reached it gets removed from the server.
     * 
     * @param simpleMapObjects a SimpleMapObjects object which contains only the information the players need.
     * @param statuses The individual statuses of the cells, mapped by their id.
     * @throws java.io.IOException
     */
    public void updateClientsWithSimpleObjects(List<MapObject> mapObjects, HashMap<Integer, Integer> statuses) throws IOException {
        List<Integer> disconnected = new ArrayList<>();
        for (Entry currentEntry : this.clients.entrySet()) {
            try {
                ClientHandler currentClient = (ClientHandler) currentEntry.getValue();
                currentClient.sendMapData(mapObjects);//, statuses.get((int)currentEntry.getKey()));
            } catch (SocketException e) {
                disconnected.add((Integer)currentEntry.getKey());
            }
        }
        for (int toRemove : disconnected) {
            clients.remove(toRemove);
        }
    }
    
    
    /**
     * Re-animates the player's cell.
     * 
     * @param id The id of the player. 
     * @param name The name of the player.
     */
    public void reAnimateCell(int id, String name) {
        this.map.reAnimateCell(id, name);
    }
    
    
    /**
     * Frees up the port.
     */
    public void closeServer() throws IOException {
        server.close();
    }

}
