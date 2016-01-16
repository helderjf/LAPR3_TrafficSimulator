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
public class SimVehicleTest {
    
    SimVehicle simVehicle;
    
    public SimVehicleTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        
        //simVehicle = new SimVehicle(null, null, null, null, null, injectionTime);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of willEndAtThisTimeStep method, of class SimVehicle.
     */
    @Test
    public void testWillEndAtThisTimeStep() {
        System.out.println("willEndAtThisTimeStep");
        double currentTime = 0.0;
        SimVehicle instance = null;
        boolean expResult = false;
        boolean result = instance.willEndAtThisTimeStep(currentTime);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of endSimulation method, of class SimVehicle.
     */
    @Test
    public void testEndSimulation() {
        System.out.println("endSimulation");
        double currentTime = 0.0;
        SimVehicle instance = null;
        boolean expResult = false;
        boolean result = instance.endSimulation(currentTime);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPredictedExitTime method, of class SimVehicle.
     */
    @Test
    public void testGetPredictedExitTime() {
        System.out.println("getPredictedExitTime");
        SimVehicle instance = null;
        double expResult = 0.0;
        double result = instance.getPredictedExitTime();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNextPos method, of class SimVehicle.
     */
    @Test
    public void testGetNextPos() {
        System.out.println("getNextPos");
        SimVehicle instance = null;
        SimPathParcel expResult = null;
        SimPathParcel result = instance.getNextPos();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of crossToNextPos method, of class SimVehicle.
     */
    @Test
    public void testCrossToNextPos() {
        System.out.println("crossToNextPos");
        double currentTime = 0.0;
        SimVehicle instance = null;
        boolean expResult = false;
        boolean result = instance.crossToNextPos(currentTime);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getStepInjectTime method, of class SimVehicle.
     */
    @Test
    public void testGetStepInjectTime() {
        System.out.println("getStepInjectTime");
        double stepInjectTime = 0.0;
        SimVehicle instance = null;
        instance.getStepInjectTime(stepInjectTime);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getInjectionTime method, of class SimVehicle.
     */
    @Test
    public void testGetInjectionTime() {
        System.out.println("getInjectionTime");
        SimVehicle instance = null;
        double expResult = 0.0;
        double result = instance.getInjectionTime();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFirstSimPathParcel method, of class SimVehicle.
     */
    @Test
    public void testGetFirstSimPathParcel() {
        System.out.println("getFirstSimPathParcel");
        SimVehicle instance = null;
        SimPathParcel expResult = null;
        SimPathParcel result = instance.getFirstSimPathParcel();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of drop method, of class SimVehicle.
     */
    @Test
    public void testDrop() {
        System.out.println("drop");
        SimVehicle instance = null;
        instance.drop();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isDropped method, of class SimVehicle.
     */
    @Test
    public void testIsDropped() {
        System.out.println("isDropped");
        SimVehicle instance = null;
        boolean expResult = false;
        boolean result = instance.isDropped();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getdroppedTime method, of class SimVehicle.
     */
    @Test
    public void testGetdroppedTime() {
        System.out.println("getdroppedTime");
        SimVehicle instance = null;
        double expResult = 0.0;
        double result = instance.getdroppedTime();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setInjected method, of class SimVehicle.
     */
    @Test
    public void testSetInjected() {
        System.out.println("setInjected");
        SimVehicle instance = null;
        instance.setInjected();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updatePredictedExitTime method, of class SimVehicle.
     */
    @Test
    public void testUpdatePredictedExitTime() {
        System.out.println("updatePredictedExitTime");
        double time = 0.0;
        SimVehicle instance = null;
        instance.updatePredictedExitTime(time);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getVehiclePK method, of class SimVehicle.
     */
    @Test
    public void testGetVehiclePK() {
        System.out.println("getVehiclePK");
        SimVehicle instance = null;
        int expResult = 0;
        int result = instance.getVehiclePK();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTrafficPattern method, of class SimVehicle.
     */
    @Test
    public void testGetTrafficPattern() {
        System.out.println("getTrafficPattern");
        SimVehicle instance = null;
        TrafficPattern expResult = null;
        TrafficPattern result = instance.getTrafficPattern();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPath method, of class SimVehicle.
     */
    @Test
    public void testGetPath() {
        System.out.println("getPath");
        SimVehicle instance = null;
        ArrayList<SimPathParcel> expResult = null;
        ArrayList<SimPathParcel> result = instance.getPath();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getVehicle method, of class SimVehicle.
     */
    @Test
    public void testGetVehicle() {
        System.out.println("getVehicle");
        SimVehicle instance = null;
        Vehicle expResult = null;
        Vehicle result = instance.getVehicle();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOriginNode method, of class SimVehicle.
     */
    @Test
    public void testGetOriginNode() {
        System.out.println("getOriginNode");
        SimVehicle instance = null;
        Junction expResult = null;
        Junction result = instance.getOriginNode();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDestinyNode method, of class SimVehicle.
     */
    @Test
    public void testGetDestinyNode() {
        System.out.println("getDestinyNode");
        SimVehicle instance = null;
        Junction expResult = null;
        Junction result = instance.getDestinyNode();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
