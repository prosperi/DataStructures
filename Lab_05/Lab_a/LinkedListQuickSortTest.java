
import static org.junit.Assert.*;
import java.util.LinkedList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Ignore;


/**
 * Zura Mestiashvili
 * v1.0.0
 */
public class LinkedListQuickSortTest{
    LinkedListQuickSort qsData;
    public LinkedListQuickSortTest()
    {
    }

    @Before
    public void setUp()
    {
        qsData = new LinkedListQuickSort();
    }

   
    @After
    public void tearDown()
    {
    }
    
    @Test
    public void addElementTest(){      
        // initialize IntegerContainer's data variable
        //qsData.al = new LinkedList<String>();
        int size = qsData.getSize();
        
        // add 100 to container, and check its size and first element. As container is empty yet, 
        // its size will be 1 and the first element 100
        qsData.addElement("100");
        assertTrue("Could not add string", qsData.getElement(0) == "100" && qsData.getSize() == (size+1));
        
        // add 100 more elements container and check size, than we check each element, and make
        // sure each of them is on its place 
        for(int i = 99; i >= 0; i--){
            qsData.addElement("" + i);
        }
        assertTrue("Could not add string", qsData.getSize() == 101);
        
        for(int j = 100; j >= 0; j--){
            assertTrue("Could not add string", (j+"").compareTo(qsData.getElement(100-j)) == 0);
        }
    }
    
    @Test
    public void printDataTest(){
       String s = "";
       qsData.addElement("100");
       s += "100/";
       assertTrue("printData does not work correctly", qsData.printData().compareTo(s) == 0);
       
       for(int i = 99; i >= 0; i--){
           s += i + "/"; 
           qsData.addElement("" + i);
       }
       
       assertTrue("printData does not work correctly", qsData.printData().compareTo(s) == 0);
    }
    
    @Test
    public void sortTest(){
        
        /// Normal Cases
        
        for(int i = 122; i >= 97; i--){
            qsData.addElement((char)i + "Foo");
        }
        
        qsData.sort();
        for(int i = 97; i < 122; i++){
            assertTrue("sort does not work properly", ((char)i + "Foo").compareTo(qsData.getElement(i-97)) == 0);
        }
        
        qsData.clearAL();
        for(int i = 9; i >= 0; i--){
            qsData.addElement(i+"");
        }
        qsData.sort();
        for(int i = 0; i < 10; i++){
            assertTrue("sort does not work properly", (i+"").compareTo(qsData.getElement(i)) == 0);
        }
        
        // test for sorted List
        qsData.clearAL();
        for(int i = 0; i < 10; i++){
            qsData.addElement(i+"");
        }
        qsData.sort();
        
        for(int i = 0; i < 10; i++){
            assertTrue("sort does not work properly", (i+"").compareTo(qsData.getElement(i)) == 0);
        }
        
        //test for one element List
        qsData.clearAL();
        qsData.addElement("1");
        qsData.sort();
        assertTrue("sort does not work properly", "1".compareTo(qsData.getElement(0)) == 0 && qsData.getSize() == 1);
        
        //test for empty list
        qsData.clearAL();
        qsData.sort();
        assertTrue("sort does not work properly", qsData.getSize() == 0);
        
        // test for list where each element has same value
        qsData.clearAL();
        for(int i = 0; i < 10; i++){
            qsData.addElement("1");
        }
        qsData.sort();
        for(int i = 0; i < 10; i++){
            assertTrue("sort does not work properly", ("1").compareTo(qsData.getElement(i)) == 0);
        }
        
        // test for list with repeating values
        qsData.clearAL();
        for(int i = 0; i < 10; i++){
            if(i%2==0) qsData.addElement("a");
            else qsData.addElement("b");
        }
        qsData.sort();
        for(int i = 0; i < 10; i++){
            if(i < 5)
                assertTrue("sort does not work properly", ("a").compareTo(qsData.getElement(i)) == 0);
            else
                assertTrue("sort does not work properly", ("b").compareTo(qsData.getElement(i)) == 0);
        }
        
    }
    
    
    @Test
    public void swapTest(){
        qsData.addElement("1");
        qsData.addElement("0");
        qsData.swap(0, 1);
        assertTrue("Did not swap values correctly", qsData.getElement(0).compareTo("0")==0 && qsData.getElement(1).compareTo("1")==0);
        
        for(int i = 2; i < 100; i++){
           qsData.addElement("" + i);
       }
       qsData.swap(50, 51);
       assertTrue("Did not swap values correctly", qsData.getElement(50).compareTo("51")==0  && qsData.getElement(51).compareTo("50")==0);
       
    }
    
    
}
