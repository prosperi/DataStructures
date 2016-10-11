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
    
   
   public void eat(Terrain terrain, ArrayList<Specimen> habitants){
       if(getEnergy()  > 0){ 
           setEnergy(getEnergy() + terrain.getLight());
       }
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
            }
        }
        
    }
   
   
   public void die(Terrain terrain, ArrayList<Specimen> habitants){
       setEnergy(0);
       System.out.println("Currently we have " + habitants.size() + " habitants, plant");
   }   
   
   
   public boolean lockedBirth(Terrain terrain){
        int x = getX();
        int y = getY();
        int tHeight = terrain.getHeight();
        int tWidth = terrain.getWidth();
        
        if(x > 0 && y > 0 && x < tHeight-1 && y < tWidth-1){
            return terrain.checkCell(x, y-1)  && terrain.checkCell(x, y+1) && terrain.checkCell(x-1, y) && terrain.checkCell(x+1, y);
        }else if(x == 0 && y == 0){
            return terrain.checkCell(1, 1) && terrain.checkCell(1, 0);
        }else if(x == tHeight-1 && y == tWidth-1){
            return terrain.checkCell(tHeight-1, tWidth-2) && terrain.checkCell(tHeight-2, tWidth-1);
        }else if(x == tHeight-1 && y == 0){
            return terrain.checkCell(tHeight-1, 1) && terrain.checkCell(tHeight-2, 0);
        }else if(x == 0 && y == tWidth-1){
            return terrain.checkCell(0, tWidth-2) && terrain.checkCell(1, tWidth-1);
        }else if(x == 0 && y > 0 && y < tWidth-1){
            return terrain.checkCell(0, y+1) && terrain.checkCell(0, y-1) && terrain.checkCell(1, y);
        }else if(y == 0 && x > 0 && x < tHeight-1){
            return terrain.checkCell(x+1, 0) && terrain.checkCell(x-1, 0) && terrain.checkCell(x, 1);
        }else if(x == tHeight-1 && y > 0 && y < tWidth-1){
            return terrain.checkCell(x, y+1) && terrain.checkCell(x, y-1) && terrain.checkCell(x-1, y);
        }else if(y == tWidth-1 && x > 0 && x < tHeight-1){
            return terrain.checkCell(x+1, y) && terrain.checkCell(x-1, y) && terrain.checkCell(x, y-1);
        }
        
        return false;
    }
    
    
    
   public void giveBirthHelper(DirectionGenerator dGen, Terrain terrain, ArrayList<Specimen> habitants, ArrayList<Specimen> children, int x, int y){
        ArrayList<Specimen> tmp = terrain.objectMap[x][y];
        if(lockedBirth(terrain)){
        
        }else if(tmp.size() != 0){
            giveBirth(dGen, terrain, habitants, children);
        }else{
            // Create a child Specimen
            setEnergy(getEnergy()/2);
            int[] position = {x, y};
            Animal child = new Animal(getName(), getType(), getSymbol(), getEnergySources(), getInitialStats(), getStats(), getBirthEnergy(), getMaxEnergy(), getLivingEnergy(), position);
            child.setEnergy(getEnergy());
            terrain.objectMap[x][y].add(child);
            terrain.map[x][y] = child.getSymbol();
            children.add(child);
        }
    } 
    
}
