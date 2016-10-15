import java.util.ArrayList;

/** 
  * @desc this class is designed for creating specific
  * kinds of Plants, which are vegetables
  * @author Zura Mestiashvili mestiasz@lafayette.edu
  * @version v1.0.0
*/
public class Vegetable extends Plant{
  
    public Vegetable(String name, String type, char symbol, ArrayList<String> energySources,
                   double energy, ArrayList<Double> stats, double birthEnergy, double maxEnergy,
                   double livingEnergy, int x, int y){
        
       super(name, type, symbol, energySources, energy, stats, birthEnergy, maxEnergy, 
             livingEnergy, x, y);
    }
   
}
