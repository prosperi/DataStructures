import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;
import java.util.Arrays;

/** 
  * @desc this class is controller of out simulation. It is used
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
    public static int max;
    public static int stepCounter;
    public static int light;
    
    public static void main(String[] args){
        
        // parse arguments
        dGen = new DirectionGenerator(11);
        popGen = new PopulationGenerator();
        habitants = new ArrayList<Specimen>();
        max = Integer.parseInt(args[1]); 
        stepCounter = 0;
        specimenLoader = new ArrayList<ArrayList<String>>();
        
        // parse config txt and initialize World
        parseConfig(args[0]);
        terrain.setLight(light);
        for(int j = 0; j < specimenLoader.size(); j++){
            String[] tmpArr = specimenLoader.get(j).get(5).split(",");
            int bound = popGen.next(Integer.parseInt(tmpArr[0]), Integer.parseInt(tmpArr[1]));
            for(int i = 0; i < bound && habitants.size() < terrain.getHeight() * terrain.getWidth(); i++){
                System.out.println(habitants.size());
                Specimen newSpecimen = createSpecimen(specimenLoader.get(j));
                habitants.add(newSpecimen);
                // Append new Specimen to objectMap and map
                terrain.objectMap.get(newSpecimen.getX()).get(newSpecimen.getY()).add(newSpecimen);
                terrain.map.get(newSpecimen.getX()).set(newSpecimen.getY(), newSpecimen.getSymbol());
            }
        }
                    
        //enable controller
        controller();
        
        
    }
    
    public static void parseConfig(String path){
        try(
            BufferedReader reader = new BufferedReader(new FileReader(path));
        ){
            String tmp;
            while((tmp = reader.readLine()) != null){
                String[] arr = tmp.split(" ");
                if((arr[0]).compareTo("species") == 0){
                    // Create new Species by parsing config.txt
                    specimenLoader.add(new ArrayList<String>(Arrays.asList(arr)));
                }else if(arr[0].compareTo("size") == 0){
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
        }finally{
            terrain.printMap();
        }
    }
    
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
                    step();
                    terrain.printMap();
                    break;
                case "i":
                    // take max steps, max was passed as an argument to the program
                    for(int i = stepCounter; i < max; i++){
                        step();
                    }
                    System.out.println("Done");
                    // Print out remaining animals
                    for(int i = 0; i < habitants.size(); i++){
                        System.out.println(habitants.get(i).getName());
                    }
                    break;
                case "q":
                    t = false;
                    System.out.println("Thanks for using our simulation :)");
                    break;
            }
        }
    }
    
    public static void step(){
        stepCounter++; // spend some time and think about this
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
               habitants.remove(z);
               z--;
           }else{
               tmp.setAge(tmp.getAge() + 1);
           }
        }
        // Clear and re-Draw terrain
        // We can not modify objectMap and map as objectMap is the used in loop in method eat, 
        // which in come cases goes to die() method
        terrain.clear();
        terrain.addHabitants(habitants);
    }
    
    public static Specimen createSpecimen(ArrayList<String> arr){
        // Build Energy Sources array
        ArrayList<String> ls_01 = new ArrayList<String>(Arrays.asList(arr.get(4).split(",")));

        // 
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
        
        
        if(arr.get(2).compareTo("herbivore") == 0 || arr.get(2).compareTo("carnivore") == 0 || arr.get(2).compareTo("omnivore") == 0 || arr.get(2).compareTo("animal") == 0)
            return new Animal(arr.get(1), arr.get(2), arr.get(3).charAt(0), ls_01, energy, ls_03, Double.parseDouble(arr.get(7)), Double.parseDouble(arr.get(8)), Double.parseDouble(arr.get(9)),  position.get(0), position.get(1));
        else
            return new Plant(arr.get(1), arr.get(2), arr.get(3).charAt(0), ls_01, energy, ls_03, Double.parseDouble(arr.get(7)), Double.parseDouble(arr.get(8)), Double.parseDouble(arr.get(9)),  position.get(0), position.get(1));
    }
        
   
    
}
