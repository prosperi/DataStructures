import java.util.Random;
import java.util.LinkedList;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.FileWriter;

/** 
  * @desc this class helps to organize experiment using
  * MyHashTable class
  * @author Zura Mestiashvili mestiasz@lafayette.edu
  * @version v1.0.0
*/ 
public class ExperimentController{
    public static MyHashTable ht;
    public static Random rnd;
    public static Wheel wheel;
    public static PrintWriter outputFile;
    
    
    public static final int BOUND_FOR_NUM_OF_ITEMS = 10;
    public static final int BOUND_FOR_SIZE = 5;
    public static final int CONST = 4;

    public ExperimentController(){
        
    }
   
    public static void main(String[] args){
        ht = null;
        wheel = new Wheel(1);
        try{
            outputFile = new PrintWriter(new FileWriter("data.csv", true));
        }catch(IOException e){
            System.out.println("Problem with output file!!!");
        }
        runExperiment();      
        outputFile.close();
    }
    
    /** 
      * @desc run experiment with different configurations,
      * using for loop to generate different params
    */ 
    public static void runExperiment(){
        for(int i = 1; i <= CONST; i++){
            int noi = BOUND_FOR_NUM_OF_ITEMS * i;
            for(int j = 1; j <= CONST; j++){
                int bfs = BOUND_FOR_SIZE + ((j-1) * 50);
                printResults(noi, bfs);                 
            }       
        }
    }
    
    /** 
      * @desc run experiment with different configurations,
      * using for loop to generate different params
      * @param int noi - number of items
      * @param int bfs - bound for size
    */ 
    public static void printResults(int noi,int bfs){
        long insertTime = timeInsert(noi,bfs);
        String output = "Number of Items: " + noi + " | Size: " + bfs + " | Load Factor: " + ht.getLoadFactor() + " | Time: " + insertTime + '\n';
        System.out.print("\n------------------ Creation ------------------\n");
        System.out.print(output);
        //System.out.println(ht);
        outputFile.append("\n------------------ Creation ------------------\n");
        outputFile.append(output);
        
        System.out.print("------------------ Search ------------------\n");
        outputFile.append("------------------ Search ------------------\n");
        
        for(int k = 1; k < 4; k++){
            LinkedList<Entry> entry = getList(bfs, k);
            long successful = -1;
            try{
                successful = timeFind(entry.get(entry.size() * 2 / 3).getKey());
            }catch(Exception e){
                System.out.println("there is no element at this postion " + entry.size() * 2 / 3);
            }
            long unsuccessful = timeFind("" + k * 10);
            
            output = "Number of Items: " + noi + " | Size: " + bfs + " | Load Factor: " + ht.getLoadFactor() + 
                     " | Time: " + insertTime + " | Successful Search: " + successful + " | Unsuccessful search: " + unsuccessful;
            System.out.print(output + '\n');
            outputFile.append(output + '\n');
                               
        }
        
    }
    
    /** 
      * @desc getNode from HashTable for which we are running
      * searching algorithm
      * @param int bfs - bound for size
      * @param int k - coefficient to generate different nodes for search 
      * @return LinkedList<Entry entry - return element from container
    */ 
    public static LinkedList<Entry> getList(int bfs, int k){
        int index = bfs * k / 4;
        LinkedList<Entry> entry = ht.getBuckets()[index];
        do{
            if(index + 1 >= bfs) break;
            entry = ht.getBuckets()[++index];
        }while(entry.size() == 0);
        
        return entry;
    }
    
    /** 
      * @desc calculate insertion time
      * @param int numOfItems - number of items
      * @param int size - bound for size
      * @return long time - insertion time
    */ 
    public static long timeInsert(int numOfItems, int size){
        ht = new MyHashTable(size);
        Team team = new Team();
        long startTime = System.currentTimeMillis();
        
        String key;
        for(int i = 0; i < numOfItems;){
            key = wheel.spinString();
            if(ht.insert(key, team)) i++;
        }
        
        long finishTime = System.currentTimeMillis();
        return finishTime - startTime;
    }
    
    /** 
      * @desc calculate searching time
      * @param String key - key that we search in HashTable
      * @return long time - insertion time
    */ 
    public static long timeFind(String key){
        long startTime = System.currentTimeMillis();
        Team team = ht.find(key);
        long finishTime = System.currentTimeMillis();
        return finishTime - startTime;
    }
}
