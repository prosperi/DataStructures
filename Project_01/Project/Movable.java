import java.util.ArrayList;
/** 
  * @desc this interface is designed for each object that can move.
  * In our case Animals are the ones that have to implement Movable interface, 
  * as plants cannot move
  * @author Zura Mestiashvili mestiasz@lafayette.edu
  * @version v1.0.0
*/
public interface Movable{
    
    void move(DirectionGenerator dGen, Terrain terrain, ArrayList<Specimen> habitants);
    
}
