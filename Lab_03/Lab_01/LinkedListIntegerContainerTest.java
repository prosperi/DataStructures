

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class LinkedListIntegerContainerTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class LinkedListIntegerContainerTest
{
    /**
     * Default constructor for test class LinkedListIntegerContainerTest
     */
    public LinkedListIntegerContainerTest()
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
    
    @Test
    public void testArrayListIntegerContainer(){
        LinkedListIntegerContainer linkedList = new LinkedListIntegerContainer();
   
        assertTrue("ArrayList was not intialized", linkedList.data != null);
        
        // check if addToFront and insertionSort methods are inherited from IntegerContainer
        try{
            linkedList.addToFront(1);
            linkedList.insertionSort();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
