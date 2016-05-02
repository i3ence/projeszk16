package server.model;

import java.awt.Color;
import server.controller.network.communication.MapObjects;
import server.controller.network.communication.ResponseInterface;
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
    private final FoodFactory foodFactory;
    private final ThornFactory thornFactory;
    private final int size;
    private final Core core;
    private final int maxSpeedOfCells;
    private final Random rand;

    public Map(Core core) {
        this.core = core;
        this.foods = new ArrayList<Food>();
        this.thorns = new ArrayList<Thorn>();
        this.cells = new HashMap<Integer, Cell>();
        this.foodFactory = new FoodFactory(this, 5);
        this.thornFactory = new ThornFactory(this);
        this.size = 1000;
        this.maxSpeedOfCells = 5;
        this.rand = new Random();

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
        Iterator mainIterator = this.cells.entrySet().iterator();
        Iterator subIterator;
        while (mainIterator.hasNext()) {
            Entry currentEntry = (Entry) mainIterator.next();
            Cell currentCell = (Cell) currentEntry.getValue();
            if (currentCell.getStatus() == ResponseInterface.STATUS_PLAYING) {
                for (Thorn thorn : this.thorns) {
                    //check collision, intersection ratio and if more than x% decrease mass
                }

                for (Food food : this.foods) {
                    //same here
                }

                subIterator = this.cells.entrySet().iterator();
                while (subIterator.hasNext()) {
                    Entry currentSubEntry = (Entry) mainIterator.next();
                    Cell currentSubCell = (Cell) currentEntry.getValue();
                    if (currentCell != currentSubCell && currentSubCell.getStatus() == ResponseInterface.STATUS_PLAYING) {
                        //if (check collision, intersection ratio and if more than y%) {
                        //currentCell.eatCell(currentSubCell)
                        //}
                    }
                }
            }
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
        
        int radius = 5, mass = 10;       
        float [] coords = this.getRandomCoordsWithEmptyRadiusOf(10);
        float x = coords[0], y = coords[1];
        Cell cell = new Cell(x, y, radius, mass, this, this.getRandomColorForCell(), name, this.maxSpeedOfCells);
        this.cells.put(id, cell);
    }

    public void reAnimateCell(int id, String name) {
        Cell cell = this.cells.get(id);
        cell.setName(name);
        cell.getAttributes().setColor(this.getRandomColorForCell());
        cell.setStatus(ResponseInterface.STATUS_PLAYING);
    }
    
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

    public FoodFactory getFoodFactory() {
        return this.foodFactory;
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

    public MapObjects createMapObjectsForResponse() {
        MapObjects mapObjects = new MapObjects(this.foods, this.thorns, this.cells);
        return mapObjects;
    }
    
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
}
