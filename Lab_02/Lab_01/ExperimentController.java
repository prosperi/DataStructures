
/**
 * @author Zura Mestiashvili 
 * @version 1.0.0
 */

public class ExperimentController{
    RandomIntegerContainer ric;
    Wheel wheel;
    
    public long timeAddToFront(int numberOfItems, int seed){
        if(numberOfItems > 0 && seed > 1){
            ric = new RandomIntegerContainer();
            wheel = new Wheel(seed);
            long startTime = System.currentTimeMillis();
            for(; numberOfItems > 0; numberOfItems--){
                ric.addToFront(wheel.spin());
            }
            long finishTime = System.currentTimeMillis();
            
            return finishTime - startTime;
        }
        
        return -1;
        
        
    }
    
    public long timeSortOfUnsortedList(){
        long startTime = System.currentTimeMillis();
        ric.insertionSort();
        long finishTime = System.currentTimeMillis();
        
        return finishTime - startTime;
    }
    
    public long timeSortOfSortedList(){
        long startTime = System.currentTimeMillis();
        ric.insertionSort();
        long finishTime = System.currentTimeMillis();
        
        return finishTime - startTime;
    }
    
}
