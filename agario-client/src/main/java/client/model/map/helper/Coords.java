package client.model.map.helper;

public class Coords {
    
    private float x,y;
    
    public Coords(float x, float y){
        this.x=x;
        this.y=y;
    }
    
    public void setX(float x){
        this.x=x;
    }
    
    public void setY(float y){
        this.y=y;
    }
    
    public float getX(){
        return this.x;
    }
    
    public float getY(){
        return this.y;
    }
    
    @Override
    public String toString(){
        return ""+ x + ";" + y;
    }
    
}
