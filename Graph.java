
/**
 * This class defines the Graph data structure that is used in 
 * the other classes. The Graph class does not extend any other
 * classes, but it does provide basic functionality, including in
 * its implementation an array vertex and some methods for finding
 * vertexes in the array.
 * 
 * @author Chris Mayer 
 * @version 5.3.17
 */
public class Graph
{
    /** array of the graph's vertexes. -CM**/
    Vertex[] v;
    
    /** a count of the number of shops with orders still unfulfilled. -CM*/
    private int shopsWithOrdersOutstanding;
    
    /** constructor for graph class. Has as parameter the array of vertexes. -CM*/
    public Graph (Vertex[] v)
    {
        this.v = v;
        if (v != null)
            shopsWithOrdersOutstanding = v.length;
    }
    
    /** 
     * resets the vertex array and updates shops with orders outstanding.
     * accepts a vertex array as parameter. -CM
     * @param v vertex set of the graph
    */
    public void setVertexArray(Vertex[] v)
    {
        this.v = v;
        if (v != null)
            shopsWithOrdersOutstanding = v.length;
    }
    
    /**
     * A method that finds a given vertex using a for loop.
     * Accepts as a parameter a key, and then iterates through
     * the vertex array, looking for the vertex with that key. -CM
     * @param key key of the vertex to find
     * @return the vertex at specified key
     */
    public Vertex findVertex(int key)
    {
        for (int i = 0; i < v.length; i ++)
        {
            if (v[i].getID() == key)
                return v[i];
        }
        
        return null;
    }
    
    /**
     * This method generates edges. It uses a nested for loop to give every vertex
     * an edge to every single coffee shop. Warehouses do not need to have edges to other warehouses,
     * so this is disregarded. -CM
     */
    public void generateEdges()
    {
        for (int i = 0; i < v.length; i ++)
        {
            int currentIndex = 0;
            for (int j = 0; j < v.length; j++)
            {
                if (j != i && v[j].getType() == 0)
                    v[i].addEdge(v[j], currentIndex++);
            }
        }
    }
    
    /**
     * a set method with shops with orders outstanding.
     * Accepts an integer s parameter to which shopsWithOrdersOutstanding
     * is then set. -CM
     * @param s vaue to set
     */
    public void setShopsWithOrdersOutstanding(int s)
    {
        shopsWithOrdersOutstanding = s;
    }
    
    /**
     * returns shops with orders outstanding -CM
     */
    public int getShopsWithOrdersOutstanding()
    {
        return shopsWithOrdersOutstanding;
    }
}
