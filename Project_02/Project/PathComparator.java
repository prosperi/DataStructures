import java.util.Comparator;
import java.util.ArrayList;

public class PathComparator implements Comparator<ArrayList<Path>>{
    
    @Override
    public int compare(ArrayList<Path> foo, ArrayList<Path> boo){
        Path fooRadar = foo.get(1);
        Path booRadar = boo.get(1);
        
        // First of all sort with deadEnd
        if(fooRadar.getDeadEnd() != booRadar.getDeadEnd())
            return -Integer.compare(fooRadar.getDeadEnd(), booRadar.getDeadEnd());

            
        // Secondly sort with sizes - decreasing
        int result = Integer.compare(fooRadar.size(), booRadar.size());
        return (result != 0) ? -result : 1;
    }
   
}
