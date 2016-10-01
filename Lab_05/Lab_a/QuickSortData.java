import java.util.AbstractList;
import java.util.Iterator;

/**
 * Zura Mestiashvili
 * v1.0.0
 */
public abstract class QuickSortData implements SortedList{
    public AbstractList<String> al;
   
    public QuickSortData(AbstractList<String> al){
        this.al = al;
    }
    
    public void addElement(String s){
        al.add(s);
    }
    
    public String printData(){
        String s = "";
        Iterator iterator = al.iterator();
        while(iterator.hasNext()){
            String tmp = iterator.next().toString();
            s += tmp + '/';
            System.out.println(tmp);
        }
        return s;
    }
    
    public abstract void quickSort(int low, int high);
    public abstract void swap(int i, int j);
    public abstract void sort();
    
    
}
