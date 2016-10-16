
/** 
  * @desc this class provides directions that Specimen can ever
  * go or give birth. Each direction represents a kind of Vector,
  * where first argument is X coordinate and second one is Y coordinte
  * @author Zura Mestiashvili mestiasz@lafayette.edu
  * @version v1.0.0
*/  

public enum Direction
{
    LEFT(0, -1), 
    RIGHT(0, 1), 
    UP(-1, 0), 
    DOWN(1, 0), 
    UP_LEFT(-1, -1), 
    UP_RIGHT(-1, 1), 
    DOWN_LEFT(1, -1), 
    DOWN_RIGHT(1, 1);
    
    private final int x;
    private final int y;
    
    Direction(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    
    public int getX(){
        return x;
    };

    public int getY(){
        return y;
    };
}
