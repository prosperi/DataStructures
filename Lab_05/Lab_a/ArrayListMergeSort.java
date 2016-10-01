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
        ArrayList<String> tmp = new ArrayList<String>();
        al = sortData(al/*,0, al.size()-1*/);
    }
    
    public ArrayList<String> sortData(AbstractList<String> al/*, AbstractList<String> tmp, int left, int right*/){
        // My version /////   ->>>> but book version is alwasys better
        if(al.size() <= 1) return (ArrayList)al;
        
        ArrayList<String> left = sortData(new ArrayList<String>(al.subList(0, al.size()/2)));
        ArrayList<String> right = sortData(new ArrayList<String>(al.subList(al.size()/2, al.size())));
        
        return merge(left, right);
        
        /*if(left < right){
            int middle = (left + right) / 2;
            sortData(al, tmp, left, middle);
            sortData(al, tmp, middle + 1, right);
            merge();
        }*/
        
        
    }
    
 
    public ArrayList<String> merge(AbstractList<String> left, AbstractList<String> right){
        ArrayList<String> tmp = new ArrayList<String>();
        
        while(left.size() > 0 && right.size() > 0){
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
        
        return tmp;
    }
    
    
}
