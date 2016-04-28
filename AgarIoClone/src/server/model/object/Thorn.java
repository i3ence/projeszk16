/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.model.object;

import server.model.Map;

/**
 *
 * @author zoli-
 */
public class Thorn extends MapObject{

    public Thorn(float x, float y, int radius, int maxSpeed, int mass, Map map) {
        super(x, y, radius, maxSpeed, mass, map);
    }

}
