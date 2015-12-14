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
public class VehicleIT {
    
    public VehicleIT() {
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
     * Test of getId method, of class Vehicle.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Vehicle instance = new Vehicle();
        String expResult = "";
        String result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of setId method, of class Vehicle.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        String id = "";
        Vehicle instance = new Vehicle();
        instance.setId(id);
    }

    /**
     * Test of getName method, of class Vehicle.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        Vehicle instance = new Vehicle();
        String expResult = "";
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of setName method, of class Vehicle.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String name = "";
        Vehicle instance = new Vehicle();
        instance.setName(name);
    }

    /**
     * Test of getMass method, of class Vehicle.
     */
    @Test
    public void testGetMass() {
        System.out.println("getMass");
        Vehicle instance = new Vehicle();
        double expResult = 0.0;
        double result = instance.getMass();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setMass method, of class Vehicle.
     */
    @Test
    public void testSetMass() {
        System.out.println("setMass");
        double mass = 0.0;
        Vehicle instance = new Vehicle();
        instance.setMass(mass);
    }

    /**
     * Test of getType method, of class Vehicle.
     */
    @Test
    public void testGetType() {
        System.out.println("getType");
        Vehicle instance = new Vehicle();
        TypeOfVehicle expResult = null;
        TypeOfVehicle result = instance.getType();
        assertEquals(expResult, result);
    }

    /**
     * Test of getLoad method, of class Vehicle.
     */
    @Test
    public void testGetLoad() {
        System.out.println("getLoad");
        Vehicle instance = new Vehicle();
        double expResult = 0.0;
        double result = instance.getLoad();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setLoad method, of class Vehicle.
     */
    @Test
    public void testSetLoad() {
        System.out.println("setLoad");
        double load = 0.0;
        Vehicle instance = new Vehicle();
        instance.setLoad(load);
    }

    /**
     * Test of getDrag_Coefficient method, of class Vehicle.
     */
    @Test
    public void testGetDrag_Coefficient() {
        System.out.println("getDrag_Coefficient");
        Vehicle instance = new Vehicle();
        double expResult = 0.0;
        double result = instance.getDrag_Coefficient();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setDrag_Coefficient method, of class Vehicle.
     */
    @Test
    public void testSetDrag_Coefficient() {
        System.out.println("setDrag_Coefficient");
        double drag_Coefficient = 0.0;
        Vehicle instance = new Vehicle();
        instance.setDrag_Coefficient(drag_Coefficient);
    }


    /**
     * Test of equals method, of class Vehicle.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = null;
        Vehicle instance = new Vehicle();
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Vehicle.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Vehicle instance = new Vehicle();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
}
