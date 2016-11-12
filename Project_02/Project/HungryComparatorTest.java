

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.*;

public class HungryComparatorTest
{
    
    World world;
    HungryComparator comparator;
    TreeSet<ArrayList<Path>> container;
    
    /**
     * Default constructor for test class HungryComparatorTest
     */
    public HungryComparatorTest()
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
        comparator = new HungryComparator();
        world = new World(10, 10, 10);  
        container = new TreeSet<ArrayList<Path>>(comparator);
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
    
    @Test
    public void testHungryComparator(){
        ArrayList<Path> arr_01 = new ArrayList<Path>();
        Path foo_01 = new Path();
        Path boo_01 = new Path();
        Cell foo_cell_01 = new Cell(world, 0, 0);
        Cell foo_cell_02 = new Cell(world, 0, 1);
        Cell foo_cell_03 = new Cell(world, 0, 2);
        foo_01.add(foo_cell_01);
        foo_01.add(foo_cell_02);
        foo_01.add(foo_cell_03);
        foo_01.setDeadEnd(3);
        arr_01.add(boo_01);
        arr_01.add(foo_01);
        
        ArrayList<Path> arr_02 = new ArrayList<Path>();
        Path foo_02 = new Path();
        Path boo_02 = new Path();
        foo_cell_01 = new Cell(world, 1, 0);
        foo_cell_02 = new Cell(world, 1, 1);
        foo_cell_03 = new Cell(world, 1, 2);
        foo_02.add(foo_cell_01);
        foo_02.add(foo_cell_02);
        foo_02.add(foo_cell_03);
        foo_02.setDeadEnd(2);
        arr_02.add(boo_02);
        arr_02.add(foo_02);
        
        ArrayList<Path> arr_03 = new ArrayList<Path>();
        Path foo_03 = new Path();
        Path boo_03 = new Path();
        foo_cell_01 = new Cell(world, 2, 0);
        foo_cell_02 = new Cell(world, 2, 1);
        foo_cell_03 = new Cell(world, 2, 2);
        foo_03.add(foo_cell_01);
        foo_03.add(foo_cell_02);
        foo_03.add(foo_cell_03);
        foo_03.setDeadEnd(1);
        arr_03.add(boo_03);
        arr_03.add(foo_03);
        
        ArrayList<Path> arr_04 = new ArrayList<Path>();
        Path foo_04 = new Path();
        Path boo_04 = new Path();
        foo_cell_01 = new Cell(world, 3, 0);
        foo_cell_02 = new Cell(world, 3, 1);
        foo_04.add(foo_cell_01);
        foo_04.add(foo_cell_02);
        foo_04.setDeadEnd(4);
        foo_04.setPlantOnTheWay(true);
        arr_04.add(boo_04);
        arr_04.add(foo_04);
        
        ArrayList<Path> arr_05 = new ArrayList<Path>();
        Path foo_05 = new Path();
        Path boo_05 = new Path();
        foo_cell_01 = new Cell(world, 4, 0);
        foo_cell_02 = new Cell(world, 4, 1);
        foo_05.add(foo_cell_01);
        foo_05.add(foo_cell_02);
        foo_05.setDeadEnd(0);
        arr_05.add(boo_05);
        arr_05.add(foo_05);
        
        container.add(arr_01);
        assertTrue("Could not sort correctly", container.first().get(1) == arr_01.get(1));
        container.add(arr_02);
        assertTrue("Could not sort correctly", container.first().get(1) == arr_02.get(1));
        container.add(arr_03);
        assertTrue("Could not sort correctly", container.first().get(1) == arr_03.get(1));
        container.add(arr_04);
        assertTrue("Could not sort correctly", container.first().get(1) == arr_04.get(1));
        container.add(arr_05);
        assertTrue("Could not sort correctly", container.last().get(1) == arr_05.get(1));
    }
}
