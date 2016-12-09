

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.Arrays;

/**
 * Zura Mestiashvili
 * v1.0.0
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
    public void testSpinInteger()
    {
        wheel = new Wheel(1);
        String str = wheel.spinString();
        String[] arr = str.split(" ");
        assertTrue("Could not create correct random value", wheel.getFoo().contains(arr[0]));
        assertTrue("Could not create correct random value", wheel.getBoo().contains(arr[1]));
        
        arr[0] = "a";
        arr[1] = "b";
        assertTrue("Could not create correct random value", !wheel.getFoo().contains(arr[0]));
        assertTrue("Could not create correct random value", !wheel.getBoo().contains(arr[1]));
     }
    
    @Test()
    public void sequenceTest(){
        /// Check Sequences: check that the same seed provides same sequence each time
        
        String[] arr = new String[100];
        wheel = new Wheel(32);
        for(int i = 0; i < arr.length; i++){
            arr[i] = wheel.spinString();
        }
        wheel = new Wheel(32);
        for(int i = 0; i < arr.length; i++){
            assertTrue("The sequence is not the same", arr[i].equals(wheel.spinString()));
        }
        
        
        arr = new String[100];
        wheel = new Wheel(0);
        for(int i = 0; i < arr.length; i++){
            arr[i] = wheel.spinString();
        }
        wheel = new Wheel(0);
        for(int i = 0; i < arr.length; i++){
            assertTrue("The sequence is not the same", arr[i].equals(wheel.spinString()));
        }
        
        
        arr = new String[100];
        wheel = new Wheel(-11);
        for(int i = 0; i < arr.length; i++){
            arr[i] = wheel.spinString();
        }
        wheel = new Wheel(-11);
        for(int i = 0; i < arr.length; i++){
            assertTrue("The sequence is not the same", arr[i].equals(wheel.spinString()));
        }
    }
}
