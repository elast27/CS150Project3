/**
 * This class establishes the basic functionality for a vertex.
 * It has instance variables which define its x,y position,
 * an id to distinguish it from other vertices and to give it
 * a key for the graph, a vertex of edges to keep track of its
 * edges, a graph to which it belongs, and a type, to keep track
 * of whether it is a coffee shop or a warehouse.
 * 
 * @author: Chris Mayer
 * @Version: 5.3.17
*/
public class Vertex
{
    /** the x and y positions of the vertex */
    int x, y;
    /** the vertex's distinct ID */
    int id;
    /** the vertex's type: coffee shop or warehouse */
    int type;
    /** an edge array that holds every edge the vertex has */
    Edge[] e;
    /** the graph to which the vertex belongs */
    Graph g;
    /** a constant denoting if it is a coffee shop */
    public final int COFFEE_SHOP = 0;
    /** a constant denoting if the vertex is a warehouse */
    public final int WAREHOUSE = 1;
    
    /** returns the x value */
    public int getX()
    {
        return x;
    }
    
    /** returns the y value */
    public int getY()
    {
        return y;
    }
    
    /** returns the id */
    public int getID()
    {
        return id;
    }
    
    /** returns the edge array */
    public Edge[] getEdges()
    {
        return e;
    }
    
    /** sets the x coordinate. Accepts an int x as parameter */
    public void setX(int x)
    {
        this.x = x;
    }
    /** sets the y coordinate. Accepts an int y as parameter */
    public void setY(int y)
    {
        this.y = y;
    }
    
    /** sets the id. Accepts an int id as parameter */
    public void setID(int id)
    {
        this.id = id;
    }
    
    /** default null constructor for vertex */
    public Vertex ()
    {
        this (-1, -1,-1, null);
    }
    
    /**
     * constructor for vertex with 4 parameters. -CM
     * @param x sets x coordinate
     * @param y sets y coordinate
     * @param id sets id
     * @param g sets graph g
     */
    public Vertex (int x, int y, int id, Graph g)
    {
        this.x = x;
        this.y = y;
        this.id = id;
        this.g = g;
    }
     
    /**
     * finds the closest coffee shop to the current vertex
     * accepts a parameter g to denote the graph
     * sets a minimum weight to the edge above whatever the maximum weight is
     * then, the method iterates through every possible edge
     * if the coffee shop still has orders left and the weight of the edge
     * is less than the minimum weight, then the closest coffee shop is updated. -CM
     * @param g a graph to pass into the method
     * @return the closest CoffeeShop to the vertex from which the method is called
     */
    public CoffeeShop findClosestShop(Graph g)
    {
        CoffeeShop closestShop = null;
        int minWeight = 11000;
        
        for (int i = 0; i < e.length; i ++)
        {
            CoffeeShop p = (CoffeeShop)e[i].getEndVertex();
            if(p.getWeight() > 0 && e[i].getWeight() < minWeight)
            {
                minWeight = e[i].getWeight();
                closestShop = (CoffeeShop)e[i].getEndVertex();
            }
        }
        
        return closestShop;
    }
    
    /**
     * finds the closest coffee shop to both the current vertex and a selected vertex
     * takes two parameters, the graph g and the vertex v, which is the vertex to which
     * the coffee shop must be close. 
     * The method essentially functions the same way as the prior method does, except
     * the minimum weight is the addition of the distances to a given vertex from both
     * the current vertex and the vertex v. -CM
     * @param v the vertex from which to find the closest vertex
     * @param g a graph to pass through
     * @return the closest CoffeeShot to v
     */
    public CoffeeShop findClosestToVertex(Vertex v, Graph g)
    {
        CoffeeShop closestShop = null;
        int minWeight = 11000;
        
        for (int i = 0; i < e.length; i ++)
        {
            CoffeeShop p = (CoffeeShop)e[i].getEndVertex();
            if(p.getType() < 1 && p.getWeight() > 0 && 
            e[i].getWeight() + v.getDistance(e[i].getEndVertex()) < minWeight)
            {
                minWeight = e[i].getWeight()+v.getDistance(this);
                closestShop = (CoffeeShop)e[i].getEndVertex();
            }
            
            else if (p.getWeight() > 0 &&
                    p.getWeight() < closestShop.getWeight() &&
                    e[i].getWeight() + v.getDistance(e[i].getEndVertex()) == minWeight)
            {
                minWeight = e[i].getWeight() + v.getDistance(this);
                closestShop = (CoffeeShop)e[i].getEndVertex();
            }  
        }
        
        return closestShop;
    }
    
    /**
     * returns distance. -CM
     * @param end second vertex to find the distance
     * @return distance between vertex from which this is called and end
     */
    public int getDistance(Vertex end)
    {
        return Math.abs(end.getX() - this.getX()) + Math.abs((end.getY() - this.getY()));
    }
    
    /**
     * adds an edge to the edge array -CM
     * @param end second vertex to add an edge
     * @param index index in the edge array
     */
    public void addEdge(Vertex end, int index)
    {
        e[index] = new Edge(this, end);
    }
    
    /** 
     * returns the type of the vertex -CM
     * @return type
     */
    public int getType()
    {
        return type;
    }
}