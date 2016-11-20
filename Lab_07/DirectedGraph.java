import java.util.LinkedList;
import java.util.Map;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class DirectedGraph<K extends Comparable<K>, E>{
   private Map<K, DirectedGraphNode<K, E>> map;
   public static final int INFINITY = Integer.MAX_VALUE;

   public DirectedGraph(){
       map = new HashMap<K, DirectedGraphNode<K, E>>();
   }

   public boolean AddNode(K k){
       // find in which case is false returned
       // if node already exists return false 
       if(map.get(k) != null)
        return false;

       DirectedGraphNode<K, E> node = new DirectedGraphNode<K, E>(k);
       map.put(k, node);

       return true;
   }

   public boolean AddEdge(K k1, K k2, int w){
       // find in which case is false returned
       // if there are no such nodes return false
       // or if there is edge between them already return false too.
       DirectedGraphNode<K, E> node1 = map.get(k1);
       DirectedGraphNode<K, E> node2 = map.get(k2);

       if(node1 == null || node2 == null)
        return false;
       
       for(int i = 0; i < node1.outgoing.size(); i++){
           if(node1.outgoing.get(i).end.key.compareTo(k2) == 0)
            return false;
       }

       Edge edge = new Edge(node1, node2, w);
       node1.outgoing.add(edge);
       node2.incoming.add(edge);

       return true;
   }
   
   public void clearAll(){
       for(DirectedGraphNode node : map.values()){
           node.reset();
       }
   }
   
   
   public String toString(){
       String str = "";
       for(DirectedGraphNode node : map.values()){
           str += node.key + node.outgoingToString() + "\n";
       }
       return str;
   }
   
   public String tryDijkstra(K k0, K k1){
        String str = "";
        PriorityQueue<Path> pq = dijkstra(k0, k1);
        Path tmpPath = pq.peek();
        System.out.println(pq.size());
        DirectedGraphNode tmpNode = tmpPath.dest;
        
        do{
            str =  tmpNode != tmpPath.dest ? tmpNode.key + " --> " + str : tmpNode.key + str;
            tmpNode = tmpNode.prev;
        }while(tmpNode != null);
        
        return str + " || " + tmpPath.cost;
    }   
   
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
           //check
           if(v == end) break;
           else pq.remove();
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
               
               if(w.dist > v.dist + tmpWeight){
                   w.dist = v.dist + tmpWeight;
                   w.prev = v;
                   pq.add(new Path(w, w.dist));
               }

           }
       }
       System.out.println(pqToString(pq));
       return pq;
   }
   
   public String pqToString(PriorityQueue<Path> pq){
       String str = "";
       Iterator it = pq.iterator();
       while(it.hasNext()){
           Path tmp = (Path)it.next();
           str += tmp.dest.key + " : " + tmp.cost + " || ";
       }
       return str;
   }

   
   /// Inner Classes

   public class DirectedGraphNode<K, E>{
        LinkedList<Edge> incoming;
        LinkedList<Edge> outgoing;
        DirectedGraphNode<K, E> prev;
        K key;
        int dist;
        int scratch;
        
        public DirectedGraphNode(K key){
           this.key = key;
           this.incoming = new LinkedList<Edge>();
           this.outgoing = new LinkedList<Edge>();
           reset();
        }
        
        public void reset(){
            dist = DirectedGraph.INFINITY;
            prev = null;
            scratch = 0;
        }
        
        public String outgoingToString(){
           String str = "";
           for(int i = 0; i < outgoing.size(); i++){
               str += "  --> " + outgoing.get(i).end.key + "(" + outgoing.get(i).weight + ")";
           }
           return str;
        }

   }

   public class Edge{
       DirectedGraphNode<K, E> start;
       DirectedGraphNode<K, E> end;
       int weight;

       public Edge(DirectedGraphNode<K, E> start, DirectedGraphNode<K, E> end, int w){
           this.start = start;
           this.end = end;
           this.weight = w;
       }
   }
   
   
   public class Path implements Comparable<Path>{
       public DirectedGraphNode<K, E> dest;
       public int cost;
       
       public Path(DirectedGraphNode<K, E> dest, int cost){
           this.dest = dest;
           this.cost = cost;
           //z/System.out.println(dest.key + " : " + cost);
       }
       
       public int compareTo(Path path){
           int tmpCost = path.cost;
           return cost < tmpCost ? -1 : cost > tmpCost ? 1 : 0;
       }
       
   }

}
