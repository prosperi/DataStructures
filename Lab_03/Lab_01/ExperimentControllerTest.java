 

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
    private static ArrayListIntegerContainer arrContainer;
    private static LinkedListIntegerContainer linkedContainer;
    private static PrintWriter outputFile;
    private static int constant = 20;
   

   
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
        arrContainer = new ArrayListIntegerContainer();
        linkedContainer = new LinkedListIntegerContainer();
        
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
    public void timeAddToFrontTest()
    {        
        long arrTime = 0;
        long linkedTime = 0;
        outputFile.append("Add to Front Test \n");
        for(int i = 1000, j = 0; j < constant; i += 500, j++){
            // clear containers
            arrContainer.data.clear();
            linkedContainer.data.clear();
            // use addToFront method to add new data
            arrTime = ec.timeAddToFront(arrContainer, i, j);
            linkedTime = ec.timeAddToFront(linkedContainer, i, j);

            
            outputFile.append(arrTime + "," + linkedTime + '\n');
        }
                
    }
    
    
    @Test
    public void timeSortOfUnsortedListTest()
    {        
        long arrTime = 0;
        long linkedTime = 0;
        outputFile.append("Unsorted Test \n");
        for(int i = 1000, j = 0; j < constant; i += 500, j++){
            // clear containers
            arrContainer.data.clear();
            linkedContainer.data.clear();
            // use addToFront method to add new data
            ec.timeAddToFront(arrContainer, i, j);
            ec.timeAddToFront(linkedContainer, i, j);
            
            arrTime = ec.timeSortOfUnsortedList(arrContainer);
            linkedTime = ec.timeSortOfUnsortedList(linkedContainer);
            
            outputFile.append(arrTime + "," + linkedTime + '\n');
        }
        
        
        

    }
    
    @Test
    public void timeSortOfSortedListTest()
    {        
        long arrTime = 0;
        long linkedTime = 0;
        outputFile.append("Sorted Test \n");
        for(int i = 1000, j = 0; j < constant; i += 500, j++){
            arrContainer.data.clear();
            linkedContainer.data.clear();
            
            // create sorted list to test later
            for(int k = 0; k < i; k++){
                arrContainer.data.add(k);
                linkedContainer.data.add(k);
            }
            
            // test sorted list
            arrTime = ec.timeSortOfSortedList(arrContainer);
            linkedTime = ec.timeSortOfSortedList(linkedContainer);
            
            outputFile.append(arrTime + "," + linkedTime + '\n');
        }

        
    }
    
}



