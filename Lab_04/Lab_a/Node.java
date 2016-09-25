
/**
 * Zura Mestiashvili 
 * v1.0.0
 */
public class Node<E>{
    private E value;
    private Node next;
    
    public Node(E value, Node next){
        this.value = value;
        this.next = next;
    }
    
    public Node getNext(){
        return this.next;
    }
    
    public E getValue(){
        return this.value;
    }
    
    public void setValue(E value){
        this.value = value;
    }
    
    public void setNext(Node node){
        this.next = node;
    }
}
