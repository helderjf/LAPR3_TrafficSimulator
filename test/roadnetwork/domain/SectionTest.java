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

        section1 = new Section("E01", node0, node1, SectionTypology.regular_road, SectionDirection.bidirectional, 0, new Wind(20, 3), list1);
        section1.setToll(0);
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
        Section instance = new Section();
        String expResult = "";
        String result = instance.getRoadName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getBeginningNode method, of class Section.
     */
    @Test
    public void testGetBeginningNode() {
        System.out.println("getBeginningNode");
        Section instance = new Section();
        Junction expResult = null;
        Junction result = instance.getBeginningNode();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setBeginningNode method, of class Section.
     */
    @Test
    public void testSetBeginningNode() {
        System.out.println("setBeginningNode");
        Junction beginningNode = null;
        Section instance = new Section();
        instance.setBeginningNode(beginningNode);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEndingNode method, of class Section.
     */
    @Test
    public void testGetEndingNode() {
        System.out.println("getEndingNode");
        Section instance = new Section();
        Junction expResult = null;
        Junction result = instance.getEndingNode();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setEndingNode method, of class Section.
     */
    @Test
    public void testSetEndingNode() {
        System.out.println("setEndingNode");
        Junction endingNode = null;
        Section instance = new Section();
        instance.setEndingNode(endingNode);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTypology method, of class Section.
     */
    @Test
    public void testGetTypology() {
        System.out.println("getTypology");
        Section instance = new Section();
        SectionTypology expResult = null;
        SectionTypology result = instance.getTypology();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTypology method, of class Section.
     */
    @Test
    public void testSetTypology() {
        System.out.println("setTypology");
        SectionTypology typology = null;
        Section instance = new Section();
        instance.setTypology(typology);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDirection method, of class Section.
     */
    @Test
    public void testGetDirection() {
        System.out.println("getDirection");
        Section instance = new Section();
        SectionDirection expResult = null;
        SectionDirection result = instance.getDirection();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDirection method, of class Section.
     */
    @Test
    public void testSetDirection() {
        System.out.println("setDirection");
        SectionDirection direction = null;
        Section instance = new Section();
        instance.setDirection(direction);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getToll method, of class Section.
     */
    @Test
    public void testGetToll() {
        System.out.println("getToll");
        Section instance = new Section();
        double expResult = 0.0;
        double result = instance.getToll();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setToll method, of class Section.
     */
    @Test
    public void testSetToll() {
        System.out.println("setToll");
        double toll = 0.0;
        Section instance = new Section();
        instance.setToll(toll);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getWind method, of class Section.
     */
    @Test
    public void testGetWind() {
        System.out.println("getWind");
        Section instance = new Section();
        Wind expResult = null;
        Wind result = instance.getWind();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setWind method, of class Section.
     */
    @Test
    public void testSetWind() {
        System.out.println("setWind");
        Wind wind = null;
        Section instance = new Section();
        instance.setWind(wind);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSegmentsList method, of class Section.
     */
    @Test
    public void testGetSegmentsList() {
        System.out.println("getSegmentsList");
        Section instance = new Section();
        ArrayList<Segment> expResult = null;
        ArrayList<Segment> result = instance.getSegmentsList();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSegmentsList method, of class Section.
     */
    @Test
    public void testSetSegmentsList() {
        System.out.println("setSegmentsList");
        ArrayList<Segment> segmentsList = null;
        Section instance = new Section();
        instance.setSegmentsList(segmentsList);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of equals method, of class Section.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = null;
        Section instance = new Section();
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Section.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Section instance = new Section();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setRoadName method, of class Section.
     */
    @Test
    public void testSetRoadName() {
        System.out.println("setRoadName");
        String roadId = "";
        Section instance = new Section();
        instance.setRoadName(roadId);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSectionType method, of class Section.
     */
    @Test
    public void testGetSectionType() {
        System.out.println("getSectionType");
        Section instance = new Section();
        SectionTypology expResult = null;
        SectionTypology result = instance.getSectionType();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPK method, of class Section.
     */
    @Test
    public void testSetPK() {
        System.out.println("setPK");
        int pk = 0;
        Section instance = new Section();
        instance.setPK(pk);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPK method, of class Section.
     */
    @Test
    public void testGetPK() {
        System.out.println("getPK");
        Section instance = new Section();
        int expResult = 0;
        int result = instance.getPK();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
