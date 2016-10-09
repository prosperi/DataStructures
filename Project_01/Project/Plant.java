import java.util.ArrayList;
/**
 * Zura Mestiashvili
 */

public class Plant extends Specimen implements Inhabitant {
    
   public Plant(String name, String type, char symbol, ArrayList<String> energySources,
                ArrayList<Double> initialStats, ArrayList<Double> stats, double birthEnergy, double maxEnergy,
                double livingEnergy, int[] position){
        
       super(name, type, symbol, energySources, initialStats, stats, birthEnergy, maxEnergy, 
             livingEnergy, position);
             
    }
    
   
   public void eat(Terrain terrain, ArrayList<Specimen> habitants){}
   public void giveBirth(){}
   public void die(Terrain terrain, ArrayList<Specimen> habitants){
       habitants.remove(this);
       System.out.println("Currently we have " + habitants.size() + " habitants");
   }
    
}
