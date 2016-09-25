
import java.lang.Iterable;
import java.util.Iterator;

/**
 * Zura Mestiashvili
 * v1.0.0
 */
public class MyLinkedList<E> implements Iterable<Node>{
    
    Node head, tail;
    
    public MyLinkedList(){
        this.head = null;
        this.tail = null;
    }
    
    public void addFirst(E value){
        head = new Node(value, head);  
        if(tail == null)
            tail = head;
    }
    
    public void addEnd(E value){
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
        return new MyLinkedListIterator(this);
    }
    
    
}
