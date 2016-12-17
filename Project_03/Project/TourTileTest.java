

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList; 


/**
 * The test class TourTileTest.
 *
 * @author  Zura Mestiashvili   
 * @version v1.0.0
 */
public class TourTileTest
{
    World world;
    Cell cell_01, cell_02, cell_03;
    TourTile tile_01, tile_02, tile_03;
    
    TouristMap tMap;
    Tour tour;
    Tourist tourist;
    /**
     * Default constructor for test class TourTileTest
     */
    public TourTileTest()
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
        cell_01 = new Cell(world, 0, 0);
        cell_02 = new Cell(world, 1, 1);
        cell_03 = new Cell(world, 2, 2);
        
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
        
                
        tour = new Tour(tMap, 'S', 11, 3, 3, 1);
        tourist = new Tourist(tMap, tour);
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
    public void testReset(){
        tile_01.setDist(11);
        tile_01.setPrev(tile_02);
        tile_01.setScratch(1);
        
        assertTrue("Could not set correctly", tile_01.getDist() == 11);
        assertTrue("Could not set correctly", tile_01.getPrev() == tile_02);
        assertTrue("Could not set correctly", tile_01.getScratch() == 1);
        
        tile_01.reset();
        
        assertTrue("Could not set correctly", tile_01.getDist() == Integer.MAX_VALUE);
        assertTrue("Could not set correctly", tile_01.getPrev() == null);
        assertTrue("Could not set correctly", tile_01.getScratch() == 0);
    }
    
    @Test
    public void testAdjacentToString(){
        TourEdge edge = new TourEdge(tile_01, tile_02, 30);
        
        tile_01.getAdjacent().add(edge);
        
        assertTrue("Could not return correct String representation " +  tile_01.adjacentToString(), 
                    tile_01.adjacentToString().equals("  --> 10(30)"));
        
        
        edge = new TourEdge(tile_01, tile_03, 50);
        
        tile_01.getAdjacent().add(edge);
        
        assertTrue("Could not return correct String representation " +  tile_01.adjacentToString(), 
                    tile_01.adjacentToString().equals("  --> 10(30)  --> 12(50)"));
    }
    
    @Test
    public void testAddTourist(){
        assertTrue("Could not initialize correctly", tile_01.getTourists().size() == 0);
        
        tile_01.addTourist(tourist);
        assertTrue("Could not add correctly", tile_01.getTourists().size() == 1);
        
        assertTrue("Could not add correctly", tile_01.addTourist(new Tourist(tMap, tour)) == true);
        assertTrue("Could not add correctly", tile_01.addTourist(new Tourist(tMap, tour)) == true);
        assertTrue("Could not add correctly", tile_01.getTourists().size() == 3);
        
        assertTrue("Could not add correctly", tile_01.addTourist(new Tourist(tMap, tour)) == false);
        assertTrue("Could not add correctly", tile_01.getTourists().size() == 3);
    }
    
}
