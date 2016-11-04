import java.util.Comparator;
import java.util.ArrayList;

public class CellComparator implements Comparator<ArrayList<Cell>>{
    
    @Override
    public int compare(ArrayList<Cell> foo, ArrayList<Cell> boo){
        int f = 0, b = 0;
        
        Cell cell_01 = foo.get(1),
             cell_02 = boo.get(1),
             current = foo.get(0);
        Animal animal = current.getAnimal();
        
        if(cell_01.getAnimal() != null && animal.getEnergySources().contains(cell_01.getAnimal())){
            f++;
        }
        if(cell_02.getAnimal() != null && animal.getEnergySources().contains(cell_02.getAnimal())){
            b++;
        }
        
        if(cell_01.getPlant() != null && animal.getEnergySources().contains(cell_01.getPlant())){
            f++;
        }
        if(cell_02.getPlant() != null && animal.getEnergySources().contains(cell_02.getPlant())){
            b++;
        }
       
        
        return f >= b ? -1 : 1;
    }
   
}
