import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.Map;
import java.util.LinkedList;

/** 
  * @desc this class tests DirectedGraph class and Dijkstra's algorithm
  * @author Zura Mestiashvili mestiasz@lafayette.edu
  * @version v1.0.0
*/ 
public class GraphExperiment{
    public static DirectedGraph<Integer, Integer> graph;
    
    public GraphExperiment(){

    }
    
    /** 
      * @desc parse the configuration file and apply Dijkstra's
      * algorithm to find shortest path from every entrance node(node 
      * with 0 incoming edges) to exit node(vertex with 0 outgoing edges).
      * @params String args[] - array of arguments
    */ 
    public static void main(String args[]){
        String path = args[0];
        graph = new DirectedGraph<Integer, Integer>();
        try(
            BufferedReader reader = new BufferedReader(new FileReader(path));
        ){
            String tmp;
            //build a graph
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
        System.out.println("Following is our graph:");
        System.out.println(graph);
        System.out.println("Shortest Paths: <path> || <cost>");
        System.out.println(solveGraph());
        
        
    }
    
    /** 
      * @desc this method applies Dijkstra's algorithm and finds 
      * shortest path for all entrance nodes.
      * @return String str - String representation of all shortest paths
    */ 
    public static String solveGraph(){
        String str= "";
        Map<Integer, DirectedGraph<Integer, Integer>.DirectedGraphNode<Integer, Integer>> map = graph.getMap();
        ArrayList<Integer> entrance = new ArrayList<Integer>();
        ArrayList<Integer> exit = new ArrayList<Integer>();
        
        for(DirectedGraph<Integer, Integer>.DirectedGraphNode<Integer, Integer> node : map.values()){
            if(node.getIncoming().size() == 0){
                entrance.add(node.getKey());
            }
            if(node.getOutgoing().size() == 0){
                exit.add(node.getKey());
            }
        }
        
        for(int i = 0; i < entrance.size(); i++){
            for(int j = 0; j < exit.size(); j++){
                ArrayList<DirectedGraph.DirectedGraphNode> shortestPath = graph.findShortestPath(entrance.get(i), exit.get(j));
                str += graph.getShortestPath() + "\n";
            }
        }
        
        return str;
    }
    
}
