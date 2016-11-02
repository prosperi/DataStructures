

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.*;

/**
 * The test class RandomNumberGeneratorTest tests class RandomNumberGenerator.
 *
 * @version (10/15/2016)
 */
public class RandomNumberGeneratorTest
{
    /**
     * Default constructor for test class RandomNumberGeneratorTest
     */
    public RandomNumberGeneratorTest()
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
    
    /**
     * Tests that the returnRandom() method is not returning the same number each time it is called
     */
    @Test
    public void TestReturnRandom()
    {
        //tests that the returnRandom() method is not returning the same number each time it is called
        RandomNumberGenerator rnd1 = new RandomNumberGenerator();
        int num1 = rnd1.returnRandom(100);
        int num2 = rnd1.returnRandom(100);
        //because it is possible for random numbers to be duplicates, we will check returnRandom multiple
        //times
        if(num2==num1)
        {
            int num3 = rnd1.returnRandom(100);
            if(num3==num1)
            {
                int num4 = rnd1.returnRandom(100);
                if(num4==num1)
                {
                    //very low chance of this happening, so if it gets to this point, we can assume
                    //the random generator is not working properly
                    fail("The random number generator is not working properly");
                }
            }
        }
        else
        {
            assertTrue("The numbers are not random", (num2!=num1));
        }
    }
    
    /**
     * Tests that when two different RandomNumberGenerator objects are created, the sequence returnRandom() creates is different
     */
    @Test
    public void TestReturnRandom2()
    {
        //tests that when the RandomNumberGenerator class makes different objects, the sequence returnRandom() creates will not
        //be the same
        
        ArrayList<Integer> rIC = new ArrayList<Integer>();
        RandomNumberGenerator rnd1 = new RandomNumberGenerator();

        rIC.add(0,rnd1.returnRandom(100));
        rIC.add(0,rnd1.returnRandom(100));
        rIC.add(0,rnd1.returnRandom(100));

        ArrayList<Integer> rIC2 = new ArrayList<Integer>();
        RandomNumberGenerator rnd2 = new RandomNumberGenerator();

        rIC2.add(0,rnd2.returnRandom(100));
        rIC2.add(0,rnd2.returnRandom(100));
        rIC2.add(0,rnd2.returnRandom(100));

        assertTrue("The random number generator is producing the same sequences",(rIC.equals(rIC2)) == false);
    }
    
    /**
     * Tests that returnRandom() always produces numbers within its specified range
     */
    @Test
    public void TestReturnRandom3()
    {
        ArrayList<Integer> rIC = new ArrayList<Integer>();
        RandomNumberGenerator rnd1 = new RandomNumberGenerator();

        rIC.add(0,rnd1.returnRandom(10));
        rIC.add(0,rnd1.returnRandom(10));
        rIC.add(0,rnd1.returnRandom(10));
        rIC.add(0,rnd1.returnRandom(10));
        rIC.add(0,rnd1.returnRandom(10));
        rIC.add(0,rnd1.returnRandom(10));
        //all random numbers should be between 0 and 10
        
        for(int i = 0; i < rIC.size(); i++)
        {
            assertTrue("The random number generator is producing numbers out of its specified range",((rIC.get(i) <= 10) && (rIC.get(i) >= 0)));
        }
    }
    
    /**
     * Tests that the returnGaussian() method is not returning the same number each time it is called
     */
    @Test
    public void TestReturnGaussian()
    {
        //tests that the returnGaussian() method is not returning the same number each time it is called
        RandomNumberGenerator rnd1 = new RandomNumberGenerator();
        int num1 = rnd1.returnGaussian(200,50);
        int num2 = rnd1.returnGaussian(200,50);
        //because it is possible for random numbers to be duplicates, we will check returnRandom multiple
        //times
        if(num2==num1)
        {
            int num3 = rnd1.returnGaussian(200,50);
            if(num3==num1)
            {
                int num4 = rnd1.returnGaussian(200,50);
                if(num4==num1)
                {
                    //very low chance of this happening, so if it gets to this point, we can assume
                    //the random generator is not working properly
                    fail("The random number generator is not working properly");
                }
            }
        }
        else
        {
            assertTrue("The numbers are not random", (num2!=num1));
        }
    }
    
    /**
     * Tests that when two different RandomNumberGenerator objects are created, the sequence returnGaussian() creates is different
     */
    @Test
    public void TestReturnGaussian2()
    {
        //tests that when the RandomNumberGenerator class makes different objects, the sequence returnGaussian() creates will not
        //be the same
        
        ArrayList<Integer> rIC = new ArrayList<Integer>();
        RandomNumberGenerator rnd1 = new RandomNumberGenerator();

        rIC.add(0,rnd1.returnGaussian(200,50));
        rIC.add(0,rnd1.returnGaussian(200,50));
        rIC.add(0,rnd1.returnGaussian(200,50));

        ArrayList<Integer> rIC2 = new ArrayList<Integer>();
        RandomNumberGenerator rnd2 = new RandomNumberGenerator();

        rIC2.add(0,rnd2.returnGaussian(200,50));
        rIC2.add(0,rnd2.returnGaussian(200,50));
        rIC2.add(0,rnd2.returnGaussian(200,50));

        assertTrue("The random number generator is producing the same sequences",(rIC.equals(rIC2)) == false);
    }
    
    /**
     * Tests that returnGaussian() returns numbers within desired normal distribution
     */
    @Test
    public void TestReturnGaussian3()
    {
        RandomNumberGenerator rnd1 = new RandomNumberGenerator();
        int num1 = rnd1.returnGaussian(15,2);
        
        if((num1 > 21) && (num1 < 9)) //is number within 3 standard deviations of median? If not, test another number to be sure
        {
            int num2 = rnd1.returnGaussian(15,2);
            if((num2 > 21) && (num2 < 9))
            {
                fail("The returnGaussian() method is not working correctly");
            }
        }
        else //number is within 3 standard deviations of median
        {
            assertTrue("The returnGaussian() method is not working correctly",((21 >= num1) && (9 <= num1)));
        }
    }
}
