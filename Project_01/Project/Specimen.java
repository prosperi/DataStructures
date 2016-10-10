import java.util.ArrayList;

/**
 * Zura Mestiashvili
 */
public class Specimen{
   private String name;
   private String type;
   private char symbol;
   private ArrayList<String> energySources;
   private ArrayList<Double> stats;
   private double birthEnergy;
   private double maxEnergy;
   private double livingEnergy;
   private ArrayList<Double> initialStats;
   private int x;
   private int y;
   private double energy;
                                
   public Specimen(String name, String type, char symbol, ArrayList<String> energySources,
                   ArrayList<Double> initialStats, ArrayList<Double> stats, double birthEnergy, double maxEnergy,
                   double livingEnergy, int[] position){
      // initialize class variables                 
      this.name = name;
      this.type = type;
      this.symbol = symbol;
      this.energySources = energySources;
      this.stats = stats;
      this.birthEnergy = birthEnergy;
      this.maxEnergy = maxEnergy;
      this.livingEnergy = livingEnergy;
      this.initialStats = initialStats;
      this.x = position[0];
      this.y = position[1];
      this.energy = initialStats.get(2);
   }
   
   /////  Setters and Getters as we want our data to be private, though we will not need Setters as those properties
   ////   are defined while the simulation starts, and do not change later ????? - think about this more
   
   /// Getters
   public String getName(){
       return name;
   }
   
   public String getType(){
       return type;
   }
   
   public char getSymbol(){
       return symbol;
   }
   
   public ArrayList<String> getEnergySources(){
       return energySources;
   }
   
   public ArrayList<Double> getStats(){
       return stats;
   }
   
   public double getBirthEnergy(){
       return birthEnergy;
   }
   
   public double getMaxEnergy(){
       return maxEnergy;
   }
    
   public double getLivingEnergy(){
       return livingEnergy;
   }
   
   public ArrayList<Double> getInitialStats(){
       return initialStats;
   }
   
   public int getX(){
       return x;
   }
   
   public int getY(){
       return y;
   }
   
   public double getEnergy(){
       return energy;
   }
   
   public void setX(int val){
       x = val;
   }
   
   public void setY(int val){
       y = val;
   }
   
   public void setEnergy(double val){
       energy = val;
   }
   
}
