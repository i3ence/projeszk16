package client.model.map;

import client.model.map.object.Cell;
import client.model.map.object.MapObject;
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
    
}
