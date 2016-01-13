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
 * @author josemiranda
 */
public class JunctionTest {
    
    Junction node0;
    Junction node1;
    
    public JunctionTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        node0 = new Junction(0,"node0");
        node1 = new Junction(1,"node1");
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getJunctionId method, of class Junction.
     */
    @Test
    public void testGetJunctionId() {
        System.out.println("getJunctionId");
        Junction instance = node0;
        String expResult = "node0";
        String result = instance.getJunctionId();
        assertEquals(expResult, result);
    }


    /**
     * Test of equals method, of class Junction.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Junction junction = node0;
        Junction instance = node1;
        boolean expResult = false;
        boolean result = instance.equals(junction);
        assertEquals(expResult, result);
        
        junction = new Junction(1, "node1");
        expResult = true;
        result = instance.equals(junction);
        assertEquals(expResult, result);
    }

    /**
     * Test of getPK method, of class Junction.
     */
    @Test
    public void testGetPK() {
        System.out.println("getPK");
        Junction instance = node0;
        int expResult = 0;
        int result = instance.getPK();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPK method, of class Junction.
     */
    @Test
    public void testSetPK() {
        System.out.println("setPK");
        int pk = 20;
        Junction instance = node0;
        instance.setPK(pk);
        int expResult = 20;
        int result=instance.getPK();
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Junction.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Junction instance = node0;
        String expResult = "Node node0";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of setJunctionId method, of class Junction.
     */
    @Test
    public void testSetJunctionId() {
        System.out.println("setJunctionId");
        String junctionId = "new node id";
        Junction instance = node0;
        instance.setJunctionId(junctionId);
        String expResult = "new node id";
        String result = instance.getJunctionId();
        assertEquals(expResult, result);
    }
    
}
