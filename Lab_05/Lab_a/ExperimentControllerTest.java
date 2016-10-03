 

import static org.junit.Assert.*;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.Ignore;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.FileWriter;

/**
 * Zura Mestiashvili
 * v1.0.0
 */

public class ExperimentControllerTest
{
    private static ExperimentController ec;
    private static PrintWriter outputFile;
    private static int constant = 5;
   

   
    @BeforeClass
    public static void classSetUp()
    {
        
    }
    
    @AfterClass
    public static void classCleanUp()
    {
    }
    
    @Before
    public void setUp(){
        // This should be in Class setup, but because of the bug I have to write here, and this is a bad practice
        ec = new ExperimentController();
        
        try{
            outputFile = new PrintWriter(new FileWriter("data.csv", true));
        }catch(IOException e){
            System.out.append("Problem with output file!!!");
        }
        //////////////////// Until this line
        
    }
    
    @After
    public void cleanUp(){
        outputFile.append('\n');
        outputFile.close(); // This should be in AfterClass method, but... bluej has the bug mentioned above
        System.out.println("Finished another");
    }
    

    
    @Test
    public void timeSortOfUnsortedListTest()
    {        
        long alqsTime = 0;
        long llqsTime = 0;
        long almsTime = 0;
        long llmsTime = 0;
        for(int i = 1000, j = 0; j < constant; i += 500, j++){
            // clear containers
            ec.alqs.clearAL();
            ec.llqs.clearAL();
            ec.alms.clearAL();
            ec.llms.clearAL();
            // use addToFront method to add new data
            ec.add(i, j);

            
            alqsTime = ec.alqsSortTime();
            llqsTime = ec.llqsSortTime();
            almsTime = ec.almsSortTime();
            llmsTime = ec.llmsSortTime();
            
            outputFile.append(alqsTime + "," + llqsTime + "," + almsTime + "," + llmsTime + '\n');
        }
        
        
        

    }
    
    
    
}



