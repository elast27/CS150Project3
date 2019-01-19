
/**
 * This class defines the setup for the warehouse. The warehouses
 * essentially just dispatches the trucks and keeps track of which trucks
 * come from which warehouse. It has a few instance variables--it keeps track
 * of the number of trucks, has an array of trucks, and a count of the next truck
 * that will leave the warehouse.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Warehouse extends Vertex
{
    /** the number of trucks the warehouse has. -CM*/
    int numTrucks;
    /** the array of trucks that belong to the warehouse. -CM*/
    Truck[] truck;
    /** the next truck to leave the warehouse. -CM */
    int nextTruckIndex = 0;
    
    /** 
     * A constructor for a warehouse that takes as parameters. -CM
     * @param g which defines the graph the warehouse is on
     * @param x x coordinate
     * @param y y coordinate
     * @param id which is the id of the warehouse
     * @param numTrucks is the number of trucks the warehouse has
     * @param numEdges number of edges in the graph
     */
    public Warehouse (Graph g, int x, int y, int id, int numTrucks, int numEdges)
    {
        super (x,y,id,g);
        this.type = WAREHOUSE;
        this.numTrucks = numTrucks;
        truck = new Truck[numTrucks];
        e = new Edge[numEdges];
    }
   
    /** 
     * if there is another truck left for the warehouse to send, then create a new
     * truck in the truck array. Then, plan its route, and deliver the cargo. Finally,
     * increment the nextTruckIndex variable to move to the next spot in the truck array. -CM
     * @return distance traveled by the truck
     */
    public int dispatchTruck()
    {
        if (nextTruckIndex >= numTrucks)
            return 0;
        else
            truck[nextTruckIndex] = new Truck(g);
            
        truck[nextTruckIndex].planRoute(this,this);
        return truck[nextTruckIndex++].deliverCargo(this);
    }
    
    /** 
     * print the id and the x and y coordinates, pluss the number of trucks. -EL
     * @return toString formatting
     */
    public String toString(){
        return "["+this.id+"]: "+this.x+", "+this.y+": "+this.numTrucks;
    }
    
    /** 
     * returns the number of trucks. -CM
     * @return numTrucks
     */
    public int numTrucks()
    {
        return truck.length;
    }
    
    /**
     * checks if the warehouse has trucks left. -CM
     * @return if warehouse has trucks to dispatch
     */
    public boolean hasTrucks()
    {
        if (nextTruckIndex >= numTrucks)
        {
            return false;
        }
        return true;
    }
}
