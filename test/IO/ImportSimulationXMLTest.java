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
import roadnetwork.domain.CombustionVehicle;
import roadnetwork.domain.HybridVehicle;
import roadnetwork.domain.Junction;
import roadnetwork.domain.Project;
import roadnetwork.domain.RoadNetwork;
import roadnetwork.domain.TrafficPattern;
import roadnetwork.domain.Vehicle;

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
        Vehicle v = new CombustionVehicle();
        v.setName("Dummy01");
        project.getVehicleList().add(v);
        v = new CombustionVehicle();
        v.setName("Dummy02");
        project.getVehicleList().add(v);
        project.setRoadNetwork(new RoadNetwork());
        project.getRoadNetwork().setNodeList(new ArrayList<>());
        project.getRoadNetwork().getNodeList().add(new Junction("n0"));
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
        ArrayList<TrafficPattern> result = instance.read(filePath,project);
        assertEquals("Dummy01", result.get(0).getVehicle().getName());
        assertEquals("n0", result.get(0).getBeginNode().getJunctionId());
    }

    /**
     * Test of read method, of class ImportSimulationXML.
     */
    @Test
    public void testReadInvalideFile() {
        System.out.println("read");
        String filePath = "otherfile.xml";
        ImportSimulationXML instance = new ImportSimulationXML();
        ArrayList<TrafficPattern> expResult = null;
        ArrayList<TrafficPattern> result = instance.read(filePath,project);
        assertEquals(expResult, result);
    }

    
    /**
     * Test of read method, of class ImportSimulationXML.
     */
    @Test
    public void testReadParsingErrors() {
        System.out.println("read");
        String filePath = "TestSet01_Network.xml";
        ImportSimulationXML instance = new ImportSimulationXML();
        ArrayList<TrafficPattern> expResult = null;
        ArrayList<TrafficPattern> result = instance.read(filePath,project);
        assertEquals(expResult, result);
    }
}
