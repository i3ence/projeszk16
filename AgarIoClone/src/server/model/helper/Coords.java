package server.model.helper;


public class Coords {
    
    private float x,y;
    
    public Coords(float x, float y){
        this.x=x;
        this.y=y;
    }
    
    public void setX(int x){
        this.x=x;
    }
    
    public void setY(int y){
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
