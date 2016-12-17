/** 
  * @desc This class connect two places(nodes) in the tour
  * and specifies the cost of moving to that place(node).
  * @author Zura Mestiashvili mestiasz@lafayette.edu
  * @version v1.0.0
*/

public class TourEdge{
    
    private TourTile start;
    private TourTile end;
    private int weight;
    
    public TourEdge(TourTile start,TourTile end, int w){
        this.start = start;
        this.end = end;
        this.weight = w;
    }
    
    /**
     * @desc get the weight of the edge
     * @return int - weight
    */
    public int getWeight(){
        return this.weight;
    }
    
    /**
     * @desc get the tile that is at the end of the edge
     * @return TourTile - tile 
    */
    public TourTile getEnd(){
        return this.end;
    }
    
    /**
     * @desc get the tile that is at the beginning of the edge
     * @return TourTile - tile 
    */
    public TourTile getStart(){
        return this.start;
    }
    
}