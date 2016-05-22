package server.model.factory;

import server.model.Map;
import server.model.object.Thorn;
import java.util.Random;
/**
 * Generates thorns on the assigned map.
 * 
 * @author zoli-
 */
public class ThornFactory {
    
    private Map map;
    private final int minRadius, maxRadius;
    
    /**
     * Sets the map instance and the min and max radius.
     * Thorn objects are generated with a random radius within this range.
     * 
     * @param map The map instance.
     */
    public ThornFactory(Map map) {
        this.map = map;
        this.minRadius = 30;
        this.maxRadius = 60;
    }
    
    /**
     * Creates a new thorn with a random radius at a random position on the map.
     */
    public void spawn() {
            Random rand = new Random();
            int radius = rand.nextInt(this.maxRadius - this.minRadius) + this.minRadius;
            float x, y;
            float [] coords = this.map.getRandomCoordsWithEmptyRadiusOf(radius + 10);
            x = coords[0];
            y = coords[1];          
            
            Thorn thorn = new Thorn(x, y, radius, 0, this.map);
            this.map.addThorn(thorn);
    }
    
    /**
     * Sets the map instance.
     * 
     * @param map The map instance.
     */
    public void setMap(Map map) {
        this.map = map;
    }
    
    /**
     * Returns the map instance.
     * 
     * @return The map instance.
     */
    public Map getMap() {
        return this.map;
    }
    
}
