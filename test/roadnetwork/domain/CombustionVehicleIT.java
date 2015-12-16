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
public class CombustionVehicleIT {
    
    public CombustionVehicleIT() {
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
     * Test of getFuel method, of class CombustionVehicle.
     */
    @Test
    public void testGetFuel() {
        System.out.println("getFuel");
        CombustionVehicle instance = null;
        String expResult = "";
        String result = instance.getFuel();
        assertEquals(expResult, result);

    }

    /**
     * Test of setFuel method, of class CombustionVehicle.
     */
    @Test
    public void testSetFuel() {
        System.out.println("setFuel");
        String fuel = "";
        CombustionVehicle instance = null;
        instance.setFuel(fuel);

    }

    /**
     * Test of getGearRatio method, of class CombustionVehicle.
     */
    @Test
    public void testGetGearRatio() {
        System.out.println("getGearRatio");
        CombustionVehicle instance = null;
        ArrayList<Double> expResult = null;
        ArrayList<Double> result = instance.getGearRatio();
        assertEquals(expResult, result);

    }

    /**
     * Test of setGearRatio method, of class CombustionVehicle.
     */
    @Test
    public void testSetGearRatio() {
        System.out.println("setGearRatio");
        ArrayList<Double> gearRatio = null;
        CombustionVehicle instance = null;
        instance.setGearRatio(gearRatio);

    }

    /**
     * Test of hashCode method, of class CombustionVehicle.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        CombustionVehicle instance = null;
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);

    }

    /**
     * Test of equals method, of class CombustionVehicle.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = null;
        CombustionVehicle instance = null;
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);

    }

    /**
     * Test of toString method, of class CombustionVehicle.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        CombustionVehicle instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);

    }
    
}
