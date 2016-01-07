/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IO;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author antonio
 */
public class ImportSimulationXMLTest {

    public ImportSimulationXMLTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of read method, of class ImportSimulationXML.
     */
    @Test
    public void testRead() {
        System.out.println("read");
        String filePath = "TestSet02_Simulation.xml";
        ImportSimulationXML instance = new ImportSimulationXML();
        boolean expResult = true;
        boolean result = instance.read(filePath);
        assertEquals(expResult, result);
    }

    /**
     * Test of read method, of class ImportSimulationXML.
     */
    @Test
    public void testReadInvalideFile() {
        System.out.println("read");
        String filePath = "otherfile.xml";
        ImportSimulationXML instance = new ImportSimulationXML();
        boolean expResult = false;
        boolean result = instance.read(filePath);
        assertEquals(expResult, result);
    }

    /**
     * Test of arrivalRateInVehiclesPerSeconds method, of class
     * ImportSimulationXML.
     */
    @Test
    public void testArrivalRateInVehiclesPerSeconds() {
        System.out.println("arrivalRateInVehiclesPerSeconds");
        String arrivalString = "1 1/s";
        ImportSimulationXML instance = new ImportSimulationXML();
        double expResult = 1.0;
        double result = instance.arrivalRateInVehiclesPerSeconds(arrivalString);
        assertEquals(expResult, result, 0.0);
        //TEST 2 (value)
        arrivalString = "25 1/s";
        expResult = 25.0;
        result = instance.arrivalRateInVehiclesPerSeconds(arrivalString);
        assertEquals(expResult, result, 0.0);
        //TEST 3 (minutes)
        arrivalString = "60 1/m";
        expResult = 1.0;
        result = instance.arrivalRateInVehiclesPerSeconds(arrivalString);
        assertEquals(expResult, result, 0.0);
        //TEST 3 (hours)
        arrivalString = "1800 1/h";
        expResult = 0.5;
        result = instance.arrivalRateInVehiclesPerSeconds(arrivalString);
        assertEquals(expResult, result, 0.0);
    }

}
