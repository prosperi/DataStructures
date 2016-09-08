import java.util.ArrayList;
/**
 * @author Zura Mestiashvili 
 * @version 1.0.0
 */
public class RandomIntegerContainer{
    public ArrayList<Integer> aList;
    
    public RandomIntegerContainer(){
        this.aList = new ArrayList();
    }
    
    public void addToFront(int x){
        aList.add(0, x);
    }
    
    public void insertionSort(){
        for(int i = 0; i < aList.size()-1; i++){
            
                int tmp = aList.get(i+1);
                int j = i+1;
                for(; j > 0 && aList.get(j-1) > tmp; j--){
                    aList.set(j, aList.get(j-1));
                }
                aList.set(j, tmp);     
            
        }
    }
    
    
}
