
import java.util.*;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class GridTest tests class Grid.
 *
 * @version (10/15/2016)
 */
public class GridTest
{
    /**
     * Default constructor for test class GridTest
     */
    public GridTest()
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
     * Tests that fillGrid puts elements in random positions in the grid
     */
    @Test
    public void TestFillGrid()
    {
        Grid myGrid = new Grid(200,200);
        LinkedList<ArrayList<Species>> allSpecies = new LinkedList<ArrayList<Species>>();
        ArrayList<Species> allFruitSpecies = new ArrayList<Species>();
        allFruitSpecies.add(new Fruit("fruit", "banana", "b", null, 2, 0, 0, 0, 0, 0, 0)); 
        allSpecies.add(allFruitSpecies);

        myGrid.fillGrid(allSpecies);
        int rowPos1 = allSpecies.get(0).get(0).getSpecies().get(0).getCell().getRowPos();
        int colPos1 = allSpecies.get(0).get(0).getSpecies().get(0).getCell().getColPos();

        int rowPos2 = allSpecies.get(0).get(0).getSpecies().get(1).getCell().getRowPos();
        int colPos2 = allSpecies.get(0).get(0).getSpecies().get(1).getCell().getColPos();

        //check if fillGrid just puts elements in one spot
        assertTrue("fillGrid does not work correctly",((rowPos1 != rowPos2) && (colPos1 != colPos2)));

        Grid myGrid2 = new Grid(200,200);
        LinkedList<ArrayList<Species>> allSpecies2 = new LinkedList<ArrayList<Species>>();
        ArrayList<Species> allFruitSpecies2 = new ArrayList<Species>();
        allFruitSpecies2.add(new Fruit("fruit", "banana", "b", null, 2, 0, 0, 0, 0, 0, 0)); 
        allSpecies2.add(allFruitSpecies2);

        myGrid2.fillGrid(allSpecies2);
        int rowPos3 = allSpecies2.get(0).get(0).getSpecies().get(0).getCell().getRowPos();
        int colPos3 = allSpecies2.get(0).get(0).getSpecies().get(0).getCell().getColPos();

        int rowPos4 = allSpecies2.get(0).get(0).getSpecies().get(1).getCell().getRowPos();
        int colPos4 = allSpecies2.get(0).get(0).getSpecies().get(1).getCell().getColPos();

        //check that fillGrid places elements in different spots when it is called on different ArrayLists
        if(rowPos1 == rowPos3 && colPos1 == colPos3)
        {
            assertTrue("fillGrid does not work correctly",((rowPos2 != rowPos4) && (colPos2 != colPos4)));
        }
        else
        {
            assertTrue("fillGrid does not work correctly",((rowPos1 != rowPos3) && (colPos1 != colPos3)));
        }
    }

    /**
     * Tests that fillGrid fills the grid with the correct number of instances, with the correct symbols
     * for both plants and animals
     */
    @Test
    public void TestFillGrid2()
    {
        Grid myGrid = new Grid(10,10);
        LinkedList<ArrayList<Species>> allSpecies = new LinkedList<ArrayList<Species>>();
        ArrayList<Species> allVegetableSpecies = new ArrayList<Species>();
        ArrayList<Species> allHerbivoreSpecies = new ArrayList<Species>();
        allVegetableSpecies.add(new Vegetable("vegetable", "bamboo", "b", null, 9, 0, 0, 0, 0, 0, 0)); 
        allHerbivoreSpecies.add(new Herbivore("herbivore", "panda", "p", null, 9, 0, 0, 0, 0, 0, 0)); 
        allSpecies.add(allVegetableSpecies);
        allSpecies.add(allHerbivoreSpecies);
        myGrid.fillGrid(allSpecies);

        int bambooCounter = 0;
        int pandaCounter = 0;

        for(int i = 0; i < 10; i++)
        {
            for(int j = 0; j < 10; j++)
            {
                String plantSymbol = myGrid.getGrid().get(i).get(j).getPlantSymbol();
                String animalSymbol = myGrid.getGrid().get(i).get(j).getAnimalSymbol();

                if(plantSymbol.equals("b"))
                {
                    bambooCounter++;
                }
                if(animalSymbol.equals("p"))
                {
                    pandaCounter++;
                }
            }
        }

        assertTrue("Grid did not fill correctly",((bambooCounter == 9) && (pandaCounter == 9)));
    }

    /**
     * Tests that surroundingEmptyCells() returns the right cells when there is no place to move (for
     * both plants and animals)
     */
    @Test
    public void TestSurroundingEmptyCells()
    {
        Grid myGrid = new Grid(2,2); //construct a 4 cell grid

        Vegetable bamboo = new Vegetable("vegetable", "bamboo", "b", null, 4, 0, 0, 0, 0, 0, 0); 

        //manually fill grid entirely with bamboo
        myGrid.getGrid().get(0).get(0).setSpeciesInstancePlant(bamboo.getSpecies().get(0));
        myGrid.getGrid().get(0).get(0).setIsOccupiedPlant(true);
        myGrid.getGrid().get(0).get(0).setPlantSymbol(bamboo.getPlantSymbol());
        bamboo.getSpecies().get(0).setCell(myGrid.getGrid().get(0).get(0));

        myGrid.getGrid().get(0).get(1).setSpeciesInstancePlant(bamboo.getSpecies().get(1));
        myGrid.getGrid().get(0).get(1).setIsOccupiedPlant(true);
        myGrid.getGrid().get(0).get(1).setPlantSymbol(bamboo.getPlantSymbol());
        bamboo.getSpecies().get(1).setCell(myGrid.getGrid().get(0).get(1));

        myGrid.getGrid().get(1).get(0).setSpeciesInstancePlant(bamboo.getSpecies().get(2));
        myGrid.getGrid().get(1).get(0).setIsOccupiedPlant(true);
        myGrid.getGrid().get(1).get(0).setPlantSymbol(bamboo.getPlantSymbol());
        bamboo.getSpecies().get(2).setCell(myGrid.getGrid().get(1).get(0));

        myGrid.getGrid().get(1).get(1).setSpeciesInstancePlant(bamboo.getSpecies().get(3));
        myGrid.getGrid().get(1).get(1).setIsOccupiedPlant(true);
        myGrid.getGrid().get(1).get(1).setPlantSymbol(bamboo.getPlantSymbol());
        bamboo.getSpecies().get(3).setCell(myGrid.getGrid().get(1).get(1));

        ArrayList<Cell> arr1 = myGrid.surroundingEmptyCells(bamboo.getSpecies().get(0)); //should return empty ArrayList
        ArrayList<Cell> test1 = new ArrayList<Cell>();

        assertTrue("surroundingEmptyCells() does not work correctly",(arr1.equals(test1))); 

        //repeat test with animals
        //manually fill grid entirely with pandas
        Herbivore panda = new Herbivore("herbivore", "panda", "p", null, 4, 0, 0, 0, 0, 0, 0); 

        myGrid.getGrid().get(0).get(0).setSpeciesInstanceAnimal(panda.getSpecies().get(0));
        myGrid.getGrid().get(0).get(0).setIsOccupiedAnimal(true);
        myGrid.getGrid().get(0).get(0).setAnimalSymbol(panda.getAnimalSymbol());
        panda.getSpecies().get(0).setCell(myGrid.getGrid().get(0).get(0));

        myGrid.getGrid().get(0).get(1).setSpeciesInstanceAnimal(panda.getSpecies().get(1));
        myGrid.getGrid().get(0).get(1).setIsOccupiedAnimal(true);
        myGrid.getGrid().get(0).get(1).setAnimalSymbol(panda.getAnimalSymbol());
        panda.getSpecies().get(1).setCell(myGrid.getGrid().get(0).get(1));

        myGrid.getGrid().get(1).get(0).setSpeciesInstanceAnimal(panda.getSpecies().get(2));
        myGrid.getGrid().get(1).get(0).setIsOccupiedAnimal(true);
        myGrid.getGrid().get(1).get(0).setAnimalSymbol(panda.getAnimalSymbol());
        panda.getSpecies().get(2).setCell(myGrid.getGrid().get(1).get(0));

        myGrid.getGrid().get(1).get(1).setSpeciesInstanceAnimal(panda.getSpecies().get(3));
        myGrid.getGrid().get(1).get(1).setIsOccupiedAnimal(true);
        myGrid.getGrid().get(1).get(1).setAnimalSymbol(panda.getAnimalSymbol());
        panda.getSpecies().get(3).setCell(myGrid.getGrid().get(1).get(1));

        ArrayList<Cell> arr2= myGrid.surroundingEmptyCells(bamboo.getSpecies().get(3)); //should return empty ArrayList

        assertTrue("surroundingEmptyCells() does not work correctly",(arr2.equals(test1)));
    }

    /**
     * Tests that surroundingEmptyCells() returns the correct cells no matter the instance's place on the grid or whether
     * it is a plant or animal
     */
    @Test
    public void TestSurroundingEmptyCells2()
    {
        Grid myGrid = new Grid(3,3); //construct 9 cell grid

        Vegetable bamboo = new Vegetable("vegetable", "bamboo", "b", null, 4, 0, 0, 0, 0, 0, 0); 
        Herbivore panda = new Herbivore("herbivore", "panda", "p", null, 5, 0, 0, 0, 0, 0, 0); 

        //manually fill grid
        myGrid.getGrid().get(0).get(0).setSpeciesInstanceAnimal(panda.getSpecies().get(0));
        myGrid.getGrid().get(0).get(0).setIsOccupiedAnimal(true);
        myGrid.getGrid().get(0).get(0).setAnimalSymbol(panda.getAnimalSymbol());
        panda.getSpecies().get(0).setCell(myGrid.getGrid().get(0).get(0));

        myGrid.getGrid().get(0).get(1).setSpeciesInstancePlant(bamboo.getSpecies().get(0));
        myGrid.getGrid().get(0).get(1).setIsOccupiedPlant(true);
        myGrid.getGrid().get(0).get(1).setPlantSymbol(bamboo.getPlantSymbol());
        bamboo.getSpecies().get(0).setCell(myGrid.getGrid().get(0).get(1));

        myGrid.getGrid().get(0).get(2).setSpeciesInstanceAnimal(panda.getSpecies().get(1));
        myGrid.getGrid().get(0).get(2).setIsOccupiedAnimal(true);
        myGrid.getGrid().get(0).get(2).setAnimalSymbol(panda.getAnimalSymbol());
        panda.getSpecies().get(1).setCell(myGrid.getGrid().get(0).get(2));

        myGrid.getGrid().get(1).get(0).setSpeciesInstancePlant(bamboo.getSpecies().get(1));
        myGrid.getGrid().get(1).get(0).setIsOccupiedPlant(true);
        myGrid.getGrid().get(1).get(0).setPlantSymbol(bamboo.getPlantSymbol());
        bamboo.getSpecies().get(1).setCell(myGrid.getGrid().get(1).get(0));

        myGrid.getGrid().get(1).get(1).setSpeciesInstanceAnimal(panda.getSpecies().get(2));
        myGrid.getGrid().get(1).get(1).setIsOccupiedAnimal(true);
        myGrid.getGrid().get(1).get(1).setAnimalSymbol(panda.getAnimalSymbol());
        panda.getSpecies().get(2).setCell(myGrid.getGrid().get(1).get(1));

        myGrid.getGrid().get(1).get(2).setSpeciesInstancePlant(bamboo.getSpecies().get(2));
        myGrid.getGrid().get(1).get(2).setIsOccupiedPlant(true);
        myGrid.getGrid().get(1).get(2).setPlantSymbol(bamboo.getPlantSymbol());
        bamboo.getSpecies().get(2).setCell(myGrid.getGrid().get(1).get(2));

        myGrid.getGrid().get(2).get(0).setSpeciesInstanceAnimal(panda.getSpecies().get(3));
        myGrid.getGrid().get(2).get(0).setIsOccupiedAnimal(true);
        myGrid.getGrid().get(2).get(0).setAnimalSymbol(panda.getAnimalSymbol());
        panda.getSpecies().get(3).setCell(myGrid.getGrid().get(2).get(0));

        myGrid.getGrid().get(2).get(1).setSpeciesInstancePlant(bamboo.getSpecies().get(3));
        myGrid.getGrid().get(2).get(1).setIsOccupiedPlant(true);
        myGrid.getGrid().get(2).get(1).setPlantSymbol(bamboo.getPlantSymbol());
        bamboo.getSpecies().get(3).setCell(myGrid.getGrid().get(2).get(1));

        myGrid.getGrid().get(2).get(2).setSpeciesInstanceAnimal(panda.getSpecies().get(4));
        myGrid.getGrid().get(2).get(2).setIsOccupiedAnimal(true);
        myGrid.getGrid().get(2).get(2).setAnimalSymbol(panda.getAnimalSymbol());
        panda.getSpecies().get(4).setCell(myGrid.getGrid().get(2).get(2));

        //begin testing on manually filled grid
        ArrayList<Cell> arr1 = myGrid.surroundingEmptyCells(panda.getSpecies().get(0));
        ArrayList<Cell> test1 = new ArrayList<Cell>();
        test1.add(myGrid.getGrid().get(0).get(1));
        test1.add(myGrid.getGrid().get(1).get(0));

        assertTrue("surroundingEmptyCells() does not work correctly",(arr1.equals(test1)));

        ArrayList<Cell> arr2 = myGrid.surroundingEmptyCells(bamboo.getSpecies().get(0));
        ArrayList<Cell> test2 = new ArrayList<Cell>();
        test2.add(myGrid.getGrid().get(0).get(0));
        test2.add(myGrid.getGrid().get(0).get(2));
        test2.add(myGrid.getGrid().get(1).get(1));

        assertTrue("surroundingEmptyCells() does not work correctly",(arr2.equals(test2)));

        ArrayList<Cell> arr3 = myGrid.surroundingEmptyCells(panda.getSpecies().get(1));
        ArrayList<Cell> test3 = new ArrayList<Cell>();
        test3.add(myGrid.getGrid().get(0).get(1));
        test3.add(myGrid.getGrid().get(1).get(2));

        assertTrue("surroundingEmptyCells() does not work correctly",(arr3.equals(test3)));

        ArrayList<Cell> arr4 = myGrid.surroundingEmptyCells(bamboo.getSpecies().get(1));
        ArrayList<Cell> test4 = new ArrayList<Cell>();
        test4.add(myGrid.getGrid().get(0).get(0));
        test4.add(myGrid.getGrid().get(2).get(0));
        test4.add(myGrid.getGrid().get(1).get(1));

        assertTrue("surroundingEmptyCells() does not work correctly",(arr4.equals(test4)));

        ArrayList<Cell> arr5 = myGrid.surroundingEmptyCells(panda.getSpecies().get(2));
        ArrayList<Cell> test5 = new ArrayList<Cell>();
        test5.add(myGrid.getGrid().get(0).get(1));
        test5.add(myGrid.getGrid().get(1).get(0));
        test5.add(myGrid.getGrid().get(1).get(2));
        test5.add(myGrid.getGrid().get(2).get(1));

        assertTrue("surroundingEmptyCells() does not work correctly",(arr5.equals(test5)));

        ArrayList<Cell> arr6 = myGrid.surroundingEmptyCells(bamboo.getSpecies().get(2));
        ArrayList<Cell> test6 = new ArrayList<Cell>();
        test6.add(myGrid.getGrid().get(0).get(2));
        test6.add(myGrid.getGrid().get(2).get(2));
        test6.add(myGrid.getGrid().get(1).get(1));

        assertTrue("surroundingEmptyCells() does not work correctly",(arr6.equals(test6)));

        ArrayList<Cell> arr7 = myGrid.surroundingEmptyCells(panda.getSpecies().get(3));
        ArrayList<Cell> test7 = new ArrayList<Cell>();
        test7.add(myGrid.getGrid().get(1).get(0));
        test7.add(myGrid.getGrid().get(2).get(1));

        assertTrue("surroundingEmptyCells() does not work correctly",(arr7.equals(test7)));

        ArrayList<Cell> arr8 = myGrid.surroundingEmptyCells(bamboo.getSpecies().get(3));
        ArrayList<Cell> test8 = new ArrayList<Cell>();
        test8.add(myGrid.getGrid().get(2).get(0));
        test8.add(myGrid.getGrid().get(2).get(2));
        test8.add(myGrid.getGrid().get(1).get(1));

        assertTrue("surroundingEmptyCells() does not work correctly",(arr8.equals(test8)));

        ArrayList<Cell> arr9 = myGrid.surroundingEmptyCells(panda.getSpecies().get(4));
        ArrayList<Cell> test9 = new ArrayList<Cell>();
        test9.add(myGrid.getGrid().get(1).get(2));
        test9.add(myGrid.getGrid().get(2).get(1));

        assertTrue("surroundingEmptyCells() does not work correctly",(arr9.equals(test9)));
    }

    /**
     * Tests that surroundingFoodCells() returns an empty ArrayList when there is nothing around the instance to eat,
     * regardless of whether it is a herbivore, carnivore, or omnivore
     */
    @Test
    public void TestSurroundingFoodCells()
    {
        Grid myGrid = new Grid(2,2); //4 cell grid
        //first test herbivore (only looks at current cell)
        
        String[] sources = {"bamboo"};
        
        //manually fill grid some pandas and a grass (not something the panda can eat)
        Herbivore panda = new Herbivore("herbivore", "panda", "p", sources, 2, 0, 0, 0, 0, 0, 0); 
        Vegetable grass = new Vegetable("vegetable", "grass", "g", null, 1, 0, 0, 0, 0, 0, 0); 

        myGrid.getGrid().get(0).get(0).setSpeciesInstanceAnimal(panda.getSpecies().get(0));
        myGrid.getGrid().get(0).get(0).setIsOccupiedAnimal(true);
        myGrid.getGrid().get(0).get(0).setAnimalSymbol(panda.getAnimalSymbol());
        panda.getSpecies().get(0).setCell(myGrid.getGrid().get(0).get(0));

        myGrid.getGrid().get(0).get(1).setSpeciesInstanceAnimal(panda.getSpecies().get(1));
        myGrid.getGrid().get(0).get(1).setIsOccupiedAnimal(true);
        myGrid.getGrid().get(0).get(1).setAnimalSymbol(panda.getAnimalSymbol());
        panda.getSpecies().get(1).setCell(myGrid.getGrid().get(0).get(1));
        
        myGrid.getGrid().get(0).get(0).setSpeciesInstancePlant(grass.getSpecies().get(0));
        myGrid.getGrid().get(0).get(0).setIsOccupiedPlant(true);
        myGrid.getGrid().get(0).get(0).setPlantSymbol(grass.getPlantSymbol());
        grass.getSpecies().get(0).setCell(myGrid.getGrid().get(0).get(0));

        ArrayList<Cell> arr1 = myGrid.surroundingFoodCells(panda.getSpecies().get(0), sources); //should return empty ArrayList
        ArrayList<Cell> test1 = new ArrayList<Cell>();

        assertTrue("surroundingFoodCells() does not work correctly",(arr1.equals(test1)));
        
        ArrayList<Cell> arr2 = myGrid.surroundingFoodCells(panda.getSpecies().get(1), sources); //should return empty ArrayList

        assertTrue("surroundingFoodCells() does not work correctly",(arr2.equals(test1)));
        
        //next test carnivore (only looks in adjacent cells)
        
        String[] sources2 = {"fish"};
        Carnivore dolphin = new Carnivore("carnivore", "dolphin", "d", sources2, 1, 0, 0, 0, 0, 0, 0);
        
        myGrid.getGrid().get(0).get(0).setSpeciesInstanceAnimal(dolphin.getSpecies().get(0)); //reset first cell to hold dolphin instead
        myGrid.getGrid().get(0).get(0).setIsOccupiedAnimal(true);
        myGrid.getGrid().get(0).get(0).setAnimalSymbol(dolphin.getAnimalSymbol());
        dolphin.getSpecies().get(0).setCell(myGrid.getGrid().get(0).get(0));
        
        ArrayList<Cell> arr3 = myGrid.surroundingFoodCells(dolphin.getSpecies().get(0), sources2); //should return empty ArrayList

        assertTrue("surroundingFoodCells() does not work correctly",(arr3.equals(test1)));
        
        //next test omnivore (can look in same and adjacent cells)
        
        String[] sources3 = {"love","peace"};
        Omnivore harambe = new Omnivore("omnivore", "harambe", "h", sources3, 1, 0, 0, 0, 0, 0, 0);
        
        myGrid.getGrid().get(1).get(1).setSpeciesInstanceAnimal(harambe.getSpecies().get(0));
        myGrid.getGrid().get(1).get(1).setIsOccupiedAnimal(true);
        myGrid.getGrid().get(1).get(1).setAnimalSymbol(harambe.getAnimalSymbol());
        harambe.getSpecies().get(0).setCell(myGrid.getGrid().get(1).get(1));
        
        ArrayList<Cell> arr4 = myGrid.surroundingFoodCells(harambe.getSpecies().get(0), sources3); //should return empty ArrayList

        assertTrue("surroundingFoodCells() does not work correctly",(arr4.equals(test1)));
        
    }
    
    /**
     * Tests that surroundingFoodCells() returns the correct cells, no matter the instance's place on the board
     * or whether it is a herbivore, carnivore, or omnivore
     */
    @Test
    public void TestSurroundingFoodCells2()
    {
        Grid myGrid = new Grid(3,3); //9 cell grid
        
        //first test herbivore (only looks at current cell)
        
        String[] sources = {"bamboo"};
        
        //manually fill grid a panda and a bamboo plant
        Herbivore panda = new Herbivore("herbivore", "panda", "p", sources, 1, 0, 0, 0, 0, 0, 0); 
        Vegetable bamboo = new Vegetable("vegetable", "bamboo", "b", null, 3, 0, 0, 0, 0, 0, 0); 

        myGrid.getGrid().get(1).get(1).setSpeciesInstanceAnimal(panda.getSpecies().get(0)); //put panda in center cell
        myGrid.getGrid().get(1).get(1).setIsOccupiedAnimal(true);
        myGrid.getGrid().get(1).get(1).setAnimalSymbol(panda.getAnimalSymbol());
        panda.getSpecies().get(0).setCell(myGrid.getGrid().get(1).get(1));
        
        myGrid.getGrid().get(1).get(1).setSpeciesInstancePlant(bamboo.getSpecies().get(0)); //put bamboo in same cell as panda
        myGrid.getGrid().get(1).get(1).setIsOccupiedPlant(true);
        myGrid.getGrid().get(1).get(1).setPlantSymbol(bamboo.getPlantSymbol());
        bamboo.getSpecies().get(0).setCell(myGrid.getGrid().get(1).get(1));

        ArrayList<Cell> arr1 = myGrid.surroundingFoodCells(panda.getSpecies().get(0), sources); 
        ArrayList<Cell> test1 = new ArrayList<Cell>();
        test1.add(myGrid.getGrid().get(1).get(1));

        assertTrue("surroundingFoodCells() does not work correctly",(arr1.equals(test1)));
        
        //then test carnivore (looks at adjacent cells)
        
        String[] sources2 = {"panda"};
        
        //manually fill four corners of grid with carnivores
        Carnivore dolphin = new Carnivore("carnivore", "dolphin", "d", sources2, 4, 0, 0, 0, 0, 0, 0); 
        
        myGrid.getGrid().get(0).get(0).setSpeciesInstanceAnimal(dolphin.getSpecies().get(0)); 
        myGrid.getGrid().get(0).get(0).setIsOccupiedAnimal(true);
        myGrid.getGrid().get(0).get(0).setAnimalSymbol(dolphin.getAnimalSymbol());
        dolphin.getSpecies().get(0).setCell(myGrid.getGrid().get(0).get(0));
        
        myGrid.getGrid().get(0).get(2).setSpeciesInstanceAnimal(dolphin.getSpecies().get(1)); 
        myGrid.getGrid().get(0).get(2).setIsOccupiedAnimal(true);
        myGrid.getGrid().get(0).get(2).setAnimalSymbol(dolphin.getAnimalSymbol());
        dolphin.getSpecies().get(1).setCell(myGrid.getGrid().get(0).get(2));
        
        myGrid.getGrid().get(2).get(0).setSpeciesInstanceAnimal(dolphin.getSpecies().get(2));
        myGrid.getGrid().get(2).get(0).setIsOccupiedAnimal(true);
        myGrid.getGrid().get(2).get(0).setAnimalSymbol(dolphin.getAnimalSymbol());
        dolphin.getSpecies().get(2).setCell(myGrid.getGrid().get(2).get(0));
        
        myGrid.getGrid().get(2).get(2).setSpeciesInstanceAnimal(dolphin.getSpecies().get(3));
        myGrid.getGrid().get(2).get(2).setIsOccupiedAnimal(true);
        myGrid.getGrid().get(2).get(2).setAnimalSymbol(dolphin.getAnimalSymbol());
        dolphin.getSpecies().get(3).setCell(myGrid.getGrid().get(2).get(2));
        
        ArrayList<Cell> test2 = new ArrayList<Cell>();
        test2.add(myGrid.getGrid().get(1).get(1)); //every carnivore test should return the center cell
        
        ArrayList<Cell> arr2 = myGrid.surroundingFoodCells(dolphin.getSpecies().get(0), sources2); 
        assertTrue("surroundingFoodCells() does not work correctly",(arr2.equals(test2)));
        
        ArrayList<Cell> arr3 = myGrid.surroundingFoodCells(dolphin.getSpecies().get(1), sources2); 
        assertTrue("surroundingFoodCells() does not work correctly",(arr3.equals(test2)));
        
        ArrayList<Cell> arr4 = myGrid.surroundingFoodCells(dolphin.getSpecies().get(2), sources2); 
        assertTrue("surroundingFoodCells() does not work correctly",(arr4.equals(test2)));
        
        ArrayList<Cell> arr5 = myGrid.surroundingFoodCells(dolphin.getSpecies().get(3), sources2); 
        assertTrue("surroundingFoodCells() does not work correctly",(arr5.equals(test2)));
        
        //then test omnivore (looks at own and adjacent cells; own cell takes precedence)
        
        String[] sources3 = {"dolphin","bamboo"};
        
        //manually fill remaining four empty cells with omnivores
        Omnivore hippo = new Omnivore("omnivore", "hippo", "h", sources3, 4, 0, 0, 0, 0, 0, 0); 
        
        myGrid.getGrid().get(0).get(1).setSpeciesInstanceAnimal(hippo.getSpecies().get(0)); 
        myGrid.getGrid().get(0).get(1).setIsOccupiedAnimal(true);
        myGrid.getGrid().get(0).get(1).setAnimalSymbol(hippo.getAnimalSymbol());
        hippo.getSpecies().get(0).setCell(myGrid.getGrid().get(0).get(1));
        
        myGrid.getGrid().get(1).get(0).setSpeciesInstanceAnimal(hippo.getSpecies().get(1)); 
        myGrid.getGrid().get(1).get(0).setIsOccupiedAnimal(true);
        myGrid.getGrid().get(1).get(0).setAnimalSymbol(hippo.getAnimalSymbol());
        hippo.getSpecies().get(1).setCell(myGrid.getGrid().get(1).get(0));
        
        myGrid.getGrid().get(1).get(2).setSpeciesInstanceAnimal(hippo.getSpecies().get(2)); 
        myGrid.getGrid().get(1).get(2).setIsOccupiedAnimal(true);
        myGrid.getGrid().get(1).get(2).setAnimalSymbol(hippo.getAnimalSymbol());
        hippo.getSpecies().get(2).setCell(myGrid.getGrid().get(1).get(2));
        
        myGrid.getGrid().get(2).get(1).setSpeciesInstanceAnimal(hippo.getSpecies().get(3)); 
        myGrid.getGrid().get(2).get(1).setIsOccupiedAnimal(true);
        myGrid.getGrid().get(2).get(1).setAnimalSymbol(hippo.getAnimalSymbol());
        hippo.getSpecies().get(3).setCell(myGrid.getGrid().get(2).get(1));
        
        //put in two more bamboo plants
        
        myGrid.getGrid().get(1).get(0).setSpeciesInstancePlant(bamboo.getSpecies().get(1)); //put bamboo in same cell as omnivore
        myGrid.getGrid().get(1).get(0).setIsOccupiedPlant(true);
        myGrid.getGrid().get(1).get(0).setPlantSymbol(bamboo.getPlantSymbol());
        bamboo.getSpecies().get(1).setCell(myGrid.getGrid().get(1).get(0));
        
        myGrid.getGrid().get(1).get(2).setSpeciesInstancePlant(bamboo.getSpecies().get(2)); //put bamboo in same cell as omnivore
        myGrid.getGrid().get(1).get(2).setIsOccupiedPlant(true);
        myGrid.getGrid().get(1).get(2).setPlantSymbol(bamboo.getPlantSymbol());
        bamboo.getSpecies().get(2).setCell(myGrid.getGrid().get(1).get(2));
        
        //actually test method on all omnivores
        ArrayList<Cell> arr6 = myGrid.surroundingFoodCells(hippo.getSpecies().get(0), sources3); 
        ArrayList<Cell> test3 = new ArrayList<Cell>();
        test3.add(myGrid.getGrid().get(0).get(0));
        test3.add(myGrid.getGrid().get(0).get(2));
        
        assertTrue("surroundingFoodCells() does not work correctly",(arr6.equals(test3)));
        
        ArrayList<Cell> arr7 = myGrid.surroundingFoodCells(hippo.getSpecies().get(1), sources3); 
        ArrayList<Cell> test4 = new ArrayList<Cell>();
        test4.add(myGrid.getGrid().get(1).get(0)); //will return own cell because there is a plant it can eat in it
        
        assertTrue("surroundingFoodCells() does not work correctly",(arr7.equals(test4)));
        
        ArrayList<Cell> arr8 = myGrid.surroundingFoodCells(hippo.getSpecies().get(2), sources3); 
        ArrayList<Cell> test5 = new ArrayList<Cell>();
        test5.add(myGrid.getGrid().get(1).get(2)); //will return own cell because there is a plant it can eat in it
        
        assertTrue("surroundingFoodCells() does not work correctly",(arr8.equals(test5)));
        
        ArrayList<Cell> arr9 = myGrid.surroundingFoodCells(hippo.getSpecies().get(3), sources3); 
        ArrayList<Cell> test6 = new ArrayList<Cell>();
        test6.add(myGrid.getGrid().get(2).get(0));
        test6.add(myGrid.getGrid().get(2).get(2));
        
        assertTrue("surroundingFoodCells() does not work correctly",(arr9.equals(test6)));
    }
}
