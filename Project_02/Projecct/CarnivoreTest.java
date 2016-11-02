
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class CarnivoreTest tests class Carnivore.
 *
 * @version (10/15/2016)
 */
public class CarnivoreTest
{
    /**
     * Default constructor for test class CarnivoreTest
     */
    public CarnivoreTest()
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
     * Tests carnivore's death method. Makes sure it returns the right boolean each time, depending on the instance's current state
     */
    @Test
    public void TestCarnivoreDeath()
    {
        Carnivore dolphin = new Carnivore("carnivore", "dolphin", "d", null, 1, 10, 15, 2, 15, 40, 3);
        
        Cell myCell = new Cell(0,0);
        
        dolphin.getSpecies().get(0).setCell(myCell);
        //energy and age are at acceptable levels (instance should not die)
        assertTrue("carnivore death is not working correctly",!(dolphin.carnivoreDeath(dolphin.getSpecies().get(0))));
        
        dolphin.getSpecies().get(0).setEnergyLevel(0);
        //energy is 0 (instance should die)
        assertTrue("carnivore death is not working correctly",(dolphin.carnivoreDeath(dolphin.getSpecies().get(0))));
        
        dolphin.getSpecies().get(0).setEnergyLevel(5);
        dolphin.getSpecies().get(0).setAge(50);
        //age is too high (instance should die)
        assertTrue("carnivore death is not working correctly",(dolphin.carnivoreDeath(dolphin.getSpecies().get(0))));
    }
    
    /**
     * Tests carnivore's birth method. Makes sure it returns the right boolean each time, depending on the instance's curent state
     */
    @Test
    public void TestCarnivoreBirth()
    {
        Carnivore dolphin = new Carnivore("carnivore", "dolphin", "d", null, 1, 10, 15, 2, 15, 40, 3);
        
        Grid myGrid = new Grid(2,2);
        
        myGrid.getGrid().get(0).get(0).setSpeciesInstanceAnimal(dolphin.getSpecies().get(0));
        myGrid.getGrid().get(0).get(0).setIsOccupiedAnimal(true);
        myGrid.getGrid().get(0).get(0).setAnimalSymbol(dolphin.getAnimalSymbol());
        dolphin.getSpecies().get(0).setCell(myGrid.getGrid().get(0).get(0));

        dolphin.getSpecies().get(0).setEnergyLevel(20);
        //instance has enough energy and room (instance should give birth and dolphin size should increase by one instance)
        assertTrue("carnivore birth is not working correctly",((dolphin.carnivoreBirth(myGrid, dolphin.getSpecies().get(0))) && (dolphin.getSpecies().size() == 2)));
        
        //instance doesn't have enough energy (instance should not give birth)
        assertTrue("carnivore birth is not working correctly", !(dolphin.carnivoreBirth(myGrid, dolphin.getSpecies().get(0))));
        
        dolphin.getSpecies().get(0).setEnergyLevel(30); //fill up rest of grid
        dolphin.carnivoreBirth(myGrid, dolphin.getSpecies().get(0));
        dolphin.getSpecies().get(0).setEnergyLevel(30);
        dolphin.carnivoreBirth(myGrid, dolphin.getSpecies().get(0));
        dolphin.getSpecies().get(0).setEnergyLevel(30);
        //instance has enough energy but no room (instance should not give birth, dolphin population should be four)
        assertTrue("carnivore birth is not working correctly",(!(dolphin.carnivoreBirth(myGrid, dolphin.getSpecies().get(0))) && (dolphin.getSpecies().size() == 4)));
    }
    
    /**
     * Tests carnivore's eat method. Makes sure it returns the right boolean each time, depending on the instance's current state
     */
    @Test
    public void TestCarnivoreEat()
    {
        String[] sources = {"fish"};
        Carnivore dolphin = new Carnivore("carnivore", "dolphin", "d", sources, 1, 10, 15, 2, 15, 40, 3);
        Herbivore fish = new Herbivore("herbivore", "fish", "f", null, 2, 10, 15, 2, 15, 40, 3);
        
        Grid myGrid = new Grid(2,2);
        
        myGrid.getGrid().get(0).get(0).setSpeciesInstanceAnimal(dolphin.getSpecies().get(0));
        myGrid.getGrid().get(0).get(0).setIsOccupiedAnimal(true);
        myGrid.getGrid().get(0).get(0).setAnimalSymbol(dolphin.getAnimalSymbol());
        dolphin.getSpecies().get(0).setCell(myGrid.getGrid().get(0).get(0));
        
        //carnivore does not have food it can eat
        assertTrue("carnivore eat is not working correctly",!(dolphin.carnivoreEat(myGrid, dolphin.getSpecies().get(0))));
        
        myGrid.getGrid().get(0).get(1).setSpeciesInstanceAnimal(fish.getSpecies().get(0)); //cell (0,1) now has food for dolphin
        myGrid.getGrid().get(0).get(1).setIsOccupiedAnimal(true);
        myGrid.getGrid().get(0).get(1).setAnimalSymbol(fish.getAnimalSymbol());
        fish.getSpecies().get(0).setCell(myGrid.getGrid().get(0).get(1));
        
        //carnivore has food it can eat and it moves to eat it
        assertTrue("carnivore eat is not working correctly",(dolphin.carnivoreEat(myGrid, dolphin.getSpecies().get(0)))&&(dolphin.getSpecies().get(0).getCell() == myGrid.getGrid().get(0).get(1)));
        
        myGrid.getGrid().get(1).get(1).setSpeciesInstanceAnimal(fish.getSpecies().get(1)); //cell (1,1) now has food for dolphin
        myGrid.getGrid().get(1).get(1).setIsOccupiedAnimal(true);
        myGrid.getGrid().get(1).get(1).setAnimalSymbol(fish.getAnimalSymbol());
        fish.getSpecies().get(1).setCell(myGrid.getGrid().get(1).get(1));
        fish.getSpecies().get(1).setEnergyLevel(30); //if dolphin eats this fish, its energy will be over max
        
        //carnivore eats food but is now above max energy (check variables before and after)
        assertTrue("carnivore eat is not working correctly",(dolphin.carnivoreEat(myGrid, dolphin.getSpecies().get(0))) && (dolphin.getSpecies().get(0).getEnergyLevel() == 40));
        //energy level would have been over max, so energy level should have been set to max
    }
    
    /**
     * Tests carnivore's move method. Makes sure that the variables are being updated correctly in the method
     */
    @Test
    public void TestCarnivoreMove()
    {
        Carnivore dolphin = new Carnivore("carnivore", "dolphin", "d", null, 1, 10, 15, 2, 15, 40, 3);
        
        Grid myGrid = new Grid(2,2);
        
        myGrid.getGrid().get(0).get(0).setSpeciesInstanceAnimal(dolphin.getSpecies().get(0)); //dolphin has three other spaces to move to
        myGrid.getGrid().get(0).get(0).setIsOccupiedAnimal(true);
        myGrid.getGrid().get(0).get(0).setAnimalSymbol(dolphin.getAnimalSymbol());
        dolphin.getSpecies().get(0).setCell(myGrid.getGrid().get(0).get(0));
        
        dolphin.carnivoreMove(myGrid, dolphin.getSpecies().get(0));
        //can move (instance's cell changes)
        assertTrue("carnivore move is not working correctly",(dolphin.getSpecies().get(0).getCell() != myGrid.getGrid().get(0).get(0))&&((dolphin.getSpecies().get(0).getCell() == myGrid.getGrid().get(0).get(1))) || (dolphin.getSpecies().get(0).getCell() == myGrid.getGrid().get(1).get(0)) || (dolphin.getSpecies().get(0).getCell() == myGrid.getGrid().get(1).get(1))); 
        //dolphin's should be in one of the other three cells in the grid, and not in its previous cell
        
        Carnivore tiger = new Carnivore("carnivore", "tiger", "t", null, 4, 10, 15, 2, 15, 40, 3);
        
        Grid myGrid2 = new Grid(2,2);
        
        myGrid2.getGrid().get(0).get(0).setSpeciesInstanceAnimal(tiger.getSpecies().get(0)); //fill all of grid's four cells with tigers
        myGrid2.getGrid().get(0).get(0).setIsOccupiedAnimal(true);
        myGrid2.getGrid().get(0).get(0).setAnimalSymbol(tiger.getAnimalSymbol());
        tiger.getSpecies().get(0).setCell(myGrid2.getGrid().get(0).get(0));
        
        myGrid2.getGrid().get(0).get(1).setSpeciesInstanceAnimal(tiger.getSpecies().get(1)); 
        myGrid2.getGrid().get(0).get(1).setIsOccupiedAnimal(true);
        myGrid2.getGrid().get(0).get(1).setAnimalSymbol(tiger.getAnimalSymbol());
        tiger.getSpecies().get(1).setCell(myGrid2.getGrid().get(0).get(1));
        
        myGrid2.getGrid().get(1).get(0).setSpeciesInstanceAnimal(tiger.getSpecies().get(2)); 
        myGrid2.getGrid().get(1).get(0).setIsOccupiedAnimal(true);
        myGrid2.getGrid().get(1).get(0).setAnimalSymbol(tiger.getAnimalSymbol());
        tiger.getSpecies().get(2).setCell(myGrid2.getGrid().get(1).get(0));
        
        myGrid2.getGrid().get(1).get(1).setSpeciesInstanceAnimal(tiger.getSpecies().get(3)); 
        myGrid2.getGrid().get(1).get(1).setIsOccupiedAnimal(true);
        myGrid2.getGrid().get(1).get(1).setAnimalSymbol(tiger.getAnimalSymbol());
        tiger.getSpecies().get(3).setCell(myGrid2.getGrid().get(1).get(1));
        
        tiger.carnivoreMove(myGrid2, tiger.getSpecies().get(1));
        //has nowhere to move (instance's cell stays the same)
        assertTrue("carnivore move is not working correctly",(tiger.getSpecies().get(1).getCell() == myGrid2.getGrid().get(0).get(1)) && ((tiger.getSpecies().get(1).getCell() != myGrid2.getGrid().get(0).get(0))||(tiger.getSpecies().get(1).getCell() != myGrid2.getGrid().get(1).get(0))||(tiger.getSpecies().get(1).getCell() != myGrid2.getGrid().get(1).get(1))));
        //tiger is still in its own cell and has not moved to any of the other cells
    }
}
