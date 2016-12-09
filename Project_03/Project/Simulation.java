import java.util.*;
import java.io.*;

/** 
  * @desc this class is a controller of whole simulation
  * this provides data for World to start artificial life 
  * imitation. This class is used to initalize objects and 
  * place on the map
  * @author Zura Mestiashvili mestiasz@lafayette.edu
  * @version v1.0.0
*/

public class Simulation
{
    public static int SEED = 12345678;
    public File configFile;
    public File graphFile;
    public World world;
    public TouristMap tMap;
    public int maxSteps;
    public int steps;
    
    public static void main(String[] args) {
        String filePath = args[0];
        int simulationSteps = Integer.parseInt(args[1]);
        String graphPath = args[2];
        
        SEED = Integer.parseInt(args[3]);
        
        Simulation sim = new Simulation(filePath, graphPath, simulationSteps);
        sim.run();
    }
    
    public Simulation(String fp, String gp, int s) {
        try {
            this.configFile = new File(fp);
            this.graphFile = new File(gp);
        } catch(Exception e) {
            System.out.println(e);
            System.exit(0);
        }
        this.maxSteps = s;
        this.steps = 0;
    }
    
    public void setWorld(World w) {
        world = w;
    }
    
    /**
     * Creates world and starts loop of commands
     */
    public void run() {
        if(configFile == null) {System.exit(0);}
        initWorld();
        if(world == null) {
            System.out.println("Unable to read config file.");
            return;
        }
        world.print();
        
        Scanner reader = new Scanner(System.in);
        String line = reader.nextLine();
        while(canRun()) {
            if(line.equals("p")) {
                world.print();
            } else if(line.equals("c")) {
                world.turn();
                tMap.guideTour();
                Species.printInfo();
                steps++;
                if(steps % 50 == 0) {
                    Species.printSummary(50);
                }
            } else if(line.equals("r")) {
                Species.printStatus();
            }
            // if command is q stop the simulation runtime
            else if(line.equals("q")){
                break;
            }
            // if command is m -> print tourist map
            else if(line.equals("m")){
                System.out.println(tMap);
            }
            
            if(line.equals("i")) {
                world.turn();
                tMap.guideTour();
                steps++;
                if(steps % 50 == 0) {
                    Species.printSummary(50);
                }
            } else {
                line = reader.nextLine();
            }
        }
        world.print();
        Species.printSummary(-1);
        if(this.steps >= this.maxSteps)
            System.out.println("Simulation ended because the turn limit was reached.");
        else if(getPopulationOfWorld() == 0)
            System.out.println("Simulation ended because all of the species died.");
        else if(getChangesInPastSteps() == 0)
            System.out.println("Simulation ended because there were no changes in the last 50 turns.");
    }
    
    /**
     * Creates the world, gets size and light values, and adds all species to a list so they can be added to the world
     */
    public void initWorld() {
        int height = 0,width = 0,light = 0;
        List<Species> species = new ArrayList<Species>();
        //Store all the mountains in List of mountains
        List<Mountain> mountains = new ArrayList<Mountain>();
        
        Random generator = new Random(SEED);
        //Creates the file scanner
        Scanner fileReader = null;
        try {
            fileReader = new Scanner(configFile);
        } catch(Exception e) {}
        
        if(fileReader != null) {
            while(fileReader.hasNext()) {
                String line = fileReader.nextLine();
                String[] words = line.split(" ");
                if(words[0].equals("size")) {
                    height = Integer.parseInt(words[1]);
                    width = Integer.parseInt(words[2]);
                } else if(words[0].equals("light")) {
                    light = Integer.parseInt(words[1]);
                    if(width > 0 && height > 0) world = new World(height,width,light);
                } else if(words[0].equals("species")) {
                    //Creates all variables to be passed as parameters to the species constructor
                    String name = words[1];
                    String type = words[2];
                    String symbol = words[3];
                    
                    String[] es = words[4].split(",");
                    List<String> energySources = new ArrayList<String>();
                    for(int i = 0; i < es.length; i++) {energySources.add(es[i]);}
                    
                    double popMedian = Double.parseDouble(words[5].split(",")[0]);
                    double popStd = Double.parseDouble(words[5].split(",")[1]);
                    int initialEnergy = Integer.parseInt(words[5].split(",")[2]);
                    double deathMedian = Double.parseDouble(words[6].split(",")[0]);
                    double deathStd = Double.parseDouble(words[6].split(",")[1]);
                    int birthEnergy = Integer.parseInt(words[7]);
                    int maxEnergy = Integer.parseInt(words[8]);
                    
                    int movementRange = Integer.parseInt(words[9]);
                    int detectionRange = Integer.parseInt(words[10]);
                    double threshold = Double.parseDouble(words[12]);
                    
                    int livingEnergy = Integer.parseInt(words[11]);
                    
                    //Creates a number of the new species based on the population distribution and adds them to the list of species to be added to the world
                    Species tempSpecies = null;
                    int numToAdd = (int)(popMedian + (popStd * generator.nextGaussian()));
                    for(int i = 0; i < numToAdd; i++) {
                        if(type.equals("carnivore")) {
                            tempSpecies = new Carnivore(name, symbol, energySources, deathMedian, deathStd, birthEnergy, maxEnergy, livingEnergy, initialEnergy, popMedian, popStd, detectionRange, movementRange, threshold);
                        } else if(type.equals("herbivore")) {
                            tempSpecies = new Herbivore(name, symbol, energySources, deathMedian, deathStd, birthEnergy, maxEnergy, livingEnergy, initialEnergy, popMedian, popStd, detectionRange, movementRange, threshold);
                        } else if(type.equals("omnivore")) {
                            tempSpecies = new Omnivore(name, symbol, energySources, deathMedian, deathStd, birthEnergy, maxEnergy, livingEnergy, initialEnergy, popMedian, popStd, detectionRange, movementRange, threshold);
                        } else if(type.equals("vegetable") || type.equals("vegetable")) {
                            tempSpecies = new Vegetable(name, symbol, energySources, deathMedian, deathStd, birthEnergy, maxEnergy, livingEnergy, initialEnergy, popMedian, popStd, detectionRange, movementRange, threshold);
                        } else if(type.equals("fruit")) {
                            tempSpecies = new Fruit(name, symbol, energySources, deathMedian, deathStd, birthEnergy, maxEnergy, livingEnergy, initialEnergy, popMedian, popStd, detectionRange, movementRange, threshold);
                        }
                        species.add(tempSpecies);
                    }
                }
                //Check if current line provides information about the mountain
                // if so build a mountain range
                else if(words[0].equals("mountain")){
                    String[] tmp = words[1].split(",");
                    int startingX = Integer.parseInt(tmp[0]);
                    int startingY = Integer.parseInt(tmp[1]);
                    int endingX = Integer.parseInt(tmp[2]);
                    int endingY = Integer.parseInt(tmp[3]);
                    
                    // Create new Mountain range
                    Mountain tmpMountain = new Mountain(startingX, startingY, endingX, endingY);
                    mountains.add(tmpMountain);
                }
                 //Check if current line provides information about the tour
                // if so build new tour
                else if(words[0].equals("tour")){
                    drawGraph();
                    char symbol = words[1].charAt(0);
                    String[] startingTiles = words[2].split(",");
                    int capacity = Integer.parseInt(words[3]);
                    int radius = Integer.parseInt(words[4]);
                    double effect = Double.parseDouble(words[5]);
                    
                    
                    for(int i = 0; i < startingTiles.length; i++){
                        tMap.addTour(new Tour(tMap, symbol, Integer.parseInt(startingTiles[i]), capacity, radius, effect));
                    }
                    
                    tMap.guideTour();
                }
            }
            //Creates world and adds species to it
            if(world == null) world = new World(height,width,light);
            Species.setStaticWorld(world);
            //add mountains to our World
            for(int i = 0; i < mountains.size(); i++){
              world.addMountainToWorld(mountains.get(i)); 
            }
            for(int i = 0; i < species.size(); i++) {
                world.randomAddToWorld(species.get(i));
            }
        } else {
            System.out.println("Unable to read config file.");
            return;
        }
    }
    
    public void drawGraph(){
        tMap = new TouristMap(world);
        try {
            Scanner sc = new Scanner(graphFile);
            Random rnd = new Random(SEED);
            while(sc.hasNext()){
                String[] line = sc.nextLine().split(" ");
                int tile_key_01 = Integer.parseInt(line[0]);
                int tile_key_02 = Integer.parseInt(line[2]);
                int w = Integer.parseInt(line[3]);
                
                if(tMap.getSize() < world.getHeight() * world.getWidth()){
                    tMap.addTile(tile_key_01, generateTilePosition(rnd));
                    tMap.addTile(tile_key_02, generateTilePosition(rnd));
                    tMap.addEdge(tile_key_01, tile_key_02, w);
                }else{
                    System.out.println("No place for tourism :)");
                    break;
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public ArrayList<Integer> generateTilePosition(Random rnd){
        ArrayList<Integer> position = new ArrayList<Integer>();
        int x, y;
        do{
            x = rnd.nextInt(world.getHeight());
            y = rnd.nextInt(world.getWidth());
        }while(world.get(x, y).getTile() != null);
        
        position.add(x);
        position.add(y);
        
        return position;
        
    }
    
    /**
     * Checks the three conditions for ending the sim
     * @return boolean - returns true if the simulation should continue, false if one of the three rules is broken
     */
    public boolean canRun() {
        if(this.steps >= this.maxSteps)
            return false;
        else if(getPopulationOfWorld() == 0)
            return false;
        else if(getChangesInPastSteps() == 0)
            return false;
        else
            return true;
    }
    
    /**
     * Counts all species
     * @return int - number of instances of species in the world
     */
    public int getPopulationOfWorld() {
        int population = 0;
        for(int i = 0; i < world.getHeight(); i++) {
            for(int j = 0; j < world.getWidth(); j++) {
                Cell cell = world.get(i,j);
                if(cell != null && cell.getAnimal() != null) {
                    population++;
                }
                if(cell != null && cell.getPlant() != null) {
                    population++;
                }
            }
        }
        return population;
    }
    
    /**
     * Counts changes from birth and death lists
     * @return int - number of change in the last 50 steps
     */
    public int getChangesInPastSteps() {
        if(Species.births.size() < 50 && Species.deaths.size() < 50)
            return 1;
        int changes = 0;
        for(int i = Species.births.size()-50; i < Species.births.size(); i++) {
            for(int j = 0; j < Species.births.get(i).size(); j++) {
                changes += Species.births.get(i).get(j);
            }
        }
        for(int i = Species.deaths.size()-50; i < Species.deaths.size(); i++) {
            for(int j = 0; j < Species.deaths.get(i).size(); j++) {
                changes += Species.deaths.get(i).get(j);
            }
        }
        return changes;
    }
}
