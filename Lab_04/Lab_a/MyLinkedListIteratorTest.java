

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Zura Mestiashvili
 * v1.0.0
 */
public class MyLinkedListIteratorTest
{
    MyLinkedListIterator<Integer> mlli;
    MyLinkedList<Integer> mll;
    
    public MyLinkedListIteratorTest()
    {
    }

    @Before
    public void setUp()
    {
        mll = new MyLinkedList<Integer>();
        mlli = new MyLinkedListIterator<Integer>(mll);
    }

    
    @After
    public void tearDown()
    {
    }
    
    @Test
    public void hasNextTest(){
        //Test that hasNext method works correctly
        // at first hasNext should return false, as there ar no objects in List
        assertTrue("hasNext method does not return correct value", mlli.hasNext() == false);
        // After adding 1 to the list, hasNext() will return true
        mll.addFirst(1);
        assertTrue("hasNext method does not return correct value", mlli.hasNext() == true);
        
        mll.addFirst(2);
        assertTrue("hasNext method does not return correct value", mlli.hasNext() == true);
        
        mlli.next();
        assertTrue("hasNext method does not return correct value", mlli.hasNext() == true);
        mlli.next();
        // as there are just 2 elements in list and next() was called twice, hasNext() should return false
        assertTrue("hasNext method does not return correct value", mlli.hasNext() == false);
        mll.addEnd(3);
        assertTrue("hasNext method does not return correct value", mlli.hasNext() == true);
        mlli.next();
        assertTrue("hasNext method does not return correct value", mlli.hasNext() == false);
        
    }
    
    @Test
    public void nextTest(){
        // test that next() method works correctly
        assertTrue("next method does not return correct value", mlli.next() == null);
        
        mll.addFirst(1);
        assertTrue("next method does not return correct value", (int)mlli.next().getValue() == 1);
        // after adding 2 as first element next will still provide null, as the current node is the tail of the list
        mll.addFirst(2);
        assertTrue("next method does not return correct value", mlli.next() == null);
        // after adding 3 to the end, next() method will return recently added node;
        mll.addEnd(3);
        assertTrue("next method does not return correct value", (int)mlli.next().getValue() == 3);
        assertTrue("next method does not return correct value", mlli.next() == null);
        
        
    }
    
    @Test
    public void removeTest(){
        // Test that remove() method works correctly
        // calling remove until calling next() method throws exception
        try {
            mlli.remove();
        }catch(IllegalStateException e){
            System.out.println("worked correctly");
        }
        
        mll.addFirst(1);
        mlli.next();
        mlli.remove();
        assertTrue("Could not remove element", mll.head == null && mll.tail == null);
        
        mll.addFirst(1);
        mll.addFirst(2);
        mll.addEnd(3);
        // after filling the list the sequence will be 2,1,3, calling next and removing will cause 1 becoming the head.value
        mlli.next();
        mlli.remove();
        assertTrue("Could not remove element", (int)mll.head.getValue() == 1);
        
        // Let's remove node with value 3
        mlli.next();
        mlli.next();
        mlli.remove();
        assertTrue("Could not remove element", mll.head == mll.tail && (int)mll.head.getValue() == 1);
        
        assertTrue("Showed next element when ther should not be one", mlli.next() == null);
     
    }
}
