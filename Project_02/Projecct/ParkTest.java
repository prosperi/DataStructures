
import java.util.*;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class ParkTest tests class Park.
 *
 * @version (10/15/2016)
 */
public class ParkTest
{
    /**
     * Default constructor for test class ParkTest
     */
    public ParkTest()
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
     * Tests that the noChangeInSimulation variable changes as it should in method printSummaryDataPartial()
     */
    @Test
    public void TestPrintSummaryDataPartial()
    {
        LinkedList<ArrayList<Species>> allSpecies = new LinkedList<ArrayList<Species>>();

        ArrayList<Species> allFruitSpecies = new ArrayList<Species>();
        allFruitSpecies.add(new Fruit("fruit", "apple", "a", null, 1, 0, 0, 0, 0, 0, 0)); 
        ArrayList<Species> allHerbivoreSpecies = new ArrayList<Species>();
        allHerbivoreSpecies.add(new Herbivore("herbivore", "panda", "p", null, 1, 0, 0, 0, 0, 0, 0)); 
        ArrayList<Species> allVegetableSpecies = new ArrayList<Species>();
        allVegetableSpecies.add(new Herbivore("vegetable", "carrot", "c", null, 1, 0, 0, 0, 0, 0, 0)); 

        allSpecies.add(allFruitSpecies);
        allSpecies.add(allHerbivoreSpecies);
        allSpecies.add(allVegetableSpecies);

        allSpecies.get(0).get(0).setNumberDeaths(10);

        Park myPark = new Park();

        boolean beforeValue = myPark.getChangeInSimulation(); //false
        myPark.printSummaryDataPartial(allSpecies);
        boolean afterValue = myPark.getChangeInSimulation(); //should be false

        assertTrue("noChangeInSimulation is not changing correctly",(beforeValue == afterValue));
        //(ignore console window printout)

        myPark.printSummaryDataPartial(allSpecies);
        afterValue = myPark.getChangeInSimulation(); //should now be true

        assertTrue("noChangeInSimulation is not changing correctly",(beforeValue != afterValue));
        //(ignore console window printout)
    }

    /*
     * Tests that isSimulationEnd() ends the simulation at the correct time
     */
    @Test
    public void TestIsSimulationEnd()
    {
        LinkedList<ArrayList<Species>> allSpecies = new LinkedList<ArrayList<Species>>();

        ArrayList<Species> allFruitSpecies = new ArrayList<Species>();
        allFruitSpecies.add(new Fruit("fruit", "apple", "a", null, 1, 0, 0, 0, 0, 0, 0)); 
        ArrayList<Species> allHerbivoreSpecies = new ArrayList<Species>();
        allHerbivoreSpecies.add(new Herbivore("herbivore", "panda", "p", null, 1, 0, 0, 0, 0, 0, 0)); 
        ArrayList<Species> allVegetableSpecies = new ArrayList<Species>();
        allVegetableSpecies.add(new Herbivore("vegetable", "carrot", "c", null, 1, 0, 0, 0, 0, 0, 0)); 

        allSpecies.add(allFruitSpecies);
        allSpecies.add(allHerbivoreSpecies);
        allSpecies.add(allVegetableSpecies);

        Park myPark = new Park();

        int stepsLeft = 2;
        boolean noChangeInSimulation = false;
        boolean result = myPark.isSimulationEnd(stepsLeft, noChangeInSimulation, allSpecies); //should not end simulation

        assertTrue("isSimulationEnd() is not working correctly",(result == false));

        stepsLeft = 0;
        result = myPark.isSimulationEnd(stepsLeft, noChangeInSimulation, allSpecies); //should end simulation

        assertTrue("isSimulationEnd() is not working correctly",(result == true));

        stepsLeft = 2;
        noChangeInSimulation = true;
        result = myPark.isSimulationEnd(stepsLeft, noChangeInSimulation, allSpecies); //should end simulation

        assertTrue("isSimulationEnd() is not working correctly",(result == true));

        allSpecies.get(0).get(0).getSpecies().remove(0);
        allSpecies.get(1).get(0).getSpecies().remove(0);
        allSpecies.get(2).get(0).getSpecies().remove(0);

        result = myPark.isSimulationEnd(stepsLeft, noChangeInSimulation, allSpecies); //should end simulation

        assertTrue("isSimulationEnd() is not working correctly",(result == true));
    }

    /**
     * Tests that printStatus() prints correct values, no matter how many instances are currently in a species
     */
    @Test
    public void TestPrintStatus()
    {
        LinkedList<ArrayList<Species>> allSpecies = new LinkedList<ArrayList<Species>>();
        //test with no instances of any species
        ArrayList<Species> allFruitSpecies = new ArrayList<Species>();
        allFruitSpecies.add(new Fruit("fruit", "apple", "a", null, 0, 10, 0, 0, 20, 25, 2));
        allSpecies.add(allFruitSpecies);

        Park myPark = new Park();

        myPark.printStatus(allSpecies);
        //console window should read: Species: apple, Population: 0, Median Energy Level: 0, Median Age: 0

        allSpecies.clear();
        allFruitSpecies.clear();
        //test with one instance of a species
        allFruitSpecies.add(new Fruit("fruit", "apple", "a", null, 1, 10, 0, 0, 20, 25, 5)); 
        allSpecies.add(allFruitSpecies);
        allSpecies.get(0).get(0).getSpecies().get(0).setAge(2);

        myPark.printStatus(allSpecies);
        //console window should read: Species: apple, Population: 1, Median Energy Level: 10, Median Age: 2

        allSpecies.clear();
        allFruitSpecies.clear();
        //test with two instances of a species
        allFruitSpecies.add(new Fruit("fruit", "apple", "a", null, 2, 10, 0, 0, 20, 25, 5)); 
        allSpecies.add(allFruitSpecies);
        allSpecies.get(0).get(0).getSpecies().get(0).setAge(2);
        allSpecies.get(0).get(0).getSpecies().get(1).setAge(4);
        allSpecies.get(0).get(0).getSpecies().get(1).setEnergyLevel(20);

        myPark.printStatus(allSpecies);
        //console window should read: Species: apple, Population: 2, Median Energy Level: 15, Median Age: 3

        allSpecies.clear();
        allFruitSpecies.clear();

        //test with more than two instances of a species
        allFruitSpecies.add(new Fruit("fruit", "apple", "a", null, 5, 10, 0, 0, 20, 25, 5));
        allSpecies.add(allFruitSpecies);
        allSpecies.get(0).get(0).getSpecies().get(0).setAge(4);
        allSpecies.get(0).get(0).getSpecies().get(1).setAge(9);
        allSpecies.get(0).get(0).getSpecies().get(3).setAge(3);
        allSpecies.get(0).get(0).getSpecies().get(4).setAge(6);
        allSpecies.get(0).get(0).getSpecies().get(0).setEnergyLevel(15);
        allSpecies.get(0).get(0).getSpecies().get(2).setEnergyLevel(9);
        allSpecies.get(0).get(0).getSpecies().get(3).setEnergyLevel(8);
        allSpecies.get(0).get(0).getSpecies().get(4).setEnergyLevel(6);

        myPark.printStatus(allSpecies);
        //console window should read: Species: apple, Population: 5, Median Energy Level: 9, Median Age: 4
    }
}
