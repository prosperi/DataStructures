import java.util.LinkedList;
/** 
  * @desc This class is used to create Path objects
  * that are lists of cells
  * @author Zura Mestiashvili mestiasz@lafayette.edu
  * @version v1.0.0
*/
public class Path
{
    private LinkedList<Cell> path;
    private int deadEnd;
    private boolean plantOnTheWay;
    private Cell plantCell;
    
    public Path(){
        this.path = new LinkedList<Cell>();
        this.deadEnd = 3;
        this.plantOnTheWay = false;
        this.plantCell = null;
    }
    
    /**
     * @desc add next cell to the path
     * @params cell - Cell
     */
    public void add(Cell cell){
        path.add(cell);
    }
    /**
     * @desc get the whole path
     * @return path - LinkedList
     */
    public LinkedList getPath(){
        return path;
    }
    /**
     * @desc set Dead End to the path
     * Dead End represent what specimen can find at the end of the road:
     * mountain, predator, food or nothing
     * @params x - int
     */
    public void setDeadEnd(int x){
        deadEnd = x;
    }
    /**
     * @desc get DeadEnd
     * @return cdeadEnd - int
     */
    public int getDeadEnd(){
        return deadEnd;
    }
    /**
     * @desc get the size of path
     * @return size - int
     */
    public int size(){
        return path.size();
    }
    /**
     * @desc getCell from the path
     * @params x - int
     * @return cell - Cell
     */
    public Cell getCell(int x){
        return path.get(x);
    }
    /**
     * @desc get last cell from the path
     * @return cell - Cell
     */
    public Cell getLast(){
        return path.getLast();
    }
    /**
     * @desc find if there is plant o our way that we can eat
     * @return plantOnTheWay - boolean
     */
    public boolean getPlantOnTheWay(){
        return plantOnTheWay;
    }
     /**
     * @desc set the value of plantOnTheWay
     * @params boolean t
     */
    public void setPlantOnTheWay(boolean t){
        plantOnTheWay = t;
    }
    
    /**
     * @desc get cell where plant lives
     * @return plantCell - Cell
     */
    public Cell getPlantCell(){
        return plantCell;
    }
    /**
     * @desc set the value of PlantCell
     * @params cell - Cell
     */
    public void setPlantCell(Cell cell){
        plantCell = cell;
    }
    
     /**
     * @desc get the String representation of the Path
     * @return str - String
     */
    public String toString(){
        String str = "";
        for(int i = 0; i < path.size(); i++){
            str += path.get(i).getX() + " " + path.get(i).getY() +"   ";
        }
        
        return str;
    }
}
