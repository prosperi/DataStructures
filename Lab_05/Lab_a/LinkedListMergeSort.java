import java.util.LinkedList;
import java.util.AbstractList;

/**
 * Zura Mestiashvili
 * v1.0.0
 */
public class LinkedListMergeSort extends MergeSortData{
   
    public LinkedListMergeSort(){
        super(new LinkedList<String>());
    }
    
    public void sort(){
        String[] tmp = new String[getSize()];
        sortData(tmp, 0, getSize()-1);
    }
    
    public void sortData(String[] tmp, int left, int right){
        
        if(left < right){
            int middle = (left + right) / 2;
            sortData(tmp, left, middle);
            sortData(tmp, middle + 1, right);
            merge(tmp, left, middle, right);
        }
        
        
    }
    
 
    public void merge(String[] tmp, int left, int middle, int right){
        
        for(int i = left; i <= right; i++){
            tmp[i] = getElement(i);
        }
        
        int i = left;
        int j = middle + 1;
        int k = left;
        
        while(i <= middle && j <= right){
            if(tmp[i].compareTo(tmp[j]) < 0){
                setElement(k, tmp[i]);
                i++;
            }else{
                setElement(k, tmp[j]);
                j++;
            }
            
            k++;
        }
        
        while(i <= middle){
            setElement(k, tmp[i]);
            i++;
            k++;
        }
        
    }
    
    
}
