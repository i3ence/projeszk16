package server.model;

import java.awt.Color;
import java.io.IOException;
import common.communication.MapObjectsImpl;
import server.model.object.*;
import server.model.factory.*;
import java.util.*;
import java.util.Map.Entry;
import server.controller.Core;
import common.model.SimpleMapObject;
import common.communication.Response;

/**
 *
 * @author zoli-
 */
public class Map {

    public List<Food> foods;
    public List<Thorn> thorns;
    public java.util.Map<Integer, Cell> cells;
    private final FoodFactory foodFactory;
    private final ThornFactory thornFactory;
    private final int size;
    private final Core core;
    private final int maxSpeedOfCells;
    private final Random rand;

    /**
     * Initializes the components of the game engine and creates 10 thorn objects on the map.
     * 
     * @param core The core instance of the server core.
     */
    public Map(Core core) {
        this.core = core;
        this.foods = new ArrayList<Food>();
        this.thorns = new ArrayList<Thorn>();
        this.cells = new HashMap<Integer, Cell>();
        this.foodFactory = new FoodFactory(this, 5);
        this.thornFactory = new ThornFactory(this);
        this.size = 1000;
        this.maxSpeedOfCells = 10;
        this.rand = new Random();

        for (int i = 0; i < 10; i++) {
            this.thornFactory.spawn();
        }
    }

    /**
     * Refreshes the state of the game. Spawns food, moves cells, checks collisions and then updates clients.
     * 
     * @throws IOException 
     */
    public void tick() throws IOException {
        this.foodFactory.spawn();
        this.moveCells();
        this.checkCollisions();
        // this.core.updateClients(this.createMapObjectsForResponse(), this.collectCellStatuses());
        this.core.updateClientsWithSimpleObjects(this.createSimpleMapObjects(), this.collectCellStatuses());
    }

    /**
     * Iterates over the cells and check collisions. First check for thorn collisions then food and then with other cells.
     * If collision happens then updates the cells if needed.
     */
    public void checkCollisions() {
        Iterator mainIterator = this.cells.entrySet().iterator();
        Iterator subIterator;
        while (mainIterator.hasNext()) {
            Entry currentEntry = (Entry) mainIterator.next();
            Cell currentCell = (Cell) currentEntry.getValue();
            if (currentCell.getStatus() == Response.STATUS_PLAYING) {
                for (Thorn thorn : this.thorns) {
                    if (thorn.getAttributes().getRadius() > currentCell.getAttributes().getRadius() && currentCell.getIntersectionWithOtherObject(thorn) > 60) {
                        currentCell.decreaseCellWithPercent(50);
                    }
                }

                List<Food> edibleFood = new ArrayList<>();
                
                for (Food food : this.foods) {
                    if (currentCell.checkCollisionWithFood(food)) {
                        edibleFood.add(food);
                    }
                }
                
                for (Food food: edibleFood) {
                    currentCell.eatFood(food);
                }
                
                subIterator = this.cells.entrySet().iterator();
                while (subIterator.hasNext()) {
                    Entry currentSubEntry = (Entry) subIterator.next();
                    Cell currentSubCell = (Cell) currentSubEntry.getValue();
                    if (currentCell != currentSubCell && currentSubCell.getStatus() == Response.STATUS_PLAYING) {
                        if (currentCell.getAttributes().getMass() * 1.05 > currentSubCell.getAttributes().getMass() * 1.05 
                                && currentCell.getIntersectionWithOtherObject(currentSubCell) > 70) {
                            currentCell.eatCell(currentSubCell);
                        }
                    }
                }
            }
        }
    }

    /**
     * Iterates over every cell and moves them according the player's last known cursor angle.
     */
    public void moveCells() {
        for (Entry<Integer, Cell> entry : this.cells.entrySet()) {
            Cell cell = entry.getValue();
            cell.move();
        }
    }

    /**
     * Add food to the map
     * 
     * @param food Food object to be added.
     */
    public void addFood(Food food) {
        this.foods.add(food);
    }

    /**
     * Updates the moving angle of the cell with the given id if the player sends a new packet.
     * 
     * @param id The id of the player.
     * @param angle The angle of the cursor according to the x axis.
     */
    public void updateCell(int id, float angle) {
        Cell cell = this.cells.get(id);
        cell.setMovingAngle(angle);
    }

    /**
     * Returns the size of the map.
     * 
     * @return The size of the map.
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Adds a new cell to the game to a random position.
     * 
     * @param id The id of the player.
     * @param name The name of the player.
     */
    public void addCell(int id, String name) {        
        int radius = 5, mass = 10;       
        float [] coords = this.getRandomCoordsWithEmptyRadiusOf(10);
        float x = coords[0], y = coords[1];
        Cell cell = new Cell(x, y, radius, mass, this, this.getRandomColorForCell(), name, this.maxSpeedOfCells);
        this.cells.put(id, cell);
    }

    /**
     * Reanimate the cell with the given id after the client starts new game after dying.
     * The cell gets a new unique color and a name if the player changes it.
     * 
     * @param id The id of the player.
     * @param name The name the player sends.
     */
    public void reAnimateCell(int id, String name) {
        Cell cell = this.cells.get(id);
        cell.setName(name);
        cell.getAttributes().setColor(this.getRandomColorForCell());
        cell.setStatus(Response.STATUS_PLAYING);
    }
    
    /**
     * Returns a random color constant.
     * 
     * @return A random color constant. 
     */
    public Color getRandomColorForCell() {
        switch (this.rand.nextInt(7)) {
            case 0:
                return Color.BLUE;
            case 1:
                return Color.CYAN;
            case 2:
                return Color.GRAY;
            case 3:
                return Color.MAGENTA;
            case 4:
                return Color.PINK;
            case 5:
                return Color.RED;
            case 6:
                return Color.YELLOW;
            default:
                return Color.BLUE;
        }
    }

    /**
     * Removes a food object from the map.
     * 
     * @param food The food object to be removed.
     */
    public void removeFood(Food food) {
        this.foods.remove(food);
    }

    /**
     * Adds a new thorn object to the map.
     * 
     * @param thorn The thorn object to be added.
     */
    public void addThorn(Thorn thorn) {
        this.thorns.add(thorn);
    }

    /**
     * Removes a thorn object from the map.
     * 
     * @param thorn The thorn object to be removed.
     */
    public void removeThorn(Thorn thorn) {
        this.thorns.remove(thorn);
    }

    /**
     * Removes the cell with the given id from the map.
     * 
     * @param id The id of the cell to be removed.
     */
    public void removeCell(int id) {
        this.cells.remove(id);
    }

    /**
     * Returns the FoodFactory object of the map.
     * 
     * @return The foodFactory object.
     */
    public FoodFactory getFoodFactory() {
        return this.foodFactory;
    }

    /**
     * Returns the ThornFactory object of the map.
     * 
     * @return The thornFactory object.
     */
    public ThornFactory getThornFactory() {
        return this.thornFactory;
    }

    /**
     * Checks if the space on the map is empty on the given coordinates within the given distance.
     * 
     * @param x The x coordinate of the map point to be checked
     * @param y The y coordinate of the map point to be checked
     * @param distanceFromObject The required distance from every objects.
     * @return True if no objects is nearer than distanceFromObject to the x,y coordinates of the map, false otherwise.
     */
    public boolean isEmptySpace(float x, float y, int distanceFromObject) {
        for (Food food : this.foods) {
            if (food.isCoordsWithninGivenArea(x, y, distanceFromObject)) {
                return false;
            }
        }

        for (Thorn thorn : this.thorns) {
            if (thorn.isCoordsWithninGivenArea(x, y, distanceFromObject)) {
                return false;
            }
        }

        for (Entry<Integer, Cell> entry : this.cells.entrySet()) {
            Cell cell = entry.getValue();
            if (cell.isCoordsWithninGivenArea(x, y, distanceFromObject)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Creates a MapObjects object with every MapObject of the map to send out to the clients.
     * 
     * @return The new mapObjects which contains every object of the map.
     */
    public MapObjectsImpl createMapObjectsForResponse() {
        MapObjectsImpl mapObjects = new MapObjectsImpl(this.foods, this.thorns, this.cells);
        return mapObjects;
    }
    
    /**
     * Convert server-based map objects to a simplified implementation
     * @return a simplified map object, to be used for data transfer
     */
    public List<? super SimpleMapObject> createSimpleMapObjects() {
        List<? super SimpleMapObject> result;
        result = new ArrayList<>();
        
        for (Food food: foods) {
            result.add(food.simplify());
        }
        
        for (Thorn thorn: thorns) {
            result.add(thorn.simplify());
        }
        
        for (Entry<Integer, Cell> entry : cells.entrySet()) {
            Integer id = entry.getKey();
            Cell cell = entry.getValue();
            result.add(cell.simplify(id));
        }

        return result;
    }
    
    /**
     * Returns a random coordinate of the map with no objects within the given emptyRadius.
     * 
     * @param emptyRadius The array containing the x and y coordinate.
     * @return 
     */
    public float[] getRandomCoordsWithEmptyRadiusOf(int emptyRadius) {
        float[] coords = new float [2];
        float x, y;
        do {
                x = this.rand.nextFloat()* this.size;
                y = this.rand.nextFloat() * this.size;
            } while (!this.isEmptySpace(x, y, emptyRadius));
        coords[0] = x;
        coords[1] = y;
        return coords;
    } 

    /**
     * Collects the statuses of the individual cells into a hashmap identified by the ids of the cells.
     * 
     * @return The hashmap containing the statuses of the cells.
     */
    private HashMap<Integer, Integer> collectCellStatuses() {
        HashMap<Integer, Integer> statuses = new HashMap<Integer, Integer>();
        
        for (Entry currentEntry : this.cells.entrySet()) {
            Cell currentCell = (Cell) currentEntry.getValue();
            statuses.put((int)currentEntry.getKey(), currentCell.getStatus());
        }
        
        return statuses;
    }
}
