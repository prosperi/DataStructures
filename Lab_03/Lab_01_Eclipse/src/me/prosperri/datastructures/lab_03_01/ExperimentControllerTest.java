package me.prosperri.datastructures.lab_03_01;

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
    private static int constant = 12;
   

   
    @BeforeClass
    public static void classSetUp()
    {
        ec = new ExperimentController();
        arrContainer = new ArrayListIntegerContainer();
        linkedContainer = new LinkedListIntegerContainer();
        
        try{
            outputFile = new PrintWriter("data.csv", "UTF-8");
        }catch(IOException e){
            System.out.println("Problem with output file!!!");
        }
        
    }
    
    @AfterClass
    public static void classCleanUp()
    {
        outputFile.close();
    }
    
    @Before
    public void setUp(){
    	arrContainer.data.clear();
    	linkedContainer.data.clear();
    }
    
    @After
    public void cleanUp(){
    	outputFile.append('\n');
    	System.out.println("Finished another");
    }
    
    @Test
    public void timeAddToFrontTest()
    {        
    	long arrTime = 0;
    	long linkedTime = 0;
    	for(int i = 100, j = 0; j < constant; i *= 2, j++){
    		arrContainer.data.clear();
    		linkedContainer.data.clear();
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
    	for(int i = 100, j = 0; j < constant; i *= 2, j++){
    		arrContainer.data.clear();
    		linkedContainer.data.clear();
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
    	for(int i = 100, j = 0; j < constant; i *= 2, j++){
    		arrContainer.data.clear();
    		linkedContainer.data.clear();
    		ec.timeAddToFront(arrContainer, i, j);
            ec.timeAddToFront(linkedContainer, i, j);
            
            arrTime = ec.timeSortOfSortedList(arrContainer);
            linkedTime = ec.timeSortOfSortedList(linkedContainer);
            
            outputFile.append(arrTime + "," + linkedTime + '\n');
    	}

        
    }
    
}



