import java.util.Comparator;

public class TestComparator implements Comparator<Integer>
{
   @Override
   public int compare(Integer i, Integer j){
       
       return i > j ? -1 : 1;
   }
}
