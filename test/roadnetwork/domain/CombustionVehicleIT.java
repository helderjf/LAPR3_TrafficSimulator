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
     * Test of getTypeOfCombustion method, of class CombustionVehicle.
     */
    @Test
    public void testGetTypeOfCombustion() {
        System.out.println("getTypeOfCombustion");
        CombustionVehicle instance = null;
        instance.setTypeOfCombustion("teste");
        String expResult = "teste";
        String result = instance.getTypeOfCombustion();
        assertEquals(expResult, result);
    }

    /**
     * Test of setTypeOfCombustion method, of class CombustionVehicle.
     */
    @Test
    public void testSetTypeOfCombustion() {
        System.out.println("setTypeOfCombustion");
        String typeOfCombustion = "";
        CombustionVehicle instance = null;
        instance.setTypeOfCombustion(typeOfCombustion);
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
