import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Iterator;
import java.util.NoSuchElementException;

/** 
  * @desc this class represents directed graphs and implements 
  * components of graphs - edge and vertex. In addition this class
  * implements dijkstra's shortest path algorithm and finds shortest
  * path between two nodes.
  * @author Zura Mestiashvili mestiasz@lafayette.edu
  * @version v1.0.0
*/ 

public class DirectedGraph<K extends Comparable<K>, E>{
   private Map<K, DirectedGraphNode<K, E>> map; // container of all nodes
   public static final int INFINITY = Integer.MAX_VALUE;
   private String shortestPath; // String representation of shortest path(shows the keys and cost of the path)
   
   public DirectedGraph(){
       map = new HashMap<K, DirectedGraphNode<K, E>>();
   }

   /**
     * @desc AddNode method creates new node and adds it to 
     * map container. If node already exists then it is not added
     * again.
     * @param K k - value that should be a key for new node
     * @return boolean - true if node was added succesfully, false if not
   */
   public boolean AddNode(K k){
       // if node already exists return false 
       if(map.get(k) != null)
        return false;

       DirectedGraphNode<K, E> node = new DirectedGraphNode<K, E>(k);
       map.put(k, node);

       return true;
   }

   /**
     * @desc AddEdge method creates a new edge and adds this edge to
     * the outgoing edges' container of k1 parameter, and to the incoming 
     * edge's container of k2 parameter
     * @param K k1 - starting point of the edge
     * @param K k2 - ending point of the edge
     * @return boolean - true if edge was added successfully
   */
   public boolean AddEdge(K k1, K k2, int w){
       // if there are no such nodes return false
       // or if there is edge between them already return false too.
       DirectedGraphNode<K, E> node1 = map.get(k1);
       DirectedGraphNode<K, E> node2 = map.get(k2);
       
       if(node1 == null || node2 == null || node1 == node2)
        return false;
       
       for(int i = 0; i < node1.outgoing.size(); i++){
           if(node1.outgoing.get(i).end.key.compareTo(k2) == 0)
            return false;
       }
       // create new edge
       Edge edge = new Edge(node1, node2, w);
       node1.outgoing.add(edge);
       node2.incoming.add(edge);

       return true;
   }
   
   /**
     * @desc Clear All method returns each node to initial state
   */
   public void clearAll(){
       for(DirectedGraphNode node : map.values()){
           node.reset();
       }
   }
   
    /**
     * @desc toString methid returns string representation of the graph
     * @return String str - string representation of the graph
   */
   public String toString(){
       String str = "";
       for(DirectedGraphNode node : map.values()){
           str += node.key + node.outgoingToString() + "\n";
       }
       return str;
   }
   
   /**
     * @desc findShortestPath method calles dijkstra()
     * to calculate the shortest path between two points
     * @param K k0 - starting point of the path
     * @param K k1 - ending point of the path
     * @return ArrayList<DirectedGraphNode> path - shortest path between these two nodes
   */
   public ArrayList<DirectedGraphNode> findShortestPath(K k0, K k1){
        shortestPath = "";
        ArrayList<DirectedGraphNode> path = new ArrayList<DirectedGraphNode>();
        PriorityQueue<Path> pq = dijkstra(k0, k1);
        // if pq is emmpty this means there is no path between these two points, therefore
        // we should show that path was not found
        if(pq.size() == 0){ 
            shortestPath = "No path from " + k0 +  " to " + k1;
            return path;
        }
        // if shortest path exists build arraylist that contains all the steps
        // and create String representation
        Path tmpPath = pq.peek();
        DirectedGraphNode tmpNode = tmpPath.dest;
        
        do{
            path.add(0, tmpNode);
            shortestPath =  tmpNode != tmpPath.dest ? tmpNode.key + " --> " + shortestPath : tmpNode.key + shortestPath;
            tmpNode = tmpNode.prev;
        }while(tmpNode != null);
        shortestPath += " || " + tmpPath.cost;
        return path;
    }   
   
   /**
     * @desc this method tries to find shortest path between two points using dijkstra's
     * algorithm. 
     * @param K k0 - starting point of the path
     * @param K k1 - ending point of the path
     * @return PriorityQueue<Path> pq - queue of the paths
   */
   public PriorityQueue<Path> dijkstra(K k0, K k1){
       PriorityQueue<Path> pq = new PriorityQueue<Path>();
       
       DirectedGraphNode<K, E> start = map.get(k0);
       DirectedGraphNode<K, E> end = map.get(k1);
       if(start == null)
        throw new NoSuchElementException("Start vertex does not exist");
       
       clearAll();
       pq.add(new Path(start, 0));
       start.dist = 0;
       
       int nodesSeen = 0;
       while(!pq.isEmpty() && nodesSeen < map.size()){
           Path vrec = pq.peek();
           DirectedGraphNode<K, E> v = vrec.dest;
           // if our end point is first in queue stop applying dijkstra's algorithm and move to next step
           if(v == end) break;
           else pq.remove();
           // if we already checked the node, do not check it again
           if(v.scratch != 0)
            continue;
           
           v.scratch = 1;
           nodesSeen++;
           
           for(Edge edge : v.outgoing){
               DirectedGraphNode<K, E> w = edge.end;
               int tmpWeight = edge.weight;
               
               if(tmpWeight < 0){
                System.out.println("Graph has negative edges");
                break;
               }
               // if the path to the node was larger than it is through the current node
               // update the weight
               if(w.dist > v.dist + tmpWeight){
                   w.dist = v.dist + tmpWeight;
                   w.prev = v;
                   pq.add(new Path(w, w.dist));
               }

           }
       }
       return pq;
   }
   
   /**
     * @desc get String representation of shortest path
     * @return String shortestPath - shortest path between two nodes
   */
   public String getShortestPath(){
       return shortestPath;
   }
   /**
     * @desc getter for map container
     * @return Map<K, DirectedGraphNode<K, E>> map
   */
   public Map<K, DirectedGraphNode<K, E>> getMap(){
       return map;
   }
   
   /// Inner Classes
    /**
     * @desc DirectedGraphNode class represents a vertex of the graph
   */
   public class DirectedGraphNode<K, E>{
        private LinkedList<Edge> incoming; // container of incoming edges
        private LinkedList<Edge> outgoing; // container of outgoing edges
        private DirectedGraphNode<K, E> prev; // previous vertex on the path
        private K key; 
        private int dist;
        private int scratch;
        
        public DirectedGraphNode(K key){
           this.key = key;
           this.incoming = new LinkedList<Edge>();
           this.outgoing = new LinkedList<Edge>();
           reset();
        }
        
         /**
           * @desc get ready for applying Dijkstra's algorithm
         */
        public void reset(){
            dist = DirectedGraph.INFINITY;
            prev = null;
            scratch = 0;
        }
        /**
          * @desc string representation of outgoing edges
          * @return String str - outgoing edges
        */
        public String outgoingToString(){
           String str = "";
           for(int i = 0; i < outgoing.size(); i++){
               str += "  --> " + outgoing.get(i).end.key + "(" + outgoing.get(i).weight + ")";
           }
           return str;
        }
        
        // getters for class variables
        public int getDist(){ return dist; }
        public int getScratch(){ return scratch; }
        public K getKey(){ return key; }
        public DirectedGraphNode<K, E> getPrev(){ return prev; }
        public LinkedList<Edge> getOutgoing(){ return outgoing; }
        public LinkedList<Edge> getIncoming(){ return incoming; }

   }
   
    /**
     * @desc Edge class is a representation of edges of graph
   */
   public class Edge{
       private DirectedGraphNode<K, E> start; // starting vertex
       private DirectedGraphNode<K, E> end; // ending vertex
       private int weight; // weight of the edge

       public Edge(DirectedGraphNode<K, E> start, DirectedGraphNode<K, E> end, int w){
           this.start = start;
           this.end = end;
           this.weight = w;
       }
   }
   
   /**
     * @desc Path class is a representation of the path to certain node
     * and shows the cost of the path
   */
   public class Path implements Comparable<Path>{
       private DirectedGraphNode<K, E> dest;
       private int cost;
       
       public Path(DirectedGraphNode<K, E> dest, int cost){
           this.dest = dest;
           this.cost = cost;
       }
       /**
         * @desc path comparator
         * @return int - -1 if cost of path is less than another's, 1 if it 
         * is bigger, 0 if they are same
       */
       public int compareTo(Path path){
           int tmpCost = path.cost;
           return cost < tmpCost ? -1 : cost > tmpCost ? 1 : 0;
       }
       
   }
   
   // used for testing purposes
   /**
     * @desc create an instance of Path class
     * @return Path path - instance of Path class
   */
   public Path createPath(DirectedGraphNode<K, E> dest, int cost){
       return new Path(dest, cost);
   }

}
