import java.util.*;

public class Carnivore extends Animal
{
    public Carnivore(String n, String sym, List<String> s, double dm, double ds, double be, double me, double le, double ie, double pm, double ps, int dr, int mr, double th) {    
        super(n, sym, s, dm, ds, be, me, le, ie, pm, ps, dr, mr, th);     
    }
    
    public Carnivore(Animal parent){
        super(parent.name, parent.symbol, parent.energySources, parent.deathMedian, parent.deathStd, parent.birthEnergy, parent.maxEnergy, parent.livingEnergy, parent.initialEnergy, parent.popMedian, parent.popStd, parent.detectionRange, parent.movementRange, parent.threshold);
    }

    /**
     * Calls methods from Animal class to try to eat
     * @return boolean - true if the animal eats, false otherwise
     */
    public boolean eat() {
        return this.eatAnimal();
    }
}