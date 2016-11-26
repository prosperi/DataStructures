import java.util.ArrayList;

public class Main
{
     public static void main(String args[]){
         Dict dict = new Dict();
         dict.add("a", "adog");
         dict.add("b", "bdog");
         dict.add("c", "cdog");
         dict.add("a", "amonkey");
         dict.add("b", "bno");
         dict.add("c", "cmonkey");
         dict.add("a", "am");
         dict.add("b", "bmonkey");
         dict.add("c", "cno");
         dict.add("a", "ano");
         
         ArrayList<String> tmp = dict.find("a");
         int i;
         for(i = 0; i < tmp.size(); i++){
             System.out.println(tmp.get(i));
         }
         
         tmp = dict.find("b");
         for(i = 0; i < tmp.size(); i++){
             System.out.println(tmp.get(i));
         }
         
         tmp = dict.find("c");
         for(i = 0; i < tmp.size(); i++){
             System.out.println(tmp.get(i));
         }
         
         tmp = dict.find("d");
         for(i = 0; i < tmp.size(); i++){
             System.out.println(tmp.get(i));
         }
         
         
      
     }
}
