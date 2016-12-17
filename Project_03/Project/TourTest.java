

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;

/**
 * The test class TourTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class TourTest
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
    
    /**
     * Default constructor for test class TourTest
     */
    public TourTest()
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
    public void testInitializeRoute(){
         ArrayList<Integer> arr_04 = new ArrayList<Integer>();
        ArrayList<Integer> arr_05 = new ArrayList<Integer>();
        
        arr_04.add(3);
        arr_04.add(3);
        arr_05.add(4);
        arr_05.add(4);
        
        tMap.addTile(13, arr_04);
        tMap.addTile(14, arr_05);
        
        tile_01 = tMap.getTile(10);
        tile_02 = tMap.getTile(11);
        tile_03 = tMap.getTile(12);
        tile_04 = tMap.getTile(13);
        tile_05 = tMap.getTile(14);
        
        TourEdge edge_01 = new TourEdge(tile_01, tile_02, 1),
                 edge_02 = new TourEdge(tile_02, tile_01, 1);
        
        tile_01.getAdjacent().add(edge_01);
        tile_02.getAdjacent().add(edge_02);
        
        edge_01 = new TourEdge(tile_01, tile_03, 4);
        edge_02 = new TourEdge(tile_03, tile_01, 4);
        tile_01.getAdjacent().add(edge_01);
        tile_03.getAdjacent().add(edge_02);
        
        edge_01 = new TourEdge(tile_02, tile_04, 3);
        edge_02 = new TourEdge(tile_04, tile_02, 3);
        tile_02.getAdjacent().add(edge_01);
        tile_04.getAdjacent().add(edge_02);
        
        edge_01 = new TourEdge(tile_04, tile_05, 2);
        edge_02 = new TourEdge(tile_05, tile_04, 2);
        tile_04.getAdjacent().add(edge_01);
        tile_05.getAdjacent().add(edge_02);
        
        edge_01 = new TourEdge(tile_03, tile_04, 1);
        edge_02 = new TourEdge(tile_04, tile_03, 1);
        tile_03.getAdjacent().add(edge_01);
        tile_04.getAdjacent().add(edge_02);
        
        edge_01 = new TourEdge(tile_03, tile_05, 7);
        edge_02 = new TourEdge(tile_05, tile_03, 7);
        tile_03.getAdjacent().add(edge_01);
        tile_05.getAdjacent().add(edge_02);
        
        tour.initializeRoute();
       
        String str = "11  10  12  13  14  ";
        String str_tmp = "";
        for(int i = 0; i < tour.getRoute().size(); i++){
            str_tmp += tour.getRoute().get(i).getKey() + "  ";
        }
        assertTrue("Could not generate correct path " + str_tmp, str_tmp.equals(str));
    }
    
    @Test
    public void testInitializeTourists(){
        assertTrue("Could not initialize correctly", tour.getTourists().size() == 0);
        
        tour.initializeTourists();
        assertTrue("Could not initialize correctly " + tour.getTourists().size(), tour.getTourists().size() == 3);
        assertTrue("Could not initialize correctly", tour.getStartingTile().getTourists().size() == 3);
        
        tile_01.getTourists().remove(tile_01.getTourists().get(0));
        assertTrue("Could not initialize correctly", tour.getStartingTile().getTourists().size() == 2);
        tour.initializeTourists();
        assertTrue("Could not initialize correctly " + tour.getTourists().size(), tour.getTourists().size() == 4);
        assertTrue("Could not initialize correctly", tour.getStartingTile().getTourists().size() == 3);
    }
    
    @Test
    public void testCreateRoute(){
        ArrayList<Integer> arr_04 = new ArrayList<Integer>();
        ArrayList<Integer> arr_05 = new ArrayList<Integer>();
        
        arr_04.add(3);
        arr_04.add(3);
        arr_05.add(4);
        arr_05.add(4);
        
        tMap.addTile(13, arr_04);
        tMap.addTile(14, arr_05);
        
        tile_01 = tMap.getTile(10);
        tile_02 = tMap.getTile(11);
        tile_03 = tMap.getTile(12);
        tile_04 = tMap.getTile(13);
        tile_05 = tMap.getTile(14);
        
        TourEdge edge_01 = new TourEdge(tile_01, tile_02, 1),
                 edge_02 = new TourEdge(tile_02, tile_01, 1);
        
        tile_01.getAdjacent().add(edge_01);
        tile_02.getAdjacent().add(edge_02);
        
        edge_01 = new TourEdge(tile_01, tile_03, 4);
        edge_02 = new TourEdge(tile_03, tile_01, 4);
        tile_01.getAdjacent().add(edge_01);
        tile_03.getAdjacent().add(edge_02);
        
        edge_01 = new TourEdge(tile_02, tile_04, 3);
        edge_02 = new TourEdge(tile_04, tile_02, 3);
        tile_02.getAdjacent().add(edge_01);
        tile_04.getAdjacent().add(edge_02);
        
        edge_01 = new TourEdge(tile_04, tile_05, 2);
        edge_02 = new TourEdge(tile_05, tile_04, 2);
        tile_04.getAdjacent().add(edge_01);
        tile_05.getAdjacent().add(edge_02);
        
        edge_01 = new TourEdge(tile_03, tile_04, 1);
        edge_02 = new TourEdge(tile_04, tile_03, 1);
        tile_03.getAdjacent().add(edge_01);
        tile_04.getAdjacent().add(edge_02);
        
        edge_01 = new TourEdge(tile_03, tile_05, 7);
        edge_02 = new TourEdge(tile_05, tile_03, 7);
        tile_03.getAdjacent().add(edge_01);
        tile_05.getAdjacent().add(edge_02);
        
        ArrayList<TourTile> tbv = new ArrayList<TourTile>();
        tbv.addAll(tMap.getTiles().values());
        TourTile cTile = tour.getStartingTile();
        tbv.remove(tour.getStartingTile());
        Route cRoute = new Route(cTile, 0);
        Route r = tour.createRoute(tbv, cRoute, cTile, 0);
        
        String str =  " 11  10  12  13  14 || 8";
        assertTrue("Could not generate correct path ", str.equals(r.toString()));
        assertTrue("Could not generate correct path " + r.getCost(), r.getCost() == 8);
    }
    
    @Test
    public void testProceed(){
        // check for the end of the tour
        // check that the size of tourists list was reduced
        // check getMoved == false
        assertTrue("Could not initialize correctly", tour.getTourists().size() == 0);
        assertTrue("Could not initialize correctly", tour.getStartingTile().getTourists().size() == 0);
        
        tour.getRoute().add(tile_01);
        tour.getRoute().add(tile_02);
        tour.getRoute().add(tile_03);
        
        tour.proceed();
        assertTrue("Could not proceed correctly", tour.getTourists().size() == 3);
        assertTrue("Could not proceed correctly", tour.getStartingTile().getTourists().size() == 3);
        assertTrue("Could not proceed correctly", tour.getStartingTile().getTourists().get(0).getMoved() == false);
        assertTrue("Could not proceed correctly", tour.getStartingTile().getTourists().get(1).getMoved() == false);
        assertTrue("Could not proceed correctly", tour.getStartingTile().getTourists().get(2).getMoved() == false);
       
        tour.proceed();
        assertTrue("Could not proceed correctly " + tour.getTourists().size(), tour.getTourists().size() == 3);
        assertTrue("Could not proceed correctly", tour.getStartingTile().getTourists().size() == 0);
        assertTrue("Could not proceed correctly", tour.getRoute().get(1).getTourists().size() == 3);
        
        tour.proceed();
        assertTrue("Could not proceed correctly " + tour.getTourists().size(), tour.getTourists().size() == 6);
        assertTrue("Could not proceed correctly", tour.getStartingTile().getTourists().size() == 3);
        assertTrue("Could not proceed correctly", tour.getRoute().get(1).getTourists().size() == 0);
        assertTrue("Could not proceed correctly", tour.getRoute().get(2).getTourists().size() == 3);
        
        tour.proceed();
        assertTrue("Could not proceed correctly " + tour.getTourists().size(), tour.getTourists().size() == 3);
        assertTrue("Could not proceed correctly", tour.getStartingTile().getTourists().size() == 0);
        assertTrue("Could not proceed correctly", tour.getRoute().get(1).getTourists().size() == 3);
        assertTrue("Could not proceed correctly", tour.getRoute().get(2).getTourists().size() == 0);
        
        assertTrue("Could not initialize correctly " + tour.getTourists().size(), tour.getTourists().size() == 3);
        tour.proceed();
        assertTrue("Could not proceed correctly " + tour.getTourists().size(), tour.getTourists().size() == 6);
        assertTrue("Could not proceed correctly", tour.getStartingTile().getTourists().size() == 3);
        assertTrue("Could not proceed correctly", tour.getRoute().get(1).getTourists().size() == 0);
        assertTrue("Could not proceed correctly", tour.getRoute().get(2).getTourists().size() == 3);
    }
    
    @Test
    public void testAffect(){
        Tourist tourist_01 = new Tourist(tMap, tour);
        Tourist tourist_02 = new Tourist(tMap, tour);
        Tourist tourist_03 = new Tourist(tMap, tour);
        Tourist tourist_04 = new Tourist(tMap, tour);
        
        tile_01.addTourist(tourist_01);
        tile_03.addTourist(tourist_03);
        
        tour.getRoute().add(tile_01);
        tour.getRoute().add(tile_02);
        tour.getRoute().add(tile_03);
        
        tour.affect();
        assertTrue("Could not influence the animal correctly" + carnivore.getEnergy(), carnivore.getEnergy() == 29.0);
        assertTrue("Could not influence the animal correctly" + herbivore.getEnergy(), herbivore.getEnergy() == 29.0);
        
        carnivore.setInfluenced(false);
        herbivore.setInfluenced(false);
        
        tour.affect();
        assertTrue("Could not influence the animal correctly" + carnivore.getEnergy(), carnivore.getEnergy() == 28.0);
        assertTrue("Could not influence the animal correctly" + herbivore.getEnergy(), herbivore.getEnergy() == 28.0);
        
        carnivore.setInfluenced(false);
        herbivore.setInfluenced(false);
                
        Tour tour_01 = new Tour(tMap, 'S', 11, 3, 2, 1);
        tour.getRoute().add(tile_03);
        tour.getRoute().add(tile_01);
        tour.getRoute().add(tile_02);
        
        tour.affect();
        tour_01.affect();
        
        assertTrue("Could not influence the animal correctly" + carnivore.getEnergy(), carnivore.getEnergy() == 27.0);
        assertTrue("Could not influence the animal correctly" + herbivore.getEnergy(), herbivore.getEnergy() == 27.0);
        
    }
}
