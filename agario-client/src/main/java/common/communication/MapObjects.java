package common.communication;

import java.util.List;
import client.model.object.Cell;
import client.model.object.Food;
import client.model.object.Thorn;

/**
 *
 * @author zoli-
 */
public interface MapObjects {

    abstract public java.util.Map<Integer, Cell> getCells();    

    abstract public List<Food> getFoods();

    abstract public List<Thorn> getThorns(); 

}
