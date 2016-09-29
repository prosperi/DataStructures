import java.util.AbstractList;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Zura Mestiashvili
 * v1.0.0
 */
 public abstract class MergeSortData implements SortedList{
    public AbstractList<String> al;
    //private AbstractList<String> alSorted;
    
    public MergeSortData(AbstractList<String> al){
        this.al = al;
        //this.alSorted = null;
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
    

    public abstract void sort();
    public abstract AbstractList<String> sortData(AbstractList<String> al);
    public abstract AbstractList<String> merge(AbstractList<String> left, AbstractList<String> right);
    
    
}
