

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
        ArrayList<Double> initialStats = new ArrayList<Double>();
        initialStats.add(300.0);
        initialStats.add(5.0);
        initialStats.add(20.0);
        ArrayList<Double> stats = new ArrayList<Double>();
        stats.add(10.1);
        stats.add(5.1);
        int[] position = {1, 1};
        plant = new Plant("wheat", "vegetable", 'h', energySources, initialStats, stats, 30, 50, 2, position);
    }
    
    

   
    @After
    public void tearDown()
    {
    }
    
    @Test
    public void testEat(){
        // check this part carefully
        plant.setEnergy(plant.getEnergy() + 100);
        
    }
    
}
