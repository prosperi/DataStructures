import java.util.Random;

/**
 * @author Zura Mestiashvili
 * @version v1.0.0
 */
public class PopulationGenerator{
   Random rnd;
   
   public PopulationGenerator(){
       rnd = new Random();
   }
    
   public int next(int mean, int deviation){
       return mean + (int)(rnd.nextGaussian() * deviation);
   }
    
}
