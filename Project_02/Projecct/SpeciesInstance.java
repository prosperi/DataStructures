import java.util.*;
import java.io.*;

/**
 * Class SpeciesInstance creates a single instance of a species used to populate the LinkedLists created in the subclasses of the Abstract Class
 * Species.
 * 
 * @version (10/15/2016)
 */

public class SpeciesInstance
{
    boolean hasDied;
    LinkedList<SpeciesInstance> species;
    private int age;
    private double energyLevel;
    private Cell myCell;
    private String name;
    private String type;
    
    /**
     * Constructs a new instance of a species, with initial energy initialEnergy, of type t, with name n, to be used within a LinkedList s of other SpeciesInstances
     * @param initialEnergy double variable representing the energy with which a SpeciesInstance starts
     * @param n String variable representing the name of the species of which the constructed instance has become a part
     * @param t String variable representing the type of the species of which the constructed instance has become a part
     * @param species LinkedList of SpeciesInstances of which the constructed SpeciesInstance has become a part
     */
    public SpeciesInstance(double initialEnergy, String n, String t, LinkedList<SpeciesInstance> s)
    {
        age = 0;
        energyLevel = initialEnergy;
        name = n;
        type = t;
        species = s;
        hasDied = false;
    }
    
    /**
     * Gets the LinkedList of SpeciesInstances of which the instance is a part
     * @return SpeciesInstance's species variable (where species is a LinkedList<SpeciesInstance>)
     */
    public LinkedList<SpeciesInstance> getThisSpecies()
    {
        return species;
    }
    
    /**
     * Gets a SpeciesInstance's species name
     * @return SpeciesInstance's name variable
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * Gets a SpeciesInstance's species type
     * @return SpeciesInstance's type variable
     */
    public String getType()
    {
        return type;
    }
    
    /**
     * Sets the cell in which the SpeciesInstance resides
     * @param c Cell in which the SpeciesInstance resides
     */
    public void setCell(Cell c)
    {
        myCell = c;
    }
    
    /**
     * Gets the cell in which the SpeciesInstance resides
     * @return SpeciesInstance's Cell myCell
     */
    public Cell getCell()
    {
        return myCell;
    }
    
    /**
     * Gets the age of the SpeciesInstance
     * @return SpeciesInstance's age variable
     */
    public int getAge()
    {
        return age;
    }
    
    /**
     * Sets the age of the SpeciesInstance
     * @param a age of SpeciesInstance
     */
    public void setAge(int a)
    {
        age = a;
    }
    
    /**
     * Gets a energy level of the SpeciesInstance
     * @return SpeciesInstance's energyLevel variable
     */
    public double getEnergyLevel()
    {
        return energyLevel;
    }
    
    /**
     * Sets the energy level of the SpeciesInstance
     * @param e energy level of SpeciesInstance
     */
    public void setEnergyLevel(double e)
    {
        energyLevel = e;
    }
    
    /**
     * Sets the SpeciesInstance's status (whether the instance is dead or not)
     * @param b boolean operator which is true if an instance has died and is false otherwise
     */
    public void setHasDied(boolean b)
    {
        hasDied = b;
    }
    
    /**
     * Gets the SpeciesInstance's status (whether the instance is dead or not)
     * @return SpeciesInstance's boolean hasDied variable (hasDied is true if instance is dead, and is false otherwise)
     */
    public boolean getHasDied()
    {
        return hasDied;
    }
}
