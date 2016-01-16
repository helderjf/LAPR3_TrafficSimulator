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
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

/**
 *
 * @author antonio
 */
public class TrafficPatternTest {
    
    TrafficPattern trafficPattern;
    
    Junction node0;
    Junction node1;
    Junction node2;
    
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
    
    public TrafficPatternTest() {
        

    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
        
        node0 = new Junction(0,"node0");
        node1 = new Junction(1,"node1");
        node2 = new Junction(2,"node2");
        
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
        
        
        
        trafficPattern = new TrafficPattern(1, node0, node2, vehicle1, 10);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of arrivalRateInVehiclesPerSeconds method, of class TrafficPattern.
     */
    @Test
    public void testArrivalRateInVehiclesPerSeconds() {
        System.out.println("arrivalRateInVehiclesPerSeconds");
        String arrivalString = "1 1/s";
        TrafficPattern instance = trafficPattern;
        double result = instance.arrivalRateInVehiclesPerSeconds(arrivalString);
        double expResult = 1.0;
        assertEquals(expResult, result, 0.0);
        //TEST 2 (value)
        arrivalString = "25 1/s";
        expResult = 25.0;
        result = instance.arrivalRateInVehiclesPerSeconds(arrivalString);
        assertEquals(expResult, result, 0.0);
        //TEST 3 (minutes)
        arrivalString = "60 1/m";
        expResult = 1.0;
        result = instance.arrivalRateInVehiclesPerSeconds(arrivalString);
        assertEquals(expResult, result, 0.0);
        //TEST 3 (hours)
        arrivalString = "1800 1/h";
        expResult = 0.5;
        result = instance.arrivalRateInVehiclesPerSeconds(arrivalString);
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of validate method, of class TrafficPattern.
     */
    @Test
    public void testValidate() {
        System.out.println("validate");
        TrafficPattern instance = new TrafficPattern();
        //False case
        boolean expResult = false;
        boolean result = instance.validate();
        assertEquals(expResult, result);
        //True case
        instance.setBeginNode(new Junction());
        instance.setEndNode(new Junction());
        instance.setVehicle(new HybridVehicle());
        instance.setArrivalRate(0.0111);
        expResult = true;
        result = instance.validate();
        assertEquals(expResult, result);
    }

    /**
     * Test of trafficPatternPseudoCopy method, of class TrafficPattern.
     */
    @Test
    public void testTrafficPatternPseudoCopy() {
        System.out.println("trafficPatternPseudoCopy");
        TrafficPattern otherTrafficPatern = trafficPattern;
        TrafficPattern auxResult = TrafficPattern.trafficPatternPseudoCopy(otherTrafficPatern);
        TrafficPattern auxExpResult = trafficPattern;
        String result = ""+auxResult.getVehicle() + auxResult.getBeginNode() + auxResult.getEndNode() + auxResult.getArrivalRate();
        String expResult = ""+auxExpResult.getVehicle() + auxExpResult.getBeginNode() + auxExpResult.getEndNode() + auxExpResult.getArrivalRate();
        
        assertEquals(expResult, result);
    }

    /**
     * Test of getBeginNode method, of class TrafficPattern.
     */
    @Test
    public void testGetBeginNode() {
        System.out.println("getBeginNode");
        TrafficPattern instance = trafficPattern;
        Junction expResult = node0;
        Junction result = instance.getBeginNode();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getEndNode method, of class TrafficPattern.
     */
    @Test
    public void testGetEndNode() {
        System.out.println("getEndNode");
        TrafficPattern instance = trafficPattern;
        Junction expResult = node2;
        Junction result = instance.getEndNode();
        assertEquals(expResult, result);

    }

    /**
     * Test of getVehicle method, of class TrafficPattern.
     */
    @Test
    public void testGetVehicle() {
        System.out.println("getVehicle");
        TrafficPattern instance = trafficPattern;
        Vehicle expResult = vehicle1;
        Vehicle result = instance.getVehicle();
        assertEquals(expResult, result);

    }

    /**
     * Test of getArrivalRate method, of class TrafficPattern.
     */
    @Test
    public void testGetArrivalRate() {
        System.out.println("getArrivalRate");
        TrafficPattern instance = trafficPattern;
        double expResult = 10.0;
        double result = instance.getArrivalRate();
        assertEquals(expResult, result, 0.0);

    }

    /**
     * Test of setBeginNode method, of class TrafficPattern.
     */
    @Test
    public void testSetBeginNode() {
        System.out.println("setBeginNode");
        Junction beginNode = node0;
        TrafficPattern instance = trafficPattern;
        instance.setBeginNode(beginNode);
        Junction expResult = node0;
        Junction result = instance.getBeginNode();
        assertEquals(expResult, result);

    }

    /**
     * Test of setEndNode method, of class TrafficPattern.
     */
    @Test
    public void testSetEndNode() {
        System.out.println("setEndNode");
        Junction endNode = node2;
        TrafficPattern instance = trafficPattern;
        instance.setEndNode(endNode);
        Junction expResult = node2;
        Junction result = instance.getEndNode();
        assertEquals(expResult, result);
    }

    /**
     * Test of setVehicle method, of class TrafficPattern.
     */
    @Test
    public void testSetVehicle() {
        System.out.println("setVehicle");
        Vehicle vehicle = vehicle1;
        TrafficPattern instance = trafficPattern;
        instance.setVehicle(vehicle);
        Vehicle expResult = vehicle1;
        Vehicle result = instance.getVehicle();
        assertEquals(expResult, result);

    }

    /**
     * Test of setArrivalRate method, of class TrafficPattern.
     */
    @Test
    public void testSetArrivalRate_double() {
        System.out.println("setArrivalRate");
        double arrivalRate = 10.0;
        TrafficPattern instance = trafficPattern;
        instance.setArrivalRate(arrivalRate);
        double expResult = 10.0;
        double result = instance.getArrivalRate();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setArrivalRate method, of class TrafficPattern.
     */
    @Test
    public void testSetArrivalRate_String() {
        System.out.println("setArrivalRate");
        String arrivalRate = "10.0";
        TrafficPattern instance = trafficPattern;
        instance.setArrivalRate(arrivalRate);
        double expResult = 10.0;
        double result = instance.getArrivalRate();
        assertEquals(expResult, result, 0.0);
 
    }

    /**
     * Test of getPK method, of class TrafficPattern.
     */
    @Test
    public void testGetPK() {
        System.out.println("getPK");
        TrafficPattern instance = trafficPattern;
        int expResult = 1;
        int result = instance.getPK();
        assertEquals(expResult, result);

    }

    /**
     * Test of setPK method, of class TrafficPattern.
     */
    @Test
    public void testSetPK() {
        System.out.println("setPK");
        int pk = 1;
        TrafficPattern instance = trafficPattern;
        instance.setPK(pk);
        int expResult = 1;
        int result = instance.getPK();
        assertEquals(expResult, result);
    }

    /**
     * Test of hasPK method, of class TrafficPattern.
     */
    @Test
    public void testHasPK() {
        System.out.println("hasPK");
        TrafficPattern instance = trafficPattern;
        boolean expResult = true;
        boolean result = instance.hasPK();
        assertEquals(expResult, result);
    
    }
    
}
