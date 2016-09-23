import java.util.Iterator;

/**
 * Zura Mestiashvili
 * v1.0.0
 */
public class MyLinkedListIntegerContainer extends IntegerContainer{
    MyLinkedList data;
    
    public MyLinkedListIntegerContainer(){
       data = new MyLinkedList();
    }
    
    public void addToFront(int value){
        data.addFirst(value);
    }
    
    public void addFromEnd(int value){
        data.addEnd(value);
    }
    
    public String toString(){
        Iterator iterator = data.iterator();
        String str = "";
        Node current;
        
        while(iterator.hasNext()){
            current = (Node) iterator.next();
            str += current.getValue() + " ";
        }
        
        return str;
    }
}
