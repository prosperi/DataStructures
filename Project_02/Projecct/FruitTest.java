

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class FruitTest tests class Fruit.
 *
 * @version (10/15/2016)
 */
public class FruitTest
{
    /**
     * Default constructor for test class FruitTest
     */
    public FruitTest()
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
    
    /**
     * Tests fruit's death method. Makes sure it returns the right boolean each time, depending on the instance's current state
     */
    @Test
    public void TestFruitDeath()
    {
        Fruit pineapple = new Fruit("fruit", "pineapple", "p", null, 1, 10, 15, 2, 15, 40, 3);
        
        Cell myCell = new Cell(0,0);
        
        pineapple.getSpecies().get(0).setCell(myCell);
        //energy and age are at acceptable levels (instance should not die)
        assertTrue("fruit death is not working correctly",!(pineapple.fruitDeath(pineapple.getSpecies().get(0))));
        
        pineapple.getSpecies().get(0).setEnergyLevel(0);
        //energy is 0 (instance should die)
        assertTrue("fruit death is not working correctly",(pineapple.fruitDeath(pineapple.getSpecies().get(0))));
        
        pineapple.getSpecies().get(0).setEnergyLevel(5);
        pineapple.getSpecies().get(0).setAge(50);
        //age is too high (instance should die)
        assertTrue("fruit death is not working correctly",(pineapple.fruitDeath(pineapple.getSpecies().get(0))));
    }
    
    /**
     * Tests fruit's birth method. Makes sure it returns the right boolean each time, depending on the instance's curent state
     */
    @Test
    public void TestFruitBirth()
    {
        Fruit pineapple = new Fruit("fruit", "pineapple", "p", null, 1, 10, 15, 2, 15, 40, 3);
        
        Grid myGrid = new Grid(2,2);
        
        myGrid.getGrid().get(0).get(0).setSpeciesInstancePlant(pineapple.getSpecies().get(0));
        myGrid.getGrid().get(0).get(0).setIsOccupiedPlant(true);
        myGrid.getGrid().get(0).get(0).setPlantSymbol(pineapple.getPlantSymbol());
        pineapple.getSpecies().get(0).setCell(myGrid.getGrid().get(0).get(0));

        pineapple.getSpecies().get(0).setEnergyLevel(20);
        //instance has enough energy and room (instance should give birth and pineapple size should increase by one instance)
        assertTrue("fruit birth is not working correctly",((pineapple.fruitBirth(myGrid, pineapple.getSpecies().get(0))) && (pineapple.getSpecies().size() == 2)));
        
        //instance doesn't have enough energy (instance should not give birth)
        assertTrue("fruit birth is not working correctly", !(pineapple.fruitBirth(myGrid, pineapple.getSpecies().get(0))));
        
        pineapple.getSpecies().get(0).setEnergyLevel(30); //fill up rest of grid
        pineapple.fruitBirth(myGrid, pineapple.getSpecies().get(0));
        pineapple.getSpecies().get(0).setEnergyLevel(30);
        pineapple.fruitBirth(myGrid, pineapple.getSpecies().get(0));
        pineapple.getSpecies().get(0).setEnergyLevel(30);
        //instance has enough energy but no room (instance should not give birth, pineapple population should be four)
        assertTrue("fruit birth is not working correctly",(!(pineapple.fruitBirth(myGrid, pineapple.getSpecies().get(0))) && (pineapple.getSpecies().size() == 4)));
    }
    
    /**
     * Tests fruit's eat method. Makes sure variables are being updated correctly in the method
     */
    @Test
    public void TestFruitEat()
    {
        Fruit pineapple = new Fruit("fruit", "pineapple", "p", null, 1, 10, 15, 2, 15, 40, 3);
        
        double light = 5;
        
        //does energy level update correctly?
        pineapple.fruitEat(pineapple.getSpecies().get(0),light); //energy level is 10 to start, should be 15 coming out
        assertTrue("fruit eat is not working correctly",(pineapple.getSpecies().get(0).getEnergyLevel() == 15));
        
        //does method stop energy level from going above max?
        pineapple.getSpecies().get(0).setEnergyLevel(37);
        pineapple.fruitEat(pineapple.getSpecies().get(0),light); //energy level would be 42, but max is 40, so should be 40 coming out
        assertTrue("fruit eat is not working correctly",(pineapple.getSpecies().get(0).getEnergyLevel() == 40));
    }
}
