/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IO;

import java.util.ArrayList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import roadnetwork.domain.HybridVehicle;
import roadnetwork.domain.Junction;
import roadnetwork.domain.Project;
import roadnetwork.domain.RoadNetwork;

/**
 *
 * @author antonio
 */
public class ImportSimulationXMLTest {

    Project project;
    
    public ImportSimulationXMLTest() {
    }

    @Before
    public void setUp() {
        //Creates a project with nodes that are in the file
        project = new Project("Project One", "No descripition");
        project.setVehicleList(new ArrayList<>());
        project.getVehicleList().add(new HybridVehicle("Dummy01"));
        project.getVehicleList().add(new HybridVehicle("Dummy02"));
        project.setRoadNetwork(new RoadNetwork());
        project.getRoadNetwork().setNodeList(new ArrayList<>());
        project.getRoadNetwork().getNodeList().add(new Junction("n1"));
        project.getRoadNetwork().getNodeList().add(new Junction("n2"));
        project.getRoadNetwork().getNodeList().add(new Junction("n3"));
        project.getRoadNetwork().getNodeList().add(new Junction("n4"));
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
        boolean result = instance.read(filePath,project);
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
        boolean result = instance.read(filePath,project);
        assertEquals(expResult, result);
    }

}
