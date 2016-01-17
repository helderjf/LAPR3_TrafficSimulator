/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadnetwork.controllers;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import roadnetwork.domain.BestPathAlgorithm;
import roadnetwork.domain.Junction;
import roadnetwork.domain.Result;
import roadnetwork.domain.Vehicle;

/**
 *
 * @author josemiranda
 */
public class VehiclesComparisonAnalysisControllerTest {
    
    public VehiclesComparisonAnalysisControllerTest() {
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
     * Test of projectActive method, of class VehiclesComparisonAnalysisController.
     */
    @Test
    public void testProjectActive() {
        System.out.println("projectActive");
        VehiclesComparisonAnalysisController instance = null;
        boolean expResult = false;
        boolean result = instance.projectActive();
        assertEquals(expResult, result);
    }

    /**
     * Test of newAnalysis method, of class VehiclesComparisonAnalysisController.
     */
    @Test
    public void testNewAnalysis() {
        System.out.println("newAnalysis");
        VehiclesComparisonAnalysisController instance = null;
        boolean expResult = false;
        boolean result = instance.newAnalysis();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of newVehicleComparisonAnalysis method, of class VehiclesComparisonAnalysisController.
     */
    @Test
    public void testNewVehicleComparisonAnalysis() {
        System.out.println("newVehicleComparisonAnalysis");
        VehiclesComparisonAnalysisController instance = null;
        ArrayList<Vehicle> expResult = null;
        ArrayList<Vehicle> result = instance.newVehicleComparisonAnalysis();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSelectedVehiclesList method, of class VehiclesComparisonAnalysisController.
     */
    @Test
    public void testSetSelectedVehiclesList() {
        System.out.println("setSelectedVehiclesList");
        ArrayList<Vehicle> vlst = null;
        VehiclesComparisonAnalysisController instance = null;
        instance.setSelectedVehiclesList(vlst);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNodeList method, of class VehiclesComparisonAnalysisController.
     */
    @Test
    public void testGetNodeList() {
        System.out.println("getNodeList");
        VehiclesComparisonAnalysisController instance = null;
        ArrayList<Junction> expResult = null;
        ArrayList<Junction> result = instance.getNodeList();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setAnalysisNodes method, of class VehiclesComparisonAnalysisController.
     */
    @Test
    public void testSetAnalysisNodes() {
        System.out.println("setAnalysisNodes");
        Junction on = null;
        Junction dn = null;
        VehiclesComparisonAnalysisController instance = null;
        instance.setAnalysisNodes(on, dn);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getBestPathAlgorithms method, of class VehiclesComparisonAnalysisController.
     */
    @Test
    public void testGetBestPathAlgorithms() {
        System.out.println("getBestPathAlgorithms");
        VehiclesComparisonAnalysisController instance = null;
        ArrayList<BestPathAlgorithm> expResult = null;
        ArrayList<BestPathAlgorithm> result = instance.getBestPathAlgorithms();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setBestPathAlgorithm method, of class VehiclesComparisonAnalysisController.
     */
    @Test
    public void testSetBestPathAlgorithm() {
        System.out.println("setBestPathAlgorithm");
        BestPathAlgorithm bpa = null;
        VehiclesComparisonAnalysisController instance = null;
        instance.setBestPathAlgorithm(bpa);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of runAnalysis method, of class VehiclesComparisonAnalysisController.
     */
    @Test
    public void testRunAnalysis() {
        System.out.println("runAnalysis");
        VehiclesComparisonAnalysisController instance = null;
        ArrayList<Result> expResult = null;
        ArrayList<Result> result = instance.runAnalysis();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of exportGlobalResultsHTML method, of class VehiclesComparisonAnalysisController.
     */
    @Test
    public void testExportGlobalResultsHTML() {
        System.out.println("exportGlobalResultsHTML");
        String fileName = "";
        VehiclesComparisonAnalysisController instance = null;
        boolean expResult = false;
        boolean result = instance.exportGlobalResultsHTML(fileName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
