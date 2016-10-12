import java.util.ArrayList;
/**
 * Zura Mestiashvili
 */

public class Plant extends Specimen {
    
   public Plant(String name, String type, char symbol, ArrayList<String> energySources,
                ArrayList<Double> initialStats, ArrayList<Double> stats, double birthEnergy, double maxEnergy,
                double livingEnergy, int[] position){
        
       super(name, type, symbol, energySources, initialStats, stats, birthEnergy, maxEnergy, 
             livingEnergy, position);
             
    }
    
   
   public void eat(Terrain terrain, ArrayList<Specimen> habitants){
       if(getEnergy()  > 0){ 
           setEnergy(getEnergy() + terrain.getLight());
       }
   }
   
   
    
}
