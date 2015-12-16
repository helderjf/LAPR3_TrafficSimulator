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
public class WindIT {
    
    public WindIT() {
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
     * Test of getAngle method, of class Wind.
     */
    @Test
    public void testGetAngle() {
        System.out.println("getAngle");
        Wind instance = new Wind();
        double expResult = 0.0;
        double result = instance.getAngle();
        assertEquals(expResult, result, 0.0);

    }

    /**
     * Test of setAngle method, of class Wind.
     */
    @Test
    public void testSetAngle() {
        System.out.println("setAngle");
        double angle = 0.0;
        Wind instance = new Wind();
        instance.setAngle(angle);

    }

    /**
     * Test of getVelocity method, of class Wind.
     */
    @Test
    public void testGetVelocity() {
        System.out.println("getVelocity");
        Wind instance = new Wind();
        double expResult = 0.0;
        double result = instance.getVelocity();
        assertEquals(expResult, result, 0.0);

    }

    /**
     * Test of setVelocity method, of class Wind.
     */
    @Test
    public void testSetVelocity() {
        System.out.println("setVelocity");
        double velocity = 0.0;
        Wind instance = new Wind();
        instance.setVelocity(velocity);

    }


    /**
     * Test of equals method, of class Wind.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = null;
        Wind instance = new Wind();
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Wind.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Wind instance = new Wind();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);

    }
    
}
