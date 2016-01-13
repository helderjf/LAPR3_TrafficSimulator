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
public class RegimeTest {
    
    Regime r1;
    Regime r2;
    
    public RegimeTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        r1 = new Regime(1, 85, 1000, 2499, 8.2);
        r2 = new Regime(2, 95, 2500, 3999, 6.2);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getPK method, of class Regime.
     */
    @Test
    public void testGetPK() {
        System.out.println("getPK");
        Regime instance = r1;
        int expResult = 1;
        int result = instance.getPK();
        assertEquals(expResult, result);
    }

    /**
     * Test of getRPMLow method, of class Regime.
     */
    @Test
    public void testGetRPMLow() {
        System.out.println("getRPMLow");
        Regime instance = r1;
        double expResult = 1000.0;
        double result = instance.getRPMLow();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getRPMHigh method, of class Regime.
     */
    @Test
    public void testGetRPMHigh() {
        System.out.println("getRPMHigh");
        Regime instance = r1;
        double expResult = 2499.0;
        double result = instance.getRPMHigh();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getSfc method, of class Regime.
     */
    @Test
    public void testGetSfc() {
        System.out.println("getSfc");
        Regime instance = r1;
        double expResult = 8.2;
        double result = instance.getSfc();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getTorque method, of class Regime.
     */
    @Test
    public void testGetTorque() {
        System.out.println("getTorque");
        Regime instance = r1;
        double expResult = 85.0;
        double result = instance.getTorque();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setPK method, of class Regime.
     */
    @Test
    public void testSetPK() {
        System.out.println("setPK");
        int pk = 5;
        Regime instance = r1;
        instance.setPK(pk);
        int result=instance.getPK();
        int expResult=5;
        assertEquals(expResult, result);
    }

    /**
     * Test of setTorque method, of class Regime.
     */
    @Test
    public void testSetTorque() {
        System.out.println("setTorque");
        double torque = 95.0;
        Regime instance = r1;
        instance.setTorque(torque);
        double result=instance.getTorque();
        double expResult=95.0;
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setM_rpmLow method, of class Regime.
     */
    @Test
    public void testSetM_rpmLow() {
        System.out.println("setM_rpmLow");
        double rpmLow = 1200;
        Regime instance = r1;
        instance.setM_rpmLow(rpmLow);
        double result=instance.getRPMLow();
        double expResult=1200;
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setM_rpmHigh method, of class Regime.
     */
    @Test
    public void testSetM_rpmHigh() {
        System.out.println("setM_rpmHigh");
        double rpmHigh = 2500.0;
        Regime instance = r1;
        instance.setM_rpmHigh(rpmHigh);
        double result=instance.getRPMHigh();
        double expResult=2500;
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setM_sfc method, of class Regime.
     */
    @Test
    public void testSetM_sfc() {
        System.out.println("setM_sfc");
        double sfc = 9.5;
        Regime instance = new Regime();
        instance.setM_sfc(sfc);
        double result=instance.getSfc();
        double expResult=9.5;
        assertEquals(expResult, result, 0.0);
    }
    
}
