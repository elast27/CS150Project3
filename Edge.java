/**
 * 
 * Edge class. Represents an edge
 * connecting two vertices. Since
 * this is a weighted graph, the
 * edges have weights.
 * 
 * @author Chris Mayer
 * @version 5.5.17 
 * 
 */
public class Edge
{
    /**Even though these are named start and end, edges do not have direction -EL*/
    Vertex start, end;
    /**Weight of the edge. -EL*/
    int weight;
    
    /**
     * Initializes instance variables, and calculates the weight of the edge.
     * Our implementation creates a complete graph (where every vertex is connected
     * to every other vertex) on the set of CoffeeShops, and a complete bipartite
     * graph (where every vertex of one set is connected to every vertex of the other
     * set) between the Warehouse set and the CoffeeShop set. This then means we
     * can calculate the distance by just adding the x and y components between
     * two vertices. -EL
     */
    public Edge (Vertex start, Vertex end)
    {
        this.start = start;
        this.end = end;
        
        weight = start.getDistance(end);
    }
    
    /**
     * Get method for weight -EL
     * @return weight of the edge
     */
    public int getWeight()
    {
        return weight;
    }
    
    /**
     * Get method for the start vertex -EL
     * @return starting vertex of the edge
     */
    public Vertex getStartVertex ()
    {
        return start;
    }
    
    /**
     * Get method for the end vertex -EL
     * @return ending vertex of the edge
     */
    public Vertex getEndVertex ()
    {
        return end;
    }
}