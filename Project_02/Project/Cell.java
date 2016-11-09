public class Cell
{
    private World world;
    private Animal animal;
    private Plant plant;
    //z/////////
    private Mountain mountain;
    private int x, y;
    //z////////
    public Cell(World w) {
        this.world = w;
    }
    
    public Cell(Animal a) {
        this.animal = a;
    }
    
    public Cell(Plant p) {
        this.plant = p;
    }
    
    public Cell(Animal a, Plant p) {
        this.animal = a;
        this.plant = p;
    }
    
    //z////
    public Cell(World w, int x, int y) {
        this.world = w;
        this.x = x;
        this.y = y;
    }
    //z///
    
    /**
     * Gets bordering cell from given relative position
     * @param int r - r is the vertical position relative to the current cell, int c - c is the horizontal position relative to the current cell
     * @return Cell - a cell that is in the given position relative to the current cell, null if not found or non existent
     */
    public Cell getAdjacent(int r, int c) {
        //z//// Replaced try/catch with if -> continue
        if((r < -1 || r > 1) || (c < -1 || c > 1) || (r == 0 && c == 0)) {
            return null;
        }
        for(int i = 0; i < this.world.getHeight(); i++) {
            if(i + r < 0 || i + r >=  this.world.getHeight())
                continue;
            for(int j = 0; j < this.world.getWidth(); j++) {
                if(j + c < 0 || j + c >=  this.world.getWidth())
                    continue;
                if(this.world.get(i,j) == this) {
                    return this.world.get(i+r,j+c);
                }
                
            }
        }
        return null;
    }
    
    public Animal getAnimal() {
        return this.animal;
    }
    
    public void setAnimal(Animal a) {
        this.animal = a;
    }
    
    public Plant getPlant() {
        return this.plant;
    }
    
    public void setPlant(Plant p) {
        this.plant = p;
    }
    
    //z//////////////////////
    public void setMountain(Mountain mountain){
       this.mountain = mountain;
    }
    
    public Mountain getMountain(){
       return this.mountain;
    }
    
    public int getX(){
        return this.x;
    }
    
    public int getY(){
        return this.y;
    }
    //z/////////////////////
    
    
    public World getWorld() {
        return this.world;
    }
}
