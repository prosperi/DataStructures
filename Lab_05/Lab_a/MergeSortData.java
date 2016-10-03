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
    
    public void clearAL(){
        al.clear();
    }
    
    public int getSize(){
        return al.size();
    }
    
    public String getElement(int i){
        return al.get(i);
    }
    
    public void setElement(int i, String s){
        al.set(i, s);
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
    public abstract void sortData(String[] tmp, int i, int j);
    public abstract void merge(String[] tmp, int left, int middle, int right);
    
    
}
