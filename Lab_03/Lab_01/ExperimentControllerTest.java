import static org.junit.Assert.*;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Ignore;
import java.io.PrintWriter;
import java.io.IOException;

/**
 * The test class ExperimentControllerTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class ExperimentControllerTest
{
    private static ExperimentController ec;
    private static ArrayListIntegerContainer arrContainer;
    private static LinkedListIntegerContainer linkedContainer;
    private static PrintWriter outputFile;
   
    public ExperimentControllerTest()
    {
    }
    

    // As it seems there is bug in BlueJ, Junit's AfterClass and BeforeClass do not work, therefore
    // I have to initialize vlass variables in first test
    // http://bugs.bluej.org/browse/BLUEJ-437
    // I'm moving on Eclipse
    @BeforeClass
    public static void classSetUp()
    {
        /*ec = new ExperimentController();
        arrContainer = new ArrContainer();
        linkedContainer = new linkedContainer();
        try{
            outputFile = new PrintWriter("data.csv", "UTF-8");
        }catch(IOException e){
            System.out.println("Problem with output file!!!");
        }*/
        
    }
    
    
    
    @Test
    public void timeAddToFrontTest()
    {
        
        /*assertFalse("Time is not calculated correctly", ec.timeAddToFront(10000, 100) < 0);
        assertTrue("numberOfItems should be positive", ec.timeAddToFront(0, 100) == -1);
        assertTrue("numberOfItems should be positive", ec.timeAddToFront(-10, 100) == -1);*/
        
        long arrTime = ec.timeAddToFront(arrContainer, 10000, 100);
        long linkedTime = ec.timeAddToFront(linkedContainer, 10000, 100);
        
        outputFile.append(arrTime + "," + linkedTime);
        System.out.println(arrTime + "," + linkedTime);
        outputFile.close();
        
    }
    
    
    @Test
    public void timeSortOfUnsortedListTest()
    {
        //assertFalse("Time is not calculated correctly", ec.timeSortOfUnsortedList() < 0);
        
        long arrTime = ec.timeSortOfUnsortedList(arrContainer);
        long linkedTime = ec.timeSortOfUnsortedList(linkedContainer);
        
        outputFile.append(arrTime + "," + linkedTime);
        System.out.println(arrTime + "," + linkedTime);

    }
    
    @Test
    public void timeSortOfSortedListTest()
    {
        //assertFalse("Time is not calculated correctly", ec.timeSortOfUnsortedList() < 0);
        
        long arrTime = ec.timeSortOfUnsortedList(arrContainer);
        long linkedTime = ec.timeSortOfUnsortedList(linkedContainer);
        
        outputFile.append(arrTime + "," + linkedTime);
        System.out.println(arrTime + "," + linkedTime);

    }
    
}



