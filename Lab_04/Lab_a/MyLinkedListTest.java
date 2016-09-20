

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Zura Mestiashvili
 * v1.0.0
 */
public class MyLinkedListTest{
    
    MyLinkedList mll;
    
    public MyLinkedListTest()
    {
    }

    @Before
    public void setUp()
    {
        mll = new MyLinkedList();
    }

    @After
    public void tearDown()
    {
    }
    
    @Test
    public void testAddFirst(){
        mll.addFirst(1);
        mll.addEnd(2);
        mll.addEnd(3);
        mll.addEnd(4);
        mll.addEnd(5);
        mll.addEnd(6);
        mll.addEnd(7);
        
        System.out.println(mll.head.getValue() + " " + mll.tail.getValue());
        
    }
}
