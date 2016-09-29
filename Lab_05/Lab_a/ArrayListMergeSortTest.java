

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Zura Mestiashvili
 * v1.0.0
 */

public class ArrayListMergeSortTest{
    
    public ArrayListMergeSortTest()
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
        ArrayListMergeSort alms= new ArrayListMergeSort();
        alms.addElement("good");
        alms.addElement("food");
        alms.addElement("mood");
        alms.printData();
        alms.sort();
        alms.printData();
    }
    
}
