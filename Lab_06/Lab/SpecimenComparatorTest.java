

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SpecimenComparatorTest
{
    SpecimenComparator comparator;
    Specimen sp1, sp2, sp3, sp4, sp5;
   
    public SpecimenComparatorTest()
    {
    }

    
    @Before
    public void setUp()
    {
        comparator = new SpecimenComparator();
        
        String type = "animal";
        String subgroup = "carnivore";
        String name = "lion";
        double age = 11;
        double birth = 3.1;
        sp1 = new Specimen(type, subgroup, name, age, birth);
        
        subgroup = "herbivore";
        name = "horse";
        sp2 = new Specimen(type, subgroup, name, 12, birth);
        
        subgroup = "herbivore";
        name = "horse";
        sp3 = new Specimen(type, subgroup, name, 11, birth);
        
        type = "plant";
        subgroup = "fruit";
        name = "peach";
        sp4 = new Specimen(type, subgroup, name, age, birth);
        sp5 = new Specimen(type, subgroup, name, age, birth);
    }

   
    @After
    public void tearDown()
    {
    }
    
    @Test
    public void testComapre(){
        assertTrue("Could not compare correctly the subgroups", comparator.compare(sp1, sp2) < 0);
        assertTrue("Could not compare correctly the type", comparator.compare(sp3, sp4) < 0);
        assertTrue("Could not compare correctly the age", comparator.compare(sp2, sp3) > 0);
        assertTrue("Could not compare correctly the name", comparator.compare(sp1, sp2) < 0);
        assertTrue("Could not compare correctly the specimen", comparator.compare(sp4, sp5) == 0);
    }
}
