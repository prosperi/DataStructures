import java.util.ArrayList;
/**
 * Zura Mestiashvili
 * v1.0.0
 */
public class Carnivore extends Animal{
    
    public Carnivore(String name, String type, char symbol, ArrayList<String> energySources,
                   double energy, ArrayList<Double> stats, double birthEnergy, double maxEnergy,
                   double livingEnergy, int x, int y){
        
       super(name, type, symbol, energySources, energy, stats, birthEnergy, maxEnergy, 
             livingEnergy, x, y);
    }
    
}
