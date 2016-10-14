import java.util.ArrayList;

/**
 * Zura Mestiashvili
 */

public class Plant extends Specimen {
    
   public Plant(String name, String type, char symbol, ArrayList<String> energySources,
                double energy, ArrayList<Double> stats, double birthEnergy, double maxEnergy,
                double livingEnergy, int x, int y){
        
       super(name, type, symbol, energySources, energy, stats, birthEnergy, maxEnergy, 
             livingEnergy, x, y);
             
    }
    
   
   public void eat(Terrain terrain, ArrayList<Specimen> habitants){
       if(getEnergy()  > 0 && getEnergy() < getMaxEnergy()){ 
           setEnergy(getEnergy() + terrain.getLight());
           this.action = false;
       }
       
       if(getEnergy() > getMaxEnergy()){
           setEnergy(getMaxEnergy());
       }
       
   }
   
   
    
}
