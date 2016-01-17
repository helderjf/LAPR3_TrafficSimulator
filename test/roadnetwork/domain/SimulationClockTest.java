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
public class SimulationClockTest {
    
    SimulationClock simulationClock;
    
    Junction node0;
    Junction node1;
    Junction node2;
    ArrayList<Junction> nodeList;
    
    Section section1;
    Section section2;
    Section section3;
    Section section4;
    
    Segment segment1;
    Segment segment2;
    ArrayList<Segment> list1;



    Segment segment3;
    Segment segment4;
    ArrayList<Segment> list2;


    ArrayList<Section> sectionlist1;
    
    ArrayList<Section> completeSectionList;
    
    RoadNetwork roadNetwork1;
    
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

    
    SimPathParcel simPathParcel1;
    SimPathParcel simPathParcel2;
    
    PathParcel pathParcel1;
    PathParcel pathParcel2;
    
    TrafficPattern trafficPattern;
    ArrayList<SimPathParcel> lstSimPathParcel;
    
    ArrayList<SimVehicle> lstSimVehicle;
    
    
    public SimulationClockTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        
        
        
        
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
        
        
        section1 = new Section("S01", node0, node1, SectionTypology.regular_road, SectionDirection.bidirectional, 0, new Wind(20, 3), list1);
        section1.setToll(0);
        
        section2 = new Section("S02", node1, node2, SectionTypology.regular_road, SectionDirection.bidirectional, 0, new Wind(-5, 3), list2);
        section2.setToll(0);

        
        completeSectionList = new ArrayList();
        completeSectionList.add(section1);
        completeSectionList.add(section2);
        completeSectionList.add(section3);
        completeSectionList.add(section4);
        
        roadNetwork1 = new RoadNetwork();
        roadNetwork1.setName("road network1");
        roadNetwork1.setDescription("road network de testes 1");
        roadNetwork1.setNodeList(nodeList);
        roadNetwork1.setSectionList(completeSectionList);
        
        simulationClock = new SimulationClock(roadNetwork1);
        
        
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
        
        lstSimVehicle = new ArrayList<>();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of updateCurrentVehicles method, of class SimulationClock.
     */
    @Test
    public void testUpdateCurrentVehicles() {
        System.out.println("updateCurrentVehicles");
        double currentTime = 2.0;
        SimulationClock instance = simulationClock;
        ArrayList<SimVehicle> expResult = lstSimVehicle;
        
        ArrayList<SimSegment> simSegsOrderedByWaitingVehicle;
        
        ArrayList<SimVehicle> result = instance.updateCurrentVehicles(currentTime);
        assertEquals(expResult, result);

    }

    /**
     * Test of injectCreatedVehicles method, of class SimulationClock.
     */
    @Test
    public void testInjectCreatedVehicles() {
        System.out.println("injectCreatedVehicles");
        double currentTime = 0.0;
        ArrayList<SimVehicle> nextStepVehicles = null;
        SimulationClock instance = simulationClock;
        ArrayList<SimVehicle> expResult = null;
        ArrayList<SimVehicle> result = instance.injectCreatedVehicles(currentTime, nextStepVehicles);
        assertEquals(expResult, result);

    }

    /**
     * Test of endSimulation method, of class SimulationClock.
     */
    @Test
    public void testEndSimulation() {
        System.out.println("endSimulation");
        double time = 0.0;
        SimulationClock instance = simulationClock;
        ArrayList<SimVehicle> expResult = null;
        ArrayList<SimVehicle> result = instance.endSimulation(time);
        assertEquals(expResult, result);

    }
    
}
