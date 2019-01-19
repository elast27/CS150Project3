
import java.util.*;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class CoffeeShopTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class CoffeeShopTest
{
    @Test
    public void TestTotalWeight(){
        ArrayList<Integer> a = new ArrayList<Integer>();
        a.add(100);
        a.add(200);
        Vertex[] v = new Vertex[5];
        Graph g = new Graph(v);
        CoffeeShop c = new CoffeeShop(1,1,1,a,g,20);
        assertEquals(c.totalWeight(), 300);
    }
}
