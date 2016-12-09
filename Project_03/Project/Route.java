import java.util.LinkedList;

public class Route implements Comparable<Route>{
    private TourTile dest;
    private int cost;
    private LinkedList<TourTile> ls;
    private LinkedList<TourTile> test;
    
    public Route(TourTile dest, int cost){
       this.dest = dest;
       this.cost = cost;
       this.ls = new LinkedList<TourTile>();
       
    }
    
    public int compareTo(Route route){
       int tmpCost = route.cost;
       return cost < tmpCost ? 1 : cost > tmpCost ? -1 : 0;
    }
    
    public String toString(){
       String str = "";
       for(int i = 0; i < ls.size(); i++){
           str += " " + ls.get(i).getKey() + " ";
       }
       str += "|| " + this.cost;
       return str;
    }
    
    public TourTile getDest(){
        return this.dest;
    }
    
    public int getCost(){
        return this.cost;
    }
   
    public void setCost(int n){
        this.cost = n;
    }
    
    public LinkedList<TourTile> getLs(){
        return this.ls;
    }
    
    public void generateLs(){
        TourTile tile = this.dest;
        while(tile != null){
            ls.add(0, tile);
            tile = tile.getPrev();
        }
    }

}
