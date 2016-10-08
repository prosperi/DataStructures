import java.util.Random;

/**
 * @author Zura Mestiashvili 
 * @version 1.0.0
 */

public class PositionGenerator{
    long seed;
    int height;
    int width;
    Random rnd;
    
    public PositionGenerator(long seed, int height, int width){
        this.seed = seed;
        this.height = height;
        this.width = width;
        this.rnd = new Random(seed);
    }
    
    public int[] initPosition(){
        int[] position = new int[]{rnd.nextInt(height), rnd.nextInt(width)};
        
        return position;
    }
    
}
