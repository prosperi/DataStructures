

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

/**
 * The test class TouristMapTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class TouristMapTest
{
    
    World world;
    Cell cell_01, cell_02, cell_03;
    TourTile tile_01, tile_02, tile_03, tile_04, tile_05;
    
    TouristMap tMap;
    Tour tour;
    Tourist tourist;
    
    Route route_01, route_02, route_03;
    Carnivore carnivore;
    Herbivore herbivore;
    
    ArrayList<Integer> arr_01, arr_02, arr_03;
    
    /**
     * Default constructor for test class TouristMapTest
     */
    public TouristMapTest()
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
        
        arr_01 = new ArrayList<Integer>();
        arr_01.add(0);
        arr_01.add(0);
        
        arr_02 = new ArrayList<Integer>();
        arr_02.add(1);
        arr_02.add(1);
        
        arr_03 = new ArrayList<Integer>();
        arr_03.add(2);
        arr_03.add(2);
        
        tMap = new TouristMap(world);
        
                
        tour = new Tour(tMap, 'S', 11, 3, 2, 1);
        tourist = new Tourist(tMap, tour);
        
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
    public void testAddTile(){
        assertTrue("Could not add tile correctly", tMap.addTile(10, arr_01) == true);
        assertTrue("Could not add tile correctly", tMap.addTile(11, arr_02) == true);
        assertTrue("Could not add tile correctly", tMap.addTile(12, arr_03) == true);
        
        assertTrue("Could not add tile correctly", tMap.addTile(10, arr_01) == false);
        assertTrue("Could not add tile correctly", tMap.addTile(11, arr_02) == false);
        assertTrue("Could not add tile correctly", tMap.addTile(12, arr_03) == false);
    }
    
    @Test
    public void testAddEdge(){
        assertTrue("Could not initialize correctly", tMap.getSize() == 0);
        
        assertTrue("Could not add tile correctly", tMap.addTile(11, arr_01));
        assertTrue("Could not add tile correctly", world.get(0, 0).getTile() != null);
        
        assertTrue("Could not add tile correctly", tMap.addTile(12, arr_02));
        assertTrue("Could not add tile correctly", world.get(1, 1).getTile() != null);
        
        assertTrue("Could not add tile correctly", tMap.addTile(13, arr_03));
        assertTrue("Could not add tile correctly", world.get(2, 2).getTile() != null);
                
        assertTrue("Could not add tiles correctly", tMap.getSize() == 3);
        
        assertTrue("Could not add tile correctly", !tMap.addTile(11, arr_01));
        assertTrue("Could not add tile correctly", !tMap.addTile(12, arr_02));
        assertTrue("Could not add tile correctly", !tMap.addTile(13, arr_03));
        assertTrue("Could not add tiles correctly", tMap.getSize() == 3);
        

    }
    
    @Test
    public void testClearAll(){
        
        tMap.addTile(11, arr_01);
        tMap.addTile(12, arr_02);
        tMap.addTile(13, arr_03);
        
        tMap.getTile(11).setDist(1);
        tMap.getTile(12).setDist(2);
        tMap.getTile(13).setDist(3);
        
        tMap.getTile(11).setPrev(tMap.getTile(13));
        tMap.getTile(12).setPrev(tMap.getTile(11));
        tMap.getTile(13).setPrev(tMap.getTile(11));
        
        tMap.getTile(11).setScratch(1);
        tMap.getTile(12).setScratch(1);
        tMap.getTile(13).setScratch(3);
        
        tMap.clearAll();
        
        for(int i = 11; i <= 13; i++){
            TourTile tile = tMap.getTile(i);
            assertTrue("Could not reset correctly", tile.getDist() == Integer.MAX_VALUE);
            assertTrue("Could not reset correctly", tile.getPrev() == null);
            assertTrue("Could not reset correctly", tile.getScratch() == 0);
        }
    }
    
    @Test
    public void testAddTour(){
        assertTrue("Could not initialie tours correctly", tMap.getTours().size() == 0);
        
        tMap.addTour(tour);
        assertTrue("Could not add tour correctly", tMap.getTours().size() == 1);
        assertTrue("Could not add tour correctly", tMap.getTours().get(0) == tour);
        
        tour = new Tour(tMap, 'Y', 11, 3, 3, 1);
        tMap.addTour(tour);
        assertTrue("Could not add tour correctly", tMap.getTours().size() == 2);
        assertTrue("Could not add tour correctly", tMap.getTours().get(1) == tour);
    }
    
    @Test
    public void testGuideTour(){
        tMap.addTile(11, arr_01);
        tMap.addTile(10, arr_02);
        tMap.addTile(12, arr_03);
        
        tile_01 = tMap.getTile(11);
        tile_02 = tMap.getTile(10);
        tile_03 = tMap.getTile(12);
        
        Tour tour_01 = new Tour(tMap, 'S', 11, 3, 3, 1);
        Tour tour_02 = new Tour(tMap, 'S', 10, 3, 3, 1);
        tMap.addTour(tour_01);
        tMap.addTour(tour_02);
        
        assertTrue("Could not initialize correctly", tour_01.getTourists().size() == 0);
        assertTrue("Could not initialize correctly", tour_01.getStartingTile().getTourists().size() == 0);
        assertTrue("Could not initialize correctly", tour_02.getTourists().size() == 0);
        assertTrue("Could not initialize correctly", tour_02.getStartingTile().getTourists().size() == 0);
        
        tour_01.getRoute().add(tile_01);
        tour_01.getRoute().add(tile_02);
        tour_01.getRoute().add(tile_03);

        tour_02.getRoute().add(tile_02);
        tour_02.getRoute().add(tile_03);
        tour_02.getRoute().add(tile_01);
        
        tMap.guideTour();
        assertTrue("Could not proceed correctly", tour_01.getTourists().size() == 3);
        assertTrue("Could not proceed correctly", tour_02.getTourists().size() == 3);
        assertTrue("Could not proceed correctly", tour_01.getStartingTile().getTourists().size() == 3);
        assertTrue("Could not proceed correctly", tour_01.getStartingTile().getTourists().get(0).getMoved() == false);
        assertTrue("Could not proceed correctly", tour_01.getStartingTile().getTourists().get(1).getMoved() == false);
        assertTrue("Could not proceed correctly", tour_01.getStartingTile().getTourists().get(2).getMoved() == false);
       
        tMap.guideTour();
        assertTrue("Could not proceed correctly " + tour_01.getTourists().size(), tour_01.getTourists().size() == 3);
        assertTrue("Could not proceed correctly " + tour_02.getTourists().size(), tour_02.getTourists().size() == 3);
        assertTrue("Could not proceed correctly", tour_01.getStartingTile().getTourists().size() == 3);
        assertTrue("Could not proceed correctly", tour_01.getRoute().get(1).getTourists().size() == 0);
        assertTrue("Could not proceed correctly", tour_01.getRoute().get(2).getTourists().size() == 3);
        
        tMap.guideTour();
        assertTrue("Could not proceed correctly " + tour_01.getTourists().size(), tour_01.getTourists().size() == 3);
        assertTrue("Could not proceed correctly " + tour_02.getTourists().size(), tour_02.getTourists().size() == 3);
        assertTrue("Could not proceed correctly", tour_01.getStartingTile().getTourists().size() == 3);
        assertTrue("Could not proceed correctly", tour_01.getRoute().get(1).getTourists().size() == 3);
        assertTrue("Could not proceed correctly", tour_01.getRoute().get(2).getTourists().size() == 0);
       
        tMap.guideTour();
        assertTrue("Could not proceed correctly " + tour_01.getTourists().size(), tour_01.getTourists().size() == 3);
        assertTrue("Could not proceed correctly " + tour_02.getTourists().size(), tour_02.getTourists().size() == 3);
        assertTrue("Could not proceed correctly", tour_01.getStartingTile().getTourists().size() == 0);
        assertTrue("Could not proceed correctly", tour_01.getRoute().get(1).getTourists().size() == 3);
        assertTrue("Could not proceed correctly", tour_01.getRoute().get(2).getTourists().size() == 3);
        
        assertTrue("Could not initialize correctly " + tour_01.getTourists().size(), tour_01.getTourists().size() == 3);
        
        tMap.guideTour();
        assertTrue("Could not proceed correctly " + tour_01.getTourists().size(), tour_01.getTourists().size() == 3);
        assertTrue("Could not proceed correctly " + tour_02.getTourists().size(), tour_02.getTourists().size() == 3);
        assertTrue("Could not proceed correctly", tour_01.getStartingTile().getTourists().size() == 3);
        assertTrue("Could not proceed correctly", tour_01.getRoute().get(1).getTourists().size() == 0);
        assertTrue("Could not proceed correctly", tour_01.getRoute().get(2).getTourists().size() == 3);
    }
    
    @Test
    public void testToString(){
        String str = "";
        assertTrue("Could not initialize correctly", tMap.toString().equals(str));
        
        tMap.addTile(11, arr_01);
        
        str = "11 | Tourists: 0 \n";
        assertTrue("Could not generate correct String representation", tMap.toString().equals(str));
        
        tMap.addTile(12, arr_02);
        str = "11 | Tourists: 0 \n12 | Tourists: 0 \n";
        assertTrue("Could not generate correct String representation", tMap.toString().equals(str));
        
        tMap.addTile(13, arr_03);
        str = "11 | Tourists: 0 \n12 | Tourists: 0 \n13 | Tourists: 0 \n";
        assertTrue("Could not generate correct String representation", tMap.toString().equals(str));
        
    }

}
