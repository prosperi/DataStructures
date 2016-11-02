import java.util.*;
import java.io.*;

/**
 * Class Fruit creates a new species of type fruit. Class Fruit initializes the variables defined in Abstract Class Species, creates a LinkedList of
 * SpeciesInstance objects, and implements methods that allow each instance stored in the LinkedList to die, eat, and give birth.
 * 
 * @version (10/15/2016)
 */

public class Fruit extends Species
{
    protected LinkedList<SpeciesInstance> fruitSpecies;
    protected double light;
    
    /**
     * Constructs a new Fruit species, which contains a LinkedList of SpeciesInstances of type fruit
     * @param typeName String type of species
     * @param speciesName String name of species
     * @param sym String symbol of species
     * @param sources String[] containing the names of the food this fruit can consume
     * @param startingPopulation population size used to determine the number of SpeciesInstances that should be created to fill the fruit's LinkedList
     * @param initialEnergy energy with which each instance of this fruit should start
     * @param medDeath median of death age for this species
     * @param stdDev standard deviation of death age for this species
     * @param minEnergy minimum amount of energy instances of this fruit need to have to give birth
     * @param energy maximum amount of energy instances of this fruit can obtain
     * @param livEnergy amount of energy instances of this fruit require each turn in order to sustain themselves
     */
    public Fruit(String typeName, String speciesName, String sym, String[] sources, int startingPopulation, double initialEnergy, double medDeath, double stdDeath, int minEnergy, int energy, int livEnergy)
    {
        energySources = sources;
        numberBirths = 0;
        numberDeaths = 0;
        plantSymbol = sym;
        name = speciesName;
        type = typeName;
        stdDevDeath = stdDeath;
        medianDeath = medDeath;
        minBirthEnergy = minEnergy;
        maxEnergy = energy;
        livingEnergy = livEnergy;
        medianEnergyLevel = initialEnergy;
        fruitSpecies = new LinkedList<SpeciesInstance>();
        partialBirths = 0;
        partialDeaths = 0;
        preIterPopulation = 0;
        preIterBirths = 0;
        preIterDeaths = 0;

        for(int i = 0; i < startingPopulation; i++) //fill starting population
        {
            fruitSpecies.add(new SpeciesInstance(initialEnergy, name, type, fruitSpecies));
        }
    }
    
    /**
     * Gets the LinkedList of SpeciesInstance of this fruit species
     * @return Fruit variable fruitSpecies
     */
    public LinkedList<SpeciesInstance> getSpecies()
    {
        return fruitSpecies;
    }
    
    /**
     * Iterates through each SpeciesInstance in a species' LinkedList, updating age and energy level and calling the methods required to perform all
     * necessary actions on each instance
     * @param i index of SpeciesInstance in a species' LinkedList<SpeciesInstance>
     * @param gameGrid Grid object representing the constructed wildlife park
     * @param light amount of energy light provides to fruits and vegetables
     */
    public void iteration(int i, Grid gameGrid, double light) //where i is the particular instance of fruit
    {
        SpeciesInstance x = fruitSpecies.get(i);
        if(x.getHasDied() == true)
        {
            return;
        }
        x.setEnergyLevel(x.getEnergyLevel()-livingEnergy); //decrease energy level by the energy it takes to live
        x.setAge(x.getAge()+1); //plant ages by one year
        if(fruitDeath(x)) //check if instance needs to die, if not, move on
        {
            return;
        }
        else if(fruitBirth(gameGrid, x)) //check if instance can give birth, if not, move on
        {
            return;
        }
        else
        {
            fruitEat(x, light); //else, eat
        }
        //Since plant, no movement
    }
    
    /**
     * Checks if a SpeciesInstance is ready to die, and sets/resets the necessary variables if it is
     * @param x SpeciesInstance variable currently being iterated through
     * @return boolean variable, true if element has died, false otherwise
     */
    public boolean fruitDeath(SpeciesInstance x) //returns true if element has died
    {
        RandomNumberGenerator rnd = new RandomNumberGenerator();
        int currentDeathAge = rnd.returnGaussian(medianDeath, stdDevDeath);
        if((x.getEnergyLevel() <= 0) || (x.getAge() >= currentDeathAge)) //if the plant's energy level is depleted or it gets too old, remove x
        {
            x.getCell().setIsOccupiedPlant(false); //cell is no longer occupied by plant
            if(x.getCell().getIsOccupiedAnimal() == false) //if there is also not an animal in the cell
            {
                x.getCell().setIsOccupied(false); //then the cell is not occupied at all
            }
            x.getCell().setSpeciesInstancePlant(null); //cell no longer has an instance in it
            x.getCell().setPlantSymbol(" "); //reset symbol
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
    public boolean fruitBirth(Grid gameGrid, SpeciesInstance x)
    {
        if(x.getEnergyLevel() >= minBirthEnergy) //enough energy to give birth?
        {
            ArrayList<Cell> posCells = gameGrid.surroundingEmptyCells(x);
            if(posCells.size() == 0) //is there room next to the plant?
            {
                return false; //no birth
            }
            else //give birth
            {
                SpeciesInstance y = new SpeciesInstance(x.getEnergyLevel()/2,name, type, fruitSpecies); //make new instance w/ half of parent's energy
                fruitSpecies.add(y); //add to list of fruit species 
                x.setEnergyLevel(x.getEnergyLevel()/2); //x loses half of its energy
                RandomNumberGenerator rnd = new RandomNumberGenerator(); 
                Cell cellToUse = posCells.get(rnd.returnRandom(posCells.size()-1)); //pick random cell from array
                y.setCell(cellToUse); //set y's cell
                cellToUse.setPlantSymbol(plantSymbol); //set cell's symbol
                cellToUse.setIsOccupied(true); //cell is now occupied
                cellToUse.setIsOccupiedPlant(true); //by plant
                cellToUse.setSpeciesInstancePlant(y); //cell now has instance in it
                numberBirths++;
                return true; //birth has happened
            }
        }
        else
        {
            return false; //no birth
        }
    }
    
    /**
     * Updates a SpeciesInstance's energy level
     * @param x SpeciesInstance variable currently being iterated through
     * @param light amount of energy added to a fruit instance's energy level
     */
    public void fruitEat(SpeciesInstance x, double light) //add light to energy level
    {
        x.setEnergyLevel(x.getEnergyLevel()+light);
        if(x.getEnergyLevel() > maxEnergy)
        {
            x.setEnergyLevel(maxEnergy);
        }
    }

}
