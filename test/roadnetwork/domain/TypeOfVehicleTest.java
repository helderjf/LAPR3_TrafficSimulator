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
public class TypeOfVehicleTest {
    
    TypeOfVehicle type1;
    TypeOfVehicle type2;
    
    public TypeOfVehicleTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        
        type1 = new TypeOfVehicle(1,"highway");
        type2 = new TypeOfVehicle(2,"regular");
        
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
        TypeOfVehicle instance = type1;
        int expResult = 1;
        int result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getType method, of class TypeOfVehicle.
     */
    @Test
    public void testGetType() {
        System.out.println("getType");
        TypeOfVehicle instance = type1;
        String expResult = "highway";
        String result = instance.getType();
        assertEquals(expResult, result);
    }

    /**
     * Test of setType method, of class TypeOfVehicle.
     */
    @Test
    public void testSetType() {
        System.out.println("setType");
        String type = "regular";
        TypeOfVehicle instance = type1;
        instance.setType(type);
        String expResult="regular";
        String result = instance.getType();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class TypeOfVehicle.
     */
    @Test
    public void testEquals() {
        //System.out.println("equals");
        //Object obj = null;
        //TypeOfVehicle instance = new TypeOfVehicle();
//        boolean expResult = false;
//        boolean result = instance.equals(obj);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
        
        
        System.out.println("equals");
        Object obj = null;
        TypeOfVehicle instance = type1;
        TypeOfVehicle instance2 = type2;
        
        boolean expResult = false;
        boolean result = instance.equals(instance2);
        assertEquals(expResult, result);
        
        instance2 = new TypeOfVehicle(1,"highway");
        expResult = true;
        result = instance.equals(instance2);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class TypeOfVehicle.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        TypeOfVehicle instance = type1;
        String expResult = "TypeOfVehicle{" + "id=" + 1 + ", type=" + "highway" + '}';
        String result = instance.toString();
        assertEquals(expResult, result);
    }  
}
