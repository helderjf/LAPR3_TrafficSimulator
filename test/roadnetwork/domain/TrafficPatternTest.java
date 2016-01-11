/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadnetwork.domain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author antonio
 */
public class TrafficPatternTest {
    
    public TrafficPatternTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of arrivalRateInVehiclesPerSeconds method, of class TrafficPattern.
     */
    @Test
    public void testArrivalRateInVehiclesPerSeconds() {
        System.out.println("arrivalRateInVehiclesPerSeconds");
        String arrivalString = "1 1/s";
        TrafficPattern instance = new TrafficPattern();
        double expResult = 1.0;
        double result = instance.arrivalRateInVehiclesPerSeconds(arrivalString);
        assertEquals(expResult, result, 0.0);
        //TEST 2 (value)
        arrivalString = "25 1/s";
        expResult = 25.0;
        result = instance.arrivalRateInVehiclesPerSeconds(arrivalString);
        assertEquals(expResult, result, 0.0);
        //TEST 3 (minutes)
        arrivalString = "60 1/m";
        expResult = 1.0;
        result = instance.arrivalRateInVehiclesPerSeconds(arrivalString);
        assertEquals(expResult, result, 0.0);
        //TEST 3 (hours)
        arrivalString = "1800 1/h";
        expResult = 0.5;
        result = instance.arrivalRateInVehiclesPerSeconds(arrivalString);
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of validate method, of class TrafficPattern.
     */
    @Test
    public void testValidate() {
        System.out.println("validate");
        TrafficPattern instance = new TrafficPattern();
        //False case
        boolean expResult = false;
        boolean result = instance.validate();
        assertEquals(expResult, result);
        //True case
        instance.setBeginNode(new Junction());
        instance.setEndNode(new Junction());
        instance.setVehicle(new HybridVehicle());
        instance.setArrivalRate(0.0111);
        expResult = true;
        result = instance.validate();
        assertEquals(expResult, result);
    }
    
}
