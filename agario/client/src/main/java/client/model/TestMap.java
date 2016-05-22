
package client.model;

import common.model.Cell;
import common.model.Food;
import common.model.Thorn;
import java.util.Random;
import org.joml.Vector2f;

/**
 *
 * @author yzsolt
 */
public class TestMap extends Map {
    
    private final Random m_random;
    
    private int getBoundedRandomInteger(int from, int to) {
        return m_random.nextInt(to - from) + from;
    }
    
    private Vector2f getRandomPosition(int radius) {
        return new Vector2f(getBoundedRandomInteger(radius, size - radius), getBoundedRandomInteger(radius, size - radius));
    }
    
    public TestMap() {
        
        super(1000);
        
        m_random = new Random(1);
        
    }
    
    public void fillWithTestObjects() {
        
        int radius = 0;
        
        // Lots of food
        
        radius = 5;
        /*
        for (int i = 0; i < 50; i++) {
            objects.add(new Food(this, getRandomPosition(radius), radius));
        }
        
        // Some cells
        
        for (int i = 0; i < 12; i++) {
            radius = getBoundedRandomInteger(5, 15);
            objects.add(new Cell(this, getRandomPosition(radius), radius, 123456789, "yzsolt", radius));
        }
        
        // A few thorns
        
        radius = 8;
        
        for (int i = 0; i < 5; i++) {
            objects.add(new Thorn(this, getRandomPosition(radius), radius));
        }
        */
    }
    
}
