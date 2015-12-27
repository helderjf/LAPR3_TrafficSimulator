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
 * @author Andre
 */
public class SectionIT {
    
    public SectionIT() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
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
        Section instance = new Section();
        int expResult = 0;
        int result = instance.getId();
        assertEquals(expResult, result);

    }

    /**
     * Test of getRoadName method, of class Section.
     */
    @Test
    public void testGetRoadId() {
        System.out.println("getRoadId");
        Section instance = new Section();
        String expResult = "";
        String result = instance.getRoadName();
        assertEquals(expResult, result);
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

    }

    /**
     * Test of getDirection method, of class Section.
     */
    @Test
    public void testGetDirection() {
        System.out.println("getDirection");
        Section instance = new Section();
        Direction expResult = null;
        Direction result = instance.getDirection();
        assertEquals(expResult, result);

    }

    /**
     * Test of setDirection method, of class Section.
     */
    @Test
    public void testSetDirection() {
        System.out.println("setDirection");
        Direction direction = null;
        Section instance = new Section();
        instance.setDirection(direction);

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

    }

    /**
     * Test of setRoadName method, of class Section.
     */
    @Test
    public void testSetRoadId() {
        System.out.println("setRoadId");
        String roadId = "";
        Section instance = new Section();
        instance.setRoadName(roadId);

    }
    
}
