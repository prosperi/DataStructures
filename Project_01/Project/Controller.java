import java.util.*;

/** 
  * @desc this class is designed for creating controller
  * for each specie
  * @author Zura Mestiashvili mestiasz@lafayette.edu
  * @version v1.0.0
*/
public class Controller{
    
    private String name;
    private int prevBirth;
    private int prevDeath;
    private int death;
    private int birth;
    private int birthFifty;
    private int deathFifty;
    private int initialPopulation;
    private ArrayList<Specimen> specimen;
    private ArrayList<Specimen> deadSpecimen;
   
    public Controller(String name){
        this.name = name;
        this.initialPopulation = 0;
        specimen = new ArrayList<Specimen>();
        deadSpecimen = new ArrayList<Specimen>();
        birth = 0;
        death = 0;
        prevBirth = 0;
        prevDeath = 0;
        birthFifty = 0;
        deathFifty = 0;
    }
    
    /**
      * @desc this method adds newly created specimen to its
      * controller, and at the same time increases th number of
      * birth.
      * @param Specimen sp - specimen that should be added
    */
    public void addSpecimen(Specimen sp){
        specimen.add(sp);
        birth++;
    }
   
   /**
      * @desc this method removes dead specimen from specimen's
      * list and adds to deadSpecimen's list, in addition
      * increases the number of death;
      * @param Specimen sp - specimen that should be removed
    */
    
    public void moveToDead(Specimen sp){
        specimen.remove(sp);
        deadSpecimen.add(sp);
        death++;
    }
    
    
    /**
      * @desc this method returns median Energy for specie
      * for current moment.
      * @return double  - returnes median energy
    */
    public double getMedianEnergy(){
        // Sort by Energy
        Collections.sort(specimen, new Comparator<Specimen>(){
            @Override
            public int compare(Specimen foo, Specimen boo){
                return (int)(boo.getEnergy() - foo.getEnergy());
            }
        });
       
        if(specimen.size() != 0){
            if(specimen.size()%2 == 1 )
                return specimen.get(specimen.size()/2).getEnergy();
            else 
                return (specimen.get(specimen.size()/2).getEnergy() + specimen.get(specimen.size()/2-1).getEnergy())/2;
        }else{
            return 0;
        }
    }
    
    /**
      * @desc this method returns median Age for specie
      * for current moment.
      * @return double  - returns median age
    */    
    public double getMedianAge(){
        // Sort by Age
        Collections.sort(specimen, new Comparator<Specimen>(){
            @Override
            public int compare(Specimen foo, Specimen boo){
                return (int)(boo.getAge() - foo.getAge());
            }
        });
        
        if(specimen.size() != 0){
            if(specimen.size()%2 == 1)
                return specimen.get(specimen.size()/2).getAge();
            else 
                return (specimen.get(specimen.size()/2).getAge() + specimen.get(specimen.size()/2-1).getAge())/2;
        }else{
            return 0;
        }
    }
    
    /**
      * @desc this method is for calculating birth change
      * during last 50 steps
      * @return double  - returns birth change during last 50 steps
    */  
     public int getBirthFifty(){
        int tmp = birth - initialPopulation - birthFifty;
        birthFifty = birth - initialPopulation;
        return tmp;
    }
    
    /**
      * @desc this method is for calculating death change
      * during last 50 steps
      * @return double  - returns death change during last 50 steps
    */  
    public int getDeathFifty(){
        int tmp = death -  deathFifty;
        deathFifty = death;
        return tmp;
    }
    
    /**
      * @desc this method is for calculating birth change
      * during previous step
      * @return double  - returns birth change during previous step
    */  
    public int getBirthChange(){
        int tmp = birth - initialPopulation - prevBirth;
        prevBirth = birth - initialPopulation;
        return tmp;
    }
    
    /**
      * @desc this method is for calculating death change
      * during previous step
      * @return double  - returns death change during previous steps
    */  
    public int getDeathChange(){
        int tmp = death -  prevDeath;
        prevDeath = death;
        return tmp;
    }
    
    /**
      * @desc get total energy of that type species
      * @return double  - returns total energy
    */  
    public double getEnergy(){
        double tmp = 0;
        for(int i = 0; i < specimen.size(); i++){
            tmp += specimen.get(i).getEnergy();
        }
        return tmp;
    }
    
    /**
      * @desc this is getter for specie's name
      * @return String name  - returns name
    */    
    public String getName(){
        return name;
    }
    
    /**
      * @desc this is getter for specie's birth rate
      * @return int birth  - returns birth rate
    */  
    public int getBirth(){
        return birth - initialPopulation;
    }
    
    /**
      * @desc this is getter for specie's death rate
      * @return int death  - returns death rate
    */  
    public int getDeath(){
        return death;
    }
    
    /**
      * @desc this is getter for specie's current population
      * @return String name  - size of specimen list
    */  
    public int getPopulation(){
        return specimen.size();
    }
    
    /**
      * @desc this is getter for specie's InitialPopulation
      * @return int - initial population
    */  
    public int getInitialPopulation(){
        return initialPopulation;
    }
    
    /**
      * @desc this is method for increasing initial population by 1
    */
    public void increaseInitialPopulation(){
        initialPopulation++;
    }
    
    
}

