

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class PathTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class PathTest
{
    Path path;
    
    /**
     * Default constructor for test class PathTest
     */
    public PathTest()
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
        path = new Path();
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

    // as all the other methods are setters and getter, we 
    // will just test toString method
    @Test
    public void testToString(){
        World world = new World(5, 5, 5);
        Cell cell_01 = new Cell(world, 0, 0);
        
        path.add(cell_01);
        assertTrue("Could not add the cell", path.getCell(0) == cell_01);
        
        Cell cell_02 = new Cell(world, 0, 1);
        path.add(cell_02);
        assertTrue("Could not add the cell", path.getCell(1) == cell_02);
        
        Cell cell_03 = new Cell(world, 0, 2);
        path.add(cell_03);
        assertTrue("Could not add the cell", path.getCell(2) == cell_03);
        
        String str = "0 0   0 1   0 2   ";
        assertTrue("Could not create a correct representation of String", str.compareTo(path.toString()) == 0);
        
        
    }
    
}
