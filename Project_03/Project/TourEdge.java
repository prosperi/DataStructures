public class TourEdge{
    
    private TourTile start;
    private TourTile end;
    private int weight;
    
    public TourEdge(TourTile start,TourTile end, int w){
        this.start = start;
        this.end = end;
        this.weight = w;
    }
    
    public int getWeight(){
        return this.weight;
    }
    
    public TourTile getEnd(){
        return this.end;
    }
    
    public TourTile getStart(){
        return this.start;
    }
    
}