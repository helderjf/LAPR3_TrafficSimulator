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
    
}
