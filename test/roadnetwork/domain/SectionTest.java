/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadnetwork.domain;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author josemiranda
 */
public class SectionTest {
    
    Junction node0;
    Junction node1;
    Junction node2;
    ArrayList<Junction> nodeList;

    Segment segment1;
    Segment segment2;
    ArrayList<Segment> list1;

    Section section1;
    
    Segment segment3;
    
    public SectionTest() {
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

        section1 = new Section(1,"E01", node0, node1, SectionTypology.regular_road, SectionDirection.bidirectional, 0, new Wind(20, 3), list1);
        section1.setToll(0);
        
        segment3 = new Segment(1, 100, 0, 10, 90, 0, 30);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getId method, of class Section.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Section instance = section1;
        int expResult = 1;
        int result = instance.getId();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getRoadName method, of class Section.
     */
    @Test
    public void testGetRoadName() {
        System.out.println("getRoadName");
        Section instance = section1;
        String expResult = "E01";
        String result = instance.getRoadName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getBeginningNode method, of class Section.
     */
    @Test
    public void testGetBeginningNode() {
        System.out.println("getBeginningNode");
        Section instance = section1;
        Junction expResult = node0;
        Junction result = instance.getBeginningNode();
        assertEquals(expResult, result);
    }

    /**
     * Test of setBeginningNode method, of class Section.
     */
    @Test
    public void testSetBeginningNode() {
        System.out.println("setBeginningNode");
        Junction beginningNode = node2;
        Section instance = section1;
        instance.setBeginningNode(beginningNode);
        Junction expResult = node2;
        Junction result = instance.getBeginningNode();
        assertEquals(expResult, result);
    }

    /**
     * Test of getEndingNode method, of class Section.
     */
    @Test
    public void testGetEndingNode() {
        System.out.println("getEndingNode");
        Section instance = section1;
        Junction expResult = node1;
        Junction result = instance.getEndingNode();
        assertEquals(expResult, result);
    }

    /**
     * Test of setEndingNode method, of class Section.
     */
    @Test
    public void testSetEndingNode() {
        System.out.println("setEndingNode");
        Junction endingNode = node2;
        Section instance = section1;
        instance.setEndingNode(endingNode);
        Junction expResult = node2;
        Junction result = instance.getEndingNode();
        assertEquals(expResult, result);
    }

    /**
     * Test of getTypology method, of class Section.
     */
    @Test
    public void testGetTypology() {
        System.out.println("getTypology");
        Section instance = section1;
        SectionTypology expResult = SectionTypology.regular_road;
        SectionTypology result = instance.getTypology();
        assertEquals(expResult, result);
    }

    /**
     * Test of setTypology method, of class Section.
     */
    @Test
    public void testSetTypology() {
        System.out.println("setTypology");
        SectionTypology typology = SectionTypology.highway;
        Section instance = section1;
        instance.setTypology(typology);
        SectionTypology expResult=SectionTypology.highway;
        SectionTypology result = instance.getTypology();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDirection method, of class Section.
     */
    @Test
    public void testGetDirection() {
        System.out.println("getDirection");
        Section instance = section1;
        SectionDirection expResult = SectionDirection.bidirectional;
        SectionDirection result = instance.getDirection();
        assertEquals(expResult, result);
    }

    /**
     * Test of setDirection method, of class Section.
     */
    @Test
    public void testSetDirection() {
        System.out.println("setDirection");
        SectionDirection direction = SectionDirection.unidirectional;
        Section instance = section1;
        instance.setDirection(direction);
        SectionDirection expResult = SectionDirection.unidirectional;
        SectionDirection result = instance.getDirection();
        assertEquals(expResult, result);
    }

    /**
     * Test of getToll method, of class Section.
     */
    @Test
    public void testGetToll() {
        System.out.println("getToll");
        Section instance = section1;
        double expResult = 0.0;
        double result = instance.getToll();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setToll method, of class Section.
     */
    @Test
    public void testSetToll() {
        System.out.println("setToll");
        double toll = 20;
        Section instance = section1;
        instance.setToll(toll);
        double expResult=20;
        double result=instance.getToll();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getWind method, of class Section.
     */
    @Test
    public void testGetWind() {
        System.out.println("getWind");
        Section instance = section1;
        Wind expResult = new Wind(20, 3);
        Wind result = instance.getWind();
        assertEquals(expResult, result);
    }

    /**
     * Test of setWind method, of class Section.
     */
    @Test
    public void testSetWind() {
        System.out.println("setWind");
        Wind wind = new Wind(50, 5);
        Section instance = section1;
        instance.setWind(wind);
        Wind expResult= new Wind(50, 5);
        Wind result = instance.getWind();
        assertEquals(expResult, result);
    }

    /**
     * Test of getSegmentsList method, of class Section.
     */
    @Test
    public void testGetSegmentsList() {
        System.out.println("getSegmentsList");
        Section instance = section1;
        ArrayList<Segment> expResult = new ArrayList<>();
        expResult.add(segment1);
        expResult.add(segment2);
        ArrayList<Segment> result = instance.getSegmentsList();
        assertEquals(expResult, result);
    }

    /**
     * Test of setSegmentsList method, of class Section.
     */
    @Test
    public void testSetSegmentsList() {
        System.out.println("setSegmentsList");
        ArrayList<Segment> segmentsList = new ArrayList<>();
        segmentsList.add(segment1);
        segmentsList.add(segment2);
        segmentsList.add(segment3);
        Section instance = section1;
        instance.setSegmentsList(segmentsList);
        ArrayList<Segment> result = instance.getSegmentsList();
        ArrayList<Segment> expResult = new ArrayList<>();
        expResult.add(segment1);
        expResult.add(segment2);
        expResult.add(segment3);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Section.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Section obj = null;
        Section instance = section1;
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
        
        Section section2 = new Section(1,"E01", node0, node1, SectionTypology.regular_road, SectionDirection.bidirectional, 0, new Wind(20, 3), list1);
        expResult=true;
        result=instance.equals(section2);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Section.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Section instance = section1;
        String expResult = "Section{id=1, roadId=E01, beginningNode=Node node0, "
                + "endingNode=Node node1, typology=regular_road, direction=bidirectional, "
                + "toll=0.0, windDirection=WindDirection{angle=20.0, velocity=3.0}, "
                + "segmentsList=[Segment{index=1, initial_Height=100.0, slope=1.5, lenght=3.2, "
                + "max_Velocity=90.0, min_Velocity=0.0, max_Vehicles=20}, "
                + "Segment{index=2, initial_Height=148.0, slope=1.5, lenght=3.2, max_Velocity=90.0, "
                + "min_Velocity=0.0, max_Vehicles=20}]}";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of setRoadName method, of class Section.
     */
    @Test
    public void testSetRoadName() {
        System.out.println("setRoadName");
        String roadId = "New Road id";
        Section instance = section1;
        instance.setRoadName(roadId);
        String result = instance.getRoadName();
        String expResult="New Road id";
        assertEquals(expResult, result);
    }

    /**
     * Test of setPK method, of class Section.
     */
    @Test
    public void testSetPK() {
        System.out.println("setPK");
        int pk = 20;
        Section instance = section1;
        instance.setPK(pk);
        int expResult = 20;
        int result=instance.getPK();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPK method, of class Section.
     */
    @Test
    public void testGetPK() {
        System.out.println("getPK");
        Section instance = section1;
        int expResult = 1;
        int result = instance.getPK();
        assertEquals(expResult, result);
    }
    
}
