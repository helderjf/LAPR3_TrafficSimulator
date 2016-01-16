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
 * @author Andre
 */
public class BestPathAnalysisContollerTest {
    
    public BestPathAnalysisContollerTest() {
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
     * Test of projectActive method, of class BestPathAnalysisContoller.
     */
    @Test
    public void testProjectActive() {
        System.out.println("projectActive");
        BestPathAnalysisContoller instance = null;
        boolean expResult = false;
        boolean result = instance.projectActive();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of newAnalysis method, of class BestPathAnalysisContoller.
     */
    @Test
    public void testNewAnalysis() {
        System.out.println("newAnalysis");
        BestPathAnalysisContoller instance = null;
        boolean expResult = false;
        boolean result = instance.newAnalysis();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of newBestPathAnalysis method, of class BestPathAnalysisContoller.
     */
    @Test
    public void testNewBestPathAnalysis() {
        System.out.println("newBestPathAnalysis");
        BestPathAnalysisContoller instance = null;
        ArrayList<Vehicle> expResult = null;
        ArrayList<Vehicle> result = instance.newBestPathAnalysis();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setVehicle method, of class BestPathAnalysisContoller.
     */
    @Test
    public void testSetVehicle() {
        System.out.println("setVehicle");
        Vehicle v = null;
        BestPathAnalysisContoller instance = null;
        instance.setVehicle(v);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNodeList method, of class BestPathAnalysisContoller.
     */
    @Test
    public void testGetNodeList() {
        System.out.println("getNodeList");
        BestPathAnalysisContoller instance = null;
        ArrayList<Junction> expResult = null;
        ArrayList<Junction> result = instance.getNodeList();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setAnalysisNodes method, of class BestPathAnalysisContoller.
     */
    @Test
    public void testSetAnalysisNodes() {
        System.out.println("setAnalysisNodes");
        Junction oj = null;
        Junction dj = null;
        BestPathAnalysisContoller instance = null;
        instance.setAnalysisNodes(oj, dj);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getBestPathAlgorithms method, of class BestPathAnalysisContoller.
     */
    @Test
    public void testGetBestPathAlgorithms() {
        System.out.println("getBestPathAlgorithms");
        BestPathAnalysisContoller instance = null;
        ArrayList<BestPathAlgorithm> expResult = null;
        ArrayList<BestPathAlgorithm> result = instance.getBestPathAlgorithms();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setAlgorithm method, of class BestPathAnalysisContoller.
     */
    @Test
    public void testSetAlgorithm() {
        System.out.println("setAlgorithm");
        BestPathAlgorithm alg = null;
        BestPathAnalysisContoller instance = null;
        instance.setAlgorithm(alg);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of runAnalysis method, of class BestPathAnalysisContoller.
     */
    @Test
    public void testRunAnalysis() {
        System.out.println("runAnalysis");
        BestPathAnalysisContoller instance = null;
        Result expResult = null;
        Result result = instance.runAnalysis();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of exportGlobalResultsCSV method, of class BestPathAnalysisContoller.
     */
    @Test
    public void testExportGlobalResultsCSV() {
        System.out.println("exportGlobalResultsCSV");
        String fileName = "";
        BestPathAnalysisContoller instance = null;
        boolean expResult = false;
        boolean result = instance.exportGlobalResultsCSV(fileName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of exportGlobalResultsHTML method, of class BestPathAnalysisContoller.
     */
    @Test
    public void testExportGlobalResultsHTML() {
        System.out.println("exportGlobalResultsHTML");
        String fileName = "";
        BestPathAnalysisContoller instance = null;
        boolean expResult = false;
        boolean result = instance.exportGlobalResultsHTML(fileName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
