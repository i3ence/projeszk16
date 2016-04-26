package server.model.object;

/**
 *
 * @author zoli-
 */

import server.model.Map;
import server.model.helper.Attributes;
import server.model.helper.Coords;

public abstract class MapObject {
    
    protected Coords coords;
    protected Attributes attr;
    protected final Map map;
    
    public MapObject(){
        this.map = null;
    }
    
    public MapObject(int x, int y){
        this.map = null;
        this.coords = new Coords(x,y);
        this.attr = new Attributes(1,2,3);
    }
    
     public MapObject(int x, int y,Map map){
        this.coords = new Coords(x,y);
        this.attr = new Attributes(1,2,3);
        this.map=map;
     }
    
    public Coords getCoords(){
        return this.coords;
    }
    
    public Attributes getAttributes(){
        return this.attr;
    }
      
    public abstract void collision();

}

