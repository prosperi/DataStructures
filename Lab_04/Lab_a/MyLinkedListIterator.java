import java.util.Iterator;

/**
 * Zura Mestiashvili
 * v1.0.0
 */

public class MyLinkedListIterator implements Iterator<Node>{
   
    private final MyLinkedList mll;
    private Node current;
    
    public MyLinkedListIterator(MyLinkedList mll){
        this.mll = mll;
        this.current = mll.head;
    }
    
    @Override
    public boolean hasNext(){
        if(current != mll.tail){
            return true;
        }else{
            return false;
        }
    }
    
    @Override
    public Node next(){
        current = current.next();
        return current;
    }
    
    @Override
    public void remove(){
        
    }
    
}
