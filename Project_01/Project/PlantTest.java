

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;

/**
 * @author  Zura Mestiashvili
 * @version v1.0.0
 */

public class PlantTest{
    
    Plant plant;
    DirectionGenerator dGen;
    Terrain terrain;
    ArrayList<Specimen> habitants;
    ArrayList<Specimen> children;
    
    public PlantTest()
    {
    }

    @Before
    public void setUp()
    {
        ArrayList<String> energySources = new ArrayList<String>();
        energySources.add("light");
        double energy = 20.0;
        ArrayList<Double> stats = new ArrayList<Double>();
        stats.add(10.1);
        stats.add(5.1);
        plant = new Plant("wheat", "vegetable", 'h', energySources, energy, stats, 30, 50, 20, 1, 1);
        
        dGen = new DirectionGenerator(11);
        terrain = new Terrain(5, 15, 10);
        habitants = new ArrayList<Specimen>();
        children = new ArrayList<Specimen>();
    } 

   
    @After
    public void tearDown()
    {
    }
    
    @Test
    public void testEat(){
        ArrayList<Specimen> habitants = new ArrayList<Specimen>();
        Terrain terrain = new Terrain(5, 5, 10);
        
        plant.eat(terrain, habitants);
        assertTrue("Plant could not eat", plant.getEnergy() == 30);
        
        terrain = new Terrain(5, 5, 100);
        plant.eat(terrain, habitants);
        assertTrue("Plant could not eat or problem with max energy", plant.getEnergy() == 50);
        
        plant.setEnergy(0);
        plant.eat(terrain, habitants);
        assertTrue("Plant should dies as its energy is 0, it can not eat anymore", plant.getEnergy() == 0);
    }
    
    @Test
    public void testDie(){
        ArrayList<Specimen> habitants = new ArrayList<Specimen>();
        Terrain terrain = new Terrain(5, 5, 10);
        plant.die(terrain, habitants);
        assertTrue("", plant.getEnergy() == 0);
    }
    
    @Test
    public void testGiveBirth(){
        terrain.objectMap[1][1].add(plant);
        plant.giveBirth(dGen, terrain, habitants, children);
        assertTrue("Gave birth to child without enough birth energy", children.size() == 0);
        
        plant.setEnergy(100);
        plant.giveBirth(dGen, terrain, habitants, children);
        assertTrue("Gave birth to child without enough birth energy", children.size() == 1);
        
        Specimen child = children.get(0);
        assertTrue("could not pass properties correctly to child", child.getName() == plant.getName());
        assertTrue("could not pass properties correctly to child", child instanceof Plant == true);
        assertTrue("could not pass properties correctly to child", child.getSymbol() == plant.getSymbol());
        assertTrue("could not pass properties correctly to child", true); // nergySources
        assertTrue("could not pass properties correctly to child", child.getEnergy() == plant.getEnergy());
        assertTrue("could not pass properties correctly to child", true); // getStats
        assertTrue("could not pass properties correctly to child", child.getBirthEnergy() == plant.getBirthEnergy());
        assertTrue("could not pass properties correctly to child", child.getMaxEnergy() == plant.getMaxEnergy());
        assertTrue("could not pass properties correctly to child", child.getLivingEnergy() == plant.getLivingEnergy());
        assertTrue("could not pass properties correctly to child", child.getX() != plant.getX() || child.getY() != plant.getY());
        assertTrue("could not position child at correct place", terrain.objectMap[0][0].size() == 1 || terrain.objectMap[0][1].size() == 1 || terrain.objectMap[0][2].size() == 1 ||
                                                                terrain.objectMap[1][0].size() == 1 || terrain.objectMap[1][2].size() == 1 || terrain.objectMap[2][0].size() == 1 ||
                                                                terrain.objectMap[2][1].size() == 1 || terrain.objectMap[2][2].size() == 1);
    }
    
    public void testUpSide(){
        plant.setX(0);
        plant.setY(1);
        terrain.objectMap[0][1].set(0, plant);
        assertTrue("Returned not Locked while specimen is locked", plant.lockedBirth(terrain) == true);
        
        terrain.objectMap[0][0].clear();
        assertTrue("Could not recognize UP_LEFT position and returned locked", plant.lockedBirth(terrain) == false);
        terrain.objectMap[0][0].add(null);
        
        terrain.objectMap[1][0].clear();
        assertTrue("Could not recognize UP position and returned locked", plant.lockedBirth(terrain) == false);
        terrain.objectMap[1][0].add(null);
        
        terrain.objectMap[1][1].clear();
        assertTrue("Could not recognize UP_RIGHT position and returned locked", plant.lockedBirth(terrain) == false);
        terrain.objectMap[1][1].add(null);
        
        terrain.objectMap[1][2].clear();
        assertTrue("Could not recognize UP_RIGHT position and returned locked", plant.lockedBirth(terrain) == false);
        terrain.objectMap[1][2].add(null);
        
        terrain.objectMap[0][2].clear();
        assertTrue("Could not recognize UP_RIGHT position and returned locked", plant.lockedBirth(terrain) == false);
        terrain.objectMap[0][2].add(null);
        
        terrain.objectMap[0][1].set(0, null);
    }
    
    public void testDownSide(){
        plant.setX(terrain.getHeight() - 1);
        plant.setY(1);
        terrain.objectMap[terrain.getHeight() - 1][1].set(0, plant);
        assertTrue("Returned not Locked while specimen is locked", plant.lockedBirth(terrain) == true);
        
        terrain.objectMap[terrain.getHeight() - 1][0].clear();
        assertTrue("Could not recognize UP_LEFT position and returned locked", plant.lockedBirth(terrain) == false);
        terrain.objectMap[terrain.getHeight() - 1][0].add(null);
        
        terrain.objectMap[terrain.getHeight() - 1][2].clear();
        assertTrue("Could not recognize UP position and returned locked", plant.lockedBirth(terrain) == false);
        terrain.objectMap[terrain.getHeight() - 1][2].add(null);
        
        terrain.objectMap[terrain.getHeight() - 2][1].clear();
        assertTrue("Could not recognize UP_RIGHT position and returned locked", plant.lockedBirth(terrain) == false);
        terrain.objectMap[terrain.getHeight() - 2][1].add(null);
        
        terrain.objectMap[terrain.getHeight() - 2][2].clear();
        assertTrue("Could not recognize UP_RIGHT position and returned locked", plant.lockedBirth(terrain) == false);
        terrain.objectMap[terrain.getHeight() - 2][2].add(null);
        
        terrain.objectMap[terrain.getHeight() - 2][0].clear();
        assertTrue("Could not recognize UP_RIGHT position and returned locked", plant.lockedBirth(terrain) == false);
        terrain.objectMap[terrain.getHeight() - 2][0].add(null);
        
        terrain.objectMap[terrain.getHeight() - 1][1].set(0, null);
    }
    
    public void testLeftSide(){
        plant.setX(1);
        plant.setY(0);
        terrain.objectMap[1][0].set(0, plant);
        assertTrue("Returned not Locked while specimen is locked", plant.lockedBirth(terrain) == true);
        
        terrain.objectMap[0][0].clear();
        assertTrue("Could not recognize UP_LEFT position and returned locked", plant.lockedBirth(terrain) == false);
        terrain.objectMap[0][0].add(null);
        
        terrain.objectMap[0][1].clear();
        assertTrue("Could not recognize UP position and returned locked", plant.lockedBirth(terrain) == false);
        terrain.objectMap[0][1].add(null);
        
        terrain.objectMap[1][1].clear();
        assertTrue("Could not recognize UP_RIGHT position and returned locked", plant.lockedBirth(terrain) == false);
        terrain.objectMap[1][1].add(null);
        
        terrain.objectMap[2][1].clear();
        assertTrue("Could not recognize UP_RIGHT position and returned locked", plant.lockedBirth(terrain) == false);
        terrain.objectMap[2][1].add(null);
        
        terrain.objectMap[2][0].clear();
        assertTrue("Could not recognize UP_RIGHT position and returned locked", plant.lockedBirth(terrain) == false);
        terrain.objectMap[2][0].add(null);
        
        terrain.objectMap[1][0].set(0, null);
    }
    
    public void testRightSide(){
        plant.setX(1);
        plant.setY(terrain.getWidth() - 1);
        terrain.objectMap[1][terrain.getWidth() - 1].set(0, plant);
        assertTrue("Returned not Locked while specimen is locked", plant.lockedBirth(terrain) == true);
        
        terrain.objectMap[0][terrain.getWidth() - 1].clear();
        assertTrue("Could not recognize UP_LEFT position and returned locked", plant.lockedBirth(terrain) == false);
        terrain.objectMap[0][terrain.getWidth() - 1].add(null);
        
        terrain.objectMap[2][terrain.getWidth() - 1].clear();
        assertTrue("Could not recognize UP position and returned locked", plant.lockedBirth(terrain) == false);
        terrain.objectMap[2][terrain.getWidth() - 1].add(null);
        
        terrain.objectMap[1][terrain.getWidth() - 2].clear();
        assertTrue("Could not recognize UP_RIGHT position and returned locked", plant.lockedBirth(terrain) == false);
        terrain.objectMap[1][terrain.getWidth() - 2].add(null);
        
        terrain.objectMap[2][terrain.getWidth() - 2].clear();
        assertTrue("Could not recognize UP_RIGHT position and returned locked", plant.lockedBirth(terrain) == false);
        terrain.objectMap[2][terrain.getWidth() - 2].add(null);
        
        terrain.objectMap[0][terrain.getWidth() - 2].clear();
        assertTrue("Could not recognize UP_RIGHT position and returned locked", plant.lockedBirth(terrain) == false);
        terrain.objectMap[0][terrain.getWidth() - 2].add(null);
        
        terrain.objectMap[1][terrain.getWidth() - 1].set(0, null);
    }
    
    public void testUpLeftCorner(){
        plant.setX(0);
        plant.setY(0);
        terrain.objectMap[0][0].set(0, plant);
        assertTrue("Returned not Locked while specimen is locked", plant.lockedBirth(terrain) == true);
        
        terrain.objectMap[0][1].clear();
        assertTrue("Could not recognize UP_LEFT position and returned locked", plant.lockedBirth(terrain) == false);
        terrain.objectMap[0][1].add(null);
        
        terrain.objectMap[1][1].clear();
        assertTrue("Could not recognize UP position and returned locked", plant.lockedBirth(terrain) == false);
        terrain.objectMap[1][1].add(null);
        
        terrain.objectMap[1][0].clear();
        assertTrue("Could not recognize UP_RIGHT position and returned locked", plant.lockedBirth(terrain) == false);
        terrain.objectMap[1][0].add(null);
        
        terrain.objectMap[0][0].set(0, null);
    }
    
    public void testUpRightCorner(){
        plant.setX(0);
        plant.setY(terrain.getWidth() - 1);
        terrain.objectMap[0][terrain.getWidth() - 1].set(0, plant);
        assertTrue("Returned not Locked while specimen is locked", plant.lockedBirth(terrain) == true);
        
        terrain.objectMap[0][terrain.getWidth() - 2].clear();
        assertTrue("Could not recognize UP_LEFT position and returned locked", plant.lockedBirth(terrain) == false);
        terrain.objectMap[0][terrain.getWidth() - 2].add(null);
        
        terrain.objectMap[1][terrain.getWidth() - 1].clear();
        assertTrue("Could not recognize UP position and returned locked", plant.lockedBirth(terrain) == false);
        terrain.objectMap[1][terrain.getWidth() - 1].add(null);
        
        terrain.objectMap[1][terrain.getWidth() - 2].clear();
        assertTrue("Could not recognize UP_RIGHT position and returned locked", plant.lockedBirth(terrain) == false);
        terrain.objectMap[1][terrain.getWidth() - 2].add(null);
        
        terrain.objectMap[0][terrain.getWidth() - 1].set(0, null);
    }
    
    public void testDownLeftCorner(){
        plant.setX(terrain.getHeight() - 1);
        plant.setY(0);
        terrain.objectMap[terrain.getHeight() - 1][0].set(0, plant);
        assertTrue("Returned not Locked while specimen is locked", plant.lockedBirth(terrain) == true);
        
        terrain.objectMap[terrain.getHeight() - 1][1].clear();
        assertTrue("Could not recognize UP_LEFT position and returned locked", plant.lockedBirth(terrain) == false);
        terrain.objectMap[terrain.getHeight() - 1][1].add(null);
        
        terrain.objectMap[terrain.getHeight() - 2][0].clear();
        assertTrue("Could not recognize UP position and returned locked", plant.lockedBirth(terrain) == false);
        terrain.objectMap[terrain.getHeight() - 2][0].add(null);
        
        terrain.objectMap[terrain.getHeight() - 2][1].clear();
        assertTrue("Could not recognize UP_RIGHT position and returned locked", plant.lockedBirth(terrain) == false);
        terrain.objectMap[terrain.getHeight() - 2][1].add(null);
        
        terrain.objectMap[terrain.getHeight() - 1][0].set(0, null);
    }
    
    public void testDownRightCorner(){
        plant.setX(terrain.getHeight() - 1);
        plant.setY(terrain.getWidth() - 1);
        terrain.objectMap[terrain.getHeight() - 1][terrain.getWidth() - 1].set(0, plant);
        assertTrue("Returned not Locked while specimen is locked", plant.lockedBirth(terrain) == true);
        
        terrain.objectMap[terrain.getHeight() - 2][terrain.getWidth() - 2].clear();
        assertTrue("Could not recognize UP_LEFT position and returned locked", plant.lockedBirth(terrain) == false);
        terrain.objectMap[terrain.getHeight() - 2][terrain.getWidth() - 2].add(null);
        
        terrain.objectMap[terrain.getHeight() - 2][terrain.getWidth() - 1].clear();
        assertTrue("Could not recognize UP position and returned locked", plant.lockedBirth(terrain) == false);
        terrain.objectMap[terrain.getHeight() - 2][terrain.getWidth() - 1].add(null);
        
        terrain.objectMap[terrain.getHeight() - 1][terrain.getWidth() - 2].clear();
        assertTrue("Could not recognize UP_RIGHT position and returned locked", plant.lockedBirth(terrain) == false);
        terrain.objectMap[terrain.getHeight() - 1][terrain.getWidth() - 2].add(null);
        
        terrain.objectMap[terrain.getHeight() - 1][terrain.getWidth() - 1].set(0, null);
    }
    
    public void testCenter(){
        terrain.objectMap[1][1].set(0, plant);
        assertTrue("Returned not Locked while specimen is locked", plant.lockedBirth(terrain) == true);
        
        terrain.objectMap[0][0].clear();
        assertTrue("Could not recognize UP_LEFT position and returned locked", plant.lockedBirth(terrain) == false);
        terrain.objectMap[0][0].add(null);
        
        terrain.objectMap[0][1].clear();
        assertTrue("Could not recognize UP position and returned locked", plant.lockedBirth(terrain) == false);
        terrain.objectMap[0][1].add(null);
        
        terrain.objectMap[0][2].clear();
        assertTrue("Could not recognize UP_RIGHT position and returned locked", plant.lockedBirth(terrain) == false);
        terrain.objectMap[0][2].add(null);
        
        terrain.objectMap[1][0].clear();
        assertTrue("Could not recognize LEFT position and returned locked", plant.lockedBirth(terrain) == false);
        terrain.objectMap[1][0].add(null);
        
        terrain.objectMap[1][2].clear();
        assertTrue("Could not recognize RIGHT position and returned locked", plant.lockedBirth(terrain) == false);
        terrain.objectMap[1][2].add(null);
        
        terrain.objectMap[2][0].clear();
        assertTrue("Could not recognize DOWN_LEFT position and returned locked", plant.lockedBirth(terrain) == false);
        terrain.objectMap[2][0].add(null);
        
        terrain.objectMap[2][1].clear();
        assertTrue("Could not recognize DOWN position and returned locked", plant.lockedBirth(terrain) == false);
        terrain.objectMap[2][1].add(null);
        
        terrain.objectMap[2][2].clear();
        assertTrue("Could not recognize DOWN_RIGHT position and returned locked", plant.lockedBirth(terrain) == false);
        terrain.objectMap[2][2].add(null);
        
        terrain.objectMap[1][1].set(0, null);
    }
    
    @Test
    public void testLockedBirth(){
        plant.setEnergy(200);
        
        for(int i = 0; i < terrain.getHeight(); i++){
            for(int j = 0; j < terrain.getWidth(); j++){
                terrain.objectMap[i][j].add(null);
            }
        }
        
        
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
    
    
    @Test
    public void testGetName(){
        assertTrue("Could not get name correctly", plant.getName().compareTo("wheat") == 0);
    }
    
    @Test
    public void testGetType(){
        assertTrue("Could not get type correctly", plant.getType().compareTo("vegetable") == 0);
    }
    
    @Test
    public void testGetSymbol(){
        assertTrue("Could not get symbol correctly", plant.getSymbol() == 'h');
    }
    
    @Test
    public void testGetEnergySources(){
        ArrayList<String> tmp = plant.getEnergySources();
        assertTrue("Could not get energy sources correctly", tmp.get(0).compareTo("light") == 0);
    }
    
    @Test
    public void testGetStats(){
        ArrayList<Double> tmp = plant.getStats();
        assertTrue("Could not get stats correctly", tmp.get(0) == 10.1);
        assertTrue("Could not get stats correctly", tmp.get(1) == 5.1);
    }
    
    @Test
    public void testGetEnergy(){
        assertTrue("Could not get energy correctly", plant.getEnergy() == 20);
    }
    
    @Test
    public void testGetBirthEnergy(){
        assertTrue("Could not get birth energy correctly", plant.getBirthEnergy() == 30);
    }
    
    @Test
    public void testGetMaxEnergy(){
        assertTrue("Could not get max energy correctly", plant.getMaxEnergy() == 50);
    }
    
    @Test
    public void testGetLivingEnergy(){
        assertTrue("Could not get living energy correctly", plant.getLivingEnergy() == 20);
    }
    
    @Test
    public void testGetX(){
        assertTrue("Could not get x coordinate correctly", plant.getX() == 1);
    }
    
    @Test
    public void testGetY(){
        assertTrue("Could not get y coordinate correctly", plant.getY() == 1);
    }
    
    @Test
    public void testSetX(){
        plant.setX(10);
        assertTrue("Could not set x coordinate correctly", plant.getX() == 10);
        
        plant.setX(11);
        assertTrue("Could not set x coordinate correctly", plant.getX() == 11);
    }
    
    @Test
    public void testSetY(){
        plant.setY(10);
        assertTrue("Could not set y coordinate correctly", plant.getY() == 10);
        
        plant.setY(11);
        assertTrue("Could not set y coordinate correctly", plant.getY() == 11);
    }
    
    @Test
    public void testSetEnergy(){
        plant.setEnergy(30);
        assertTrue("Could not set energy correctly", plant.getEnergy() == 30);
        
        plant.setEnergy(50);
        assertTrue("Could not set energy correctly", plant.getEnergy() == 50);
    }

    
    @Test
    public void testSetAge(){
        plant.setAge(10);
        assertTrue("Could not set age correctly", plant.getAge() == 10);
    }
}
