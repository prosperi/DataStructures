import java.util.ArrayList;

/**
 * Zura Mestiashvili
 */


public class Animal extends Specimen implements Movable{
    
    public Animal(String name, String type, char symbol, ArrayList<String> energySources,
                   double energy,  ArrayList<Double> stats, double birthEnergy, double maxEnergy,
                   double livingEnergy, int x, int y){
        
       super(name, type, symbol, energySources, energy, stats, birthEnergy, maxEnergy, 
             livingEnergy, x, y);

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
            case UP_RIGHT:
                if(getX() == 0 || getY() == terrain.getWidth() - 1){
                    move(dGen, terrain, habitants);
                }else{
                    moveHelper(dGen, terrain, habitants, getX()-1, getY() + 1);
                }
                break;
            case UP_LEFT:
                if(getX() == 0 || getY() == 0){
                    move(dGen, terrain, habitants);
                }else{
                    moveHelper(dGen, terrain, habitants, getX() - 1, getY() - 1);
                }
                break;
            case DOWN_RIGHT:
                if(getX() == terrain.getHeight() - 1 || getY() == terrain.getWidth() - 1){
                    move(dGen, terrain, habitants);
                }else{
                    moveHelper(dGen, terrain, habitants, getX() + 1, getY() + 1);
                }
                break;
            case DOWN_LEFT:
                if(getX() == terrain.getHeight() - 1 || getY() == 0){
                    move(dGen, terrain, habitants);
                }else{
                    moveHelper(dGen, terrain, habitants, getX() + 1, getY() - 1);
                }
                break;
        }
        
        
    }
   
    public void eat(Terrain terrain, ArrayList<Specimen> habitants){
        ArrayList<Specimen> tmp = terrain.objectMap[getX()][getY()];
        for(int i = 0; i < tmp.size(); i++){
            Specimen tmpHabitant = tmp.get(i);
            
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
    
    
    
    public boolean locked(Terrain terrain){
        int x = getX();
        int y = getY();
        int tHeight = terrain.getHeight();
        int tWidth = terrain.getWidth();
        
        if(x > 0 && y > 0 && x < tHeight-1 && y < tWidth-1){
            return lockedHelper(terrain, x, y-1) && lockedHelper(terrain, x, y+1) && lockedHelper(terrain, x-1, y) && lockedHelper(terrain, x+1, y) &&
                   lockedHelper(terrain, x-1, y-1) && lockedHelper(terrain, x+1, y+1) && lockedHelper(terrain, x-1, y+1) && lockedHelper(terrain, x+1, y-1);          
        }else if(x == 0 && y == 0){
            return lockedHelper(terrain, 0, 1) && lockedHelper(terrain, 1, 0) && lockedHelper(terrain, 1, 1);
        }else if(x == tHeight-1 && y == tWidth-1){
            return lockedHelper(terrain, tHeight - 1, tWidth - 2) && lockedHelper(terrain, tHeight - 2, tWidth - 1) && lockedHelper(terrain, tHeight-2, tWidth-2);
        }else if(x == tHeight-1 && y == 0){
            return lockedHelper(terrain, tHeight - 1, 1) && lockedHelper(terrain, tHeight - 2, 0) && lockedHelper(terrain, tHeight-2, 1);
        }else if(x == 0 && y == tWidth-1){
            return lockedHelper(terrain, 0, tWidth - 2) && lockedHelper(terrain, 1, tWidth - 1) && lockedHelper(terrain, 1, tWidth-2);
        }else if(x == 0 && y > 0 && y < tWidth-1){
            return lockedHelper(terrain, 0, y+1) && lockedHelper(terrain, 0, y-1) && lockedHelper(terrain, 1, y) && lockedHelper(terrain, 1, y-1) && lockedHelper(terrain, 1, y+1);
        }else if(y == 0 && x > 0 && x < tHeight-1){
            return lockedHelper(terrain, x+1, 0) && lockedHelper(terrain, x-1, 0) && lockedHelper(terrain, x, 1) && lockedHelper(terrain, x-1, 1) && lockedHelper(terrain, x+1, 1);
        }else if(x == tHeight-1 && y > 0 && y < tWidth-1){
            return lockedHelper(terrain, x, y+1) && lockedHelper(terrain, x, y-1) && lockedHelper(terrain, x-1, y) && lockedHelper(terrain, x-1, y-1) && lockedHelper(terrain, x-1, y+1);
        }else if(y == tWidth-1 && x > 0 && x < tHeight-1){
            return lockedHelper(terrain, x+1, y) && lockedHelper(terrain, x-1, y) && lockedHelper(terrain, x, y-1) && lockedHelper(terrain, x-1, y-1) && lockedHelper(terrain, x+1, y-1);
        }
        
        return false;
    }    
    
    
    public boolean lockedHelper(Terrain terrain, int x, int y){
        ArrayList<Specimen> tmp = terrain.objectMap[x][y];
        
        if( tmp.size() >= 2 || (tmp.size() == 1 && tmp.get(0) instanceof Animal && !this.getEnergySources().contains(tmp.get(0).getName())) )
            return true;
        
        return false;
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
            this.action = false;
        }
    }
    
    

}
