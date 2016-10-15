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
    public char[][] map;
    public ArrayList<Specimen>[][] objectMap;
    
    
    public Terrain(int width, int height, double light){
        this.width = width;
        this.height = height;
        this.light = light;
        
        /// Create Map where each cell is # at the beginning
        /// And create objectMap where for each cell we have ArrayList of Specimen living in that cell
        map = new char[height][width];
        objectMap = new ArrayList[height][width];
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                map[i][j] = '#';
                objectMap[i][j] = new ArrayList<Specimen>();
            }
        }     

        
    }
    
   
    
    // Check if cell is taken  ---> should delete this one
    public boolean checkCell(ArrayList<Integer> position){
        if(objectMap[position.get(0)][position.get(1)].size() == 0) return true;
        else return false;
    }
    // Use overloading and use this method in Animal and Plant lockedBirth method
    public boolean checkCell(int x, int y){
        if(objectMap[x][y].size() == 0) return false;
        else return true;
    }
    
    // Add Habitants or just re-Draw the map and objectMap
    public void addHabitants(ArrayList<Specimen> habitants){
        for(int i = 0; i < habitants.size(); i++){
            Specimen habitant = habitants.get(i);
            int x = habitant.getX();
            int y = habitant.getY();
            map[x][y] = habitant.getSymbol();
            objectMap[x][y].add(habitant);
        }
       
    }
    
    // Clear the terrain to get ready for redraw
    public void clear(){
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                map[i][j] = '#';
                objectMap[i][j] = new ArrayList<Specimen>();
            }
        }
    }
    
    // Print current state, each empty cell is marked with #, while others 
    // are marked with Specimen's own symbol
    public void printMap(){
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                System.out.print(map[i][j] +" " );
            }
            System.out.println();
        }     
    }
    
    
    public int getWidth(){
        return width;
    }
    
    public int getHeight(){
        return height;
    }
    
    public double getLight(){
        return light;
    }
    
    public void setLight(int val){
        light = val;
    }
    
}
