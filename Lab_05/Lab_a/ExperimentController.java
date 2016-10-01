import java.util.AbstractList;

/**
 * @author Zura Mestiashvili 
 * @version 1.0.0
 */

public class ExperimentController{
    Wheel wheel;
    
    public long timeAddElement(QuickSortData iContainer, int numberOfItems, long seed){
        if(numberOfItems > 0){
            wheel = new Wheel(seed);
            long startTime = System.currentTimeMillis();
            for(; numberOfItems > 0; numberOfItems--){
                iContainer.addElement(wheel.spin());
            }
            long finishTime = System.currentTimeMillis();
            
            return finishTime - startTime;
        }
        
        return -1;
        
        
    }
    
    public long timeSortOfUnsortedList(QuickSortData iContainer){
        long startTime = System.currentTimeMillis();
        iContainer.printData();
        iContainer.sort();
        long finishTime = System.currentTimeMillis();
        
        return finishTime - startTime;
    }
    
    /*public long timeSortOfSortedList(QuickSortData iContainer){
        long startTime = System.currentTimeMillis();
        iContainer.insertionSort();
        long finishTime = System.currentTimeMillis();
        
        return finishTime - startTime;
    }*/
    
}
