

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GraphExperimentTest
{
    GraphExperiment exp;
    /**
     * Default constructor for test class LauncherTest
     */
    public GraphExperimentTest()
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
       exp = new GraphExperiment();
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
    public void testSolveGraph(){
        String[] args = new String[]{"graph.txt"};
        exp.main(args);
        
        String str = "1 --> 3 --> 7 --> 5 || 4\nNo path from 1 to 9\n8 --> 3 --> 7 --> 5 || 4\n8 --> 9 || 1\n";
        assertTrue("Could not print correct short pathes", str.equals(exp.solveGraph()));
    }
    
    @Test
    public void testMain(){
        String[] args = new String[]{"graph.txt"};
        int[] edges = new int[]{0, 2, 2, 3, 2, 0, 2, 1, 2, 0};
        exp.main(args);
        
        for(int i = 1; i < exp.graph.getMap().size(); i++){
            assertTrue("Could not instantiate correctly", exp.graph.getMap().get(i) != null);
            assertTrue("Could not instantiate correctly", exp.graph.getMap().get(i).getOutgoing().size() == edges[i]);
        }
    }
    
}
