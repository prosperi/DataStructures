

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Zura Mestiashvili
 * v1.0.0
 */
public class ArrayListQuickSortTest{
   
    public ArrayListQuickSortTest()
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
        ArrayListQuickSort alqs= new ArrayListQuickSort();
        alqs.addElement("good");
        alqs.printData();
    }
}
