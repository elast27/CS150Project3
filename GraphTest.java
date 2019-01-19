

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;

/**
 * The test class GraphTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class GraphTest
{
    /**
     * Default constructor for test class GraphTest
     */
    public GraphTest()
    {
    }
    
    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
    }
    
    @Test
    public void findVertexTest ()
    {
        Graph g = new Graph (null);
        Vertex[] v = new Vertex [] {new Vertex (0,0,1,g), new Vertex(1,1,2,g)};
        g.setVertexArray(v);
        assertEquals(g.findVertex(1), v[0]);
    }
    
    @Test
    public void generateEdgesTest ()
    {
        Graph g = new Graph (null);
        CoffeeShop [] c = new CoffeeShop [] {new CoffeeShop (0,0,1,new ArrayList<Integer>(),g, 2),
                                            new CoffeeShop (1,1,2, new ArrayList<Integer>(),g, 2)};
        g.setVertexArray((Vertex[])c);
        g.generateEdges();
        
        CoffeeShop [] c1 = new CoffeeShop [] {new CoffeeShop (0,0,1,new ArrayList<Integer>(),g, 2),
                                            new CoffeeShop (1,1,2, new ArrayList<Integer>(),g, 2)};
        c1[0].addEdge(c1[1], 0);
        
        assertEquals(c1[0].getEdges()[0].getWeight(), g.findVertex(1).getEdges()[0].getWeight());
    }
    
    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }
}
