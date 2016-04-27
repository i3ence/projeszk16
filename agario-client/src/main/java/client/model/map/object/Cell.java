package client.model.map.object;

import client.model.map.helper.Attributes;
import client.model.map.helper.Coords;

/**
 *
 * @author zoli-
 */
public class Cell extends MapObject{

    String name;
    String id;
    Attributes attributes;
    Coords coords;
    
    public Cell(String id, String name, int radius, int maxSpeed, int mass, int coordX, int coordY) {
        this.id = id;
        this.name = name;
        attributes = new Attributes(radius, maxSpeed, mass);
        coords = new Coords(coordX, coordY);
    }
    
    @Override
    public void collision() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
