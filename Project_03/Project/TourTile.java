import java.util.ArrayList;
import java.util.LinkedList;

public class TourTile{
    
    public static final int INFINITY = Integer.MAX_VALUE;
    private ArrayList<Tourist> tourists;
    private LinkedList<TourEdge> adjacent;
    private TourTile prev;
    private int key;
    private int dist;
    private int scratch;
    private int x;
    private int y;
    
    private Cell cell;
    
    
    public TourTile(int key, Cell cell){
        tourists = new ArrayList<Tourist>();
        this.key = key;
        this.adjacent = new LinkedList<TourEdge>();
        this.x = cell.getX();
        this.y = cell.getY();
        this.cell = cell;
        reset();
    }
    
    public void reset(){
        dist = INFINITY;
        prev = null;
        scratch = 0;
    }
      
    public void addTourist(Tourist tourist){
        this.tourists.add(tourist);
    }
    
    public ArrayList<Tourist> getTourists(){
        return tourists;
    }
    
    public int getDist(){ 
        return dist; 
    }
    
    public int getScratch(){ 
        return scratch; 
    }
    
    public int getKey(){ 
        return key; 
    }
    
    public TourTile getPrev(){
        return prev; 
    }
    
    public LinkedList<TourEdge> getAdjacent(){ 
        return adjacent; 
    }
    
    public Cell getCell(){
        return this.cell;
    }
    
    public void setCell(Cell cell){
        this.cell = cell;
    }
    
    public int getX(){
        return this.x;
    }
    
    public int getY(){
        return this.y;
    }
    
    public String adjacentToString(){
       String str = "";
       for(int i = 0; i < adjacent.size(); i++){
           str += "  --> " + adjacent.get(i).getEnd().getKey() + "(" + adjacent.get(i).getWeight() + ")";
       }
       return str;
    }
    
}
