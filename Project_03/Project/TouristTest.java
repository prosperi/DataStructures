

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;

/**
 * The test class TouristTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class TouristTest
{
    TouristMap tMap;
    Tour tour;
    Tourist tourist;
    World world;
    Cell cell_01, cell_02, cell_03;
    TourTile tile_01, tile_02, tile_03;
    
    Carnivore carnivore;
    Herbivore herbivore;
    
    /**
     * Default constructor for test class TouristTest
     */
    public TouristTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
        world = new World(5, 5, 10);
        
        cell_01 = world.get(0, 0);
        cell_02 = world.get(1, 1);
        cell_03 = world.get(2, 2);
        
        ArrayList<Integer> arr_01 = new ArrayList<Integer>();
        arr_01.add(0);
        arr_01.add(0);
        
        ArrayList<Integer> arr_02 = new ArrayList<Integer>();
        arr_02.add(1);
        arr_02.add(1);
        
        ArrayList<Integer> arr_03 = new ArrayList<Integer>();
        arr_03.add(2);
        arr_03.add(2);
        
        tMap = new TouristMap(world);
        tMap.addTile(11, arr_01);
        tMap.addTile(10, arr_02);
        tMap.addTile(12, arr_03);
        
        tile_01 = tMap.getTile(11);
        tile_02 = tMap.getTile(10);
        tile_03 = tMap.getTile(12);
        
                
        tour = new Tour(tMap, 'S', 11, 3, 2, 1);
        tourist = new Tourist(tMap, tour);
        tile_01.addTourist(tourist);
        
        carnivore = new Carnivore("tiger", "t", new ArrayList<String>(), 10.0, 1.0, 40.0, 30.0, 100.0, 30.0, 10.0, 1.0, 2, 2, 10);
        herbivore = new Herbivore("horse", "h", new ArrayList<String>(), 10.0, 1.0, 40.0, 30.0, 100.0, 30.0, 10.0, 1.0, 2, 2, 10);
        cell_01.setAnimal(carnivore);
        cell_03.setAnimal(herbivore);
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }
    
    @Test
    public void testMove(){
        assertTrue("Could not initialize correctly " + tourist.getCurrentTile().getKey() + " " + tile_01.getKey(), tourist.getCurrentTile() == tour.getStartingTile() && tile_01 == tourist.getCurrentTile());
        assertTrue("Could not initialize correctly " + tile_01.getTourists().size(), tile_01.getTourists().size() == 1);        
        assertTrue("Could not initialize correctly", tile_01.getTourists().get(0) == tourist);
        
        assertTrue("Could not move correctly", !tourist.move(tile_02));
        tourist.setMoved(false);
        assertTrue("Could not move correctly", tourist.move(tile_02));
        assertTrue("Could not move correctly", tourist.getCurrentTile() == tile_02);
        assertTrue("Could not move correctly ", tile_01.getTourists().size() == 0);
        
        Tourist tourist_01 = new Tourist(tMap, tour);
        Tourist tourist_02 = new Tourist(tMap, tour);
        Tourist tourist_03 = new Tourist(tMap, tour);
        Tourist tourist_04 = new Tourist(tMap, tour);
        
        tile_01.addTourist(tourist_01);
        tile_01.addTourist(tourist_02);
        tile_01.addTourist(tourist_03);
        tile_01.addTourist(tourist_04);
        
        assertTrue("Could not move correctly " + tile_01.getTourists().size(), tile_01.getTourists().size() == 3);
        assertTrue("Could not move correctly", !tourist_01.move(tile_02));
        assertTrue("Could not move correctly", !tourist_02.move(tile_02));
        assertTrue("Could not move correctly", !tourist_03.move(tile_02));
        tourist_01.setMoved(false);
        tourist_02.setMoved(false);
        tourist_03.setMoved(false);
        assertTrue("Could not move correctly", tourist_01.move(tile_02));
        assertTrue("Could not move correctly", tourist_02.move(tile_02));
        assertTrue("Could not move correctly", !tourist_03.move(tile_02));
        assertTrue("Could not move correctly " + tile_01.getTourists().size(), tile_01.getTourists().size() == 1);
        assertTrue("Could not move correctly " + tile_02.getTourists().size(), tile_02.getTourists().size() == 3);

    }
    
    @Test
    public void testInfluenceAnimals(){
        Tourist tourist_01 = new Tourist(tMap, tour);
        Tourist tourist_02 = new Tourist(tMap, tour);
        Tourist tourist_03 = new Tourist(tMap, tour);
        Tourist tourist_04 = new Tourist(tMap, tour);
        
        tile_01.addTourist(tourist_01);
        tourist_01.influenceAnimals();
        assertTrue("Could not influence the animal " + carnivore.getEnergy(), carnivore.getEnergy() == 29.0);
        assertTrue("Could not influence the animal " + herbivore.getEnergy(), herbivore.getEnergy() == 29.0);
        
        tile_03.addTourist(tourist_03);
        tourist_03.influenceAnimals();
        assertTrue("Could not influence the animal correctly" + carnivore.getEnergy(), carnivore.getEnergy() == 29.0);
        assertTrue("Could not influence the animal correctly" + herbivore.getEnergy(), herbivore.getEnergy() == 29.0);
        
        carnivore.setInfluenced(false);
        herbivore.setInfluenced(false);
        
        tourist_03.influenceAnimals();
        assertTrue("Could not influence the animal correctly" + carnivore.getEnergy(), carnivore.getEnergy() == 28.0);
        assertTrue("Could not influence the animal correctly" + herbivore.getEnergy(), herbivore.getEnergy() == 28.0);
    }
}
