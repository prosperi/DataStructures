

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Ignore;

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

        alqs.addElement("zood");
        alqs.addElement("ood");
        alqs.addElement("food");
        alqs.printData();
        
        for(int i = 122; i >= 65; i--){
            alqs.addElement((char)i + "Foo");
        }
        
        alqs.sort();
        alqs.printData();
    }
}
