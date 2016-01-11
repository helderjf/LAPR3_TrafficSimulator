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
public class SegmentIT {
    
    public SegmentIT() {
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
     * Test of getId method, of class Segment.
     */
//    @Test
//    public void testGetId() {
//        System.out.println("getId");
//        Segment instance = new Segment();
//        int expResult = 0;
//        int result = instance.getId();
//        assertEquals(expResult, result);
//
//    }

    /**
     * Test of getIndex method, of class Segment.
     */
    @Test
    public void testGetIndex() {
        System.out.println("getIndex");
        Segment instance = new Segment();
        int expResult = 0;
        int result = instance.getIndex();
        assertEquals(expResult, result);

    }

    /**
     * Test of setIndex method, of class Segment.
     */
    @Test
    public void testSetIndex() {
        System.out.println("setIndex");
        int index = 0;
        Segment instance = new Segment();
        instance.setIndex(index);

    }

    /**
     * Test of getInitialHeight method, of class Segment.
     */
    @Test
    public void testGetInitial_Height() {
        System.out.println("getInitial_Height");
        Segment instance = new Segment();
        double expResult = 0.0;
        double result = instance.getInitialHeight();
        assertEquals(expResult, result, 0.0);

    }

    /**
     * Test of setInitialHeight method, of class Segment.
     */
    @Test
    public void testSetInitial_Height() {
        System.out.println("setInitial_Height");
        double initial_Height = 0.0;
        Segment instance = new Segment();
        instance.setInitialHeight(initial_Height);

    }

    /**
     * Test of getSlope method, of class Segment.
     */
    @Test
    public void testGetSlope() {
        System.out.println("getSlope");
        Segment instance = new Segment();
        double expResult = 0.0;
        double result = instance.getSlope();
        assertEquals(expResult, result, 0.0);

    }

    /**
     * Test of setSlope method, of class Segment.
     */
    @Test
    public void testSetSlope() {
        System.out.println("setSlope");
        double slope = 0.0;
        Segment instance = new Segment();
        instance.setSlope(slope);

    }

    /**
     * Test of getLenght method, of class Segment.
     */
    @Test
    public void testGetLenght() {
        System.out.println("getLenght");
        Segment instance = new Segment();
        double expResult = 0.0;
        double result = instance.getLenght();
        assertEquals(expResult, result, 0.0);

    }

    /**
     * Test of setLenght method, of class Segment.
     */
    @Test
    public void testSetLenght() {
        System.out.println("setLenght");
        double lenght = 0.0;
        Segment instance = new Segment();
        instance.setLenght(lenght);

    }

    /**
     * Test of getMax_Velocity method, of class Segment.
     */
    @Test
    public void testGetMax_Velocity() {
        System.out.println("getMax_Velocity");
        Segment instance = new Segment();
        double expResult = 0.0;
        double result = instance.getMax_Velocity();
        assertEquals(expResult, result, 0.0);

    }

    /**
     * Test of setMax_Velocity method, of class Segment.
     */
    @Test
    public void testSetMax_Velocity() {
        System.out.println("setMax_Velocity");
        double max_Velocity = 0.0;
        Segment instance = new Segment();
        instance.setMax_Velocity(max_Velocity);

    }

    /**
     * Test of getMin_Velocity method, of class Segment.
     */
    @Test
    public void testGetMin_Velocity() {
        System.out.println("getMin_Velocity");
        Segment instance = new Segment();
        double expResult = 0.0;
        double result = instance.getMin_Velocity();
        assertEquals(expResult, result, 0.0);

    }

    /**
     * Test of setMin_Velocity method, of class Segment.
     */
    @Test
    public void testSetMin_Velocity() {
        System.out.println("setMin_Velocity");
        double min_Velocity = 0.0;
        Segment instance = new Segment();
        instance.setMin_Velocity(min_Velocity);

    }

    /**
     * Test of getMax_Vehicles method, of class Segment.
     */
    @Test
    public void testGetMax_Vehicles() {
        System.out.println("getMax_Vehicles");
        Segment instance = new Segment();
        double expResult = 0.0;
        double result = instance.getMax_Vehicles();
        assertEquals(expResult, result, 0.0);

    }

    /**
     * Test of setMax_Vehicles method, of class Segment.
     */
    @Test
    public void testSetMax_Vehicles() {
        System.out.println("setMax_Vehicles");
        int max_Vehicles = 0;
        Segment instance = new Segment();
        instance.setMax_Vehicles(max_Vehicles);

    }


    /**
     * Test of equals method, of class Segment.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = null;
        Segment instance = new Segment();
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);

    }
    
}
