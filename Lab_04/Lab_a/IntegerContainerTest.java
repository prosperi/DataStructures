import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Arrays;

/**
 * Zura Mestiashvili
 * v1.0.0
 */
public class IntegerContainerTest
{
    private static IntegerContainer ic;
    
    public IntegerContainerTest()
    {
    }
    
    @BeforeClass
    public static void classSetUp(){
        // Create new instance of IntegerContainer, this should run only once , when class
        // is initialized, but in this case, it will run after completion of each test method
        // because of BlueJ's bug.
        ic = new IntegerContainer();
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
        ic.data.clear();
    }
    
}