import java.util.Random;
import java.util.ArrayList;

/**
 * @author Zura Mestiashvili 
 * @version 1.0.0
 */

public class PositionGenerator{
    long seed;
    int height;
    int width;
    Random rnd;
    
    public PositionGenerator(long seed, int width, int height){
        this.seed = seed;
        this.height = height;
        this.width = width;
        this.rnd = new Random(seed);
    }
    
    public ArrayList<Integer> initPosition(){
        ArrayList<Integer> position = new ArrayList<Integer>();
        position.add(rnd.nextInt(height));
        position.add(rnd.nextInt(width));
        return position;
    }
    
}
