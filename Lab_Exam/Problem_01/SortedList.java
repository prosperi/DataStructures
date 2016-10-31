
/**
 * @author (your name) 
 * @version (a version number or a date)
 */

public class  SortedList<K extends Number & Comparable, E>{
    SortedNode head;
    SortedNode current;
    
    public static void main(String[] args){
        SortedList<Integer, String> sl= new SortedList<Integer, String>();
        sl.add(41, "boo");
        System.out.println(sl);
        sl.add(45, "foo");
        System.out.println(sl);
        sl.add(40, "moo");
        System.out.println(sl);
     }
    
    public SortedList(){
        this.head = null;
    }
    
    public boolean add(K key, E value){
        SortedNode tmp = head;
        SortedNode prev = null;
        if(head == null){
            head = current = new SortedNode(key, value);
            return true;
        }
        
        while(tmp != null && key.compareTo(tmp.getKey()) == 1){
            prev = tmp;
            tmp = tmp.getNext();
        }
        
        
        if(tmp == null){
            prev.next = new SortedNode(key, value);
            return true;
        }
        
        K tmpK = (K)(tmp.getKey());
        if(tmpK.compareTo(key) == 0){ 
            return false;
        }else{
            SortedNode node = new SortedNode(key, value);
            node.next = tmp;
            if(prev == null){
                head = node;
            }else{
                prev.next = node;
            }
            return true;
        } 
        
    }
    
    public int count(E value){
        int counter = 0;
        SortedNode tmp = head;
        while(tmp.getNext() != null){
            if(tmp.getValue() == value) counter++;
            tmp = tmp.getNext();
        }
        return counter;
    }
    
    public String toString(){
        String str = "";
        SortedNode tmp = head;
        while(tmp != null){
            str += tmp.getValue() + " ";
            tmp = tmp.getNext();
        }
        
        return str;
    }
    
    class SortedNode<K extends Number & Comparable, E>{
        E value;
        K key;
        SortedNode next;
        
        public SortedNode(K key, E value){
            this.value = value;
            this.key = key;
            this.next = null;
        }
        
        void add(SortedNode<K,E> n){
            next = n;
        }
        
        SortedNode getNext(){
            return next;
        }
        
        E getValue(){
            return value;
        }
        
        K getKey(){
            return key;
        }
        
    }
}
