import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.HashMap;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.FileWriter;

/** 
  * @desc this class is controller of our simulation. It is used
  * to load simulation, parse data, gather information and provide
  * user interaction.
  * @author Zura Mestiashvili mestiasz@lafayette.edu
  * @version v1.0.0
*/

public class World{
    public static Terrain terrain;
    public static ArrayList<Specimen> habitants;
    public static DirectionGenerator dGen;
    public static PositionGenerator pGen;
    public static PopulationGenerator popGen;
    public static ArrayList<ArrayList<String>> specimenLoader;
    public static ArrayList<Controller> controllers;
    public static int max;
    public static int stepCounter;
    public static int light;
    public static int changeCounter;
    public static PrintWriter outputFile;
    private static final int seed = 1;
    
    public static void main(String[] args){
        
        // parse arguments
        dGen = new DirectionGenerator(seed);
        popGen = new PopulationGenerator(seed);
        habitants = new ArrayList<Specimen>();
        max = Integer.parseInt(args[1]); 
        stepCounter = 0;
        changeCounter = 0;
        specimenLoader = new ArrayList<ArrayList<String>>();
        controllers = new ArrayList<Controller>();
        try{
            outputFile = new PrintWriter(new FileWriter("data.csv", true));
            outputFile.append('\n' + "seed = " + seed + '\n');
        }catch(IOException e){
            System.out.append("Problem with output file!!!");
        }
        // parse config txt and initialize World
        parseConfig(args[0]);
        // set light to our terrain
        terrain.setLight(light);
        // load species
        loadSpecies();
        // print out the map
        terrain.printMap();             
        // enable controller
        controller();
        
        
    }
    
    
    /**
      * @desc this method is part of creating new species
      * assigning them to controller and initializing map
    */
    public static void loadSpecies(){
        for(int j = 0; j < specimenLoader.size(); j++){
            String[] tmpArr = specimenLoader.get(j).get(5).split(",");
            int bound = popGen.next(Integer.parseInt(tmpArr[0]), Integer.parseInt(tmpArr[1]));
            // create new controller for each new species
            Controller controller = new Controller(specimenLoader.get(j).get(1));
            controllers.add(controller);
            // add new specimen while we have enough space
            for(int i = 0; i < bound && habitants.size() < terrain.getHeight() * terrain.getWidth(); i++){
                // create new specimen
                Specimen newSpecimen = createSpecimen(specimenLoader.get(j), controller);
                controller.addSpecimen(newSpecimen);
                controller.increaseInitialPopulation();
                habitants.add(newSpecimen);
                // Append new Specimen to objectMap and map
                terrain.objectMap.get(newSpecimen.getX()).get(newSpecimen.getY()).add(newSpecimen);
                terrain.map.get(newSpecimen.getX()).set(newSpecimen.getY(), newSpecimen.getSymbol());
                
            }
        }
    }
    
    
    /**
      * @desc this method is for parsing the config file
      * it checks if line starts with size, and initializes terrain
      * which is our board for the whole world, if the line
      * starts with word - species, than adds the arguments to ArrayList
      * to create new species later. If its starts with word - light,
      * saves the value to setLight for terrain later.
      * @param String path - path to config file
    */
    public static void parseConfig(String path){
        try(
            BufferedReader reader = new BufferedReader(new FileReader(path));
        ){
            String tmp;
            while((tmp = reader.readLine()) != null){
                outputFile.append(tmp + '\n');
                String[] arr = tmp.split(" ");
                if((arr[0]).compareTo("species") == 0){
                    // Create new Species by parsing config.txt
                    specimenLoader.add(new ArrayList<String>(Arrays.asList(arr)));
                }else if(arr[0].compareTo("size") == 0){
                    // initialize terrain
                    int width = Integer.parseInt(arr[1]);
                    int height = Integer.parseInt(arr[2]);
                    pGen = new PositionGenerator(31, width, height);
                    terrain = new Terrain(width, height, 0);
                }else if(arr[0].compareTo("light") == 0){
                    light = Integer.parseInt(arr[1]);
                }
                    
            }
            
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    /**
      * @desc this method handles user interaction; implements
      * commands provided in project description; p - prints
      * whole map, c - proceeds to next step, while i - goes through
      * the end of simulation, r - provides stats for current stage.
    */
    public static void controller(){
        // Here will be commander
        // Check for commands and execute them
        Scanner sc = new Scanner(System.in);
        boolean t = true;
        while( t == true && sc.hasNext() ){
            String cmd = sc.nextLine();
            switch (cmd){
                case "p":
                    terrain.printMap();
                    break;
                case "c":
                    // proceed to next step if simulation age has not exceded provided
                    // number and at least on specimen is alive
                    if(changeCounter < 50 && habitants.size() > 0 && stepCounter < max){
                        step();
                        terrain.printMap();
                        showChanges();
                    }else{
                        //System.out.println(changeCounter + " " + habitants.size() + " " + stepCounter );
                        System.out.println("End of Simulation");
                        finalStats();
                        t = false;
                    }
                    
                    if(stepCounter % 50 == 0){
                       showFifty();
                    }
                  
                    break;
                case "i":
                    // take max steps, max was passed as an argument to the program
                    for(int i = stepCounter; i < max && changeCounter < 50 && habitants.size() > 0; i++){
                        step();
                        if(stepCounter % 50 == 0){
                           showFifty();
                        }
                    }
                    System.out.println("End of Simulation");
                    finalStats();
                    t = false;
                    break;
                case "q":
                    t = false;
                    System.out.println("Thanks for using our simulation :)");
                    break;
                case "r":
                    showStats();
                    break;
            }
        }
        
       
    }
    
    /**
      * @desc this method create next step, at first checks 
      * if specimen can give birth to a child, than adds children
      * to map, as for the following steps like - move, specimen will 
      * be able to see children in the cell, this will avoid any problems
      * with movement restrictions. Next step is movement, and last one is
      * removing dead specimen. after which we redraw our map.
      * 
    */
    public static void step(){
       
        int previousPopulation = habitants.size();
        stepCounter++; 
        // substract living energy at first and allow all specimen to act
        for(int l = 0; l < habitants.size(); l++){
            Specimen tmp = habitants.get(l);
            tmp.setEnergy(tmp.getEnergy() - tmp.getLivingEnergy());
            tmp.action = true;
        }
        
        // Let's have a child
        ArrayList<Specimen> children = new ArrayList<Specimen>();
        for(int k= 0; k < habitants.size(); k++){
            Specimen tmp = habitants.get(k);
            if(tmp.action == true){
                tmp.giveBirth(dGen, terrain, habitants, children);
            }
        }        
        // Let's have a dinner together
        for(int j = 0; j < habitants.size(); j++){
          Specimen tmp = habitants.get(j);
          if(tmp.action == true){
              tmp.eat(terrain, habitants);
          }
        }   
        // Move Animals
        for(int i = 0; i < habitants.size(); i++){
            Specimen tmp = habitants.get(i);
            if(tmp instanceof Animal && tmp.action == true){
                ((Animal)habitants.get(i)).move(dGen, terrain, habitants);
            }
        }
        
        habitants.addAll(children);
        // Time to bury the dead
        for(int z = 0; z < habitants.size(); z++){
           Specimen tmp = habitants.get(z);
           if(tmp.getEnergy() <= 0 || stepCounter >= tmp.getDeathAge()){
               // remove this specific specimen from controller and habitants List
               tmp.getController().moveToDead(tmp);
               habitants.remove(z);
               z--;
           }else{
               // if not dead then increase the age
               tmp.setAge(tmp.getAge() + 1);
           }
        }
        // Clear and re-Draw terrain
        // We can not modify objectMap and map as objectMap is the used in loop in method eat, 
        // which in come cases goes to die() method
        terrain.clear();
        terrain.addHabitants(habitants);
        
        // check if nothing changed, if that's the case increase the counter, if not
        // make it equal to zero
        if(children.size() == 0 && habitants.size() == previousPopulation){
            changeCounter++;
        }else{
            changeCounter = 0;
        }
    }
    
    /**
      * @desc this method is used as a helper method
      * for r command and displaying the stats
    */
    public static void showStats(){
        for(int i = 0; i < controllers.size(); i++){
            Controller tmp = controllers.get(i);
            if(tmp.getPopulation() != 0)
                System.out.println(tmp.getName() + " " + tmp.getPopulation() + 
                                   " energy: " + tmp.getMedianEnergy() + " age: " + tmp.getMedianAge());
        }
    }
    
    /**
      * @desc this method is used as a helper method
      * for c command and displaying the stats
    */
    public static void showChanges(){
        for(int i = 0; i < controllers.size(); i++){
            Controller tmp = controllers.get(i);
            System.out.println(tmp.getName() + ": birth - " + tmp.getBirthChange() + " death - " + tmp.getDeathChange());
        }
    }
    
    /**
      * @desc this method is used as a helper method
      * for c command and displaying the stats after each
      * 50 steps
    */
    public static void showFifty(){
        System.out.println("Last 50 steps report: ");
        outputFile.append("Last 50 steps report: ");
        for(int i = 0; i < controllers.size(); i++){
            Controller tmp = controllers.get(i);
            String str= tmp.getName() + ": birth - " + tmp.getBirthFifty() + 
                                " death - " + tmp.getDeathFifty() + 
                                " energy - " + tmp.getEnergy();
            System.out.println(str);
            outputFile.append('\n' + str);

        }
    }
    
    /**
      * @desc this method is used as a helper method
      * for c and i command and displaying the stats exceeding
      * max steps
    */
    public static void finalStats(){
        terrain.printMap();
        outputFile.append("Final Results");
        for(int i = 0; i < controllers.size(); i++){
            Controller tmp = controllers.get(i);
            String str = tmp.getName() + ": birth - " + tmp.getBirth() + 
                                " death - " + tmp.getDeath() + 
                                " current population - " + tmp.getPopulation();
            System.out.println(str);
            outputFile.append('\n' + str);
            
        }
        System.out.println("Total steps: " + stepCounter);
        outputFile.append('\n' + "Total steps: " + stepCounter);
        outputFile.close();
    }
    
    /**
      * @desc this method creates new specimen based on 
      * parameters taken from config.txt
      * @param ArrayList<String> arr - list of specimen's properties
      * @param Controller controller - controller for this specific specimen
      * @return Specimen - return new Specimen, animal or plant
    */
    public static Specimen createSpecimen(ArrayList<String> arr, Controller controller){
        // Build Energy Sources array
        ArrayList<String> ls_01 = new ArrayList<String>(Arrays.asList(arr.get(4).split(",")));
        // get initial energy
        String[] tmp_02 = arr.get(5).split(",");
        double energy = Double.parseDouble(tmp_02[2]);
        
        // Build stats array 
        String[] tmp_03 = arr.get(6).split(",");
        ArrayList<Double> ls_03 = new ArrayList<Double>();
        for(int i = 0; i < tmp_03.length; i++){
            ls_03.add(Double.parseDouble(tmp_03[i]));
        }
        
        // Generate position, so that two specimen not occupy same cell 
        // in the beginning of simulation
        ArrayList<Integer> position;
        
        do{
            position = pGen.initPosition();
        }while(terrain.checkCell(position) == false);
        
        // if its one of herbivores, carnivores or omnivores then generate new Animal, if not generate new Plant
        if(arr.get(2).compareTo("herbivore") == 0 || arr.get(2).compareTo("carnivore") == 0 || arr.get(2).compareTo("omnivore") == 0 || arr.get(2).compareTo("animal") == 0)
            return new Animal(arr.get(1), arr.get(2), arr.get(3).charAt(0), ls_01, energy, ls_03, Double.parseDouble(arr.get(7)), Double.parseDouble(arr.get(8)), Double.parseDouble(arr.get(9)),  position.get(0), position.get(1), controller);
        else
            return new Plant(arr.get(1), arr.get(2), arr.get(3).charAt(0), ls_01, energy, ls_03, Double.parseDouble(arr.get(7)), Double.parseDouble(arr.get(8)), Double.parseDouble(arr.get(9)),  position.get(0), position.get(1), controller);
        
    }
        
    
   
    
}
