import java.util.*;

public abstract class Animal extends Species
{
    Random generator;
    boolean moved;
    
    public Animal(String n, String sym, List<String> s, double dm, double ds, double be, double me, double le, double ie, double pm, double ps) {    
        super(n, sym, s, dm, ds, be, me, le, ie, pm, ps);
        generator = new Random(Simulation.SEED);
        moved = false;
    }
    
    /**
     * This method provides and easy way to enforce the order of behaviors the species can make
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
     * Checks if the species doesn't have enough energy or is too old
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
     * Checks if the species has enough energy and room nearby to give birth
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
     * Looks at adjacent cells and checks if any animal it can eat is there
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
     * If it finds a prey, it is removed from the board and added to the death list
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
     * If all else fails, the animal tries to move to an adjacent space
     * @return boolean - true if the animal moves, false otherwise
     */
    public boolean move() {
        /*for(int i = 0; i < 20; i++) {
            Cell temp = this.getCell().getAdjacent(generator.nextInt(3)-1,generator.nextInt(3)-1);
            //z/////// Modified if statement by checking for mountains too
            if(temp != null && temp.getAnimal() == null && temp.getMountain() == null) {
                temp.setAnimal(this);
                this.getCell().setAnimal(null);
                this.setCell(temp);
                return true;
            }
        }*/
        
        //z///////////////
        CellComparator cellComparator = new CellComparator();
        // used when the specimen is looking for food
        PriorityQueue<ArrayList<Cell>> cellsQueue = new PriorityQueue<ArrayList<Cell>>(cellComparator);
        // used when the specimen do not need to look for food
        ArrayList<Cell> cellsArr = new ArrayList<Cell>();
        
        int range = 2;
        int i = this.getCell().getX();
        int j = this.getCell().getY();
        ArrayList<Cell> cellsTmp;
               
        for(int k = i - range; k <= i + range; k++){
            if(k < 0 || k >= this.getCell().getWorld().getHeight())
                    continue;
            for(int l = j - range; l <= j + range;  l++){
                // check if the cell is out of the board or if it is the same cell 
                // from which we are trying to move out 
                if(l < 0 || l >= this.getCell().getWorld().getWidth() || this.getCell().getWorld().get(k, l) == this.getCell())
                    continue;
                if(!checkPossibleHome(k, l)) 
                    continue;
                
                cellsTmp = new ArrayList<Cell>();
                cellsTmp.add(this.getCell());
                cellsTmp.add(this.getCell().getWorld().get(k, l));
                // yeah, you wrote this
                if(true)
                    cellsQueue.add(cellsTmp);
                else
                    cellsArr.add(cellsTmp.get(0));
                
            }
        }
        
        if(cellsQueue.size() > 0){
            printQueue(cellsQueue);
            Cell tmp = cellsQueue.peek().get(1);
            tmp.setAnimal(this);
            this.getCell().setAnimal(null);
            this.setCell(tmp);
            return true;
        }
        
        return false;
        //z//////////////
    }
    
    //z/
    public boolean checkPossibleHome(int k, int l){
        // If a cell is occupied by an animal that is not energy source for current animal
        // we can not move to this cell
        Animal tmpAnimal = this.getCell().getWorld().get(k, l).getAnimal();
        if(tmpAnimal != null && !this.getEnergySources().contains(tmpAnimal)){ 
            return false;
        }
        

        // We need to check if there is a mountain or some animal between possible new home and current cell
        // we need to apply line approximation algorithm once more
        if(checkIfBlocked(k, l))
            return false;
            
        return true;
        
    }
    //z/
    public boolean checkIfBlocked(int k, int l){
        Cell tmpCell = this.getCell().getWorld().get(k, l);
        int x0 = this.getCell().getX(),
            y0 = this.getCell().getY(),
            x1 = tmpCell.getX(),
            y1 = tmpCell.getY();
                
        int dx = Math.abs(x1 - x0),
            sx = x0 < x1 ? 1 : -1;
        int dy = Math.abs(y1 - y0),
            sy = y0 < y1 ? 1 : -1;
        int err = (dx > dy ? dx : -dy)/2;
    
        while(true){
                World tmpWorld = this.getCell().getWorld();
                // check if there is mountain on the path or an animal, and be sure
                // that this animal will not be our current animal
                if(tmpWorld.get(x0, y0).getMountain() != null ||
                  (tmpWorld.get(x0, y0).getAnimal() != null && 
                   tmpWorld.get(x0, y0).getAnimal() != this))
                    return true;
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
        return false;
    
    }

    
    //z/ used for test purposes
    public void printQueue(PriorityQueue<ArrayList<Cell>> q){
        Iterator iterator = q.iterator();
        while(iterator.hasNext()){
            ArrayList<Cell> tmp = (ArrayList<Cell>)iterator.next();
            System.out.print(tmp.get(0).getX() + " " + tmp.get(0).getY() + " " + tmp.get(1).getX() + " " + tmp.get(1).getY() + '\n');
        }
    }
}
