import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Zura Mestiashvili
 * v1.0.0
 */
public class MyListIntegerContainerTest
{
    MyListIntegerContainer mlic;
    
    public MyListIntegerContainerTest()
    {
    }

   
    @Before
    public void setUp()
    {
        mlic = new MyListIntegerContainer();
    }

    
    @After
    public void tearDown()
    {
    }
    
    @Test
    public void addToFrontTest(){
        // check that addToFront method works correctly
        mlic.addToFront(3);
        assertTrue("Could not add value in front", (int)mlic.data.head.getValue() == 3);
        mlic.addToFront(2);
        assertTrue("Could not add value in front", (int)mlic.data.head.getValue() == 2);
        mlic.addToFront(1);
        assertTrue("Could not add value in front", (int)mlic.data.head.getValue() == 1);
        
        
    }
    
    @Test
    public void addFromEndTest(){
        // check that addFromEnd method works correctly
        mlic.addFromEnd(3);
        assertTrue("Could not add value from end", (int)mlic.data.tail.getValue() == 3);
        mlic.addFromEnd(2);
        assertTrue("Could not add value from end", (int)mlic.data.tail.getValue() == 2);
        mlic.addFromEnd(1);
        assertTrue("Could not add value from end", (int)mlic.data.tail.getValue() == 1);
        
        
    }
    
    @Test
    public void toStringTest(){
        // check that toString method works correctly
        // at first it should return empty String, as there are no nodes in the list
        assertTrue("Returned string value is wrong", mlic.toString().equals(""));
        mlic.addToFront(3);
        mlic.addToFront(2);
        mlic.addToFront(1);
        assertTrue("Returned string value is wrong", mlic.toString().equals("1 2 3 "));
        
        mlic.addFromEnd(3);
        mlic.addFromEnd(2);
        mlic.addFromEnd(1);
        assertTrue("Returned string value is wrong", mlic.toString().equals("1 2 3 3 2 1 "));
    }
    
    
}
