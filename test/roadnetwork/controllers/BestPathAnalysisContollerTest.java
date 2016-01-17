/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadnetwork.controllers;

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
import roadnetwork.domain.FastestPathAlgorithm;
import roadnetwork.domain.Junction;
import roadnetwork.domain.Manager;
import roadnetwork.domain.Project;
import roadnetwork.domain.Regime;
import roadnetwork.domain.Result;
import roadnetwork.domain.Road;
import roadnetwork.domain.RoadNetwork;
import roadnetwork.domain.Section;
import roadnetwork.domain.SectionDirection;
import roadnetwork.domain.SectionTypology;
import roadnetwork.domain.Segment;
import roadnetwork.domain.Throttle;
import roadnetwork.domain.Vehicle;
import roadnetwork.domain.Wind;
import roadnetwork.state.ProjectStateSimulationReady;

/**
 *
 * @author Andre
 */
public class BestPathAnalysisContollerTest {
    
    BestPathAnalysisContoller controller;
    Manager manager;
    
    Junction node0;
    Junction node1;
    Junction node2;
    ArrayList<Junction> nodeList;

    Segment segment1;
    Segment segment2;
    ArrayList<Segment> list1;

    Section section1;

    Segment segment3;
    Segment segment4;
    ArrayList<Segment> list2;

    Section section2;
    ArrayList<Section> sectionlist1;

    Road road1;

    Segment segment5;
    ArrayList<Segment> list3;
    Section section3;
    ArrayList<Section> sectionlist2;

    Road road2;

    Segment segment6;
    ArrayList<Segment> list4;
    Section section4;
    ArrayList<Section> sectionlist3;

    Road road3;

    ArrayList<Section> completeSectionList;

    RoadNetwork roadNetwork1;

    HashMap<SectionTypology, Double> velocityLimit;
    HashMap<Integer, Double> gearList;

    Regime r1;
    Regime r2;
    Regime r3;
    ArrayList<Regime> lr1;
    Throttle t1;

    Regime r4;
    Regime r5;
    Regime r6;
    ArrayList<Regime> lr2;
    Throttle t2;

    Regime r7;
    Regime r8;
    Regime r9;
    ArrayList<Regime> lr3;
    Throttle t3;

    ArrayList<Throttle> throttleList1;

    Vehicle vehicle1;

    ArrayList<Vehicle> vehicleList;
    
    Project project1;
    Project project2;
    
    public BestPathAnalysisContollerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        manager= new Manager("Manager Test");
        controller = new BestPathAnalysisContoller(manager);
        
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

        section1 = new Section("S01", node0, node1, SectionTypology.regular_road, SectionDirection.bidirectional, 0, new Wind(20, 3), list1);
        section1.setToll(0);
        

        segment3 = new Segment(1, 100, 0, 10, 90, 0, 30);
        segment4 = new Segment(2, 100, 0.5, 5, 90, 0, 20);
        list2 = new ArrayList();
        list2.add(segment3);
        list2.add(segment4);

        section2 = new Section("S02", node1, node2, SectionTypology.regular_road, SectionDirection.bidirectional, 0, new Wind(-5, 3), list2);
        section2.setToll(0);
        sectionlist1 = new ArrayList();
        sectionlist1.add(section1);
        sectionlist1.add(section2);

        road1 = new Road(1, "R01");
        road1.setSections(sectionlist1);

        segment5 = new Segment(1, 100, 0, 100, 120, 50, 100);
        list3 = new ArrayList();
        list3.add(segment5);
        section3 = new Section("S03", node0, node2, SectionTypology.highway, SectionDirection.bidirectional, 12, new Wind(-5, 3), list3);
        section3.setToll(12);
        sectionlist2 = new ArrayList();
        sectionlist2.add(section3);

        road2 = new Road(2, "R02");
        road2.setSections(sectionlist2);

        segment6 = new Segment(1, 100, 0.125, 10, 61, 50, 100);
        list4 = new ArrayList();
        list4.add(segment6);
        section4 = new Section("S04", node1, node2, SectionTypology.highway, SectionDirection.bidirectional, 4, new Wind(-5, 3), list4);
        section4.setToll(4);
        sectionlist3 = new ArrayList();
        sectionlist3.add(section4);

        road3 = new Road(3, "R03");
        road3.setSections(sectionlist3);

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

        velocityLimit = new HashMap();
        velocityLimit.put(SectionTypology.highway, 60d);
        gearList = new HashMap<>();
        gearList.put(1, 3.5);
        gearList.put(2, 2.5);
        gearList.put(3, 1.25);
        gearList.put(4, 0.9);

        r1 = new Regime(85, 1000, 2499, 8.2);
        r2 = new Regime(95, 2500, 3999, 6.2);
        r3 = new Regime(80, 4000, 5500, 10.2);
        lr1 = new ArrayList();
        lr1.add(r1);
        lr1.add(r2);
        lr1.add(r3);
        t1 = new Throttle("25", lr1);

        r4 = new Regime(135, 1000, 2499, 5.2);
        r5 = new Regime(150, 2500, 3999, 3.2);
        r6 = new Regime(140, 4000, 5500, 8.2);
        lr2 = new ArrayList();
        lr2.add(r4);
        lr2.add(r5);
        lr2.add(r6);
        t2 = new Throttle("50", lr2);

        r7 = new Regime(200, 1000, 2499, 2.2);
        r8 = new Regime(240, 2500, 3999, 1.2);
        r9 = new Regime(190, 4000, 5500, 4.2);
        lr3 = new ArrayList();
        lr3.add(r7);
        lr3.add(r8);
        lr3.add(r9);
        t3 = new Throttle("100", lr3);

        throttleList1 = new ArrayList();
        throttleList1.add(t1);
        throttleList1.add(t2);
        throttleList1.add(t3);

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
                velocityLimit,//
                1000,//min rpm
                5500,//max rpm
                2.6,//final drive ratio
                gearList,
                throttleList1);

        vehicleList = new ArrayList();
        vehicleList.add(vehicle1);
        
        project1 = new Project();
        project1.setName("Project 1");
        project1.setDescription("Test ptoject");
        project1.setRoadNetwork(roadNetwork1);
        project1.setVehicleList(vehicleList);
        project1.setState(new ProjectStateSimulationReady(project1));

        manager.setCurrentProject(project1);
        
        project2= new Project();
        project2.setName("Project 2");
        project2.setDescription("Test ptoject innactive");
        
        
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of projectActive method, of class BestPathAnalysisContoller.
     */
    @Test
    public void testProjectActive() {
        System.out.println("projectActive");
        BestPathAnalysisContoller instance = controller;
        boolean expResult = true;
        boolean result = instance.projectActive();
        assertEquals(expResult, result);
    }

    /**
     * Test of newAnalysis method, of class BestPathAnalysisContoller.
     */
    @Test
    public void testNewAnalysis() {
        System.out.println("newAnalysis");
        BestPathAnalysisContoller instance = controller;
        boolean expResult = true;
        instance.projectActive();
        boolean result = instance.newAnalysis();
        assertEquals(expResult, result);
    }

    /**
     * Test of newBestPathAnalysis method, of class BestPathAnalysisContoller.
     */
    @Test
    public void testNewBestPathAnalysis() {
        System.out.println("newBestPathAnalysis");
        BestPathAnalysisContoller instance = controller;
        instance.projectActive();
        instance.newAnalysis();
        ArrayList<Vehicle> expResult = new ArrayList<>();
        expResult.add(vehicle1);
        ArrayList<Vehicle> result = instance.newBestPathAnalysis();
        assertEquals(expResult, result);
    }

    /**
     * Test of setVehicle method, of class BestPathAnalysisContoller.
     */

    /**
     * Test of getNodeList method, of class BestPathAnalysisContoller.
     */
    @Test
    public void testGetNodeList() {
        System.out.println("getNodeList");
        BestPathAnalysisContoller instance = controller;
        instance.projectActive();
        ArrayList<Junction> expResult = nodeList;
        ArrayList<Junction> result = instance.getNodeList();
        assertEquals(expResult, result);
    }


    /**
     * Test of getBestPathAlgorithms method, of class BestPathAnalysisContoller.
     */
    @Test
    public void testGetBestPathAlgorithms() {
        System.out.println("getBestPathAlgorithms");
        BestPathAnalysisContoller instance = controller;
        int expResult = 3;
        ArrayList<BestPathAlgorithm> result = instance.getBestPathAlgorithms();
        assertEquals(expResult, result.size());
    }

    /**
     * Test of runAnalysis method, of class BestPathAnalysisContoller.
     */
    @Test
    public void testRunAnalysis() {
        System.out.println("runAnalysis");
        BestPathAnalysisContoller instance = controller;
        instance.projectActive();
        instance.newAnalysis();
        instance.setVehicle(vehicle1);
        instance.getNodeList();
        instance.setAnalysisNodes(node0, node1);
        instance.setAlgorithm(new FastestPathAlgorithm());
        String expResult = "Fastest path between Node node0 and Node node1 is:\n" +
                            "node0 ---> node1  @  S01\n" +
                            "";
        Result result = instance.runAnalysis();
        assertEquals(expResult, result.toString());
    }

    
}
