/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadnetwork.domain;

import java.util.HashMap;
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
        CombustionVehicle instance = new CombustionVehicle();
        String expResult = null;
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
        CombustionVehicle instance = new CombustionVehicle();
        instance.setFuel(fuel);

    }


    /**
     * Test of setGearList method, of class CombustionVehicle.
     */
    @Test
    public void testSetGearRatio() {
        System.out.println("setGearRatio");
        HashMap<Integer, Double> gearRatio = null;
        CombustionVehicle instance = new CombustionVehicle();
        instance.setGearList(gearRatio);

    }


    /**
     * Test of equals method, of class CombustionVehicle.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = null;
        CombustionVehicle instance = new CombustionVehicle();
        boolean expResult = false;
        boolean result = instance.equals(obj);

    }
    
}
