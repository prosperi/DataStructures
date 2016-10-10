import java.util.ArrayList;

/**
 * Zura Mestiashvili
 * v1.0.0
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
        map = new char[height][width];
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                map[i][j] = '#';
            }
        }     
        
        objectMap = new ArrayList[height][width];
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                objectMap[i][j] = new ArrayList<Specimen>();
            }
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
    
    public boolean checkCell(int[] position){
        if(objectMap[position[0]][position[1]].size() == 0) return true;
        else return false;
    }
    
    public void addHabitants(ArrayList<Specimen> habitants){
        for(int i = 0; i < habitants.size(); i++){
            Specimen habitant = habitants.get(i);
            int x = habitant.getX();
            int y = habitant.getY();
            map[x][y] = habitant.getSymbol();
            objectMap[x][y].add(habitant);
        }
       
    }
    
    public void clear(){
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                map[i][j] = '#';
                objectMap[i][j] = new ArrayList<Specimen>();
            }
        }
    }
    
    public void printMap(){
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                System.out.print(map[i][j] +" " );
            }
            System.out.println();
        }     
    }
    
}
