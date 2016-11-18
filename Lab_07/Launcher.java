import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;

public class Launcher{
    
    public static void main(String args[]){
        String path = args[0];
        DirectedGraph<Integer, Integer> graph = new DirectedGraph<Integer, Integer>();
        
        try(
            BufferedReader reader = new BufferedReader(new FileReader(path));
        ){
            String tmp;
            while((tmp = reader.readLine()) != null){
                String[] arr = tmp.split(" ");
                int n1 = Integer.parseInt(arr[0]);
                int n2 = Integer.parseInt(arr[2]);
                int w = Integer.parseInt(arr[3]);
                
                graph.AddNode(n1);
                graph.AddNode(n2);
                graph.AddEdge(n1, n2, w);
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        System.out.println(graph);
    }
    
}
