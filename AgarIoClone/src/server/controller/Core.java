package server.controller;

/**
 *
 * @author zoli-
 */

import server.model.Map;
import java.net.*;

public class Core {
    private Map map;

    public void tick() {
        map.tick();
    }
    
}
