/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphutils;

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
public class EdgeTest {
    
    Edge e1;
    Edge e2;
    Vertex v1;
    Vertex v2;
    
    public EdgeTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        
        e1 = new Edge(2,10,null,null);
        v1 = new Vertex(1,1);
        v2 = new Vertex(2,2);
        e2 = new Edge(2,10,v1,v2);

    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getElement method, of class Edge.
     */
    @Test
    public void testGetElement() {
        System.out.println("getElement");
        Edge instance = e1;
        Integer expResult = 2;
        Object result = instance.getElement();
        assertEquals(expResult, result);

    }

    /**
     * Test of setElement method, of class Edge.
     */
    @Test
    public void testSetElement() {
        System.out.println("setElement");
        Object eInf = 2;
        Edge instance = new Edge();
        instance.setElement(eInf);
        Integer expResult = 2;
        Object result = instance.getElement();
        assertEquals(expResult, result);
    }

    /**
     * Test of getWeight method, of class Edge.
     */
    @Test
    public void testGetWeight() {
        System.out.println("getWeight");
        Edge instance = e1;
        double expResult = 10;
        double result = instance.getWeight();
        assertEquals(expResult, result, 0);
    }

    /**
     * Test of setWeight method, of class Edge.
     */
    @Test
    public void testSetWeight() {
        System.out.println("setWeight");
        double ew = 10.0;
        Edge instance = new Edge();
        instance.setWeight(ew);
        double expResult = 10;
        double result = instance.getWeight();
        assertEquals(expResult, result, 0);
    }

    /**
     * Test of getVOrig method, of class Edge.
     */
    @Test
    public void testGetVOrig() {
        System.out.println("getVOrig");
        Edge instance = new Edge(2,10,v1,null);
        Vertex expResult = v1;
        Vertex result = instance.getVOrig();
        assertEquals(expResult, result);
    }

    /**
     * Test of setVOrig method, of class Edge.
     */
    @Test
    public void testSetVOrig() {
        System.out.println("setVOrig");
        Edge instance = new Edge();
        instance.setVOrig(v1);
        Vertex expResult = v1;
        Vertex result = instance.getVOrig();
        assertEquals(expResult, result);
    }

    /**
     * Test of getVDest method, of class Edge.
     */
    @Test
    public void testGetVDest() {
        System.out.println("getVDest");
        Edge instance = new Edge(2,10,null,v1);
        Vertex expResult = v1;
        Vertex result = instance.getVDest();
        assertEquals(expResult, result);
    }

    /**
     * Test of setVDest method, of class Edge.
     */
    @Test
    public void testSetVDest() {
        System.out.println("setVDest");
        Edge instance = new Edge();
        instance.setVDest(v1);
        Vertex expResult = v1;
        Vertex result = instance.getVDest();
        assertEquals(expResult, result);
    }

    /**
     * Test of getEndpoints method, of class Edge.
     */
    @Test
    public void testGetEndpoints() {
        System.out.println("getEndpoints");
        Edge instance = new Edge(1,10,v1,v2);
        Vertex[] expResult = new Vertex[2];
        expResult[0]=v1;
        expResult[1]=v2;
        Vertex[] result = instance.getEndpoints();
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Edge.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object otherObj = e1;
        Edge instance = e1;
        boolean expResult = true;
        boolean result = instance.equals(otherObj);
        assertEquals(expResult, result);
    }

    /**
     * Test of compareTo method, of class Edge.
     */
    @Test
    public void testCompareTo() {
        System.out.println("compareTo");
        Object otherObject = e1;
        Edge instance = e1;
        int expResult = 0;
        int result = instance.compareTo(otherObject);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Edge.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Edge instance = e2;
        String expResult = "	- (2)10.0 - 2";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
}
