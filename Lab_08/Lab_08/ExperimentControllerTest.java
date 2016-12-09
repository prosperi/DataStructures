import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.PrintWriter;
import java.io.IOException;
import java.io.FileWriter;
import java.util.LinkedList;

/**
 * The test class ExperimentControllerTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class ExperimentControllerTest
{
    ExperimentController ec;
    /**
     * Default constructor for test class ExperimentControllerTest
     */
    public ExperimentControllerTest()
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
        ec = new ExperimentController();
        ec.ht = null;
        ec.wheel = new Wheel(1);
        try{
            ec.outputFile = new PrintWriter(new FileWriter("data.csv", true));
        }catch(IOException e){
            System.out.println("Problem with output file!!!");
        }
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
    
    @Test
    public void testRunExperiment(){
        // cannot be tested as result is based on time taken for insertion and searching which may change for each runtime
    }
    
    @Test
    public void testPrintResults(){
        // cannot be tested as result is based on time taken for insertion and searching which may change for each runtime
    }
    
    @Test
    public void testGetList(){
        ec.ht = new MyHashTable(10);
        Team team = new Team();
        
        for(int i = 1; i <= 10; i++){
            ec.ht.insert(i + "", team);
        }
        
        LinkedList<Entry> entry = ec.getList(10, 1);
        assertTrue("Could not return correct list " + entry.get(0).getKey(), entry == ec.ht.getBuckets()[3]);
        entry = ec.getList(10, 2);
        assertTrue("Could not return correct list " + entry.get(0).getKey(), entry == ec.ht.getBuckets()[6]);
        // check when it's empty
        entry = ec.getList(10, 3);
        assertTrue("Could not return correct list " + entry.get(0).getKey(), entry == ec.ht.getBuckets()[9]);
        
    }
    
    @Test
    public void testTimeInsert(){        
        assertTrue("Could not work correctly", ec.timeInsert(10, 100) >= 0);
        assertTrue("Could not work correctly", ec.timeInsert(0,  0) >= 0);
        assertTrue("Could not work correctly", ec.timeInsert(100, 100) >= 0);
    }
    
    @Test
    public void testTimeFind(){
        ec.ht = new MyHashTable(10);
        ec.ht.insert("a", new Team());
        ec.ht.insert("b", new Team());
        
        assertTrue("Could not work correctly", ec.timeFind("a") >= 0);
        assertTrue("Could not work correctly", ec.timeFind("b") >= 0);
        assertTrue("Could not work correctly", ec.timeFind("c") >= 0);
    }
    
    
}
