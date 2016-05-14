package server.model.helper;


public class Coords {
    
    private float x,y;
    
    /**
     * Sets the x and y coordinates.
     * 
     * @param x The x coordinate.
     * @param y The y coordinate.
     */
    public Coords(float x, float y){
        this.x=x;
        this.y=y;
    }
    
    /**
     * Sets the x coordinate.
     * 
     * @param x The x coordinate.
     */
    public void setX(float x){
        this.x=x;
    }
    
    /**
     * Sets the y coordinate.
     * 
     * @param y The y coordinate.
     */
    public void setY(float y){
        this.y=y;
    }
    
    /**
     * Returns the x coordinate.
     * 
     * @return The x coordinate.
     */
    public float getX(){
        return this.x;
    }
    
    /**
     * Returns the y coordinate.
     * 
     * @return The y coordinate.
     */
    public float getY(){
        return this.y;
    }
    
    /**
     * Prints the x and y coordinates.
     * 
     * @return The x and y coordinate printed.
     */
    @Override
    public String toString(){
        return ""+ x + ";" + y;
    }
    
}
