

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;

/**
 * @author  Zura Mestiashvili
 * @version v1.0.0
 */

public class AnimalTest{
    
    Animal animal;
    Plant plant;
    Plant strangePlant;
    DirectionGenerator dGen;
    Terrain terrain;
    ArrayList<Specimen> habitants;
    ArrayList<Specimen> children;
    
    
    public AnimalTest()
    {
    }

    @Before
    public void setUp()
    {
        ArrayList<String> energySources = new ArrayList<String>();
        energySources.add("wheat");
        energySources.add("banana");
        energySources.add("chicken");
        double energy = 20.0;
        ArrayList<Double> stats = new ArrayList<Double>();
        stats.add(10.1);
        stats.add(5.1);
        Controller controller = new Controller("cow");
        animal = new Animal("cow", "herbivore", 'c', energySources, energy, stats, 30, 50, 20, 1, 1, controller);
        plant = new Plant("wheat", "vegetable", 'h', energySources, energy, stats, 30, 50, 20, 1, 1, controller);
        strangePlant = new Plant("grapes", "fruit", 'g', energySources, energy, stats, 30, 50, 20, 0, 0, controller);
        
        dGen = new DirectionGenerator(11);
        terrain = new Terrain(5, 15, 10);
        habitants = new ArrayList<Specimen>();
        children = new ArrayList<Specimen>();
    } 

   
    @After
    public void tearDown()
    {
    }
    
    /**
     * @desc check that eat() works correctly. 
     */
    @Test
    public void testEat(){
        ArrayList<Specimen> habitants = new ArrayList<Specimen>();
        Terrain terrain = new Terrain(5, 5, 10);
        
        terrain.objectMap.get(1).get(1).add(plant);
        terrain.objectMap.get(1).get(1).add(animal);
        
        animal.eat(terrain, habitants);
        assertTrue("Animal could not eat", animal.getEnergy() == 40);
        
        plant.setEnergy(20);
        animal.eat(terrain, habitants);
        assertTrue("Animal could not eat or problem with max energy", animal.getEnergy() == 50);
        
        terrain.objectMap.get(1).get(1).remove(plant);
        animal.setEnergy(10);
        strangePlant.setX(1);
        strangePlant.setY(1);
        terrain.objectMap.get(1).get(1).add(strangePlant);
        
        animal.eat(terrain, habitants);
        assertTrue("Animal could not eat or problem with max energy", animal.getEnergy() == 10);
        
        animal.setEnergy(0);
        animal.eat(terrain, habitants);
        assertTrue("Animal should dies as its energy is 0, it can not eat anymore", animal.getEnergy() == 0);
    }
    
    /**
     * @desc check that die() works correctly. 
     */
    @Test
    public void testDie(){
        ArrayList<Specimen> habitants = new ArrayList<Specimen>();
        Terrain terrain = new Terrain(5, 5, 10);
        animal.die(terrain, habitants);
        assertTrue("", animal.getEnergy() == 0);
    }
   
    /**
     * @desc check that locked() works correctly. 
     */
    @Test
    public void testLocked(){
        for(int i = 0; i < terrain.getHeight(); i++){
            for(int j = 0; j < terrain.getWidth(); j++){
                terrain.objectMap.get(i).get(j).add(bulkDog(i, j));
            }
        }
        
        // use helper methods that test for each side and corner of board individually
        testUpLeftCorner();
        testUpRightCorner();
        testDownLeftCorner();
        testDownRightCorner();
        testLeftSide();
        testUpSide();
        testDownSide();
        testRightSide();
        testLeftSide();        
    }
    
    /**
     * @desc check that moveHelper() works correctly. 
     */
    @Test
    public void testMoveHelper(){
        
        terrain.objectMap.get(1).get(1).add(animal);
        
        terrain.objectMap.get(0).get(0).add(bulkDog(0, 0));
        terrain.objectMap.get(0).get(1).add(bulkDog(0, 1));
        terrain.objectMap.get(0).get(2).add(bulkDog(0, 2));
        terrain.objectMap.get(1).get(0).add(bulkDog(1, 0));
        terrain.objectMap.get(1).get(2).add(bulkDog(1, 2));
        terrain.objectMap.get(2).get(0).add(bulkDog(2, 0));
        terrain.objectMap.get(2).get(1).add(bulkDog(2, 1));
        terrain.objectMap.get(2).get(2).add(bulkDog(2, 2));
        
        animal.moveHelper(dGen, terrain, habitants, 0, 0);
        assertTrue("Animal moved when it should not have moved", animal.getX() == 1 && animal.getY() == 1);
        
        terrain.objectMap.get(0).get(0).clear();
        animal.moveHelper(dGen, terrain, habitants, 2, 0);
        assertTrue("Animal did not move when it should have moved", animal.getX() == 0 && animal.getY() == 0);
        
        animal.moveHelper(dGen, terrain, habitants, 1, 1);
        assertTrue("Animal did not move when it should have moved", animal.getX() == 1 && animal.getY() == 1);
        assertTrue("Animal did not move when it should have moved", terrain.objectMap.get(0).get(0).size() == 0);
        
    }
    
    /**
     * @desc check that lockedHelper() works correctly. 
     */
    @Test
    public void testLockedHelper(){
        terrain.objectMap.get(0).get(0).add(bulkDog(0, 0));
        
        assertTrue("Should be true as animal can not move to [0, 0] position", animal.lockedHelper(terrain, 0, 0) == true);
        
        terrain.objectMap.get(0).get(1).add(bulkDog(0, 1));
        terrain.objectMap.get(0).get(1).add(bulkBanana(0, 1));
        assertTrue("Should be true as animal can not move to [0, 1] position", animal.lockedHelper(terrain, 0, 1) == true);
        
        terrain.objectMap.get(0).get(2).add(bulkBanana(0, 2));
        assertTrue("Should be false as animal can move to [0, 2], where only plant lives", animal.lockedHelper(terrain, 0, 2) == false);
        
        terrain.objectMap.get(1).get(0).add(bulkChicken(1, 0));
        assertTrue("Should be false as animal can move to [1, 0], where only chicken lives", animal.lockedHelper(terrain, 1, 0) == false);
        
    }
    
    /**
     * @desc method that provides bulk animals, in that case dogs
     */
    public Animal bulkDog(int x, int y){
        ArrayList<String> energySources = new ArrayList<String>();
        energySources.add("bones");
        ArrayList<Double> stats = new ArrayList<Double>();
        stats.add(10.1);
        stats.add(5.1);
        Controller controller = new Controller("dog");
        return new Animal("dog", "herbivore", 'd', energySources, 20.0, stats, 30, 50, 20, x, y, controller);
    }
    
    /**
     * @desc method that provides bulk specimen - chicken.
     */
    public Animal bulkChicken(int x, int y){
        ArrayList<String> energySources = new ArrayList<String>();
        energySources.add("bones");
        ArrayList<Double> stats = new ArrayList<Double>();
        stats.add(10.1);
        stats.add(5.1);
        Controller controller = new Controller("chicken");
        return new Animal("chicken", "herbivore", 'd', energySources, 20.0, stats, 30, 50, 20, x, y, controller);
    }
    
    /**
     * @desc method that provides bulk bananas
     */
    public Plant bulkBanana(int x, int y){
        ArrayList<String> energySources = new ArrayList<String>();
        energySources.add("bones");
        ArrayList<Double> stats = new ArrayList<Double>();
        stats.add(10.1);
        stats.add(5.1);
        Controller controller = new Controller("banana");
        return new Plant("banana", "fruit", 'b', energySources, 20.0, stats, 30, 50, 20, x, y, controller);
    }
    
    
    public void testUpSide(){
        animal.setX(0);
        animal.setY(1);
        terrain.objectMap.get(0).get(1).set(0, animal);
        assertTrue("Returned not Locked while specimen is locked", animal.locked(terrain) == true);
        
        terrain.objectMap.get(0).get(0).clear();
        assertTrue("Could not recognize UP_LEFT position and returned locked", animal.locked(terrain) == false);
        terrain.objectMap.get(0).get(0).add(bulkDog(0, 0));
        
        terrain.objectMap.get(1).get(0).clear();
        assertTrue("Could not recognize UP position and returned locked", animal.locked(terrain) == false);
        terrain.objectMap.get(1).get(0).add(bulkDog(1, 0));
        
        terrain.objectMap.get(1).get(1).clear();
        assertTrue("Could not recognize UP_RIGHT position and returned locked", animal.locked(terrain) == false);
        terrain.objectMap.get(1).get(1).add(bulkDog(1, 1));
        
        terrain.objectMap.get(1).get(2).clear();
        assertTrue("Could not recognize UP_RIGHT position and returned locked", animal.locked(terrain) == false);
        terrain.objectMap.get(1).get(2).add(bulkDog(1, 2));
        
        terrain.objectMap.get(0).get(2).clear();
        assertTrue("Could not recognize UP_RIGHT position and returned locked", animal.locked(terrain) == false);
        terrain.objectMap.get(0).get(2).add(bulkDog(0, 2));
        
        terrain.objectMap.get(0).get(1).set(0, bulkDog(0, 1));
    }
    
    public void testDownSide(){
        animal.setX(terrain.getHeight() - 1);
        animal.setY(1);
        terrain.objectMap.get(terrain.getHeight() - 1).get(1).set(0, animal);
        assertTrue("Returned not Locked while specimen is locked", animal.locked(terrain) == true);
        
        terrain.objectMap.get(terrain.getHeight() - 1).get(0).clear();
        assertTrue("Could not recognize UP_LEFT position and returned locked", animal.locked(terrain) == false);
        terrain.objectMap.get(terrain.getHeight() - 1).get(0).add(bulkDog(terrain.getHeight() - 1, 0));
        
        terrain.objectMap.get(terrain.getHeight() - 1).get(2).clear();
        assertTrue("Could not recognize UP position and returned locked", animal.locked(terrain) == false);
        terrain.objectMap.get(terrain.getHeight() - 1).get(2).add(bulkDog(terrain.getHeight() - 1, 2));
        
        terrain.objectMap.get(terrain.getHeight() - 2).get(1).clear();
        assertTrue("Could not recognize UP_RIGHT position and returned locked", animal.locked(terrain) == false);
        terrain.objectMap.get(terrain.getHeight() - 2).get(1).add(bulkDog(terrain.getHeight() - 2, 1));
        
        terrain.objectMap.get(terrain.getHeight() - 2).get(2).clear();
        assertTrue("Could not recognize UP_RIGHT position and returned locked", animal.locked(terrain) == false);
        terrain.objectMap.get(terrain.getHeight() - 2).get(2).add(bulkDog(terrain.getHeight() - 2, 2));
        
        terrain.objectMap.get(terrain.getHeight() - 2).get(0).clear();
        assertTrue("Could not recognize UP_RIGHT position and returned locked", animal.locked(terrain) == false);
        terrain.objectMap.get(terrain.getHeight() - 2).get(0).add(bulkDog(terrain.getHeight() - 2, 0));
        
        terrain.objectMap.get(terrain.getHeight() - 1).get(1).set(0, bulkDog(terrain.getHeight() - 1, 1));
    }
    
    public void testLeftSide(){
        animal.setX(1);
        animal.setY(0);
        terrain.objectMap.get(1).get(0).set(0, animal);
        assertTrue("Returned not Locked while specimen is locked", animal.locked(terrain) == true);
        
        terrain.objectMap.get(0).get(0).clear();
        assertTrue("Could not recognize UP_LEFT position and returned locked", animal.locked(terrain) == false);
        terrain.objectMap.get(0).get(0).add(bulkDog(0, 0));
       
        terrain.objectMap.get(0).get(1).clear();
        assertTrue("Could not recognize UP position and returned locked", animal.locked(terrain) == false);
        terrain.objectMap.get(0).get(1).add(bulkDog(0, 2));
        
        terrain.objectMap.get(1).get(1).clear();
        assertTrue("Could not recognize UP_RIGHT position and returned locked", animal.locked(terrain) == false);
        terrain.objectMap.get(1).get(1).add(bulkDog(1, 1));
        
        terrain.objectMap.get(2).get(1).clear();
        assertTrue("Could not recognize UP_RIGHT position and returned locked", animal.locked(terrain) == false);
        terrain.objectMap.get(2).get(1).add(bulkDog(2, 1));
        
        terrain.objectMap.get(2).get(0).clear();
        assertTrue("Could not recognize UP_RIGHT position and returned locked", animal.locked(terrain) == false);
        terrain.objectMap.get(2).get(0).add(bulkDog(2, 0));
        
        terrain.objectMap.get(1).get(0).set(0, bulkDog(1, 0));
    }
    
    public void testRightSide(){
        animal.setX(1);
        animal.setY(terrain.getWidth() - 1);
        terrain.objectMap.get(1).get(terrain.getWidth() - 1).set(0, animal);
        assertTrue("Returned not Locked while specimen is locked", animal.locked(terrain) == true);
        
        terrain.objectMap.get(0).get(terrain.getWidth() - 1).clear();
        assertTrue("Could not recognize UP_LEFT position and returned locked", animal.locked(terrain) == false);
        terrain.objectMap.get(0).get(terrain.getWidth() - 1).add(bulkDog(0, terrain.getWidth() - 1));
        
        terrain.objectMap.get(2).get(terrain.getWidth() - 1).clear();
        assertTrue("Could not recognize UP position and returned locked", animal.locked(terrain) == false);
        terrain.objectMap.get(2).get(terrain.getWidth() - 1).add(bulkDog(2, terrain.getWidth() - 1));
        
        terrain.objectMap.get(1).get(terrain.getWidth() - 2).clear();
        assertTrue("Could not recognize UP_RIGHT position and returned locked", animal.locked(terrain) == false);
        terrain.objectMap.get(1).get(terrain.getWidth() - 2).add(bulkDog(1, terrain.getWidth() - 2));
        
        terrain.objectMap.get(2).get(terrain.getWidth() - 2).clear();
        assertTrue("Could not recognize UP_RIGHT position and returned locked", animal.locked(terrain) == false);
        terrain.objectMap.get(2).get(terrain.getWidth() - 2).add(bulkDog(2, terrain.getWidth() - 2));
        
        terrain.objectMap.get(0).get(terrain.getWidth() - 2).clear();
        assertTrue("Could not recognize UP_RIGHT position and returned locked", animal.locked(terrain) == false);
        terrain.objectMap.get(0).get(terrain.getWidth() - 2).add(bulkDog(0, terrain.getWidth() - 2));
        
        terrain.objectMap.get(1).get(terrain.getWidth() - 1).set(0, bulkDog(1, terrain.getWidth() - 1));
    }
    
    public void testUpLeftCorner(){
        animal.setX(0);
        animal.setY(0);
        terrain.objectMap.get(0).get(0).set(0, animal);
        assertTrue("Returned not Locked while specimen is locked", animal.locked(terrain) == true);
        
        terrain.objectMap.get(0).get(1).clear();
        assertTrue("Could not recognize UP_LEFT position and returned locked", animal.locked(terrain) == false);
        terrain.objectMap.get(0).get(1).add(bulkDog(0, 1));
        
        terrain.objectMap.get(1).get(1).clear();
        assertTrue("Could not recognize UP position and returned locked", animal.locked(terrain) == false);
        terrain.objectMap.get(1).get(1).add(bulkDog(1, 1));
        
        terrain.objectMap.get(1).get(0).clear();
        assertTrue("Could not recognize UP_RIGHT position and returned locked", animal.locked(terrain) == false);
        terrain.objectMap.get(1).get(0).add(bulkDog(1, 0));
        
        terrain.objectMap.get(0).get(0).set(0, bulkDog(0, 0));
    }
    
    public void testUpRightCorner(){
        animal.setX(0);
        animal.setY(terrain.getWidth() - 1);
        terrain.objectMap.get(0).get(terrain.getWidth() - 1).set(0, animal);
        assertTrue("Returned not Locked while specimen is locked", animal.locked(terrain) == true);
        
        terrain.objectMap.get(0).get(terrain.getWidth() - 2).clear();
        assertTrue("Could not recognize UP_LEFT position and returned locked", animal.locked(terrain) == false);
        terrain.objectMap.get(0).get(terrain.getWidth() - 2).add(bulkDog(0, terrain.getWidth() - 2));
        
        terrain.objectMap.get(1).get(terrain.getWidth() - 1).clear();
        assertTrue("Could not recognize UP position and returned locked", animal.locked(terrain) == false);
        terrain.objectMap.get(1).get(terrain.getWidth() - 1).add(bulkDog(1, terrain.getWidth() - 1));
        
        terrain.objectMap.get(1).get(terrain.getWidth() - 2).clear();
        assertTrue("Could not recognize UP_RIGHT position and returned locked", animal.locked(terrain) == false);
        terrain.objectMap.get(1).get(terrain.getWidth() - 2).add(bulkDog(1, terrain.getWidth() - 2));
        
        terrain.objectMap.get(0).get(terrain.getWidth() - 1).set(0, bulkDog(0, terrain.getWidth() - 1));
    }
    
    public void testDownLeftCorner(){
        animal.setX(terrain.getHeight() - 1);
        animal.setY(0);
        terrain.objectMap.get(terrain.getHeight() - 1).get(0).set(0, animal);
        assertTrue("Returned not Locked while specimen is locked", animal.locked(terrain) == true);
        
        terrain.objectMap.get(terrain.getHeight() - 1).get(1).clear();
        assertTrue("Could not recognize UP_LEFT position and returned locked", animal.locked(terrain) == false);
        terrain.objectMap.get(terrain.getHeight() - 1).get(1).add(bulkDog(terrain.getHeight() - 1, 1));
        
        terrain.objectMap.get(terrain.getHeight() - 2).get(0).clear();
        assertTrue("Could not recognize UP position and returned locked", animal.locked(terrain) == false);
        terrain.objectMap.get(terrain.getHeight() - 2).get(0).add(bulkDog(terrain.getHeight() - 2, 0));
        
        terrain.objectMap.get(terrain.getHeight() - 2).get(1).clear();
        assertTrue("Could not recognize UP_RIGHT position and returned locked", animal.locked(terrain) == false);
        terrain.objectMap.get(terrain.getHeight() - 2).get(1).add(bulkDog(terrain.getHeight() - 2, 1));
        
        terrain.objectMap.get(terrain.getHeight() - 1).get(0).set(0, bulkDog(terrain.getHeight() - 1, 0));
    }
    
    public void testDownRightCorner(){
        animal.setX(terrain.getHeight() - 1);
        animal.setY(terrain.getWidth() - 1);
        terrain.objectMap.get(terrain.getHeight() - 1).get(terrain.getWidth() - 1).set(0, animal);
        assertTrue("Returned not Locked while specimen is locked", animal.locked(terrain) == true);
        
        terrain.objectMap.get(terrain.getHeight() - 2).get(terrain.getWidth() - 2).clear();
        assertTrue("Could not recognize UP_LEFT position and returned locked", animal.locked(terrain) == false);
        terrain.objectMap.get(terrain.getHeight() - 2).get(terrain.getWidth() - 2).add(bulkDog(terrain.getHeight() - 2, terrain.getWidth() - 2));
        
        terrain.objectMap.get(terrain.getHeight() - 2).get(terrain.getWidth() - 1).clear();
        assertTrue("Could not recognize UP position and returned locked", animal.locked(terrain) == false);
        terrain.objectMap.get(terrain.getHeight() - 2).get(terrain.getWidth() - 1).add(bulkDog(terrain.getHeight() - 2, terrain.getWidth() - 1));
        
        terrain.objectMap.get(terrain.getHeight() - 1).get(terrain.getWidth() - 2).clear();
        assertTrue("Could not recognize UP_RIGHT position and returned locked", animal.locked(terrain) == false);
        terrain.objectMap.get(terrain.getHeight() - 1).get(terrain.getWidth() - 2).add(bulkDog(terrain.getHeight() - 1, terrain.getWidth() - 2));
        
        terrain.objectMap.get(terrain.getHeight() - 1).get(terrain.getWidth() - 1).set(0, bulkDog(terrain.getHeight() - 1, terrain.getWidth() - 1));
    }
    
    public void testCenter(){
        terrain.objectMap.get(1).get(1).set(0, animal);
        assertTrue("Returned not Locked while specimen is locked", animal.locked(terrain) == true);
        
        terrain.objectMap.get(0).get(0).clear();
        assertTrue("Could not recognize UP_LEFT position and returned locked", animal.locked(terrain) == false);
        terrain.objectMap.get(0).get(0).add(bulkDog(0, 0));
        
        terrain.objectMap.get(0).get(1).clear();
        assertTrue("Could not recognize UP position and returned locked", animal.locked(terrain) == false);
        terrain.objectMap.get(0).get(1).add(bulkDog(0, 1));
        
        terrain.objectMap.get(0).get(2).clear();
        assertTrue("Could not recognize UP_RIGHT position and returned locked", animal.locked(terrain) == false);
        terrain.objectMap.get(0).get(2).add(bulkDog(0, 2));
        
        terrain.objectMap.get(1).get(0).clear();
        assertTrue("Could not recognize LEFT position and returned locked", animal.locked(terrain) == false);
        terrain.objectMap.get(1).get(0).add(bulkDog(1, 0));
        
        terrain.objectMap.get(1).get(2).clear();
        assertTrue("Could not recognize RIGHT position and returned locked", animal.locked(terrain) == false);
        terrain.objectMap.get(1).get(2).add(bulkDog(1, 2));
        
        terrain.objectMap.get(2).get(0).clear();
        assertTrue("Could not recognize DOWN_LEFT position and returned locked", animal.locked(terrain) == false);
        terrain.objectMap.get(2).get(0).add(bulkDog(2, 0));
        
        terrain.objectMap.get(2).get(1).clear();
        assertTrue("Could not recognize DOWN position and returned locked", animal.locked(terrain) == false);
        terrain.objectMap.get(2).get(1).add(bulkDog(2, 1));
        
        terrain.objectMap.get(2).get(2).clear();
        assertTrue("Could not recognize DOWN_RIGHT position and returned locked", animal.locked(terrain) == false);
        terrain.objectMap.get(2).get(2).add(bulkDog(2, 2));
        
        terrain.objectMap.get(1).get(1).set(0, bulkDog(1, 1));
    }
    
    
    @Test
    public void testGetName(){
        assertTrue("Could not get name correctly", animal.getName().compareTo("cow") == 0);
    }
    
    @Test
    public void testGetType(){
        assertTrue("Could not get type correctly", animal.getType().compareTo("herbivore") == 0);
    }
    
    @Test
    public void testGetSymbol(){
        assertTrue("Could not get symbol correctly", animal.getSymbol() == 'c');
    }
    
    @Test
    public void testGetEnergySources(){
        ArrayList<String> tmp = animal.getEnergySources();
        assertTrue("Could not get energy sources correctly", tmp.get(0).compareTo("wheat") == 0 && tmp.get(1).compareTo("banana") == 0);
    }
    
    @Test
    public void testGetStats(){
        ArrayList<Double> tmp = animal.getStats();
        assertTrue("Could not get stats correctly", tmp.get(0) == 10.1);
        assertTrue("Could not get stats correctly", tmp.get(1) == 5.1);
    }
    
    @Test
    public void testGetEnergy(){
        assertTrue("Could not get energy correctly", animal.getEnergy() == 20);
    }
    
    @Test
    public void testGetBirthEnergy(){
        assertTrue("Could not get birth energy correctly", animal.getBirthEnergy() == 30);
    }
    
    @Test
    public void testGetMaxEnergy(){
        assertTrue("Could not get max energy correctly", animal.getMaxEnergy() == 50);
    }
    
    @Test
    public void testGetLivingEnergy(){
        assertTrue("Could not get living energy correctly", animal.getLivingEnergy() == 20);
    }
    
    @Test
    public void testGetX(){
        assertTrue("Could not get x coordinate correctly", animal.getX() == 1);
    }
    
    @Test
    public void testGetY(){
        assertTrue("Could not get y coordinate correctly", animal.getY() == 1);
    }
    
    @Test
    public void testSetX(){
        animal.setX(10);
        assertTrue("Could not set x coordinate correctly", animal.getX() == 10);
        
        animal.setX(11);
        assertTrue("Could not set x coordinate correctly", animal.getX() == 11);
    }
    
    @Test
    public void testSetY(){
        animal.setY(10);
        assertTrue("Could not set y coordinate correctly", animal.getY() == 10);
        
        animal.setY(11);
        assertTrue("Could not set y coordinate correctly", animal.getY() == 11);
    }
    
    @Test
    public void testSetEnergy(){
        animal.setEnergy(30);
        assertTrue("Could not set energy correctly", animal.getEnergy() == 30);
        
        animal.setEnergy(50);
        assertTrue("Could not set energy correctly", animal.getEnergy() == 50);
    }

    
    @Test
    public void testSetAge(){
        animal.setAge(10);
        assertTrue("Could not set age correctly", animal.getAge() == 10);
    }
}
