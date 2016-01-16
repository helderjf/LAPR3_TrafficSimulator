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
public class SimPathParcelTest {
    
    Junction node0;
    Junction node1;
    Junction node2;
    ArrayList<Junction> nodeList;

    Segment segment1;
    Segment segment2;
    ArrayList<Segment> list1;

    Section section1;
    
    Segment segment3;
    
    SimPathParcel simPathParcel;
    
    PathParcel pathParcel;
    
    public SimPathParcelTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        
        node0 = new Junction("node0");
        node1 = new Junction("node1");
        node2 = new Junction("node2");
        nodeList = new ArrayList();
        nodeList.add(node0);
        nodeList.add(node1);
        nodeList.add(node2);

        segment1 = new Segment(1, 100, 1.5, 3.2, 90, 0, 20);
        segment2 = new Segment(2, 148, 1.5, 3.2, 90, 0, 20);
        list1 = new ArrayList();
        list1.add(segment1);
        list1.add(segment2);

        section1 = new Section(1,"E01", node0, node1, SectionTypology.regular_road, SectionDirection.bidirectional, 0, new Wind(20, 3), list1);
        section1.setToll(0);
        
        segment3 = new Segment(1, 100, 0, 10, 90, 0, 30);
        
        simPathParcel = new SimPathParcel(section1);
        
 
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getSection method, of class SimPathParcel.
     */
    @Test
    public void testGetSection() {
        System.out.println("getSection");
        SimPathParcel instance = simPathParcel;
        Section expResult = section1;
        Section result = instance.getSection();

    }

    /**
     * Test of getSegment method, of class SimPathParcel.
     */
    @Test
    public void testGetSegment() {
        System.out.println("getSegment");
        SimPathParcel instance = simPathParcel;
        instance.setSegment(segment1);
        Segment expResult = segment1;
        Segment result = instance.getSegment();
        assertEquals(expResult, result);

    }

    /**
     * Test of getDirection method, of class SimPathParcel.
     */
    @Test
    public void testGetDirection() {
        System.out.println("getDirection");
        SimPathParcel instance = simPathParcel;
        SimDirection expResult = SimDirection.direct;
        instance.setDirection(SimDirection.direct);
        SimDirection result = instance.getDirection();
        assertEquals(expResult, result);

    }

    /**
     * Test of getTheoreticalTravelTime method, of class SimPathParcel.
     */
    @Test
    public void testGetTheoreticalTravelTime() {
        System.out.println("getTheoreticalTravelTime");
        SimPathParcel instance = simPathParcel;
        instance.setTheoreticalTravelTime(10.0);
        double result = instance.getTheoreticalTravelTime();
        double expResult = 10.0;
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setSection method, of class SimPathParcel.
     */
    @Test
    public void testSetSection() {
        System.out.println("setSection");
        Section m_section = section1;
        SimPathParcel instance = simPathParcel;
        instance.setSection(m_section);
        Section expResult = section1;
        Section result = instance.getSection();
    }

    /**
     * Test of setSegment method, of class SimPathParcel.
     */
    @Test
    public void testSetSegment() {
        System.out.println("setSegment");
        Segment m_segment = segment1;
        SimPathParcel instance = simPathParcel;
        instance.setSegment(m_segment);
        Segment expResult = segment1;
        Segment result = instance.getSegment();
        assertEquals(expResult, result);
    }

    /**
     * Test of setDirection method, of class SimPathParcel.
     */
    @Test
    public void testSetDirection() {
        System.out.println("setDirection");
        SimDirection m_direction = SimDirection.direct;
        SimPathParcel instance = simPathParcel;
        instance.setDirection(m_direction);
        SimDirection result = instance.getDirection();
        SimDirection expResult = SimDirection.direct;
        assertEquals(expResult, result);
    }

    /**
     * Test of setTheoreticalTravelTime method, of class SimPathParcel.
     */
    @Test
    public void testSetTheoreticalTravelTime() {
        System.out.println("setTheoreticalTravelTime");
        double time = 10.0;
        SimPathParcel instance = simPathParcel;
        instance.setTheoreticalTravelTime(time);
        double result = instance.getTheoreticalTravelTime();
        double expResult = 10.0;
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getSimInTime method, of class SimPathParcel.
     */
    @Test
    public void testGetSimInTime() {
        System.out.println("getSimInTime");
        SimPathParcel instance = simPathParcel;
        double expResult = 5.0;
        instance.setSimInTime(5.0);
        double result = instance.getSimInTime();
        assertEquals(expResult, result, 0.0);

    }

    /**
     * Test of setSimInTime method, of class SimPathParcel.
     */
    @Test
    public void testSetSimInTime() {
        System.out.println("setSimInTime");
        double time = 5.0;
        SimPathParcel instance = simPathParcel;
        instance.setSimInTime(time);
        double expResult = 5.0;
        double result = instance.getSimInTime();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getSimExitTime method, of class SimPathParcel.
     */
    @Test
    public void testGetSimExitTime() {
        System.out.println("getSimExitTime");
        SimPathParcel instance = simPathParcel;
        instance.setSimExitTime(5.0);
        double expResult = 5.0;
        double result = instance.getSimExitTime();
        assertEquals(expResult, result, 0.0);
        
    }

    /**
     * Test of setSimExitTime method, of class SimPathParcel.
     */
    @Test
    public void testSetSimExitTime() {
        System.out.println("setSimExitTime");
        double time = 5.0;
        SimPathParcel instance = simPathParcel;
        instance.setSimExitTime(time);
        double expResult = 5.0;
        double result = instance.getSimExitTime();
        assertEquals(expResult, result, 0.0);

    }

    /**
     * Test of createReversePP method, of class SimPathParcel.
     */
    @Test
    public void testCreateReversePP() {
        System.out.println("createReversePP");
        SimPathParcel instance = simPathParcel;
        SimPathParcel expResult = new SimPathParcel(section1);
        expResult.createReversePP();
        PathParcel result = instance.createReversePP();
        assertEquals(expResult, result);
    }

    /**
     * Test of getTheoreticalEnergyConsumption method, of class SimPathParcel.
     */
    @Test
    public void testGetTheoreticalEnergyConsumption() {
        System.out.println("getTheoreticalEnergyConsumption");
        SimPathParcel instance = null;
        double expResult = 0.0;
        double result = instance.getTheoreticalEnergyConsumption();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTheoreticalEnergyConsumption method, of class SimPathParcel.
     */
    @Test
    public void testSetTheoreticalEnergyConsumption() {
        System.out.println("setTheoreticalEnergyConsumption");
        double energyConsumption = 0.0;
        SimPathParcel instance = null;
        instance.setTheoreticalEnergyConsumption(energyConsumption);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTollCosts method, of class SimPathParcel.
     */
    @Test
    public void testGetTollCosts() {
        System.out.println("getTollCosts");
        SimPathParcel instance = null;
        double expResult = 0.0;
        double result = instance.getTollCosts();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTollCosts method, of class SimPathParcel.
     */
    @Test
    public void testSetTollCosts() {
        System.out.println("setTollCosts");
        double tollCosts = 0.0;
        SimPathParcel instance = null;
        instance.setTollCosts(tollCosts);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPredictedExitTime method, of class SimPathParcel.
     */
    @Test
    public void testGetPredictedExitTime() {
        System.out.println("getPredictedExitTime");
        SimPathParcel instance = null;
        double expResult = 0.0;
        double result = instance.getPredictedExitTime();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPredictedExitTime method, of class SimPathParcel.
     */
    @Test
    public void testSetPredictedExitTime() {
        System.out.println("setPredictedExitTime");
        double time = 0.0;
        SimPathParcel instance = null;
        instance.setPredictedExitTime(time);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSimEnergyConsumption method, of class SimPathParcel.
     */
    @Test
    public void testGetSimEnergyConsumption() {
        System.out.println("getSimEnergyConsumption");
        SimPathParcel instance = null;
        double expResult = 0.0;
        double result = instance.getSimEnergyConsumption();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addToSimEnergyConsumption method, of class SimPathParcel.
     */
    @Test
    public void testAddToSimEnergyConsumption() {
        System.out.println("addToSimEnergyConsumption");
        double idleCosumption = 0.0;
        SimPathParcel instance = null;
        instance.addToSimEnergyConsumption(idleCosumption);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of initializePredictedExitTime method, of class SimPathParcel.
     */
    @Test
    public void testInitializePredictedExitTime() {
        System.out.println("initializePredictedExitTime");
        double injectionTime = 0.0;
        SimPathParcel instance = null;
        instance.initializePredictedExitTime(injectionTime);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
