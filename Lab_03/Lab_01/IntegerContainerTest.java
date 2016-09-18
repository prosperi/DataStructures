import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Arrays;

/**
 * Zura Mestiashvili
 * v1.0.0
 */
public class IntegerContainerTest
{
    private static IntegerContainer ic;
    
    /**
     * Default constructor for test class IntegerContainerTest
     */
    public IntegerContainerTest()
    {
    }
    
    @BeforeClass
    public static void classSetUp(){
        // Create new instance of IntegerContainer, this should run only once , when class
        // is initialized, but in this case, it will run after completion of each test method
        // because of BlueJ's bug.
        ic = new IntegerContainer();
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
        ic.data.clear();
    }
    
    
    @Test
    public void addToFrontTest(){      
        // initialize IntegerContainer's data variable
        ic.data = new ArrayList<Integer>();
        int size = ic.data.size();
        
        // add 100 to container, and check its size and first element. As container is empty yet, 
        // its size will be 1 and the first element 100
        ic.addToFront(100);
        assertTrue("Could not add number in front", ic.data.get(0) == 100 && ic.data.size() == (size+1));
        
        // add 100 more elements container and check size, than we check each element, and make
        // sure each of them is on its place 
        for(int i = 99; i >= 0; i--){
            ic.addToFront(i);
        }
        assertTrue(ic.data.size() == 101);
        
        for(int j = 0; j <= 100; j++){
            assertTrue("Could not add int the front", j == ic.data.get(j));
        }
        
        
        // add numbers from -100 to -1(inclusive), and check size and elemens again. size should
        // equal to 201 and first element should equal to -100
        for(int i = -1; i >= -100; i--){
            ic.addToFront(i);
        }
        
        assertTrue(ic.data.size() == 201);
        for(int j = -100; j <= 100; j++){
            assertTrue("Could not add int the front", j == ic.data.get(j+100));
        }
    }
    
    @Test
    public void insertionSortTest()
    {
        // Check for unsorted array without repeating values
        ic.data = new ArrayList<Integer>(Arrays.asList(10,9,8,7,6,5,4,3,2,1));
        ic.insertionSort();
        int[] sorted = {1,2,3,4,5,6,7,8,9,10};
        Object[] afterSort = ic.data.toArray();
        for(int i = 0; i < sorted.length; i++){
            assertEquals("InsertionSort could not sort", sorted[i], afterSort[i]);
        }
        
        // Check for unsorted array with repeating values
        ic.data = new ArrayList<Integer>(Arrays.asList(1,2,3,4,1,6,7,1,9,2));        
        ic.insertionSort();
        sorted = new int[]{1,1,1,2,2,3,4,6,7,9};
        afterSort = ic.data.toArray();
        for(int i = 0; i < sorted.length; i++){
            assertEquals("InsertionSort could not sort", sorted[i], afterSort[i]);
        }
        
        // Check for unsorted array with the same values
        ic.data = new ArrayList<Integer>(Arrays.asList(1,1,1,1,1,1,1,1,1,1));
        ic.insertionSort();
        sorted = new int[]{1,1,1,1,1,1,1,1,1,1};
        afterSort = ic.data.toArray();
        for(int i = 0; i < sorted.length; i++){
            assertEquals("InsertionSort could not sort", sorted[i], afterSort[i]);
        }
                
        // Check for an empty array
        ic.data.clear();        
        ic.insertionSort();
        sorted = new int[]{};
        afterSort = ic.data.toArray();
        for(int i = 0; i < sorted.length; i++){
            assertEquals("InsertionSort could not sort", sorted[i], afterSort[i]);
        }        
        
        // Check for 1 element array
        ic.data.clear();
        ic.data.add(1);
        ic.insertionSort();
        sorted = new int[]{1};
        afterSort = ic.data.toArray();
        for(int i = 0; i < sorted.length; i++){
            assertEquals("InsertionSort could not sort", sorted[i], afterSort[i]);
        }        
        
        // Check for sorted array
        ic.data = new ArrayList<Integer>(Arrays.asList(1,2,3,4,5,6,7,8,9,10));        
        ic.insertionSort();
        sorted = new int[]{1,2,3,4,5,6,7,8,9,10};
        afterSort = ic.data.toArray();
        for(int i = 0; i < sorted.length; i++){
            assertEquals("InsertionSort could not sort", sorted[i], afterSort[i]);
        }

        
        //// For LinkedList
        
         // Check for unsorted array without repeating values
        ic.data = new LinkedList<Integer>(Arrays.asList(10,9,8,7,6,5,4,3,2,1));
        ic.insertionSort();
        sorted = new int[]{1,2,3,4,5,6,7,8,9,10};
        afterSort = ic.data.toArray();
        for(int i = 0; i < sorted.length; i++){
            assertEquals("InsertionSort could not sort", sorted[i], afterSort[i]);
        }
        
        // Check for unsorted array with repeating values
        ic.data = new LinkedList<Integer>(Arrays.asList(1,2,3,4,1,6,7,1,9,2));        
        ic.insertionSort();
        sorted = new int[]{1,1,1,2,2,3,4,6,7,9};
        afterSort = ic.data.toArray();
        for(int i = 0; i < sorted.length; i++){
            assertEquals("InsertionSort could not sort", sorted[i], afterSort[i]);
        }
        
        // Check for unsorted array with the same values
        ic.data = new LinkedList<Integer>(Arrays.asList(1,1,1,1,1,1,1,1,1,1));
        ic.insertionSort();
        sorted = new int[]{1,1,1,1,1,1,1,1,1,1};
        afterSort = ic.data.toArray();
        for(int i = 0; i < sorted.length; i++){
            assertEquals("InsertionSort could not sort", sorted[i], afterSort[i]);
        }
                
        // Check for an empty array
        ic.data.clear();        
        ic.insertionSort();
        sorted = new int[]{};
        afterSort = ic.data.toArray();
        for(int i = 0; i < sorted.length; i++){
            assertEquals("InsertionSort could not sort", sorted[i], afterSort[i]);
        }        
        
        // Check for 1 element array
        ic.data.clear();
        ic.data.add(1);
        ic.insertionSort();
        sorted = new int[]{1};
        afterSort = ic.data.toArray();
        for(int i = 0; i < sorted.length; i++){
            assertEquals("InsertionSort could not sort", sorted[i], afterSort[i]);
        }        
        
        // Check for sorted array
        ic.data = new LinkedList<Integer>(Arrays.asList(1,2,3,4,5,6,7,8,9,10));        
        ic.insertionSort();
        sorted = new int[]{1,2,3,4,5,6,7,8,9,10};
        afterSort = ic.data.toArray();
        for(int i = 0; i < sorted.length; i++){
            assertEquals("InsertionSort could not sort", sorted[i], afterSort[i]);
        }
        
        
        
    }
}
