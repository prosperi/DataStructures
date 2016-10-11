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
                    moveHelper(dGen, terrain, habitants, getX(), getY() - 1 );
                }
                break;
            case RIGHT:
                if(getY()  == terrain.getWidth()-1){
                    move(dGen, terrain, habitants);
                }else{
                    moveHelper(dGen, terrain, habitants, getX(), getY() + 1 );
                }
                break;
            case UP:
                if(getX() == 0){
                    move(dGen, terrain, habitants);
                }else{
                    moveHelper(dGen, terrain, habitants, getX() - 1, getY() );
                }
                break;
            case DOWN:
                if(getX() == terrain.getHeight()-1){
                    move(dGen, terrain, habitants);
                }else{
                    moveHelper(dGen, terrain, habitants, getX() + 1, getY() );
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
    
    // Could use Abstractness
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
        System.out.println("Currently we have " + habitants.size() + " habitants, animal");
    }
    
    public boolean locked(Terrain terrain){
        int x = getX();
        int y = getY();
        int tHeight = terrain.getHeight();
        int tWidth = terrain.getWidth();
        
        if(x > 0 && y > 0 && x < tHeight-1 && y < tWidth-1){
            return lockedHelper(terrain, x, y-1) && lockedHelper(terrain, x, y+1) && lockedHelper(terrain, x-1, y) && lockedHelper(terrain, x+1, y);          
        }else if(x == 0 && y == 0){
            return lockedHelper(terrain, 0, 1) && lockedHelper(terrain, 1, 0);
        }else if(x == tHeight-1 && y == tWidth-1){
            return lockedHelper(terrain, tHeight - 1, tWidth - 2) && lockedHelper(terrain, tHeight - 2, tWidth - 1);
        }else if(x == tHeight-1 && y == 0){
            return lockedHelper(terrain, tHeight - 1, 1) && lockedHelper(terrain, tHeight - 2, 0);
        }else if(x == 0 && y == tWidth-1){
            return lockedHelper(terrain, 0, tWidth - 2) && lockedHelper(terrain, 1, tWidth - 1);
        }else if(x == 0 && y > 0 && y < terrain.getWidth()-1){
            return lockedHelper(terrain, 0, y+1) && lockedHelper(terrain, 0, y-1) && lockedHelper(terrain, 1, y);
        }else if(y == 0 && x > 0 && x < tHeight-1){
            return lockedHelper(terrain, x+1, 0) && lockedHelper(terrain, x-1, 0) && lockedHelper(terrain, x, 1);
        }else if(x == terrain.getHeight()-1 && y > 0 && y < tWidth-1){
            return lockedHelper(terrain, x, y+1) && lockedHelper(terrain, x, y-1) && lockedHelper(terrain, x-1, y);
        }else if(y == tWidth-1 && x > 0 && x < tHeight-1){
            return lockedHelper(terrain, x+1, y) && lockedHelper(terrain, x-1, y) && lockedHelper(terrain, x, y-1);
        }
        
        return false;
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
    
    
    
    public boolean lockedHelper(Terrain terrain, int x, int y){
        ArrayList<Specimen> tmp = terrain.objectMap[x][y];
        
        if( tmp.size() >= 2 || (tmp.size() == 1 && tmp.get(0) instanceof Animal && !this.getEnergySources().contains(tmp.get(0).getName())) )
            return true;
        
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
            int[] position = {x, y};
            Animal child = new Animal(getName(), getType(), getSymbol(), getEnergySources(), getInitialStats(), getStats(), getBirthEnergy(), getMaxEnergy(), getLivingEnergy(), position);
            child.setEnergy(getEnergy());
            terrain.objectMap[x][y].add(child);
            terrain.map[x][y] = child.getSymbol();
            children.add(child);
        }
    }
    
    public void moveHelper(DirectionGenerator dGen, Terrain terrain, ArrayList<Specimen> habitants, int x, int y){
        ArrayList<Specimen> tmp = terrain.objectMap[x][y];
        if(locked(terrain)){
            // we can not move
        }else if( tmp.size() >= 2 || (tmp.size() == 1 && tmp.get(0) instanceof Animal && !this.getEnergySources().contains(tmp.get(0).getName())) ){
            move(dGen, terrain, habitants);
        }else{
            terrain.objectMap[getX()][getY()].remove(this);
            terrain.map[getX()][getY()] = '#';
            setY(y);
            setX(x);
            terrain.objectMap[x][y].add(this);
            terrain.map[x][y] = this.getSymbol();
        }
    }
    
}
