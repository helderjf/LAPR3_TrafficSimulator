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
public class WindTest {
    
    Wind w1;
    Wind w2;
    
    public WindTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        w1=new Wind(20, 3);
        w2=new Wind(-5, 3);
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
        Wind instance = w1;
        double expResult = 20.0;
        double result = instance.getAngle();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setAngle method, of class Wind.
     */
    @Test
    public void testSetAngle() {
        System.out.println("setAngle");
        double angle = 30.0;
        Wind instance = w1;
        instance.setAngle(angle);
        double expResult=30.0;
        double result=instance.getAngle();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getVelocity method, of class Wind.
     */
    @Test
    public void testGetVelocity() {
        System.out.println("getVelocity");
        Wind instance = w1;
        double expResult = 3.0;
        double result = instance.getVelocity();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setVelocity method, of class Wind.
     */
    @Test
    public void testSetVelocity() {
        System.out.println("setVelocity");
        double velocity = 10.0;
        Wind instance = w1;
        instance.setVelocity(velocity);
        double expResult=10.0;
        double result=instance.getVelocity();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of equals method, of class Wind.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Wind wind = w2;
        Wind instance = w1;
        boolean expResult = false;
        boolean result = instance.equals(wind);
        assertEquals(expResult, result);
        
        wind =new Wind(20,0);
        expResult = false;
        result = instance.equals(wind);
        assertEquals(expResult, result);
        
        wind.setVelocity(3);
        expResult = true;
        result = instance.equals(wind);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Wind.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Wind instance = w1;
        String expResult = "WindDirection{angle=20.0, velocity=3.0}";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
}
