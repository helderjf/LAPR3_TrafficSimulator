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
public class EngineEfficiencyTest {
    
    
    EngineEfficiency engineEfficiency1;
    EngineEfficiency engineEfficiency2;
    
    
    public EngineEfficiencyTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        
        engineEfficiency1 = new EngineEfficiency();
        engineEfficiency2 = new EngineEfficiency();

        
        engineEfficiency1.setGear(1);
        engineEfficiency1.setGearRatio(1.98);
        engineEfficiency1.setM_rpmHigh(5000.0);
        engineEfficiency1.setM_rpmLow(1000.0);
        engineEfficiency1.setM_sfc(100.0);
        engineEfficiency1.setThrottleRatio("50");
        engineEfficiency1.setTorque(10.0);
        
        engineEfficiency2.setGear(2);
        engineEfficiency2.setGearRatio(1.18);
        engineEfficiency2.setM_rpmHigh(4000.0);
        engineEfficiency2.setM_rpmLow(900.0);
        engineEfficiency2.setM_sfc(120.0);
        engineEfficiency2.setThrottleRatio("100");
        engineEfficiency2.setTorque(19.0);
    }
    

    /**
     * Test of getGear method, of class EngineEfficiency.
     */
    @Test
    public void testGetGear() {
        System.out.println("getGear");
        EngineEfficiency instance = engineEfficiency1;
        int expResult = 1;
        int result = instance.getGear();
        assertEquals(expResult, result);
    }

    /**
     * Test of setGear method, of class EngineEfficiency.
     */
    @Test
    public void testSetGear() {
        System.out.println("setGear");
        int gear = 1;
        EngineEfficiency instance = engineEfficiency1;
        instance.setGear(gear);
        int expResult = 1;
        int result = instance.getGear();
        assertEquals(expResult, result);
    }

    /**
     * Test of getThrottleRatio method, of class EngineEfficiency.
     */
    @Test
    public void testGetThrottleRatio() {
        System.out.println("getThrottleRatio");
        EngineEfficiency instance = engineEfficiency1;
        String expResult = "50";
        String result = instance.getThrottleRatio();
        assertEquals(expResult, result);
    }

    /**
     * Test of setThrottleRatio method, of class EngineEfficiency.
     */
    @Test
    public void testSetThrottleRatio() {
        System.out.println("setThrottleRatio");
        String throttleRatio = "50";
        EngineEfficiency instance = engineEfficiency1;
        instance.setThrottleRatio(throttleRatio);
        String expResult = "50";
        String result = instance.getThrottleRatio();
        assertEquals(expResult, result);

    }

    /**
     * Test of getTorque method, of class EngineEfficiency.
     */
    @Test
    public void testGetTorque() {
        System.out.println("getTorque");
        EngineEfficiency instance = engineEfficiency1;
        double expResult = 10.0;
        double result = instance.getTorque();
        assertEquals(expResult, result, 0.0);
        
    }

    /**
     * Test of setTorque method, of class EngineEfficiency.
     */
    @Test
    public void testSetTorque() {
        System.out.println("setTorque");
        double torque = 10.0;
        EngineEfficiency instance = engineEfficiency1;
        instance.setTorque(torque);
        double expResult = 10.0;
        double result = instance.getTorque();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getM_rpmLow method, of class EngineEfficiency.
     */
    @Test
    public void testGetM_rpmLow() {
        System.out.println("getM_rpmLow");
        EngineEfficiency instance = engineEfficiency1;
        double expResult = 1000.0;
        double result = instance.getM_rpmLow();
        assertEquals(expResult, result, 0.0);

    }

    /**
     * Test of setM_rpmLow method, of class EngineEfficiency.
     */
    @Test
    public void testSetM_rpmLow() {
        System.out.println("setM_rpmLow");
        double m_rpmLow = 1000.0;
        EngineEfficiency instance = engineEfficiency1;
        instance.setM_rpmLow(m_rpmLow);
        double expResult = 1000.0;
        double result = instance.getM_rpmLow();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getM_rpmHigh method, of class EngineEfficiency.
     */
    @Test
    public void testGetM_rpmHigh() {
        System.out.println("getM_rpmHigh");
        EngineEfficiency instance = engineEfficiency1;
        double expResult = 5000.0;
        double result = instance.getM_rpmHigh();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setM_rpmHigh method, of class EngineEfficiency.
     */
    @Test
    public void testSetM_rpmHigh() {
        System.out.println("setM_rpmHigh");
        double m_rpmHigh = 5000.0;
        EngineEfficiency instance = engineEfficiency1;
        instance.setM_rpmHigh(m_rpmHigh);
        double expResult = 5000.0;
        double result = instance.getM_rpmHigh();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getM_sfc method, of class EngineEfficiency.
     */
    @Test
    public void testGetM_sfc() {
        System.out.println("getM_sfc");
        EngineEfficiency instance = engineEfficiency1;
        double expResult = 100.0;
        double result = instance.getM_sfc();
        assertEquals(expResult, result, 0.0);

    }

    /**
     * Test of setM_sfc method, of class EngineEfficiency.
     */
    @Test
    public void testSetM_sfc() {
        System.out.println("setM_sfc");
        double m_sfc = 100.0;
        EngineEfficiency instance = engineEfficiency1;
        instance.setM_sfc(m_sfc);
        double expResult = 100.0;
        double result = instance.getM_sfc();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getGearRatio method, of class EngineEfficiency.
     */
    @Test
    public void testGetGearRatio() {
        System.out.println("getGearRatio");
        EngineEfficiency instance = engineEfficiency1;
        double expResult = 1.98;
        double result = instance.getGearRatio();
        assertEquals(expResult, result, 0.0);

    }

    /**
     * Test of setGearRatio method, of class EngineEfficiency.
     */
    @Test
    public void testSetGearRatio() {
        System.out.println("setGearRatio");
        double gearRatio = 1.98;
        EngineEfficiency instance = engineEfficiency1;
        instance.setGearRatio(gearRatio);
        double expResult = 1.98;
        double result = instance.getGearRatio();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of equals method, of class EngineEfficiency.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = engineEfficiency1;
        EngineEfficiency instance = engineEfficiency2;
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);

    }

    /**
     * Test of toString method, of class EngineEfficiency.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        EngineEfficiency instance = engineEfficiency1;
        String expResult = "EngineEfficiency{torque=10.0, m_rpmLow=1000.0, m_rpmHigh=5000.0, m_sfc=100.0, throttleRatio=50, gear=1, gearRatio=1.98}";
        String result = instance.toString();
        assertEquals(expResult, result);
    }   
}
