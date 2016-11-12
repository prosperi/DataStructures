import java.util.Comparator;
import java.util.ArrayList;

public class HungryComparator implements Comparator<ArrayList<Path>>{
    
    @Override
    public int compare(ArrayList<Path> foo, ArrayList<Path> boo){
        Path fooRadar = foo.get(1);
        Path booRadar = boo.get(1);
        
        //sort with plants that are energy sources for the specimen
        if(fooRadar.getPlantOnTheWay() == false && fooRadar.getPlantOnTheWay() == true)
            return 1;
        // sort with deadEnd = 1 || 2 and 1 -> 2 -> 3 ->4 -> 0
        if(fooRadar.getDeadEnd() != booRadar.getDeadEnd()){
            if(fooRadar.getDeadEnd() == 0) return 1;
            if(booRadar.getDeadEnd() == 0) return -1;
            return Integer.compare(fooRadar.getDeadEnd(), booRadar.getDeadEnd());
        }
            
        // Secondly sort with sizes - decreasing
        int result = Integer.compare(fooRadar.size(), booRadar.size());
        return (result != 0) ? -result : 1;
    }
   
}
