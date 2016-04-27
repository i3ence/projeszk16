package client.model.map;

import client.model.map.object.Cell;
import client.model.map.object.MapObject;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author zoli-
 */
public class Map {

    List<MapObject> mapContent;
    
    
    /**
     * initialize empty map
     */
    public Map() {
        mapContent = new ArrayList<>();
    }
    
    /**
     * update map data
     * call this on every frame/tick
     * @param newMap 
     */
    public void update(Map newMap) {
        this.mapContent = newMap.mapContent;
    }
    
}
