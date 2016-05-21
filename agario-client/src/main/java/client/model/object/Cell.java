
package client.model.object;

import client.Util;
import client.model.Map;
import client.model.helper.Attributes;
import common.model.SimpleCell;

import org.joml.Vector2f;
import org.joml.Vector3f;

/**
 * A cell that represents a player in the game.
 * 
 * @author zoli-
 * @author yzsolt
 */
public class Cell extends MapObject {
    
    private static final int MAX_SPEED = 3;

    private int id;
    private String name;
    private int mass;
    
    /**
     * Sets the attributes of the cell.
     * 
     * @param map The map instance.
     * @param position The position of the cell.
     * @param radius The radius of the cell.
     * @param id The id of the cell.
     * @param name Cell owner player's name.
     * @param mass The mass of the cell.
     */
    public Cell(Map map, Vector2f position, int radius, int id, String name, int mass) {
        
        // TODO: calculate some random color
        
        super(map, position, new Attributes(radius, new Vector3f(0.2f, 0.6f, 0.5f)));
        
        this.id = id;
        this.name = name;
        this.mass = mass;

    }

    /**
     * Sets the attributes of the cell.
     * 
     * @param position The position of the cell.
     * @param id The id of the cell.
     * @param name Cell owner player's name.
     * @param mass The mass of the cell.
     * @param attributes The attributes of the cell.
     */
    public Cell(int id, String name, Vector2f position, int mass, Attributes attributes) {
        super(position, attributes);
        this.mass = mass;
        this.id = id;
        this.name = name;
    }
    
    /**
     * Returns the Cell ID
     * @return Cell ID
     */
    public int getId() {
        return this.id;
    }
    
    /**
     * Returns the Player name assigned to cell.
     * @return Player name assigned to cell.
     */
    public String getName() {
        return this.name;
    }
    
    /**
     * Returns the mass of the Cell object.
     * @return mass of the Cell object.
     */
    public int getMass() {
        return this.mass;
    }
    
    // This seems deprecated
    public void move(Vector2f movement) {
        
        float speed = movement.length();
        
        if (speed > MAX_SPEED) {
            
            float ratio = MAX_SPEED / speed;
            
            movement.mul(ratio);
            
        }
        
        position.add(movement);
        
        int radius = this.attributes.getRadius();
        
        position.x = Util.clamp(position.x, 0 + radius, map.getSize() - radius);
        position.y = Util.clamp(position.y, 0 + radius, map.getSize() - radius);
        
    }
    
        
    /**
     * Unwraps response Simple Cell object to be used by GL renderer.
     * @param simpleCell Cell object 
     * @return A client-side implementation of Cell
     */
    public static Cell fromSimpleCell(SimpleCell simpleCell) {
        int id = simpleCell.getId();
        String name = simpleCell.getName();
        int mass = simpleCell.getMass();
        Vector2f position = new Vector2f(simpleCell.getX(), simpleCell.getY());
        Attributes attributes = new Attributes(simpleCell.getRadius(), Util.convertColor(simpleCell.getColor()));
        
        return new Cell(id, name, position, mass, attributes);
    }
    
}
