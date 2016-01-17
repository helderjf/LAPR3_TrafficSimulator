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
        
        Junction node0;
        Junction node1;
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
        SimPathParcel expResult = new SimPathParcel(section1) {};
        expResult = (SimPathParcel) expResult.createReversePP();
        SimPathParcel result = (SimPathParcel) instance.createReversePP();
        assertEquals(expResult.toString2(), result.toString2());
    }

    /**
     * Test of getTheoreticalEnergyConsumption method, of class SimPathParcel.
     */
    @Test
    public void testGetTheoreticalEnergyConsumption() {
        System.out.println("getTheoreticalEnergyConsumption");
        SimPathParcel instance = simPathParcel;
        instance.setTheoreticalEnergyConsumption(5.0);
        double result = instance.getTheoreticalEnergyConsumption();
        double expResult = 5.0;
        assertEquals(expResult, result, 0.0);

    }

    /**
     * Test of setTheoreticalEnergyConsumption method, of class SimPathParcel.
     */
    @Test
    public void testSetTheoreticalEnergyConsumption() {
        System.out.println("setTheoreticalEnergyConsumption");
        double energyConsumption = 5.0;
        SimPathParcel instance = simPathParcel;
        instance.setTheoreticalEnergyConsumption(energyConsumption);
        double result = instance.getTheoreticalEnergyConsumption();
        double expResult = 5.0;
        assertEquals(expResult, result, 0.0);

    }

    /**
     * Test of getTollCosts method, of class SimPathParcel.
     */
    @Test
    public void testGetTollCosts() {
        System.out.println("getTollCosts");
        SimPathParcel instance = simPathParcel;
        double expResult = 2.0;
        simPathParcel.setTollCosts(2.0);
        double result = instance.getTollCosts();
        assertEquals(expResult, result, 0.0);

    }

    /**
     * Test of setTollCosts method, of class SimPathParcel.
     */
    @Test
    public void testSetTollCosts() {
        System.out.println("setTollCosts");
        double tollCosts = 2.0;
        SimPathParcel instance = simPathParcel;
        instance.setTollCosts(tollCosts);
        double result = instance.getTollCosts();
        double expResult = 2.0;
        assertEquals(expResult, result, 0.0);

    }

    /**
     * Test of getPredictedExitTime method, of class SimPathParcel.
     */
    @Test
    public void testGetPredictedExitTime() {
        System.out.println("getPredictedExitTime");
        SimPathParcel instance = simPathParcel;
        simPathParcel.setPredictedExitTime(2.0);
        double expResult = 2.0;
        double result = instance.getPredictedExitTime();
        assertEquals(expResult, result, 0.0);

    }

    /**
     * Test of setPredictedExitTime method, of class SimPathParcel.
     */
    @Test
    public void testSetPredictedExitTime() {
        System.out.println("setPredictedExitTime");
        double time = 2.0;
        SimPathParcel instance = simPathParcel;
        instance.setPredictedExitTime(time);
        double expResult = 2.0;
        double result = instance.getPredictedExitTime();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getSimEnergyConsumption method, of class SimPathParcel.
     */
    @Test
    public void testGetSimEnergyConsumption() {
        System.out.println("getSimEnergyConsumption");
        SimPathParcel instance = simPathParcel;
        double expResult = 3.0;
        instance.addToSimEnergyConsumption(3.0);
        double result = instance.getSimEnergyConsumption();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of addToSimEnergyConsumption method, of class SimPathParcel.
     */
    @Test
    public void testAddToSimEnergyConsumption() {
        System.out.println("addToSimEnergyConsumption");
        double idleCosumption = 2.0;
        SimPathParcel instance = simPathParcel;
        instance.setTheoreticalEnergyConsumption(3.0);
        instance.addToSimEnergyConsumption(idleCosumption);
        double result = idleCosumption + instance.getTheoreticalEnergyConsumption();
        double expResult = 5.0;
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of initializePredictedExitTime method, of class SimPathParcel.
     */
    @Test
    public void testInitializePredictedExitTime() {
        System.out.println("initializePredictedExitTime");
        double injectionTime = 2.0;
        SimPathParcel instance = simPathParcel;
        instance.setTheoreticalTravelTime(3.0);
        instance.initializePredictedExitTime(injectionTime);
        double result = injectionTime + instance.getTheoreticalTravelTime();
        double expResult = 5.0;
        assertEquals(expResult, result, 0.0);
    }
    
}
