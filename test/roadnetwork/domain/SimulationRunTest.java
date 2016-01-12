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
 * @author André Pedrosa, Hélder Faria, José Miranda, Rubén Rosário
 */
public class SimulationRunTest {
    
    private static String m_name = "sim run teste";
    private static double m_duration=3600;
    private static double m_timeStep=180;
    private static RoadNetwork roadNetwork1;
    private static BestPathAlgorithm m_bestPathMethod=new FastestPathAlgorithm();
    private static TrafficPattern m_trafficPattern;
    private static SimSegmentsManager m_simSegmentsManager;
    private static SimVehiclesGenerator m_simVehiclesGenerator;

    private static double m_currentTime=0;
    
    private static ArrayList<SimVehicle> m_endedVehicles;
    private static ArrayList<SimVehicle> m_droppedVehicles;
    private static ArrayList<SimVehicle> m_cruisingVehicles;
    
    private static ResultSimulation m_runResults;
    
    
    
    
    public SimulationRunTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        
         //CREATE MOCK OBJECTS
        Junction node0 = new Junction("node0");
        Junction node1 = new Junction("node1");
        Junction node2 = new Junction("node2");
        ArrayList<Junction> nodeList = new ArrayList();
        nodeList.add(node0);
        nodeList.add(node1);
        nodeList.add(node2);

        Segment segment1 = new Segment(1, 100, 1.5, 3.2, 90, 0, 20);
        Segment segment2 = new Segment(2, 148, 1.5, 3.2, 90, 0, 20);
        ArrayList<Segment> list1 = new ArrayList();
        list1.add(segment1);
        list1.add(segment2);

        Section section1 = new Section("E01", node0, node1, SectionTypology.regular_road, SectionDirection.bidirectional, 0, new Wind(20, 3), list1);
        section1.setToll(0);

        Segment segment3 = new Segment(1, 100, 0, 10, 90, 0, 30);
        Segment segment4 = new Segment(2, 100, 0.5, 5, 90, 0, 20);
        ArrayList<Segment> list2 = new ArrayList();
        list2.add(segment3);
        list2.add(segment4);

        Section section2 = new Section("E01", node1, node2, SectionTypology.regular_road, SectionDirection.bidirectional, 0, new Wind(-5, 3), list2);
        section2.setToll(0);
        ArrayList<Section> sectionlist1 = new ArrayList();
        sectionlist1.add(section1);
        sectionlist1.add(section2);

        Road road1 = new Road(1, "E01");
        road1.setSections(sectionlist1);

        Segment segment5 = new Segment(1, 100, 0, 100, 120, 50, 100);
        ArrayList<Segment> list3 = new ArrayList();
        list3.add(segment5);
        Section section3 = new Section("A01", node0, node2, SectionTypology.highway, SectionDirection.bidirectional, 12, new Wind(-5, 3), list3);
        section3.setToll(12);
        ArrayList<Section> sectionlist2 = new ArrayList();
        sectionlist2.add(section3);

        Road road2 = new Road(2, "A01");
        road2.setSections(sectionlist2);

        Segment segment6 = new Segment(1, 100, 0.125, 10, 61, 50, 100);
        ArrayList<Segment> list4 = new ArrayList();
        list4.add(segment6);
        Section section4 = new Section("A03", node1, node2, SectionTypology.highway, SectionDirection.bidirectional, 4, new Wind(-5, 3), list4);
        section4.setToll(4);
        ArrayList<Section> sectionlist3 = new ArrayList();
        sectionlist3.add(section4);

        Road road3 = new Road(3, "A03");
        road3.setSections(sectionlist3);

        ArrayList<Section> completeSectionList = new ArrayList();
        completeSectionList.add(section1);
        completeSectionList.add(section2);
        completeSectionList.add(section3);
        completeSectionList.add(section4);

        RoadNetwork roadNetwork1 = new RoadNetwork();
        roadNetwork1.setName("road network1");
        roadNetwork1.setDescription("road network de testes 1");
        roadNetwork1.setNodeList(nodeList);
        roadNetwork1.setSectionList(completeSectionList);

        HashMap<SectionTypology, Double> velocityLimit = new HashMap();
        velocityLimit.put(SectionTypology.highway, 60d);
        HashMap<Integer, Double> gearList = new HashMap<>();
        gearList.put(1, 3.5);
        gearList.put(2, 2.5);
        gearList.put(3, 1.25);
        gearList.put(4, 0.9);

        Regime r1 = new Regime(85, 1000, 2499, 8.2);
        Regime r2 = new Regime(95, 2500, 3999, 6.2);
        Regime r3 = new Regime(80, 4000, 5500, 10.2);
        ArrayList<Regime> lr1 = new ArrayList();
        lr1.add(r1);
        lr1.add(r2);
        lr1.add(r3);
        Throttle t1 = new Throttle("25", lr1);

        Regime r4 = new Regime(135, 1000, 2499, 5.2);
        Regime r5 = new Regime(150, 2500, 3999, 3.2);
        Regime r6 = new Regime(140, 4000, 5500, 8.2);
        ArrayList<Regime> lr2 = new ArrayList();
        lr2.add(r4);
        lr2.add(r5);
        lr2.add(r6);
        Throttle t2 = new Throttle("50", lr2);

        Regime r7 = new Regime(200, 1000, 2499, 2.2);
        Regime r8 = new Regime(240, 2500, 3999, 1.2);
        Regime r9 = new Regime(190, 4000, 5500, 4.2);
        ArrayList<Regime> lr3 = new ArrayList();
        lr3.add(r7);
        lr3.add(r8);
        lr3.add(r9);
        Throttle t3 = new Throttle("100", lr3);

        ArrayList<Throttle> throttleList1 = new ArrayList();
        throttleList1.add(t1);
        throttleList1.add(t2);
        throttleList1.add(t3);

        Vehicle vehicle1 = new CombustionVehicle("dummy1", //name
                "descricao", //descricao
                "car",//type
                "gasoline",//fuel
                1400, //mass
                120,//load
                0.35,//drag
                1.8,//frontal area
                0.01,//rcc
                0.5,//wheel size
                velocityLimit,//
                1000,//min rpm
                5500,//max rpm
                2.6,//final drive ratio
                gearList,
                throttleList1);

        
        
        
        
        
        
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of start method, of class SimulationRun.
     */
    @Test
    public void testStart() {
        System.out.println("start");
        SimulationRun instance = null;
        instance.start();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getResults method, of class SimulationRun.
     */
    @Test
    public void testGetResults() {
        System.out.println("getResults");
        SimulationRun instance = null;
        ResultSimulation expResult = null;
        ResultSimulation result = instance.getResults();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
