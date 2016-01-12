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
public class SegmentTest {
    
    Segment segment1;
    Segment segment2;
    
    public SegmentTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        segment1 = new Segment(1, 100, 1.5, 3.2, 90, 0, 20);
        segment2 = new Segment(2, 148, 1.5, 3.2, 90, 0, 20);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getIndex method, of class Segment.
     */
    @Test
    public void testGetIndex() {
        System.out.println("getIndex");
        Segment instance = segment1;
        int expResult = 1;
        int result = instance.getIndex();
        assertEquals(expResult, result);
    }

    /**
     * Test of setIndex method, of class Segment.
     */
    @Test
    public void testSetIndex() {
        System.out.println("setIndex");
        int index = 5;
        Segment instance = segment1;
        instance.setIndex(index);
        int expResult=5;
        int result=instance.getIndex();
        assertEquals(expResult, result);
    }

    /**
     * Test of getInitialHeight method, of class Segment.
     */
    @Test
    public void testGetInitialHeight() {
        System.out.println("getInitialHeight");
        Segment instance = segment1;
        double expResult = 100;
        double result = instance.getInitialHeight();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setInitialHeight method, of class Segment.
     */
    @Test
    public void testSetInitialHeight() {
        System.out.println("setInitialHeight");
        double initial_Height = 200;
        Segment instance = segment1;
        instance.setInitialHeight(initial_Height);
        double expResult = 200;
        double result=instance.getInitialHeight();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getSlope method, of class Segment.
     */
    @Test
    public void testGetSlope() {
        System.out.println("getSlope");
        Segment instance = segment1;
        double expResult = 1.5;
        double result = instance.getSlope();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setSlope method, of class Segment.
     */
    @Test
    public void testSetSlope() {
        System.out.println("setSlope");
        double slope = -0.5;
        Segment instance = segment1;
        instance.setSlope(slope);
        double expResult=-0.5;
        double result=instance.getSlope();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getLenght method, of class Segment.
     */
    @Test
    public void testGetLenght() {
        System.out.println("getLenght");
        Segment instance = segment1;
        double expResult = 3.2;
        double result = instance.getLenght();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setLenght method, of class Segment.
     */
    @Test
    public void testSetLenght() {
        System.out.println("setLenght");
        double lenght = 20.0;
        Segment instance = segment1;
        instance.setLenght(lenght);
        double expResult=20;
        double result=instance.getLenght();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getMax_Velocity method, of class Segment.
     */
    @Test
    public void testGetMax_Velocity() {
        System.out.println("getMax_Velocity");
        Segment instance = segment1;
        double expResult = 90;
        double result = instance.getMax_Velocity();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setMax_Velocity method, of class Segment.
     */
    @Test
    public void testSetMax_Velocity() {
        System.out.println("setMax_Velocity");
        double max_Velocity = 120.0;
        Segment instance = new Segment();
        instance.setMax_Velocity(max_Velocity);
        double expResult = 120;
        double result=instance.getMax_Velocity();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getMin_Velocity method, of class Segment.
     */
    @Test
    public void testGetMin_Velocity() {
        System.out.println("getMin_Velocity");
        Segment instance = segment1;
        double expResult = 0;
        double result = instance.getMin_Velocity();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setMin_Velocity method, of class Segment.
     */
    @Test
    public void testSetMin_Velocity() {
        System.out.println("setMin_Velocity");
        double min_Velocity = 50;
        Segment instance = segment1;
        instance.setMin_Velocity(min_Velocity);
        double expResult=50;
        double result=instance.getMin_Velocity();
    }

    /**
     * Test of getMax_Vehicles method, of class Segment.
     */
    @Test
    public void testGetMax_Vehicles() {
        System.out.println("getMax_Vehicles");
        Segment instance = segment1;
        int expResult = 20;
        int result = instance.getMax_Vehicles();
        assertEquals(expResult, result);
    }

    /**
     * Test of setMax_Vehicles method, of class Segment.
     */
    @Test
    public void testSetMax_Vehicles() {
        System.out.println("setMax_Vehicles");
        int max_Vehicles = 2;
        Segment instance = new Segment();
        instance.setMax_Vehicles(max_Vehicles);
        int expResult=2;
        int result=instance.getMax_Vehicles();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Segment.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Segment segment = segment2;
        Segment instance = segment1;
        boolean expResult = false;
        boolean result = instance.equals(segment);
        assertEquals(expResult, result);
        
        segment=new Segment(1, 100, 1.5, 3.2, 90, 0, 20);
        expResult = true;
        result = instance.equals(segment);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Segment.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Segment instance = segment1;
        String expResult = "Segment{index=1, initial_Height=100.0, slope=1.5, "
                + "lenght=3.2, max_Velocity=90.0, min_Velocity=0.0, max_Vehicles=20}";
        String result = instance.toString();
        assertEquals(expResult, result);
        
    }
    
}
