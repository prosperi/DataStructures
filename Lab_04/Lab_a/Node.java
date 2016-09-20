
/**
 * Zura Mestiashvili 
 * v1.0.0
 */
public class Node{
    private int value;
    private Node next;
    
    public Node(int value, Node next){
        this.value = value;
        this.next = next;
    }
    
    public Node next(){
        return this.next;
    }
    
    public int getValue(){
        return this.value;
    }
    
    public void setValue(int value){
        this.value = value;
    }
    
    public void setNext(Node node){
        this.next = node;
    }
}
