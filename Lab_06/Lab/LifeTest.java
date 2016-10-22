import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class LifeTest{
    Specimen sp1, sp2, sp3, sp4, sp5;
    Life life;
    ArrayList<Specimen> species;
    public LifeTest()
    {
    }

   
    @Before
    public void setUp()
    {
        life = new Life();
        
        String type = "animal";
        String subgroup = "carnivore";
        String name = "wolf";
        double age = 10.2;
        double birth = 3.9;
        sp1 = new Specimen(type, subgroup, name, age, birth);
        
        subgroup = "herbivore";
        name = "deer";
        age = 10.4;
        birth = 3.7;
        sp2 = new Specimen(type, subgroup, name, age, birth);
        
        subgroup = "omnivore";
        name = "cow";
        age = 11.0;
        birth = 7.0;
        sp3 = new Specimen(type, subgroup, name, age, birth);
        
        type = "plant";
        subgroup = "fruit";
        name = "peach";
        age = 15.7;
        birth = 7.9;
        sp4 = new Specimen(type, subgroup, name, age, birth);
        
        subgroup = "grain";
        name = "wheat";
        age = 2.1;
        birth = 101.1;
        sp5 = new Specimen(type, subgroup, name, age, birth);
        
        species = new ArrayList<Specimen>();
        species.add(sp1);
        species.add(sp2);
        species.add(sp3);
        species.add(sp4);
        species.add(sp5);
        
        life.construct();
    }

    @After
    public void tearDown()
    {
    }
    
    @Test
    public void testConstruct(){
        Iterator iterator = life.getSortedSpecies().iterator();
        int i = 0;
        while(iterator.hasNext()){
            String tmp_01 = species.get(i).toString();
            String tmp_02 = iterator.next().toString();
            assertTrue("Did not add/sort correctly", tmp_01.compareTo(tmp_02) == 0 );
            i++;
        }
    }
    
    @Test
    public void testPrint(){
        try{
            Scanner sc = new Scanner(new File("data.txt"));
            int i = 0;
            while(sc.hasNext()){
                String tmp_01 = sc.nextLine();
                String tmp_02 = species.get(i).toString();
                assertTrue("Could not print correctly", tmp_01.compareTo(tmp_02) == 0);
                i++;
            }
            
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
    }
    
}
