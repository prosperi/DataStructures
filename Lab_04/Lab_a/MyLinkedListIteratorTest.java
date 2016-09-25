

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class MyLinkedListIteratorTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
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
        
        assertTrue("hasNext method does not return correct value", mlli.hasNext() == false);
        
        mll.addFirst(1);
        assertTrue("hasNext method does not return correct value", mlli.hasNext() == true);
        
        mll.addFirst(2);
        assertTrue("hasNext method does not return correct value", mlli.hasNext() == true);
        
        mlli.next();
        assertTrue("hasNext method does not return correct value", mlli.hasNext() == true);
        mlli.next();
        assertTrue("hasNext method does not return correct value", mlli.hasNext() == false);
        
        mll.addEnd(3);
        assertTrue("hasNext method does not return correct value", mlli.hasNext() == true);
        mlli.next();
        assertTrue("hasNext method does not return correct value", mlli.hasNext() == false);
        
    }
    
    @Test
    public void nextTest(){
        
        assertTrue("next method does not return correct value", mlli.next() == null);
        
        mll.addFirst(1);
        assertTrue("next method does not return correct value", (int)mlli.next().getValue() == 1);
        mll.addFirst(2);
        assertTrue("next method does not return correct value", mlli.next() == null);
        mll.addEnd(3);
        assertTrue("next method does not return correct value", (int)mlli.next().getValue() == 3);
        assertTrue("next method does not return correct value", mlli.next() == null);
        
        
    }
    
    @Test
    public void removeTest(){
        
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
        
        mlli.next();
        mlli.remove();
        assertTrue("Could not remove element", (int)mll.head.getValue() == 1);
        
        mlli.next();
        mlli.next();
        mlli.remove();
        assertTrue("Could not remove element", mll.head == mll.tail && (int)mll.head.getValue() == 1);
        
        assertTrue("Showed next element when ther should not be one", mlli.next() == null);
     
    }
}
