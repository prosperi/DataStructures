import java.util.ArrayList;
import java.util.AbstractList;

/**
 * Zura Mestiashvili
 * v1.0.0
 */
public class ArrayListMergeSort extends MergeSortData{
   
    public ArrayListMergeSort(){
        super(new ArrayList<String>());
    }
    
    public void sort(){
        int size = getSize();
        String[] tmp = new String[size];
        sortData(tmp, 0, size-1);
    }
    
    public void sortData(/*AbstractList<String> al,*/ String[] tmp, int left, int right){
        // My version /////   ->>>> but book version is better
        /*if(al.size() <= 1) return (ArrayList)al;
        
        ArrayList<String> left = sortData(new ArrayList<String>(al.subList(0, al.size()/2)));
        ArrayList<String> right = sortData(new ArrayList<String>(al.subList(al.size()/2, al.size())));
        
        return merge(left, right);*/
        
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
        
        /*This is continuation of my version
         * But as my version needs creqating more objects than the book version
         * Book version is better
         * while(left.size() > 0 && right.size() > 0){
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
        
        return tmp;*/
    }
    
    
}
