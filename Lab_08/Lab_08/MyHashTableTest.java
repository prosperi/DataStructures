import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MyHashTableTest
{
    MyHashTable mht;
    Team team;
    /**
     * Default constructor for test class MyHashTableTest
     */
    public MyHashTableTest()
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
        mht = new MyHashTable(100);
        team =  new Team();
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
    public void testInsert(){
        for(int i = 0; i < 100; i++){
            boolean t = mht.insert(i + "", team);
            if(!t){
                System.out.println(i + " == " +  (i + " ").hashCode() % mht.size());
            }
        }
        
        for(int i = 0; i < 100; i++){
            boolean t = mht.insert(i + "", team);
            assertTrue("Could not insert correctly", t == false);
        }
        
        assertTrue("Could not insert correctly",  mht.insert("111", team) == true);
    }
    
    @Test
    public void testFind(){
       assertTrue("Cannot search correctly", mht.find("0") == null);
       for(int i = 0; i < 100; i++){
           mht.insert(i + "", team);
       }
        
       for(int i = 0; i < 100; i++){
           assertTrue("Cannot search correctly", mht.find(i + "") != null);
       } 
       
       assertTrue("Cannot search correctly", mht.find("111") == null);
    }
    
    @Test
    public void testRemove(){
        assertTrue("Cannot remove correctly", mht.remove("0") == null);
        for(int i = 0; i < 100; i++){
           mht.insert(i + "", team);
        }
        
        for(int i = 0; i < 100; i++){
           assertTrue("Cannot remove correctly", mht.remove(i + "") != null);
        } 
        
        assertTrue("Cannot remove correctly", mht.remove("0") == null);
        assertTrue("Cannot remove correctly", mht.remove("101") == null);
    }
    
    @Test
    public void testGetLoadFactor(){
        mht.insert("0", team);
        assertTrue("Cannot find correct Load Factor", mht.getLoadFactor() == 0.01);
        
        for(int i = 1; i < 100; i++){
           mht.insert(i + "", team);
        }
        
        double counter = 0;
        for(int i = 0; i < mht.size(); i++){
            if(mht.getBuckets()[i].size() > 0) counter++;
        }
        
        assertTrue("Cannot find correct Load Factor", mht.getLoadFactor() == counter / mht.size());
    }
    
    @Test
    public void testToString(){
        mht = new MyHashTable(5);
        String answer = "#0  Size: 0 | \n#1  Size: 0 | \n#2  Size: 0 | \n#3  Size: 0 | \n#4  Size: 0 | \n";
        assertTrue("Cannot print out correct String representation " + mht.toString(), answer.equals(mht.toString()));
        for(int i = 0; i < 5; i++){
           mht.insert(i + "", team);
        }
        
        answer = "#0  Size: 1 | 2 | \n#1  Size: 1 | 3 | \n#2  Size: 1 | 4 | \n#3  Size: 1 | 0 | \n#4  Size: 1 | 1 | \n";
        assertTrue("Cannot print out correct String representation " + mht.toString(), answer.equals(mht.toString()));
        
    }
}
