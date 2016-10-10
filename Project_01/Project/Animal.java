import java.util.ArrayList;

/**
 * Zura Mestiashvili
 */
public class Animal extends Specimen implements Movable, Inhabitant{
    
    public Animal(String name, String type, char symbol, ArrayList<String> energySources,
                   ArrayList<Double> initialStats,  ArrayList<Double> stats, double birthEnergy, double maxEnergy,
                   double livingEnergy, int[] position){
        
       super(name, type, symbol, energySources, initialStats, stats, birthEnergy, maxEnergy, 
             livingEnergy, position);

    }
    
    public void move(DirectionGenerator dGen, Terrain terrain, ArrayList<Specimen> habitants){
        
        switch(dGen.next()){
            case LEFT:
                if(getY() == 0 ){
                    move(dGen, terrain, habitants);
                }else{
                    // Could write another method for that
                    ArrayList<Specimen> tmp = terrain.objectMap[getX()][getY()-1];
                    // The right version according to description: if(tmp.size() >= 2 || (tmp.size() != 0 && !this.getEnergySources().contains(tmp.get(0).getName()))){
                    if(locked(terrain)){
                        
                    }else if( tmp.size() >= 2 || (tmp.size() == 1 && tmp.get(0) instanceof Animal && !this.getEnergySources().contains(tmp.get(0).getName())) ){
                        move(dGen, terrain, habitants);
                    }else{
                        terrain.objectMap[getX()][getY()].remove(this);
                        terrain.map[getX()][getY()] = '#';
                        setY(getY()-1);
                        terrain.objectMap[getX()][getY()].add(this);
                        terrain.map[getX()][getY()] = this.getSymbol();
                    }
                }
                break;
            case RIGHT:
                if(getY()  == terrain.getHeight()-1){
                    move(dGen, terrain, habitants);
                }else{
                   ArrayList<Specimen> tmp = terrain.objectMap[getX()][getY()+1];
                   if(locked(terrain)){
                       
                   }else if( tmp.size() >= 2 || (tmp.size() == 1 && tmp.get(0) instanceof Animal && !this.getEnergySources().contains(tmp.get(0).getName())) ){
                       move(dGen, terrain, habitants);
                   }else{
                       terrain.objectMap[getX()][getY()].remove(this);
                       terrain.map[getX()][getY()] = '#';
                       setY(getY()+1);
                       terrain.objectMap[getX()][getY()].add(this);
                       terrain.map[getX()][getY()] = this.getSymbol();
                   }
                }
                break;
            case UP:
                if(getX() == 0){
                    move(dGen, terrain, habitants);
                }else{
                    ArrayList<Specimen> tmp = terrain.objectMap[getX()-1][getY()];
                    if(locked(terrain)){
                        
                    }else if( tmp.size() >= 2 || (tmp.size() == 1 && tmp.get(0) instanceof Animal && !this.getEnergySources().contains(tmp.get(0).getName())) ){
                        move(dGen, terrain, habitants);
                    }else{
                        terrain.objectMap[getX()][getY()].remove(this);
                        terrain.map[getX()][getY()] = '#';
                        setX(getX()-1);
                        terrain.objectMap[getX()][getY()].add(this);
                        terrain.map[getX()][getY()] = this.getSymbol();
                    }
                }
                break;
            case DOWN:
                if(getX() == terrain.getWidth()-1){
                    move(dGen, terrain, habitants);
                }else{
                    ArrayList<Specimen> tmp = terrain.objectMap[getX()+1][getY()];
                    if(locked(terrain)){
                        
                    }else if( tmp.size() >= 2 || (tmp.size() == 1 && tmp.get(0) instanceof Animal && !this.getEnergySources().contains(tmp.get(0).getName())) ){
                        move(dGen, terrain, habitants);
                    }else{
                        terrain.objectMap[getX()][getY()].remove(this);
                        terrain.map[getX()][getY()] = '#';
                        setX(getX()+1);
                        terrain.objectMap[getX()][getY()].add(this);
                        terrain.map[getX()][getY()] = this.getSymbol();
                    }
                }
                break;
        }
        
        
    }
   
    public void eat(Terrain terrain, ArrayList<Specimen> habitants){
        ArrayList<Specimen> tmp = terrain.objectMap[getX()][getY()];
        for(int i = 0; i < tmp.size(); i++){
            Specimen tmpHabitant = tmp.get(i);
            
            if(this != tmpHabitant && getEnergy() != 0 && tmpHabitant.getEnergy() != 0 && this.getEnergySources().contains(tmpHabitant.getName())){
                setEnergy(getEnergy() + tmpHabitant.getEnergy());
                if(tmpHabitant instanceof Plant) ((Plant)tmpHabitant).die(terrain, habitants);
                else ((Animal)tmpHabitant).die(terrain, habitants);
            }
            
        }


    }
    
    public void giveBirth(){}
    public void die(Terrain terrain, ArrayList<Specimen> habitants){
        setEnergy(0);
        System.out.println("Currently we have " + habitants.size() + " habitants, animal");
    }
    
    public boolean locked(Terrain terrain){
        int x = getX();
        int y = getY();
        
        if(x > 0 && y > 0 && x < terrain.getHeight()-1 && y < terrain.getWidth()-1){
            ArrayList<Specimen> tmp_01 = terrain.objectMap[x][y-1];
            ArrayList<Specimen> tmp_02 = terrain.objectMap[x][y+1];
            ArrayList<Specimen> tmp_03 = terrain.objectMap[x-1][y];
            ArrayList<Specimen> tmp_04 = terrain.objectMap[x+1][y];
            if( ( tmp_01.size() >= 2 || (tmp_01.size() == 1 && tmp_01.get(0) instanceof Animal && !this.getEnergySources().contains(tmp_01.get(0).getName())) ) && 
                ( tmp_02.size() >= 2 || (tmp_02.size() == 1 && tmp_02.get(0) instanceof Animal && !this.getEnergySources().contains(tmp_02.get(0).getName())) ) &&     
                ( tmp_03.size() >= 2 || (tmp_03.size() == 1 && tmp_03.get(0) instanceof Animal && !this.getEnergySources().contains(tmp_03.get(0).getName())) ) &&
                ( tmp_04.size() >= 2 || (tmp_04.size() == 1 && tmp_04.get(0) instanceof Animal && !this.getEnergySources().contains(tmp_04.get(0).getName())) ) ){
                    return true;
            }
            return false;
        }else if(x == 0 && y == 0){
            ArrayList<Specimen> tmp_01 = terrain.objectMap[0][1];
            ArrayList<Specimen> tmp_02 = terrain.objectMap[1][0];

            if( ( tmp_01.size() >= 2 || (tmp_01.size() == 1 && tmp_01.get(0) instanceof Animal && !this.getEnergySources().contains(tmp_01.get(0).getName())) ) && 
                ( tmp_02.size() >= 2 || (tmp_02.size() == 1 && tmp_02.get(0) instanceof Animal && !this.getEnergySources().contains(tmp_02.get(0).getName())) ) ){
                    return true;
            }
            return false;
        }else if(x == terrain.getHeight()-1 && y == terrain.getWidth()-1){
            ArrayList<Specimen> tmp_01 = terrain.objectMap[terrain.getHeight()-1][terrain.getWidth()-2];
            ArrayList<Specimen> tmp_02 = terrain.objectMap[terrain.getHeight()-2][terrain.getWidth()-1];
            if( ( tmp_01.size() >= 2 || (tmp_01.size() == 1 && tmp_01.get(0) instanceof Animal && !this.getEnergySources().contains(tmp_01.get(0).getName())) ) && 
                ( tmp_02.size() >= 2 || (tmp_02.size() == 1 && tmp_02.get(0) instanceof Animal && !this.getEnergySources().contains(tmp_02.get(0).getName())) ) ){
                    return true;
            }
            return false;
        }else if(x == terrain.getHeight()-1 && y == 0){
            ArrayList<Specimen> tmp_01 = terrain.objectMap[terrain.getHeight()-1][1];
            ArrayList<Specimen> tmp_02 = terrain.objectMap[terrain.getHeight()-2][0];
            if( ( tmp_01.size() >= 2 || (tmp_01.size() == 1 && tmp_01.get(0) instanceof Animal && !this.getEnergySources().contains(tmp_01.get(0).getName())) ) && 
                ( tmp_02.size() >= 2 || (tmp_02.size() == 1 && tmp_02.get(0) instanceof Animal && !this.getEnergySources().contains(tmp_02.get(0).getName())) ) ){
                    return true;
            }
            return false;
        }else if(x == 0 && y == terrain.getWidth()-1){
            ArrayList<Specimen> tmp_01 = terrain.objectMap[0][terrain.getWidth()-2];
            ArrayList<Specimen> tmp_02 = terrain.objectMap[1][terrain.getWidth()-1];
            if( ( tmp_01.size() >= 2 || (tmp_01.size() == 1 && tmp_01.get(0) instanceof Animal && !this.getEnergySources().contains(tmp_01.get(0).getName())) ) && 
                ( tmp_02.size() >= 2 || (tmp_02.size() == 1 && tmp_02.get(0) instanceof Animal && !this.getEnergySources().contains(tmp_02.get(0).getName())) ) ){
                    return true;
            }
            return false;
        }else if(x == 0 && y > 0 && y < terrain.getWidth()-1){
            ArrayList<Specimen> tmp_01 = terrain.objectMap[0][y+1];
            ArrayList<Specimen> tmp_02 = terrain.objectMap[0][y-1];
            ArrayList<Specimen> tmp_03 = terrain.objectMap[1][y];
            if( ( tmp_01.size() >= 2 || (tmp_01.size() == 1 && tmp_01.get(0) instanceof Animal && !this.getEnergySources().contains(tmp_01.get(0).getName())) ) &&
                ( tmp_02.size() >= 2 || (tmp_02.size() == 1 && tmp_02.get(0) instanceof Animal && !this.getEnergySources().contains(tmp_02.get(0).getName())) ) &&
                ( tmp_03.size() >= 2 || (tmp_03.size() == 1 && tmp_03.get(0) instanceof Animal && !this.getEnergySources().contains(tmp_03.get(0).getName())) ) ){
                    return true;
            }
            return false;
        }else if(y == 0 && x > 0 && x < terrain.getHeight()-1){
            ArrayList<Specimen> tmp_01 = terrain.objectMap[x+1][0];
            ArrayList<Specimen> tmp_02 = terrain.objectMap[x-1][0];
            ArrayList<Specimen> tmp_03 = terrain.objectMap[x][1];
            if( ( tmp_01.size() >= 2 || (tmp_01.size() == 1 && tmp_01.get(0) instanceof Animal && !this.getEnergySources().contains(tmp_01.get(0).getName())) ) &&
                ( tmp_02.size() >= 2 || (tmp_02.size() == 1 && tmp_02.get(0) instanceof Animal && !this.getEnergySources().contains(tmp_02.get(0).getName())) ) &&
                ( tmp_03.size() >= 2 || (tmp_03.size() == 1 && tmp_03.get(0) instanceof Animal && !this.getEnergySources().contains(tmp_03.get(0).getName())) ) ){
                    return true;
            }
            return false;
        }else if(x == terrain.getHeight()-1 && y > 0 && y < terrain.getWidth()-1){
            ArrayList<Specimen> tmp_01 = terrain.objectMap[x][y+1];
            ArrayList<Specimen> tmp_02 = terrain.objectMap[x][y-1];
            ArrayList<Specimen> tmp_03 = terrain.objectMap[x-1][y];
            if( ( tmp_01.size() >= 2 || (tmp_01.size() == 1 && tmp_01.get(0) instanceof Animal && !this.getEnergySources().contains(tmp_01.get(0).getName())) ) &&
                ( tmp_02.size() >= 2 || (tmp_02.size() == 1 && tmp_02.get(0) instanceof Animal && !this.getEnergySources().contains(tmp_02.get(0).getName())) ) &&
                ( tmp_03.size() >= 2 || (tmp_03.size() == 1 && tmp_03.get(0) instanceof Animal && !this.getEnergySources().contains(tmp_03.get(0).getName())) ) ){
                    return true;
            }
            return false;
        }else if(y == terrain.getWidth()-1 && x > 0 && x < terrain.getHeight()-1){
            ArrayList<Specimen> tmp_01 = terrain.objectMap[x+1][y];
            ArrayList<Specimen> tmp_02 = terrain.objectMap[x-1][y];
            ArrayList<Specimen> tmp_03 = terrain.objectMap[x][y-1];
            if( ( tmp_01.size() >= 2 || (tmp_01.size() == 1 && tmp_01.get(0) instanceof Animal && !this.getEnergySources().contains(tmp_01.get(0).getName())) ) &&
                ( tmp_02.size() >= 2 || (tmp_02.size() == 1 && tmp_02.get(0) instanceof Animal && !this.getEnergySources().contains(tmp_02.get(0).getName())) ) &&
                ( tmp_03.size() >= 2 || (tmp_03.size() == 1 && tmp_03.get(0) instanceof Animal && !this.getEnergySources().contains(tmp_03.get(0).getName())) ) ){
                    return true;
            }
            return false;
        }
        
        return false;
    }
}
