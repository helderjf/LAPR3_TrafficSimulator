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
public class ResultSimulationTest {
    
    ResultSimulation resultSimulation;
    
    public ResultSimulationTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        
        resultSimulation = new ResultSimulation("run1", 60, 0.20, null, null, null, null);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of printResults method, of class ResultSimulation.
     */
    @Test
    public void testPrintResults() {
        System.out.println("printResults");
        ResultSimulation instance = null;
        String expResult = "";
        String result = instance.printResults();
        assertEquals(expResult, result);

    }

    /**
     * Test of getDroppedVehicles method, of class ResultSimulation.
     */
    @Test
    public void testGetDroppedVehicles() {
        System.out.println("getDroppedVehicles");
        ResultSimulation instance = null;
        ArrayList<SimVehicle> expResult = null;
        ArrayList<SimVehicle> result = instance.getDroppedVehicles();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEndedVehicles method, of class ResultSimulation.
     */
    @Test
    public void testGetEndedVehicles() {
        System.out.println("getEndedVehicles");
        ResultSimulation instance = null;
        ArrayList<SimVehicle> expResult = null;
        ArrayList<SimVehicle> result = instance.getEndedVehicles();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCruisingVehicles method, of class ResultSimulation.
     */
    @Test
    public void testGetCruisingVehicles() {
        System.out.println("getCruisingVehicles");
        ResultSimulation instance = null;
        ArrayList<SimVehicle> expResult = null;
        ArrayList<SimVehicle> result = instance.getCruisingVehicles();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getResultsHTMLCode method, of class ResultSimulation.
     */
    @Test
    public void testGetResultsHTMLCode() {
        System.out.println("getResultsHTMLCode");
        ResultSimulation instance = null;
        String expResult = "";
        String result = instance.getResultsHTMLCode();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
