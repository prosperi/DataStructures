import java.util.Comparator;
import java.util.ArrayList;

public class PredatorCellComparator implements Comparator<ArrayList<Cell>>{
    
    @Override
    public int compare(ArrayList<Cell> foo, ArrayList<Cell> boo){
        int f = 0, b = 0;
        ArrayList<Cell> fooPath = buildPath(foo);
        ArrayList<Cell> booPath = buildPath(boo);
        Animal current = foo.get(0).getAnimal();
       
        for(int i = 0; i < fooPath.size(); i++){
            Animal tmp = fooPath.get(i).getAnimal();
            if(tmp != null && tmp.getEnergySources().contains(current)){
                f = i;
                break;
            }
            
        }
        
        for(int i = 0; i < booPath.size(); i++){
            Animal tmp = booPath.get(i).getAnimal();
            if(tmp != null && tmp.getEnergySources().contains(current)){
                b = i;
                break;
            }
            
        }
        
        return f >= b ? 1 : -1;
    }
   
    public ArrayList<Cell> buildPath(ArrayList<Cell> arr){
        ArrayList<Cell> path = new ArrayList<Cell>();
        
        Cell start = arr.get(0),
             finish = arr.get(1);
        World tmp = start.getWorld();
             
        int x0 = start.getX(),
            y0 = start.getY(),
            x1 = finish.getX(),
            y1 = finish.getY();
        
        int dx = Math.abs(x1 - x0), 
            sx = x0 < x1 ? 1 : -1;
        int dy = Math.abs(y1 - y0), 
            sy = y0 < y1 ? 1 : -1;
        int err = (dx > dy ? dx : -dy)/2;
    
        while(true){
            if(tmp.get(x0, y0) == start) 
                continue;
            
            path.add(tmp.get(x0, y0));
            if(x0 == x1 && y0 == y1) break;
            int e2 = err;
            if(e2 > -dx) {
              err -= dy;
              x0 += sx;
            }
            if(e2 < dy) {
              err += dx;
              y0 += sy;
            }
        }
        
        return path;
    }
}
