import java.util.Random;
import java.util.ArrayList;

/** 
  * @desc this class is a Position generator and provides a random 
  * position(x and y values) for a new Specimen. 
  * @author Zura Mestiashvili mestiasz@lafayette.edu
  * @version v1.0.0
*/

public class PositionGenerator{
    private long seed;
    private int height;
    private int width;
    private Random rnd;
    
    public PositionGenerator(long seed, int width, int height){
        this.seed = seed;
        this.height = height;
        this.width = width;
        this.rnd = new Random(seed);
    }
    
    /**
      * @desc provides random position for new Specimen
      * by using height as the first bound and width as second.
      * @return ArrayList<Integer> - return new ArraList of integers, 
      * which are randomly chosen and depict new position
    */
    public ArrayList<Integer> initPosition(){
        ArrayList<Integer> position = new ArrayList<Integer>();
        position.add(rnd.nextInt(height));
        position.add(rnd.nextInt(width));
        return position;
    }
    
}
