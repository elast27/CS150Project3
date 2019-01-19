import java.util.*;
/**
 * Class that represents a truck which delivers the orders to coffee shops.
 * The truck class calculates the route it is going to take and the total distance traveled,
 * using two methods, the planRoute method and the deliverCargo method.
 * 
 * @author Eric Last
 * @version 5.3.17
 */
public class Truck
{
    /** Total capacity of a truck -EL **/
    public static final int CAPACITY = 500;
    /** Current weight on the truck -EL **/
    int contents; 
    /**Checks if the truck has already delivered cargo -EL **/
    boolean dispatched; 
    /**Graph instance variable used in certain methods -EL**/
    Graph g; 

    /**
     * Constructor
     * Sets the initial values of the truck's instance variables
     **/
    public Truck(Graph g){
        this.g = g; this.contents = 0; this.dispatched = false;
    }
    /**Instance variable that stores the route the truck will take. Gets set via planRoute method -EL**/
    Queue<CoffeeShop> route=new LinkedList<CoffeeShop>(); 
    /**
     * Plans the route the truck will take to deliver cargo. 
     * Recursively finds the closest vertex to the home warehouse that has not already been taken care of.
     * Loads up the truck based on the needs of the coffee shops it will deliver to. -EL
     * @param home the warehouse the truck originates from
     * @param v the vertex from which to call the method
     **/
    public void planRoute(Warehouse home, Vertex v){
        CoffeeShop w = v.findClosestToVertex(home,g);
        if (w == null)
            return;
        int startContents = contents; //This is initialized to make sure that cargo is added to a truck -EL
        int i = 0;
        while(w.orders.size()>0){
            if(w.orders.get(i) <= CAPACITY - contents){
                contents += w.orders.get(i); //Adds the weight to the truck -EL
                w.orders.remove(i); //Removes the order from the CoffeeShop's order list -EL
                w.weight = w.totalWeight(); //Recalculates the total weight demanded by the CoffeeShop -EL
                if(w.orders.size() == 0) g.setShopsWithOrdersOutstanding(g.getShopsWithOrdersOutstanding()-1);
            }
            else break;
        }

        if(startContents == contents)return; //This line checks to see if cargo was added. If not, the method returns. -EL
        route.add(w); //Adds the current vertex to the route the truck will take -EL
        planRoute(home, w); //Recursively calls the method on the next vertex -EL
    }

    /**
     * Method that simulates the delivery of cargo to each CoffeShop on the route.
     * Calculates the distance traveled by the truck by adding the weights of each
     * edge it passes through. Once it gets to the end, it adds on the weight of the
     * edge between the last shop and the warehouse it originated from.
     * @param w Warehouse the truck originates from
     * @return the total distance traveled by the truck
     */
    public int deliverCargo(Warehouse w){
        if (!route.isEmpty())
        {
            if(!dispatched){
                dispatched = true; //Makes sure no truck is dispatched more than once
                System.out.println("Truck from warehouse "+(-1*w.getID())+" delivers to: ");
                int distanceTraveled = 0;
                CoffeeShop lastSeen = route.poll(); //Need to keep track of the last vertex seen for weight calculation between shops
                distanceTraveled += w.getDistance(lastSeen);
                System.out.print("Shop "+lastSeen);
                System.out.println(" (Current Distance = "+distanceTraveled+"), ");
                while(!route.isEmpty()){
                    CoffeeShop u = route.poll();
                    distanceTraveled += u.getDistance(lastSeen); //Adds the weight connecting u to lastSeen
                    lastSeen = u;
                    System.out.print("Shop "+lastSeen);
                    System.out.println(" (Current Distance = "+distanceTraveled+"), ");
                }
                distanceTraveled += w.getDistance(lastSeen); //Adds the distance to return back to the shop
                System.out.println("and back to the warehouse (Distance to warehouse: "+w.getDistance(lastSeen)+")\nTotal distance = "+distanceTraveled+"\nWarehouse has trucks left? "+w.hasTrucks()+"\n");
                return distanceTraveled;
            }
            else return 0;
        }
        return 0;
    }
}
