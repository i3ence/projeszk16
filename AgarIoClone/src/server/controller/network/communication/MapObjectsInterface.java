package server.controller.network.communication;

import java.util.List;
import server.model.object.Cell;
import server.model.object.Food;
import server.model.object.Thorn;

/**
 *
 * @author zoli-
 */
public interface MapObjectsInterface {

    abstract public java.util.Map<Integer, Cell> getCells();    

    abstract public List<Food> getFoods();

    abstract public List<Thorn> getThorns(); 

}
