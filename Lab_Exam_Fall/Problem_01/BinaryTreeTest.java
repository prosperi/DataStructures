

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;

public class BinaryTreeTest
{
    BinaryTree<Integer> bt1;
    BinaryTree<String> bt2;
    ArrayList<Node> arr1;
    ArrayList<Node> arr2;
    
    public BinaryTreeTest()
    {
    }

   
    @Before
    public void setUp()
    {
        bt1 = new BinaryTree<Integer>();
        bt2 = new BinaryTree<String>();
        
        // Containers of bt1 and bt2
        arr1 = bt1.getContainer();
        arr2 = bt2.getContainer();
    }

    
    @After
    public void tearDown()
    {
    }
    
    @Test
    public void testAdd(){
        assertTrue("Problem with current variable initialization", bt1.getCurrent() == 0);
        assertTrue("Problem with current variable initialization", bt2.getCurrent() == 0);
        
        bt1.add(2);
        bt1.add(1);
        bt1.add(4);
        assertTrue("Did not add new vlaue correctly", bt1.getCurrent() == 3);
        assertTrue("Did not add new vlaue correctly", (int)arr1.get(0).getValue() == 2);
        assertTrue("Did not add new vlaue correctly", (int)arr1.get(1).getValue() == 1);
        assertTrue("Did not add new vlaue correctly", (int)arr1.get(2).getValue() == 4);
        assertTrue("Did not add new vlaue correctly", (int)arr1.get(0).getLeft().getValue() == 1);
        assertTrue("Did not add new vlaue correctly", (int)arr1.get(0).getRight().getValue() == 4);
        
        bt1.add(3);
        bt1.add(5);
        assertTrue("Did not add new vlaue correctly", (int)arr1.get(2).getLeft().getValue() == 3);
        assertTrue("Did not add new vlaue correctly", (int)arr1.get(2).getRight().getValue() == 5);
        
        bt1.add(null);
        assertTrue("Did not add new vlaue correctly", arr1.get(5) == null);
        
        for(int i = 6; i < 31; i++){
            bt1.add(i);
        }
        assertTrue("Did not add new vlaue correctly " + bt1.getCurrent(), bt1.getCurrent() == 30);
        bt1.add(100);
        assertTrue("Did not add new vlaue correctly", bt1.getCurrent() == 30 && arr1.size() == 30);
        
        System.out.println(bt1);
        
        bt2.add("2");
        bt2.add("1");
        bt2.add("4");
        assertTrue("Did not add new vlaue correctly", bt2.getCurrent() == 3);
        assertTrue("Did not add new vlaue correctly", arr2.get(0).getValue().equals("2"));
        assertTrue("Did not add new vlaue correctly", arr2.get(1).getValue().equals("1"));
        assertTrue("Did not add new vlaue correctly", arr2.get(2).getValue().equals("4"));
        assertTrue("Did not add new vlaue correctly", arr2.get(0).getLeft().getValue().equals("1"));
        assertTrue("Did not add new vlaue correctly", arr2.get(0).getRight().getValue().equals("4"));
        
        bt2.add("3");
        bt2.add("5");
        assertTrue("Did not add new vlaue correctly", arr2.get(2).getLeft().getValue().equals("3"));
        assertTrue("Did not add new vlaue correctly", arr2.get(2).getRight().getValue().equals("5"));
        
        bt2.add(null);
        assertTrue("Did not add new vlaue correctly", arr2.get(5) == null);
        
        for(int i = 6; i < 31; i++){
            bt2.add(i + "");
        }
        assertTrue("Did not add new vlaue correctly", bt2.getCurrent() == 30);
        bt2.add("100");
        assertTrue("Did not add new vlaue correctly", bt2.getCurrent() == 30 && arr2.size() == 30);
        
        System.out.println(bt2);
    }
    
    @Test
    public void testToString(){
        bt1.add(4);
        bt1.add(2);
        bt1.add(6);
        bt1.add(1);
        bt1.add(null);
        bt1.add(5);
        bt1.add(7);
        
        String traversal = "1 2 4 5 6 7 ";
        assertTrue("Could not traverse correctly " + bt1.toString(), bt1.toString().equals(traversal));
        
        arr1.clear();
        bt1.add(null);
        assertTrue("Could not traverse correctly " + bt1.toString(), bt1.toString().equals(""));
    }
}
