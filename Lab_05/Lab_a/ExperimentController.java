import java.util.AbstractList;

/**
 * @author Zura Mestiashvili 
 * @version 1.0.0
 */

public class ExperimentController{
    Wheel wheel;
    ArrayListQuickSort alqs;
    LinkedListQuickSort llqs;
    ArrayListMergeSort alms;
    LinkedListMergeSort llms;
    
    public ExperimentController(){
        alqs = new ArrayListQuickSort();
        llqs = new LinkedListQuickSort();
        alms = new ArrayListMergeSort();
        llms = new LinkedListMergeSort();
    }
    
    public long alqsSortTime(){
        long startTime = System.currentTimeMillis();
        alqs.sort();
        long finishTime = System.currentTimeMillis();
        
        return finishTime - startTime;
    }
    
    public long llqsSortTime(){
        long startTime = System.currentTimeMillis();
        llqs.sort();
        long finishTime = System.currentTimeMillis();
        
        return finishTime - startTime;
    }
    
    public long almsSortTime(){
        long startTime = System.currentTimeMillis();
        alms.sort();
        long finishTime = System.currentTimeMillis();
        
        return finishTime - startTime;
    }
    
    public long llmsSortTime(){
        long startTime = System.currentTimeMillis();
        alms.sort();
        long finishTime = System.currentTimeMillis();
        
        return finishTime - startTime;
    }
    
    public void add(int numberOfItems, long seed){
        if(numberOfItems > 0){
            wheel = new Wheel(seed);
            for(; numberOfItems > 0; numberOfItems--){
                String tmp = wheel.spin();
                alqs.addElement(tmp);
                llqs.addElement(tmp);
                alms.addElement(tmp);
                llms.addElement(tmp);
            }
            
        }        
        
    }
    
    
}
