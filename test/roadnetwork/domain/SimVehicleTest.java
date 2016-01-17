/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadnetwork.domain;

import java.util.ArrayList;
import java.util.HashMap;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Andre
 */
public class SimVehicleTest {
    
    Vehicle vehicle1;
    Vehicle vehicle2;
    Vehicle vehicle3;
    Vehicle vehicle4;
    
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
    
    EngineEfficiency engineEfficiency1;
    EngineEfficiency engineEfficiency2;
    EngineEfficiency engineEfficiency3;
    
    ArrayList<EngineEfficiency> lstEngineEfficiency;
    
    SimVehicle simVehicle;
    
    
    Junction node0;
    Junction node1;
    Junction node2;
    ArrayList<Junction> nodeList;

    Segment segment1;
    Segment segment2;
    ArrayList<Segment> list1;
    ArrayList<Segment> list2;

    Section section1;
    Section section2;
    
    Segment segment3;
    Segment segment4;
    
    SimPathParcel simPathParcel1;
    SimPathParcel simPathParcel2;
    
    PathParcel pathParcel1;
    PathParcel pathParcel2;
    
    TrafficPattern trafficPattern;
    ArrayList<SimPathParcel> lstSimPathParcel;


    
    public SimVehicleTest() {
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
        
        gearList2 = new HashMap<>();
        gearList2.put(1, 10.00);

        
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
        
        vehicle4 = new CombustionVehicle("dummy4", //name
                "descricao2", //descricao
                "truck",//type
                "diesel",//fuel
                1100, //mass
                100,//load
                0.21,//drag
                1.1,//frontal area
                0.02,//rcc
                0.1,//wheel size
                velocityLimit1,//
                1200,//min rpm
                5900,//max rpm
                4.6,//final drive ratio
                gearList2,
                throttleList1);
        
        node0 = new Junction("node0");
        node1 = new Junction("node1");
        node2 = new Junction("node2");
        nodeList = new ArrayList();
        nodeList.add(node0);
        nodeList.add(node1);
        nodeList.add(node2);

        segment1 = new Segment(1, 100, 1.5, 3.2, 90, 0, 20);
        segment2 = new Segment(2, 148, 1.5, 3.2, 90, 0, 20);
        list1 = new ArrayList();
        list1.add(segment1);
        list1.add(segment2);
        
        segment3 = new Segment(1, 100, 0, 10, 90, 0, 30);
        segment4 = new Segment(2, 100, 0.5, 5, 90, 0, 20);
        list2 = new ArrayList();
        list2.add(segment3);
        list2.add(segment4);
        

        section1 = new Section(1,"E01", node0, node1, SectionTypology.regular_road, SectionDirection.bidirectional, 0, new Wind(20, 3), list1);
        section1.setToll(0);
        
        section2 = new Section(2,"E02", node1, node2, SectionTypology.highway, SectionDirection.bidirectional, 0, new Wind(-5, 3), list2);
        section2.setToll(0);
        
        segment3 = new Segment(1, 100, 0, 10, 90, 0, 30);
        
        simPathParcel1 = new SimPathParcel(section1);
        simPathParcel2 = new SimPathParcel(section2);
        
        lstSimPathParcel = new ArrayList<>();
        lstSimPathParcel.add(simPathParcel1);
        lstSimPathParcel.add(simPathParcel2);
 

        trafficPattern = new TrafficPattern(1, node0, node2, vehicle1, 10);
        simVehicle = new SimVehicle(vehicle1, node0, node2, lstSimPathParcel, trafficPattern, 10.0);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of willEndAtThisTimeStep method, of class SimVehicle.
     */
    @Test
    public void testWillEndAtThisTimeStep() {
        System.out.println("willEndAtThisTimeStep");
        double currentTime = 2.0;
        SimVehicle instance = simVehicle;
        instance.setM_nextPos(simPathParcel1);
        boolean expResult = false;
        boolean result = instance.willEndAtThisTimeStep(currentTime);
        assertEquals(expResult, result);

    }

    /**
     * Test of endSimulation method, of class SimVehicle.
     */
    @Test
    public void testEndSimulation() {
        System.out.println("endSimulation");
        double currentTime = 2.0;
        SimVehicle instance = simVehicle;
        simPathParcel1.setSimExitTime(currentTime);
        
        double idleConsumption = vehicle1.getIdleConsumption(2.0);
        simPathParcel1.addToSimEnergyConsumption(idleConsumption);
        
        instance.setM_currentPos(simPathParcel1);
        boolean expResult = true;
        boolean result = instance.endSimulation(currentTime);
        assertEquals(expResult, result);
        

    }

    /**
     * Test of getPredictedExitTime method, of class SimVehicle.
     */
    @Test
    public void testGetPredictedExitTime() {
        System.out.println("getPredictedExitTime");
        SimVehicle instance = simVehicle;
        instance.setM_currentPos(simPathParcel1);
        double result = simVehicle.getPredictedExitTime();

        double expResult = 0.0;
        assertEquals(expResult, result, 0.0);

    }

    /**
     * Test of getNextPos method, of class SimVehicle.
     */
    @Test
    public void testGetNextPos() {
        System.out.println("getNextPos");
        SimVehicle instance = simVehicle;
        SimPathParcel expResult = null;
        SimPathParcel result = instance.getNextPos();
        assertEquals(expResult, result);

    }

    /**
     * Test of crossToNextPos method, of class SimVehicle.
     */
    @Test
    public void testCrossToNextPos() {
        System.out.println("crossToNextPos");
        double currentTime = 0.0;
        SimVehicle instance = simVehicle;
        boolean expResult = false;
        boolean result = instance.crossToNextPos(currentTime);
        assertEquals(expResult, result);

    }

    /**
     * Test of getStepInjectTime method, of class SimVehicle.
     */
    @Test
    public void testGetStepInjectTime() {
        System.out.println("getStepInjectTime");
        double stepInjectTime = 0.0;
        SimVehicle instance = simVehicle;
        instance.getStepInjectTime(stepInjectTime);

    }

    /**
     * Test of getInjectionTime method, of class SimVehicle.
     */
    @Test
    public void testGetInjectionTime() {
        System.out.println("getInjectionTime");
        SimVehicle instance = simVehicle;
        double result = instance.getInjectionTime();
        double expResult = 10.0;
        assertEquals(expResult, result, 0.0);
  
    }

    /**
     * Test of getFirstSimPathParcel method, of class SimVehicle.
     */
    @Test
    public void testGetFirstSimPathParcel() {
        System.out.println("getFirstSimPathParcel");
        SimVehicle instance = simVehicle;
        SimPathParcel result = instance.getFirstSimPathParcel();
        SimPathParcel expResult = simPathParcel1;
        
        assertEquals(expResult, result);

    }

    /**
     * Test of drop method, of class SimVehicle.
     */
    @Test
    public void testDrop() {
        System.out.println("drop");
        SimVehicle instance = simVehicle;
        instance.drop();

    }

    /**
     * Test of isDropped method, of class SimVehicle.
     */
    @Test
    public void testIsDropped() {
        System.out.println("isDropped");
        SimVehicle instance = simVehicle;
        boolean expResult = false;
        boolean result = instance.isDropped();
        assertEquals(expResult, result);

    }

    /**
     * Test of getdroppedTime method, of class SimVehicle.
     */
    @Test
    public void testGetdroppedTime() {
        System.out.println("getdroppedTime");
        SimVehicle instance = simVehicle;
        double result = instance.getdroppedTime();
        double expResult = -1.0;
        assertEquals(expResult, result, 0.0);

    }

    /**
     * Test of setInjected method, of class SimVehicle.
     */
    @Test
    public void testSetInjected() {
        System.out.println("setInjected");
        SimVehicle instance = simVehicle;
        instance.setInjected();

    }

    /**
     * Test of updatePredictedExitTime method, of class SimVehicle.
     */
    @Test
    public void testUpdatePredictedExitTime() {
        System.out.println("updatePredictedExitTime");
        double expResult = 2.0;
        SimVehicle instance = simVehicle;
        simPathParcel1.setPredictedExitTime(expResult);
        instance.setM_currentPos(simPathParcel1);
        instance.updatePredictedExitTime(expResult);
        double result = instance.getPredictedExitTime();
        assertEquals(expResult, result,0);
    }

    /**
     * Test of getVehiclePK method, of class SimVehicle.
     */
    @Test
    public void testGetVehiclePK() {
        System.out.println("getVehiclePK");
        SimVehicle instance = simVehicle;
        int expResult = 0;
        int result = instance.getVehiclePK();
        assertEquals(expResult, result);

    }

    /**
     * Test of getTrafficPattern method, of class SimVehicle.
     */
    @Test
    public void testGetTrafficPattern() {
        System.out.println("getTrafficPattern");
        SimVehicle instance = simVehicle;
        TrafficPattern expResult = trafficPattern;
        TrafficPattern result = instance.getTrafficPattern();
        assertEquals(expResult, result);

    }

    /**
     * Test of getPath method, of class SimVehicle.
     */
    @Test
    public void testGetPath() {
        System.out.println("getPath");
        SimVehicle instance = simVehicle;
        ArrayList<SimPathParcel> expResult = lstSimPathParcel;
        ArrayList<SimPathParcel> result = instance.getPath();
        assertEquals(expResult, result);

    }

    /**
     * Test of getVehicle method, of class SimVehicle.
     */
    @Test
    public void testGetVehicle() {
        System.out.println("getVehicle");
        SimVehicle instance = simVehicle;
        Vehicle expResult = vehicle1;
        Vehicle result = instance.getVehicle();
        assertEquals(expResult, result);

    }

    /**
     * Test of getOriginNode method, of class SimVehicle.
     */
    @Test
    public void testGetOriginNode() {
        System.out.println("getOriginNode");
        SimVehicle instance = simVehicle;
        Junction expResult = node0;
        Junction result = instance.getOriginNode();
        assertEquals(expResult, result);

    }

    /**
     * Test of getDestinyNode method, of class SimVehicle.
     */
    @Test
    public void testGetDestinyNode() {
        System.out.println("getDestinyNode");
        SimVehicle instance = simVehicle;
        Junction expResult = node2;
        Junction result = instance.getDestinyNode();
        assertEquals(expResult, result);

    }
    
}
