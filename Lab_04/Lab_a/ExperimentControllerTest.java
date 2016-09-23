

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class ExperimentControllerTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class ExperimentControllerTest
{
    ExperimentController ec;
    MyLinkedListIntegerContainer mlic;

 
    public ExperimentControllerTest()
    {
    }

    
    @Before
    public void setUp()
    {
        ec = new ExperimentController();
        mlic = new MyLinkedListIntegerContainer();
    }

    @After
    public void tearDown()
    {
    }
    
    @Test
    public void timeAddToFrontTest(){
        ec.timeAddToFront(mlic, 100, 12);
        ec.timeAddFromEnd(mlic, 100, 11);
        
        System.out.println(mlic.data.head.getValue());
        System.out.println(mlic.data.tail.getValue());
        System.out.println(mlic);
    }
}
