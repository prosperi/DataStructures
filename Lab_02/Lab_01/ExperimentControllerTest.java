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
        assertTrue("numberOfItems should be positive", ec.timeAddToFront(0, 100) == -1);
        assertTrue("numberOfItems should be positive", ec.timeAddToFront(-10, 100) == -1);

        
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



