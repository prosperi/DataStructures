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
    
   public int next(double mean, double deviation){
       return (int)(mean + (rnd.nextGaussian() * deviation));
   }
    
}
