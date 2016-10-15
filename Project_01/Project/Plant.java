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
    
   /**
      * @desc plant's eating functionality - this method
      * allows plant to eat food and increase energy, if it's 
      * still alive(energy > 0) and does not already have max 
      * energy, if adding food energy gives plant more energy 
      * than max energy, specimen's energy is still left to max
      * energy.
      * @param Terrain terrain - our board of specimen
      * @param ArrayList<Specimen> habitants - list of all the habitants
    */
   public void eat(Terrain terrain, ArrayList<Specimen> habitants){
       // eat if specimen is alive and eater's energy is less than max energy
       if(getEnergy()  > 0 && getEnergy() < getMaxEnergy()){ 
           setEnergy(getEnergy() + terrain.getLight());
           this.action = false;
       }
       
       // check if absorbed more than max energy and if so reduce energy level to max Energy
       if(getEnergy() > getMaxEnergy()){
           setEnergy(getMaxEnergy());
       }
       
   }
   
   
    
}
