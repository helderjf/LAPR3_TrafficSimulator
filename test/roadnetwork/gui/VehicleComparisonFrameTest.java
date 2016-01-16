/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadnetwork.gui;

import java.util.ArrayList;
import java.util.HashMap;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import roadnetwork.domain.BestPathAlgorithm;
import roadnetwork.domain.CombustionVehicle;
import roadnetwork.domain.Junction;
import roadnetwork.domain.Regime;
import roadnetwork.domain.SectionTypology;
import roadnetwork.domain.Throttle;
import roadnetwork.domain.Vehicle;

/**
 *
 * @author Andre
 */
public class VehicleComparisonFrameTest {
    
    Vehicle vehicle1;
    Vehicle vehicle2;
    Vehicle vehicle3;
    
    ArrayList<Vehicle> lstVehicle1;
    ArrayList<Vehicle> lstVehicle2;
    
    
    SectionTypology stype1;
    SectionTypology stype2;
    SectionTypology stype3;
    SectionTypology stype4;
    
    HashMap<SectionTypology, Double> velocityLimit1;
    HashMap<SectionTypology, Double> velocityLimit2;
    HashMap<SectionTypology, Double> velocityLimit3;
    
    HashMap<Integer, Double> gearList1;
    HashMap<Integer, Double> gearList2;
    
    ArrayList<Throttle> throttleList1;
    ArrayList<Throttle> throttleList2;
    
    Regime regime1;
    Regime regime2;
    Regime regime3;
    Regime regime4;
    
    ArrayList<Regime> lstregime1;
    ArrayList<Regime> lstregime2;
    ArrayList<Regime> lstregime3;
    
    Throttle throttle1;
    Throttle throttle2;
    Throttle throttle3;
    
    public VehicleComparisonFrameTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        
        stype1 = SectionTypology.regular_road;
        stype2 = SectionTypology.highway;
        stype3 = SectionTypology.street;
        stype4 = SectionTypology.urban_street;
        
        velocityLimit1 = new HashMap<>();
        velocityLimit1.put(stype1, 90.00);
        velocityLimit1.put(stype2, 80.00);
        
        velocityLimit2 = null;
        
        velocityLimit3 = new HashMap<>();
        velocityLimit3.put(stype3, 100.00);
        velocityLimit3.put(stype4, 200.00);
        
        gearList1 = new HashMap<>();
        gearList1.put(1, 10.00);
        gearList1.put(2, 20.00);
        gearList1.put(3, 30.00);
        gearList1.put(4, 40.00);
        
        regime1 = new Regime(85, 1000, 2499, 8.2);
        regime2 = new Regime(95, 2500, 3999, 6.2);
        regime3 = new Regime(80, 4000, 5500, 10.2);
        regime4 = new Regime(135, 1000, 2499, 5.2);
        
        lstregime1 = new ArrayList();
        lstregime1.add(regime1);
        lstregime1.add(regime2);
        throttle1 = new Throttle("25", lstregime1);
        
        lstregime2 = new ArrayList();
        lstregime2.add(regime3);
        throttle2 = new Throttle("50", lstregime2);
        
        lstregime3 = new ArrayList();
        lstregime3.add(regime4);
        throttle3 = new Throttle("100", lstregime3);
        
        throttleList1 = new ArrayList();
        throttleList1.add(throttle1);
        throttleList1.add(throttle2);
        
        throttleList2 = new ArrayList();
        throttleList2.add(throttle3);

        vehicle1 = new CombustionVehicle("dummy1", //name
                "descricao", //descricao
                "car",//type
                "gasoline",//fuel
                1400, //mass
                120,//load
                0.35,//drag
                1.8,//frontal area
                0.01,//rcc
                0.5,//wheel size
                velocityLimit1,//
                1000,//min rpm
                5500,//max rpm
                2.6,//final drive ratio
                gearList1,
                throttleList1);
        
        vehicle2 = new CombustionVehicle("dummy2", //name
                "descricao2", //descricao
                "truck",//type
                "diesel",//fuel
                1200, //mass
                110,//load
                0.25,//drag
                1.9,//frontal area
                0.02,//rcc
                0.6,//wheel size
                velocityLimit1,//
                1200,//min rpm
                5900,//max rpm
                4.6,//final drive ratio
                gearList1,
                throttleList1);
        
        vehicle3 = new CombustionVehicle(1, //pk
                "dummy3", //name
                "descricao3", //descricao
                "moto",//type
                "gasoline",//fuel
                600, //mass
                70,//load
                0.12,//drag
                0.5,//frontal area
                0.01,//rcc
                0.3,//wheel size
                velocityLimit1,//
                900,//min rpm
                6900,//max rpm
                2.6,//final drive ratio
                gearList1,
                throttleList1);
        
        lstVehicle1 = new ArrayList<>();
        lstVehicle1.add(vehicle1);
        lstVehicle1.add(vehicle2);
        
        lstVehicle2 = new ArrayList<>();
        lstVehicle2.add(vehicle3);
        
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of setVehiclesList method, of class VehicleComparisonFrame.
     */
    @Test
    public void testSetVehiclesList() {
        System.out.println("setVehiclesList");
        ArrayList<Vehicle> vlist = lstVehicle1;
        VehicleComparisonFrame instance = null;
        instance.setVehiclesList(vlist);

    }

    /**
     * Test of setChooseNodesPane method, of class VehicleComparisonFrame.
     */
    @Test
    public void testSetChooseNodesPane() {
        System.out.println("setChooseNodesPane");
        VehicleComparisonFrame instance = null;
        instance.setChooseNodesPane();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setAnalysisNodes method, of class VehicleComparisonFrame.
     */
    @Test
    public void testSetAnalysisNodes() {
        System.out.println("setAnalysisNodes");
        Junction on = null;
        Junction dn = null;
        VehicleComparisonFrame instance = null;
        instance.setAnalysisNodes(on, dn);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setChooseAlgorithmPane method, of class VehicleComparisonFrame.
     */
    @Test
    public void testSetChooseAlgorithmPane() {
        System.out.println("setChooseAlgorithmPane");
        VehicleComparisonFrame instance = null;
        instance.setChooseAlgorithmPane();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setAlgorithm method, of class VehicleComparisonFrame.
     */
    @Test
    public void testSetAlgorithm() {
        System.out.println("setAlgorithm");
        BestPathAlgorithm bpa = null;
        VehicleComparisonFrame instance = null;
        instance.setAlgorithm(bpa);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of runAnalysis method, of class VehicleComparisonFrame.
     */
    @Test
    public void testRunAnalysis() {
        System.out.println("runAnalysis");
        VehicleComparisonFrame instance = null;
        instance.runAnalysis();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of exportGlobalResultsHTML method, of class VehicleComparisonFrame.
     */
    @Test
    public void testExportGlobalResultsHTML() {
        System.out.println("exportGlobalResultsHTML");
        String fileName = "";
        VehicleComparisonFrame instance = null;
        boolean expResult = false;
        boolean result = instance.exportGlobalResultsHTML(fileName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
