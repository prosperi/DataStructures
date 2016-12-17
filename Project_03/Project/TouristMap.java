/** 
  * @desc this class represents the tourist map,
  * in real life each tourist has a map that helps to
  * find the tours, entrances and exits. this class 
  * implelements those features.
  * @author Zura Mestiashvili mestiasz@lafayette.edu
  * @version v1.0.0
*/

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class TouristMap{
   
    private World world;
    private ArrayList<Tour> tours;
    private Map<Integer, TourTile> tiles;
    private char symbol;

    public TouristMap(World world){
        
      this.tours = new ArrayList<Tour>();  
      this.tiles = new HashMap<Integer, TourTile>();
      this.world = world;
      
    }
    
    /**
     * @desc add new tile(node) to the map and update
     * cell.tile.
     * @params int key - key for the new Tile
     * @params ArrayList<Integer> position - position in our World for the new Tile
     * @return boolean - true if successfully added
    */
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
    
    /**
     * @desc add new Edge to tourist map. The edge will connect two
     * tiles(nodes).
     * @params int start - starting point of the edge
     * @params int end  - ending point of the edge
     * @params int w - weight for the edge
     * @return boolean - true if successfully added
    */
    public boolean addEdge(int start, int end, int w){
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
    
    
    /**
     * @desc reset all the tiles(nodes) on our tourist map.
    */
    public void clearAll(){
        for(TourTile tile : tiles.values()){
            tile.reset();
        }
    }
    
    /**
     * @desc add new tour to the map
     * @params Tour tour - new Tour that should be added to container
    */
    public void addTour(Tour tour){
        clearAll();
        tours.add(tour);
    }
    
    /**
     * @desc allow Tourist to start their trip
    */
    public void guideTour(){
        for(Tour tour : tours){
            tour.proceed();
        }
    }
    
    /**
     * @desc get the total amount of tiles(nodes)
     * @return int - size of container tiles
    */
    public int getSize(){
        return tiles.size();
    }
    
    /**
     * @desc access the World object
     * @return World world - return the world in which this tourist map exists
    */
    public World getWorld(){
        return this.world;
    }
    
    /**
     * @desc find the value for the provided key
     * @params int key - key that we are searching for
     * @return Tourtile - get the tile by key
    */
    public TourTile getTile(int key){
        return this.tiles.get(key);
    }
    
    /**
     * @desc get all the tiles in this world
     * @return Map<Integer, TourTile> - container of all the tiles
    */
    public Map<Integer, TourTile> getTiles(){
        return this.tiles;
    }
    
    /**
     * @desc get all the tours in this world
     * @return ArrayList<Tour> - get the container of tours
    */
    public ArrayList<Tour> getTours(){
        return this.tours;
    }
    
     /**
     * @desc get the String representation of whole tourist map
     * @return String str - string representation of the map
    */
    public String toString(){
       String str = "";
       for(TourTile tile : tiles.values()){
           str += tile.getKey() + " | Tourists: " + tile.getTourists().size() + " " +tile.adjacentToString() + "\n";
       }
       return str;
   }
}
