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
    private ArrayListIntegerContainer arrContainer;
    private LinkedListIntegerContainer linkedContainer;
   
    public ExperimentControllerTest()
    {
    }
    

    @Before
    public void setUp()
    {
        ec = new ExperimentController();
        arrContainer = new ArrayListIntegerContainer();
        linkedContainer = new LinkedListIntegerContainer();
    }

    @After
    public void tearDown()
    {
    }



    @Test
    public void timeAddToFrontTest()
    {
        
        /*assertFalse("Time is not calculated correctly", ec.timeAddToFront(10000, 100) < 0);
        assertTrue("numberOfItems should be positive", ec.timeAddToFront(0, 100) == -1);
        assertTrue("numberOfItems should be positive", ec.timeAddToFront(-10, 100) == -1);*/
        
        System.out.println(ec.timeAddToFront(arrContainer, 10000, 100));
        System.out.println(ec.timeAddToFront(linkedContainer, 10000, 100));
        
        
    }
    
    @Test
    public void timeSortOfUnsortedListTest()
    {
        ec.timeAddToFront(arrContainer, 10000, 100);
        System.out.println(ec.timeSortOfUnsortedList(arrContainer));
        
        ec.timeAddToFront(linkedContainer, 10000, 100);
        System.out.println(ec.timeSortOfUnsortedList(linkedContainer));
        //assertFalse("Time is not calculated correctly", ec.timeSortOfUnsortedList() < 0);
    }
    
    @Test
    public void timeSortOfSortedListTest()
    {
        ec.timeAddToFront(arrContainer, 10000, 100);
        System.out.println(ec.timeSortOfUnsortedList(arrContainer));
        
        ec.timeAddToFront(linkedContainer, 10000, 100);
        System.out.println(ec.timeSortOfUnsortedList(linkedContainer));
        //assertFalse("Time is not calculated correctly", ec.timeSortOfUnsortedList() < 0);
    }
}



