import java.util.ArrayList;

/**
 * Zura Mestiashvili
 * v1.0.0
 */
public class ArrayListMergeSort extends MergeSortData{
   
    public ArrayListMergeSort(){
        super(new ArrayList<String>());
    }
    
    public ArrayList<String> sortdata(ArrayList<String> al){
        if(al.size() == 1) return al;
        
        ArrayList<String> left = sorted(al.subList(0, al.size()/2));
        ArrayList<String> right = sorted(al.subList(al.size()/2, al.size()));
        
        return merge(left, right);
    }
    
 
    public ArrayList<String> merge(ArrayList<String> left, ArrayList<String> right){
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
