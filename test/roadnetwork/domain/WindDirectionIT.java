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
public class WindDirectionIT {
    
    public WindDirectionIT() {
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
     * Test of getAngle method, of class WindDirection.
     */
    @Test
    public void testGetAngle() {
        System.out.println("getAngle");
        WindDirection instance = new WindDirection();
        double expResult = 0.0;
        double result = instance.getAngle();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setAngle method, of class WindDirection.
     */
    @Test
    public void testSetAngle() {
        System.out.println("setAngle");
        double angle = 0.0;
        WindDirection instance = new WindDirection();
        instance.setAngle(angle);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getVelocity method, of class WindDirection.
     */
    @Test
    public void testGetVelocity() {
        System.out.println("getVelocity");
        WindDirection instance = new WindDirection();
        double expResult = 0.0;
        double result = instance.getVelocity();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setVelocity method, of class WindDirection.
     */
    @Test
    public void testSetVelocity() {
        System.out.println("setVelocity");
        double velocity = 0.0;
        WindDirection instance = new WindDirection();
        instance.setVelocity(velocity);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hashCode method, of class WindDirection.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        WindDirection instance = new WindDirection();
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of equals method, of class WindDirection.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = null;
        WindDirection instance = new WindDirection();
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class WindDirection.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        WindDirection instance = new WindDirection();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
