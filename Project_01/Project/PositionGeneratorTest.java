

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.Arrays;

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
        pGen = new PositionGenerator(100, 20, 30);
        int[] num = pGen.initPosition();
        assertTrue("Picked number out of range", num[0] >= 0 && num[0] < 30 && num[1] >= 0 && num[1] < 20);
        
        pGen = new PositionGenerator(0, 20, 20);
        num = pGen.initPosition();
        assertTrue("Picked number out of range", num[0] >= 0 && num[0] < 20 && num[1] >= 0 && num[1] < 20);
        
        pGen = new PositionGenerator(-100, 50, 70);
        num = pGen.initPosition();
        assertTrue("Picked number out of range", num[0] >= 0 && num[0] < 70 && num[1] >= 0 && num[1] < 50);
    }
    
    @Test()
    public void sequenceTest(){
        /// Check Sequences: check that the same seed provides same sequence each time
        
        int[][] arr = new int[100][2];
        pGen = new PositionGenerator(32, 10, 10);
        for(int i = 0; i < arr.length; i++){
            arr[i] = pGen.initPosition();
        }
        pGen = new PositionGenerator(32, 10, 10);
        for(int i = 0; i < arr.length; i++){
            int[] tmp = pGen.initPosition();
            for(int j = 0; j < arr[i].length; j++){
                assertTrue("The sequence is not the same", arr[i][j] == tmp[j]);
            }
        }
        
        
        arr = new int[100][2];
        pGen = new PositionGenerator(0, 11, 31);
        for(int i = 0; i < arr.length; i++){
            arr[i] = pGen.initPosition();
        }
        pGen = new PositionGenerator(0, 11, 31);
        for(int i = 0; i < arr.length; i++){
            int[] tmp = pGen.initPosition();
            for(int j = 0; j < arr[i].length; j++){
                assertTrue("The sequence is not the same", arr[i][j] == tmp[j]);
            }
        }
        
        
        arr = new int[100][2];
        pGen = new PositionGenerator(-11, 7, 9);
        for(int i = 0; i < arr.length; i++){
            arr[i] = pGen.initPosition();
        }
        pGen = new PositionGenerator(-11, 7, 9);
        for(int i = 0; i < arr.length; i++){
            int[] tmp = pGen.initPosition();
            for(int j = 0; j < arr[i].length; j++){
                assertTrue("The sequence is not the same", arr[i][j] == tmp[j]);
            }
        }
        
    }
}
