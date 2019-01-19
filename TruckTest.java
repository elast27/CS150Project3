
import java.util.*;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class TruckTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class TruckTest
{
    @Test
    public void TestPlanRoute(){
        ArrayList<Integer> a = new ArrayList<Integer>();
        a.add(100);
        a.add(200);
        ArrayList<Vertex> v = new ArrayList<Vertex>();
        Graph g = new Graph(null);
        CoffeeShop c = new CoffeeShop(1,1,1,a,g,2);
        CoffeeShop d = new CoffeeShop(2,2,2,a,g,2);
        CoffeeShop e = new CoffeeShop(3,1,3,a,g,2);
        Warehouse w = new Warehouse(g,4,4,-1,5,3);
        v.add(c);
        v.add(d);
        v.add(e);
        v.add(w);
        Vertex[] vertexArray = new Vertex[v.size()];
        for (int i = 0; i < v.size(); i ++)
        {
            vertexArray[i] = v.get(i);
        }
        g.setVertexArray(vertexArray);
        g.generateEdges();
        w.truck[1] = new Truck(g);
        w.truck[1].planRoute(w,w);
    }

    @Test
    public void TestDeliverCargo(){
        ArrayList<Integer> a = new ArrayList<Integer>();
        a.add(10);
        ArrayList<Vertex> v = new ArrayList<Vertex>();
        Graph g = new Graph(null);
        CoffeeShop c = new CoffeeShop(1,1,1,a,g,2);
        CoffeeShop d = new CoffeeShop(2,2,2,a,g,2);
        CoffeeShop e = new CoffeeShop(3,1,3,a,g,2);
        Warehouse w = new Warehouse(g,4,4,-1,5,3);
        v.add(c);
        v.add(d);
        v.add(e);
        v.add(w);
        Vertex[] vertexArray = new Vertex[v.size()];
        for (int i = 0; i < v.size(); i ++)
        {
            vertexArray[i] = v.get(i);
        }
        g.setVertexArray(vertexArray);
        g.generateEdges();
        w.truck[0] = new Truck(g);
        w.truck[0].planRoute(w,w);
        assertEquals(w.truck[0].deliverCargo(w),12);
    }
}
