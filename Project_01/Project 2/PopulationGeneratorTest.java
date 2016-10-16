

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class PopulationGeneratorTest.
 *
 * @author  Zura Mestiashvili
 * @version v1.0.0
 */
public class PopulationGeneratorTest
{
    PopulationGenerator pGen;
    public PopulationGeneratorTest()
    {
    }

    @Before
    public void setUp()
    {
        pGen = new PopulationGenerator(11);
    }

    @After
    public void tearDown()
    {
    }
    
    @Test
    public void testNext(){
        for(int i = 100, j = 10; i <= 1000; i += 100, j += 10){
            int tmp = pGen.next(i, j);
            
            assertTrue("Returned number out of range " + i + " " + j + " " + tmp, tmp >= 0 && tmp <= 2*i);
        }
    }
    
}
