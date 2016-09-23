
/**
 * @author Zura Mestiashvili 
 * @version 1.0.0
 */

public class ExperimentController{
    MyLinkedListIntegerContainer mlic;
    //LinkedListIntegerContainer linkedContainer;
    Wheel wheel;
    
    public long timeAddToFront(IntegerContainer iContainer, int numberOfItems, long seed){
        if(numberOfItems > 0){
            wheel = new Wheel(seed);
            long startTime = System.currentTimeMillis();
            for(; numberOfItems > 0; numberOfItems--){
                iContainer.addToFront(wheel.spin());
            }
            long finishTime = System.currentTimeMillis();
            
            return finishTime - startTime;
        }
        
        return -1;
        
    }
    
    public long timeAddFromEnd(MyLinkedListIntegerContainer iContainer, int numberOfItems, long seed){
        if(numberOfItems > 0){
            wheel = new Wheel(seed);
            long startTime = System.currentTimeMillis();
            for(; numberOfItems > 0; numberOfItems--){
                iContainer.addFromEnd(wheel.spin());
            }
            long finishTime = System.currentTimeMillis();
            
            return finishTime - startTime;
        }
        
        return -1;
        
    }
    
}
