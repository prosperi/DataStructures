
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
public class LinkedListIntegerContainerTest
{
    
    LinkedListIntegerContainer llic;

    public LinkedListIntegerContainerTest()
    {
    }

    @Before
    public void setUp()
    {
        // Create new instance of IntegerContainer, this should run only once , when class
        // is initialized, but in this case, it will run after completion of each test method
        // because of BlueJ's bug.
        llic = new LinkedListIntegerContainer();
    }

    @After
    public void tearDown()
    {
        llic.data.clear();
    }
    
    @Test
    public void addToFrontTest(){      
        // initialize IntegerContainer's data variable
        llic.data = new LinkedList<Integer>();
        int size = llic.data.size();
        
        // add 100 to container, and check its size and first element. As container is empty yet, 
        // its size will be 1 and the first element 100
        llic.addToFront(100);
        assertTrue("Could not add number in front", llic.data.get(0) == 100 && llic.data.size() == (size+1));
        
        // add 100 more elements container and check size, than we check each element, and make
        // sure each of them is on its place 
        for(int i = 99; i >= 0; i--){
            llic.addToFront(i);
        }
        assertTrue(llic.data.size() == 101);
        
        for(int j = 0; j <= 100; j++){
            assertTrue("Could not add int the front", j == llic.data.get(j));
        }
        
        
        // add numbers from -100 to -1(inclusive), and check size and elemens again. size should
        // equal to 201 and first element should equal to -100
        for(int i = -1; i >= -100; i--){
            llic.addToFront(i);
        }
        
        assertTrue(llic.data.size() == 201);
        for(int j = -100; j <= 100; j++){
            assertTrue("Could not add int the front", j == llic.data.get(j+100));
        }
    }
    
    @Test
    public void addFromEndTest(){      
        // initialize IntegerContainer's data variable
        llic.data = new LinkedList<Integer>();
        int size = llic.data.size();
        
        // add 100 to container, and check its size and first element. As container is empty yet, 
        // its size will be 1 and the first element 100
        llic.addFromEnd(100);
        assertTrue("Could not add number in front", llic.data.get(0) == 100 && llic.data.size() == (size+1));
        
        // add 100 more elements container and check size, than we check each element, and make
        // sure each of them is on its place 
        for(int i = 0; i <= 99; i++){
            llic.addFromEnd(i);
        }
        assertTrue(llic.data.size() == 101);
        
        for(int j = 1; j < 100; j++){
            assertTrue("Could not add int the front", (j-1) == llic.data.get(j));
        }
        
    }
    
    @Test
    public void insertionSortTest()
    {   
         // Check for unsorted array without repeating values
        llic.data = new LinkedList<Integer>(Arrays.asList(10,9,8,7,6,5,4,3,2,1));
        llic.insertionSort();
        int[] sorted = new int[]{1,2,3,4,5,6,7,8,9,10};
        Object[] afterSort = llic.data.toArray();
        for(int i = 0; i < sorted.length; i++){
            assertEquals("InsertionSort could not sort", sorted[i], afterSort[i]);
        }
        
        // Check for unsorted array with repeating values
        llic.data = new LinkedList<Integer>(Arrays.asList(1,2,3,4,1,6,7,1,9,2));        
        llic.insertionSort();
        sorted = new int[]{1,1,1,2,2,3,4,6,7,9};
        afterSort = llic.data.toArray();
        for(int i = 0; i < sorted.length; i++){
            assertEquals("InsertionSort could not sort", sorted[i], afterSort[i]);
        }
        
        // Check for unsorted array with the same values
        llic.data = new LinkedList<Integer>(Arrays.asList(1,1,1,1,1,1,1,1,1,1));
        llic.insertionSort();
        sorted = new int[]{1,1,1,1,1,1,1,1,1,1};
        afterSort = llic.data.toArray();
        for(int i = 0; i < sorted.length; i++){
            assertEquals("InsertionSort could not sort", sorted[i], afterSort[i]);
        }
                
        // Check for an empty array
        llic.data.clear();        
        llic.insertionSort();
        sorted = new int[]{};
        afterSort = llic.data.toArray();
        for(int i = 0; i < sorted.length; i++){
            assertEquals("InsertionSort could not sort", sorted[i], afterSort[i]);
        }        
        
        // Check for 1 element array
        llic.data.clear();
        llic.data.add(1);
        llic.insertionSort();
        sorted = new int[]{1};
        afterSort = llic.data.toArray();
        for(int i = 0; i < sorted.length; i++){
            assertEquals("InsertionSort could not sort", sorted[i], afterSort[i]);
        }        
        
        // Check for sorted array
        llic.data = new LinkedList<Integer>(Arrays.asList(1,2,3,4,5,6,7,8,9,10));        
        llic.insertionSort();
        sorted = new int[]{1,2,3,4,5,6,7,8,9,10};
        afterSort = llic.data.toArray();
        for(int i = 0; i < sorted.length; i++){
            assertEquals("InsertionSort could not sort", sorted[i], afterSort[i]);
        }
    }
}
