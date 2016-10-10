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
                    // Could write another method for that
                    ArrayList<Specimen> tmp = terrain.objectMap[getX()][getY()-1];
                    if(lockedBirth(terrain)){
                    
                    }else if(tmp.size() != 0){
                        giveBirth(dGen, terrain, habitants, children);
                    }else{
                        // Create a child Specimen
                        setEnergy(getEnergy()/2);
                        int[] position = {getX(), getY()-1};
                        Plant child = new Plant(getName(), getType(), getSymbol(), getEnergySources(), getInitialStats(), getStats(), getBirthEnergy(), getMaxEnergy(), getLivingEnergy(), position);
                        child.setEnergy(getEnergy());
                        terrain.objectMap[getX()][getY()-1].add(child);
                        terrain.map[getX()][getY()-1] = child.getSymbol();
                        children.add(child);
                    }
                }
                break;
            case RIGHT:
                if(getY()  == terrain.getHeight()-1){
                    giveBirth(dGen, terrain, habitants, children);
                }else{
                   ArrayList<Specimen> tmp = terrain.objectMap[getX()][getY()+1];
                    if(lockedBirth(terrain)){
                    
                    }else if(tmp.size() != 0){
                        giveBirth(dGen, terrain, habitants, children);
                    }else{
                        // Create a child Specimen
                        setEnergy(getEnergy()/2);
                        int[] position = {getX(), getY()+1};
                        Plant child = new Plant(getName(), getType(), getSymbol(), getEnergySources(), getInitialStats(), getStats(), getBirthEnergy(), getMaxEnergy(), getLivingEnergy(), position);
                        child.setEnergy(getEnergy());
                        terrain.objectMap[getX()][getY()+1].add(child);
                        terrain.map[getX()][getY()+1] = child.getSymbol();
                        children.add(child);
                    }
                }
                break;
            case UP:
                if(getX() == 0){
                    giveBirth(dGen, terrain, habitants, children);
                }else{
                    ArrayList<Specimen> tmp = terrain.objectMap[getX()-1][getY()];
                    if(lockedBirth(terrain)){
                    
                    }else if(tmp.size() != 0){
                        giveBirth(dGen, terrain, habitants, children);
                    }else{
                        // Create a child Specimen
                        setEnergy(getEnergy()/2);
                        int[] position = {getX()-1, getY()};
                        Plant child = new Plant(getName(), getType(), getSymbol(), getEnergySources(), getInitialStats(), getStats(), getBirthEnergy(), getMaxEnergy(), getLivingEnergy(), position);
                        child.setEnergy(getEnergy());
                        terrain.objectMap[getX()-1][getY()].add(child);
                        terrain.map[getX()-1][getY()] = child.getSymbol();
                        children.add(child);
                    }
                }
                break;
            case DOWN:
                if(getX() == terrain.getWidth()-1){
                    giveBirth(dGen, terrain, habitants, children);
                }else{
                    ArrayList<Specimen> tmp = terrain.objectMap[getX()+1][getY()];
                    if(lockedBirth(terrain)){
                    
                    }else if(tmp.size() != 0){
                        giveBirth(dGen, terrain, habitants, children);
                    }else{
                        // Create a child Specimen
                        setEnergy(getEnergy()/2);
                        int[] position = {getX()+1, getY()};
                        Plant child = new Plant(getName(), getType(), getSymbol(), getEnergySources(), getInitialStats(), getStats(), getBirthEnergy(), getMaxEnergy(), getLivingEnergy(), position);
                        child.setEnergy(getEnergy());
                        terrain.objectMap[getX()+1][getY()].add(child);
                        terrain.map[getX()+1][getY()] = child.getSymbol();
                        children.add(child);
                    }
                   
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
        
        if(x > 0 && y > 0 && x < terrain.getHeight()-1 && y < terrain.getWidth()-1){
            ArrayList<Specimen> tmp_01 = terrain.objectMap[x][y-1];
            ArrayList<Specimen> tmp_02 = terrain.objectMap[x][y+1];
            ArrayList<Specimen> tmp_03 = terrain.objectMap[x-1][y];
            ArrayList<Specimen> tmp_04 = terrain.objectMap[x+1][y];
            if(  tmp_01.size() != 0  && tmp_02.size() != 0 && tmp_03.size() != 0 && tmp_04.size() != 0){
                    return true;
            }
            return false;
        }else if(x == 0 && y == 0){
            ArrayList<Specimen> tmp_01 = terrain.objectMap[0][1];
            ArrayList<Specimen> tmp_02 = terrain.objectMap[1][0];

            if( tmp_01.size() != 0 && tmp_02.size() != 0){
                    return true;
            }
            return false;
        }else if(x == terrain.getHeight()-1 && y == terrain.getWidth()-1){
            ArrayList<Specimen> tmp_01 = terrain.objectMap[terrain.getHeight()-1][terrain.getWidth()-2];
            ArrayList<Specimen> tmp_02 = terrain.objectMap[terrain.getHeight()-2][terrain.getWidth()-1];
            if( tmp_01.size() != 0 && tmp_02.size() != 0 ){
                    return true;
            }
            return false;
        }else if(x == terrain.getHeight()-1 && y == 0){
            ArrayList<Specimen> tmp_01 = terrain.objectMap[terrain.getHeight()-1][1];
            ArrayList<Specimen> tmp_02 = terrain.objectMap[terrain.getHeight()-2][0];
            if( tmp_01.size() != 0 && tmp_02.size() != 0 ){
                    return true;
            }
            return false;
        }else if(x == 0 && y == terrain.getWidth()-1){
            ArrayList<Specimen> tmp_01 = terrain.objectMap[0][terrain.getWidth()-2];
            ArrayList<Specimen> tmp_02 = terrain.objectMap[1][terrain.getWidth()-1];
            if( tmp_01.size() != 0 && tmp_02.size() != 0 ){
                    return true;
            }
            return false;
        }else if(x == 0 && y > 0 && y < terrain.getWidth()-1){
            ArrayList<Specimen> tmp_01 = terrain.objectMap[0][y+1];
            ArrayList<Specimen> tmp_02 = terrain.objectMap[0][y-1];
            ArrayList<Specimen> tmp_03 = terrain.objectMap[1][y];
            if( tmp_01.size() != 0 && tmp_02.size() != 0 && tmp_03.size() != 0 ){
                    return true;
            }
            return false;
        }else if(y == 0 && x > 0 && x < terrain.getHeight()-1){
            ArrayList<Specimen> tmp_01 = terrain.objectMap[x+1][0];
            ArrayList<Specimen> tmp_02 = terrain.objectMap[x-1][0];
            ArrayList<Specimen> tmp_03 = terrain.objectMap[x][1];
            if( tmp_01.size() != 0 && tmp_02.size() != 0 && tmp_03.size() != 0 ){
                    return true;
            }
            return false;
        }else if(x == terrain.getHeight()-1 && y > 0 && y < terrain.getWidth()-1){
            ArrayList<Specimen> tmp_01 = terrain.objectMap[x][y+1];
            ArrayList<Specimen> tmp_02 = terrain.objectMap[x][y-1];
            ArrayList<Specimen> tmp_03 = terrain.objectMap[x-1][y];
            if( tmp_01.size() != 0 && tmp_02.size() != 0 && tmp_03.size() != 0 ){
                    return true;
            }
            return false;
        }else if(y == terrain.getWidth()-1 && x > 0 && x < terrain.getHeight()-1){
            ArrayList<Specimen> tmp_01 = terrain.objectMap[x+1][y];
            ArrayList<Specimen> tmp_02 = terrain.objectMap[x-1][y];
            ArrayList<Specimen> tmp_03 = terrain.objectMap[x][y-1];
            if( tmp_01.size() != 0 && tmp_02.size() != 0 && tmp_03.size() != 0 ){
                    return true;
            }
            return false;
        }
        
        return false;
    }
    
}
