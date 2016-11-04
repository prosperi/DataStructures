import java.util.PriorityQueue;

public class Test
{
    public static void main(String[] args){
        TestComparator tc = new TestComparator();
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(tc);
        pq.add(9);
        pq.add(8);
        pq.add(7);
        pq.add(6);    
        pq.add(-4);      
        pq.add(4);
        pq.add(3);
        pq.add(2);
        pq.add(1);
        pq.add(40);
        pq.add(30);
        pq.add(20);
        pq.add(-10);
        
        System.out.println(pq.peek());
    }

}
