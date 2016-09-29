

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Zura Mestiashvili
 * v1.0.0
 */

public class LinkedListMergeSortTest{
    
    public LinkedListMergeSortTest()
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
    
    @Test
    public void test(){
        LinkedListMergeSort llms= new LinkedListMergeSort();
        llms.addElement("good");
        llms.addElement("food");
        llms.addElement("mood");
        llms.printData();
        llms.sort();
        llms.printData();
    }
    
}
