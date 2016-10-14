import java.util.ArrayList;

/**
 * Zura Mestiashvili
 */
public class Vegetable extends Plant{
  
    public Vegetable(String name, String type, char symbol, ArrayList<String> energySources,
                   double energy, ArrayList<Double> stats, double birthEnergy, double maxEnergy,
                   double livingEnergy, int x, int y){
        
       super(name, type, symbol, energySources, energy, stats, birthEnergy, maxEnergy, 
             livingEnergy, x, y);
    }
   
}
