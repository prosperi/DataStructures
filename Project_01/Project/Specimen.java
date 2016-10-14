import java.util.ArrayList;

/**
 * Zura Mestiashvili
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
   
   
   public void giveBirth(DirectionGenerator dGen, Terrain terrain, ArrayList<Specimen> habitants, ArrayList<Specimen> children){
        
        if(getEnergy() >= getBirthEnergy()){
            switch(dGen.next()){
            case LEFT:
                if(getY() == 0 ){
                    giveBirth(dGen, terrain, habitants, children);
                }else{
                    giveBirthHelper(dGen, terrain, habitants, children, getX(), getY()-1);
                }
                break;
            case RIGHT:
                if(getY()  == terrain.getWidth()-1){
                    giveBirth(dGen, terrain, habitants, children);
                }else{
                    giveBirthHelper(dGen, terrain, habitants, children, getX(), getY()+1);
                }
                break;
            case UP:
                if(getX() == 0){
                    giveBirth(dGen, terrain, habitants, children);
                }else{
                    giveBirthHelper(dGen, terrain, habitants, children, getX()-1, getY());
                }
                break;
            case DOWN:
                if(getX() == terrain.getHeight()-1){
                    giveBirth(dGen, terrain, habitants, children);
                }else{
                    giveBirthHelper(dGen, terrain, habitants, children, getX()+1, getY());
                }
                break;
            case UP_RIGHT:
                if(getX() == 0 || getY() == terrain.getWidth() - 1){
                    giveBirth(dGen, terrain, habitants, children);
                }else{
                    giveBirthHelper(dGen, terrain, habitants, children, getX()-1, getY() + 1);
                }
                break;
            case UP_LEFT:
                if(getX() == 0 || getY() == 0){
                    giveBirth(dGen, terrain, habitants, children);
                }else{
                    giveBirthHelper(dGen, terrain, habitants, children, getX() - 1, getY() - 1);
                }
                break;
            case DOWN_RIGHT:
                if(getX() == terrain.getHeight() - 1 || getY() == terrain.getWidth() - 1){
                    giveBirth(dGen, terrain, habitants, children);
                }else{
                    giveBirthHelper(dGen, terrain, habitants, children, getX() + 1, getY() + 1);
                }
                break;
            case DOWN_LEFT:
                if(getX() == terrain.getHeight() - 1 || getY() == 0){
                    giveBirth(dGen, terrain, habitants, children);
                }else{
                    giveBirthHelper(dGen, terrain, habitants, children, getX() + 1, getY() - 1);
                }
                break;
            }
        }
        
    }
    
    
    public boolean lockedBirth(Terrain terrain){
        int x = getX();
        int y = getY();
        int tHeight = terrain.getHeight();
        int tWidth = terrain.getWidth();
        
        if(x > 0 && y > 0 && x < tHeight-1 && y < tWidth-1){
            return terrain.checkCell(x, y-1)  && terrain.checkCell(x, y+1) && terrain.checkCell(x-1, y) && terrain.checkCell(x+1, y) &&
                   terrain.checkCell(x-1, y-1) && terrain.checkCell(x+1, y+1) && terrain.checkCell(x-1, y+1) && terrain.checkCell(x+1, y-1);
        }else if(x == 0 && y == 0){
            return terrain.checkCell(0, 1) && terrain.checkCell(1, 0) && terrain.checkCell(1, 1);
        }else if(x == tHeight-1 && y == tWidth-1){
            return terrain.checkCell(tHeight-1, tWidth-2) && terrain.checkCell(tHeight-2, tWidth-1) && terrain.checkCell(tHeight-2, tWidth-2);
        }else if(x == tHeight-1 && y == 0){
            return terrain.checkCell(tHeight-1, 1) && terrain.checkCell(tHeight-2, 0) && terrain.checkCell(tHeight-2, 1);
        }else if(x == 0 && y == tWidth-1){
            return terrain.checkCell(0, tWidth-2) && terrain.checkCell(1, tWidth-1) && terrain.checkCell(1, tWidth-2);
        }else if(x == 0 && y > 0 && y < tWidth-1){
            return terrain.checkCell(0, y+1) && terrain.checkCell(0, y-1) && terrain.checkCell(1, y) && terrain.checkCell(1, y-1) && terrain.checkCell(1, y+1);
        }else if(y == 0 && x > 0 && x < tHeight-1){
            return terrain.checkCell(x+1, 0) && terrain.checkCell(x-1, 0) && terrain.checkCell(x, 1) && terrain.checkCell(x-1, 1) && terrain.checkCell(x+1, 1);
        }else if(x == tHeight-1 && y > 0 && y < tWidth-1){
            return terrain.checkCell(x, y+1) && terrain.checkCell(x, y-1) && terrain.checkCell(x-1, y) && terrain.checkCell(x-1, y-1) && terrain.checkCell(x-1, y+1);
        }else if(y == tWidth-1 && x > 0 && x < tHeight-1){
            return terrain.checkCell(x+1, y) && terrain.checkCell(x-1, y) && terrain.checkCell(x, y-1) && terrain.checkCell(x-1, y-1) && terrain.checkCell(x+1, y-1);
        }
        
        return false;
    }
   
    public void giveBirthHelper(DirectionGenerator dGen, Terrain terrain, ArrayList<Specimen> habitants, ArrayList<Specimen> children, int x, int y){
        ArrayList<Specimen> tmp = terrain.objectMap[x][y];
        if(lockedBirth(terrain)){
            // we can not give birth
        }else if(tmp.size() != 0){
            giveBirth(dGen, terrain, habitants, children);
        }else{
            // Create a child Specimen
            setEnergy(getEnergy()/2);
            Specimen child;
            if(this instanceof Animal)
                child = new Animal(getName(), getType(), getSymbol(), getEnergySources(), getEnergy(), getStats(), getBirthEnergy(), getMaxEnergy(), getLivingEnergy(), x, y);
            else
                child = new Plant(getName(), getType(), getSymbol(), getEnergySources(), getEnergy(), getStats(), getBirthEnergy(), getMaxEnergy(), getLivingEnergy(), x, y);
            terrain.objectMap[x][y].add(child);
            terrain.map[x][y] = child.getSymbol();
            children.add(child);
            this.action = false;
        }
    } 
    
    public void die(Terrain terrain, ArrayList<Specimen> habitants){
       setEnergy(0);
       System.out.println("Currently we have " + habitants.size() + " habitants " + getX() + " " + getY());
    }   
    
    abstract public void eat(Terrain terrain, ArrayList<Specimen> habitants);

   
   /////  Setters and Getters as we want our data to be private, though we will not need Setters as those properties
   ////   are defined while the simulation starts, and do not change later ????? - think about this more
   
   /// Getters
   public String getName(){
       return name;
   }
   
   public String getType(){
       return type;
   }
   
   public char getSymbol(){
       return symbol;
   }
   
   public ArrayList<String> getEnergySources(){
       return energySources;
   }
   
   public ArrayList<Double> getStats(){
       return stats;
   }
   
   public double getBirthEnergy(){
       return birthEnergy;
   }
   
   public double getMaxEnergy(){
       return maxEnergy;
   }
    
   public double getLivingEnergy(){
       return livingEnergy;
   }
   
   public int getX(){
       return x;
   }
   
   public int getY(){
       return y;
   }
   
   public double getEnergy(){
       return energy;
   }
   
   public int getDeathAge(){
       return deathAge;
   }
   
   public int getAge(){
       return age;
   }
   
   public void setX(int val){
       x = val;
   }
   
   public void setY(int val){
       y = val;
   }
   
   public void setEnergy(double val){
       energy = val;
   }
   
   public void setAge(int val){
       age = val;
   }
   
}
