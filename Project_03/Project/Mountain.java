/** 
  * @desc This class is used to create mountain objects
  * that are hindrance for animals
  * @author Zura Mestiashvili mestiasz@lafayette.edu
  * @version v1.0.0
*/
public class Mountain{
    
    private int startingX, startingY, endingX, endingY;
    private char symbol;
    
    public Mountain(int startingX, int startingY, int endingX, int endingY){
        this.startingX = startingX;
        this.startingY = startingY;
        this.endingX = endingX;
        this.endingY = endingY;
        this.symbol = '*';
    }
    
    /**
     * @desc get X coordinate of starting point
     * @return startingX - int
     */
    public int getStartingX(){
        return startingX;
    }
    
    /**
     * @desc get Y coordinate of starting point
     * @return startingY - int
     */
    public int getStartingY(){
        return startingY;
    }
    
    /**
     * @desc get X coordinate of ending point
     * @return startingX - int
     */
    public int getEndingX(){
        return endingX;
    }
    
    /**
     * @desc get Y coordinate of ending point
     * @return startingY - int
     */
    public int getEndingY(){
        return endingY;
    }
    
    /**
     * @desc get representation of mountain
     * @return symbol - char
     */
    public char getRepresentation(){
        return symbol;
    }
  
}
