 

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;

/**
 * The test class VertexTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class VertexTest
{
    /**
     * Default constructor for test class VertexTest
     */
    public VertexTest()
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
    public void getClosestShopTest()
    {
        Graph g = new Graph (null);
        ArrayList orders = new ArrayList();
        orders.add(1);
        CoffeeShop [] c = new CoffeeShop [] {new CoffeeShop (0,0,1,orders,g, 1),
                                            new CoffeeShop (1,1,2,orders,g, 1)};
        g.setVertexArray((Vertex[])c);
        g.generateEdges();
        
        assertEquals(c[0].findClosestShop(g), c[1]);
    }
    
    @Test
    public void getClosestShopToVertexTest()
    {
        Graph g = new Graph (null);
        ArrayList orders = new ArrayList();
        orders.add(1);
        CoffeeShop [] c = new CoffeeShop [] {new CoffeeShop (0,0,1,orders,g, 2),
                                            new CoffeeShop (1,1,2,orders,g, 2), 
                                            new CoffeeShop (2,2,3,orders,g, 2)};
        g.setVertexArray((Vertex[])c);
        g.generateEdges();
        
        assertEquals(c[0].findClosestToVertex(c[1],g), c[1]);
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
