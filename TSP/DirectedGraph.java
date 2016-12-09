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
   private String shortestRoute;
   PriorityQueue<Route> pqRoute = new PriorityQueue<Route>();
   
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
       
       
       // create new edge
       Edge edge_01 = new Edge(node1, node2, w);
       Edge edge_02 = new Edge(node2, node1, w);
       node1.adjacent.add(edge_01);
       node2.adjacent.add(edge_02);

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
   
   
   
  
   
    public ArrayList<DirectedGraphNode> findShortestRoute(K k0){
        shortestPath = "";
        ArrayList<DirectedGraphNode> route = new ArrayList<DirectedGraphNode>();
        
        ArrayList<DirectedGraphNode<K, E>> toBeVisited = new ArrayList<DirectedGraphNode<K, E>>();
        toBeVisited.addAll(map.values());
        Route routeC = new Route(map.get(k0), 0, new LinkedList<DirectedGraphNode<K, E>>());
        DirectedGraphNode<K, E> sNode = map.get(k0);
        toBeVisited.remove(sNode);
        
        Route tmpRoute = buildTour(toBeVisited, routeC, sNode, 0);
        for(Route r : pqRoute){
            System.out.println(r.dest.getKey() + " | weight: " + r.cost );
        }
        // if pq is emmpty this means there is no path between these two points, therefore
        // we should show that path was not found
        /*if(pq.size() == 0){ 
            shortestPath = "No path from " + k0;
            return route;
        }*/
        // if shortest path exists build arraylist that contains all the steps
        // and create String representation
        //Route tmpRoute = pq.peek();
        DirectedGraphNode tmpNode;
        System.out.println(tmpRoute.dest.getKey() + " | " + tmpRoute.cost );
        for(int i  = 0; i < tmpRoute.ls.size(); i++){
            tmpNode = tmpRoute.ls.get(i);
            route.add(tmpNode);
            shortestRoute =  tmpNode.key + " | ";
        }
        shortestRoute = tmpRoute.toString();
        return route; 
    }   
   
   public Route buildTour(ArrayList<DirectedGraphNode<K, E>> toBeVisited, Route routeC, DirectedGraphNode<K, E> sNode, int w){
       boolean extend = false;
       
       //System.out.println("Dest " + sNode.getKey()+ " | " + w + " | Size: " + pqRoute.size()); 
       if(toBeVisited.size() == 0){
           boolean t = false;
           int nw = 0;
           for(Edge e : sNode.getAdjacent()){
               if(e.end == routeC.ls.get(0) ){
                t = true;
                nw = e.weight;
               }
           }
           if(t){
               return new Route(routeC.ls.get(0), w + nw, routeC.ls);
           }else{
             w = Integer.MAX_VALUE;
             return new Route(routeC.dest, w, routeC.ls);  
           }  
       }
       
       for(Edge e : sNode.getAdjacent()){
           
           if(toBeVisited.contains(e.end)){
               extend = true;
               Route route = new Route(e.end, routeC.cost + e.weight, routeC.ls);
               ArrayList<DirectedGraphNode<K, E>> toBeVisitedTMP = new ArrayList<DirectedGraphNode<K, E>>(toBeVisited);
               toBeVisitedTMP.remove(e.end);
               Route tmp = buildTour(toBeVisitedTMP, route, e.end, w + e.weight);
               pqRoute.add(tmp);
           }
           
       }
       
       if(extend == false && toBeVisited.size() > 0){
           w = Integer.MAX_VALUE;
           return new Route(routeC.dest, w, routeC.ls);
       }
       
       
       return pqRoute.poll();
   }
    
   
   /**
     * @desc get String representation of shortest path
     * @return String shortestPath - shortest path between two nodes
   */
   public String getShortestPath(){
       return shortestPath;
   }
   
    public String getShortestRoute(){
       return shortestRoute;
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
        private LinkedList<Edge> adjacent; // container of incoming edges
        private DirectedGraphNode<K, E> prev; // previous vertex on the path
        private K key; 
        private int dist;
        private int scratch;
        
        public DirectedGraphNode(K key){
           this.key = key;
           this.adjacent = new LinkedList<Edge>();
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
           for(int i = 0; i < adjacent.size(); i++){
               str += "  --> " + adjacent.get(i).end.key + "(" + adjacent.get(i).weight + ")";
           }
           return str;
        }
        
        // getters for class variables
        public int getDist(){ return dist; }
        public int getScratch(){ return scratch; }
        public K getKey(){ return key; }
        public DirectedGraphNode<K, E> getPrev(){ return prev; }
        public LinkedList<Edge> getAdjacent(){ return adjacent; }

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

   
    public class Route implements Comparable<Route>{
       private DirectedGraphNode<K, E> dest;
       private int cost;
       private LinkedList<DirectedGraphNode<K, E>> ls;
       
       public Route(DirectedGraphNode<K, E> dest, int cost, LinkedList<DirectedGraphNode<K, E>> ls){
           this.dest = dest;
           this.cost = cost;
           this.ls = new LinkedList<DirectedGraphNode<K, E>>(ls);
           this.ls.add(dest);
           
       }
       /**
         * @desc path comparator
         * @return int - -1 if cost of path is less than another's, 1 if it 
         * is bigger, 0 if they are same
       */
       public int compareTo(Route route){
           int tmpCost = route.cost;
           return cost < tmpCost ? -1 : cost > tmpCost ? 1 : 0;
       }
       
       public String toString(){
           String str = "";
           for(int i = 0; i < ls.size(); i++){
               str += " " + ls.get(i).key + " ";
           }
           
           return str;
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
