

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class WheelTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class WheelTest
{    
    private Wheel wheel;
    
    /**
     * Default constructor for test class WheelTest
     */
    public WheelTest()
    {
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
    }

    @Test()
    public void spinTest()
    {
        wheel = new Wheel(100);
        int num = wheel.spin();
        assertTrue("Picked number out of range", num >= 0 && num < wheel.bound);
        
        wheel = new Wheel(0);
        num = wheel.spin();
        assertTrue("Picked number out of range", num >= 0 && num < wheel.bound);
        
        wheel = new Wheel(-100);
        num = wheel.spin();
        assertTrue("Picked number out of range", num >= 0 && num < wheel.bound);
    }
    
    
}

