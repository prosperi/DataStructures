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
    
    public String printData(){
        Iterator iterator = al.iterator();
        String s = "";
        while(iterator.hasNext()){
            String tmp = iterator.next().toString();
            s += tmp + "/";
            System.out.println(tmp);
        }
        
        return s;
    }
    

    public abstract void sort();
    public abstract AbstractList<String> sortData(AbstractList<String> al);
    public abstract AbstractList<String> merge(AbstractList<String> left, AbstractList<String> right);
    
    
}
