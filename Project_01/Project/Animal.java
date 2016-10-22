import java.util.ArrayList;

/** 
  * @desc this class is designed for creating specific kinds of Specimen, 
  * which are Animals. This class defines specifications of Animals, such 
  * as eating functionality and moving ability.
  * @author Zura Mestiashvili mestiasz@lafayette.edu
  * @version v1.0.0
*/


public class Animal extends Specimen implements Movable{
    
    public Animal(String name, String type, char symbol, ArrayList<String> energySources,
                   double energy,  ArrayList<Double> stats, double birthEnergy, double maxEnergy,
                   double livingEnergy, int x, int y, Controller controller, long seed){
        
       super(name, type, symbol, energySources, energy, stats, birthEnergy, maxEnergy, 
             livingEnergy, x, y, controller, seed);

    }
    
    /**
      * @desc animal's moving functionality - this method
      * gets a new direction from DirectionGenerator, if
      * moving to that direction causes getting outside of board
      * it recursively calls itself again, if that's not the case
      * it moves to moveHelper method to cause movement.
      * @param DirectionGenerator dGen - Direction Random Generator
      * @param Terrain terrain - board of our world
      * @param ArrayList<Specimen> habitants - list of all the specimen
    */
    public void move(DirectionGenerator dGen, Terrain terrain, ArrayList<Specimen> habitants){
        Direction direction = dGen.next();
        int x = direction.getX();
        int y = direction.getY();
        int tHeight = terrain.getHeight() - 1;
        int tWidth = terrain.getWidth() - 1; 
        
        // check if position to move is outside of bord
        if(getX() + x < 0 || getX() + x > tHeight || getY() + y < 0 || getY() + y > tWidth){
            move(dGen, terrain, habitants);
        }else{
             moveHelper(dGen, terrain, habitants, getX() + x, getY() + y );
        }        
        
    }
    
    
    /**
      * @desc this method checks if specimen is locked(each adjecent cell 
      * is occupied by 2 specimen or with the animal which is not among 
      * energy sources of current specimen), it discusses 9 places specimen
      * can be: left, right, top or down side of board, or upper-left, upper-right,
      * lower-left, lower-right corners or none of those.
      * @param Terrain terrain - board of our world
      * @return boolean - true if locked, false if not
    */
    public boolean locked(Terrain terrain){
        int x = getX();
        int y = getY();
        int tHeight = terrain.getHeight();
        int tWidth = terrain.getWidth();
        
        // check if it is locked when specimen is not at the edge side or corner of board
        if(x > 0 && y > 0 && x < tHeight-1 && y < tWidth-1){
            return lockedHelper(terrain, x, y-1) && lockedHelper(terrain, x, y+1) && lockedHelper(terrain, x-1, y) && lockedHelper(terrain, x+1, y) &&
                   lockedHelper(terrain, x-1, y-1) && lockedHelper(terrain, x+1, y+1) && lockedHelper(terrain, x-1, y+1) && lockedHelper(terrain, x+1, y-1);          
        }else if(x == 0 && y == 0){
            //check if it is locked when specimen is at (0, 0) position
            return lockedHelper(terrain, 0, 1) && lockedHelper(terrain, 1, 0) && lockedHelper(terrain, 1, 1);
        }else if(x == tHeight-1 && y == tWidth-1){
            // check if it is locked when specimen is at lower-right corner
            return lockedHelper(terrain, tHeight - 1, tWidth - 2) && lockedHelper(terrain, tHeight - 2, tWidth - 1) && lockedHelper(terrain, tHeight-2, tWidth-2);
        }else if(x == tHeight-1 && y == 0){
            // check if it is locked when specimen is at lower-left corner
            return lockedHelper(terrain, tHeight - 1, 1) && lockedHelper(terrain, tHeight - 2, 0) && lockedHelper(terrain, tHeight-2, 1);
        }else if(x == 0 && y == tWidth-1){
            // check if it is locked when specimen is at upper-right corner
            return lockedHelper(terrain, 0, tWidth - 2) && lockedHelper(terrain, 1, tWidth - 1) && lockedHelper(terrain, 1, tWidth-2);
        }else if(x == 0 && y > 0 && y < tWidth-1){
            // check if it is locked when specimen is at top side
            return lockedHelper(terrain, 0, y+1) && lockedHelper(terrain, 0, y-1) && lockedHelper(terrain, 1, y) && lockedHelper(terrain, 1, y-1) && lockedHelper(terrain, 1, y+1);
        }else if(y == 0 && x > 0 && x < tHeight-1){
            // check if it is locked when specimen is at left side
            return lockedHelper(terrain, x+1, 0) && lockedHelper(terrain, x-1, 0) && lockedHelper(terrain, x, 1) && lockedHelper(terrain, x-1, 1) && lockedHelper(terrain, x+1, 1);
        }else if(x == tHeight-1 && y > 0 && y < tWidth-1){
            // check if it is locked when specimen is at bottom side
            return lockedHelper(terrain, x, y+1) && lockedHelper(terrain, x, y-1) && lockedHelper(terrain, x-1, y) && lockedHelper(terrain, x-1, y-1) && lockedHelper(terrain, x-1, y+1);
        }else if(y == tWidth-1 && x > 0 && x < tHeight-1){
            // check if it is locked when specimen is at right side
            return lockedHelper(terrain, x+1, y) && lockedHelper(terrain, x-1, y) && lockedHelper(terrain, x, y-1) && lockedHelper(terrain, x-1, y-1) && lockedHelper(terrain, x+1, y-1);
        }
        
        return false;
    }    
    
    /**
      * @desc check if specimen can move to the cell (x, y).
      * if it has at least two specimen already or or contains 
      * one specimen which is not among the energy sources of
      * our current specimen, we can not move there.
      * @param Terrain terrain - board of our world
      * @param int x - x coordinate of the cell we should check
      * @param int y - y coordinate of the cell we should check
      * @return boolean - true if the cell contains at least 2 specimen
      * or contains one specimen which is not among the energy sources of
      * our current specimen
    */
    
    public boolean lockedHelper(Terrain terrain, int x, int y){
        ArrayList<Specimen> tmp = terrain.objectMap.get(x).get(y);
        // check if the cell has at least 2 inhabitants or has just one which is instance of Animal and is not among energy sources
        // of current specimen
        if( tmp.size() >= 2 || (tmp.size() == 1 && tmp.get(0) instanceof Animal && !this.getEnergySources().contains(tmp.get(0).getName())) )
            return true;
        
        return false;
    }

     /**
      * @desc this method check if specimen is locked, if not then 
      * checks if it can move to the cell (x,y), if not calls move() 
      * method, if it can move to that cell, then just removes specimen
      * from existing location and adds to new location.
      * @param DirectionGenerator dGen - Direction generator, which
      * is used to pass move() method as argument if it is called again
      * @param Terrain terrain - board of our world
      * @param ArrayList<Specimen> habitants - list of all our habitants
      * @param int x - x coordinate of the cell we should move to
      * @param int y - y coordinate of the cell we should move to
    */
    public void moveHelper(DirectionGenerator dGen, Terrain terrain, ArrayList<Specimen> habitants, int x, int y){
        ArrayList<Specimen> tmp = terrain.objectMap.get(x).get(y);
        if(locked(terrain)){
            // we can not move
        }else if( tmp.size() >= 2 || (tmp.size() == 1 && tmp.get(0) instanceof Animal && !this.getEnergySources().contains(tmp.get(0).getName())) ){
            move(dGen, terrain, habitants);
        }else{
            // move specimen to new position
            terrain.objectMap.get(getX()).get(getY()).remove(this);
            terrain.map.get(getX()).set(getY(), '#');
            setY(y);
            setX(x);
            terrain.objectMap.get(x).get(y).add(this);
            terrain.map.get(x).set(y, this.getSymbol());
            this.action = false;
        }
    }
    
    /**
      * @desc animal's eating functionality - this method
      * allows animal to eat food and increase energy, if it's 
      * still alive(energy > 0) and does not already have max 
      * energy, if adding food energy gives plant more energy 
      * than max energy, specimen's energy is still left to max
      * energy. In addition, it considers that specimen in the cell
      * may be already dead(energy < 0), in which case that specimen
      * can not be eaten anymore.
      * @param Terrain terrain - our board of specimen
      * @param ArrayList<Specimen> habitants - list of all the habitants
    */
    public void eat(Terrain terrain, ArrayList<Specimen> habitants){
        ArrayList<Specimen> tmp = terrain.objectMap.get(getX()).get(getY());
        for(int i = 0; i < tmp.size(); i++){
            Specimen tmpHabitant = tmp.get(i);
            // check is the specimen can be eaten
            if(this != tmpHabitant && getEnergy() > 0 && getEnergy() < getMaxEnergy() && tmpHabitant.getEnergy() > 0 && this.getEnergySources().contains(tmpHabitant.getName())){
                setEnergy(getEnergy() + tmpHabitant.getEnergy());
                tmpHabitant.die(terrain, habitants);
                this.action = false;
            }
            
        }

        if(getEnergy() > getMaxEnergy()){
           setEnergy(getMaxEnergy());
        }

    }
    

}
