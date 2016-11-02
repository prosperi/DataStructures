import java.io.*;
import java.util.*;

/**
 * Class Grid creates a two-dimensional ArrayList filled with Cell objects to represent a grid. 
 * This grid can be printed, filled, and accessed by Parks and Species.
 * 
 * @version (10/15/2016)
 */

public class Grid
{
    private int rowsSize;
    private int columnsSize;
    private ArrayList<ArrayList<Cell>> grid;

    /**
     * Constructs a two-dimensional ArrayList filled with (r*c) Cell objects to be used as grid.
     * @param r int number of rows (first dimension of grid)
     * @param c int number of columns (second dimension of grid)
     */
    public Grid(int r, int c)
    {
        rowsSize = r;
        columnsSize = c;
        grid = new ArrayList<ArrayList<Cell>>();
        
        for(int i = 0; i < rowsSize; i++) //fill grid with empty cells
        {
            ArrayList<Cell> column = new ArrayList<Cell>();
            for(int j = 0; j < columnsSize; j++)
            {
                column.add(new Cell(j,i));
            }
            grid.add(column);
        }
    }
    
    /**
     * Gets a constructed grid
     * @return Grid variable grid
     */
    public ArrayList<ArrayList<Cell>> getGrid() //used for unit testing only
    {
        return grid;
    }
    
    /**
     * Formats and prints the constructed grid
     */
    public void printGrid()
    {
        for(int i = 0; i < columnsSize; i++) //print top line
        {
            System.out.print(" ----");
        }
        System.out.println();
        
        for(int i = 0; i < rowsSize; i++) //for each row
        {
            System.out.print("|");
            for(int j = 0; j < columnsSize; j++) //print symbols
            {
                System.out.print(grid.get(i).get(j).getPlantSymbol() + " " + grid.get(i).get(j).getAnimalSymbol() + " |");
            }
            System.out.println();
            for(int m = 0; m < columnsSize; m++) //after printing each row, print line
            {
                System.out.print(" ----");
            }
            System.out.println();
        }
    }
    
    /**
     * Fills the constructed grid with all the SpeciesInstances of each species featured in the park
     * @param allSpecies LinkedList<ArrayList<Species>>, a LinkedList of ArrayLists of Species representing 
     * all the species within the park
     */
    public void fillGrid(LinkedList<ArrayList<Species>> allSpecies)
    {
        RandomNumberGenerator newRandom = new RandomNumberGenerator();
        
        //first account for the chance that the configfile specified a number of instances that the grid size could not fit
        
        int totalSpaces = (rowsSize * columnsSize)*2; //number of spaces available in grid
        int instancesToPlace = 0; //amount of spaces needed to fit all species
        
        //find instancesToPlace
        for(int i = 0; i < allSpecies.size(); i++) //for each arraylist of linked list of species
        {
            for(int j = 0; j < allSpecies.get(i).size(); j++) //for each arraylist in arraylist array
            {
                instancesToPlace += allSpecies.get(i).get(j).getSpecies().size(); //add size of each species to instancesToPlace
            }
        }
        
        if(instancesToPlace > totalSpaces)
        {
            System.out.println("Your initial populations may be too big for this grid size. Please increase grid size and try again.");
        }
        
        //numberOfInstances will fit in grid, so start placing
        for(int i = 0; i < allSpecies.size(); i++) //for each array list in linked list allSpecies
        {
            for(int j = 0; j < allSpecies.get(i).size(); j++) //for each array list in arraylist
            {
                for(int k = 0; k < allSpecies.get(i).get(j).getSpecies().size(); k++) //for each instance in arraylist
                {
                    int rowNum = newRandom.returnRandom(rowsSize-1); //random row element 
                    int colNum = newRandom.returnRandom(columnsSize-1); //random column element
                    if(grid.get(rowNum).get(colNum).getIsOccupied() == false) //if there is nothing in spot, place
                    {
                        allSpecies.get(i).get(j).getSpecies().get(k).setCell(grid.get(rowNum).get(colNum)); //set cell variable in species instance
                        if(allSpecies.get(i).get(j).getType().equals("fruit") || allSpecies.get(i).get(j).getType().equals("vegetable")) //if species is plant
                        {  
                            grid.get(rowNum).get(colNum).setIsOccupied(true); //cell is occupied
                            grid.get(rowNum).get(colNum).setIsOccupiedPlant(true); //by plant
                            grid.get(rowNum).get(colNum).setPlantSymbol(allSpecies.get(i).get(j).getPlantSymbol());
                            grid.get(rowNum).get(colNum).setSpeciesInstancePlant(allSpecies.get(i).get(j).getSpecies().get(k)); //give cell variable that instance
                        }
                        else //species is animal
                        {
                            grid.get(rowNum).get(colNum).setIsOccupied(true); //cell is occupied
                            grid.get(rowNum).get(colNum).setIsOccupiedAnimal(true); //by animal
                            grid.get(rowNum).get(colNum).setAnimalSymbol(allSpecies.get(i).get(j).getAnimalSymbol());
                            grid.get(rowNum).get(colNum).setSpeciesInstanceAnimal(allSpecies.get(i).get(j).getSpecies().get(k)); //give cell variable that instance
                        }
                    }
                    else //space is occupied, is there room for another a plant or animal?
                    {
                        boolean placed = false;
                        while(placed == false) //while instance has not been placed
                        {
                            if(grid.get(rowNum).get(colNum).getIsOccupiedPlant() == true && grid.get(rowNum).get(colNum).getIsOccupiedAnimal() == true) //if space is totally full
                            {
                                rowNum = newRandom.returnRandom(rowsSize-1); //find new coordinates
                                colNum = newRandom.returnRandom(columnsSize-1);
                            }
                            //either plant or animal space is not occupied
                            else if(allSpecies.get(i).get(j).getType().equals("fruit") || allSpecies.get(i).get(j).getType().equals("vegetable")) //if species is plant
                            {  
                                if(grid.get(rowNum).get(colNum).getIsOccupiedPlant() == true) //plant space is full
                                {
                                    rowNum = newRandom.returnRandom(rowsSize-1); //find new coordinates
                                    colNum = newRandom.returnRandom(columnsSize-1);
                                }
                                else //plant space is empty
                                {
                                    allSpecies.get(i).get(j).getSpecies().get(k).setCell(grid.get(rowNum).get(colNum)); //set cell variable in species instance
                                    grid.get(rowNum).get(colNum).setIsOccupiedPlant(true); //cell now is occupied by plant
                                    grid.get(rowNum).get(colNum).setPlantSymbol(allSpecies.get(i).get(j).getPlantSymbol());
                                    grid.get(rowNum).get(colNum).setSpeciesInstancePlant(allSpecies.get(i).get(j).getSpecies().get(k));
                                    placed = true; //instance has been placed
                                }
                            }
                            else //species is animal
                            {
                                if(grid.get(rowNum).get(colNum).getIsOccupiedAnimal() == true)
                                {
                                    rowNum = newRandom.returnRandom(rowsSize-1); //find new coordinates
                                    colNum = newRandom.returnRandom(columnsSize-1);
                                }
                                else //animal space is empty
                                {
                                    allSpecies.get(i).get(j).getSpecies().get(k).setCell(grid.get(rowNum).get(colNum)); //set cell variable in species instance
                                    grid.get(rowNum).get(colNum).setIsOccupiedAnimal(true); //cell is now occupied by animal
                                    grid.get(rowNum).get(colNum).setAnimalSymbol(allSpecies.get(i).get(j).getAnimalSymbol());
                                    grid.get(rowNum).get(colNum).setSpeciesInstanceAnimal(allSpecies.get(i).get(j).getSpecies().get(k));
                                    placed = true; //instance has been placed
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    
    /**
     * Determines the cells surrounding a given SpeciesInstance that the instance can use to move or give birth
     * @param x SpeciesInstance whose surrounding cells are examined
     * @return ArrayList<Cell>, an ArrayList of Cell objects representing all the surrounding cells the SpeciesInstance can use
     */
    public ArrayList<Cell> surroundingEmptyCells(SpeciesInstance x) //returns the cells surrounding x that can be moved to 
    {
        int currRowPos = x.getCell().getRowPos();
        int currColPos = x.getCell().getColPos();
        ArrayList<Cell> cellList = new ArrayList<Cell>();
        String type = x.getType();
        
        //all cells have 8 adjacent cells, except for the four corners of the grid, which have 3 adjacent cells, and other cells on the four sides of the grid,
        //which have 5 adjacent cells. This method first checks which category the given element fits in: is it a corner? If not, is it on a side? If not, then
        //it is an element in the middle of the board. Within each of these categories, we determine whether the particular instance is a plant or an animal.
        //If it is a plant, then it checks the adjacent cells to see if they are already occupied by a plant. If it is an animal, then it checks the adjacent
        //cells to see if they are already occupied by an animal. If there is room for the instance to move to a particular adjacent cell, then that cell is 
        //added to an ArrayList of cells that is returned once all adjacent cells have been checked
        
        if(currRowPos == 0 && currColPos == 0) //top left corner
        {
            if(type.equals("fruit") || type.equals("vegetable")) //if instance is a plant
            {
                if(grid.get(0).get(1).getIsOccupiedPlant() == false) //is occupied by plant? If not, add to cell to list
                {
                    cellList.add(grid.get(0).get(1));
                }
                if(grid.get(1).get(0).getIsOccupiedPlant() == false) //repeat for next possible cell
                {
                    cellList.add(grid.get(1).get(0));
                }
                if(grid.get(1).get(1).getIsOccupiedPlant() == false) //repeat for next possible cell
                {
                    cellList.add(grid.get(1).get(1));
                }
                return cellList; //return possible cells
            }
            else //instance is an animal
            {
                if(grid.get(0).get(1).getIsOccupiedAnimal() == false) //is occupied by animal? If not, add to cell to list
                {
                    cellList.add(grid.get(0).get(1));
                }
                if(grid.get(1).get(0).getIsOccupiedAnimal() == false) //repeat for next possible cell
                {
                    cellList.add(grid.get(1).get(0));
                }
                if(grid.get(1).get(1).getIsOccupiedAnimal() == false) //repeat for next possible cell
                {
                    cellList.add(grid.get(1).get(1));
                }
                return cellList; //return possible cells
            }
        }
        else if(currRowPos == (rowsSize-1) && currColPos == 0) //bottom left corner
        {
            if(type.equals("fruit") || type.equals("vegetable"))
            {
                if(grid.get(rowsSize-2).get(0).getIsOccupiedPlant() == false)
                {
                    cellList.add(grid.get(rowsSize-2).get(0));
                }
                if(grid.get(rowsSize-2).get(1).getIsOccupiedPlant() == false)
                {
                    cellList.add(grid.get(rowsSize-2).get(1));
                }
                if(grid.get(rowsSize-1).get(1).getIsOccupiedPlant() == false)
                {
                    cellList.add(grid.get(rowsSize-1).get(1));
                }
                return cellList;
            }
            else
            {
                if(grid.get(rowsSize-2).get(0).getIsOccupiedAnimal() == false)
                {
                    cellList.add(grid.get(rowsSize-2).get(0));
                }
                if(grid.get(rowsSize-2).get(1).getIsOccupiedAnimal() == false)
                {
                    cellList.add(grid.get(rowsSize-2).get(1));
                }
                if(grid.get(rowsSize-1).get(1).getIsOccupiedAnimal() == false)
                {
                    cellList.add(grid.get(rowsSize-1).get(1));
                }
                return cellList;
            }
        }
        else if(currRowPos == 0 && currColPos == (columnsSize-1)) //top right corner
        {
            if(type.equals("fruit") || type.equals("vegetable"))
            {
                if(grid.get(0).get(columnsSize-2).getIsOccupiedPlant() == false)
                {
                    cellList.add(grid.get(0).get(columnsSize-2));
                }
                if(grid.get(1).get(columnsSize-2).getIsOccupiedPlant() == false)
                {
                    cellList.add(grid.get(1).get(columnsSize-2));
                }
                if(grid.get(1).get(columnsSize-1).getIsOccupiedPlant() == false)
                {
                    cellList.add(grid.get(1).get(columnsSize-1));
                }
                return cellList;
            }
            else
            {
                if(grid.get(0).get(columnsSize-2).getIsOccupiedAnimal() == false)
                {
                    cellList.add(grid.get(0).get(columnsSize-2));
                }
                if(grid.get(1).get(columnsSize-2).getIsOccupiedAnimal() == false)
                {
                    cellList.add(grid.get(1).get(columnsSize-2));
                }
                if(grid.get(1).get(columnsSize-1).getIsOccupiedAnimal() == false)
                {
                    cellList.add(grid.get(1).get(columnsSize-1));
                }
                return cellList;
            }
        }
        else if(currRowPos == (rowsSize-1) && currColPos == (columnsSize-1)) //bottom right corner
        {
            if(type.equals("fruit") || type.equals("vegetable"))
            {
                if(grid.get(rowsSize-2).get(columnsSize-1).getIsOccupiedPlant() == false)
                {
                    cellList.add(grid.get(rowsSize-2).get(columnsSize-1));
                }
                if(grid.get(rowsSize-2).get(columnsSize-2).getIsOccupiedPlant() == false)
                {
                    cellList.add(grid.get(rowsSize-2).get(columnsSize-2));
                }
                if(grid.get(rowsSize-1).get(columnsSize-2).getIsOccupiedPlant() == false)
                {
                    cellList.add(grid.get(rowsSize-1).get(columnsSize-2));
                }
                return cellList;
            }
            else
            {
                if(grid.get(rowsSize-2).get(columnsSize-1).getIsOccupiedAnimal() == false)
                {
                    cellList.add(grid.get(rowsSize-2).get(columnsSize-1));
                }
                if(grid.get(rowsSize-2).get(columnsSize-2).getIsOccupiedAnimal() == false)
                {
                    cellList.add(grid.get(rowsSize-2).get(columnsSize-2));
                }
                if(grid.get(rowsSize-1).get(columnsSize-2).getIsOccupiedAnimal() == false)
                {
                    cellList.add(grid.get(rowsSize-1).get(columnsSize-2));
                }
                return cellList;
            }
        }
        else if(currRowPos == 0) //on top row
        {
            if(type.equals("fruit") || type.equals("vegetable")) 
            {
                if(grid.get(0).get(currColPos-1).getIsOccupiedPlant() == false)
                {
                    cellList.add(grid.get(0).get(currColPos-1));
                }
                if(grid.get(0).get(currColPos+1).getIsOccupiedPlant() == false)
                {
                    cellList.add(grid.get(0).get(currColPos+1));
                }
                if(grid.get(1).get(currColPos-1).getIsOccupiedPlant() == false)
                {
                    cellList.add(grid.get(1).get(currColPos-1));
                }
                if(grid.get(1).get(currColPos).getIsOccupiedPlant() == false)
                {
                    cellList.add(grid.get(1).get(currColPos));
                }
                if(grid.get(1).get(currColPos+1).getIsOccupiedPlant() == false)
                {
                    cellList.add(grid.get(1).get(currColPos+1));
                }
                return cellList;
            }
            else
            {
                if(grid.get(0).get(currColPos-1).getIsOccupiedAnimal() == false)
                {
                    cellList.add(grid.get(0).get(currColPos-1));
                }
                if(grid.get(0).get(currColPos+1).getIsOccupiedAnimal() == false)
                {
                    cellList.add(grid.get(0).get(currColPos+1));
                }
                if(grid.get(1).get(currColPos-1).getIsOccupiedAnimal() == false)
                {
                    cellList.add(grid.get(1).get(currColPos-1));
                }
                if(grid.get(1).get(currColPos).getIsOccupiedAnimal() == false)
                {
                    cellList.add(grid.get(1).get(currColPos));
                }
                if(grid.get(1).get(currColPos+1).getIsOccupiedAnimal() == false)
                {
                    cellList.add(grid.get(1).get(currColPos+1));
                }
                return cellList;
            }
        }
        else if(currColPos == 0) //on left column
        {
            if(type.equals("fruit") || type.equals("vegetable")) 
            {
                if(grid.get(currRowPos-1).get(0).getIsOccupiedPlant() == false)
                {
                    cellList.add(grid.get(currRowPos-1).get(0));
                }
                if(grid.get(currRowPos+1).get(0).getIsOccupiedPlant() == false)
                {
                    cellList.add(grid.get(currRowPos+1).get(0));
                }
                if(grid.get(currRowPos-1).get(1).getIsOccupiedPlant() == false)
                {
                    cellList.add(grid.get(currRowPos-1).get(1));
                }
                if(grid.get(currRowPos).get(1).getIsOccupiedPlant() == false)
                {
                    cellList.add(grid.get(currRowPos).get(1));
                }
                if(grid.get(currRowPos+1).get(1).getIsOccupiedPlant() == false)
                {
                    cellList.add(grid.get(currRowPos+1).get(1));
                }
                return cellList;
            }
            else
            {
                if(grid.get(currRowPos-1).get(0).getIsOccupiedAnimal() == false)
                {
                    cellList.add(grid.get(currRowPos-1).get(0));
                }
                if(grid.get(currRowPos+1).get(0).getIsOccupiedAnimal() == false)
                {
                    cellList.add(grid.get(currRowPos+1).get(0));
                }
                if(grid.get(currRowPos-1).get(1).getIsOccupiedAnimal() == false)
                {
                    cellList.add(grid.get(currRowPos-1).get(1));
                }
                if(grid.get(currRowPos).get(1).getIsOccupiedAnimal() == false)
                {
                    cellList.add(grid.get(currRowPos).get(1));
                }
                if(grid.get(currRowPos+1).get(1).getIsOccupiedAnimal() == false)
                {
                    cellList.add(grid.get(currRowPos+1).get(1));
                }
                return cellList;
            }
        }
        else if(currRowPos == (rowsSize-1)) //on bottom row
        {
            if(type.equals("fruit") || type.equals("vegetable")) 
            {
                if(grid.get(rowsSize-1).get(currColPos-1).getIsOccupiedPlant() == false)
                {
                    cellList.add(grid.get(rowsSize-1).get(currColPos-1));
                }
                if(grid.get(rowsSize-1).get(currColPos+1).getIsOccupiedPlant() == false)
                {
                    cellList.add(grid.get(rowsSize-1).get(currColPos+1));
                }
                if(grid.get(rowsSize-2).get(currColPos-1).getIsOccupiedPlant() == false)
                {
                    cellList.add(grid.get(rowsSize-2).get(currColPos-1));
                }
                if(grid.get(rowsSize-2).get(currColPos).getIsOccupiedPlant() == false)
                {
                    cellList.add(grid.get(rowsSize-2).get(currColPos));
                }
                if(grid.get(rowsSize-2).get(currColPos+1).getIsOccupiedPlant() == false)
                {
                    cellList.add(grid.get(rowsSize-2).get(currColPos+1));
                }
                return cellList;
            }
            else
            {
                if(grid.get(rowsSize-1).get(currColPos-1).getIsOccupiedAnimal() == false)
                {
                    cellList.add(grid.get(rowsSize-1).get(currColPos-1));
                }
                if(grid.get(rowsSize-1).get(currColPos+1).getIsOccupiedAnimal() == false)
                {
                    cellList.add(grid.get(rowsSize-1).get(currColPos+1));
                }
                if(grid.get(rowsSize-2).get(currColPos-1).getIsOccupiedAnimal() == false)
                {
                    cellList.add(grid.get(rowsSize-2).get(currColPos-1));
                }
                if(grid.get(rowsSize-2).get(currColPos).getIsOccupiedAnimal() == false)
                {
                    cellList.add(grid.get(rowsSize-2).get(currColPos));
                }
                if(grid.get(rowsSize-2).get(currColPos+1).getIsOccupiedAnimal() == false)
                {
                    cellList.add(grid.get(rowsSize-2).get(currColPos+1));
                }
                return cellList;
            }
        }
        else if(currColPos == (columnsSize-1)) //on right column
        {
            if(type.equals("fruit") || type.equals("vegetable")) 
            {
                if(grid.get(currRowPos-1).get(columnsSize-1).getIsOccupiedPlant() == false)
                {
                    cellList.add(grid.get(currRowPos-1).get(columnsSize-1));
                }
                if(grid.get(currRowPos+1).get(columnsSize-1).getIsOccupiedPlant() == false)
                {
                    cellList.add(grid.get(currRowPos+1).get(columnsSize-1));
                }
                if(grid.get(currRowPos-1).get(columnsSize-2).getIsOccupiedPlant() == false)
                {
                    cellList.add(grid.get(currRowPos-1).get(columnsSize-2));
                }
                if(grid.get(currRowPos).get(columnsSize-2).getIsOccupiedPlant() == false)
                {
                    cellList.add(grid.get(currRowPos).get(columnsSize-2));
                }
                if(grid.get(currRowPos+1).get(columnsSize-2).getIsOccupiedPlant() == false)
                {
                    cellList.add(grid.get(currRowPos+1).get(columnsSize-2));
                }
                return cellList;
            }
            else
            {
                if(grid.get(currRowPos-1).get(columnsSize-1).getIsOccupiedAnimal() == false)
                {
                    cellList.add(grid.get(currRowPos-1).get(columnsSize-1));
                }
                if(grid.get(currRowPos+1).get(columnsSize-1).getIsOccupiedAnimal() == false)
                {
                    cellList.add(grid.get(currRowPos+1).get(columnsSize-1));
                }
                if(grid.get(currRowPos-1).get(columnsSize-2).getIsOccupiedAnimal() == false)
                {
                    cellList.add(grid.get(currRowPos-1).get(columnsSize-2));
                }
                if(grid.get(currRowPos).get(columnsSize-2).getIsOccupiedAnimal() == false)
                {
                    cellList.add(grid.get(currRowPos).get(columnsSize-2));
                }
                if(grid.get(currRowPos+1).get(columnsSize-2).getIsOccupiedAnimal() == false)
                {
                    cellList.add(grid.get(currRowPos+1).get(columnsSize-2));
                }
                return cellList;
            }
        }
        else //any other place
        {
            if(type.equals("fruit") || type.equals("vegetable")) 
            {
                if(grid.get(currRowPos-1).get(currColPos-1).getIsOccupiedPlant() == false)
                {
                    cellList.add(grid.get(currRowPos-1).get(currColPos-1));
                }
                if(grid.get(currRowPos-1).get(currColPos).getIsOccupiedPlant() == false)
                {
                    cellList.add(grid.get(currRowPos-1).get(currColPos));
                }
                if(grid.get(currRowPos-1).get(currColPos+1).getIsOccupiedPlant() == false)
                {
                    cellList.add(grid.get(currRowPos-1).get(currColPos+1));
                }
                if(grid.get(currRowPos).get(currColPos-1).getIsOccupiedPlant() == false)
                {
                    cellList.add(grid.get(currRowPos).get(currColPos-1));
                }
                if(grid.get(currRowPos).get(currColPos+1).getIsOccupiedPlant() == false)
                {
                    cellList.add(grid.get(currRowPos).get(currColPos+1));
                }
                if(grid.get(currRowPos+1).get(currColPos-1).getIsOccupiedPlant() == false)
                {
                    cellList.add(grid.get(currRowPos+1).get(currColPos-1));
                }
                if(grid.get(currRowPos+1).get(currColPos).getIsOccupiedPlant() == false)
                {
                    cellList.add(grid.get(currRowPos+1).get(currColPos));
                }
                if(grid.get(currRowPos+1).get(currColPos+1).getIsOccupiedPlant() == false)
                {
                    cellList.add(grid.get(currRowPos+1).get(currColPos+1));
                }
                return cellList;
            }
            else
            {
                if(grid.get(currRowPos-1).get(currColPos-1).getIsOccupiedAnimal() == false)
                {
                    cellList.add(grid.get(currRowPos-1).get(currColPos-1));
                }
                if(grid.get(currRowPos-1).get(currColPos).getIsOccupiedAnimal() == false)
                {
                    cellList.add(grid.get(currRowPos-1).get(currColPos));
                }
                if(grid.get(currRowPos-1).get(currColPos+1).getIsOccupiedAnimal() == false)
                {
                    cellList.add(grid.get(currRowPos-1).get(currColPos+1));
                }
                if(grid.get(currRowPos).get(currColPos-1).getIsOccupiedAnimal() == false)
                {
                    cellList.add(grid.get(currRowPos).get(currColPos-1));
                }
                if(grid.get(currRowPos).get(currColPos+1).getIsOccupiedAnimal() == false)
                {
                    cellList.add(grid.get(currRowPos).get(currColPos+1));
                }
                if(grid.get(currRowPos+1).get(currColPos-1).getIsOccupiedAnimal() == false)
                {
                    cellList.add(grid.get(currRowPos+1).get(currColPos-1));
                }
                if(grid.get(currRowPos+1).get(currColPos).getIsOccupiedAnimal() == false)
                {
                    cellList.add(grid.get(currRowPos+1).get(currColPos));
                }
                if(grid.get(currRowPos+1).get(currColPos+1).getIsOccupiedAnimal() == false)
                {
                    cellList.add(grid.get(currRowPos+1).get(currColPos+1));
                }
                return cellList;
            }
        }
    }
    
    /**
     * Determines the cells surrounding a given SpeciesInstance that contain food the instance can eat
     * @param x SpeciesInstance whose surrounding cells are examined
     * @return ArrayList<Cell>, an ArrayList of Cell objects representing all the surrounding cells that contain food the SpeciesInstance can eat
     */
    public ArrayList<Cell> surroundingFoodCells(SpeciesInstance x, String[] sources) //returns the cells surrounding x that have food x can eat
    {
        int currRowPos = x.getCell().getRowPos();
        int currColPos = x.getCell().getColPos();
        LinkedList<Cell> cellList = new LinkedList<Cell>();
        ArrayList<Cell> food = new ArrayList<Cell>();
        String type = x.getType(); 
        
        //if herbivore or omnivore, method checks if there is edible food in current cell, if there is it returns it
        //if herbivore and there is no food, it returns empty food arrayList
        //if omnivore or carnivore, method checks all adjacent cells for animals and adds any cells that have animals to cellList
        //finally, method iterates through cellList and picks all the cells that have edible food to go into food arrayList
        //program returns this arrayList
        
        if(type.equals("herbivore") || type.equals("omnivore"))
        {
            if(x.getCell().getIsOccupiedPlant()) //is there a plant in this cell?
            {
                if(Arrays.asList(sources).contains(x.getCell().getSpeciesInstancePlant().getName())) //get plant in cell's name and see if it is an acceptable option
                {
                    food.add(x.getCell()); //add instance's cell to food
                    return food; //return food
                }
                else //food option is not compatable
                {
                    if(type.equals("herbivore")) //if the animal is a herbivore
                    {
                        return food; //return empty list of cells, since herbivore has no options for food
                    }
                }
            }
            else //there is not a plant
            {
                if(type.equals("herbivore")) //if there isn't a plant in this cell and it is a herbivore
                {
                    return food; //return empty list of cells, since herbivore has no options for food
                }
            }
        }
        
        //omnivore and carnivore continue on
        //for carnivore and omnivore gather all cells that are occupied around current instance in linked list
        
        boolean listComplete = false;
        
        if(currRowPos == 0 && currColPos == 0) //top left corner
        {
            if(grid.get(0).get(1).getIsOccupiedAnimal() == true) //is occupied by possible food? If so, add to list
            {
                cellList.add(grid.get(0).get(1));
            }
            if(grid.get(1).get(0).getIsOccupiedAnimal() == true) //repeat for next possible cell
            {
                cellList.add(grid.get(1).get(0));
            }
            if(grid.get(1).get(1).getIsOccupiedAnimal() == true) //repeat for next possible cell
            {
                cellList.add(grid.get(1).get(1));
            }
            listComplete = true;
        }
        else if(currRowPos == (rowsSize-1) && currColPos == 0) //bottom left corner
        {
            if(grid.get(rowsSize-2).get(0).getIsOccupiedAnimal() == true)
            {
                cellList.add(grid.get(rowsSize-2).get(0));
            }
            if(grid.get(rowsSize-2).get(1).getIsOccupiedAnimal() == true)
            {
                cellList.add(grid.get(rowsSize-2).get(1));
            }
            if(grid.get(rowsSize-1).get(1).getIsOccupiedAnimal() == true)
            {
                cellList.add(grid.get(rowsSize-1).get(1));
            }
            listComplete = true;
        }
        else if(currRowPos == 0 && currColPos == (columnsSize-1)) //top right corner
        {
            if(grid.get(0).get(columnsSize-2).getIsOccupiedAnimal() == true)
            {
                cellList.add(grid.get(0).get(columnsSize-2));
            }
            if(grid.get(1).get(columnsSize-2).getIsOccupiedAnimal() == true)
            {
                cellList.add(grid.get(1).get(columnsSize-2));
            }
            if(grid.get(1).get(columnsSize-1).getIsOccupiedAnimal() == true)
            {
                cellList.add(grid.get(1).get(columnsSize-1));
            }
            listComplete = true;
        }
        else if(currRowPos == (rowsSize-1) && currColPos == (columnsSize-1)) //bottom right corner
        {
            if(grid.get(rowsSize-2).get(columnsSize-1).getIsOccupiedAnimal() == true)
            {
                cellList.add(grid.get(rowsSize-2).get(columnsSize-1));
            }
            if(grid.get(rowsSize-2).get(columnsSize-2).getIsOccupiedAnimal() == true)
            {
                cellList.add(grid.get(rowsSize-2).get(columnsSize-2));
            }
            if(grid.get(rowsSize-1).get(columnsSize-2).getIsOccupiedAnimal() == true)
            {
                cellList.add(grid.get(rowsSize-1).get(columnsSize-2));
            }
            listComplete = true;
        }
        else if(currRowPos == 0 && listComplete == false) //on top row, not corner
        {
            if(grid.get(0).get(currColPos-1).getIsOccupiedAnimal() == true)
            {
                cellList.add(grid.get(0).get(currColPos-1));
            }
            if(grid.get(0).get(currColPos+1).getIsOccupiedAnimal() == true)
            {
                cellList.add(grid.get(0).get(currColPos+1));
            }
            if(grid.get(1).get(currColPos-1).getIsOccupiedAnimal() == true)
            {
                cellList.add(grid.get(1).get(currColPos-1));
            }
            if(grid.get(1).get(currColPos).getIsOccupiedAnimal() == true)
            {
                cellList.add(grid.get(1).get(currColPos));
            }
            if(grid.get(1).get(currColPos+1).getIsOccupiedAnimal() == true)
            {
                cellList.add(grid.get(1).get(currColPos+1));
            }
            listComplete = true;
        }
        else if(currColPos == 0 && listComplete == false) //on left column, not corner
        {
            if(grid.get(currRowPos-1).get(0).getIsOccupiedAnimal() == true)
            {
                cellList.add(grid.get(currRowPos-1).get(0));
            }
            if(grid.get(currRowPos+1).get(0).getIsOccupiedAnimal() == true)
            {
                cellList.add(grid.get(currRowPos+1).get(0));
            }
            if(grid.get(currRowPos-1).get(1).getIsOccupiedAnimal() == true)
            {
                cellList.add(grid.get(currRowPos-1).get(1));
            }
            if(grid.get(currRowPos).get(1).getIsOccupiedAnimal() == true)
            {
                cellList.add(grid.get(currRowPos).get(1));
            }
            if(grid.get(currRowPos+1).get(1).getIsOccupiedAnimal() == true)
            {
                cellList.add(grid.get(currRowPos+1).get(1));
            }
            listComplete = true;
        }
        else if(currRowPos == (rowsSize-1) && listComplete == false) //on bottom row, not corner
        {
            if(grid.get(rowsSize-1).get(currColPos-1).getIsOccupiedAnimal() == true)
            {
                cellList.add(grid.get(rowsSize-1).get(currColPos-1));
            }
            if(grid.get(rowsSize-1).get(currColPos+1).getIsOccupiedAnimal() == true)
            {
                cellList.add(grid.get(rowsSize-1).get(currColPos+1));
            }
            if(grid.get(rowsSize-2).get(currColPos-1).getIsOccupiedAnimal() == true)
            {
                cellList.add(grid.get(rowsSize-2).get(currColPos-1));
            }
            if(grid.get(rowsSize-2).get(currColPos).getIsOccupiedAnimal() == true)
            {
                cellList.add(grid.get(rowsSize-2).get(currColPos));
            }
            if(grid.get(rowsSize-2).get(currColPos+1).getIsOccupiedAnimal() == true)
            {
                cellList.add(grid.get(rowsSize-2).get(currColPos+1));
            }
            listComplete = true;
        }
        else if(currColPos == (columnsSize-1) && listComplete == false) //on right column, not corner
        {
            if(grid.get(currRowPos-1).get(columnsSize-1).getIsOccupiedAnimal() == true)
            {
                cellList.add(grid.get(currRowPos-1).get(columnsSize-1));
            }
            if(grid.get(currRowPos+1).get(columnsSize-1).getIsOccupiedAnimal() == true)
            {
                cellList.add(grid.get(currRowPos+1).get(columnsSize-1));
            }
            if(grid.get(currRowPos-1).get(columnsSize-2).getIsOccupiedAnimal() == true)
            {
                cellList.add(grid.get(currRowPos-1).get(columnsSize-2));
            }
            if(grid.get(currRowPos).get(columnsSize-2).getIsOccupiedAnimal() == true)
            {
                cellList.add(grid.get(currRowPos).get(columnsSize-2));
            }
            if(grid.get(currRowPos+1).get(columnsSize-2).getIsOccupiedAnimal() == true)
            {
                cellList.add(grid.get(currRowPos+1).get(columnsSize-2));
            }
            listComplete = true;
        }
        else if(listComplete = false) //any other place
        {
            if(grid.get(currRowPos-1).get(currColPos-1).getIsOccupiedAnimal() == true)
            {
                cellList.add(grid.get(currRowPos-1).get(currColPos-1));
            }
            if(grid.get(currRowPos-1).get(currColPos).getIsOccupiedAnimal() == true)
            {
                cellList.add(grid.get(currRowPos-1).get(currColPos));
            }
            if(grid.get(currRowPos-1).get(currColPos+1).getIsOccupiedAnimal() == true)
            {
                cellList.add(grid.get(currRowPos-1).get(currColPos+1));
            }
            if(grid.get(currRowPos).get(currColPos-1).getIsOccupiedAnimal() == true)
            {
                cellList.add(grid.get(currRowPos).get(currColPos-1));
            }
            if(grid.get(currRowPos).get(currColPos+1).getIsOccupiedAnimal() == true)
            {
                cellList.add(grid.get(currRowPos).get(currColPos+1));
            }
            if(grid.get(currRowPos+1).get(currColPos-1).getIsOccupiedAnimal() == true)
            {
                cellList.add(grid.get(currRowPos+1).get(currColPos-1));
            }
            if(grid.get(currRowPos+1).get(currColPos).getIsOccupiedAnimal() == true)
            {
                cellList.add(grid.get(currRowPos+1).get(currColPos));
            }
            if(grid.get(currRowPos+1).get(currColPos+1).getIsOccupiedAnimal() == true)
            {
                cellList.add(grid.get(currRowPos+1).get(currColPos+1));
            }
            listComplete = true;
        }
        
        if(type.equals("carnivore") || type.equals("omnivore"))
        {
            for(Cell c : cellList) // for each cell in cellList, add cell to food if that cell has an animal that is in the list of the instance's acceptable sources
            {
                if(Arrays.asList(sources).contains(c.getSpeciesInstanceAnimal().getName())) //if animal in cell is in instance's sources
                {
                    food.add(c);
                }
            }
            return food;
        }
        else
        {
            return food; //return empty food arrayList
        }
    }
}
