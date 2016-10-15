import java.util.Random;

/** 
  * @desc this class is Direction generator, provides a random direction
  * while object moves or gives birth to a new child specimen. In first case, 
  * the generator defines where the specimen will move, and in second occasion
  * it defines where the child specimen will be placed.
  * @author Zura Mestiashvili mestiasz@lafayette.edu
  * @version v1.0.0
*/

public class DirectionGenerator{
    long seed;
    int bound;
    Random rnd;
    
    public DirectionGenerator(long seed){
        this.seed = seed;
        this.bound = 8;
        this.rnd = new Random(seed);
    }
    
    /**
      * @desc returns random direction according to the bound which is always 8
      * as we have 8 adjacent cells at maximum.
      * @return Direction  - return new Random Direction or Direction.LEFT if randomly generated
      * number does not have approprite direction to return
    */
    public Direction next(){
        int i = rnd.nextInt(bound);
        switch (i){
            case 0: return Direction.LEFT;
            case 1: return Direction.UP;
            case 2: return Direction.RIGHT;
            case 3: return Direction.DOWN;
            case 4: return Direction.UP_LEFT;
            case 5: return Direction.UP_RIGHT;
            case 6: return Direction.DOWN_LEFT;
            case 7: return Direction.DOWN_RIGHT;
            
        }
        
        return Direction.LEFT;
    }
    
}
