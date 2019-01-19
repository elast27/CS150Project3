import java.util.*;
/**
 * This class acts as a CoffeeShop.
 * The coffee shop extends Vertex, having the same properties
 * and instance variables. This class adds a few more variables
 * and two methods for calculating the total weight of cargo
 * demanded to fulfil an order.
 * 
 * @author Eric Last
 * @version 5.3.17
 */
public class CoffeeShop extends Vertex
{
    /**List of orders*/
    ArrayList<Integer> orders;
    /**Total weight desired by the shop*/
    int weight;
    /**For traversal*/
    boolean visited;
    /**
     * Constructor that initializes the instance variables. Calls
     * its parent's constructor to initialize the position and id,
     * then also sets the cargo list and the total weight desired.
     */
    public CoffeeShop(int x, int y, int id, ArrayList<Integer> cargo, Graph g, int numEdges){
        super(x,y,id,g);
        type = COFFEE_SHOP; //Static final int that defines what type of vertex
        this.orders = cargo; 
        weight = totalWeight(); //Calls the totalWeight method to calculate
        this.visited = false;
        e = new Edge[numEdges]; //Since each shop is connected to every other shop, the edge[] only needs to be size 200 because there are 200 shops in the graph
    }

    /**
     * Calculates the total weight of cargo requested by the CoffeeShop.
     * This method is useful for knowing how much cargo is still needed
     * for delivery, and is called every time something is dropped off
     * to reevaluate how much is still needed.
     * @return total weight of cargo requested
     */
    public int totalWeight(){
        int sum = 0;
        for(Integer i : orders){
            sum += i;
        }
        return sum;
    }

    /**
     * @return weight
     */
    public int getWeight(){ return this.totalWeight(); }

    /**
     * @return toString formatting
     */
    public String toString(){
        return "["+this.id+"]: "+this.x+", "+this.y+" "+this.orders.toString();
    }
    
    public Warehouse findClosestWarehouse(int numWarehouses){
        ArrayList<Warehouse> w = new ArrayList();
        
        for (int i = -1; i > -1 *(numWarehouses + 1); i --){
            w.add((Warehouse)g.findVertex(i));
        }
        
        int minDistance = 11000;
        Warehouse closest = null;
        
        for (Warehouse h : w){
            if (h.getDistance(this) < minDistance && h.hasTrucks()){
                closest = h;
                minDistance = h.getDistance(this);
            }
        }
        
        return closest;
    }
}
