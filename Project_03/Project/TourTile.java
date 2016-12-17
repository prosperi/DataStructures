/** 
  * @desc This class represents place(node) of the Tour.
  * TourTile provides information about the tourists who occupy
  * this tile and is used in shortest tour generation.
  * @author Zura Mestiashvili mestiasz@lafayette.edu
  * @version v1.0.0
*/

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
    private char symbol;
    
    private Cell cell;
    
    
    public TourTile(int key, Cell cell){
        tourists = new ArrayList<Tourist>();
        this.key = key;
        this.adjacent = new LinkedList<TourEdge>();
        this.x = cell.getX();
        this.y = cell.getY();
        this.cell = cell;
        symbol = ' ';
        reset();
    }
    
    /**
     * @desc reset the tile
    */
    public void reset(){
        this.dist = INFINITY;
        this.prev = null;
        this.scratch = 0;
    }
      
    /**
     * @desc addTourist to the tile
     * @params Tourist tourist - that should be added to tourists list
     * @return boolean - true if successfully added
    */
    public boolean addTourist(Tourist tourist){
        if(tourists.size() < tourist.getTour().getCapacity()){
            this.tourists.add(tourist);
            return true;
        }
        
        return false;
    }
    
    /**
     * @desc get the list of tourists
     * @return ArrayList<Tourist> - list of tourists
    */
    public ArrayList<Tourist> getTourists(){
        return tourists;
    }
    
    /**
     * @desc get the dist value of the tile
     * @return int - dist
    */
    public int getDist(){ 
        return dist; 
    }
    
    /**
     * @desc set the dist value of the tile
     * @params new dist
    */
    public void setDist(int dist){ 
        this.dist = dist;
    }
    
    /**
     * @desc get the scratched value of tile
     * @return int - scratch 
    */
    public int getScratch(){ 
        return scratch; 
    }
    
    /**
     * @desc change the scratched value of tile
     * @params int scratch
    */
    public void setScratch(int scratch){ 
        this.scratch = scratch; 
    }
    
    /**
     * @desc get the key of the tile
     * @return int - key
    */
    public int getKey(){ 
        return key; 
    }
    
    /**
     * @desc get the prev tile for this tile
     * @return TourTile - prev
    */
    public TourTile getPrev(){
        return prev; 
    }
    
    /**
     * @desc set the prev tile for this tile
     * @params TourTile - new prev value
    */
    public void setPrev(TourTile tile){
        this.prev = tile;
    }
    
    /**
     * @desc get the list of adjacent edges
     * @return LinkedList<TourEdge> - adjacent
    */
    public LinkedList<TourEdge> getAdjacent(){ 
        return adjacent; 
    }
    
    /**
     * @desc get the Cell of the tile
     * @return Cell - cell
    */
    public Cell getCell(){
        return this.cell;
    }
    
    /**
     * @desc chnage the cell
     * @params Cell - new cell value
    */
    public void setCell(Cell cell){
        this.cell = cell;
    }
    
    /**
     * @desc get the X coordinate of the tile
     * @return int - x
    */
    public int getX(){
        return this.x;
    }
    
    /**
     * @desc get the Y coordinate of the tile
     * @return int - y
    */
    public int getY(){
        return this.y;
    }
    
    /**
     * @desc get the Symbol of the tile
     * @return char - symbol
    */
    public char getSymbol(){
        return this.symbol;
    }
    
    /**
     * @desc set the Symbol of the tile
     * @params char symbol - new value
    */
    public void setSymbol(char symbol){
        this.symbol = symbol;
    }
    
    /**
     * @desc get the String representation of adjacent edges
     * @return String - String representation
    */
    public String adjacentToString(){
       String str = "";
       for(int i = 0; i < adjacent.size(); i++){
           str += "  --> " + adjacent.get(i).getEnd().getKey() + "(" + adjacent.get(i).getWeight() + ")";
       }
       return str;
    }
    
}
