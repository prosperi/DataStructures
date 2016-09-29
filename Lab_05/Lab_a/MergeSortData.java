import java.util.AbstractList;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Zura Mestiashvili
 * v1.0.0
 */
 public abstract class MergeSortData implements SortedList{
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
    
    public abstract void sortData(AbstractList<String> al);
    public abstract AbstractList<String> merge(AbstractList<String> left, AbstractList<String> right);
    
    /*public AbstractList<String> sortData(AbstractList<String> al){
        if(al.size() == 1) return al;
        
        return merge(sortData((AbstractList)al.subList(0, al.size()/2)), sortData((AbstractList)al.subList(al.size()/2, al.size())));
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
        
        if(left.size() == 0){
            for(int i = 0; i < right.size(); i++){
                tmp.add(right.get(i));
            }
        }else if(right.size() == 0){
            for(int i = 0; i < left.size(); i++){
                tmp.add(left.get(i));
            }
        }
        
    } */
    
}
