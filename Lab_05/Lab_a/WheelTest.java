

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
    
    public WheelTest()
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

    @Test()
    public void spinTest()
    {
        wheel = new Wheel(100);
        int num = Integer.parseInt(wheel.spin());
        assertTrue("Picked number out of range", num >= 0 && num < wheel.bound);
        
        wheel = new Wheel(0);
        num = Integer.parseInt(wheel.spin());
        assertTrue("Picked number out of range", num >= 0 && num < wheel.bound);
        
        wheel = new Wheel(-100);
        num = Integer.parseInt(wheel.spin());
        assertTrue("Picked number out of range", num >= 0 && num < wheel.bound);
    }
    
    @Test()
    public void sequenceTest(){
        /// Check Sequences: check that the same seed provides same sequence each time
        
        int[] arr = new int[100];
        wheel = new Wheel(32);
        for(int i = 0; i < arr.length; i++){
            arr[i] = Integer.parseInt(wheel.spin());
        }
        
        wheel = new Wheel(32);
        for(int i = 0; i < arr.length; i++){
            assertTrue("The sequence is not the same", arr[i] == Integer.parseInt(wheel.spin()));
        }
        
        
        arr = new int[100];
        wheel = new Wheel(0);
        for(int i = 0; i < arr.length; i++){
            arr[i] = Integer.parseInt(wheel.spin());
        }
        wheel = new Wheel(0);
        for(int i = 0; i < arr.length; i++){
            assertTrue("The sequence is not the same", arr[i] == Integer.parseInt(wheel.spin()));
        }
        
        
        arr = new int[100];
        wheel = new Wheel(-11);
        for(int i = 0; i < arr.length; i++){
            arr[i] = Integer.parseInt(wheel.spin());
        }
        wheel = new Wheel(-11);
        for(int i = 0; i < arr.length; i++){
            assertTrue("The sequence is not the same", arr[i] == Integer.parseInt(wheel.spin()));
        }
    }
}
