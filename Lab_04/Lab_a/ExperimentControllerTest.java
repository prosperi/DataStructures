

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Zura Mestiashvili
 * v1.0.0
 */
public class ExperimentControllerTest
{
    private ExperimentController ec;
    private MyListIntegerContainer mlic;
    private LinkedListIntegerContainer llic;
    private static final int NUM_OF_CONTAINERS = 3;

 
    public ExperimentControllerTest()
    {
    }

    
    @Before
    public void setUp()
    {
        ec = new ExperimentController();
        mlic = new MyListIntegerContainer();
        llic = new LinkedListIntegerContainer();
    }

    @After
    public void tearDown()
    {
    }
    
    
    @Test
    public void timeAddToFrontTest()
    {        
        long mlicTime = 0;
        long llicTime = 0;

        for(int i = 1000, j = 0; j < NUM_OF_CONTAINERS; i += 500, j++){
            // clear containers
            mlic = new MyListIntegerContainer();
            llic.data.clear();
            // use addToFront method to add new data
            mlicTime = ec.timeAddToFront(mlic, i, j);
            llicTime = ec.timeAddToFront(llic, i, j);

        }
                
    }
    
     @Test
    public void timeAddFromEndTest()
    {        
        long mlicTime = 0;
        long llicTime = 0;

        for(int i = 1000, j = 0; j < NUM_OF_CONTAINERS; i += 500, j++){
            // clear containers
            mlic = new MyListIntegerContainer();
            llic.data.clear();
            // use addToFront method to add new data
            mlicTime = ec.timeAddFromEnd(mlic, i, j);
            llicTime = ec.timeAddFromEnd(llic, i, j);

        }
                
    }
}
