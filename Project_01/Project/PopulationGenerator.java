import java.util.Random;

/** 
  * @desc this class generates random of certain kind Specimen,
  * using Gaussian method.
  * @author Zura Mestiashvili mestiasz@lafayette.edu
  * @version v1.0.0
*/

public class PopulationGenerator{
   private Random rnd;
   
   public PopulationGenerator(long seed){
       rnd = new Random(seed);
   }
    
   /**
      * @desc returns next Gaussian random number
      * @param double mean - mean to calculate next random number
      * @param double deviation - deviation to calculate next random number
      * @return int - return new integer, which is randomly chosen
    */
   public int next(double mean, double deviation){
       return (int)(mean + (rnd.nextGaussian() * deviation));
   }
    
}
