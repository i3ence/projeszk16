package server.model.object;

/**
 *
 * @author zoli-
 */
import java.awt.Color;
import server.model.Map;
import server.model.helper.Attributes;
import server.model.helper.Coords;

public abstract class MapObject {

    protected Coords coords;
    protected Attributes attr;
    protected final Map map;

    public MapObject() {
        this.map = null;
    }

    public MapObject(float x, float y) {
        this.map = null;
        this.coords = new Coords(x, y);
        this.attr = new Attributes(1, 2, Color.ORANGE);
    }

    public MapObject(float x, float y, Map map) {
        this.coords = new Coords(x, y);
        this.attr = new Attributes(1, 2, Color.ORANGE);
        this.map = map;
    }

    public MapObject(float x, float y, int radius, int mass, Map map, Color color) {
        this.coords = new Coords(x, y);
        this.attr = new Attributes(radius, mass, color);
        this.map = map;
    }

    public Coords getCoords() {
        return this.coords;
    }

    public Attributes getAttributes() {
        return this.attr;
    }

    public boolean isCoordsWithninGivenArea(float x, float y, int distanceFromTheEdge) {
        double distance = Math.sqrt((x - this.coords.getX()) * (x - this.coords.getX()) + (y - this.coords.getY()) * (y - this.coords.getY()));
       
        return distance < (this.attr.getRadius() + distanceFromTheEdge);
    }

}
