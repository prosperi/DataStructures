

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * The test class RandomIntegerContainerTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class RandomIntegerContainerTest
{
    private static RandomIntegerContainer ric;
    
    /**
     * Default constructor for test class RandomIntegerContainerTest
     */
    public RandomIntegerContainerTest()
    {
    }
    
    @BeforeClass
    public static void classSetUp(){
        ric = new RandomIntegerContainer();
    }
    
    @AfterClass
    public static void classCleanUp(){
    
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
        ric.aList.clear();
    }

    
    @Test
    public void addToFrontTest()
    {
        int size = ric.aList.size();
        ric.addToFront(100);
        assertTrue("Could not add number in front", ric.aList.get(0) == 100 && ric.aList.size() == (size+1));
    }
    
    @Test
    public void insertionSortTest()
    {
        // Check for unsorted array without repeating values
        ric.aList = new ArrayList<Integer>(Arrays.asList(10,9,8,7,6,5,4,3,2,1));
        ric.insertionSort();
        int[] sorted = {1,2,3,4,5,6,7,8,9,10};
        Object[] afterSort = ric.aList.toArray();
        for(int i = 0; i < sorted.length; i++){
            assertEquals("InsertionSort could not sort", sorted[i], afterSort[i]);
        }
        
        // Check for unsorted array with repeating values
        ric.aList = new ArrayList<Integer>(Arrays.asList(1,2,3,4,1,6,7,1,9,2));        
        ric.insertionSort();
        sorted = new int[]{1,1,1,2,2,3,4,6,7,9};
        afterSort = ric.aList.toArray();
        for(int i = 0; i < sorted.length; i++){
            assertEquals("InsertionSort could not sort", sorted[i], afterSort[i]);
        }
        
        // Check for unsorted array with the same values
        ric.aList = new ArrayList<Integer>(Arrays.asList(1,1,1,1,1,1,1,1,1,1));
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
        ric.aList.add(1);
        ric.insertionSort();
        sorted = new int[]{1};
        afterSort = ric.aList.toArray();
        for(int i = 0; i < sorted.length; i++){
            assertEquals("InsertionSort could not sort", sorted[i], afterSort[i]);
        }        
        
        // Check for sorted array
        ric.aList = new ArrayList<Integer>(Arrays.asList(1,2,3,4,5,6,7,8,9,10));        
        ric.insertionSort();
        sorted = new int[]{1,2,3,4,5,6,7,8,9,10};
        afterSort = ric.aList.toArray();
        for(int i = 0; i < sorted.length; i++){
            assertEquals("InsertionSort could not sort", sorted[i], afterSort[i]);
        }
        
    }
}


