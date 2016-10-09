import java.util.ArrayList;
/**
 * Zura Mestiashvili
 * v1.0.0
 */
public interface Movable{
    
    void move(DirectionGenerator dGen, Terrain terrain, ArrayList<Specimen> habitants);
    
}
