/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.model.factory;

import server.model.Map;
import server.model.object.Thorn;
import java.util.Random;
/**
 *
 * @author zoli-
 */
public class ThornFactory {
    
    private Map map;
    private int minRadius, maxRadius;
    
    public ThornFactory(Map map) {
        this.map = map;
        this.minRadius = 50;
        this.maxRadius = 100;
    }
    
    public void spawn() {
            Random rand = new Random();
            float x;
            float y;
            int radius = rand.nextInt(this.maxRadius - this.minRadius) + this.minRadius;
            do {
                x = rand.nextFloat();
                y = rand.nextFloat();
            } while (this.map.isEmptySpace(x, y, radius + 10));
            Thorn thorn = new Thorn(x, y, radius, 0, 0, this.map);
            this.map.addThorn(thorn);
    }
    
    public void setMap(Map map) {
        this.map = map;
    }
    
    public Map getMap() {
        return this.map;
    }
    
}
