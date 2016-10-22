import java.util.ArrayList;

/** 
  * @desc this class is used for creating board-like structure
  * for our simulation. Instances of this Object represent
  * kind of map that contain information about the positions of habitants
  * and energy that can absorbed from light.
  * @author Zura Mestiashvili mestiasz@lafayette.edu
  * @version v1.0.0
*/

public class Terrain{
    private int width;
    private int height;
    private double light;
    public ArrayList<ArrayList<Character>> map;
    public ArrayList<ArrayList<ArrayList<Specimen>>> objectMap;
    
    
    public Terrain(int width, int height, double light){
        this.width = width;
        this.height = height;
        this.light = light;
        
        // Create Map where each cell is # at the beginning
        // And create objectMap where for each cell we have ArrayList of Specimen living in that cell
        map = new ArrayList<ArrayList<Character>>();
        objectMap = new ArrayList<ArrayList<ArrayList<Specimen>>>();
        for(int i = 0; i < height; i++){
            map.add(new ArrayList<Character>());
            objectMap.add(new ArrayList<ArrayList<Specimen>>());
            for(int j = 0; j < width; j++){
                map.get(i).add('#');
                objectMap.get(i).add(new ArrayList<Specimen>());
            }
        }     

        
    }
    
   
    
    /**
      * @desc checks if cell is empty(nobody lives there)
      * @param int x - x coordinate
      * @param int y - y coordinate
      * @return boolean - false if cell is not empty, true if nobody lives there
    */
    public boolean checkCell(ArrayList<Integer> position){
        if(objectMap.get(position.get(0)).get(position.get(1)).size() == 0) return true;
        else return false;
    }
    
    /**
      * @desc checks if cell is empty(nobody lives there)
      * @param int x - x coordinate
      * @param int y - y coordinate
      * @return boolean - false if cell is empty, true if at 
      * least one specimen lives there
    */
    public boolean checkCell(int x, int y){
        if(objectMap.get(x).get(y).size() == 0) return false;
        else return true;
    }
    
    /**
      * @desc add Habitants to the objectMap and update the 
      * map(representing positions of each specimen)
      * @param ArrayList<Specimen> habitants - container of all 
      * the habitants in our world.
    */
    public void addHabitants(ArrayList<Specimen> habitants){
        for(int i = 0; i < habitants.size(); i++){
            Specimen habitant = habitants.get(i);
            int x = habitant.getX();
            int y = habitant.getY();
            map.get(x).set(y, habitant.getSymbol());
            objectMap.get(x).get(y).add(habitant);
        }
       
    }
    
    /**
      * @desc clear the map and objectMap containers and get ready
      * for drawing next step results
    */
    public void clear(){
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                map.get(i).set(j, '#');
                objectMap.get(i).set(j, new ArrayList<Specimen>());
            }
        }
    }
    
    /**
      * @desc print whole map, using '#' for marking empty cells
      * and using specimen's own symbols to represent specimen
    */
    public void printMap(){
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                System.out.print(map.get(i).get(j) + " " );
            }
            System.out.println();
        }     
        
        /*System.out.println("Map by numbers:");
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                char tmp = map.get(i).get(j);
                if(tmp != '#')
                    System.out.println("("+ tmp + " " + i + ";" + j + ")" );
            }
        }*/  
    }
    
    /**
      * @desc getter for terrain width
      * @return int - width of terrain
    */
    public int getWidth(){
        return width;
    }
    
    /**
      * @desc getter for terrain height
      * @return int - height of terrain
    */
    public int getHeight(){
        return height;
    }
    
    /**
      * @desc getter for terrain light
      * @return double - light of terrain, which 
      * is food for plants
    */
    public double getLight(){
        return light;
    }
    
    /**
      * @desc setter for terrain light
      * @param int val - new value for light
    */
    public void setLight(double val){
        light = val;
    }
    
}
