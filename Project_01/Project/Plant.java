import java.util.ArrayList;

/** 
  * @desc this class is designed for creating specific kinds of Specimen, 
  * which are Plants. This class defines specifications of Plants, such 
  * as eating functionality.
  * @author Zura Mestiashvili mestiasz@lafayette.edu
  * @version v1.0.0
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
