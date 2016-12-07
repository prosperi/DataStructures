import java.util.LinkedList;

public class Route implements Comparable<Route>{
    private TourTile dest;
    private int cost;
    private LinkedList<TourTile> ls;
    
    public Route(TourTile dest, int cost, LinkedList<TourTile> ls){
       this.dest = dest;
       this.cost = cost;
       this.ls = ls != null ? new LinkedList<TourTile>(ls) : new LinkedList<TourTile>();
       this.ls.add(dest);
       
    }
    
    public int compareTo(Route route){
       int tmpCost = route.cost;
       return cost < tmpCost ? -1 : cost > tmpCost ? 1 : 0;
    }
    
    public String toString(){
       String str = "";
       for(int i = 0; i < ls.size(); i++){
           str += " " + ls.get(i).getKey() + " ";
       }
       
       return str;
    }
    
    public TourTile getDest(){
        return this.dest;
    }
    
    public int getCost(){
        return this.cost;
    }
    
    public LinkedList<TourTile> getLs(){
        return this.ls;
    }
}