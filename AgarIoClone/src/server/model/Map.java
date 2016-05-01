package server.model;

import communication.MapObjects;
import communication.ResponseInterface;
import server.model.object.*;
import server.model.factory.*;
import java.util.*;
import java.util.Map.Entry;
import server.controller.Core;

/**
 *
 * @author zoli-
 */
public class Map {

    private List<Food> foods;
    private List<Thorn> thorns;
    private java.util.Map<Integer, Cell> cells;
    private FoodFactory foodFactory;
    private ThornFactory thornFactory;
    private int size;
    private Core core;

    public Map(Core core) {
        this.core = core;
        this.foods = new ArrayList<Food>();
        this.thorns = new ArrayList<Thorn>();
        this.cells = new HashMap<Integer, Cell>();
        this.foodFactory = new FoodFactory(this, 5);
        this.thornFactory = new ThornFactory(this);
        this.size = 1000;

        for (int i = 0; i < 10; i++) {
            this.thornFactory.spawn();
        }
    }

    public void tick() {
        this.foodFactory.spawn();
        this.moveCells();
        this.checkCollisions();
        this.core.updateClients(this.createMapObjectsForResponse());
    }

    public void checkCollisions() {
        List<Cell> cellContainer = new LinkedList<Cell>();
        for (Iterator<Cell> iterator = cellContainer.iterator(); iterator.hasNext();) {
            Cell cell = iterator.next();
            for (Thorn thorn : this.thorns) {
                //do collision associated methods
            }

            for (Food food : this.foods) {
                //same here
            }
            //todo: iterate over cells, but without the current one and always on the updated list
            iterator.remove();
        }
    }

    public void moveCells() {
        for (Entry<Integer, Cell> entry : this.cells.entrySet()) {
            Cell cell = entry.getValue();
            cell.move();
        }
    }

    public void addFood(Food food) {
        this.foods.add(food);
    }

    public void updateCell(int id, float angle, float length) {
        Cell cell = this.cells.get(id);
        cell.setMovingAngle(angle);
        cell.setMovingSpeedRatio(length);
    }

    public int getSize() {
        return this.size;
    }

    public void addCell(int id, String name) {
        Cell cell = new Cell();
        this.cells.put(id, cell);
    }

    public void reAnimateCell(int id, String name) {
        Cell cell = this.cells.get(id);
        cell.setName(name);
        cell.setStatus(ResponseInterface.STATUS_PLAYING);
    }

    public void removeFood(Food food) {
        this.foods.remove(food);
    }

    public void addThorn(Thorn thorn) {
        this.thorns.add(thorn);
    }

    public void removeThorn(Thorn thorn) {
        this.thorns.remove(thorn);
    }

    public void removeCell(int id) {
        this.cells.remove(id);
    }

    public void setFoodFactory(FoodFactory foodFactory) {
        this.foodFactory = foodFactory;
    }

    public FoodFactory getFoodFactory() {
        return this.foodFactory;
    }

    public void setThornFactory(ThornFactory thornFactory) {
        this.thornFactory = thornFactory;
    }

    public ThornFactory getThornFactory() {
        return this.thornFactory;
    }

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

    public void setSize(int size) {
        this.size = size;
    }
    
    public MapObjects createMapObjectsForResponse() {
        //Create mapobjects here then return
        MapObjects mapObjects = new MapObjects();
        return mapObjects;
    }
}
