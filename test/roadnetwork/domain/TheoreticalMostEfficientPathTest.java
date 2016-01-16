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
public class TheoreticalMostEfficientPathTest {
    
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
    
    public TheoreticalMostEfficientPathTest() {
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
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getBestPathResults method, of class TheoreticalMostEfficientPath.
     */
    @Test
    public void testGetBestPathResults() {
        System.out.println("getBestPathResults");
        
        Junction originNode = node0;
        Junction destinyNode = node2;
        Vehicle vehicle = vehicle1;
        FastestPathAlgorithm instance = new FastestPathAlgorithm();
        ResultStaticAnalysis expResult = new ResultStaticAnalysis(node0,node2);
        //ResultStaticAnalysis result = instance.getBestPathResults(roadNetwork1, originNode, destinyNode, vehicle1);
        
        //Origin and destiny nodes verification
        //assertEquals(expResult.getOriginNode(), result.getOriginNode());
        //assertEquals(expResult.getDestinyNode(), result.getDestinyNode());
        
        
        //Path PathParcel
        ArrayList<PathParcel> path = new ArrayList<>();
        StaticPathParcel pp1 = new StaticPathParcel(section1);
        pp1.setDirection(SimDirection.direct);
        pp1.setTheoreticalTravelTime(256);
        pp1.setTollCosts(0);
        //pp1.setTheoreticalEnergyConsumption();
        StaticPathParcel pp2 = new StaticPathParcel(section2);
        pp2.setDirection(SimDirection.direct);
        pp2.setTheoreticalTravelTime(600);
        pp2.setTollCosts(0);
        //pp2.setTheoreticalEnergyConsumption();
        path.add(pp1);
        path.add(pp2);
        expResult.setPath(path);
        //assertEquals(expResult.getPath().size(), result.getPath().size());
        //assertEquals(expResult.getPath(), result.getPath());

    }

    /**
     * Test of toString method, of class TheoreticalMostEfficientPath.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        TheoreticalMostEfficientPath instance = new TheoreticalMostEfficientPath();
        String expResult = "Theoretical Most Efficient Path";
        String result = instance.toString();
        assertEquals(expResult, result);

    }

    /**
     * Test of getBestPath method, of class TheoreticalMostEfficientPath.
     */
    @Test
    public void testGetBestPath() {
        System.out.println("getBestPath");
        RoadNetwork roadNetwork = roadNetwork1;
        Junction originNode = node0;
        Junction destinyNode = node2;
        Vehicle vehicle = vehicle1;
        FastestPathAlgorithm instance = new FastestPathAlgorithm();
        instance.getBestPath(roadNetwork, originNode, destinyNode, vehicle);

        
        String result=instance.showData();
        String expResult="Section{id=0, roadId=S01, beginningNode=Node node0, endingNode=Node node1, typology=regular_road, direction=bidirectional, toll=0.0, windDirection=WindDirection{angle=20.0, velocity=3.0}, segmentsList=[Segment{index=1, initial_Height=100.0, slope=1.5, lenght=3.2, max_Velocity=90.0, min_Velocity=0.0, max_Vehicles=20}, Segment{index=2, initial_Height=148.0, slope=1.5, lenght=3.2, max_Velocity=90.0, min_Velocity=0.0, max_Vehicles=20}]}Section{id=0, roadId=S02, beginningNode=Node node1, endingNode=Node node2, typology=regular_road, direction=bidirectional, toll=0.0, windDirection=WindDirection{angle=-5.0, velocity=3.0}, segmentsList=[Segment{index=1, initial_Height=100.0, slope=0.0, lenght=10.0, max_Velocity=90.0, min_Velocity=0.0, max_Vehicles=30}, Segment{index=2, initial_Height=100.0, slope=0.5, lenght=5.0, max_Velocity=90.0, min_Velocity=0.0, max_Vehicles=20}]}";


        assertEquals(expResult, result);
    }
    
}
