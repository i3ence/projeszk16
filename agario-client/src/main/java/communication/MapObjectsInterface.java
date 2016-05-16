package communication;

import java.util.List;
import client.model.object.Cell;
import client.model.object.Food;
import client.model.object.Thorn;

/**
 *
 * @author zoli-
 */
public interface MapObjectsInterface {

    abstract public java.util.Map<Integer, Cell> getCells();    

    abstract public List<Food> getFoods();

    abstract public List<Thorn> getThorns(); 

}
