import java.util.*;
import java.io.FileReader;
/**
 * Simulation class that calls the methods to run the experiment.
 * The class loads and populates a graph from a file and then runs
 * the delivery simulation.
 * 
 * @author Eric Last and Chris Mayer 
 * @version 5.5.17
 */
public class Simulation
{
    Graph g;
    int numWarehouses = 0;
    int numShops = 0;
    
    /**
     * Method that loads the graph by setting the vertices and edges.
     * The method reads from two files in the package and parses through them,
     * creating a vertex from each line. -EL
     * @param warehouseFile the name of a file to read from to initialize warehouses
     * @param shopsFile the name of a file to read from to initialize shops
     */
    public void loadGraph(String warehouseFile, String shopsFile){
        g = new Graph(null);
        Scanner sc = null; //Must initialize to null before trying to read the filename -EL
        ArrayList<Vertex> v = new ArrayList<Vertex>(); 
        
        try{
            sc = new Scanner(new FileReader(shopsFile));
        } catch (Exception e){
            System.out.println("Cannot find file with name '"+shopsFile+"'");
        }

        try{
            numShops = Integer.parseInt(sc.nextLine()); //First line of the file shows how many vertices are to be added -EL
            while(sc.hasNextLine()){
                /*
                 * Since the files are formatted strangely, we had to use both
                 * split and substring to breakdown the information in a way that
                 * the computer could understand the input as an integer -EL
                 */
                String[] data = sc.nextLine().split(" ");
                int id = Integer.parseInt(data[0]);
                int x = Integer.parseInt(data[1].substring(1,data[1].length()-1));
                int y = Integer.parseInt(data[2].substring(0,data[2].length()-2));
                ArrayList<Integer> cargo = new ArrayList(); //This ArrayList is passed as the orders per shop -EL
                for(int i = 3; i < data.length - 1; i++){
                    cargo.add(Integer.parseInt(data[i].substring(0,data[i].length()-1))); //These lines contain commas and need to be dealt with seperately -EL
                }
                cargo.add(Integer.parseInt(data[data.length-1])); //This is the last line and will not have a comma -EL
                CoffeeShop c = new CoffeeShop(x,y,id,cargo,g, numShops - 1);
                v.add(c); //Adds the new CoffeeShop to the vertex set -EL
            }
        } catch (Exception e){
            System.out.println("Cannot parse through file");
        }

        sc = null; //Resets the scanner -EL

        try{
            sc = new Scanner(new FileReader(warehouseFile));
        } catch (Exception e){
            System.out.println("Cannot find file with name '"+warehouseFile+"'");
        }

        try{
            numWarehouses = Integer.parseInt(sc.nextLine());
            while(sc.hasNextLine()){
                //Again, weird formatting causes weird parsing and splitting -EL
                String[] data = sc.nextLine().split(" ");
                int id = Integer.parseInt(data[0]);
                int x = Integer.parseInt(data[1].substring(1,data[1].length()-1));
                int y = Integer.parseInt(data[2].substring(0,data[2].length()-2));
                int numTrucks = Integer.parseInt(data[3]);
                Warehouse w = new Warehouse(g,x,y,id *-1,numTrucks, numShops);
                v.add(w);
            }
        } catch (Exception e){
            System.out.println("Cannot parse through file");
        }
        
        /*
         * This effectively does the same as the toArray() method
         * in the ArrayList class, but we like this way better -CM
         */
        Vertex[] vertexArray = new Vertex[v.size()];
        for (int i = 0; i < v.size(); i ++)
        {
            vertexArray[i] = v.get(i);
        }
        g.setVertexArray(vertexArray);
        g.generateEdges();
    }
    /**
     * This method controls the simulation itself. It 
     * controls when the trucks are dispatched, from which warehouses they are dispatched,
     * and when the simulation ends. It takes a warehouseFile and a shopsFile as parameters,
     * which are the files used to create the graph. -CM
     * @param warehouseFile the name of a file to read from to initialize warehouses
     * @param shopsFile the name of a file to read from to initialize shops
     */
    public void runSim(String warehouseFile, String shopsFile)
    {
        //load the graph -CM
        this.loadGraph(warehouseFile, shopsFile);
        //create and populate a list of warehouses by searching for all of the vertexes with negative id's -CM
        ArrayList<Warehouse> warehouses = new ArrayList();
        for (int i = -1; i > -1 *(numWarehouses + 1); i --)
        {
            warehouses.add((Warehouse)g.findVertex(i));
        }

        //intialize variables to keep track of the total distance traveled, the distance traveled per loop, and the next shop from which to call a truck -CM
        int totalDistance = 0;
        int distancePerLoop = 1;
        int nextShopID = 1;
        
        //as long as some truck travels some distance, continue looping -CM
        while (distancePerLoop > 0)
        {
            //set the distance per loop to 0 -CM
            distancePerLoop = 0;
            //find the next coffee shop from which to call a warehouse -CM
            CoffeeShop c = (CoffeeShop)g.findVertex(nextShopID);
            //if the nextID has no shops higher than it, return to 1 -CM
            if (nextShopID >= numShops)
                nextShopID = 1;
            
            //otherwise, find the closest warehouse to the truck and then dispatch the truck -CM
            else
            {
                int truckDistance = c.findClosestWarehouse(numWarehouses).dispatchTruck();
                //add up the distance that truck travels to the total distance and the distance per loop -CM
                totalDistance += truckDistance;
                distancePerLoop += truckDistance;
            }
            //increment the shop id -CM
            nextShopID ++;
        }

        //print the total distance -CM
        System.out.println("Total distance traveled by all trucks: "+totalDistance);
        for(Vertex v: g.v){
            System.out.println(v);
        }
    }
}
