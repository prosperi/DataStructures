import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class TouristMap{
   
    private World world;
    private ArrayList<Tour> tours;
    private Map<Integer, TourTile> tiles;

    public TouristMap(World world){
        
      this.tours = new ArrayList<Tour>();  
      this.tiles = new HashMap<Integer, TourTile>();
      this.world = world;
      
    }
    
    public boolean addTile(int key, ArrayList<Integer> position){
        if(tiles.get(key) != null)
            return false;
            
        int x = position.get(0);
        int y = position.get(1);
        Cell cell = world.get(x, y);
        TourTile tile = new TourTile(key, cell);
        tiles.put(key, tile);
        cell.setTile(tile);

        
        return true;
    }
    
    public boolean addEdge(int start, int end, int w){
        // what if negative
        TourTile tile_01 = tiles.get(start);
        TourTile tile_02 = tiles.get(end);
        
        if(tile_01 == null || tile_02 == null){
            return false;
        }
        
        TourEdge edge_01 = new TourEdge(tile_01, tile_02, w);
        TourEdge edge_02 = new TourEdge(tile_02, tile_01, w);
        tile_01.getAdjacent().add(edge_01);
        tile_02.getAdjacent().add(edge_02);
        
        return true;
    }
    
    
    
    public void clearAll(){
        for(TourTile tile : tiles.values()){
            tile.reset();
        }
    }
    
    public void addTour(Tour tour){
        tours.add(tour);
    }
    
    public void guideTour(){
        for(Tour tour : tours){
            tour.proceed();
        }
    }
    
    public int getSize(){
        return tiles.size();
    }
    
    public World getWorld(){
        return this.world;
    }
    
    public TourTile getTile(int key){
        return this.tiles.get(key);
    }
    
    public Map<Integer, TourTile> getTiles(){
        return this.tiles;
    }
    
    public ArrayList<Tour> getTours(){
        return this.tours;
    }
    
    
    public String toString(){
       String str = "";
       for(TourTile tile : tiles.values()){
           str += tile.getKey() + " | Tourists: " + tile.getTourists().size() + " " +tile.adjacentToString() + "\n";
       }
       return str;
   }
}
