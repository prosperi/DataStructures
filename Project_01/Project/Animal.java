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
    
    public void move(DirectionGenerator dGen){
        
        switch(dGen.next()){
            case LEFT:
                if(getY() == 0 ){
                    move(dGen);
                }else{
                    setY(getY()-1);
                }
                break;
            case RIGHT:
                if(getY()  == 14){
                    move(dGen);
                }else{
                   setY(getY()+1);
                }
                break;
            case UP:
                if(getX() == 0){
                    move(dGen);
                }else{
                    setX(getX()-1);
                }
                break;
            case DOWN:
                if(getX() == 14){
                    move(dGen);
                }else{
                    setX(getX()+1);
                }
                break;
        }
        
        
    }
   
    public void eat(){}
    public void giveBirth(){}
    public void die(){}
}
