package server.model.helper;


public class Coords {
    
    private int x,y;
    
    public Coords(int x, int y){
        this.x=x;
        this.y=y;
    }
    
    public void setX(int x){
        this.x=x;
    }
    
    public void setY(int y){
        this.y=y;
    }
    
    public int getX(){
        return this.x;
    }
    
    public int getY(){
        return this.y;
    }
    
    @Override
    public String toString(){
        return ""+ x + ";" + y;
    }
    
}