

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class RandomIntegerContainerTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class RandomIntegerContainerTest
{
    /**
     * Default constructor for test class RandomIntegerContainerTest
     */
    public RandomIntegerContainerTest()
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
    public void addToFrontTest()
    {
        RandomIntegerContainer ric = new RandomIntegerContainer();
        ric.addToFront(100);
        assertTrue("Could not add number in front", ric.aList.get(0) == 100);
        
    }
    
    @Test
    public void insertionSort()
    {
        // Create an Instance 
        RandomIntegerContainer ric = new RandomIntegerContainer();
        
        // Check for unsorted array without repeating values
        ric.addToFront(1);
        ric.addToFront(2);
        ric.addToFront(3);
        ric.addToFront(4);
        ric.addToFront(5);
        ric.addToFront(6);
        ric.addToFront(7);
        ric.addToFront(8);
        ric.addToFront(9);
        ric.addToFront(10);
        
        ric.insertionSort();
        int[] sorted = {1,2,3,4,5,6,7,8,9,10};
        Object[] afterSort = ric.aList.toArray();
        for(int i = 0; i < sorted.length; i++){
            assertEquals("InsertionSort could not sort", sorted[i], afterSort[i]);
        }
        
        // Check for unsorted array with repeating values
        ric.aList.clear();
        ric.addToFront(1);
        ric.addToFront(2);
        ric.addToFront(3);
        ric.addToFront(4);
        ric.addToFront(1);
        ric.addToFront(6);
        ric.addToFront(7);
        ric.addToFront(1);
        ric.addToFront(9);
        ric.addToFront(2);
        
        ric.insertionSort();
        sorted = new int[]{1,1,1,2,2,3,4,6,7,9};
        afterSort = ric.aList.toArray();
        for(int i = 0; i < sorted.length; i++){
            assertEquals("InsertionSort could not sort", sorted[i], afterSort[i]);
        }
        
        // Check for unsorted array with the same values
        ric.aList.clear();
        ric.addToFront(1);
        ric.addToFront(1);
        ric.addToFront(1);
        ric.addToFront(1);
        ric.addToFront(1);
        ric.addToFront(1);
        ric.addToFront(1);
        ric.addToFront(1);
        ric.addToFront(1);
        ric.addToFront(1);
        
        ric.insertionSort();
        sorted = new int[]{1,1,1,1,1,1,1,1,1,1};
        afterSort = ric.aList.toArray();
        for(int i = 0; i < sorted.length; i++){
            assertEquals("InsertionSort could not sort", sorted[i], afterSort[i]);
        }
        
        
        // Check for an empty array
        
        ric.aList.clear();        
        ric.insertionSort();
        sorted = new int[]{};
        afterSort = ric.aList.toArray();
        for(int i = 0; i < sorted.length; i++){
            assertEquals("InsertionSort could not sort", sorted[i], afterSort[i]);
        }
        
        
        // Check for 1 element array
        
        ric.aList.clear();
        ric.addToFront(1);
        ric.insertionSort();
        sorted = new int[]{1};
        afterSort = ric.aList.toArray();
        for(int i = 0; i < sorted.length; i++){
            assertEquals("InsertionSort could not sort", sorted[i], afterSort[i]);
        }
        
        
        // Check for sorted array
        ric.aList.clear();
        ric.addToFront(10);
        ric.addToFront(9);
        ric.addToFront(8);
        ric.addToFront(7);
        ric.addToFront(6);
        ric.addToFront(5);
        ric.addToFront(4);
        ric.addToFront(3);
        ric.addToFront(2);
        ric.addToFront(1);
        
        ric.insertionSort();
        sorted = new int[]{1,2,3,4,5,6,7,8,9,10};
        afterSort = ric.aList.toArray();
        for(int i = 0; i < sorted.length; i++){
            assertEquals("InsertionSort could not sort", sorted[i], afterSort[i]);
        }
        
    }
}


