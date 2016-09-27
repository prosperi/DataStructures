import java.util.AbstractList;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Zura Mestiashvili
 * v1.0.0
 */
public class MergeSortData implements SortedList{
    private AbstractList<String> al;
    
    public MergeSortData(AbstractList<String> al){
        this.al = al;
    }
    
    public void addElement(String s){
        al.add(s);
    }
    
    public void printData(){
        Iterator iterator = al.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
    
    public void sortData(){
        
    }
    
    private void merge(AbstractList<String> left, AbstractList<String> right){
        AbstractList<String> tmp = (left instanceof ArrayList) ? new ArrayList<String>() : new LinkedList<String>();
        
        while(left.size() > 0 && right.size() > 0){
            if(left.get(0).compareTo(right.get(0)) < 0){
                tmp.add(left.get(0));
                left.remove(0);
            }else{
                tmp.add(right.get(0));
                right.remove(0);
            }
        }
    }
    
}
