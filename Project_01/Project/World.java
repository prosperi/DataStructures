import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;

/**
 * Zura Mestiashvili
 * v1.0.0
 */

public class World{
    public static Terrain terrain;
    public static ArrayList<Specimen> habitants;
    public static DirectionGenerator dGen;
    public static PositionGenerator pGen;
    public static PopulationGenerator popGen;
    public static int max;
    
    public static void main(String[] args){
        
        // parse arguments
        Scanner sc = new Scanner(System.in);
        dGen = new DirectionGenerator(11);
        popGen = new PopulationGenerator();
        habitants = new ArrayList<Specimen>();
        max = Integer.parseInt(args[1]); 
        
        try(
            BufferedReader reader = new BufferedReader(new FileReader(args[0]));
        ){
            String tmp;
            //terrain.clear();
            while((tmp = reader.readLine()) != null){
                String[] arr = tmp.split(" ");
                if((arr[0]).compareTo("species") == 0){
                    // Create new Species by parsing config.txt
                    String[] tmpArr = arr[5].split(",");
                    int bound = popGen.next(Integer.parseInt(tmpArr[0]), Integer.parseInt(tmpArr[1]));
                    for(int i = 0; i < bound; i++){
                        Specimen newSpecimen = createSpecimen(arr);
                        habitants.add(newSpecimen);
                        // Append new Specimen to objectMap and map
                        terrain.objectMap[newSpecimen.getX()][newSpecimen.getY()].add(newSpecimen);
                        terrain.map[newSpecimen.getX()][newSpecimen.getY()] = newSpecimen.getSymbol();
                    }
                }else if(arr[0].compareTo("size") == 0){
                    int width = Integer.parseInt(arr[1]);
                    int height = Integer.parseInt(arr[2]);
                    pGen = new PositionGenerator(31, width, height);
                    terrain = new Terrain(width, height, 0);
                }else if(arr[0].compareTo("light") == 0){
                    terrain.setLight(Integer.parseInt(arr[1]));
                }
                    
            }
            
        }catch(IOException e){
            e.printStackTrace();
        }
        
        terrain.printMap();
        
        
        // Check for commands and execute them
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
                    // take max steps, max was passed as ana argument to the program
                    for(int i = 0; i < max; i++){
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
           if(habitants.get(z).getEnergy() <= 0){
               habitants.remove(z);
               z--;
           }
        }
        // Clear and re-Draw terrain
        // We can not modify objectMap and map as objectMap is the used in loop in method eat, 
        // which in come cases goes to die() method
        terrain.clear();
        terrain.addHabitants(habitants);
    }
    
    public static Specimen createSpecimen(String[] arr){
        // Build Energy Sources array
        String[] tmp_01 = arr[4].split(",");
        ArrayList<String> ls_01 = new ArrayList<String>();
        for(int i = 0; i < tmp_01.length; i++){
            ls_01.add(tmp_01[i]);
        }
        // Build Initial Stats array
        String[] tmp_02 = arr[5].split(",");
        ArrayList<Double> ls_02 = new ArrayList<Double>();
        for(int i = 0; i < tmp_02.length; i++){
            ls_02.add(Double.parseDouble(tmp_02[i]));
        }
        // Build stats array
        String[] tmp_03 = arr[6].split(",");
        ArrayList<Double> ls_03 = new ArrayList<Double>();
        for(int i = 0; i < tmp_03.length; i++){
            ls_03.add(Double.parseDouble(tmp_03[i]));
        }
        
        // Generate position, so that two specimen not occupy same cell 
        // in the beginning of simulation
        int[] position;
        do{
            position = pGen.initPosition();
        }while(terrain.checkCell(position) == false);
        
        
        if(arr[2].compareTo("herbivore") == 0 || arr[2].compareTo("carnivore") == 0 || arr[2].compareTo("omnivore") == 0 || arr[2].compareTo("animal") == 0)
            return new Animal(arr[1], arr[2], arr[3].charAt(0), ls_01, ls_02, ls_03, Double.parseDouble(arr[7]), Double.parseDouble(arr[8]), Double.parseDouble(arr[9]),  position);
        else
            return new Plant(arr[1], arr[2], arr[3].charAt(0), ls_01, ls_02, ls_03, Double.parseDouble(arr[7]), Double.parseDouble(arr[8]), Double.parseDouble(arr[9]),  position);
    }
        
   
    
}
