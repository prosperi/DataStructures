import java.util.Iterator;

/**
 * Zura Mestiashvili
 * v1.0.0
 */
public class MyListIntegerContainer extends IntegerContainer{
    MyLinkedList data;
    
    public MyListIntegerContainer(){
       this.data = new MyLinkedList<Integer>();
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
