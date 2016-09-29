import java.util.LinkedList;
import java.util.AbstractList;

/**
 * Zura Mestiashvili
 * v1.0.0
 */

public class LinkedListMergeSort extends MergeSortData{
    
    public LinkedListMergeSort(){
        super(new LinkedList<String>());
    }
    
    public void sort(){
        al = sortData(al);
    }
    
    public LinkedList<String> sortData(AbstractList<String> al){
        if(al.size() == 1) return (LinkedList)al;
        
        LinkedList<String> left = sortData(new LinkedList<String>(al.subList(0, al.size()/2)));
        LinkedList<String> right = sortData(new LinkedList<String>(al.subList(al.size()/2, al.size())));
        
        return merge(left, right);
    }
    
 
    public LinkedList<String> merge(AbstractList<String> left, AbstractList<String> right){
        LinkedList<String> tmp = new LinkedList<String>();
        
        while(left.size() > 0 && right.size() > 0){
            if(left.get(0).compareTo(right.get(0)) < 0){
                tmp.add(left.get(0));
                left.remove(0);
            }else{
                tmp.add(right.get(0));
                right.remove(0);
            }
        }
        
        if(left.size() == 0){
            for(int i = 0; i < right.size(); i++){
                tmp.add(right.get(i));
            }
        }else if(right.size() == 0){
            for(int i = 0; i < left.size(); i++){
                tmp.add(left.get(i));
            }
        }
        
        return tmp;
    }
}
