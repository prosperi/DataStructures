import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Iterator;
import java.util.ArrayList;

public class DirectedGraphTest
{
    DirectedGraph<Integer, Integer> graph1;
    DirectedGraph<String, Integer> graph2;
    
    /**
     * Default constructor for test class DirectedGraphTest
     */
    public DirectedGraphTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
        graph1 = new DirectedGraph<Integer, Integer>();
        graph2 = new DirectedGraph<String, Integer>();
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }
    
    @Test
    public void testAddNode(){
        // test for Integers
        Map map = graph1.getMap();
        
        graph1.AddNode(1);
        assertTrue("could not add the first node correctly", map.get(1) != null);
        
        graph1.AddNode(3);
        assertTrue("could not add the node correctly", map.get(3) != null);
        assertTrue("could not add node correctly", graph1.AddNode(1) == false);
        
        // test for Strings
        map = graph2.getMap();
        
        graph2.AddNode("1");
        assertTrue("could not add the first node correctly", map.get("1") != null);
        
        graph2.AddNode("3");
        assertTrue("could not add the node correctly", map.get("3") != null);
        assertTrue("could not add node correctly", graph2.AddNode("1") == false);
        
    }
    
    @Test
    public void testAddEdge(){
        // test for Integers        
        graph1.AddNode(1);
        graph1.AddNode(2);
        graph1.AddNode(3);
        
        assertTrue("Could not add edge correctly", graph1.AddEdge(1, 4, 5) == false);
        assertTrue("Could not add edge correctly", graph1.AddEdge(1, 1, 5) == false);
        assertTrue("Could not add edge correctly", graph1.AddEdge(1, 2, 5) == true);
        assertTrue("Could not add edge correctly", graph1.AddEdge(1, 2, 3) == false);
        assertTrue("Could not add edge correctly", graph1.AddEdge(2, 1, 1) == true);
        
        // test for Strings       
        graph2.AddNode("1");
        graph2.AddNode("2");
        graph2.AddNode("3");
        
        assertTrue("Could not add edge correctly", graph2.AddEdge("1", "4", 5) == false);
        assertTrue("Could not add edge correctly", graph2.AddEdge("1", "1", 5) == false);
        assertTrue("Could not add edge correctly", graph2.AddEdge("1", "2", 5) == true);
        assertTrue("Could not add edge correctly", graph2.AddEdge("1", "2", 3) == false);
        assertTrue("Could not add edge correctly", graph2.AddEdge("2", "1", 1) == true);
    }
    
    @Test
    public void testClearAll(){
        // test for Integers  
        Map map = graph1.getMap();
        
        graph1.AddNode(1);
        graph1.AddNode(2);
        graph1.AddNode(3);
        
        graph1.clearAll();
        for(int i = 1; i < 4; i++){
            DirectedGraph.DirectedGraphNode tmp = (DirectedGraph.DirectedGraphNode)map.get(i);
            assertTrue("Could not clear correctly", tmp.getDist() == graph1.INFINITY);
            assertTrue("Could not clear correctly", tmp.getPrev() == null);
            assertTrue("Could not clear correctly", tmp.getScratch() == 0);
        }
        
        // test for Strings
        map = graph2.getMap();
        
        graph2.AddNode("1");
        graph2.AddNode("2");
        graph2.AddNode("3");
        
        graph2.clearAll();
        for(int i = 1; i < 4; i++){
            DirectedGraph.DirectedGraphNode tmp = (DirectedGraph.DirectedGraphNode)map.get(i + "");
            assertTrue("Could not clear correctly", tmp.getDist() == graph1.INFINITY);
            assertTrue("Could not clear correctly", tmp.getPrev() == null);
            assertTrue("Could not clear correctly", tmp.getScratch() == 0);
        }
    }
    
    @Test
    public void testToString(){
        // test for Integers
        Map map = graph1.getMap();
        
        graph1.AddNode(1);
        graph1.AddNode(2);
        graph1.AddNode(3);
        String str = "1\n2\n3\n";
        assertTrue("Could not convert to String correctly \n" +  graph1.toString(), str.equals(graph1.toString()));
        
         // test for Strings
        map = graph2.getMap();
        
        graph2.AddNode("1");
        graph2.AddNode("2");
        graph2.AddNode("3");
        str = "1\n2\n3\n";
        assertTrue("Could not convert to String correctly \n" +  graph1.toString(), str.equals(graph1.toString()));
    }
    
    @Test
    public void testReset(){
        // test for Integers
        graph1.AddNode(1);
        DirectedGraph.DirectedGraphNode node = graph1.getMap().get(1);
        node.reset();
        assertTrue("Could not reset node correctly", node.getIncoming().size() == 0);
        assertTrue("Could not reset node correctly", node.getOutgoing().size() == 0);
        assertTrue("Could not reset node correctly", node.getDist() == graph1.INFINITY);
        assertTrue("Could not reset node correctly", node.getPrev() == null);
        assertTrue("Could not reset node correctly", node.getScratch() == 0);
        
        // test for Integers
        graph2.AddNode("1");
        node = graph2.getMap().get("1");
        node.reset();
        assertTrue("Could not reset node correctly", node.getIncoming().size() == 0);
        assertTrue("Could not reset node correctly", node.getOutgoing().size() == 0);
        assertTrue("Could not reset node correctly", node.getDist() == graph2.INFINITY);
        assertTrue("Could not reset node correctly", node.getPrev() == null);
        assertTrue("Could not reset node correctly", node.getScratch() == 0);
    }
    
    @Test
    public void testOutgoingToString(){
        // for integers
        Map<Integer, DirectedGraph<Integer, Integer>.DirectedGraphNode<Integer, Integer>> map1 = graph1.getMap();
        
        graph1.AddNode(1);
        graph1.AddNode(2);
        graph1.AddNode(3);
        DirectedGraph<Integer, Integer>.DirectedGraphNode<Integer, Integer> node1 = map1.get(1);
        graph1.AddEdge(1, 2, 3);
        graph1.AddEdge(1, 3, 4);
        String str = "  --> 2(3)  --> 3(4)";
        
        assertTrue("outgoingToString does not work correctly", str.equals(node1.outgoingToString()));
        
        // for Strings
        Map<String, DirectedGraph<String, Integer>.DirectedGraphNode<String, Integer>> map2 =  graph2.getMap();
        
        graph2.AddNode("1");
        graph2.AddNode("2");
        graph2.AddNode("3");
        DirectedGraph<String, Integer>.DirectedGraphNode<String, Integer> node2 = map2.get("1");
        graph2.AddEdge("1", "2", 3);
        graph2.AddEdge("1", "3", 4);
        str = "  --> 2(3)  --> 3(4)";
        
        assertTrue("outgoingToString does not work correctly", str.equals(node2.outgoingToString()));
        
    }
    
    @Test
    public void testCompareTo(){
        
        Map<Integer, DirectedGraph<Integer, Integer>.DirectedGraphNode<Integer, Integer>> map = graph1.getMap();  
        DirectedGraph<Integer, Integer>.Path path1 = graph1.createPath(null, 3);
        DirectedGraph<Integer, Integer>.Path path2 = graph1.createPath(null, 1);
        DirectedGraph<Integer, Integer>.Path path3 = graph1.createPath(null, 3);
        assertTrue("Could not compare Path correctly", path1.compareTo(path2) == 1);
        assertTrue("Could not compare Path correctly", path1.compareTo(path3) == 0);
        assertTrue("Could not compare Path correctly", path2.compareTo(path3) == -1);
    }
    
    @Test
    public void testDijkstra(){
        // test for Integers
        Map<Integer, DirectedGraph<Integer, Integer>.DirectedGraphNode<Integer, Integer>> map1 = graph1.getMap();
        DirectedGraph<Integer, Integer>.DirectedGraphNode<Integer, Integer> node1 = map1.get(1);
        for(int i = 1; i < 10; i++){
            graph1.AddNode(i);
        }
        graph1.AddEdge(1, 2, 2);
        graph1.AddEdge(1, 3, 1);
        graph1.AddEdge(2, 7, 3);
        graph1.AddEdge(2, 3, 4);
        graph1.AddEdge(3, 4, 5);
        graph1.AddEdge(3, 7, 1);
        graph1.AddEdge(3, 6, 2);
        graph1.AddEdge(4, 5, 1);
        graph1.AddEdge(4, 7, 3);
        graph1.AddEdge(6, 7, 3);
        graph1.AddEdge(6, 5, 3);
        graph1.AddEdge(7, 5, 2);
        
        PriorityQueue<DirectedGraph<Integer, Integer>.Path> pq = graph1.dijkstra(1, 5); 
        assertTrue("Could not create correct PriorityQueue", pq.size() == 2);
        
        graph1.AddEdge(8, 3, 1);
        pq = graph1.dijkstra(8, 5); 
        assertTrue("Could not create correct PriorityQueue", pq.size() == 2);
        
        graph1.AddEdge(8, 9, 1);
        pq = graph1.dijkstra(8, 9); 
        // pq should be queue of path ending with 9(1), 6(3), 7(2), 4(6) nodes
        assertTrue("Could not create correct PriorityQueue " + pq.size(), pq.size() == 4);
        
        // test for Strings
        Map<String, DirectedGraph<String, Integer>.DirectedGraphNode<String, Integer>> map2 = graph2.getMap();
        DirectedGraph<String, Integer>.DirectedGraphNode<String, Integer> node2 = map2.get(1);
        for(int i = 1; i < 10; i++){
            graph2.AddNode(i + "");
        }
        graph2.AddEdge("1", "2", 2);
        graph2.AddEdge("1", "3", 1);
        graph2.AddEdge("2", "7", 3);
        graph2.AddEdge("2", "3", 4);
        graph2.AddEdge("3", "4", 5);
        graph2.AddEdge("3", "7", 1);
        graph2.AddEdge("3", "6", 2);
        graph2.AddEdge("4", "5", 1);
        graph2.AddEdge("4", "7", 3);
        graph2.AddEdge("6", "7", 3);
        graph2.AddEdge("6", "5", 3);
        graph2.AddEdge("7", "5", 2);
        
        PriorityQueue<DirectedGraph<String, Integer>.Path> pq1 = graph2.dijkstra("1", "5"); 
        assertTrue("Could not create correct PriorityQueue", pq1.size() == 2);
        
        graph2.AddEdge("8", "3", 1);
        pq1 = graph2.dijkstra("8", "5"); 
        assertTrue("Could not create correct PriorityQueue", pq1.size() == 2);
        
        graph2.AddEdge("8", "9", 1);
        pq1 = graph2.dijkstra("8", "9"); 
        // pq should be queue of path ending with 9(1), 6(3), 7(2), 4(6) nodes
        assertTrue("Could not create correct PriorityQueue " + pq1.size(), pq.size() == 4);
        
    }
    
    @Test
    public void testFindShortestPath(){
        // test for Integers
        Map<Integer, DirectedGraph<Integer, Integer>.DirectedGraphNode<Integer, Integer>> map1 = graph1.getMap();
        DirectedGraph<Integer, Integer>.DirectedGraphNode<Integer, Integer> node1 = map1.get(1);
        for(int i = 1; i < 10; i++){
            graph1.AddNode(i);
        }
        graph1.AddEdge(1, 2, 2);
        graph1.AddEdge(1, 3, 1);
        graph1.AddEdge(2, 7, 3);
        graph1.AddEdge(2, 3, 4);
        graph1.AddEdge(3, 4, 5);
        graph1.AddEdge(3, 7, 1);
        graph1.AddEdge(3, 6, 2);
        graph1.AddEdge(4, 5, 1);
        graph1.AddEdge(4, 7, 3);
        graph1.AddEdge(6, 7, 3);
        graph1.AddEdge(6, 5, 3);
        graph1.AddEdge(7, 5, 2);
        
        ArrayList<DirectedGraph.DirectedGraphNode> al = graph1.findShortestPath(1, 5); 
        assertTrue("Could not find shortest path", al.size() == 4);
        assertTrue("Could not find shortest path", (int)al.get(0).getKey() == 1);
        assertTrue("Could not find shortest path", (int)al.get(1).getKey() == 3);
        assertTrue("Could not find shortest path", (int)al.get(2).getKey() == 7);
        assertTrue("Could not find shortest path", (int)al.get(3).getKey() == 5);

        graph1.AddEdge(8, 3, 1);
        al = graph1.findShortestPath(8, 5); 
        assertTrue("Could not find shortest path", al.size() == 4);
        assertTrue("Could not find shortest path", (int)al.get(0).getKey() == 8);
        assertTrue("Could not find shortest path", (int)al.get(1).getKey() == 3);
        assertTrue("Could not find shortest path", (int)al.get(2).getKey() == 7);
        assertTrue("Could not find shortest path", (int)al.get(3).getKey() == 5);
        
        graph1.AddEdge(8, 9, 1);
        al = graph1.findShortestPath(8, 9); 
        assertTrue("Could not find shortest path " + al.size(), al.size() == 2);
        assertTrue("Could not find shortest path", (int)al.get(0).getKey() == 8);
        assertTrue("Could not find shortest path", (int)al.get(1).getKey() == 9);
        
        al = graph1.findShortestPath(1, 9);
        assertTrue("Could not find shortest path " + al.size(), al.size() == 0);
        
       // test for Integers
        Map<String, DirectedGraph<String, Integer>.DirectedGraphNode<String, Integer>> map2 = graph2.getMap();
        DirectedGraph<String, Integer>.DirectedGraphNode<String, Integer> node2 = map2.get(1);
        for(int i = 1; i < 10; i++){
            graph2.AddNode(i + "");
        }
        graph2.AddEdge("1", "2", 2);
        graph2.AddEdge("1", "3", 1);
        graph2.AddEdge("2", "7", 3);
        graph2.AddEdge("2", "3", 4);
        graph2.AddEdge("3", "4", 5);
        graph2.AddEdge("3", "7", 1);
        graph2.AddEdge("3", "6", 2);
        graph2.AddEdge("4", "5", 1);
        graph2.AddEdge("4", "7", 3);
        graph2.AddEdge("6", "7", 3);
        graph2.AddEdge("6", "5", 3);
        graph2.AddEdge("7", "5", 2);
        
        ArrayList<DirectedGraph.DirectedGraphNode> al1 = graph2.findShortestPath("1", "5"); 
        assertTrue("Could not find shortest path", al1.size() == 4);
        assertTrue("Could not find shortest path", al1.get(0).getKey().equals("1"));
        assertTrue("Could not find shortest path", al1.get(1).getKey().equals("3"));
        assertTrue("Could not find shortest path", al1.get(2).getKey().equals("7"));
        assertTrue("Could not find shortest path", al1.get(3).getKey().equals("5"));

        graph2.AddEdge("8", "3", 1);
        al1 = graph2.findShortestPath("8", "5"); 
        assertTrue("Could not find shortest path", al1.size() == 4);
        assertTrue("Could not find shortest path", al1.get(0).getKey().equals("8"));
        assertTrue("Could not find shortest path", al1.get(1).getKey().equals("3"));
        assertTrue("Could not find shortest path", al1.get(2).getKey().equals("7"));
        assertTrue("Could not find shortest path", al1.get(3).getKey().equals("5"));
        
        graph2.AddEdge("8", "9", 1);
        al1 = graph2.findShortestPath("8", "9"); 
        assertTrue("Could not find shortest path " + al1.size(), al1.size() == 2);
        assertTrue("Could not find shortest path", al1.get(0).getKey().equals("8"));
        assertTrue("Could not find shortest path", al1.get(1).getKey().equals("9"));
        
        al1 = graph2.findShortestPath("1", "9");
        assertTrue("Could not find shortest path " + al.size(), al.size() == 0);
    }
}
