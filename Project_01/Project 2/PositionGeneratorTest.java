

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;

/**
 * Zura Mestiashvili
 * v1.0.0
 */
public class PositionGeneratorTest
{
      
    PositionGenerator pGen;
    
    public PositionGeneratorTest()
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
    public void initPositionTest()
    {
        // test that Position Generator return position that is inside border
        pGen = new PositionGenerator(100, 20, 30);
        ArrayList<Integer> num = pGen.initPosition();
        assertTrue("Picked number out of range", num.get(0) >= 0 && num.get(0) < 30 && num.get(1) >= 0 && num.get(1) < 20);
        
        // test the same functionality with different seed and different size of board
        pGen = new PositionGenerator(0, 20, 20);
        num = pGen.initPosition();
        assertTrue("Picked number out of range", num.get(0) >= 0 && num.get(0) < 20 && num.get(1) >= 0 && num.get(1) < 20);
        
        // test the same functionality with different seed and different size of board
        pGen = new PositionGenerator(-100, 50, 70);
        num = pGen.initPosition();
        assertTrue("Picked number out of range", num.get(0) >= 0 && num.get(0) < 70 && num.get(1) >= 0 && num.get(1) < 50);
    }
    
    @Test()
    public void sequenceTest(){
        /// Check Sequences: check that the same seed provides same sequence each time
        /// repeat test for different sizes of the board and different values of seed
        ArrayList<ArrayList<Integer>> arr = new ArrayList<ArrayList<Integer>>();
        pGen = new PositionGenerator(32, 10, 10);
        for(int i = 0; i < arr.size(); i++){
            arr.set(i, pGen.initPosition());
        }
        pGen = new PositionGenerator(32, 10, 10);
        for(int i = 0; i < arr.size(); i++){
            ArrayList<Integer> tmp = pGen.initPosition();
            for(int j = 0; j < arr.get(i).size(); j++){
                assertTrue("The sequence is not the same", arr.get(i).get(j) == tmp.get(j));
            }
        }
        
        
        arr = new ArrayList<ArrayList<Integer>>();
        pGen = new PositionGenerator(0, 11, 31);
        for(int i = 0; i < arr.size(); i++){
            arr.set(i, pGen.initPosition());
        }
        pGen = new PositionGenerator(0, 11, 31);
        for(int i = 0; i < arr.size(); i++){
            ArrayList<Integer> tmp = pGen.initPosition();
            for(int j = 0; j < arr.get(i).size(); j++){
                assertTrue("The sequence is not the same", arr.get(i).get(j) == tmp.get(j));
            }
        }
        
        
        arr = new ArrayList<ArrayList<Integer>>();
        pGen = new PositionGenerator(-11, 7, 9);
        for(int i = 0; i < arr.size(); i++){
            arr.set(i, pGen.initPosition());
        }
        pGen = new PositionGenerator(-11, 7, 9);
        for(int i = 0; i < arr.size(); i++){
            ArrayList<Integer> tmp = pGen.initPosition();
            for(int j = 0; j < arr.get(i).size(); j++){
                assertTrue("The sequence is not the same", arr.get(i).get(j) == tmp.get(j));
            }
        }
        
    }
}
