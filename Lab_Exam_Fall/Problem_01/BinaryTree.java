import java.util.ArrayList;


public class BinaryTree<E extends Comparable>{
    public ArrayList<Node> container; 
    private int current = 0; // shows the current number of elements in container
    private String s;
    
    public BinaryTree(){
        container = new ArrayList<Node>();
        for(int i = 0; i < 30; i++){
            container.add(null);
        }
    }
   
    public void add(E e){
        // check if container's size is less than 30, as we do not 
        // want our container to have more than 30 elements and also check if the passed
        // value is null, in which case we do not perform any manipulation on our container
        if(current < 30 && e != null){
            // if container does not have Node yet, than set this new node as head
            if(container.get(0) == null){
                container.set(0, new Node(e));
            }else{
                // We should find right place for the node in our binary search tree
                Node tmp = container.get(0);
                while(tmp != null){
                    try{
                        // check if new value is less than the current one, and go to the left branch
                        if(e.compareTo(tmp.getValue()) < 0 ){
                            if(tmp.getLeft() == null){
                                Node child = new Node(e);
                                tmp.setLeft(child);
                                container.set(current, child);
                                break;
                            }else{
                                tmp = tmp.getLeft();
                            }
                        }
                        // check if new value is more than the current one, and go to the right branch
                        else if(e.compareTo(tmp.getValue()) > 0 ){
                           if(tmp.getRight() == null){
                                Node child = new Node(e);
                                tmp.setRight(child);
                                container.set(current, child);
                                break;
                            }else{
                                tmp = tmp.getRight();
                            }
                        }
                        // If we already have this element than throw the error
                        else{
                            throw new Exception("Duplicate element");
                        }
                    }catch(Exception exception){
                        tmp = null;
                        exception.printStackTrace();
                    }
                }
                
            }
            current++;
        }else{
            System.out.println("There is no space left for nodes in this tree");
        }
        
        
    }
    
    
    
    public String toString(){
        s = "";
        if(container.size() >= 1){
            inorderTraversal(container.get(0));
        }else{
            System.out.println("We have an empty binary search tree");
        }
        return s;
    }
    
    public void inorderTraversal(Node currentNode){
        // check at first if the left branch exists
        if(currentNode.getLeft() != null){
            inorderTraversal(currentNode.getLeft());
        }
        // add curent node's value
        s += currentNode.getValue() + " ";
        // check if right branch exists
        if(currentNode.getRight() != null){
            inorderTraversal(currentNode.getRight());
        }
    }
    
    
    // used for test purposes
    public int getCurrent(){
        return current;
    }
    
    public ArrayList<Node> getContainer(){
        return container;
    }
}
