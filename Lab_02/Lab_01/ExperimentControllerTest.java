import static org.junit.Assert.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * The test class ExperimentControllerTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class ExperimentControllerTest
{
    private ExperimentController ec;
   
    public ExperimentControllerTest()
    {
    }
    

    @Before
    public void setUp()
    {
        ec = new ExperimentController();
    }

    @After
    public void tearDown()
    {
    }



    @Test
    public void timeAddToFrontTest()
    {
        
        assertFalse("Time is not calculated correctly", ec.timeAddToFront(10000, 100) < 0);
        assertTrue("Values less or equal to 1 for seed should not be allowed", ec.timeAddToFront(10000, -100) == -1);
        assertTrue("negative values for numberOfItems should not be allowed", ec.timeAddToFront(-10000, 100) == -1);
        assertTrue("negative values for seed and numberOfItems should not be allowed", ec.timeAddToFront(-10000, -100) == -1);
        assertTrue("numberOfItems should be positive", ec.timeAddToFront(0, 100) == -1);
        assertTrue("numberOfItems should be positive and seed more than 1", ec.timeAddToFront(0, 1) == -1);
        //assertTrue("numberOfItems should be positive and seed more than 1", ec.timeAddToFront(0, 0) == -1);
        //assertTrue("numberOfItems should be positive and seed more than 1", ec.timeAddToFront(0, -1) == -1);
        assertTrue("seed should be more than 1", ec.timeAddToFront(10000, 0) == -1);
        
    }
    
    @Test
    public void timeSortOfUnsortedListTest()
    {
        ec.timeAddToFront(10000, 100);
        assertFalse("Time is not calculated correctly", ec.timeSortOfUnsortedList() < 0);
    }
    
    @Test
    public void timeSortOfSortedListTest()
    {
        ec.timeAddToFront(10000, 100);
        assertFalse("Time is not calculated correctly", ec.timeSortOfUnsortedList() < 0);
    }
}



