
public class Tourist{
    
    private TouristMap map;
    private TourTile currentTile;
    private Tour tour;
    private boolean moved;
    private int radius;
    private double effect;
    
    public Tourist(TouristMap map, Tour tour, int radius, double effect){
        this.map = map;
        this.currentTile = tour.getStartingTile();
        this.tour = tour;
        this.moved = true;
        this.radius = radius;
        this.effect = effect;
    }
    
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
    
    public void influenceAnimals(){
        World world = map.getWorld();
        int width = world.getWidth();
        
        int minI = (currentTile.getX() - radius < 0) ? 0 : currentTile.getX() - radius;
        int maxI = (currentTile.getX() + radius >  world.getHeight()) ?  world.getHeight() : currentTile.getX() + radius;

        int minJ = (currentTile.getY() - radius < 0) ? 0 : currentTile.getY() - radius;
        int maxJ = (currentTile.getY() + radius >  world.getHeight()) ?  world.getWidth() : currentTile.getY() + radius;
        
        for(int i = minI; i < maxI; i++){
            for(int j = minJ; j < maxJ; j++){
                Animal animal = world.get(i, j).getAnimal();
                if(animal != null){
                    animal.setEnergy(animal.getEnergy() - effect);
                }
            }
        }
    }
    
    public TourTile getCurrentTile(){
        return this.currentTile;
    }
    
     public void setCurrentTile(TourTile tile){
        this.currentTile = tile;
    }
    
    public Tour getTour(){
        return this.tour;
    }
    
    public TouristMap getMap(){
        return this.map;
    }
    
    public boolean getMoved(){
        return this.moved;
    }   
    
    public void setMoved(boolean t){
        moved = t;
    }
    
    public char getRepresentation(){
        return 'T';
    }
    
    public int getRadius(){
        return this.radius;
    }
    
    public double getEffect(){
        return this.effect;
    }
}
