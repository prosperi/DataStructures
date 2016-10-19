import java.util.*;

 public class SpecimenComparator implements Comparator<Specimen>{
   
     @Override
     public int compare(Specimen foo, Specimen boo){       
        
        if(!foo.getType().equals(boo.getType()))
           return foo.getType().compareTo(boo.getType());
        
        if(!foo.getSubgroup().equals(boo.getSubgroup()))
           return foo.getSubgroup().compareTo(boo.getSubgroup());
            
        if(foo.getAge() != boo.getAge())
            return (foo.getAge() > boo.getAge()) ? 1 : -1;
        
        return foo.getName().compareTo(boo.getName());
        
     }
     
}
