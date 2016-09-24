

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Zura Mestiashvili
 * v1.0.0
 */
public class MyLinkedListTest{
    
    
    public MyLinkedListTest()
    {
    }

    @Before
    public void setUp()
    {
    }

    @After
    public void tearDown()
    {
    }
    
    @Test
    public void testAddFirst(){
        MyLinkedList mll = new MyLinkedList();
        assertTrue("Head does not equal to null", mll.head == null);
        assertTrue("Tail does not equal to null", mll.tail == null);
        
        mll.addFirst(3);
        assertTrue("Head and tail initialization problem", mll.head == mll.tail);
        assertTrue("Head and tail did not get the passed arguments", mll.tail.getValue() == 3 && mll.tail.getNext() == null);
        
        mll.addFirst(2);
        assertTrue("Head equals to tail, while it shouldn't", mll.head != mll.tail);
        assertTrue("Head did not get correct values", mll.head.getValue() == 2 && mll.head.getNext() == mll.tail);
     
        mll.addFirst(1);
        assertTrue("Head does not have correct value", mll.head.getValue() == 1);
        assertTrue("Head did not get correct reference", mll.head.getNext().getValue() == 2);
    }
    
    @Test
    public void testAddEnd(){
        MyLinkedList mll = new MyLinkedList();
        assertTrue("Head does not equal to null", mll.head == null);
        assertTrue("Tail does not equal to null", mll.tail == null);
        
        mll.addEnd(1);
        assertTrue("Head and tail initialization problem", mll.head == mll.tail);
        assertTrue("Head and tail did not get the passed arguments", mll.tail.getValue() == 1 && mll.tail.getNext() == null);
        
        mll.addEnd(2);
        assertTrue("Head equals to tail, while it shouldn't", mll.head != mll.tail);
        assertTrue("Tail did not get correct values", mll.tail.getValue() == 2 && mll.head.getNext() == mll.tail);
     
        mll.addEnd(3);
        assertTrue("Head does not have correct value", mll.tail.getValue() == 3);
        assertTrue("Head did not get correct reference", mll.tail.getNext() == null);
    }
    
    @Test
    public void testIterator(){
        MyLinkedList mll = new MyLinkedList();
      
        assertTrue("Could not return new MyLinkedListIterator", (mll.iterator() instanceof MyLinkedListIterator));
    }
}
