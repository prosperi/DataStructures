import java.util.*;

public class Vegetable extends Plant
{
    public Vegetable(String n, String sym, List<String> s, double dm, double ds, double be, double me, double le, double ie, double pm, double ps, int dr, int mr, double th) {    
        super(n, sym, s, dm, ds, be, me, le, ie, pm, ps, dr, mr, th);
    }
    
    public Vegetable(Species parent) {    
        super(parent.name, parent.symbol, parent.energySources, parent.deathMedian, parent.deathStd, parent.birthEnergy, parent.maxEnergy, parent.livingEnergy, parent.initialEnergy, parent.popMedian, parent.popStd, parent.detectionRange, parent.movementRange, parent.threshold);
    }
}
