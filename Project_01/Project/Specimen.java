import java.util.ArrayList;

/** 
  * @desc this class defines the base functionality of each object
  * created in our wildlife. It defines such functionality as giving 
  * birth to child specimen, or death. We dan not define eat - 
  * functionality in this class, as Animals and Plants have different 
  * ways of eating. 
  * @author Zura Mestiashvili mestiasz@lafayette.edu
  * @version v1.0.0
*/

abstract public class Specimen{
   private String name;
   private String type;
   private char symbol;
   private ArrayList<String> energySources;
   private ArrayList<Double> stats;
   private double birthEnergy;
   private double maxEnergy;
   private double livingEnergy;
   private ArrayList<Double> initialStats;
   private int x;
   private int y;
   private double energy;
   private int deathAge;
   private int age;
   public boolean action;
   
                                
   public Specimen(String name, String type, char symbol, ArrayList<String> energySources,
                   double energy, ArrayList<Double> stats, double birthEnergy, double maxEnergy,
                   double livingEnergy, int x, int y){
      // initialize class variables                 
      this.name = name;
      this.type = type;
      this.symbol = symbol;
      this.energySources = energySources;
      this.stats = stats;
      this.birthEnergy = birthEnergy;
      this.maxEnergy = maxEnergy;
      this.livingEnergy = livingEnergy;
      this.x = x;
      this.y = y;
      this.energy = energy;
      this.deathAge = (new PopulationGenerator()).next(stats.get(0), stats.get(1));
      this.age = 0;
   }
   
    /**
      * @desc this method provides giving birth functionality
      * for specimen. It  checks if specimen has enough energy 
      * to give birth, genertes new position for child speciment 
      * to be placed, if the position is outside of board, recursively 
      * generates another. If it is not outside of board then
      * calls birth helper to give birth to a child specimen.
      * @param DirectionGenerator dGen - new direction generator
      * @param Terrain terrain - board of our world
      * @param ArrayList<Specimen> habitants - list of all our habitants
      * @param ArrayList<Specimen> children - list of all the children, created
      * during this step.
    */
   public void giveBirth(DirectionGenerator dGen, Terrain terrain, ArrayList<Specimen> habitants, ArrayList<Specimen> children){
        Direction direction = dGen.next();
        int x = direction.getX();
        int y = direction.getY();
        int tHeight = terrain.getHeight() - 1;
        int tWidth = terrain.getWidth() - 1; 
        if(getEnergy() >= getBirthEnergy()){
            // As direction return kind of Vector we can just add x and y values to specimen's x and y values and
            // check if that position will be outside of board
            if(getX() + x < 0 || getX() + x > tHeight || getY() + y < 0 || getY() + y > tWidth){
                giveBirth(dGen, terrain, habitants, children);
            }else{
                giveBirthHelper(dGen, terrain, habitants, children, getX() + x, getY() + y );
            }    
        }
        
    }
    
    /**
      * @desc this method checks if specimen is locked for
      * giving birth, which means checking is there is any free 
      * adjacent cell(whre nobody lives).
      * @param Terrain terrain - board of our world
      * @return boolean - true if locke, false if not
    */
    public boolean lockedBirth(Terrain terrain){
        int x = getX();
        int y = getY();
        int tHeight = terrain.getHeight();
        int tWidth = terrain.getWidth();
        
        // check if it is locked when specimen is not at the edge side or corner of board
        if(x > 0 && y > 0 && x < tHeight-1 && y < tWidth-1){
            return terrain.checkCell(x, y-1)  && terrain.checkCell(x, y+1) && terrain.checkCell(x-1, y) && terrain.checkCell(x+1, y) &&
                   terrain.checkCell(x-1, y-1) && terrain.checkCell(x+1, y+1) && terrain.checkCell(x-1, y+1) && terrain.checkCell(x+1, y-1);
        }else if(x == 0 && y == 0){
            //check if it is locked when specimen is at (0, 0) position
            return terrain.checkCell(0, 1) && terrain.checkCell(1, 0) && terrain.checkCell(1, 1);
        }else if(x == tHeight-1 && y == tWidth-1){
            // check if it is locked when specimen is at lower-right corner
            return terrain.checkCell(tHeight-1, tWidth-2) && terrain.checkCell(tHeight-2, tWidth-1) && terrain.checkCell(tHeight-2, tWidth-2);
        }else if(x == tHeight-1 && y == 0){
            // check if it is locked when specimen is at lower-left corner
            return terrain.checkCell(tHeight-1, 1) && terrain.checkCell(tHeight-2, 0) && terrain.checkCell(tHeight-2, 1);
        }else if(x == 0 && y == tWidth-1){
            // check if it is locked when specimen is at upper-right corner
            return terrain.checkCell(0, tWidth-2) && terrain.checkCell(1, tWidth-1) && terrain.checkCell(1, tWidth-2);
        }else if(x == 0 && y > 0 && y < tWidth-1){
            // check if it is locked when specimen is at top side
            return terrain.checkCell(0, y+1) && terrain.checkCell(0, y-1) && terrain.checkCell(1, y) && terrain.checkCell(1, y-1) && terrain.checkCell(1, y+1);
        }else if(y == 0 && x > 0 && x < tHeight-1){
            // check if it is locked when specimen is at left side
            return terrain.checkCell(x+1, 0) && terrain.checkCell(x-1, 0) && terrain.checkCell(x, 1) && terrain.checkCell(x-1, 1) && terrain.checkCell(x+1, 1);
        }else if(x == tHeight-1 && y > 0 && y < tWidth-1){
            // check if it is locked when specimen is at bottom side
            return terrain.checkCell(x, y+1) && terrain.checkCell(x, y-1) && terrain.checkCell(x-1, y) && terrain.checkCell(x-1, y-1) && terrain.checkCell(x-1, y+1);
        }else if(y == tWidth-1 && x > 0 && x < tHeight-1){
            // check if it is locked when specimen is at right side
            return terrain.checkCell(x+1, y) && terrain.checkCell(x-1, y) && terrain.checkCell(x, y-1) && terrain.checkCell(x-1, y-1) && terrain.checkCell(x+1, y-1);
        }
        
        return false;
    }
   
    /**
      * @desc this method checks if specimen is locked, if not
      * checks if child can be placed at (x, y) position, if not recursively calls
      * giveBirth, if it can be placed then creates new child, and places it to
      * (x, y) position, with updating objectMap and map.
      * @param DirectionGenerator dGen - new direction generator
      * @param Terrain terrain - board of our world
      * @param ArrayList<Specimen> habitants - list of all our habitants
      * @param ArrayList<Specimen> children - list of all the children, created
      * during this step.
      * @param int x - x coordinate of position new child should be placed
      * @param int y - y coordinate of position new child should be placed
    */
    public void giveBirthHelper(DirectionGenerator dGen, Terrain terrain, ArrayList<Specimen> habitants, ArrayList<Specimen> children, int x, int y){
        ArrayList<Specimen> tmp = terrain.objectMap.get(x).get(y);
        if(lockedBirth(terrain)){
            // we can not give birth as specimen is locked
        }else if(tmp.size() != 0){
            // recursively call giveBirth() as we cannot move to that position
            giveBirth(dGen, terrain, habitants, children);
        }else{
            // Create a child Specimen, and share energy
            setEnergy(getEnergy()/2);
            Specimen child;
            // create new instance of Animal or Plant depending on parent's type
            if(this instanceof Animal)
                child = new Animal(getName(), getType(), getSymbol(), getEnergySources(), getEnergy(), getStats(), getBirthEnergy(), getMaxEnergy(), getLivingEnergy(), x, y);
            else
                child = new Plant(getName(), getType(), getSymbol(), getEnergySources(), getEnergy(), getStats(), getBirthEnergy(), getMaxEnergy(), getLivingEnergy(), x, y);
            terrain.objectMap.get(x).get(y).add(child);
            terrain.map.get(x).set(y, child.getSymbol());
            children.add(child);
            // disable action, now specimen can not move or eat
            this.action = false;
        }
    } 
    
    /**
      * @desc this method provides death feature for specimen. It set energy
      * to 0, which means that the specimen is dead.
      * @param Terrain terrain - board of our world
      * @param ArrayList<Specimen> habitants - list of all our habitants
    */
    public void die(Terrain terrain, ArrayList<Specimen> habitants){
       setEnergy(0);
    }   
    
    /**
      * @desc this method provides eat feature of specimen. it 
      * is abstract as animals and plants feed differently.
      * @param Terrain terrain - board of our world
      * @param ArrayList<Specimen> habitants - list of all our habitants
    */
    abstract public void eat(Terrain terrain, ArrayList<Specimen> habitants);
   
   
    
   /**
     * @desc getter for specimen's name
     * @return String - name of Specimen
   */
   public String getName(){
       return name;
   }
   
   /**
     * @desc getter for specimen's type
     * @return String - type of Specimen
   */
   public String getType(){
       return type;
   }
   
   /**
     * @desc getter for specimen's symbol
     * @return char - unique symbol of Specimen
   */
   public char getSymbol(){
       return symbol;
   }
   
   /**
     * @desc getter for specimen's list of energySources
     * @return ArrayList<String> - energySources of Specimen
   */
   public ArrayList<String> getEnergySources(){
       return energySources;
   }
   
   /**
     * @desc getter for specimen's stats(death median, std dev)
     * @return ArrayList<Double> - stats of Specimen
   */
   public ArrayList<Double> getStats(){
       return stats;
   }
   
   /**
     * @desc getter for specimen's birth energy
     * @return double - birth energy required for 
     * Specimen to give brth
   */
   public double getBirthEnergy(){
       return birthEnergy;
   }
   
   /**
     * @desc getter for specimen's max energy
     * @return double - maxEnergy of Specimen
   */
   public double getMaxEnergy(){
       return maxEnergy;
   }
    
   /**
     * @desc getter for specimen's living energy
     * @return double - livingEnergy of Specimen
   */
   public double getLivingEnergy(){
       return livingEnergy;
   }
   
   /**
     * @desc getter for specimen's x position
     * @return int - x position of Specimen
   */
   public int getX(){
       return x;
   }
   
   /**
     * @desc getter for specimen's y position
     * @return int - y position of Specimen
   */
   public int getY(){
       return y;
   }
   
   /**
     * @desc getter for specimen's current energy
     * @return double - current energy of Specimen
   */
   public double getEnergy(){
       return energy;
   }
   
   /**
     * @desc getter for specimen's death age
     * @return String - deathAge of Specimen
   */
   public int getDeathAge(){
       return deathAge;
   }
   
   /**
     * @desc getter for specimen's age
     * @return int - age of Specimen
   */
   public int getAge(){
       return age;
   }
   
   /**
     * @desc setter for specimen's x coordinate
   */
   public void setX(int val){
       x = val;
   }
   
   /**
     * @desc setter for specimen's y coordinate
   */
   public void setY(int val){
       y = val;
   }
   
   /**
     * @desc setter for specimen's energy
   */
   public void setEnergy(double val){
       energy = val;
   }
   
   /**
     * @desc setter for specimen's age
   */
   public void setAge(int val){
       age = val;
   }
   
}
