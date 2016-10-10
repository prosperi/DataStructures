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
    public static int max;
    
    public static void main(String[] args){
        
        Scanner sc = new Scanner(System.in);
        dGen = new DirectionGenerator(11);
        pGen = new PositionGenerator(3, 15, 15);
        terrain = new Terrain(15, 15, 5);
        habitants = new ArrayList<Specimen>();
        max = 1000000; 
        
        try(
            BufferedReader reader = new BufferedReader(new FileReader(args[0]));
        ){
            String tmp;
            terrain.clear();
            while((tmp = reader.readLine()) != null){
                String[] arr = tmp.split(" ");
                if((arr[0]).compareTo("species") == 0){
                    for(int i = 0; i < 7; i++){
                        Specimen newSpecimen = createSpecimen(arr);
                        habitants.add(newSpecimen);
                        terrain.objectMap[newSpecimen.getX()][newSpecimen.getY()].add(newSpecimen);
                        terrain.map[newSpecimen.getX()][newSpecimen.getY()] = newSpecimen.getSymbol();
                    }
                }
                    
            }
            
        }catch(IOException e){
            e.printStackTrace();
        }
        
        terrain.printMap();
        
        while(sc.hasNext()){
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
                    for(int i = 0; i < max; i++){
                        step();
                    }
                    for(int i = 0; i < habitants.size(); i++){
                        System.out.println(habitants.get(i).getName());
                    }
                    break;
            }
        }
        
    }
    
    public static void step(){
        // Move Animals
        for(int i = 0; i < habitants.size(); i++){
            if(habitants.get(i) instanceof Animal){
                ((Animal)habitants.get(i)).move(dGen, terrain, habitants);
            }
        }
        // Let's have a dinner together
        for(int j = 0; j < habitants.size(); j++){
           Specimen tmp = habitants.get(j);
           if(tmp instanceof Plant) ((Plant)tmp).eat(terrain, habitants);
           else ((Animal)tmp).eat(terrain, habitants);
        }
        // Time to bury the dead
        for(int z = 0; z < habitants.size(); z++){
           if(habitants.get(z).getEnergy() == 0) habitants.remove(z);
        }
        
        terrain.clear();
        terrain.addHabitants(habitants);
    }
    
    public static Specimen createSpecimen(String[] arr){
        String[] tmp_01 = arr[4].split(",");
        ArrayList<String> ls_01 = new ArrayList<String>();
        for(int i = 0; i < tmp_01.length; i++){
            ls_01.add(tmp_01[i]);
        }
        
        String[] tmp_02 = arr[5].split(",");
        ArrayList<Double> ls_02 = new ArrayList<Double>();
        for(int i = 0; i < tmp_02.length; i++){
            ls_02.add(Double.parseDouble(tmp_02[i]));
        }
        
        String[] tmp_03 = arr[6].split(",");
        ArrayList<Double> ls_03 = new ArrayList<Double>();
        for(int i = 0; i < tmp_03.length; i++){
            ls_03.add(Double.parseDouble(tmp_03[i]));
        }
        
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
