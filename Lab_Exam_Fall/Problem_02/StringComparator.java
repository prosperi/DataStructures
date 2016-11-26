import java.util.*;

 public class StringComparator implements Comparator<String>{
   
     @Override
     public int compare(String foo, String boo){       
        
        
        return (foo.length() >= boo.length()) ? 1 : -1;    
        
     }
     
}
