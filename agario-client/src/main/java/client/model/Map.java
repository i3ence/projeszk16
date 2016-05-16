package client.model;

import client.model.object.Cell;
import client.model.object.Food;
import client.model.object.MapObject;
import client.model.object.Thorn;
import common.model.SimpleCell;
import common.model.SimpleFood;
import common.model.SimpleMapObject;
import common.model.SimpleThorn;
import java.util.ArrayList;
import java.util.List;

/**
 * A square-shaped map.
 * @author zoli-
 * @author yzsolt
 */
public class Map {

    /** Size of the map's sides. */
    int size;
    
    List<MapObject> objects;
    
    /**
     * initialize empty map
     */
    public Map(int size) {
        this.size = size;
        this.objects = new ArrayList<>();
    }
    
    /**
     * update map data
     * call this on every frame/tick
     * @param newMap 
     */
    public void update(Map newMap) {
        this.objects = newMap.objects;
    }
    
    public int getSize() {
        return size;
    }
    
    public List<MapObject> getObjects() {
        return objects;
    }
    
    /**
     * Updates the map with the given simple values (from server)
     * This iteration might not be the fastest way to solve this.
     * @param simpleObjects 
     */
    public void updateWithSimpleMapObjects(List<? super SimpleMapObject> simpleObjects) {
        objects.clear();
        
        for (Object simple: simpleObjects) {
            if (simple instanceof SimpleFood) {
                objects.add(Food.fromSimpleFood((SimpleFood)simple));
            }
            if (simple instanceof SimpleThorn) {
                objects.add(Thorn.fromSimpleThorn((SimpleThorn)simple));
            }
            if (simple instanceof SimpleCell) {
                objects.add(Cell.fromSimpleCell((SimpleCell)simple));
            }
        }
    }
    
}
