/** 
  * @desc This class represents a tourist who can move
  * throught the tour. Tuorist takes one step per unit time.
  * Tourist influences animals in the influence range and 
  * decreases their energy and moves only if next place has 
  * free space left.
  * @author Zura Mestiashvili mestiasz@lafayette.edu
  * @version v1.0.0
*/

public class Tourist{
    
    private TouristMap map;
    private TourTile currentTile;
    private Tour tour;
    private boolean moved;
    private int radius;
    private double effect;
    
    public Tourist(TouristMap map, Tour tour){
        this.map = map;
        this.currentTile = tour.getStartingTile();
        this.tour = tour;
        this.moved = true;
        this.radius = tour.getRadius();
        this.effect = tour.getEffect();
    }
    
    /**
     * @desc move tourist to the provided tile(node) if there is free space left
     * @params TourTile tile - tile that tourist is going to move to
     * @return boolean - true if successfully moved, otherwise false
    */
    public boolean move(TourTile tile){
        if(!moved && tile.getTourists().size() < tour.getCapacity()){
            currentTile.getTourists().remove(this);
            currentTile = tile;
            currentTile.getTourists().add(this);
            moved = true;
            return true;
        }
        return false;
    }
    
    /**
     * @desc find if there are some animals in range and influence them
    */
    public void influenceAnimals(){
        World world = map.getWorld();
        int width = world.getWidth();
        
        int minI = (currentTile.getX() - radius < 0) ? 0 : currentTile.getX() - radius;
        int maxI = (currentTile.getX() + radius >=  world.getHeight()) ?  world.getHeight() - 1 : currentTile.getX() + radius;

        int minJ = (currentTile.getY() - radius < 0) ? 0 : currentTile.getY() - radius;
        int maxJ = (currentTile.getY() + radius >=  world.getHeight()) ?  world.getWidth() - 1 : currentTile.getY() + radius;
        
        for(int i = minI; i <= maxI; i++){
            for(int j = minJ; j <= maxJ; j++){
                Animal animal = world.get(i, j).getAnimal();
                if(animal != null && !animal.getInfluenced()){
                    animal.setEnergy(animal.getEnergy() - effect);
                    animal.setInfluenced(true);
                }
            }
        }
        
    }
    
    /**
     * @desc get the current tile(node) the tourist is occupying
     * @return TourTile - tile that is occupied
    */
    public TourTile getCurrentTile(){
        return this.currentTile;
    }
    
    /**
     * @desc change current place
     * @params TourTile tile - tile that is to be assigned to the tourist
    */
    public void setCurrentTile(TourTile tile){
        this.currentTile = tile;
    }
    
    /**
     * @desc get the tour the tourist is taking
     * @return Tour tour - current tour
    */
    public Tour getTour(){
        return this.tour;
    }
    
    /**
     * @desc get the tourist map
     * @return TouristMap map - tourist map
    */
    public TouristMap getMap(){
        return this.map;
    }
    
    /**
     * @desc find if tourist already changed place in the time unit
     * @return boolean moved - true if already moved
    */
    public boolean getMoved(){
        return this.moved;
    }   
    
    /**
     * @desc change moved variable value
     * @params boolean - new value for moved
    */
    public void setMoved(boolean t){
        moved = t;
    }
    
    /**
     * @desc get representation of a tourist
     * @char symbol for tourist
    */
    public char getRepresentation(){
        return 'T';
    }
    
    /**
     * @desc get the radius of influence
     * @return int  - radius
    */
    public int getRadius(){
        return this.radius;
    }
    
    /**
     * @desc get the effect value of the tourist
     * @return double - effect
    */
    public double getEffect(){
        return this.effect;
    }
}
