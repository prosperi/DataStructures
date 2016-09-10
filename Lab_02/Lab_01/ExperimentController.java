
/**
 * @author Zura Mestiashvili 
 * @version 1.0.0
 */

public class ExperimentController{
    RandomIntegerContainer ric;
    Wheel wheel;
    
    public static void main(String args[]){
        
        ExperimentController controller = new ExperimentController();
        
        System.out.println(controller.timeAddToFront(1000000, 10000));
        System.out.println(controller.timeSortOfUnsortedList());
        System.out.println(controller.timeSortOfSortedList());
        
    }
    
    public long timeAddToFront(int numberOfItems, int seed){
        ric = new RandomIntegerContainer();
        wheel = new Wheel(seed);
        long startTime = System.currentTimeMillis();
        for(; numberOfItems > 0; numberOfItems--){
            ric.addToFront(wheel.spin());
        }
        long finishTime = System.currentTimeMillis();
        
        return finishTime - startTime;
        
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
