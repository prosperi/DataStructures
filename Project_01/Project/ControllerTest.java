

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;

/** 
  * @author Zura Mestiashvili mestiasz@lafayette.edu
  * @version v1.0.0
*/

public class ControllerTest
{
    public static Controller controller;
    public static Terrain terrain;
    public static ArrayList<Specimen> habitants;
    public static Animal animal;
    public static Animal foo;
    public static Animal boo;
    
    public ControllerTest()
    {
    }

   
    @Before
    public void setUp()
    {
        ArrayList<String> energySources = new ArrayList<String>();
        energySources.add("wheat");
        energySources.add("banana");
        double energy = 20.0;
        ArrayList<Double> stats = new ArrayList<Double>();
        stats.add(10.1);
        stats.add(5.1);
        controller = new Controller("cow");
        animal = new Animal("cow", "herbivore", 'c', energySources, energy, stats, 30, 50, 20, 1, 1, controller, 1);
        foo = new Animal("cow", "herbivore", 'c', energySources, 30, stats, 30, 50, 20, 1, 1, controller, 1);
        boo = new Animal("cow", "herbivore", 'c', energySources, 30, stats, 30, 50, 20, 1, 1, controller, 1);
    }

    
    @After
    public void tearDown()
    {
    }
    
    @Test
    public void testAddSpecimen(){
        controller.addSpecimen(animal);
        controller.increaseInitialPopulation();
        controller.addSpecimen(foo);
        controller.increaseInitialPopulation();
        assertTrue("could not add specimen", controller.getPopulation() == 2);
        assertTrue("could not add specimen", controller.getBirth() == 0);
    }
    
    @Test
    public void testMoveToDead(){
        controller.addSpecimen(animal);
        controller.moveToDead(animal);
        assertTrue("could not remove specimen", controller.getPopulation() == 0);
        assertTrue("could not remove specimen", controller.getDeath() == 1);
    }
    
    @Test
    public void testGetMedianEnergy(){
        controller.addSpecimen(animal);
        controller.addSpecimen(foo);
        double tmp = controller.getMedianEnergy();
        assertTrue("could not get median energy specimen", tmp == 25);
    }
    
    @Test
    public void testGetMedianAge(){
        controller.addSpecimen(animal);
        animal.setAge(15);
        controller.addSpecimen(foo);
        foo.setAge(14);
        controller.addSpecimen(boo);
        boo.setAge(17);
        double tmp = controller.getMedianAge();
        assertTrue("could not get median energy specimen " + tmp, tmp == 15);
    }
    
}
