

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class ArrayListIntegerContainerTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class ArrayListIntegerContainerTest
{
    /**
     * Default constructor for test class ArrayListIntegerContainerTest
     */
    public ArrayListIntegerContainerTest()
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
        ArrayListIntegerContainer arrList = new ArrayListIntegerContainer();
   
        assertTrue("ArrayList was not intialized", arrList.data != null);
        
        // check if addToFront and insertionSort methods are inherited from IntegerContainer
        try{
            arrList.addToFront(1);
            arrList.insertionSort();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
