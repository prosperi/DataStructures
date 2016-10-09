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
                    setY(getY()-1);
                }
                break;
            case RIGHT:
                if(getY()  == 14){
                    move(dGen, terrain, habitants);
                }else{
                   setY(getY()+1);
                }
                break;
            case UP:
                if(getX() == 0){
                    move(dGen, terrain, habitants);
                }else{
                    setX(getX()-1);
                }
                break;
            case DOWN:
                if(getX() == 14){
                    move(dGen, terrain, habitants);
                }else{
                    setX(getX()+1);
                }
                break;
        }
        
        if(terrain.objectMap[getX()][getY()].size() != 0){
            eat(terrain, habitants);
        }
        
    }
   
    public void eat(Terrain terrain, ArrayList<Specimen> habitants){
        ArrayList<Specimen> tmp = terrain.objectMap[getX()][getY()];
        for(int i = 0; i < tmp.size(); i++){
            Specimen tmpHabitant = tmp.get(i);
            if(tmpHabitant.getEnergySources().contains(getName())){
                System.out.println("Fuck, I'm dying, " + tmpHabitant.getName() + " is eating me - " + getName());
                die(terrain, habitants);
                break;
            }
            
            if(this.getEnergySources().contains(tmpHabitant.getName())){
                if(tmpHabitant instanceof Plant) ((Plant)tmpHabitant).die(terrain, habitants);
                else ((Animal)tmpHabitant).die(terrain, habitants);
                System.out.println("Let's eat");  
            }
        }


    }
    public void giveBirth(){}
    public void die(Terrain terrain, ArrayList<Specimen> habitants){
        habitants.remove(this);
        System.out.println("Currently we have " + habitants.size() + " habitants");
    }
}
