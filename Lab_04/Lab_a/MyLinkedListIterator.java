import java.util.Iterator;

/**
 * Zura Mestiashvili
 * v1.0.0
 */

public class MyLinkedListIterator<E> implements Iterator<Node>{
   
    private final MyLinkedList<E> mll;
    private Node current;
    private boolean calledNext;
    private Node previous;
    
    public MyLinkedListIterator(MyLinkedList<E> mll){
        this.mll = mll;
        this.current = null;
        this.calledNext = false;
    }
    
    @Override
    public boolean hasNext(){
        if(current != mll.tail && mll.tail != null){
            return true;
        }else{
            return false;
        }
    }
    
    @Override
    public Node next(){
        previous = current;
        calledNext = true;
        if(current == null){
            current = mll.head;
        }else if(current.getNext() == null){
            return null;
        }else{
            current = current.getNext();
        }

        return current;
    }
    
    @Override
    public void remove(){
        if(calledNext == true){
            Node n;
            if(current == mll.head){
                if(current == mll.tail){
                    mll.head = mll.tail = null;
                    current = null;
                }else{
                    mll.head = current.getNext();
                    current = null;
                }
            }else if(current == mll.tail){
                previous.setNext(null);
                mll.tail = previous;
                current = mll.tail;
            }else{
                previous.setNext(current.getNext());
            }
            
            calledNext = false;
        }else{
            throw new IllegalStateException();
        }
    }
    
}
