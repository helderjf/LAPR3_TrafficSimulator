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
 * @author josemiranda
 */
public class RoadTest {

    Junction node0;
    Junction node1;
    Junction node2;
    ArrayList<Junction> nodeList;

    Segment segment1;
    Segment segment2;
    ArrayList<Segment> list1;

    Section section1;

    Segment segment3;
    Segment segment4;
    ArrayList<Segment> list2;

    Section section2;
    ArrayList<Section> sectionlist1;

    Road road1;
    
    Segment segment5;
    ArrayList<Segment> list3;
    Section section3;
    ArrayList<Section> sectionlist2;

    public RoadTest() {
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

        segment3 = new Segment(1, 100, 0, 10 * 1000, 90 * 1000 / 3600, 0, 100);
        segment4 = new Segment(2, 100, 0.5, 5 * 1000, 90 * 1000 / 3600, 0, 50);
        list2 = new ArrayList();
        list2.add(segment3);
        list2.add(segment4);

        section2 = new Section("E01", node1, node2, SectionTypology.regular_road, SectionDirection.bidirectional, 0, new Wind(-5, 3), list2);
        section2.setToll(0);
        sectionlist1 = new ArrayList();
        sectionlist1.add(section1);
        sectionlist1.add(section2);

        road1 = new Road(1, "E01");
        road1.setSections(sectionlist1);
        
        segment5 = new Segment(1, 100, 0, 100*1000, 120*1000/3600, 50*1000/3600, 1000);
        list3 = new ArrayList();
        list3.add(segment5);
        section3 = new Section("A01", node0, node2, SectionTypology.highway, SectionDirection.bidirectional, 12, new Wind(-5, 3), list3);
        section3.setToll(12);
        sectionlist2 = new ArrayList();
        sectionlist2.add(section3);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getId method, of class Road.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Road instance = road1;
        int expResult = 1;
        int result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of setId method, of class Road.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        int id = 10;
        Road instance = road1;
        instance.setId(id);
        int expResult = 10;
        int result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getName method, of class Road.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        Road instance = road1;
        String expResult = "E01";
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of setName method, of class Road.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String name = "New name";
        Road instance = road1;
        instance.setName(name);
        String expResult="New name";
        String result=instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getSections method, of class Road.
     */
    @Test
    public void testGetSections() {
        System.out.println("getSections");
        Road instance = road1;
        ArrayList<Section> expResult = new ArrayList<>();
        expResult.add(section1);
        expResult.add(section2);
        ArrayList<Section> result = instance.getSections();
        assertEquals(expResult, result);
    }

    /**
     * Test of setSections method, of class Road.
     */
    @Test
    public void testSetSections() {
        System.out.println("setSections");
        ArrayList<Section> sections = new ArrayList<>();
        sections.add(section1);
        sections.add(section2);
        sections.add(section3);
        Road instance = road1;
        instance.setSections(sections);
        ArrayList<Section> expResult= new ArrayList<>();
        expResult.add(section1);
        expResult.add(section2);
        expResult.add(section3);
        ArrayList<Section> result=instance.getSections();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Road.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Road road = new Road();
        Road instance = road1;
        boolean expResult = false;
        boolean result = instance.equals(road);
        assertEquals(expResult, result);
        
        road.setId(1);
        road.setName("E01");
        expResult = false;
        result = instance.equals(road);
        assertEquals(expResult, result);
        
        ArrayList<Section> sectionsList= new ArrayList<>();
        sectionsList.add(section1);
        sectionsList.add(section2);
        road.setSections(sectionsList);
        expResult = true;
        result = instance.equals(road);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of toString method, of class Road.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Road instance = road1;
        String expResult = "Road{id=1, name=E01, sections=[Section{id=0, "
                + "roadId=E01, beginningNode=Node node0, endingNode=Node node1,"
                + " typology=regular_road, direction=bidirectional, toll=0.0, "
                + "windDirection=WindDirection{angle=20.0, velocity=3.0}, "
                + "segmentsList=[Segment{index=1, initial_Height=100.0, "
                + "slope=1.5, lenght=3200.0, max_Velocity=25.0, min_Velocity=0.0, "
                + "max_Vehicles=32}, Segment{index=2, initial_Height=148.0, "
                + "slope=1.5, lenght=3200.0, max_Velocity=25.0, min_Velocity=0.0,"
                + " max_Vehicles=32}]}, Section{id=0, roadId=E01, "
                + "beginningNode=Node node1, endingNode=Node node2, "
                + "typology=regular_road, direction=bidirectional, toll=0.0, "
                + "windDirection=WindDirection{angle=-5.0, velocity=3.0}, "
                + "segmentsList=[Segment{index=1, initial_Height=100.0, "
                + "slope=0.0, lenght=10000.0, max_Velocity=25.0, "
                + "min_Velocity=0.0, max_Vehicles=100}, Segment{index=2, "
                + "initial_Height=100.0, slope=0.5, lenght=5000.0, max_Velocity=25.0, "
                + "min_Velocity=0.0, max_Vehicles=50}]}]}";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

}
