import java.util.*;
/** 
  * @desc This class provides functionality for animals
  * @author Zura Mestiashvili mestiasz@lafayette.edu
  * @version v1.0.0
*/
public abstract class Animal extends Species
{
    Random generator;
    boolean moved;
    
    public Animal(String n, String sym, List<String> s, double dm, double ds, double be, double me, double le, double ie, double pm, double ps, int dr, int mr, double th) {    
        super(n, sym, s, dm, ds, be, me, le, ie, pm, ps, dr, mr, th);
        generator = new Random(Simulation.SEED);
        // use moved not to move the same animal twice, when move == true we can change position, when it is false, this
        // means this animal already mvoed in this step
        moved = false;
    }
    
    /**
     * @desc This method provides and easy way to enforce the order of behaviors the species can make
     * Each behavior method returns true if it works, which in turn stops any further behaviors from happening that turn
     */
    public void activity() {
        if(die()) {
            //System.out.println("Animal Die");
            return;
        }
        if(birth()) {
            //System.out.println("Animal Birth");
            return;
        }
        if(eat()) {
            //System.out.println("Animal Eat");
            return;
        }
        if(!moved && move()) {
            //System.out.println("Animal Move");
            moved = true;
            return;
        }
    }
    
    /**
     * @desc Checks if the species doesn't have enough energy or is too old
     * The species is added to the list of deaths at the corresponding turn and species indices
     * @return boolean - returns true if the animal dies, false otherwise
     */
    public boolean die() {
        //Check for energy
        this.energy -= this.livingEnergy;
        if(this.energy <= 0) {
            int index = species.indexOf(this.name);
            try {
                deaths.get(this.getCell().getWorld().getSteps()).set(index, deaths.get(this.getCell().getWorld().getSteps()).get(index) + 1);
            } catch(Exception e) {}
            this.getCell().setAnimal(null);
            this.setCell(null);
            return true;
        }
        this.age++;
        //Check for age
        int deathAge = (int)(this.deathMedian + (this.deathStd * this.generator.nextGaussian()));
        if(age >= deathAge) {
            int index = species.indexOf(this.name);
            try {
                deaths.get(this.getCell().getWorld().getSteps()).set(index, deaths.get(this.getCell().getWorld().getSteps()).get(index) + 1);
            } catch(Exception e) {}
            this.getCell().setAnimal(null);
            this.setCell(null);
            return true;
        }
        return false;
    }
    
    /**
     * @desc Checks if the species has enough energy and room nearby to give birth
     * The child is added to the list of births at the corresponding turn and species indices
     * @return boolean - true if animal gives birth, false otherwise
     */
    public boolean birth() {
        if(this.energy >= this.birthEnergy) {
            //This loops through the adjacent cells, denoted as ranging from -1 to 1 in the horizontal and vertical directions in relation to the current cell
            for(int i = -1; i <= 1; i++) {
                for(int j = -1; j <= 1; j++) {
                    Cell birthCell = this.getCell().getAdjacent(i,j); //Get adjacent uses the -1 to 1 range to find the cell
                    if(i == 0 && j == 0) {}
                    //z////////// Modified if statement by checking mountains too
                    else if(birthCell != null && birthCell.getAnimal() == null && birthCell.getMountain() == null) {
                        Cell place = this.getCell().getAdjacent(i,j);
                        double parentEnergy = this.getEnergy()/2.0; //Important to halve the parent's energy to give to the child
                        this.setEnergy(parentEnergy);
                        Species child = null;
                        if(this instanceof Carnivore) {
                            child = new Carnivore(this);
                        } else if(this instanceof Herbivore) {
                            child = new Herbivore(this);
                        } else if(this instanceof Omnivore) {
                            child = new Omnivore(this);
                        }
                        child.setEnergy(parentEnergy);
                        place.setAnimal((Animal)child);
                        child.setCell(place);
                        int index = species.indexOf(this.name);
                        try {
                            births.get(this.getCell().getWorld().getSteps()).set(index, births.get(this.getCell().getWorld().getSteps()).get(index) + 1);
                        } catch(Exception e) {}
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    public abstract boolean eat();
    
    /**
     * @desc Looks at adjacent cells and checks if any animal it can eat is there
     * If it finds a prey, it is removed from the board and added to the death list
     * @return boolean - true if an acceptable animal is found and eaten, false otherwise
     */
    public boolean eatAnimal() {
        for(int i = -1; i <= 1; i++) {
            for(int j = -1; j <= 1; j++) {
                if(i == 0 && j == 0) {}
                else {
                    Cell preyCell = this.getCell().getAdjacent(i,j);
                    if(preyCell != null) {
                        Animal prey = preyCell.getAnimal();
                        if(prey != null && this.energySources.contains(prey.getName())) {
                            this.setEnergy(this.getEnergy() + prey.getEnergy());
                            if(this.getEnergy() > this.getMaxEnergy()) {
                                this.setEnergy(this.getMaxEnergy());
                            }
                            int curSteps = prey.getCell().getWorld().getSteps();
                            
                            ArrayList<ArrayList<Integer>> deathAL = Species.getDeaths();
                            int index = Species.getSpecies().indexOf(prey.getName());
                            
                            //System.out.println("Death by Eating");
                            try {
                                deathAL.get(curSteps).set(index, deathAL.get(curSteps).get(index) + 1);
                            } catch(Exception e) {}
                            prey.getCell().setAnimal(null);
                            prey.setCell(null);
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    
    /**
     * Looks at current cell and checks if any plant it can eat is there
     * @desc If it finds a prey, it is removed from the board and added to the death list
     * @return boolean - true if an acceptable plant is found and eaten, false otherwise
     */
    public boolean eatPlant() {
       Cell preyCell = this.getCell();
        if(preyCell != null) {
            Plant prey = preyCell.getPlant();
            if(prey != null && this.energySources.contains(prey.getName())) {
                this.setEnergy(this.getEnergy() + prey.getEnergy());
                int curSteps = prey.getCell().getWorld().getSteps();
                
                ArrayList<ArrayList<Integer>> deathAL = Species.getDeaths();
                int index = Species.getSpecies().indexOf(prey.getName());
                
                //System.out.println("Death by Eating");
                try {
                    deathAL.get(curSteps).set(index, deathAL.get(curSteps).get(index) + 1);
                } catch(Exception e) {}
                prey.getCell().setPlant(null);
                prey.setCell(null);
                return true;
            }
        }
        return false;
    }
    
    /**
     * @desc this represents the movement algorithm for the animal
     * at first checks if animal needs to find some food, if so than
     * sorts possible cells according to the food place, if not moves randomly
     * @return boolean - true if the animal moves, false otherwise
     */
    public boolean move(){
        // we use TreeSet to keep all the path animal can take
        // TreeSet itself is a container of ArrayLists, which keep 
        // the whole path which is unchecked and is based on the movement range
        // and the checked path which is based on detection range
        AbstractCollection<ArrayList<Path>> radar;
        World world = this.getCell().getWorld();
        
        // if animal needs food use Hungry comparator that sorts according to food
        // if not use comparatos that puts pathes without predators on the first place
        if(this.getEnergy() > this.threshold){
            radar = new TreeSet<ArrayList<Path>>(new PathComparator());
        }else{
            radar = new PriorityQueue<ArrayList<Path>>(new HungryComparator());
        }
        
        int i = this.getCell().getX();
        int j = this.getCell().getY();
        ArrayList<Cell> cellsTmp;
        // in roder to draw all the path from the current animal to every possible cell, we take the cells that are farthest from animal
        // and are in movement range. At first we find these points by following algorithm:
        int minI = (i - movementRange < 0 ) ? 0 : i - movementRange; 
        int maxI = (i + movementRange >= this.getCell().getWorld().getHeight() ) ? this.getCell().getWorld().getHeight() - 1 : i + movementRange; 
        int minJ = (j - movementRange < 0 ) ? 0 : j - movementRange; 
        int maxJ = (j + movementRange >= this.getCell().getWorld().getWidth() ) ? this.getCell().getWorld().getWidth() - 1 : j + movementRange; 
        
        // build path to each of these cells
        cellFinder(radar, minI, maxI, minJ, maxJ, i, j);

        // Time to move, if energy is above threshold move randomly, if not get first 
        // element from treeset and move to the cell where food can be found
        ArrayList<Path> tmpArr = new ArrayList<Path>(); 
        if(radar.size() != 0 && radar instanceof PriorityQueue){
            tmpArr = (ArrayList<Path>)((PriorityQueue)radar).peek();
        }else if(radar.size() != 0 && radar instanceof TreeSet){
            tmpArr = (ArrayList<Path>)((TreeSet)radar).first();
        }
        if(radar.size() != 0 && tmpArr.get(0).size() != 0){
            if(this.getEnergy() > this.threshold){
                Random rnd = new Random(1);
                int cellIndex = rnd.nextInt(tmpArr.get(0).size());
                Cell tmp = tmpArr.get(0).getCell(cellIndex);
                changeHome(tmp);
            }else{
                Path tmpChecked = tmpArr.get(1);
                if(tmpChecked.getPlantOnTheWay() == true){
                    Cell tmp = tmpArr.get(1).getPlantCell();
                    changeHome(tmp);
                }else{
                    Cell tmp = tmpArr.get(1).getLast();
                    changeHome(tmp);
                }
            }
            return true;
        }
        
        return false;
    }
    
    /**
     * @desc this method finds the farthest cells and builds 
     * path for them, than adds to radar
     * @params AbstractCollection<ArrayList<Path>> radar - radar
     * @params int minI - X coordinate of the farthest cell vertically(up)
     * @params int maxI - X coordinate of the farthest cell vertically(bottom)
     * @params int minJ - Y coordinate of the farthest cell vertically(left)
     * @params int maxJ - Y coordinate of the farthest cell vertically(right)
     * @params int minI - X coordinate of the current cell
     * @params int maxI - Y coordinate of the current cell
     */
    public void cellFinder(AbstractCollection<ArrayList<Path>> radar, int minI, int maxI, int minJ, int maxJ, int i, int j){
        // build path to each of these cells
        for(int k = minJ; k <= maxJ; k++){
            if(k == minJ || k == maxJ){
                // check if animal is in the left or right column of the rectangle we are checking if so take only those 
                // points that are at maxI and minI positions
                if(minJ == j){
                    radar.add(drawPath(minI, j));
                    radar.add(drawPath(maxI, j));
                }else if(maxJ == j){
                    radar.add(drawPath(minI, j));
                    radar.add(drawPath(maxI, j));
                }else{
                    for(int l = minI; l <= maxI; l++){
                        radar.add(drawPath(l, k));
                    }
                }
            }else{
                // check if animal is in the top or bottom row of the rectangle we are checking if so take only those 
                // points that are at maxJ and minJ positions
                if(minI != i){
                    radar.add(drawPath(minI, k));
                }
                if(maxI != i){
                    radar.add(drawPath(maxI, k));
                }
            }
        }
    }
    
    /**
     * @desc this method changes the home of a cell
     * @params Cell tmp - new home cell
     */
    public void changeHome(Cell tmp){
        tmp.setAnimal(this);
        this.getCell().setAnimal(null);
        this.setCell(tmp);
    }
    
    /**
     * @desc drawPath between animal and the cell, that creates a new Path
     * @params int x1 - x coordinate of that cell
     * @params int y1 - y coordinate of that cell
     * @return boolean - true if the animal moves, false otherwise
     */
    public ArrayList<Path> drawPath(int x1, int y1){
        ArrayList<Path> pathArr = new ArrayList<Path>();
        Path path_01 = new Path();
        Path path_02 = new Path();
        
        Cell tmp = this.getCell();
        World world = cell.getWorld();
        
        // Apply Bresenham line algorithm once more
        int x0 = cell.getX();
        int y0 = cell.getY();
        int dx = Math.abs(x1 - x0), 
            sx = x0 < x1 ? 1 : -1;
        int dy = Math.abs(y1 - y0), 
            sy = y0 < y1 ? 1 : -1;
        int err = (dx > dy ? dx : -dy)/2;
        
        boolean t = true;
        while(t){
            switch (checkPathCell(x0, y0)){
               // so the path edns with predator 
               case 0:
                addToPath(path_01, path_02, 0, x0, y0, false, false, world);
                t = false;
                break;
               // so the path ends with food that is animal
               case 1:
                addToPath(path_01, path_02, 1, x0, y0, true, false, world);
                t = false;
                break;
               // so the path ends with plant that is food
               case 2:
                addToPath(path_01, path_02, 2, x0, y0, true, true, world);
                break;
               // so the path ends with mountain or animal that is not food
               case 3:
                addToPath(path_01, path_02, 3, x0, y0, false, false, world);
                t = false;
                break;
               // path does not end and it's free, their is not anything on 
               // the path(except plants that are not among energy sources)
               case 4:
                addToPath(path_01, path_02, 4, x0, y0, true, false, world);
                break;
            }
            
            if(x0 == x1 && y0 == y1) break;
            int e2 = err;
            if(e2 > -dx) {
              err -= dy;
              x0 += sx;
            }
            if(e2 < dy) {
              err += dx;
              y0 += sy;
            }
        }
        
        pathArr.add(path_01);
        pathArr.add(path_02);
        return pathArr;
    }
    
     /**
     * @desc helper method for drawPath() method
     * this method assigns deadEnd value to path and adds 
     * new cells to Path
     * @params Path path_01 - whole path
     * @params Path path_02 - checked Path
     * @params int i - dead End value
     * @params int x0 - X coordinate for new Cell in Path
     * @params int y0 - Y coordinate for new Cell in Path
     * @params boolean add - if this is true the cell becomes part of the path
     * @params boolean plant - if this is true the cell is stored as place where food can be found
     * @params boolean World world - the world
     */
    public void addToPath(Path path_01, Path path_02, int i, int x0, int y0, boolean add, boolean plant, World world){
        if(path_01.size() < movementRange){
            if(add)  path_01.add(world.get(x0, y0));
            if(plant){
                path_01.setPlantOnTheWay(true);
                path_01.setPlantCell(world.get(x0, y0));
            }
            path_01.setDeadEnd(i);
        }
        if(path_02.size() < detectionRange){
            if(add)  path_02.add(world.get(x0, y0));
            if(plant){
                path_02.setPlantOnTheWay(true);
                path_02.setPlantCell(world.get(x0, y0));
            }
            path_02.setDeadEnd(i);
        }
    }
    
    /**
     * @desc check the Path, and find if it ends already
     * @params int k - X coordinate of the cell
     * @params int l - Y coordinate of that cell
     * @return int checkPathCell - 0,1,2,3 or 4 according to the specimen living in the cell
     */
    public int checkPathCell(int k, int l){
        World world = this.getCell().getWorld();
        // lets check if there is some animal that's not among animals energy sources or if there is mountain
        // in which case our path stops there
        Mountain mountain = world.get(k, l).getMountain();
        Animal animal = world.get(k, l).getAnimal();
        Plant plant = world.get(k, l).getPlant();
        
        if(animal == this)
            return -1;
        
        // Path for which dead end equals to 0 means there is predator in the end
        if(animal != null && animal.getEnergySources().contains(this.getName())){
            return 0;
        }
        // check if there is some food in the cell, we use this
        // not to check further path later, as we already found food
        if(animal != null && this.getEnergySources().contains(animal.getName())){
            return 1;
        }
        
        if(plant != null && this.getEnergySources().contains(plant.getName())){
            return 2;
        }
        // if there is just mountain or animal that's not among energy sources go this way
        if(mountain != null || animal != null)
            return 3;
            
        return 4;
    }

    /**
     * @desc used for test purposes, prints out the radar
     * @params TreeSet<ArrayList<Path>> radar- radar
     */
    public void printRadar(TreeSet<ArrayList<Path>> radar){
        Iterator iterator = radar.iterator();
        while(iterator.hasNext()){
            ArrayList<Path> tmp = (ArrayList<Path>)iterator.next();
            System.out.println(this.getCell().getX() + " " + this.getCell().getY() + "   " + tmp.get(0) + " deadEnd: " + tmp.get(0).getDeadEnd() );
        }
    }
}