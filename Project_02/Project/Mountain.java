
public class Mountain{
    
    private int startingX,
                startingY,
                endingX,
                endingY;
    
    public Mountain(int startingX, int startingY, int endingX, int endingY){
        this.startingX = startingX;
        this.startingY = startingY;
        this.endingX = endingX;
        this.endingY = endingY;
    }
    
    public int getStartingX(){
        return startingX;
    }
    
    public int getStartingY(){
        return startingY;
    }
    
    public int getEndingX(){
        return endingX;
    }
    
    public int getEndingY(){
        return endingY;
    }
  
}
