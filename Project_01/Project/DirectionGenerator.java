import java.util.Random;

/**
 * @author Zura Mestiashvili 
 * @version 1.0.0
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
