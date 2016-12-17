/** 
  * @desc Route class is a helper for shortest tour 
  * generation method in Tour class. This class keeps 
  * the shortest path to the current place. Finally, when 
  * the shortest tour is generated it builds up the list 
  * of places(nodes) that tourists are going to visit.
  * @author Zura Mestiashvili mestiasz@lafayette.edu
  * @version v1.0.0
*/
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
    
    /**
     * @desc as Route implements Comparable interface we need to 
     * initialize compareTo method, which compares two Route objects
     * @params Route route - Route object that we are comparing
     * @return int - 1 if current route is shorter, -1 if current route is longer
     * 0 if they are equal length
    */
    public int compareTo(Route route){
       int tmpCost = route.cost;
       return cost < tmpCost ? 1 : cost > tmpCost ? -1 : 0;
    }
    
    /**
     * @desc get the String representation of the route
     * @return String str - String representation
    */
    public String toString(){
       String str = "";
       for(int i = 0; i < ls.size(); i++){
           str += " " + ls.get(i).getKey() + " ";
       }
       str += "|| " + this.cost;
       return str;
    }
    
    /**
     * @desc get the destination of the route
     * @return TourTile - destination
    */
    public TourTile getDest(){
        return this.dest;
    }
    
    /**
     * @desc get the cost of the route
     * @return int - cost
    */
    public int getCost(){
        return this.cost;
    }
   
    /**
     * @desc change the cost of the route
     * @params int  - new cost
    */
    public void setCost(int n){
        this.cost = n;
    }
    
    /**
     * @desc get the list of the tiles(nodes) on the route
     * @return LinkedList<TourTile> - list of the tiles(nodes)
    */
    public LinkedList<TourTile> getLs(){
        return this.ls;
    }
    
    /**
     * @desc build up the tiles' list, using tiles' prev values
    */
    public void generateLs(){
        TourTile tile = this.dest;
        while(tile != null){
            ls.add(0, tile);
            tile = tile.getPrev();
        }
    }

}
