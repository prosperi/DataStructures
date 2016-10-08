import java.util.ArrayList;
import java.util.Scanner;

/**
 * Zura Mestiashvili
 * v1.0.0
 */
public class World{
    public static Terrain terrain;
    public static ArrayList<Specimen> habitants;
    public static DirectionGenerator dGen;
    public static PositionGenerator pGen;
    
    public static void main(String[] args){
        
        Scanner sc = new Scanner(System.in);
        dGen = new DirectionGenerator(11);
        pGen = new PositionGenerator(31, 15, 15);
        terrain = new Terrain(15, 15, 5);
        habitants = new ArrayList<Specimen>();
        
        
        for(int i = 97; i < 100; i++){
            Animal animal = bulkAnimals((char)i);
            habitants.add(animal);
        }
        
        terrain.addHabitants(habitants);
        terrain.printMap();
        
        while(sc.hasNext()){
            String cmd = sc.nextLine();
            switch (cmd){
                case "p":
                    terrain.printMap();
                    break;
                case "n":
                    for(int i = 0; i < habitants.size(); i++){
                        if(habitants.get(i) instanceof Animal){
                            ((Animal)habitants.get(i)).move(dGen);
                        }
                    }
                    terrain.clear();
                    terrain.addHabitants(habitants);
                    terrain.printMap();
                    break;
            }
        }
        
    }
    
    
    public static Animal bulkAnimals(char c){
        ArrayList<String> ls_01 = new ArrayList<String>();
        ls_01.add("wheat");
        ls_01.add("banana");
        ArrayList<Double> ls_02 = new ArrayList<Double>();
        ls_02.add(70.0);
        ls_02.add(3.0);
        ls_02.add(30.0);
        ArrayList<Double> ls_03 = new ArrayList<Double>();
        ls_03.add(15.3);
        ls_03.add(4.2);
        
        int[] position;
        do{
            position = pGen.initPosition();
        }while(terrain.checkCell(position) == false);
        
        Animal animal = new Animal("cow", "herbivore", c, ls_01, ls_02, ls_03, 50.0, 60.0, 5.0,  position);
        
        return animal;
    }
   
    
}
