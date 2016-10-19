import java.util.Scanner;
import java.util.ArrayList;
import java.util.TreeSet;
import java.util.Iterator;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Life{
    
    private static TreeSet<Specimen> sortedSpecies;
    
    public static void main(String args[]){
        
        try{
            Scanner sc = new Scanner(new File("data.txt"));
            sortedSpecies = new TreeSet<Specimen>(new SpecimenComparator());
            
            while(sc.hasNext()){
                String[] tmp = sc.nextLine().split(" ");
                sortedSpecies.add(new Specimen(tmp[0], tmp[1], tmp[2], Double.parseDouble(tmp[3]), Double.parseDouble(tmp[4])));
            }
            
            print(sortedSpecies);
            
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
        
        
    }
    
    public static void print(TreeSet sortedSpecies){
        try{
            PrintWriter writer = new PrintWriter(new FileWriter("data.txt"));
            Iterator iterator = sortedSpecies.iterator();
            while(iterator.hasNext()){
                writer.append(iterator.next().toString() + "\n");
            }
            writer.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
}
