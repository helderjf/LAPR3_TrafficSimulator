/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadnetwork.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
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
public class SimSegmentTest {
    
    SimSegment simSegment;
    
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
    
    public SimSegmentTest() {
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
        
        simSegment = new SimSegment(section1, segment1, SimDirection.direct);
        
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getVehicleQueue method, of class SimSegment.
     */
    @Test
    public void testGetVehicleQueue() {
        System.out.println("getVehicleQueue");
        SimSegment instance = simSegment;
        Queue<SimVehicle> expResult = new ArrayBlockingQueue(segment1.getMax_Vehicles());
        instance.setM_vehicleQueue(expResult);
        Queue<SimVehicle> result = instance.getVehicleQueue();
        assertEquals(expResult, result);
    }

    /**
     * Test of getSegment method, of class SimSegment.
     */
    @Test
    public void testGetSegment() {
        System.out.println("getSegment");
        SimSegment instance = simSegment;
        Segment expResult = segment1;
        Segment result = instance.getSegment();
        assertEquals(expResult, result);

    }

    /**
     * Test of getDirection method, of class SimSegment.
     */
    @Test
    public void testGetDirection() {
        System.out.println("getDirection");
        SimSegment instance = simSegment;
        SimDirection expResult = SimDirection.direct;
        SimDirection result = instance.getDirection();
        assertEquals(expResult, result);

    }

    /**
     * Test of canAddVehicle method, of class SimSegment.
     */
    @Test
    public void testCanAddVehicle() {
        System.out.println("canAddVehicle");
        Queue<SimVehicle> qsv = new ArrayBlockingQueue(segment1.getMax_Vehicles());
        SimSegment instance = simSegment;
        boolean expResult = qsv.size() < segment1.getMax_Vehicles();  
        boolean result = instance.canAddVehicle();
        assertEquals(expResult, result);

    }

    /**
     * Test of popCrossingVehicle method, of class SimSegment.
     */
    @Test
    public void testPopCrossingVehicle() {
        System.out.println("popCrossingVehicle");
        Queue<SimVehicle> qsv = new ArrayBlockingQueue(segment1.getMax_Vehicles());
        SimSegment instance = simSegment;
        SimVehicle expResult = qsv.poll();
        SimVehicle result = instance.popCrossingVehicle();
        assertEquals(expResult, result);

    }

    /**
     * Test of pushCrossingVehicle method, of class SimSegment.
     */
    @Test
    public void testPushCrossingVehicle() {
        System.out.println("pushCrossingVehicle");
        Queue<SimVehicle> qsv = new ArrayBlockingQueue(segment1.getMax_Vehicles());
        SimVehicle vehicle = simVehicle;
        SimSegment instance = simSegment;
        boolean expResult = true;
        boolean result = instance.pushCrossingVehicle(simVehicle);
        assertEquals(expResult, result);

    }

    /**
     * Test of injectCreatedVehicle method, of class SimSegment.
     */
    @Test
    public void testInjectCreatedVehicle() {
        System.out.println("injectCreatedVehicle");
        SimVehicle vehicle = simVehicle;
        SimSegment instance = simSegment;
        instance.injectCreatedVehicle(vehicle);

    }

    /**
     * Test of getCruisingVehicles method, of class SimSegment.
     */
    @Test
    public void testGetCruisingVehicles() {
        System.out.println("getCruisingVehicles");
        SimSegment instance = simSegment;
        Queue<SimVehicle> expResult = new ArrayBlockingQueue(segment1.getMax_Vehicles());
        instance.setM_vehicleQueue(expResult);
        Queue<SimVehicle> result = instance.getCruisingVehicles();
        assertEquals(expResult, result);

    }
    
}
