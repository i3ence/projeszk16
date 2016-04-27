package client.model.map.object;

/**
 *
 * @author zoli-
 */

import client.model.map.Map;
import client.model.map.helper.Attributes;

import org.joml.Vector2i;

public abstract class MapObject {
    
    protected Vector2i position;
    protected Attributes attr;
    protected final Map map;
    
    public MapObject(){
        this.map = null;
    }
    
    public MapObject(int x, int y){
        this.map = null;
        this.position = new Vector2i(x,y);
        this.attr = new Attributes(1,2,3);
    }
    
     public MapObject(int x, int y,Map map){
        this.position = new Vector2i(x,y);
        this.attr = new Attributes(1,2,3);
        this.map=map;
     }
    
    public Vector2i getPosition(){
        return this.position;
    }
    
    public Attributes getAttributes(){
        return this.attr;
    }
      
    public abstract void collision();

}

