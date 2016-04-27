
package client.model.map.object;

import org.joml.Vector2i;

import client.model.map.helper.Attributes;

/**
 *
 * @author zoli-
 */
public class Cell extends MapObject{

    String name;
    String id;
    Attributes attributes;
    
    public Cell(String id, String name, int radius, int maxSpeed, int mass, int coordX, int coordY) {
        this.id = id;
        this.name = name;
        attributes = new Attributes(radius, maxSpeed, mass);
        position = new Vector2i(coordX, coordY);
    }
    
    @Override
    public void collision() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
