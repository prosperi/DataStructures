import java.util.AbstractList;
import java.util.Iterator;

/**
 * Zura Mestiashvili
 * v1.0.0
 */
abstract public class QuickSortData implements SortedList{
    private AbstractList<String> al;
   
    public QuickSortData(AbstractList<String> al){
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
    
    
    
}
