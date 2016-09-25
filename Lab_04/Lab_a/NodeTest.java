

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Zura Mestiashvili
 * v1.0.0
 */
public class NodeTest
{
    Node node, newNode;
    
    public NodeTest(){
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
    public void getNextTest(){
        // Test if Next object reference getter works correctly
        node = new Node(2,null);
        System.out.println(node.getNext() == null);
        assertTrue("Node could not get next object reference correctly", node.getNext() == null);
        
        newNode = new Node(1, node);
        assertTrue("Node could not get next object reference correctly", newNode.getNext() == node);
    }
    
    @Test
    public void getValueTest(){
        // Test if value getter works correctly
        node = new Node(2,null);
        assertTrue("getValue function fails to return correct value", (int)node.getValue() == 2);
        
        newNode = new Node(1, node);
        assertTrue("getValue function fails to return correct value", (int)newNode.getValue() == 1);
    }
    
    @Test
    public void setNextTest(){
        // Test if Next object reference setter works correctly
        node = new Node(2,null);        
        newNode = new Node(1, null);
        
        node.setNext(newNode);
        newNode.setNext(node);
        
        assertTrue("setNext method can not set object reference correctly", node.getNext() == newNode);
        assertTrue("setNext method can not set object reference correctly", newNode.getNext() == node);

    }
    
    @Test
    public void setValueTest(){
        // Test if value setter works correctly
        node = new Node(2,null);
        newNode = new Node(1, node);
        
        node.setValue(0);
        newNode.setValue(10);
        assertTrue("Node could not set value", (int)node.getValue() == 0);
        assertTrue("Node could not set value", (int)newNode.getValue() == 10);
    }
    
    
}
