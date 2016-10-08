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
        this.bound = 4;
        this.rnd = new Random(seed);
    }
    
    public Direction next(){
        int i = rnd.nextInt(bound);
        switch (i){
            case 0: return Direction.LEFT;
            case 1: return Direction.UP;
            case 2: return Direction.RIGHT;
            case 3: return Direction.DOWN;
        }
        
        return Direction.UP;
    }
    
}
