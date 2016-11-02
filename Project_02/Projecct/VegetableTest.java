
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class VegetableTest tests class Vegetable. 
 *
 * @version (10/15/2016)
 */
public class VegetableTest
{
    /**
     * Default constructor for test class VegetableTest
     */
    public VegetableTest()
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
     * Tests vegetable's death method. Makes sure it returns the right boolean each time, depending on the instance's current state
     */
    @Test
    public void TestVegetableDeath()
    {
        Vegetable bamboo = new Vegetable("vegetable", "bamboo", "b", null, 1, 10, 15, 2, 15, 40, 3);
        
        Cell myCell = new Cell(0,0);
        
        bamboo.getSpecies().get(0).setCell(myCell);
        //energy and age are at acceptable levels (instance should not die)
        assertTrue("vegetable death is not working correctly",!(bamboo.vegetableDeath(bamboo.getSpecies().get(0))));
        
        bamboo.getSpecies().get(0).setEnergyLevel(0);
        //energy is 0 (instance should die)
        assertTrue("vegetable death is not working correctly",(bamboo.vegetableDeath(bamboo.getSpecies().get(0))));
        
        bamboo.getSpecies().get(0).setEnergyLevel(5);
        bamboo.getSpecies().get(0).setAge(50);
        //age is too high (instance should die)
        assertTrue("vegetable death is not working correctly",(bamboo.vegetableDeath(bamboo.getSpecies().get(0))));
    }
    
    /**
     * Tests vegetable's birth method. Makes sure it returns the right boolean each time, depending on the instance's curent state
     */
    @Test
    public void TestVegetableBirth()
    {
        Vegetable bamboo = new Vegetable("vegetable", "bamboo", "b", null, 1, 10, 15, 2, 15, 40, 3);
        
        Grid myGrid = new Grid(2,2);
        
        myGrid.getGrid().get(0).get(0).setSpeciesInstancePlant(bamboo.getSpecies().get(0));
        myGrid.getGrid().get(0).get(0).setIsOccupiedPlant(true);
        myGrid.getGrid().get(0).get(0).setPlantSymbol(bamboo.getPlantSymbol());
        bamboo.getSpecies().get(0).setCell(myGrid.getGrid().get(0).get(0));

        bamboo.getSpecies().get(0).setEnergyLevel(20);
        //instance has enough energy and room (instance should give birth and bamboo size should increase by one instance)
        assertTrue("vegetable birth is not working correctly",((bamboo.vegetableBirth(myGrid, bamboo.getSpecies().get(0))) && (bamboo.getSpecies().size() == 2)));
        
        //instance doesn't have enough energy (instance should not give birth)
        assertTrue("vegetable birth is not working correctly", !(bamboo.vegetableBirth(myGrid, bamboo.getSpecies().get(0))));
        
        bamboo.getSpecies().get(0).setEnergyLevel(30); //fill up rest of grid
        bamboo.vegetableBirth(myGrid, bamboo.getSpecies().get(0));
        bamboo.getSpecies().get(0).setEnergyLevel(30);
        bamboo.vegetableBirth(myGrid, bamboo.getSpecies().get(0));
        bamboo.getSpecies().get(0).setEnergyLevel(30);
        //instance has enough energy but no room (instance should not give birth, bamboo population should be four)
        assertTrue("vegetable birth is not working correctly",(!(bamboo.vegetableBirth(myGrid, bamboo.getSpecies().get(0))) && (bamboo.getSpecies().size() == 4)));
    }
    
    /**
     * Tests vegetable's eat method. Makes sure variables are being updated correctly in the method
     */
    @Test
    public void TestVegetableEat()
    {
        Vegetable bamboo = new Vegetable("vegetable", "bamboo", "b", null, 1, 10, 15, 2, 15, 40, 3);
        
        double light = 5;
        
        //does energy level update correctly?
        bamboo.vegetableEat(bamboo.getSpecies().get(0),light); //energy level is 10 to start, should be 15 coming out
        assertTrue("vegetable eat is not working correctly",(bamboo.getSpecies().get(0).getEnergyLevel() == 15));
        
        //does method stop energy level from going above max?
        bamboo.getSpecies().get(0).setEnergyLevel(37);
        bamboo.vegetableEat(bamboo.getSpecies().get(0),light); //energy level would be 42, but max is 40, so should be 40 coming out
        assertTrue("vegetable eat is not working correctly",(bamboo.getSpecies().get(0).getEnergyLevel() == 40));
    }
}
