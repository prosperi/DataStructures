
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class HerbivoreTest tests class Herbivore.
 *
 * @version (10/15/2016)
 */
public class HerbivoreTest
{
    /**
     * Default constructor for test class HerbivoreTest
     */
    public HerbivoreTest()
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
     * Tests herbivore's death method. Makes sure it returns the right boolean each time, depending on the instance's current state
     */
    @Test
    public void TestHerbivoreDeath()
    {
        Herbivore panda = new Herbivore("herbivore", "panda", "p", null, 1, 10, 15, 2, 15, 40, 3);
        
        Cell myCell = new Cell(0,0);
        
        panda.getSpecies().get(0).setCell(myCell);
        //energy and age are at acceptable levels (instance should not die)
        assertTrue("herbivore death is not working correctly",!(panda.herbivoreDeath(panda.getSpecies().get(0))));
        
        panda.getSpecies().get(0).setEnergyLevel(0);
        //energy is 0 (instance should die)
        assertTrue("herbivore death is not working correctly",(panda.herbivoreDeath(panda.getSpecies().get(0))));
        
        panda.getSpecies().get(0).setEnergyLevel(5);
        panda.getSpecies().get(0).setAge(50);
        //age is too high (instance should die)
        assertTrue("herbivore death is not working correctly",(panda.herbivoreDeath(panda.getSpecies().get(0))));
    }
    
    /**
     * Tests herbivore's birth method. Makes sure it returns the right boolean each time, depending on the instance's curent state
     */
    @Test
    public void TestHerbivoreBirth()
    {
        Herbivore panda = new Herbivore("herbivore", "panda", "p", null, 1, 10, 15, 2, 15, 40, 3);
        
        Grid myGrid = new Grid(2,2);
        
        myGrid.getGrid().get(0).get(0).setSpeciesInstanceAnimal(panda.getSpecies().get(0));
        myGrid.getGrid().get(0).get(0).setIsOccupiedAnimal(true);
        myGrid.getGrid().get(0).get(0).setAnimalSymbol(panda.getAnimalSymbol());
        panda.getSpecies().get(0).setCell(myGrid.getGrid().get(0).get(0));

        panda.getSpecies().get(0).setEnergyLevel(20);
        //instance has enough energy and room (instance should give birth and panda size should increase by one instance)
        assertTrue("herbivore birth is not working correctly",((panda.herbivoreBirth(myGrid, panda.getSpecies().get(0))) && (panda.getSpecies().size() == 2)));
        
        //instance doesn't have enough energy (instance should not give birth)
        assertTrue("herbivore birth is not working correctly", !(panda.herbivoreBirth(myGrid, panda.getSpecies().get(0))));
        
        panda.getSpecies().get(0).setEnergyLevel(30); //fill up rest of grid
        panda.herbivoreBirth(myGrid, panda.getSpecies().get(0));
        panda.getSpecies().get(0).setEnergyLevel(30);
        panda.herbivoreBirth(myGrid, panda.getSpecies().get(0));
        panda.getSpecies().get(0).setEnergyLevel(30);
        //instance has enough energy but no room (instance should not give birth, panda population should be four)
        assertTrue("herbivore birth is not working correctly",(!(panda.herbivoreBirth(myGrid, panda.getSpecies().get(0))) && (panda.getSpecies().size() == 4)));
    }
    
    /**
     * Tests herbivore's eat method. Makes sure it returns the right boolean each time, depending on the instance's current state
     */
    @Test
    public void TestHerbivoreEat()
    {
        String[] sources = {"bamboo"};
        Herbivore panda = new Herbivore("herbivore", "panda", "p", sources, 3, 10, 15, 2, 15, 40, 3);
        Vegetable bamboo = new Vegetable("vegetable", "bamboo", "b", null, 2, 10, 15, 2, 15, 40, 3);
        
        Grid myGrid = new Grid(2,2);
        
        myGrid.getGrid().get(0).get(0).setSpeciesInstanceAnimal(panda.getSpecies().get(0));
        myGrid.getGrid().get(0).get(0).setIsOccupiedAnimal(true);
        myGrid.getGrid().get(0).get(0).setAnimalSymbol(panda.getAnimalSymbol());
        panda.getSpecies().get(0).setCell(myGrid.getGrid().get(0).get(0));
        
        myGrid.getGrid().get(0).get(0).setSpeciesInstancePlant(bamboo.getSpecies().get(0)); //cell (0,0) has food for panda
        myGrid.getGrid().get(0).get(0).setIsOccupiedPlant(true);
        myGrid.getGrid().get(0).get(0).setPlantSymbol(bamboo.getPlantSymbol());
        bamboo.getSpecies().get(0).setCell(myGrid.getGrid().get(0).get(0));
        
        myGrid.getGrid().get(0).get(1).setSpeciesInstanceAnimal(panda.getSpecies().get(1)); //cell (0,1) does not have food for panda
        myGrid.getGrid().get(0).get(1).setIsOccupiedAnimal(true);
        myGrid.getGrid().get(0).get(1).setAnimalSymbol(panda.getAnimalSymbol());
        panda.getSpecies().get(1).setCell(myGrid.getGrid().get(0).get(1));
        
        //herbivore has food it can eat
        assertTrue("herbivore eat is not working correctly",(panda.herbivoreEat(myGrid, panda.getSpecies().get(0))));
        
        //herbivore does not have food it can eat
        assertTrue("herbivore eat is not working correctly",!(panda.herbivoreEat(myGrid, panda.getSpecies().get(1))));
        
        myGrid.getGrid().get(1).get(0).setSpeciesInstanceAnimal(panda.getSpecies().get(2));
        myGrid.getGrid().get(1).get(0).setIsOccupiedAnimal(true);
        myGrid.getGrid().get(1).get(0).setAnimalSymbol(panda.getAnimalSymbol());
        panda.getSpecies().get(2).setCell(myGrid.getGrid().get(1).get(0));
        
        bamboo.getSpecies().get(1).setEnergyLevel(30);
        myGrid.getGrid().get(1).get(0).setSpeciesInstancePlant(bamboo.getSpecies().get(1)); //cell (1,0) has food for panda
        myGrid.getGrid().get(1).get(0).setIsOccupiedPlant(true);
        myGrid.getGrid().get(1).get(0).setPlantSymbol(bamboo.getPlantSymbol());
        bamboo.getSpecies().get(1).setCell(myGrid.getGrid().get(1).get(0));
        
        //herbivore eats food but is now above max energy (check variables before and after)
        assertTrue("herbivore eat is not working correctly",(panda.herbivoreEat(myGrid, panda.getSpecies().get(2))) && (panda.getSpecies().get(2).getEnergyLevel() == 40));
        //energy level would have been over max, so energy level should have been set to max
    }
    
    /**
     * Tests herbivore's move method. Makes sure that the variables are being updated correctly in the method
     */
    @Test
    public void TestHerbivoreMove()
    {
        Herbivore panda = new Herbivore("herbivore", "panda", "p", null, 1, 10, 15, 2, 15, 40, 3);
        
        Grid myGrid = new Grid(2,2);
        
        myGrid.getGrid().get(0).get(0).setSpeciesInstanceAnimal(panda.getSpecies().get(0)); //panda has three other spaces to move to
        myGrid.getGrid().get(0).get(0).setIsOccupiedAnimal(true);
        myGrid.getGrid().get(0).get(0).setAnimalSymbol(panda.getAnimalSymbol());
        panda.getSpecies().get(0).setCell(myGrid.getGrid().get(0).get(0));
        
        panda.herbivoreMove(myGrid, panda.getSpecies().get(0));
        //can move (instance's cell changes)
        assertTrue("herbivore move is not working correctly",(panda.getSpecies().get(0).getCell() != myGrid.getGrid().get(0).get(0))&&((panda.getSpecies().get(0).getCell() == myGrid.getGrid().get(0).get(1))) || (panda.getSpecies().get(0).getCell() == myGrid.getGrid().get(1).get(0)) || (panda.getSpecies().get(0).getCell() == myGrid.getGrid().get(1).get(1))); 
        //panda's should be in one of the other three cells in the grid, and not in its previous cell
        
        Herbivore giraffe = new Herbivore("herbivore", "giraffe", "g", null, 4, 10, 15, 2, 15, 40, 3);
        
        Grid myGrid2 = new Grid(2,2);
        
        myGrid2.getGrid().get(0).get(0).setSpeciesInstanceAnimal(giraffe.getSpecies().get(0)); //fill all of grid's four cells with giraffes
        myGrid2.getGrid().get(0).get(0).setIsOccupiedAnimal(true);
        myGrid2.getGrid().get(0).get(0).setAnimalSymbol(giraffe.getAnimalSymbol());
        giraffe.getSpecies().get(0).setCell(myGrid2.getGrid().get(0).get(0));
        
        myGrid2.getGrid().get(0).get(1).setSpeciesInstanceAnimal(giraffe.getSpecies().get(1)); 
        myGrid2.getGrid().get(0).get(1).setIsOccupiedAnimal(true);
        myGrid2.getGrid().get(0).get(1).setAnimalSymbol(giraffe.getAnimalSymbol());
        giraffe.getSpecies().get(1).setCell(myGrid2.getGrid().get(0).get(1));
        
        myGrid2.getGrid().get(1).get(0).setSpeciesInstanceAnimal(giraffe.getSpecies().get(2)); 
        myGrid2.getGrid().get(1).get(0).setIsOccupiedAnimal(true);
        myGrid2.getGrid().get(1).get(0).setAnimalSymbol(giraffe.getAnimalSymbol());
        giraffe.getSpecies().get(2).setCell(myGrid2.getGrid().get(1).get(0));
        
        myGrid2.getGrid().get(1).get(1).setSpeciesInstanceAnimal(giraffe.getSpecies().get(3)); 
        myGrid2.getGrid().get(1).get(1).setIsOccupiedAnimal(true);
        myGrid2.getGrid().get(1).get(1).setAnimalSymbol(giraffe.getAnimalSymbol());
        giraffe.getSpecies().get(3).setCell(myGrid2.getGrid().get(1).get(1));
        
        giraffe.herbivoreMove(myGrid2, giraffe.getSpecies().get(1));
        //has nowhere to move (instance's cell stays the same)
        assertTrue("herbivore move is not working correctly",(giraffe.getSpecies().get(1).getCell() == myGrid2.getGrid().get(0).get(1)) && ((giraffe.getSpecies().get(1).getCell() != myGrid2.getGrid().get(0).get(0))||(giraffe.getSpecies().get(1).getCell() != myGrid2.getGrid().get(1).get(0))||(giraffe.getSpecies().get(1).getCell() != myGrid2.getGrid().get(1).get(1))));
        //giraffe is still in its own cell and has not moved to any of the other cells
    }
}
