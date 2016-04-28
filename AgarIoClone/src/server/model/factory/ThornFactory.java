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
    
    public ThornFactory(Map map) {
        this.map = map;
    }
    
    public void spawn() {
            Random rand = new Random();
            float x;
            float y;
            do {
                x = rand.nextFloat();
                y = rand.nextFloat();
            } while (this.map.isEmptySpace(x, y, 10));
            Thorn thorn = new Thorn(x, y, 15, 0, 0, this.map);
            this.map.addThorn(thorn);
    }
    
    public void setMap(Map map) {
        this.map = map;
    }
    
    public Map getMap() {
        return this.map;
    }
    
}
