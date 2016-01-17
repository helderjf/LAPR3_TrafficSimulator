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
import roadnetwork.state.SimulationState;
import roadnetwork.state.SimulationStateActive;

/**
 *
 * @author Andre
 */
public class SimulationTest {
    Simulation simulation;
    Simulation simulation2;
    SimulationState simulationState;
    SimulationRun simulationRun;
    TrafficPattern trafficPattern1;
    TrafficPattern trafficPattern2;
    ArrayList<TrafficPattern> lstTrafficPattern;
    RoadNetwork roadNetwork;
    BestPathAlgorithm bestPathAlgorithm;
    Vehicle vehicle1;
    Junction junction1;
    Junction junction2;
    Junction junction3;
    
    public SimulationTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        
        simulationState = new SimulationStateActive(simulation2);
        
        bestPathAlgorithm = new FastestPathAlgorithm();
        
        roadNetwork = new RoadNetwork();
        roadNetwork.setPK(1);
        
        vehicle1 = new CombustionVehicle();
        vehicle1.setPK(1);
        
        junction1 = new Junction();
        junction2 = new Junction();
        junction3 = new Junction();
        
        junction1.setPK(1);
        junction2.setPK(2);
        junction3.setPK(3);
        
        trafficPattern1 = new TrafficPattern(1, junction1, junction2, vehicle1, 20);
        trafficPattern2 = new TrafficPattern(2, junction2, junction3, vehicle1, 30);
        
        lstTrafficPattern = new ArrayList<>();
        lstTrafficPattern.add(trafficPattern1);
        lstTrafficPattern.add(trafficPattern2);
        
        simulationRun = new SimulationRun("run1", 10.0, 1.0, roadNetwork, lstTrafficPattern, bestPathAlgorithm);
        
        simulation = new Simulation("sim1", "descricao1");
        simulation2 = new Simulation(2, "sim2", "descricao2", simulationState, lstTrafficPattern, simulationRun);
        
        
        
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getName method, of class Simulation.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        Simulation instance = simulation;
        String expResult = "sim1";
        String result = instance.getName();
        assertEquals(expResult, result);

    }

    /**
     * Test of getPK method, of class Simulation.
     */
    @Test
    public void testGetPK() {
        System.out.println("getPK");
        Simulation instance = simulation2;
        int expResult = 2;
        int result = instance.getPK();
        assertEquals(expResult, result);

    }

    /**
     * Test of getDescription method, of class Simulation.
     */
    @Test
    public void testGetDescription() {
        System.out.println("getDescription");
        Simulation instance = simulation;
        String expResult = "descricao1";
        String result = instance.getDescription();
        assertEquals(expResult, result);

    }

    /**
     * Test of xmlImported method, of class Simulation.
     */
    @Test
    public void testXmlImported() {
        System.out.println("xmlImported");
        Simulation instance = simulation2;
        boolean expResult = false;
        boolean result = instance.xmlImported();
        assertEquals(expResult, result);

    }

    /**
     * Test of getTrafficPatternList method, of class Simulation.
     */
    @Test
    public void testGetTrafficPatternList() {
        System.out.println("getTrafficPatternList");
        Simulation instance = simulation2;
        ArrayList<TrafficPattern> result = instance.getTrafficPatternList();
        ArrayList<TrafficPattern> expResult = lstTrafficPattern;
        assertEquals(expResult, result);

    }

    /**
     * Test of setName method, of class Simulation.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String m_name = "sim1";
        Simulation instance = simulation;
        instance.setName(m_name);
        String expResult = "sim1";
        String result = instance.getName();
        assertEquals(expResult, result);

    }

    /**
     * Test of setPK method, of class Simulation.
     */
    @Test
    public void testSetPK() {
        System.out.println("setPK");
        int m_pk = 2;
        Simulation instance = simulation2;
        instance.setPK(2);
        int expResult = 2;
        int result = instance.getPK();
        assertEquals(expResult, result);

    }

    /**
     * Test of setDescription method, of class Simulation.
     */
    @Test
    public void testSetDescription() {
        System.out.println("setDescription");
        String m_description = "descricao1";
        Simulation instance = new Simulation();
        instance.setDescription(m_description);
        String expResult = "descricao1";
        String result = instance.getDescription();
        assertEquals(expResult, result);
    }

    /**
     * Test of setState method, of class Simulation.
     */
    @Test
    public void testSetState() {
        System.out.println("setState");
        SimulationState m_state = simulationState;
        String expResult = "Active";
        Simulation instance = simulation2;
        instance.setState(m_state);
        String result = instance.getState().getDescription();
        assertEquals(expResult, result);
    }

    /**
     * Test of setTrafficPatternList method, of class Simulation.
     */
    @Test
    public void testSetTrafficPatternList() {
        System.out.println("setTrafficPatternList");
        ArrayList<TrafficPattern> m_trafficPatternList = null;
        Simulation instance = simulation2;
        instance.setTrafficPatternList(m_trafficPatternList);

    }

    /**
     * Test of canRunSimulation method, of class Simulation.
     */
    @Test
    public void testCanRunSimulation() {
        System.out.println("canRunSimulation");
        Simulation instance = simulation2;
        boolean result = instance.canRunSimulation();
        boolean expResult = true;
        assertEquals(expResult, result);

    }

    /**
     * Test of newSimulationRun method, of class Simulation.
     */
    @Test
    public void testNewSimulationRun() {
        System.out.println("newSimulationRun");
        RoadNetwork roadNetwork2 = roadNetwork;
        String runName = "run1";
        double runDuration = 10.0;
        double runTimeStep = 1.0;
        BestPathAlgorithm bpm = bestPathAlgorithm;
        Simulation instance = new Simulation();
        SimulationRun result = instance.newSimulationRun(roadNetwork2, runName, runDuration, runTimeStep, bpm);
        SimulationRun expResult = simulationRun;
        String result1 = result.getName();
        String expResult1 = expResult.getName();
        assertEquals(expResult1, result1);

    }

    /**
     * Test of getState method, of class Simulation.
     */
    @Test
    public void testGetState() {
        System.out.println("getState");
        Simulation instance = simulation2;
        SimulationState expResult = simulationState;
        SimulationState result = instance.getState();
        assertEquals(expResult, result);
    }

    /**
     * Test of hasPK method, of class Simulation.
     */
    @Test
    public void testHasPK() {
        System.out.println("hasPK");
        Simulation instance = simulation2;
        boolean expResult = true;
        boolean result = instance.hasPK();
        assertEquals(expResult, result);

    }

    /**
     * Test of canEditProperties method, of class Simulation.
     */
    @Test
    public void testCanEditProperties() {
        System.out.println("canEditProperties");
        Simulation instance = simulation2;
        boolean expResult = true;
        boolean result = instance.canEditProperties();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of propertiesChanged method, of class Simulation.
     */
    @Test
    public void testPropertiesChanged() {
        System.out.println("propertiesChanged");
        Simulation instance = simulation2;
        boolean result = instance.propertiesChanged();
        boolean expResult = true;
        assertEquals(expResult, result);

    }

    /**
     * Test of getCurrentRun method, of class Simulation.
     */
    @Test
    public void testGetCurrentRun() {
        System.out.println("getCurrentRun");
        Simulation instance = simulation2;
        SimulationRun result = instance.getCurrentRun();
        SimulationRun expResult = simulationRun;
        assertEquals(expResult, result);

    }

    /**
     * Test of canCopySimulation method, of class Simulation.
     */
    @Test
    public void testCanCopySimulation() {
        System.out.println("canCopySimulation");
        Simulation instance = simulation2;
        boolean result = instance.canCopySimulation();
        boolean expResult = true;
        assertEquals(expResult, result);

    }

    /**
     * Test of getTrafficPatternByPK method, of class Simulation.
     */
    @Test
    public void testGetTrafficPatternByPK() {
        System.out.println("getTrafficPatternByPK");
        int pk = 2;
        Simulation instance = simulation2;
        TrafficPattern result = instance.getTrafficPatternByPK(pk);
        TrafficPattern expResult = trafficPattern2;
        assertEquals(expResult, result);

    }

    /**
     * Test of canDeleteRun method, of class Simulation.
     */
    @Test
    public void testCanDeleteRun() {
        System.out.println("canDeleteRun");
        Simulation instance = simulation2;
        boolean result = instance.canDeleteRun();
        boolean expResult = true;
        assertEquals(expResult, result);

    }
    
}
