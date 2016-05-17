package common.communication;

import java.util.List;
import client.model.object.Cell;
import client.model.object.Food;
import client.model.object.Thorn;
import java.io.Serializable;

/**
 *
 * @author zoli-
 */
public class MapObjectsImpl implements Serializable, MapObjects {

    private final List<Food> foods;
    private final List<Thorn> thorns;
    private final java.util.Map<Integer, Cell> cells;
    
    /**
     * Sets the food objects, the thorn objects and the cells of the map.
     * 
     * @param foods The container of the food objects.
     * @param thorns The container of the thorn objects.
     * @param cells  The container of the cell objects.
     */
    public MapObjectsImpl(List<Food> foods, List<Thorn> thorns, java.util.Map<Integer, Cell> cells) {
        this.foods = foods;
        this.thorns = thorns;
        this.cells = cells;
    }
    
    /**
     * Returns the container of the cell objects.
     * 
     * @return Container of the cell objects.
     */
    @Override
    public java.util.Map<Integer, Cell> getCells() {
        return this.cells;
    }
    
    /**
     * Returns the container of the food objects.
     * 
     * @return Container of the food objects.
     */
    @Override
    public List<Food> getFoods() {
        return this.foods;
    }
    
    /**
     * Returns the container of the thorn objects.
     * 
     * @return Container of the thorn objects.
     */
    @Override
    public List<Thorn> getThorns() {
        return this.thorns;
    }   
}