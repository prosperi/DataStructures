
import java.lang.Iterable;
import java.util.Iterator;

/**
 * Zura Mestiashvili
 * v1.0.0
 */
public class MyLinkedList implements Iterable<Node>{
    
    Node head, tail;
    
    public MyLinkedList(){
        
    }
    
    public void addFirst(int value){
        head = new Node(value, head);  
        if(tail == null)
            tail = head;
    }
    
    public void addEnd(int value){
        if(tail == null || head == null){
            head = new Node(value, head);
            tail = head;
        }else{
            Node node = new Node(value, null);
            tail.setNext(node);
            tail = node;
        } 

    }
    
    public Iterator<Node> iterator(){
        return null;
    }
    
    
}
