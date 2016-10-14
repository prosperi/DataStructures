

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author  Zura Mestiashvili
 * @version v1.0.0
 */
public class DirectionGeneratorTest
{
    DirectionGenerator dGen;
    
    public DirectionGeneratorTest(){
    }


    @Before
    public void setUp()
    {
        dGen = new DirectionGenerator(11);
    }

    @After
    public void tearDown()
    {
    
    }
    
    @Test
    public void testNext(){
        // Tests that next() returns LEFT, UP, RIGHT or DOWN
        Direction direction = dGen.next();
        assertTrue("Direction generator does not work correctly", direction == Direction.LEFT || direction == Direction.RIGHT || direction == Direction.UP || direction == Direction.DOWN ||
                                                                  direction == Direction.UP_LEFT || direction == Direction.UP_RIGHT || direction == Direction.DOWN_LEFT || direction == Direction.DOWN_RIGHT);
        
        // try with different seed
        dGen = new DirectionGenerator(31);
        assertTrue("Direction generator does not work correctly", direction == Direction.LEFT || direction == Direction.RIGHT || direction == Direction.UP || direction == Direction.DOWN ||
                                                                  direction == Direction.UP_LEFT || direction == Direction.UP_RIGHT || direction == Direction.DOWN_LEFT || direction == Direction.DOWN_RIGHT);
        
        dGen = new DirectionGenerator(100);
        assertTrue("Direction generator does not work correctly", direction == Direction.LEFT || direction == Direction.RIGHT || direction == Direction.UP || direction == Direction.DOWN ||
                                                                  direction == Direction.UP_LEFT || direction == Direction.UP_RIGHT || direction == Direction.DOWN_LEFT || direction == Direction.DOWN_RIGHT);
    
    }
}
