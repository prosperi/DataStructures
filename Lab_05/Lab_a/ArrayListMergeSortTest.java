
import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Ignore;


/**
 * Zura Mestiashvili
 * v1.0.0
 */
public class ArrayListMergeSortTest{
    ArrayListMergeSort msData;
    public ArrayListMergeSortTest()
    {
    }

    @Before
    public void setUp()
    {
        msData = new ArrayListMergeSort();
    }

   
    @After
    public void tearDown()
    {
    }
    
    @Test
    public void addElementTest(){      
        // initialize IntegerContainer's data variable
        msData.al = new ArrayList<String>();
        int size = msData.al.size();
        
        // add 100 to container, and check its size and first element. As container is empty yet, 
        // its size will be 1 and the first element 100
        msData.addElement("100");
        assertTrue("Could not add string", msData.al.get(0) == "100" && msData.al.size() == (size+1));
        
        // add 100 more elements container and check size, than we check each element, and make
        // sure each of them is on its place 
        for(int i = 99; i >= 0; i--){
            msData.addElement("" + i);
        }
        assertTrue("Could not add string", msData.al.size() == 101);
        
        for(int j = 100; j >= 0; j--){
            assertTrue("Could not add string", (j+"").compareTo(msData.al.get(100-j)) == 0);
        }
    }
    
    @Test
    public void printDataTest(){
       String s = "";
       msData.addElement("100");
       s += "100/";
       assertTrue("printData does not work correctly", msData.printData().compareTo(s) == 0);
       
       for(int i = 99; i >= 0; i--){
           s += i + "/"; 
           msData.addElement("" + i);
       }
       
       assertTrue("printData does not work correctly", msData.printData().compareTo(s) == 0);
    }
    
    @Test
    public void sortTest(){
        
        /// Normal Cases
        
        for(int i = 122; i >= 97; i--){
            msData.addElement((char)i + "Foo");
        }
        
        msData.sort();
        for(int i = 97; i < 122; i++){
            assertTrue("sort does not work properly", ((char)i + "Foo").compareTo(msData.al.get(i-97)) == 0);
        }
        
        msData.al.clear();
        for(int i = 9; i >= 0; i--){
            msData.addElement(i+"");
        }
        msData.sort();
        for(int i = 0; i < 10; i++){
            assertTrue("sort does not work properly", (i+"").compareTo(msData.al.get(i)) == 0);
        }
        
        // test for sorted List
        msData.al.clear();
        for(int i = 0; i < 10; i++){
            msData.addElement(i+"");
        }
        msData.sort();
        
        for(int i = 0; i < 10; i++){
            assertTrue("sort does not work properly", (i+"").compareTo(msData.al.get(i)) == 0);
        }
        
        //test for one element List
        msData.al.clear();
        msData.addElement("1");
        msData.sort();
        assertTrue("sort does not work properly", "1".compareTo(msData.al.get(0)) == 0 && msData.al.size() == 1);
        
        //test for empty list
        msData.al.clear();
        msData.sort();
        assertTrue("sort does not work properly", msData.al.size() == 0);
        
        // test for list where each element has same value
        msData.al.clear();
        for(int i = 0; i < 10; i++){
            msData.addElement("1");
        }
        msData.sort();
        for(int i = 0; i < 10; i++){
            assertTrue("sort does not work properly", ("1").compareTo(msData.al.get(i)) == 0);
        }
        
        // test for list with repeating values
        msData.al.clear();
        for(int i = 0; i < 10; i++){
            if(i%2==0) msData.addElement("a");
            else msData.addElement("b");
        }
        msData.sort();
        for(int i = 0; i < 10; i++){
            if(i < 5)
                assertTrue("sort does not work properly", ("a").compareTo(msData.al.get(i)) == 0);
            else
                assertTrue("sort does not work properly", ("b").compareTo(msData.al.get(i)) == 0);
        }
        
    }
    
    
    
    
}
