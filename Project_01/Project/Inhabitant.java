import java.util.ArrayList;
/**
 * Zura Mestiashvili
 * v1.0.0
 */
public interface Inhabitant{
    
    void die(Terrain terrain, ArrayList<Specimen> habitants);
    void giveBirth(DirectionGenerator dGen, Terrain terrain, ArrayList<Specimen> habitants, ArrayList<Specimen> children);
    void eat(Terrain terrain, ArrayList<Specimen> habitants);
    
}
