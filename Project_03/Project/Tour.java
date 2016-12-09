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
    
    public void initializeRoute(){
        ArrayList<TourTile> tbv = new ArrayList<TourTile>();
        tbv.addAll(map.getTiles().values());
        TourTile cTile= this.startingTile;
        tbv.remove(this.startingTile);
        Route cRoute = new Route(cTile, 0);
        Route r = createRoute(tbv, cRoute, cTile, 0);
        
        route = r.getLs();
        System.out.println(r);
    }
    
    public void initializeTourists(){
        int dif = capacity - startingTile.getTourists().size();
        //System.out.println("HEY " + startingTile.getTourists().size());
        for(; dif > 0; dif--){
            Tourist tourist = new Tourist(map, this, radius, effect);
            this.startingTile.addTourist(tourist);
            tourists.add(tourist);
        }
        
    }
    
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
    
    public void proceed(){
        initializeTourists();
        ArrayList<Tourist> satisfiedTourists = new ArrayList<Tourist>();
        
        // you are modifying the container idiot!!!!
        for(int i = 0; i < tourists.size(); i++){
            Tourist tourist = tourists.get(i);
            TourTile tile = tourist.getCurrentTile();
            if(tile != route.getLast()){
                int k = route.indexOf(tile);
                tourist.move(route.get(k + 1));
            }else{
                System.out.println("Hallelujah");
                // let tourist go home
                satisfiedTourists.add(tourist);
                tile.getTourists().remove(tourist);
            }
        }
        
        for(int i = 0; i < tourists.size(); i++){
            Tourist tourist  = tourists.get(i);
            if(satisfiedTourists.contains(tourist)){
                tourists.remove(tourist);
                continue;
            }
            tourist.setMoved(false);
        }
    }
    
    public void affect(){
        for(int i = 0; i < route.size(); i++){
            if(route.get(i).getTourists().size() > 0){
                route.get(i).getTourists().get(0).influenceAnimals();
            }
        }
    }
    
    public TouristMap getMap(){
        return this.map;
    }
    
    public TourTile getStartingTile(){
        return this.startingTile;
    }
    
    public char getSymbol(){
        return this.symbol;
    }
    
    public int getCapacity(){
        return this.capacity;
    }
    
    public int getRadius(){
        return this.radius;
    }
    
    public double getEffect(){
        return this.effect;
    }
    
}