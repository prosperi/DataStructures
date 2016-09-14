package me.prosperri.datastructures.lab_03_01;

import java.util.AbstractList;

/**
 * @author Zura Mestiashvili 
 * @version 1.0.0
 */

public class ExperimentController{
    ArrayListIntegerContainer arrContainer;
    LinkedListIntegerContainer linkedContainer;
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
    
    public long timeSortOfUnsortedList(IntegerContainer iContainer){
        long startTime = System.currentTimeMillis();
        iContainer.insertionSort();
        long finishTime = System.currentTimeMillis();
        
        return finishTime - startTime;
    }
    
    public long timeSortOfSortedList(IntegerContainer iContainer){
        long startTime = System.currentTimeMillis();
        iContainer.insertionSort();
        long finishTime = System.currentTimeMillis();
        
        return finishTime - startTime;
    }
    
}
