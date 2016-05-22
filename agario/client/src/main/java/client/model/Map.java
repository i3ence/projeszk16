
package client.model;

import common.model.Cell;
import common.model.Food;
import common.model.MapObject;
import common.model.Thorn;
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
     * Initialize a map.
     * @param size size of the map.
     */
    public Map(int size) {
        this.size = size;
        this.objects = new ArrayList<>();
    }
    
    /**
     * Overwrite map with another.
     * @param newMap the other map.
     */
    public void update(Map newMap) {
        this.objects = newMap.objects;
    }
    
    /**
     * Returns the size of the map.
     * @return Size of the map.
     */
    public int getSize() {
        return size;
    }
    
    /**
     * Returns a list of objects located on the map.
     * @return The objects located on the map.
     */
    public List<MapObject> getObjects() {
        return objects;
    }
    
    public MapObject getObject(int id) {
        
        for (MapObject mapObject : objects) {
            if (mapObject.getId() == id) {
                return mapObject;
            }
        }
        
        return null;
        
    }
    
    /**
     * Updates the map with the given simple values (from server)
     * This iteration might not be the fastest way to solve this. 
     * @param mapObjects
     */
    public void resetObjects(List<MapObject> mapObjects) {
        objects = mapObjects;
    }
    
    /**
     * Updates the map with the given simple values (from server)
     * This iteration might not be the fastest way to solve this.
     * @param mapObjects 
     */
    public void updateObjects(List<MapObject> mapObjects) {
        
        for (MapObject freshObject : mapObjects) { 
            
            int index = objects.indexOf(freshObject);
            
            if (index >= 0) {
               
                MapObject oldObject = objects.get(index);
                
                oldObject.copyData(freshObject);
                
            } else {
                // Object has been removed on the server, remove here too
                objects.remove(index);
            }
            
        }
        
    }
    
    @Override
    public String toString() {
        return "Map { size: " + size + ", objects: " + objects.size() + " }";
    }
    
}
