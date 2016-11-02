import java.util.*;
import java.io.*;

/**
 * Abstract class Species defines all the variables and methods its subclasses initialize and inherit. It also
 * provides a variety of get/set methods for accessing variables common across all species.
 *
 * @version (10/15/2016)
 */

public abstract class Species
{
    protected LinkedList<SpeciesInstance> aSpecies;
    protected String[] energySources;
    protected double medianEnergyLevel; //stat
    protected int numberBirths; //stat
    protected int numberDeaths; //stat
    protected int partialBirths; //stat
    protected int partialDeaths; //stat
    protected int preIterBirths; //stat
    protected int preIterDeaths;
    protected int preIterPopulation; //stat
    protected String plantSymbol;
    protected String animalSymbol;
    protected String name;
    protected String type;
    protected double medianDeath;
    protected double stdDevDeath;
    protected int minBirthEnergy;
    protected int maxEnergy;
    protected int livingEnergy;

    /**
     * Gets the LinkedList of SpeciesInstances of a given species
     * @return Species' LinkedList<SpeciesInstance> aSpecies
     */
    public LinkedList<SpeciesInstance> getSpecies()
    {
        return aSpecies;
    }

    /**
     * Sets a portion of the number of births of a species
     * @param b births at a certain point in the simulation
     */
    public void setPartialBirths(int b)
    {
        partialBirths = b;
    }

    /**
     * Sets a portion of the number of deaths of a species
     * @param d deaths at a certain point in the simulation
     */
    public void setPartialDeaths(int d)
    {
        partialDeaths = d;
    }

    /**
     * Gets a portion of the number of births of a species
     * @return Species' partialBirths variable
     */
    public int getPartialBirths()
    {
        return partialBirths;
    }

    /**
     * Gets a portion of the number of deaths of a species
     * @return Species' partialDeaths variable
     */
    public int getPartialDeaths()
    {
        return partialDeaths;
    }

    /**
     * Sets the number of deaths of a species
     * @param d the number of deaths of a species
     */
    public void setNumberDeaths(int d)
    {
        numberDeaths = d;
    }

    /**
     * Gets a plant species' symbol
     * @return Species' plant symbol (should only be called on fruit or vegetable species)
     */
    public String getPlantSymbol()
    {
        return plantSymbol;
    }

    /**
     * Gets an animal species' symbol
     * @return Species' animal symbol (should only be called on a herbivore, carnivore, or omnivore species)
     */
    public String getAnimalSymbol()
    {
        return animalSymbol;
    }

    /**
     * Gets the type of a given species
     * @return Species' type variable
     */
    public String getType()
    {
        return type;
    }

    /**
     * Gets the name of a give species
     * @return Species' name variable
     */
    public String getName()
    {
        return name;
    }

    /**
     * Gets the total number of births of a species
     * @return Species' variable numberBirths
     */
    public int getNumberBirths()
    {
        return numberBirths;
    }

    /**
     * Gets the total number of deaths of a species
     * @return Species' variable numberDeaths
     */
    public int getNumberDeaths()
    {
        return numberDeaths;
    }

    /**
     * Gets the population of a species before the instances of the species are iterated through (temp variable)
     * @return Species' variable preIterPopulation
     */
    public int getPreIterPopulation()
    {
        return preIterPopulation;
    }

    /**
     * Sets a population of a species before the instances of the species are iterated through (temp variable)
     * @param p population of species before instances have been iterated through
     */
    public void setPreIterPopulation(int p)
    {
        preIterPopulation = p;
    }

    /**
     * Gets the number of births of a species before the instances of the species are iterated through (temp variable)
     * @return Species' variable preIterBirths
     */
    public int getPreIterBirths()
    {
        return preIterBirths;
    }

    /**
     * Sets the number of births of a species before the instances of the species are iterated through (temp variable)
     * @param b births of species at some point in the iteration
     */
    public void setPreIterBirths(int b)
    {
        preIterBirths = b;
    }

    /**
     * Gets the number of deaths of a species before the instances of the species are iterated through (temp variable)
     * @return Species' variable preIterDeaths
     */
    public int getPreIterDeaths()
    {
        return preIterDeaths;
    }

    /**
     * Sets the number of deaths of a species before the instances of the species are iterated through (temp variable)
     * @param d deaths of species at some point in the iteration
     */
    public void setPreIterDeaths(int d)
    {
        preIterDeaths = d;
    }

    /**
     * Iterates through each SpeciesInstance in a species' LinkedList, calling the methods required to perform all
     * necessary actions on each instance
     * @param i index of SpeciesInstance in a species' LinkedList<SpeciesInstance>
     * @param gameGrid Grid object representing the constructed wildlife park
     * @param light amount of energy light provides to fruits and vegetables
     */
    public void iteration(int i, Grid gameGrid, double light)
    {

    }

}
