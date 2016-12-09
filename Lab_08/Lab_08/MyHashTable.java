import java.util.LinkedList;

/** 
  * @desc this class builds a container with HashMap's features
  * keys for container are String type, while values are Team type.
  * @author Zura Mestiashvili mestiasz@lafayette.edu
  * @version v1.0.0
*/ 
public class MyHashTable{
   private LinkedList<Entry>[] buckets;
   private int size;
   
   @SuppressWarnings("unchecked")
   public MyHashTable(int n){
       this.buckets = new LinkedList[n];
       this.size = n;
       
       for(int i = 0; i < size; i++){
           this.buckets[i] = new LinkedList<Entry>();
       }
   }
   
   /** 
      * @desc insert new element in container
      * @param key - String
      * @param value - Team
      * @return boolean - if inserted successfuly
    */ 
   public boolean insert(String key, Team value){
       // find when to return false
       int hashCode = Math.abs(key.hashCode()%size);
       for(int i = 0; i < buckets[hashCode].size(); i++){
           if(buckets[hashCode].get(i).getKey().equals(key)){
               return false;
            }
       }
       Entry entry = new Entry(key, value);
       buckets[hashCode].add(0, entry);
       return true;
   }
   
   /** 
      * @desc find element in container
      * @param key - String
      * @return Team - return value for the key, or null if it
      * is not in our container
    */ 
   public Team find(String key){
       int hashCode = Math.abs(key.hashCode()%size);
       LinkedList<Entry> tmpList = buckets[hashCode];
       for(int i = 0; i < tmpList.size(); i++){
           Entry tmpEntry = tmpList.get(i);
           if(tmpEntry.getKey().equals(key))
            return tmpEntry.getValue();
       }
       return null;
   }
   
   /** 
      * @desc remove element from container
      * @param key - String
      * @return Team - return value for the key, or null if it
      * is not in our container or we could not remove
    */ 
   public Team remove(String key){
       int hashCode = Math.abs(key.hashCode()%size);
       LinkedList<Entry> tmpList = buckets[hashCode];
       for(int i = 0; i < tmpList.size(); i++){
           Entry tmpEntry = tmpList.get(i);
           if(tmpEntry.getKey().equals(key)){
            tmpList.remove(tmpEntry);
            return tmpEntry.getValue();
           }
       }
       return null;
   }
   
   /** 
      * @desc find load factor for our container
      * @return double - load factor
    */ 
   public double getLoadFactor(){
       int counter = 0;
       for(int i = 0; i < size; i++){
           if(buckets[i].size() > 0) counter++;
       }
       
       return 1.0 * counter/size;
   }
   
    /** 
      * @desc find String representation of the container
      * @return String - String representation of the container
    */ 
   public String toString(){
       String str = "";
       for(int i = 0; i < size; i++){
           str += "#" + i + "  Size: " + buckets[i].size() + " | ";
           for(int j = 0; j < buckets[i].size(); j++){
               str += buckets[i].get(j).getKey() + " | ";
           }
           str += "\n";
       }
       return str;
   }
   
   /** 
      * @desc get size of the container
      * @return int -size of the container
    */ 
   public int size(){
       return this.size;
   }
   
   /** 
      * @desc get the container itself
      * @return LinkedList<Entry>[] - the container
    */ 
   public LinkedList<Entry>[] getBuckets(){
       return this.buckets;
   }
   
}
