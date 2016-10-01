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
        quickSort(0, al.size()-1);
    }
    
    
    public void quickSort(int low, int high){
       if((high - low) <= 0){
           // do nothing, as one element array is already sorted
       }else if((high - low) == 1) {
          // check if we are sorting for two elements, if that's the case we do not the following proccesses
          // just swap those two elements if the are not sorted yet
          if(al.get(low).compareTo(al.get(high)) > 0){
              String tmp = al.get(low);
              al.set(low, al.get(high));
              al.set(high, tmp);
          }
       }else{
           
           int mid = (low  + high) / 2;
           if(al.get(mid).compareTo(al.get(low)) < 0)
                swap(low, mid);
           if (al.get(high).compareTo(al.get(low)) < 0)
                swap(low, high);
           if(al.get(high).compareTo(al.get(mid)) < 0)
                swap(mid, high);
           
           swap(mid, high - 1);
           String pivot = al.get(high - 1);
           
           // If we are sorting 3 element array , we have already sorted by swapping possible pivots
           // there is no need to continue this process
           if((high - low) != 2){
               int i, j;
               for( i = low, j = high - 1; ; ){
                   while(al.get(++i).compareTo(pivot) < 0);
                   while(al.get(--j).compareTo(pivot) > 0);
                   
                   if(i >= j) break;

                   swap(i, j);
                   
               }
              
               swap(i, high-1);
    
               quickSort(low, i - 1);
               quickSort(i + 1, high);
           }
           
       }
       
    }
    
    public void swap(int i, int j){
         String tmp = al.get(i);
         al.set(i, al.get(j));
         al.set(j, tmp);
    }
   
    

    
}
