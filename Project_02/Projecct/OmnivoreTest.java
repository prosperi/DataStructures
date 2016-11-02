
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class OmnivoreTest tests class Omnivore.
 *
 * @version (10/15/2016)
 */
public class OmnivoreTest
{
    /**
     * Default constructor for test class OmnivoreTest
     */
    public OmnivoreTest()
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
     * Tests omnivore's death method. Makes sure it returns the right boolean each time, depending on the instance's current state
     */
    @Test
    public void TestOmnivoreDeath()
    {
        Omnivore penguin = new Omnivore("omnivore", "penguin", "p", null, 1, 10, 15, 2, 15, 40, 3);
        
        Cell myCell = new Cell(0,0);
        
        penguin.getSpecies().get(0).setCell(myCell);
        //energy and age are at acceptable levels (instance should not die)
        assertTrue("omnivore death is not working correctly",!(penguin.omnivoreDeath(penguin.getSpecies().get(0))));
        
        penguin.getSpecies().get(0).setEnergyLevel(0);
        //energy is 0 (instance should die)
        assertTrue("omnivore death is not working correctly",(penguin.omnivoreDeath(penguin.getSpecies().get(0))));
        
        penguin.getSpecies().get(0).setEnergyLevel(5);
        penguin.getSpecies().get(0).setAge(50);
        //age is too high (instance should die)
        assertTrue("omnivore death is not working correctly",(penguin.omnivoreDeath(penguin.getSpecies().get(0))));
    }
    
    /**
     * Tests omnivore's birth method. Makes sure it returns the right boolean each time, depending on the instance's curent state
     */
    @Test
    public void TestOmnivoreBirth()
    {
        Omnivore penguin = new Omnivore("omnivore", "penguin", "p", null, 1, 10, 15, 2, 15, 40, 3);
        
        Grid myGrid = new Grid(2,2);
        
        myGrid.getGrid().get(0).get(0).setSpeciesInstanceAnimal(penguin.getSpecies().get(0));
        myGrid.getGrid().get(0).get(0).setIsOccupiedAnimal(true);
        myGrid.getGrid().get(0).get(0).setAnimalSymbol(penguin.getAnimalSymbol());
        penguin.getSpecies().get(0).setCell(myGrid.getGrid().get(0).get(0));

        penguin.getSpecies().get(0).setEnergyLevel(20);
        //instance has enough energy and room (instance should give birth and penguin size should increase by one instance)
        assertTrue("omnivore birth is not working correctly",((penguin.omnivoreBirth(myGrid, penguin.getSpecies().get(0))) && (penguin.getSpecies().size() == 2)));
        
        //instance doesn't have enough energy (instance should not give birth)
        assertTrue("omnivore birth is not working correctly", !(penguin.omnivoreBirth(myGrid, penguin.getSpecies().get(0))));
        
        penguin.getSpecies().get(0).setEnergyLevel(30); //fill up rest of grid
        penguin.omnivoreBirth(myGrid, penguin.getSpecies().get(0));
        penguin.getSpecies().get(0).setEnergyLevel(30);
        penguin.omnivoreBirth(myGrid, penguin.getSpecies().get(0));
        penguin.getSpecies().get(0).setEnergyLevel(30);
        //instance has enough energy but no room (instance should not give birth, penguin population should be four)
        assertTrue("omnivore birth is not working correctly",(!(penguin.omnivoreBirth(myGrid, penguin.getSpecies().get(0))) && (penguin.getSpecies().size() == 4)));
    }
    
    /**
     * Tests omnivore's eat method. Makes sure it returns the right boolean each time, depending on the instance's current state
     */
    @Test
    public void TestOmnivoreEat()
    {
        String[] sources = {"fish","seaweed"};
        Omnivore penguin = new Omnivore("omnivore", "penguin", "p", sources, 1, 10, 15, 2, 15, 40, 3);
        Herbivore fish = new Herbivore("herbivore", "fish", "f", null, 2, 10, 15, 2, 15, 40, 3);
        Vegetable seaweed = new Vegetable("vegetable", "seaweed", "s", null, 1, 10, 15, 2, 15, 40, 3);
        
        Grid myGrid = new Grid(2,2);
        
        myGrid.getGrid().get(0).get(0).setSpeciesInstanceAnimal(penguin.getSpecies().get(0));
        myGrid.getGrid().get(0).get(0).setIsOccupiedAnimal(true);
        myGrid.getGrid().get(0).get(0).setAnimalSymbol(penguin.getAnimalSymbol());
        penguin.getSpecies().get(0).setCell(myGrid.getGrid().get(0).get(0));
        
        //omnivore does not have food it can eat
        assertTrue("omnivore eat is not working correctly",!(penguin.omnivoreEat(myGrid, penguin.getSpecies().get(0))));
        
        myGrid.getGrid().get(0).get(0).setSpeciesInstancePlant(seaweed.getSpecies().get(0)); //cell (0,0) now has food for penguin (plant)
        myGrid.getGrid().get(0).get(0).setIsOccupiedPlant(true);
        myGrid.getGrid().get(0).get(0).setPlantSymbol(seaweed.getPlantSymbol());
        seaweed.getSpecies().get(0).setCell(myGrid.getGrid().get(0).get(0));
        
        myGrid.getGrid().get(0).get(1).setSpeciesInstanceAnimal(fish.getSpecies().get(0)); //cell (0,1) now has food for penguin (animal)
        myGrid.getGrid().get(0).get(1).setIsOccupiedAnimal(true);
        myGrid.getGrid().get(0).get(1).setAnimalSymbol(fish.getAnimalSymbol());
        fish.getSpecies().get(0).setCell(myGrid.getGrid().get(0).get(1));
        
        //omnivore has food it can eat and should eat the plant in its own cell
        assertTrue("omnivore eat is not working correctly",(penguin.omnivoreEat(myGrid, penguin.getSpecies().get(0)))&&(penguin.getSpecies().get(0).getCell() == myGrid.getGrid().get(0).get(0)));
        
        //omnivore has food it can eat and should move to the food's cell and eat it
        assertTrue("omnivore eat is not working correctly",(penguin.omnivoreEat(myGrid, penguin.getSpecies().get(0)))&&(penguin.getSpecies().get(0).getCell() == myGrid.getGrid().get(0).get(1)));
        
        myGrid.getGrid().get(1).get(1).setSpeciesInstanceAnimal(fish.getSpecies().get(1)); //cell (1,1) now has food for penguin
        myGrid.getGrid().get(1).get(1).setIsOccupiedAnimal(true);
        myGrid.getGrid().get(1).get(1).setAnimalSymbol(fish.getAnimalSymbol());
        fish.getSpecies().get(1).setCell(myGrid.getGrid().get(1).get(1));
        fish.getSpecies().get(1).setEnergyLevel(30); //if penguin eats this fish, its energy will be over max
        
        //omnivore eats food but is now above max energy (check variables before and after)
        assertTrue("omnivore eat is not working correctly",(penguin.omnivoreEat(myGrid, penguin.getSpecies().get(0))) && (penguin.getSpecies().get(0).getEnergyLevel() == 40));
        //energy level would have been over max, so energy level should have been set to max
    }
    
    /**
     * Tests omnivore's move method. Makes sure that the variables are being updated correctly in the method
     */
    @Test
    public void TestOmnivoreMove()
    {
        Omnivore penguin = new Omnivore("omnivore", "penguin", "d", null, 1, 10, 15, 2, 15, 40, 3);
        
        Grid myGrid = new Grid(2,2);
        
        myGrid.getGrid().get(0).get(0).setSpeciesInstanceAnimal(penguin.getSpecies().get(0)); //penguin has three other spaces to move to
        myGrid.getGrid().get(0).get(0).setIsOccupiedAnimal(true);
        myGrid.getGrid().get(0).get(0).setAnimalSymbol(penguin.getAnimalSymbol());
        penguin.getSpecies().get(0).setCell(myGrid.getGrid().get(0).get(0));
        
        penguin.omnivoreMove(myGrid, penguin.getSpecies().get(0));
        //can move (instance's cell changes)
        assertTrue("omnivore move is not working correctly",(penguin.getSpecies().get(0).getCell() != myGrid.getGrid().get(0).get(0))&&((penguin.getSpecies().get(0).getCell() == myGrid.getGrid().get(0).get(1))) || (penguin.getSpecies().get(0).getCell() == myGrid.getGrid().get(1).get(0)) || (penguin.getSpecies().get(0).getCell() == myGrid.getGrid().get(1).get(1))); 
        //penguin's should be in one of the other three cells in the grid, and not in its previous cell
        
        Omnivore gorilla = new Omnivore("omnivore", "gorilla", "t", null, 4, 10, 15, 2, 15, 40, 3);
        
        Grid myGrid2 = new Grid(2,2);
        
        myGrid2.getGrid().get(0).get(0).setSpeciesInstanceAnimal(gorilla.getSpecies().get(0)); //fill all of grid's four cells with gorillas
        myGrid2.getGrid().get(0).get(0).setIsOccupiedAnimal(true);
        myGrid2.getGrid().get(0).get(0).setAnimalSymbol(gorilla.getAnimalSymbol());
        gorilla.getSpecies().get(0).setCell(myGrid2.getGrid().get(0).get(0));
        
        myGrid2.getGrid().get(0).get(1).setSpeciesInstanceAnimal(gorilla.getSpecies().get(1)); 
        myGrid2.getGrid().get(0).get(1).setIsOccupiedAnimal(true);
        myGrid2.getGrid().get(0).get(1).setAnimalSymbol(gorilla.getAnimalSymbol());
        gorilla.getSpecies().get(1).setCell(myGrid2.getGrid().get(0).get(1));
        
        myGrid2.getGrid().get(1).get(0).setSpeciesInstanceAnimal(gorilla.getSpecies().get(2)); 
        myGrid2.getGrid().get(1).get(0).setIsOccupiedAnimal(true);
        myGrid2.getGrid().get(1).get(0).setAnimalSymbol(gorilla.getAnimalSymbol());
        gorilla.getSpecies().get(2).setCell(myGrid2.getGrid().get(1).get(0));
        
        myGrid2.getGrid().get(1).get(1).setSpeciesInstanceAnimal(gorilla.getSpecies().get(3)); 
        myGrid2.getGrid().get(1).get(1).setIsOccupiedAnimal(true);
        myGrid2.getGrid().get(1).get(1).setAnimalSymbol(gorilla.getAnimalSymbol());
        gorilla.getSpecies().get(3).setCell(myGrid2.getGrid().get(1).get(1));
        
        gorilla.omnivoreMove(myGrid2, gorilla.getSpecies().get(1));
        //has nowhere to move (instance's cell stays the same)
        assertTrue("omnivore move is not working correctly",(gorilla.getSpecies().get(1).getCell() == myGrid2.getGrid().get(0).get(1)) && ((gorilla.getSpecies().get(1).getCell() != myGrid2.getGrid().get(0).get(0))||(gorilla.getSpecies().get(1).getCell() != myGrid2.getGrid().get(1).get(0))||(gorilla.getSpecies().get(1).getCell() != myGrid2.getGrid().get(1).get(1))));
        //gorilla is still in its own cell and has not moved to any of the other cells
    }
}
