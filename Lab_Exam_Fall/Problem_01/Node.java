

public class Node<E extends Comparable>{
    E value;
    Node left;
    Node right;
    
    public Node(E value){
        this.value = value;
        this.left = null;
        this.right = null;
    }
    
    public E getValue(){
        return value;
    }
   
    public Node getLeft(){
        return left;
    }
    
    public void setLeft(Node node){
        left = node;
    }
    
    public Node getRight(){
        return right;
    }
    
    public void setRight(Node node){
        right = node;
    }
}
