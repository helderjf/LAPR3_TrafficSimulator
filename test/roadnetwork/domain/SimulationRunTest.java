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
public class SimulationRunTest {
    
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
    ResultSimulation resultSimulation;
    
    
    public SimulationRunTest() {
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
        
        Vehicle vehicle2 = new CombustionVehicle();
        vehicle2.setPK(2);
        
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
        
        
        Section section1 = new Section();
        section1.setPK(1);
        
        Section section2 = new Section();
        section1.setPK(2);
        
        SimPathParcel simPathParcel1 = new SimPathParcel(section1);
        SimPathParcel simPathParcel2 = new SimPathParcel(section2);
        ArrayList<SimPathParcel> lstSimPathParcels = new ArrayList<>();
        lstSimPathParcels.add(simPathParcel1);
        lstSimPathParcels.add(simPathParcel2);
        
        
        SimVehicle simVehicle1 = new SimVehicle(vehicle1, junction1, junction2, lstSimPathParcels, trafficPattern1, 1);
        SimVehicle simVehicle2 = new SimVehicle(vehicle1, junction2, junction3, lstSimPathParcels, trafficPattern1, 2);
        
        ArrayList<SimVehicle> lstSimVehicles = new ArrayList<>();
        lstSimVehicles.add(simVehicle1);
        lstSimVehicles.add(simVehicle2);
        
        ArrayList<SimVehicle> lstSimVehicles2 = new ArrayList<>();
        lstSimVehicles2.add(simVehicle1);
        
        ArrayList<SimVehicle> lstSimVehicles3 = new ArrayList<>();
        lstSimVehicles2.add(simVehicle2);
        
        
        resultSimulation = new ResultSimulation("run1", 10.0, 1.0, bestPathAlgorithm, lstSimVehicles, lstSimVehicles2, lstSimVehicles3);
        
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getResults method, of class SimulationRun.
     */
    @Test
    public void testGetResults() {
        System.out.println("getResults");
        SimulationRun instance = simulationRun;
        instance.setResults(resultSimulation);              
        ResultSimulation result = instance.getResults();
        ResultSimulation expResult = resultSimulation;
        assertEquals(expResult, result);

    }

    /**
     * Test of getName method, of class SimulationRun.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        SimulationRun instance = simulationRun;
        String expResult = "run1";
        String result = instance.getName();
        assertEquals(expResult, result);

    }

    /**
     * Test of getDuration method, of class SimulationRun.
     */
    @Test
    public void testGetDuration() {
        System.out.println("getDuration");
        SimulationRun instance = simulationRun;
        double result = instance.getDuration();
        double expResult = 10.0;
        assertEquals(expResult, result, 0.0);

    }

    /**
     * Test of getTimeStep method, of class SimulationRun.
     */
    @Test
    public void testGetTimeStep() {
        System.out.println("getTimeStep");
        SimulationRun instance = simulationRun;
        double result = instance.getTimeStep();
        double expResult = 1.0;
        assertEquals(expResult, result, 0.0);

    }

    /**
     * Test of getBestPathMethod method, of class SimulationRun.
     */
    @Test
    public void testGetBestPathMethod() {
        System.out.println("getBestPathMethod");
        SimulationRun instance = simulationRun;
        BestPathAlgorithm expResult = bestPathAlgorithm;
        BestPathAlgorithm result = instance.getBestPathMethod();
        assertEquals(expResult, result);

    }

    /**
     * Test of setPK method, of class SimulationRun.
     */
    @Test
    public void testSetPK() {
        System.out.println("setPK");
        int runPK = 1;
        SimulationRun instance = simulationRun;
        instance.setPK(runPK);
        int expResult = 1;
        int result = instance.getM_pk();
        assertEquals(expResult, result, 0);
    }
    
}
