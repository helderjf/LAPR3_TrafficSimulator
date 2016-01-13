/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadnetwork.domain;

import java.util.ArrayList;
import java.util.HashMap;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author josemiranda
 */
public class StaticPathParcelTest {

    Junction node0;
    Junction node1;
    Junction node2;
    ArrayList<Junction> nodeList;

    Segment segment1;
    Segment segment2;
    ArrayList<Segment> list1;

    Section section1;

    StaticPathParcel pp1;

    Segment segment3;
    Segment segment4;
    ArrayList<Segment> list2;

    Section section2;

    public StaticPathParcelTest() {
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

        segment1 = new Segment(1, 100, 1.5, 3.2 * 1000, 90 * 1000 / 3600, 0, 32);
        segment2 = new Segment(2, 148, 1.5, 3.2 * 1000, 90 * 1000 / 3600, 0, 32);
        list1 = new ArrayList();
        list1.add(segment1);
        list1.add(segment2);

        section1 = new Section("E01", node0, node1, SectionTypology.regular_road, SectionDirection.bidirectional, 0, new Wind(20, 3), list1);
        section1.setToll(0);

        pp1 = new StaticPathParcel(section1, SimDirection.direct);
        pp1.setTheoreticalEnergyConsumption(1000);
        pp1.setTheoreticalTravelTime(200);
        pp1.setTollCosts(15);

        segment3 = new Segment(1, 100, 0, 10 * 1000, 90 * 1000 / 3600, 0, 100);
        segment4 = new Segment(2, 100, 0.5, 5 * 1000, 90 * 1000 / 3600, 0, 50);
        list2 = new ArrayList();
        list2.add(segment3);
        list2.add(segment4);

        section2 = new Section("E01", node1, node2, SectionTypology.regular_road, SectionDirection.bidirectional, 0, new Wind(-5, 3), list2);
        section2.setToll(0);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getSection method, of class StaticPathParcel.
     */
    @Test
    public void testGetSection() {
        System.out.println("getSection");
        StaticPathParcel instance = pp1;
        Section expResult = section1;
        Section result = instance.getSection();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDirection method, of class StaticPathParcel.
     */
    @Test
    public void testGetDirection() {
        System.out.println("getDirection");
        StaticPathParcel instance = pp1;
        SimDirection expResult = SimDirection.direct;
        SimDirection result = instance.getDirection();
        assertEquals(expResult, result);
    }

    /**
     * Test of getTheoreticalTravelTime method, of class StaticPathParcel.
     */
    @Test
    public void testGetTheoreticalTravelTime() {
        System.out.println("getTheoreticalTravelTime");
        StaticPathParcel instance = pp1;
        double expResult = 200.0;
        double result = instance.getTheoreticalTravelTime();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getTheoreticalEnergyConsumption method, of class StaticPathParcel.
     */
    @Test
    public void testGetTheoreticalEnergyConsumption() {
        System.out.println("getTheoreticalEnergyConsumption");
        StaticPathParcel instance = pp1;
        double expResult = 1000.0;
        double result = instance.getTheoreticalEnergyConsumption();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getTollCosts method, of class StaticPathParcel.
     */
    @Test
    public void testGetTollCosts() {
        System.out.println("getTollCosts");
        StaticPathParcel instance = pp1;
        double expResult = 15.0;
        double result = instance.getTollCosts();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setSection method, of class StaticPathParcel.
     */
    @Test
    public void testSetSection() {
        System.out.println("setSection");
        Section m_section = section2;
        StaticPathParcel instance = pp1;
        instance.setSection(m_section);
        Section expResult = section2;
        Section result = instance.getSection();
        assertEquals(expResult, result);
    }

    /**
     * Test of setDirection method, of class StaticPathParcel.
     */
    @Test
    public void testSetDirection() {
        System.out.println("setDirection");
        SimDirection m_direction = SimDirection.reverse;
        StaticPathParcel instance = pp1;
        instance.setDirection(m_direction);
        SimDirection expResult = SimDirection.reverse;
        SimDirection result = instance.getDirection();
        assertEquals(expResult, result);
    }

    /**
     * Test of setTheoreticalTravelTime method, of class StaticPathParcel.
     */
    @Test
    public void testSetTheoreticalTravelTime() {
        System.out.println("setTheoreticalTravelTime");
        double m_travelTime = 300.0;
        StaticPathParcel instance = pp1;
        instance.setTheoreticalTravelTime(m_travelTime);
        double expResult=300;
        double result=instance.getTheoreticalTravelTime();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setTheoreticalEnergyConsumption method, of class StaticPathParcel.
     */
    @Test
    public void testSetTheoreticalEnergyConsumption() {
        System.out.println("setTheoreticalEnergyConsumption");
        double energyConsumption = 2500.0;
        StaticPathParcel instance = pp1;
        instance.setTheoreticalEnergyConsumption(energyConsumption);
        double expResult=2500;
        double result=instance.getTheoreticalEnergyConsumption();
        assertEquals(expResult, result,0.0);
    }

    /**
     * Test of setTollCosts method, of class StaticPathParcel.
     */
    @Test
    public void testSetTollCosts() {
        System.out.println("setTollCosts");
        double tollCosts = 30.0;
        StaticPathParcel instance = pp1;
        instance.setTollCosts(tollCosts);
        double expResult=30;
        double result=instance.getTollCosts();
        assertEquals(expResult, result,0.0);
    }

    /**
     * Test of createReversePP method, of class StaticPathParcel.
     */
    @Test
    public void testCreateReversePP() {
        System.out.println("createReversePP");
        StaticPathParcel instance = pp1;
        StaticPathParcel expResult = new StaticPathParcel(section1);
        PathParcel result = instance.createReversePP();
        assertEquals(expResult.getSection(), result.getSection());
    }
    
}
