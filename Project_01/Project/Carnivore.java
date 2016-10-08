import java.util.ArrayList;
/**
 * Zura Mestiashvili
 * v1.0.0
 */
public class Carnivore extends Animal{
    
    public Carnivore(String name, String type, char symbol, ArrayList<String> energySources,
                   ArrayList<Double> initialStats, ArrayList<Double> stats, double birthEnergy, double maxEnergy,
                   double livingEnergy, int[] position){
        
       super(name, type, symbol, energySources, initialStats, stats, birthEnergy, maxEnergy, 
             livingEnergy, position);
    }
    
}
