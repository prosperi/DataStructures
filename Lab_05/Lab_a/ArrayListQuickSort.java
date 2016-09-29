import java.util.ArrayList;
import java.util.AbstractList;
/**
 * Zura Mestiashvili
 * v1.0.0
 */

public class ArrayListQuickSort extends QuickSortData{
   
    public ArrayListQuickSort(){
        super(new ArrayList<String>());
    }
    
    public void sort(){
        quickSort(al, 0, al.size()-1);
    }
    
    
    public ArrayList<String> quickSort(AbstractList<String> al, int low, int high){
       System.out.println(low + " " + high);
       String[] pivots = new String[]{al.get(low), al.get((high + low)/2), al.get(high)};
       String pivot = "";
       boolean t = true;
        
       while(t){
           t = false;
           for(int i = 0; i < pivots.length-1; i++){
               if(pivots[i].compareTo(pivots[i+1]) > 0){
                   String tmp = pivots[i];
                   pivots[i] = pivots[i+1];
                   pivots[i+1] = tmp;
                   t = true;
               }
           }
       }
       
       pivot = pivots[1];
        
       al.set(low, pivots[0]);
       al.set(high, pivots[2]);
       al.set((low + high)/2, al.get(high-1));
       al.set(high - 1, pivot);
       
       if(al.size() <= 3) return null;
        
       int i, j;
       
       for( i = 0, j = high - 1; ; ){
           while(al.get(++i).compareTo(pivot) < 0);
           while(al.get(--j).compareTo(pivot) > 0);
           
           if(i >= j) break;
           
           String tmp = al.get(i);
           al.set(i, al.get(j));
           al.set(j, tmp);
       }
      
       al.set(high-1, al.get(i));
       al.set(i, pivot);
       
       quickSort(al, low, i - 1);
       quickSort(al, i + 1, high); 
       
       return (ArrayList)al;
    }
   

    
}
