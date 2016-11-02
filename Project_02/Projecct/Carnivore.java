import java.util.*;
import java.io.*;

/**
 * Class Carnivore creates a new species of type carnivore. Class Carnivore initializes the variables defined in Abstract Class Species, creates a LinkedList of
 * SpeciesInstance objects, and implements methods that allow each instance stored in the LinkedList to die, eat, and give birth.
 * 
 * @version (10/15/2016)
 */

public class Carnivore extends Species
{
    protected LinkedList<SpeciesInstance> carnivoreSpecies;

    /**
     * Constructs a new Carnivore species, which contains a LinkedList of SpeciesInstances of type carnivore
     * @param typeName String type of species
     * @param speciesName String name of species
     * @param sym String symbol of species
     * @param sources String[] containing the names of the food this carnivore can consume
     * @param startingPopulation population size used to determine the number of SpeciesInstances that should be created to fill the carnivore's LinkedList
     * @param initialEnergy energy with which each instance of this carnivore should start
     * @param medDeath median of death age for this species
     * @param stdDev standard deviation of death age for this species
     * @param minEnergy minimum amount of energy instances of this carnivore need to have to give birth
     * @param energy maximum amount of energy instances of this carnivore can obtain
     * @param livEnergy amount of energy instances of this carnivore require each turn in order to sustain themselves
     */
    public Carnivore(String typeName, String speciesName, String sym, String[] sources, int startingPopulation, double initialEnergy, double medDeath, double stdDeath, int minEnergy, int energy, int livEnergy)
    {
        energySources = sources;
        numberBirths = 0;
        numberDeaths = 0;
        animalSymbol = sym;
        name = speciesName;
        type = typeName;
        stdDevDeath = stdDeath;
        medianDeath = medDeath;
        minBirthEnergy = minEnergy;
        maxEnergy = energy;
        livingEnergy = livEnergy;
        medianEnergyLevel = initialEnergy;
        carnivoreSpecies = new LinkedList<SpeciesInstance>();
        partialBirths = 0;
        partialDeaths = 0;
        preIterPopulation = 0;
        preIterBirths = 0;
        preIterDeaths = 0;

        for(int i = 0; i < startingPopulation; i++) //fill starting population
        {
            carnivoreSpecies.add(new SpeciesInstance(initialEnergy, name, type, carnivoreSpecies));
        }
    }
    
    /**
     * Gets the LinkedList of SpeciesInstance of this carnivore species
     * @return carnivore variable carnivoreSpecies
     */
    public LinkedList<SpeciesInstance> getSpecies()
    {
        return carnivoreSpecies;
    }
    
    /**
     * Iterates through each SpeciesInstance in a species' LinkedList, updating age and energy level and calling the methods required to perform all
     * necessary actions on each instance
     * @param i index of SpeciesInstance in a species' LinkedList<SpeciesInstance>
     * @param gameGrid Grid object representing the constructed wildlife park
     * @param light amount of energy light provides to fruits and vegetables
     */
    public void iteration(int i, Grid gameGrid, double light) //where i is the particular instance of carnivore
    {
        SpeciesInstance x = carnivoreSpecies.get(i);
        if(x.getHasDied() == true)
        {
            return;
        }
        x.setEnergyLevel(x.getEnergyLevel()-livingEnergy); //decrease energy level by the energy it takes to live
        x.setAge(x.getAge()+1); //carnivore ages by one year
        if(carnivoreDeath(x)) //check if instance needs to die, if not, move on
        {
            return;
        }
        else if(carnivoreBirth(gameGrid, x)) //check if instance can give birth, if not, move on
        {
            return;
        }
        else if(carnivoreEat(gameGrid, x)) //check if instance can eat, if not, move on
        {
            return;
        }
        else
        {
            carnivoreMove(gameGrid, x);
        }
    }
    
    /**
     * Checks if a SpeciesInstance is ready to die, and sets/resets the necessary variables if it is
     * @param x SpeciesInstance variable currently being iterated through
     * @return boolean variable, true if element has died, false otherwise
     */
    public boolean carnivoreDeath(SpeciesInstance x) //returns true if element has died
    {
        RandomNumberGenerator rnd = new RandomNumberGenerator();
        int currentDeathAge = rnd.returnGaussian(medianDeath, stdDevDeath);
        if((x.getEnergyLevel() <= 0) || (x.getAge() >= currentDeathAge)) //if the omnivore's energy level is depleted or it gets too old, remove x
        {
            x.getCell().setIsOccupiedAnimal(false); //cell is no longer occupied by animal
            if(x.getCell().getIsOccupiedPlant() == false) //if there is also not a plant in the cell
            {
                x.getCell().setIsOccupied(false); //then the cell is not occupied at all
            }
            x.getCell().setSpeciesInstanceAnimal(null); //cell no longer has an animal instance in it
            x.getCell().setAnimalSymbol(" "); //reset symbol
            x.setHasDied(true); //x has died and should be removed
            return true;
        }
        else
        {
            return false;
        }
    }
    
    /**
     * Checks if a SpeciesInstance can give birth, and sets/resets/creates the necessary variables if it can
     * @param gameGrid Grid object representing the constructed wildlife park
     * @param x SpeciesInstance variable currently being iterated through
     * @return boolean variable, true if element has given birth, false otherwise
     */
    public boolean carnivoreBirth(Grid gameGrid, SpeciesInstance x)
    {
        if(x.getEnergyLevel() >= minBirthEnergy) //enough energy to give birth?
        {
            ArrayList<Cell> posCells = gameGrid.surroundingEmptyCells(x);
            if(posCells.size() == 0) //is there room next to the animal?
            {
                return false; //no birth because not enough room
            }
            else //give birth
            {
                SpeciesInstance y = new SpeciesInstance(x.getEnergyLevel()/2, name, type, carnivoreSpecies); //make new instance w/ half of parent's energy
                carnivoreSpecies.add(y); //add to list of carnivore species 
                x.setEnergyLevel(x.getEnergyLevel()/2); //x loses half of its energy
                RandomNumberGenerator rnd = new RandomNumberGenerator(); 
                Cell cellToUse = posCells.get(rnd.returnRandom(posCells.size()-1)); //pick random cell from array of available cells
                y.setCell(cellToUse); //set y's cell
                cellToUse.setAnimalSymbol(animalSymbol); //set new cell's symbol
                cellToUse.setIsOccupied(true); //cell is now occupied
                cellToUse.setIsOccupiedAnimal(true); //by animal
                cellToUse.setSpeciesInstanceAnimal(y); //cell now has instance y in it
                numberBirths++;
                return true; //birth has happened
            }
        }
        else
        {
            return false; //no birth because not enough energy
        }
    }
    
    /**
     * Checks if there is food in the cells adjacent to SpeciesInstance, and, if so, the SpeciesInstance moves to that cell and "eats" the food, 
     * and necessary variables are set/reset
     * @gameGrid Grid object representing the constructed wildlife park
     * @param x SpeciesInstance variable currently being iterated through
     * @return boolean variable, true if element has eaten, false otherwise
     */
    public boolean carnivoreEat(Grid gameGrid, SpeciesInstance x) //eat food if possible
    {
        ArrayList<Cell> posFoodCells = gameGrid.surroundingFoodCells(x, energySources); //get list of cells that have food
        if(posFoodCells.size() == 0) //is there any food?
        {
            return false; // no eating because there is no food to eat
        }
        else //eat
        {
            RandomNumberGenerator rnd = new RandomNumberGenerator(); 
            Cell foodCell = posFoodCells.get(rnd.returnRandom(posFoodCells.size()-1)); //pick random cell from array of available cells
            SpeciesInstance food = foodCell.getSpeciesInstanceAnimal(); //since carnivore, get cell's animal instance to eat
            x.setEnergyLevel(x.getEnergyLevel() + food.getEnergyLevel()); //instance gets food's energy
            x.getCell().setIsOccupiedAnimal(false); //current cell is no longer occupied by animal
            if(x.getCell().getIsOccupiedPlant() == false) //if there is also not a plant in the cell
            {
                x.getCell().setIsOccupied(false); //then the cell is not occupied at all
            }
            x.getCell().setSpeciesInstanceAnimal(null); //cell no longer has an instance in it
            x.getCell().setAnimalSymbol(" "); //reset symbol
            x.setCell(foodCell); //x is now in new cell
            foodCell.setAnimalSymbol(animalSymbol); //foodCell now contains new animal
            foodCell.setSpeciesInstanceAnimal(x); //foodCell now has instance in it
            //do not have to reset boolean variables of foodCell because previous occupant was also an animal
            food.setHasDied(true); //show that cell has died and needs to be removed
        }
        if(x.getEnergyLevel() > maxEnergy) //if energy is over max, then keep energy at max
        {
            x.setEnergyLevel(maxEnergy);
        }
        return true;
    }

    /**
     * Checks if there is space for the SpeciesInstance to move, and, if so, the SpeciesInstance "moves" and the necessary variables are set/reset
     * @gameGrid Grid object representing the constructed wildlife park
     * @param x SpeciesInstance variable currently being iterated through
     */
    public void carnivoreMove(Grid gameGrid, SpeciesInstance x)
    {
        ArrayList<Cell> posCells = gameGrid.surroundingEmptyCells(x); //array of cells that x can move to
        if(posCells.size() == 0) //is there room to move?
        {
            return; //no room to move
        }
        else //move
        {
            RandomNumberGenerator rnd = new RandomNumberGenerator(); 
            Cell cellToUse = posCells.get(rnd.returnRandom(posCells.size()-1)); //pick random cell from array of available cells
            x.getCell().setIsOccupiedAnimal(false); //current cell is no longer occupied by animal
            if(x.getCell().getIsOccupiedPlant() == false) //if there is also not a plant in the cell
            {
                x.getCell().setIsOccupied(false); //then the cell is not occupied at all
            }
            x.getCell().setSpeciesInstanceAnimal(null); //cell no longer has an instance in it
            x.getCell().setAnimalSymbol(" "); //reset symbol
            x.setCell(cellToUse); //x is now in new cell
            cellToUse.setAnimalSymbol(animalSymbol); //set new cell's symbol
            cellToUse.setIsOccupied(true); //cell is now occupied
            cellToUse.setIsOccupiedAnimal(true); //by animal
            cellToUse.setSpeciesInstanceAnimal(x); //cell now has instance x in it
        }
    }
}
