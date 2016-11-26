import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Ignore;
import java.util.*;

public class CarnivoreTest
{
    World world;
    
    public CarnivoreTest()
    {
    }

    /* Called before every test case method. */
    @Before
    public void setUp()
    {
        world = new World(5,5,5);
    }

    /* Called after every test case method. */
    @After
    public void tearDown()
    {
    }
    
    @Test
    public void testEat() {
        ArrayList<String> energySources = new ArrayList<String>();
        energySources.add("rabbit");
        Species testPrey = new Herbivore("rabbit", "r", new ArrayList<String>(), 10.0, 1.0, 40.0, 30.0, 10.0, 30.0, 10.0, 1.0, 2, 2, 10);
        Species testAnimal = new Carnivore("bear", "b", energySources, 10.0, 1.0, 40.0, 30.0, 10.0, 30.0, 10.0, 1.0, 2, 2, 10);
        world.get(1,1).setAnimal((Animal)testPrey);
        testPrey.setCell(world.get(1,1));
        world.get(2,2).setAnimal((Animal)testAnimal);
        testAnimal.setCell(world.get(2,2));
        assert testAnimal.eat() : "Didn't eat animal";
    }
    
    @Test
    public void testDie() {
        Species testEnergy = new Carnivore("tiger", "t", new ArrayList<String>(), 10.0, 1.0, 40.0, 30.0, 100.0, 30.0, 10.0, 1.0, 2, 2, 10);
        Species testAge = new Carnivore("bear", "b", new ArrayList<String>(), 1.0, 0.25, 40.0, 30.0, 10.0, 30.0, 10.0, 1.0, 2, 2, 10);
        world.get(1,1).setAnimal((Animal)testEnergy);
        testEnergy.setCell(world.get(1,1));
        world.get(2,2).setAnimal((Animal)testAge);
        testAge.setCell(world.get(2,2));
        assert testEnergy.die() : "Didn't die of energy";
        assert testAge.die() || testAge.die() : "Didn't die of age"; //Will fail occasionally depending on the Gaussian distribution
    }
    
    @Test
    public void testBirth() {
        Species testBirth = new Carnivore("bear", "b", new ArrayList<String>(), 10.0, 0.25, 5.0, 30.0, 10.0, 30.0, 10.0, 1.0, 2, 2, 10);
        world.get(2,2).setAnimal((Animal)testBirth);
        testBirth.setCell(world.get(2,2));
        assert testBirth.birth() : "Didn't give birth";
    }
    
    @Test
    public void testMove() {
        Species testMove = new Carnivore("bear", "b", new ArrayList<String>(), 10.0, 0.25, 50.0, 30.0, 10.0, 30.0, 10.0, 1.0, 2, 2, 10);
        world.get(2,2).setAnimal((Animal)testMove);
        testMove.setCell(world.get(2,2));
        assert testMove.move() : "Didn't move";
    }
    
    @Test
    public void testActivity() {
        ArrayList<String> energySources = new ArrayList<String>();
        energySources.add("rabbit");
        Species testPrey = new Herbivore("rabbit", "r", new ArrayList<String>(), 10.0, 1.0, 40.0, 30.0, 10.0, 30.0, 10.0, 1.0, 2, 2, 10);
        Species testAnimal = new Carnivore("bear", "b", energySources, 10.0, 1.0, 40.0, 30.0, 10.0, 30.0, 10.0, 1.0, 2, 2, 10);
        world.get(1,1).setAnimal((Animal)testPrey);
        testPrey.setCell(world.get(1,1));
        world.get(2,2).setAnimal((Animal)testAnimal);
        testAnimal.setCell(world.get(2,2));
        testAnimal.activity();
        assert getPopulation() == 1 : "Didn't eat animal";

        world = new World(5,5,5);
        Species testEnergy = new Carnivore("tiger", "t", new ArrayList<String>(), 10.0, 1.0, 40.0, 30.0, 100.0, 30.0, 10.0, 1.0, 2, 2, 10);
        Species testAge = new Carnivore("bear", "b", new ArrayList<String>(), 1.0, 0.25, 40.0, 30.0, 10.0, 30.0, 10.0, 1.0, 2, 2, 10);
        world.get(1,1).setAnimal((Animal)testEnergy);
        testEnergy.setCell(world.get(1,1));
        world.get(2,2).setAnimal((Animal)testAge);
        testAge.setCell(world.get(2,2));
        testEnergy.activity();
        testAge.activity();
        assert getPopulation() == 0 : "Didn't die"; //Will fail occasionally depending on the Gaussian distribution

        world = new World(5,5,5);
        Species testBirth = new Carnivore("bear", "b", new ArrayList<String>(), 10.0, 0.25, 5.0, 30.0, 10.0, 30.0, 10.0, 1.0, 2, 2, 10);
        world.get(2,2).setAnimal((Animal)testBirth);
        testBirth.setCell(world.get(2,2));
        testBirth.activity();
        assert getPopulation() == 2 : "Didn't give birth";

        world = new World(5,5,5);
        Species testMove = new Carnivore("bear", "b", new ArrayList<String>(), 10.0, 0.25, 50.0, 30.0, 10.0, 30.0, 10.0, 1.0, 2, 2, 10);
        world.get(2,2).setAnimal((Animal)testMove);
        testMove.setCell(world.get(2,2));
        testMove.activity();
        assert world.get(2,2).getAnimal() == null : "Didn't move";
    }
    
    public int getPopulation() {
        int pop = 0;
        for(int i = 0; i < world.getHeight(); i++) {
            for(int j = 0; j < world.getWidth(); j++) {
                if(world.get(i,j).getAnimal() != null) {
                    pop++;
                }
                if(world.get(i,j).getPlant() != null) {
                    pop++;
                }
            }
        }
        return pop;
    }
    
    @Test
    public void testChangeHome(){
        ArrayList<String> energySources = new ArrayList<String>();
        energySources.add("rabbit");
        Animal testAnimal = new Carnivore("bear", "b", energySources, 10.0, 1.0, 40.0, 30.0, 10.0, 30.0, 10.0, 1.0, 2, 2, 10);
        
        world.get(1, 1).setAnimal(null);
        testAnimal.setCell(world.get(1, 1));
        testAnimal.changeHome(world.get(2,2));
        
        assertTrue("Could not change Home", world.get(1, 1).getAnimal() == null && world.get(2, 2).getAnimal() == testAnimal);
        
    }
    
    @Test
    public void testCheckPathCell(){
        ArrayList<String> energySources = new ArrayList<String>();
        energySources.add("rabbit");
        energySources.add("broccoli");
        Animal testPrey = new Herbivore("rabbit", "r", new ArrayList<String>(), 10.0, 1.0, 40.0, 30.0, 10.0, 30.0, 10.0, 1.0, 2, 2, 10);
        Animal testAnimal = new Carnivore("bear", "b", energySources, 10.0, 1.0, 40.0, 30.0, 10.0, 30.0, 10.0, 1.0, 2, 2, 10);
        ArrayList<String> energySources1 = new ArrayList<String>();
        energySources1.add("bear");
        Animal predator = new Carnivore("tiget", "t", energySources1, 10.0, 1.0, 40.0, 30.0, 10.0, 30.0, 10.0, 1.0, 2, 2, 10);
        Mountain mountain = new Mountain(1, 0, 1, 0);
        
        energySources = new ArrayList<String>();
        energySources.add("light");
        Plant plant = new Vegetable("broccoli", "b", energySources, 10.0, 1.0, 40.0, 300.0, 10.0, 30.0, 10.0, 1.0, 7, 12, 20);
        
        
        world.get(0, 0).setAnimal(testPrey);
        testPrey.setCell(world.get(0, 0));
        
        world.get(1, 1).setAnimal(testAnimal);
        testAnimal.setCell(world.get(1, 1));
        
        world.get(0, 1).setAnimal(predator);
        predator.setCell(world.get(0, 1));
        
        world.get(1, 0).setMountain(mountain);
        
        world.get(2, 1).setPlant(plant);
        
        assertTrue("Did not return correct Dead End value", testAnimal.checkPathCell(0, 0) == 1);
        assertTrue("Did not return correct Dead End value", testAnimal.checkPathCell(0, 1) == 0);
        assertTrue("Did not return correct Dead End value", testAnimal.checkPathCell(1, 0) == 3);
        assertTrue("Did not return correct Dead End value", testAnimal.checkPathCell(1, 2) == 4);
        assertTrue("Did not return correct Dead End value" +  world.get(2, 1).getPlant().getName(), testAnimal.checkPathCell(2, 1) == 2);
    }
    
    @Test
    public void testAddToPath(){
        ArrayList<String> energySources = new ArrayList<String>();
        energySources.add("rabbit");
        energySources.add("broccoli");
        Animal testPrey = new Herbivore("rabbit", "r", new ArrayList<String>(), 10.0, 1.0, 40.0, 30.0, 10.0, 30.0, 10.0, 1.0, 2, 2, 10);
        Animal testAnimal = new Carnivore("bear", "b", energySources, 10.0, 1.0, 40.0, 30.0, 10.0, 30.0, 10.0, 1.0, 2, 2, 10);
        
        
        Path foo = new Path();
        Path boo = new Path();
        
        Cell cell = world.get(0, 0);
        testAnimal.addToPath(foo, boo, 1, 0, 0, true, true, world);
        assertTrue("Could not add to path correctly", foo.getCell(0) == cell);
        assertTrue("Could not add to path correctly", boo.getCell(0) == cell);
        assertTrue("Could not add to path correctly", foo.getPlantOnTheWay() == true && boo.getPlantOnTheWay() == true);
        assertTrue("Could not add to path correctly", foo.getDeadEnd() == 1 && boo.getDeadEnd() == 1);
    }
    
    @Test
    public void testDrawPath(){
        ArrayList<String> energySources = new ArrayList<String>();
        energySources.add("rabbit");
        energySources.add("broccoli");
        Animal testPrey = new Herbivore("rabbit", "r", new ArrayList<String>(), 10.0, 1.0, 40.0, 30.0, 10.0, 30.0, 10.0, 1.0, 2, 2, 10);
        Animal testAnimal = new Carnivore("bear", "b", energySources, 10.0, 1.0, 40.0, 30.0, 10.0, 30.0, 10.0, 1.0, 2, 2, 10);
        
        energySources = new ArrayList<String>();
        energySources.add("bear");
        Animal predator = new Carnivore("tiger", "t", energySources, 10.0, 1.0, 40.0, 30.0, 10.0, 30.0, 10.0, 1.0, 2, 2, 10);
        
        Mountain mountain = new Mountain(1, 0, 1, 0);
        
        energySources = new ArrayList<String>();
        energySources.add("light");
        Plant plant = new Vegetable("broccoli", "b", energySources, 10.0, 1.0, 40.0, 300.0, 10.0, 30.0, 10.0, 1.0, 7, 12, 20);
        
        world.get(1, 1).setAnimal(testAnimal);
        testAnimal.setCell(world.get(1, 1));
        ArrayList<Path> tmp = testAnimal.drawPath(3, 3);
        String str = "";
        for(int i = 0; i < tmp.size(); i++){
            str += tmp.get(i).toString();
        }
        
        assertTrue("Could not build path correctly" + str, str.compareTo("2 2   3 3   2 2   3 3   ") == 0);
        world.get(3, 3).setMountain(mountain);
        tmp = testAnimal.drawPath(3, 3);
        str = "";
        for(int i = 0; i < tmp.size(); i++){
            str += tmp.get(i).toString();
        }
        assertTrue("Could not build path correctly" + str, str.compareTo("2 2   2 2   ") == 0);
    }
    
}
