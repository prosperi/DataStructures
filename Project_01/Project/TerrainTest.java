

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;

/**
 * @author  Zura Mestiashvili
 * @version (a version number or a date)
 */

public class TerrainTest
{
    Terrain terrain;
    Animal animal;
    Plant plant;
    
    public TerrainTest()
    {
    }

   
    @Before
    public void setUp()
    {
        terrain = new Terrain(5, 15, 10);
        ArrayList<String> ls_01 = new ArrayList<String>();
        ArrayList<Double> ls_03 = new ArrayList<Double>();
        ls_01.add("light");
        ls_03.add(10.1);
        ls_03.add(5.1);
        plant = new Plant("wheat", "vegetable", 'h', ls_01, 20, ls_03, 30, 50, 2, 1, 1);
        ls_01.clear();
        ls_01.add("wheat");
        ls_01.add("banana");
        animal = new Animal("cow", "herbivore", 'c', ls_01, 20, ls_03, 30, 50, 2, 2, 2);
    }

   
    @After
    public void tearDown()
    {
    }
    
    @Test
    public void testTerrain(){
        for(int i = 0; i < terrain.getHeight(); i++){
            for(int j = 0; j < terrain.getWidth(); j++){
                assertTrue("Could not initialize map correctly", terrain.map[i][j] == '#');
                assertTrue("Could not initialize objectMap correctly", terrain.objectMap[i][j] != null);
            }
        }   
    }
    
    @Test
    public void testCheckCell(){
         for(int i = 0; i < terrain.getHeight(); i++){
            for(int j = 0; j < terrain.getWidth(); j++){
                assertTrue("Could not check cell in objectMap correctly", terrain.checkCell(i, j) == false);
            }
         }   
         
         terrain.objectMap[1][1].add(plant);
         terrain.objectMap[2][2].add(animal);
         for(int i = 0; i < terrain.getHeight(); i++){
            for(int j = 0; j < terrain.getWidth(); j++){
                if((i == 1 && j == 1) || (i == 2 && j == 2)) assertTrue("Could not check cell in objectMap correctly", terrain.checkCell(i, j) == true);
                else assertTrue("Could not check cell in objectMap correctly", terrain.checkCell(i, j) == false);
            } 
         }  
         
    }
    
    @Test
    public void testAddHabitants(){
        ArrayList<Specimen> habitants = new ArrayList<Specimen>();
        habitants.add(plant);
        habitants.add(animal);
        terrain.addHabitants(habitants);
        for(int i = 0; i < habitants.size(); i++){
            Specimen tmp = habitants.get(i);
            assertTrue("Could not add habitants correctly", terrain.objectMap[tmp.getX()][tmp.getY()].contains(tmp) == true);
        }
    }
    
    
    @Test
    public void testClear(){
        ArrayList<Specimen> habitants = new ArrayList<Specimen>();
        habitants.add(plant);
        habitants.add(animal);
        terrain.addHabitants(habitants);
        terrain.clear();
        for(int i = 0; i < terrain.getHeight(); i++){
            for(int j = 0; j < terrain.getWidth(); j++){
                assertTrue("Could not initialize map correctly", terrain.map[i][j] == '#');
                assertTrue("Could not initialize objectMap correctly", terrain.objectMap[i][j] != null);
            }
        }   
    }
    
    
    @Test
    public void testGetWidth(){
        assertTrue("Can't get terrain width properly", terrain.getWidth() == 5);
    }
    
    @Test
    public void testGetHeight(){
        assertTrue("Can't get terrain height properly", terrain.getHeight() == 15);
    }
    
    @Test
    public void testGetLight(){
        assertTrue("Can't get light properly", terrain.getLight() == 10);
    }
    
    @Test
    public void testSetLight(){
        terrain.setLight(12);
        assertTrue("Can't set terrain light properly", terrain.getLight() == 12);
    }
    
}
