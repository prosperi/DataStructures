
import java.util.*;
import java.io.*;

/**
 * Class Park creates a new park, creates and distributes information read from the 
 * config file, interacts with the user, keeps track of the stats for all 
 * the species in the park, and actually runs the simulation (it contains the main method).
 * 
 * @version (10/15/2016)
 */

public class Park
{
    private Grid gameGrid;
    private LinkedList<ArrayList<Species>> allSpecies;
    private ArrayList<Species> allFruitSpecies;
    private ArrayList<Species> allHerbivoreSpecies;
    private ArrayList<Species> allVegetableSpecies;
    private ArrayList<Species> allCarnivoreSpecies;
    private ArrayList<Species> allOmnivoreSpecies;
    private int totalSteps;
    private int stepsLeft;
    private double light;
    private int stepsTaken;
    private boolean noChangeInSimulation;
    
    /**
     * Constructs a Park object
     */
    public Park()
    {
        
    }
    
    public boolean getChangeInSimulation() //used for unit testing only
    {
        return noChangeInSimulation;
    }
    
    /**
     * Reads in information from a given input file and distributes this information to 
     * create the park's necessary species
     * @param fileIn file inputted by the user at the start of the program
     */
    public void readConfig(Scanner fileIn)
    {
        allFruitSpecies = new ArrayList<Species>();
        allHerbivoreSpecies = new ArrayList<Species>();
        allVegetableSpecies = new ArrayList<Species>();
        allCarnivoreSpecies = new ArrayList<Species>();
        allOmnivoreSpecies = new ArrayList<Species>();
        while(fileIn.hasNext()) //while there is still text in the file
        {
            String keyword = fileIn.next(); //first word in line
            if(keyword.equals("size"))
            {
                int rows = fileIn.nextInt(); //grid: rows
                int columns = fileIn.nextInt(); //grid: width
                gameGrid = new Grid(rows,columns);
            }
            else if(keyword.equals("light"))
            {
                double energy = fileIn.nextDouble(); //plant energy
                light = energy;
            }
            else if(keyword.equals("species"))
            {
                String speciesName = fileIn.next(); //specific name
                String speciesType = fileIn.next(); //omnivore, carnivore, etc
                String symbol = fileIn.next(); //species symbol
                String[] energySources = fileIn.next().split(",");
                String[] popInfo = fileIn.next().split(",");
                double medianPopulation = Double.parseDouble(popInfo[0]); //median starting population
                double stdDevPopulation = Double.parseDouble(popInfo[1]); //standard deviation of population
                
                RandomNumberGenerator newRandom = new RandomNumberGenerator();
                int startingPopulation = newRandom.returnGaussian(medianPopulation, stdDevPopulation);
                
                double initialEnergy = Double.parseDouble(popInfo[2]); //initial energy
                String[] deathInfo = fileIn.next().split(",");
                double medianDeath = Double.parseDouble(deathInfo[0]); //median death age
                double stdDevDeath = Double.parseDouble(deathInfo[1]); // standard deviation of death
                int minBirthEnergy = fileIn.nextInt(); //minimum energy required for birth
                int maxEnergy = fileIn.nextInt(); //maximum possible energy
                int livingEnergy = fileIn.nextInt(); //energy used for living
                
                if(speciesType.equals("fruit")) //if it is a fruit, add it to the ArrayList of all fruits
                {
                    allFruitSpecies.add(new Fruit(speciesType, speciesName, symbol, energySources, startingPopulation, initialEnergy, medianDeath, stdDevDeath, minBirthEnergy, maxEnergy, livingEnergy));
                }
                
                if(speciesType.equals("vegetable"))
                {
                    allVegetableSpecies.add(new Vegetable(speciesType, speciesName, symbol, energySources, startingPopulation, initialEnergy, medianDeath, stdDevDeath, minBirthEnergy, maxEnergy, livingEnergy));
                }
                
                if(speciesType.equals("herbivore"))
                {
                    allHerbivoreSpecies.add(new Herbivore(speciesType, speciesName, symbol, energySources, startingPopulation, initialEnergy, medianDeath, stdDevDeath, minBirthEnergy, maxEnergy, livingEnergy));
                }
                
                if(speciesType.equals("carnivore"))
                {
                    allCarnivoreSpecies.add(new Carnivore(speciesType, speciesName, symbol, energySources, startingPopulation, initialEnergy, medianDeath, stdDevDeath, minBirthEnergy, maxEnergy, livingEnergy));
                }
                
                if(speciesType.equals("omnivore"))
                {
                    allOmnivoreSpecies.add(new Omnivore(speciesType, speciesName, symbol, energySources, startingPopulation, initialEnergy, medianDeath, stdDevDeath, minBirthEnergy, maxEnergy, livingEnergy));
                }
            }
        }
    }
    
    /**
     * Adds all of the different types of species to a single LinkedList of all species, then fills the park's grid with all of these species
     */
    public void startingBoard() //initialize allSpecies and fill the board
    {
        allSpecies = new LinkedList<ArrayList<Species>>(); //initialize allSpecies
        allSpecies.add(allFruitSpecies); //add all of the fruit species to allSpecies
        allSpecies.add(allVegetableSpecies); //add all of the vegetables to allSpecies
        allSpecies.add(allHerbivoreSpecies); //add all of the herbivores to allSpecies
        allSpecies.add(allCarnivoreSpecies); //add all of the carnivores to allSpecies
        allSpecies.add(allOmnivoreSpecies); //add all of the omnivores to allSpecies
        gameGrid.fillGrid(allSpecies); //grid is filled with all species
    }
    
    /**
     * Runs the actual simulation. Reads in input from the user and calls different methods depending on the user's response
     * @param steps number of times the program should iterate through all the instances of all the species
     */
    public void simulation(int steps) //runs simulation
    {
        totalSteps = steps;
        stepsLeft = steps;
        stepsTaken = 0;
        noChangeInSimulation = false;
        Scanner input = new Scanner(System.in);
        while(isSimulationEnd(stepsLeft, noChangeInSimulation, allSpecies) == false) //check if simulation should change
        {
            System.out.println("Please select a command. Enter p to print a map of the world. Enter c to move forward one turn and print a summary of the changes.");
            System.out.println("Enter i to continue until the simulation ends. Enter r to print the status of each species.");
            String command = input.nextLine();
            if(command.equals("p")) //print map
            {
                gameGrid.printGrid();
            }
            else if(command.equals("c")) //move forward one iteration and print changes in births/deaths
            {
                oneIteration();
            }
            else if(command.equals("i")) //start from current iteration and continue until end of simulation without prompting
            {
                for(int i = stepsLeft; i > 0; i--)
                {
                    oneIteration();
                    if(isSimulationEnd(stepsLeft, noChangeInSimulation, allSpecies) == true)
                    {
                        break;
                    }
                }
            }
            else if(command.equals("r"))
            {
                printStatus(allSpecies);
            }
            else //user did not put in correct input and should be prompted again
            {
                //do nothing, will prompt again
            }
        }
        printSummaryDataEnd();
        gameGrid.printGrid();
    }
    
    /**
     * Moves all instances of all species forward one "turn" and then prints the changes in births and deaths for that turn
     */
    public void oneIteration() //moves all instances of all species forward one turn and prints the change in births and deaths
    {   
        for(int i = 0; i < allSpecies.size(); i++) //for each arraylist of linked list of species
        {
            for(int j = 0; j < allSpecies.get(i).size(); j++) //for each arraylist in arraylist array
            {
                allSpecies.get(i).get(j).setPreIterBirths(allSpecies.get(i).get(j).getNumberBirths());
                allSpecies.get(i).get(j).setPreIterPopulation(allSpecies.get(i).get(j).getSpecies().size());
                for(int k = 0; k < allSpecies.get(i).get(j).getSpecies().size(); k++) //for each instance
                {
                    allSpecies.get(i).get(j).iteration(k, gameGrid, light);
                }
            }
        }
        
        for(int n = 0; n < allSpecies.size(); n++) //remove all instances that have died
        {
            for(int s = 0; s < allSpecies.get(n).size(); s++)
            {
                allSpecies.get(n).get(s).setPreIterDeaths(allSpecies.get(n).get(s).getNumberDeaths());
                ArrayList<SpeciesInstance> toRemove = new ArrayList<SpeciesInstance>(); //make array of things to remove
                for(int g = 0; g < allSpecies.get(n).get(s).getSpecies().size(); g++)
                {
                    if(allSpecies.get(n).get(s).getSpecies().get(g).getHasDied() == true)
                    {
                        toRemove.add(allSpecies.get(n).get(s).getSpecies().get(g)); //if the animal has died, remove it
                        allSpecies.get(n).get(s).setNumberDeaths(allSpecies.get(n).get(s).getNumberDeaths() + 1); //increment death counter
                    }
                }

                for(SpeciesInstance i : toRemove) //remove each item in toRemove from the current species
                {
                    allSpecies.get(n).get(s).getSpecies().remove(i);
                }
            }
        }
        
        for(int k = 0; k < allSpecies.size(); k++) //calculate and then print stats
        {
            for(int m = 0; m < allSpecies.get(k).size(); m++)
            {
                System.out.println("Species: " + allSpecies.get(k).get(m).getName());
                System.out.println("Number of Births in Past Turn: " + (allSpecies.get(k).get(m).getNumberBirths() - allSpecies.get(k).get(m).getPreIterBirths()));
                System.out.println("Number of Deaths in Past Turn: " + (allSpecies.get(k).get(m).getNumberDeaths() - allSpecies.get(k).get(m).getPreIterDeaths()));
                System.out.println("Population: " + allSpecies.get(k).get(m).getSpecies().size());
            }
        }
        
        stepsTaken++;
        
        if(stepsTaken % 50 == 0)
        {
            printSummaryDataPartial(allSpecies);
        }
        
        stepsLeft--; //decrease counter
    }
    
    /**
     * Checks to see if the simulation has triggered one of the boundary conditions
     * @param stepsLeft, used for unit testing
     * @param noChangeInSimulation, used for unit testing
     * @param allSpecies, used for unit testing
     * @return boolean variable, true if simulation should end, false otherwise
     */
    public boolean isSimulationEnd(int stepsLeft, boolean noChangeInSimulation, LinkedList<ArrayList<Species>> allSpecies) //returns true if the simulation should end //parameters only used for unit testing
    {
        if(stepsLeft==0) //if there are no steps left in the simulation
        {
            return true;
        }
        boolean shouldEnd = true;
        for(int i = 0; i < allSpecies.size(); i++) //for each arraylist of linked list of species
        {
            for(int j = 0; j < allSpecies.get(i).size(); j++) //for each arraylist in arraylist array
            {
                if(!allSpecies.get(i).get(j).getSpecies().isEmpty()) //if there is still something in array
                {
                    shouldEnd = false;
                }
            }
        }
        if(shouldEnd == true) //did shouldEnd's value change?
        {
            return true;
        }
        else if(noChangeInSimulation)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    /**
     * Prints a summary of each species after each 50 steps of the simulation (includes current population, and number of births and deaths in the past 50 turns)
     * @param allSpecies, used for unit testing
     */
    public void printSummaryDataPartial(LinkedList<ArrayList<Species>> allSpecies) //parameter only used for unit testing
    {
        noChangeInSimulation = true;
        for(int i = 0; i < allSpecies.size(); i++) //for each arraylist of linked list of species
        {
            for(int j = 0; j < allSpecies.get(i).size(); j++) //for each arraylist in arraylist array
            {
                System.out.println("Species: " + allSpecies.get(i).get(j).getName());
                System.out.println("Number of births in the last 50 steps: " + (allSpecies.get(i).get(j).getNumberBirths() - allSpecies.get(i).get(j).getPartialBirths()));
                System.out.println("Number of deaths in the last 50 steps: " + (allSpecies.get(i).get(j).getNumberDeaths() - allSpecies.get(i).get(j).getPartialDeaths()));
                System.out.println("Current Population: " + allSpecies.get(i).get(j).getSpecies().size());
        
                if(allSpecies.get(i).get(j).getNumberBirths() != allSpecies.get(i).get(j).getPartialBirths() || allSpecies.get(i).get(j).getNumberDeaths() != allSpecies.get(i).get(j).getPartialDeaths()) //if there is a change in simulation
                {
                    noChangeInSimulation = false;
                }
                
                allSpecies.get(i).get(j).setPartialBirths(allSpecies.get(i).get(j).getNumberBirths());
                allSpecies.get(i).get(j).setPartialDeaths(allSpecies.get(i).get(j).getNumberDeaths());
            }
        }
        //if simulation hasn't changed, noChangeInSimulation will be true
    }
    
    /**
     * Prints a final summary of each species when the simulation ends (inclues current population, and total number of births and deaths throughout the entire simulation)
     */
    public void printSummaryDataEnd() //number of births, deaths, current population for each species
    {
        for(int i = 0; i < allSpecies.size(); i++) //for each arraylist of linked list of species
        {
            for(int j = 0; j < allSpecies.get(i).size(); j++) //for each arraylist in arraylist array
            {
                System.out.println("Species: " + allSpecies.get(i).get(j).getName());
                System.out.println("Number of Births: " + allSpecies.get(i).get(j).getNumberBirths());
                System.out.println("Number of Deaths: " + allSpecies.get(i).get(j).getNumberDeaths());
                System.out.println("Current Population: " + allSpecies.get(i).get(j).getSpecies().size());
            }
        }
    }
    
    /**
     * Prints the current status of each species when prompted by the user (includes total population, median energy level, and median age)
     * @param allSpecies, used for unit testing method
     */
    public void printStatus(LinkedList<ArrayList<Species>> allSpecies) //for each species, print total population, median energy level, median age //parameter only used for unit testing
    {
        for(int i = 0; i < allSpecies.size(); i++) //for each arraylist of linked list of species
        {
            for(int j = 0; j < allSpecies.get(i).size(); j++) //for each arraylist in arraylist array
            {
                System.out.println("Species: " + allSpecies.get(i).get(j).getName());
                System.out.println("Total Population: " + allSpecies.get(i).get(j).getSpecies().size());
                ArrayList<Double> energyLevels = new ArrayList<Double>();
                ArrayList<Integer> ages = new ArrayList<Integer>();
                
                for(int m = 0; m < allSpecies.get(i).get(j).getSpecies().size(); m++) //get all ages and energy levels of instances
                {
                    energyLevels.add(allSpecies.get(i).get(j).getSpecies().get(m).getEnergyLevel());
                    ages.add(allSpecies.get(i).get(j).getSpecies().get(m).getAge());
                }
                
                if(allSpecies.get(i).get(j).getSpecies().isEmpty())
                {
                    System.out.println("Median Energy Level: 0");
                    System.out.println("Median Age: 0");
                }
                ///  this part was used in the Project-01, but it can be resized by changing + with minus on line 366 - get((energyLevels.size()/2)-1))/2)
                /*else if(allSpecies.get(i).get(j).getSpecies().size() == 1)
                {
                    System.out.println("Median Energy Level: " + allSpecies.get(i).get(j).getSpecies().get(0).getEnergyLevel());
                    System.out.println("Median Age: " + allSpecies.get(i).get(j).getSpecies().get(0).getAge());
                }
                else if(allSpecies.get(i).get(j).getSpecies().size() == 2)
                {
                    System.out.println("Median Energy Level: " + ((allSpecies.get(i).get(j).getSpecies().get(0).getEnergyLevel()+allSpecies.get(i).get(j).getSpecies().get(1).getEnergyLevel())/2));
                    System.out.println("Median Age: " + ((allSpecies.get(i).get(j).getSpecies().get(0).getAge()+allSpecies.get(i).get(j).getSpecies().get(1).getAge())/2));
                } */
                else
                {
                    Collections.sort(energyLevels); //sort in ascending order
                    Collections.sort(ages);
                        
                    if(energyLevels.size() % 2 == 0) //if there is an even number of elements
                    {
                        System.out.println("Median Energy Level: " + ((energyLevels.get(energyLevels.size()/2)+energyLevels.get((energyLevels.size()/2)-1))/2));
                    }
                    else //if there is an odd number of elements
                    {
                        System.out.println("Median Energy Level: " + (energyLevels.get(energyLevels.size()/2)));
                    }
                        
                    if(ages.size() % 2 == 0) //if there is an even number of elements
                    {
                        System.out.println("Median Age: " + ((ages.get(ages.size()/2)+ ages.get((ages.size()/2)+1))/2));
                    }
                    else //if there is an odd number of elements
                    {
                        System.out.println("Median Age: " + (ages.get(ages.size()/2)));
                    }
                }
            }
        }
    }
    
    /**
     * Program is run through main. A new park is created, the board is filled and printed, and the simulation is started
     * @param args String[], args[0] is the configuration file, args[1] is the number of steps the user wishes the simulation to advance
     * @throws FileNotFoundException if the file the user specified cannot be found
     */
    public static void main(String[] args)
    {
        Park newPark = new Park();
        int timeSteps = Integer.parseInt(args[1]);
            
        try{ //read in config file and distribute variables
            Scanner fileIn = new Scanner(new FileReader(args[0]));
            newPark.readConfig(fileIn);
            fileIn.close();
        } catch(FileNotFoundException e)
        {
            System.out.println("File not found.");
        }
        
        newPark.startingBoard(); //fill board
        newPark.gameGrid.printGrid(); //print grid
        
        newPark.simulation(timeSteps); //start simulation
    }
}
