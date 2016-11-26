import java.util.Comparator;
import java.util.ArrayList;
/** 
  * @desc This Comparator class is used to sort Path when
  * specimen is hungry
  * @author Zura Mestiashvili mestiasz@lafayette.edu
  * @version v1.0.0
*/

public class HungryComparator implements Comparator<ArrayList<Path>>{
    
    @Override
    public int compare(ArrayList<Path> foo, ArrayList<Path> boo){
        Path fooRadar = foo.get(1);
        Path booRadar = boo.get(1);
        
        //sort with plants that are energy sources for the specimen
        if(fooRadar.getPlantOnTheWay() != booRadar.getPlantOnTheWay())
            return (fooRadar.getPlantOnTheWay()) ? -1 : 1;
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
