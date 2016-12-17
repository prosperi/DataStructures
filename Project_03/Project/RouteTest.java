

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

/**
 * The test class RouteTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class RouteTest
{
    World world;
    Cell cell_01, cell_02, cell_03;
    TourTile tile_01, tile_02, tile_03;
    
    TouristMap tMap;
    Tour tour;
    Tourist tourist;
    
    Route route_01, route_02, route_03;
    /**
     * Default constructor for test class RouteTest
     */
    public RouteTest()
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
        
        route_01 = new Route(tile_01, 10);
        route_02 = new Route(tile_02, 15);
        route_03 = new Route(tile_03, 10);
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
    public void testCompareTo(){
        assertTrue("Could not compare correctly", route_01.compareTo(route_02) == 1);
        assertTrue("Could not compare correctly", route_02.compareTo(route_03) == -1);
        assertTrue("Could not compare correctly", route_01.compareTo(route_03) == 0);
    }
    
    @Test
    public void testToString(){
        String str_01 = "|| 10",
               str_02 = "|| 15";
        
        assertTrue("Could not generate correct String representation", route_01.toString().equals(str_01));
        assertTrue("Could not generate correct String representation", route_03.toString().equals(str_01));
        assertTrue("Could not generate correct String representation", route_02.toString().equals(str_02));
               
        route_01.getLs().add(tile_01);
        route_01.getLs().add(tile_02);
        route_01.getLs().add(tile_03);
        route_02.getLs().add(tile_03);
        
        str_01 = " 11  10  12 || 10";
        str_02 = " 12 || 15";

        assertTrue("Could not generate correct String representation " + route_01.toString(), route_01.toString().equals(str_01));
    }
    
    @Test
    public void testGenerateLs(){
        tile_03.setPrev(tile_02);
        tile_02.setPrev(tile_01);
        
        route_03.generateLs();
        assertTrue("Could not generate correct list of tiles", route_03.getLs().get(0) == tile_01);
        assertTrue("Could not generate correct list of tiles", route_03.getLs().get(1) == tile_02);
        assertTrue("Could not generate correct list of tiles", route_03.getLs().get(2) == tile_03);
        
        route_02.generateLs();
        assertTrue("Could not generate correct list of tiles", route_02.getLs().get(0) == tile_01);
        assertTrue("Could not generate correct list of tiles", route_02.getLs().get(1) == tile_02);
        assertTrue("Could not generate correct list of tiles", route_02.getLs().size() == 2);
        
        route_01.generateLs();
        assertTrue("Could not generate correct list of tiles", route_01.getLs().get(0) == tile_01);
        assertTrue("Could not generate correct list of tiles", route_01.getLs().size() == 1);
    }
    
}
