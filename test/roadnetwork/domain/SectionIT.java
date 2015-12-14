/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadnetwork.domain;

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
        Node expResult = null;
        Node result = instance.getBeginningNode();
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
        Node beginningNode = null;
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
        Node expResult = null;
        Node result = instance.getEndingNode();
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
        Node endingNode = null;
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
        String expResult = "";
        String result = instance.getTypology();
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
        String typology = "";
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
        String expResult = "";
        String result = instance.getDirection();
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
        String direction = "";
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
     * Test of getWindDirection method, of class Section.
     */
    @Test
    public void testGetWindDirection() {
        System.out.println("getWindDirection");
        Section instance = new Section();
        WindDirection expResult = null;
        WindDirection result = instance.getWindDirection();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setWindDirection method, of class Section.
     */
    @Test
    public void testSetWindDirection() {
        System.out.println("setWindDirection");
        WindDirection windDirection = null;
        Section instance = new Section();
        instance.setWindDirection(windDirection);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hashCode method, of class Section.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Section instance = new Section();
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
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
    
}
