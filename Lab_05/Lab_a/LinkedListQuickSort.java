import java.util.LinkedList;
import java.util.AbstractList;
/**
 * Zura Mestiashvili
 * v1.0.0
 */

public class LinkedListQuickSort extends QuickSortData{
   
    public LinkedListQuickSort(){
        super(new LinkedList<String>());
    }
    
    public void sort(){
        quickSort(0, getSize()-1);
    }
    
    
    public void quickSort(int low, int high){
       if((high - low) <= 0){
           // do nothing, as one element array is already sorted
       }else if((high - low) == 1) {
          // check if we are sorting for two elements, if that's the case we do not the following proccesses
          // just swap those two elements if the are not sorted yet
          if(getElement(low).compareTo(getElement(high)) > 0){
              String tmp = getElement(low);
              setElement(low, getElement(high));
              setElement(high, tmp);
          }
       }else{
           
           int mid = (low  + high) / 2;
           if(getElement(mid).compareTo(getElement(low)) < 0)
                swap(low, mid);
           if (getElement(high).compareTo(getElement(low)) < 0)
                swap(low, high);
           if(getElement(high).compareTo(getElement(mid)) < 0)
                swap(mid, high);
           
           swap(mid, high - 1);
           String pivot = getElement(high - 1);
           
           // If we are sorting 3 element array , we have already sorted by swapping possible pivots
           // there is no need to continue this process
           if((high - low) != 2){
               int i, j;
               for( i = low, j = high - 1; ; ){
                   while(getElement(++i).compareTo(pivot) < 0);
                   while(getElement(--j).compareTo(pivot) > 0);
                   
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
         String tmp = getElement(i);
         setElement(i, getElement(j));
         setElement(j, tmp);
    }
   
    

    
}
