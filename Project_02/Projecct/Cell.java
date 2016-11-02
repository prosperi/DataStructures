
/**
 * Class Cell creates a cell to be used by Class Grid. This class features a variety of get/set methods
 * to access and manipulate a cell's information.
 * 
 * @version (10/15/2016)
 */
public class Cell
{
    SpeciesInstance sIP;
    SpeciesInstance sIA;
    boolean isOccupied;
    boolean isOccupiedPlant;
    boolean isOccupiedAnimal;
    String plantSymbol;
    String animalSymbol;
    int colPos;
    int rowPos;
    
    /**
     * Constructs a new cell at position (rP, cP)
     * @param cP int variable representing cell's position in its column
     * @param rP int variable representing cell's position in its row
     */
    public Cell(int cP, int rP)
    {
        isOccupied = false;
        isOccupiedPlant = false;
        isOccupiedAnimal = false;
        plantSymbol = " ";
        animalSymbol = " ";
        colPos = cP;
        rowPos = rP;
        sIP = null;
        sIA = null;
    }
    
    /**
     * Sets a cell's plant SpeciesInstance variable
     * @param sI SpeciesInstance to set a cell's plant SpeciesInstance variable sIP to
     */
    public void setSpeciesInstancePlant(SpeciesInstance sI)
    {
        sIP = sI;
    }
    
    /**
     * Gets a cell's plant SpeciesInstance variable
     * @return cell's plant SpeciesInstance variable
     */
    public SpeciesInstance getSpeciesInstancePlant()
    {
        return sIP;
    }
    
    /**
     * Sets a cell's animal SpeciesInstance variable
     * @param sI SpeciesInstance to set a cell's animal SpeciesInstance variable sIA to
     */
    public void setSpeciesInstanceAnimal(SpeciesInstance sI)
    {
        sIA = sI;
    }
    
    /**
     * Gets a cell's animal SpeciesInstance variable
     * @return cell's animal SpeciesInstance variable
     */
    public SpeciesInstance getSpeciesInstanceAnimal()
    {
        return sIA;
    }
    
    /**
     * Gets a cell's plant symbol
     * @return String representing cell's plant symbol
     */
    public String getPlantSymbol()
    {
        return plantSymbol;
    }
    
    /**
     * Sets a cell's plant symbol
     * @param sym String to set cell's plantSymbol to
     */
    public void setPlantSymbol(String sym)
    {
        plantSymbol = sym;
    }
    
    /**
     * Gets a cell's animal symbol
     * @return String representing cell's animal symbol
     */
    public String getAnimalSymbol()
    {
        return animalSymbol;
    }
    
    /**
     * Sets a cell's animal symbol
     * @param sym String to set cell's animalSymbol to
     */
    public void setAnimalSymbol(String sym)
    {
        animalSymbol = sym;
    }
    
    /**
     * Gets a cell's isOccupied variable
     * @return boolean variable to represent whether the cell is occupied
     */
    public boolean getIsOccupied()
    {
        return isOccupied;
    }
    
    /**
     * Sets a cell's isOccupied variable
     * @param occupied boolean variable to set a cell's isOccupied variable to
     */
    public void setIsOccupied(boolean occupied)
    {
        isOccupied = occupied;
    }
    
    /**
     * Gets a cell's plant isOccupied variable
     * @return boolean variable to represent whether the cell is occupied by a plant
     */
    public boolean getIsOccupiedPlant()
    {
        return isOccupiedPlant;
    }
    
    /**
     * Sets a cell's isOccupiedPlant variable
     * @param occupied boolean variable to set a cell's isOccupiedPlant variable to
     */
    public void setIsOccupiedPlant(boolean occupied)
    {
        isOccupiedPlant = occupied;
    }
    
    /**
     * Gets a cell's animal isOccupied variable
     * @return boolean variable to represent whether the cell is occupied by an animal
     */
    public boolean getIsOccupiedAnimal()
    {
        return isOccupiedAnimal;
    }
    
    /**
     * Sets a cell's isOccupiedAnimal variable
     * @param occupied boolean variable to set a cell's isOccupiedAnimal variable to
     */
    public void setIsOccupiedAnimal(boolean occupied)
    {
        isOccupiedAnimal = occupied;
    }
    
    /**
     * Gets a cell's column position in the grid
     * @return int variable to represent the cell's position in its column
     */
    public int getColPos()
    {
        return colPos;
    }
    
    /**
     * Gets a cell's row position in the grid
     * @return rowPos int variable to represent the cell's position in its row
     */
    public int getRowPos()
    {
        return rowPos;
    }
}
