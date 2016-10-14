

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
        assertTrue("Plant could not eat", plant.getEnergy() == 50);
        
        plant.setEnergy(0);
        plant.eat(terrain, habitants);
        assertTrue("Plant could not eat", plant.getEnergy() == 0);
    }
    
    @Test
    public void testGetName(){
        assertTrue("Could not get name correctly", plant.getName().compareTo("wheat") == 0);
    }
    
    @Test
    public void testGetType(){
        assertTrue("Could not get name correctly", plant.getType().compareTo("vegetable") == 0);
    }
    
    @Test
    public void testGetSymbol(){
        assertTrue("Could not get name correctly", plant.getSymbol() == 'h');
    }
    
    @Test
    public void testGetEnergySources(){
        ArrayList<String> tmp = plant.getEnergySources();
        assertTrue("Could not get name correctly", tmp.get(0).compareTo("light") == 0);
    }
    
    @Test
    public void testGetStats(){
        ArrayList<Double> tmp = plant.getStats();
        assertTrue("Could not get name correctly", tmp.get(0) == 10.1);
        assertTrue("Could not get name correctly", tmp.get(1) == 5.1);
    }
    
    @Test
    public void testGetEnergy(){
        assertTrue("Could not get name correctly", plant.getEnergy() == 20);
    }
    
    @Test
    public void testGetBirthEnergy(){
        assertTrue("Could not get name correctly", plant.getBirthEnergy() == 30);
    }
    
    @Test
    public void testGetMaxEnergy(){
        assertTrue("Could not get name correctly", plant.getMaxEnergy() == 50);
    }
    
    @Test
    public void testGetLivingEnergy(){
        assertTrue("Could not get name correctly", plant.getLivingEnergy() == 20);
    }
    
    @Test
    public void testGetX(){
        assertTrue("Could not get name correctly", plant.getX() == 1);
    }
    
    @Test
    public void testGetY(){
        assertTrue("Could not get name correctly", plant.getY() == 1);
    }
    
    @Test
    public void testSetX(){
        plant.setX(10);
        assertTrue("Could not get name correctly", plant.getX() == 10);
        
        plant.setX(11);
        assertTrue("Could not get name correctly", plant.getX() == 11);
    }
    
    @Test
    public void testSetY(){
        plant.setY(10);
        assertTrue("Could not get name correctly", plant.getY() == 10);
        
        plant.setY(11);
        assertTrue("Could not get name correctly", plant.getY() == 11);
    }
    
    @Test
    public void testSetEnergy(){
        plant.setEnergy(30);
        assertTrue("Could not get name correctly", plant.getEnergy() == 30);
        
        plant.setEnergy(50);
        assertTrue("Could not get name correctly", plant.getEnergy() == 50);
    }
    
    /*@Test
    public void testGetDeathAge(){
        assertTrue("Could not get name correctly", plant.getName.compareTo("wheat") == 0);
    }
    
    @Test
    public void testGetAge(){
        assertTrue("Could not get name correctly", plant.getName.compareTo("wheat") == 0);
    }
    
    @Test
    public void testSetAge(){
        assertTrue("Could not get name correctly", plant.getName.compareTo("wheat") == 0);
    }*/
}
