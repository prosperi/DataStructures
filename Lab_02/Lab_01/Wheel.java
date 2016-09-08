import java.util.Random;

/**
 * @author Zura Mestiashvili 
 * @version 1.0.0
 */

public class Wheel{
    int seed;
    Random rnd;
    
    public Wheel(int seed){
        this.seed = seed;
        this.rnd = new Random();
    }
    
    public int spin(){
        return this.rnd.nextInt(seed-1) + 1;
    }
    
}
