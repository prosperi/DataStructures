/** 
  * @desc this class represents the tour itself,
  * identifies the places tourists are going to visit 
  * in this tour and generates shortest tour for provided 
  * starting place(node). Tour class also keeps information
  * about the tourists moving on this tour and allows new 
  * tourists to enter our World if there is some free space.
  * @author Zura Mestiashvili mestiasz@lafayette.edu
  * @version v1.0.0
*/

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class Tour{
    public static final int INFINITY = Integer.MAX_VALUE;
    public static final int PQ_MAX_SIZE = 1;
    
    private TouristMap map;
    private TourTile startingTile;
    private char symbol;
    private int capacity;
    private int radius;
    private double effect;
    private ArrayList<Tourist> tourists;
    private PriorityQueue<Route> routes;
    // restructure
    private LinkedList<TourTile> route;
    
    public Tour(TouristMap map, char symbol, int st, int capacity, int radius, double effect){
        this.map = map;
        this.startingTile = map.getTile(st);
        this.symbol = symbol;
        this.capacity = capacity;
        this.radius = radius;
        this.effect = effect;
        this.tourists = new ArrayList<Tourist>();
        this.routes = new PriorityQueue<Route>(11);
        this.route = new LinkedList<TourTile>();
        
        // build up route
        initializeRoute();
    }
    
    /**
     * @desc Initialize the shortest tour by running createRoute() method
     * and assigning result to the route container of this class
    */
    public void initializeRoute(){
        ArrayList<TourTile> tbv = new ArrayList<TourTile>();
        tbv.addAll(map.getTiles().values());
        TourTile cTile = this.startingTile;
        tbv.remove(this.startingTile);
        Route cRoute = new Route(cTile, 0);
        Route r = createRoute(tbv, cRoute, cTile, 0);
        
        route = r.getLs();
        System.out.println(r);
        
        for(int i = 0; i < route.size(); i++){
            route.get(i).setSymbol(this.symbol);
        }
    }
    
    /**
     * @desc create new Tourists if there is space on starting tile(node)
    */
    public void initializeTourists(){
        int dif = capacity - startingTile.getTourists().size();
        for(; dif > 0; dif--){
            Tourist tourist = new Tourist(map, this);
            this.startingTile.addTourist(tourist);
            tourists.add(tourist);
        }
        
    }
    
    /**
     * @desc generate the shortest tour using TSP algorithm
     * @params ArrayList<TourTile> tbv - list of to be visited nodes
     * @params Route cRoute - current route
     * @params TourTile cTile - current tile we are cheking for
     * @params int w - weight of current route
    */
    public Route createRoute(ArrayList<TourTile> tbv, Route cRoute, TourTile cTile, int w){
        boolean extend = false;
        if(tbv.size() == 0){
            cRoute.generateLs();
            return cRoute;
        }

        ArrayList<TourTile> tbv_tmp;
        Route route;
        for(TourEdge e : cTile.getAdjacent()){
            if(tbv.contains(e.getEnd())){
                extend = true;
                e.getEnd().setPrev(cTile);
                route = new Route(e.getEnd(), cRoute.getCost() + e.getWeight());
                tbv_tmp = new ArrayList<TourTile>(tbv);
                tbv_tmp.remove(e.getEnd());
                routes.add(createRoute(tbv_tmp, route, e.getEnd(), w + e.getWeight()));
                // if size of priority queue gets more than maximum size, then remove the first element
                // which in our case is the longest tour for that moment
                if(routes.size() > PQ_MAX_SIZE) routes.remove();
            }
        }
        
        if(extend == false && tbv.size() > 0){
            w = INFINITY;
            cRoute.setCost(w);
            return cRoute;
        }

        
        return routes.poll();
    }
    
    /**
     * @desc proceed the tour and allow tourists to move or go home.
     * mark each tourists moved(boolean) variable ad false after the process is finished.
    */
    public void proceed(){
        initializeTourists();
        affect();
        ArrayList<Tourist> satisfiedTourists = new ArrayList<Tourist>();
        
        for(int i = 0; i < tourists.size(); i++){
            Tourist tourist = tourists.get(i);
            TourTile tile = tourist.getCurrentTile();
            if(tile != route.getLast()){
                int k = route.indexOf(tile);
                tourist.move(route.get(k + 1));
            }else{
                // let tourist go home
                satisfiedTourists.add(tourist);
                tile.getTourists().remove(tourist);
            }
        }
        // mark each tourists moved(boolean) variable false after the process is finished
        for(int i = 0; i < tourists.size(); i++){
            Tourist tourist  = tourists.get(i);
            tourist.setMoved(false);
        }
        
        for(int i = 0; i < satisfiedTourists.size(); i++){
            tourists.remove(satisfiedTourists.get(i));
        }
    }
    
    
    /**
     * @desc if there is at least one tourist on the tile, make him/her
     * affect the animals and decrease their energy
    */
    public void affect(){
        for(int i = 0; i < route.size(); i++){
            if(route.get(i).getTourists().size() > 0){
                route.get(i).getTourists().get(0).influenceAnimals();
            }
        }
    }
    
    /**
     * @desc get the tourist map
     * @return TouristMap - map
    */
    public TouristMap getMap(){
        return this.map;
    }
    
    /**
     * @desc get the starting tile(node)
     * @return TouristTile - tile
    */
    public TourTile getStartingTile(){
        return this.startingTile;
    }
    
    /**
     * @desc get the representation of tour
     * @return char - symbol
    */
    public char getSymbol(){
        return this.symbol;
    }
    
    /**
     * @desc get the capacity of the node in this tour
     * @return int - capacity
    */
    public int getCapacity(){
        return this.capacity;
    }
    
    /**
     * @desc get the radius of influence
     * @return int - radius
    */
    public int getRadius(){
        return this.radius;
    }
    
    /**
     * @desc get the value of effect on animals
     * @return double - effect
    */
    public double getEffect(){
        return this.effect;
    }
    
    /**
     * @desc get the value of the route
     * @return LinkedList<TourTile> - route
    */
    public LinkedList<TourTile> getRoute(){
        return this.route;
    }
    
     /**
     * @desc get the tourists list
     * @return ArrayList<Tourist> - tourists
    */
    public ArrayList<Tourist> getTourists(){
        return this.tourists;
    }
    
}