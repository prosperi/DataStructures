import java.util.AbstractList;
/**
 * Zura Mestiashvili
 * v1.0.0
 */
public class IntegerContainer{

    AbstractList<Integer> data;
    
    public IntegerContainer(){
        this.data = null;
    }
    
    public void addToFront(int x){
        data.add(0, x);
    }
    
     public void insertionSort(){
        for(int i = 1; i < data.size(); i++){
            
                int tmp = data.get(i);
                int j = i;
                for(; j > 0 && data.get(j-1) > tmp; j--){
                    data.set(j, data.get(j-1));
                }
                data.set(j, tmp);     
            
        }
    }
    
    
    
}
