import java.util.*;
/** 
  * @desc this class represents the World which
  * consists of mountains, species and cells
  * @author Zura Mestiashvili mestiasz@lafayette.edu
  * @version v1.0.0
*/
public class World
{
    private List<List<Cell>> board;
    private int lightEnergy;
    private int steps;
    
    public World(int m, int n, int l) {        
        lightEnergy = l;
        steps = 0;
        board = new ArrayList<List<Cell>>();
        for(int i = 0; i < m; i++) {
            board.add(new ArrayList<Cell>());
            for(int j = 0; j < n; j++) {
                board.get(i).add(new Cell(this, i, j));
            }
        }
    }
    
    public int getLightEnergy() {
        return lightEnergy;
    }
    
    public Cell get(int row, int col) {
        return this.board.get(row).get(col);
    }
    
    public int getHeight() {
        return this.board.size();
    }
    
    public int getWidth() {
        return this.board.get(0).size();
    }
    
    public int getSteps() {
        return this.steps;
    }
    
    /**
     * Goes through each cell and runs the activities for each species
     * Also adds a new element to the birth and death lists so they can be 
     * tracker for the turn
     */
    public void turn() {
        int curSteps = this.getSteps();
        if(Species.getDeaths().size() == curSteps) {
            Species.getDeaths().add(new ArrayList<Integer>());
            for(int i = 0; i < Species.getSpecies().size(); i++) {
                Species.getDeaths().get(curSteps).add(0);
            }
        }
        if(Species.getBirths().size() == curSteps) {
            Species.getBirths().add(new ArrayList<Integer>());
            for(int i = 0; i < Species.getSpecies().size(); i++) {
                Species.getBirths().get(curSteps).add(0);
            }
        }
        for(int i = 0; i < board.size(); i++) {
            List<Cell> row = board.get(i);
            for(int j = 0; j < row.size(); j++) {
                Cell cell = row.get(j);
                if(cell.getAnimal() != null) {
                    cell.getAnimal().activity();
                }
                if(cell.getPlant() != null) {
                    cell.getPlant().activity();
                }
            }
        }
        for(int i = 0; i < board.size(); i++) {
            List<Cell> row = board.get(i);
            for(int j = 0; j < row.size(); j++) {
                Cell cell = row.get(j);
                if(cell.getAnimal() != null) {
                    cell.getAnimal().moved = false;
                }
            }
        }
        steps++;
    }
    
    /**
     * Prints the board in an easy to view way
     */
    public void print() {
        for(int i = 0; i < board.size(); i++) {
            List<Cell> row = board.get(i);
            System.out.print("|");
            for(int j = 0; j < row.size(); j++) {
                Cell cell = row.get(j);
                String message = "";
                if(cell.getAnimal() != null) {
                    message += cell.getAnimal().getRepresentation();
                }
                if(message.length() > 0 && cell.getPlant() != null) {
                    message += "/";
                }
                if(cell.getPlant() != null) {
                    message += cell.getPlant().getRepresentation();
                }
                //Mountains will be represented with *
                if(cell.getMountain() != null){
                    message += cell.getMountain().getRepresentation();
                }
                System.out.print(message + "\t|");
            }
            System.out.println();
            System.out.println("-----------------------------------------------------------------------------------------------------");
        }
    }
    
    /**
     * Takes a species and generates random coordinates until it finds an empty cell for it
     * @param Species s - the species that should be randomly added to the world
     */
    public void randomAddToWorld(Species s) {
        Random generator = new Random(Simulation.SEED);
        boolean found = false;
        int count = 0; //Prevents infinite loop in case more species are trying to be added than there are spaces for them
        while(!found && count < 10000) {
            count++;
            int row = generator.nextInt(this.board.size());
            int col = generator.nextInt(this.board.get(0).size());
            Cell cell = this.board.get(row).get(col);
            
            if(s instanceof Animal) {
                //z/ check if there are no animals and no mountains in the cell
                if(cell.getAnimal() == null && cell.getMountain() == null) {
                    cell.setAnimal((Animal)s);
                    s.setCell(cell);
                    found = true;
                }
            } else if(s instanceof Plant) {
                //z/ check if the cell is empty(no animals, no mountains)
                if(cell.getPlant() == null && cell.getMountain() == null) {
                    cell.setPlant((Plant)s);
                    s.setCell(cell);
                    found = true;
                }
            }
        }
    }
    
    
    /**
     * Draw Mountains on the board with line approximation algorithm.
     * For that we use Bresenham's line algorithm.
     * @param Mountain mountain - Mountain mountain
     * @reference https://rosettacode.org/wiki/Bitmap/Bresenham%27s_line_algorithm#JavaScript
     */
    public void addMountainToWorld(Mountain mountain){
        System.out.println("Here comes the Mountain again");
        
        // Find the Starting and Ending points
        int x0 = mountain.getStartingX(),
            y0 = mountain.getStartingY(),
            x1 = mountain.getEndingX(),
            y1 = mountain.getEndingY();
        // find the direction of line and start applying Bresenham's Algorithm
        int dx = Math.abs(x1 - x0), 
            sx = x0 < x1 ? 1 : -1;
        int dy = Math.abs(y1 - y0), 
            sy = y0 < y1 ? 1 : -1;
        int err = (dx > dy ? dx : -dy)/2;
    
        while(true){
            // if we are still inside the loop, xurrent x0 and y0 are coordinates of Mountain
            plot(x0, y0, mountain);
            if(x0 == x1 && y0 == y1) break;
            int e2 = err;
            if(e2 > -dx) {
              err -= dy;
              x0 += sx;
            }
            if(e2 < dy) {
              err += dx;
              y0 += sy;
            }
        }
    }
    
    /**
     * Place mountain on the board
     * @param int x - mountain's x coordinate
     * @param int y - mountain's y coordinate
     * @param Mountain mountain - Mountain mountain
     */
    void plot(int x, int y, Mountain mountain){
      Cell cell = this.board.get(x).get(y);
      cell.setMountain(mountain);
    }
 
}
