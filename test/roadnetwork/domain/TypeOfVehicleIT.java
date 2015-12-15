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
public class TypeOfVehicleIT {
    
    public TypeOfVehicleIT() {
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
     * Test of getId method, of class TypeOfVehicle.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        TypeOfVehicle instance = new TypeOfVehicle();
        int expResult = 0;
        int result = instance.getId();
        assertEquals(expResult, result);
 
    }

    /**
     * Test of getType method, of class TypeOfVehicle.
     */
    @Test
    public void testGetType() {
        System.out.println("getType");
        TypeOfVehicle instance = new TypeOfVehicle();
        String expResult = "";
        String result = instance.getType();
        assertEquals(expResult, result);

    }

    /**
     * Test of setType method, of class TypeOfVehicle.
     */
    @Test
    public void testSetType() {
        System.out.println("setType");
        String type = "";
        TypeOfVehicle instance = new TypeOfVehicle();
        instance.setType(type);

    }


    /**
     * Test of equals method, of class TypeOfVehicle.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = null;
        TypeOfVehicle instance = new TypeOfVehicle();
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }
    
}
