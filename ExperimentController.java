
/**
 * Write a description of class ExperimentController here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ExperimentController
{
    public static void main(String[] args){
        Simulation s = new Simulation();
        s.runSim("shops.txt","warehouses1.txt");
        s.runSim("shops.txt","warehouses2.txt");
        s.runSim("shops.txt","warehouses3.txt");
        s.runSim("shops.txt","warehouses4.txt");
        s.runSim("shops.txt","warehouses5.txt");
    }
}
