

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SpecimenTest
{
    Specimen specimen;
    
    public SpecimenTest()
    {
    }

    
    @Before
    public void setUp()
    {
        String type = "animal";
        String subgroup = "carnivore";
        String name = "lion";
        double age = 11;
        double birth = 3.1;
        specimen = new Specimen(type, subgroup, name, age, birth);
    }

   
    @After
    public void tearDown()
    {
    }
    
    @Test
    public void testSpecimen(){
        
        assertTrue("This should be animal", specimen.getType().compareTo("animal") == 0);
        assertTrue("This should be carnivore", specimen.getSubgroup().compareTo("carnivore") == 0);
        assertTrue("This should be lion", specimen.getName().compareTo("lion") == 0);
        assertTrue("This should be equal to 11", specimen.getAge() == 11);
        assertTrue("This should be equal to 3.1", specimen.getBirth() == 3.1);
        
        String str = "animal carnivore lion 11.0 3.1";
        assertTrue("This should be printing details of specimen " + specimen.toString(), str.compareTo(specimen.toString()) == 0);
        
    }
    
}
