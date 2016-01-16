/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphutils;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import roadnetwork.domain.Junction;
import roadnetwork.domain.Section;
import roadnetwork.domain.SectionDirection;
import roadnetwork.domain.SectionTypology;
import roadnetwork.domain.Segment;
import roadnetwork.domain.Wind;

/**
 *
 * @author josemiranda
 */
public class VertexTest {
    
    Junction node0;
    Junction node1;
    Junction node2;
    ArrayList<Junction> nodeList;

    Segment segment1;
    Segment segment2;

    ArrayList<Segment> list1;
    ArrayList<Segment> list2;

    Section section1;
    Section section2;
    
    Segment segment3;
    
    Vertex<Section, Integer> v1;
    Vertex<Section, Integer> v2;

    Edge<Section, Integer> e1;
    private Map<Vertex<Section,Integer>, Edge<Section,Integer>> out;


    public VertexTest() {
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
        segment3 = new Segment(3, 145, 1.1, 3.1, 91, 0, 21);
        
        list1 = new ArrayList();
        list1.add(segment1);
        list1.add(segment2);
        
        list2 = new ArrayList();
        list2.add(segment3);

        section1 = new Section(1,"E01", node0, node1, SectionTypology.regular_road, SectionDirection.bidirectional, 0, new Wind(20, 3), list1);
        section1.setToll(0);
        
        section2 = new Section(2,"E02", node1, node2, SectionTypology.highway, SectionDirection.unidirectional, 0, new Wind(20, 3), list2);
        section2.setToll(0);
        
  
        v1 = new Vertex<>(1, section1);
        v2 = new Vertex<>(2, section2);

        e1 = new Edge<>(null, 10, v1, v2);
        out = new HashMap<>();
        out.put(v1, e1);
        

        
        
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getKey method, of class Vertex.
     */
    @Test
    public void testGetKey() {
        System.out.println("getKey");
        Vertex instance = v1;
        int expResult = 1;
        int result = instance.getKey();
        assertEquals(expResult, result);
    }

    /**
     * Test of setKey method, of class Vertex.
     */
    @Test
    public void testSetKey() {
        System.out.println("setKey");
        int k = 10;
        Vertex instance = v2;
        instance.setKey(k);
        int result = instance.getKey();
        int expResult = 10;
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getElement method, of class Vertex.
     */
    @Test
    public void testGetElement() {
        System.out.println("getElement");
        Vertex instance = v1;
        Section expResult = section1;
        Object result = instance.getElement();
        assertEquals(expResult, result);
    }

    /**
     * Test of setElement method, of class Vertex.
     */
    @Test
    public void testSetElement() {
        System.out.println("setElement");
        Object vInf = section1;
        Vertex instance = v1;
        instance.setElement(vInf);
        Section expResult = section1;
        Object result = instance.getElement();
        assertEquals(result, result);
    }

    /**
     * Test of getOutgoing method, of class Vertex.
     */
    @Test
    public void testGetOutgoing() {
        System.out.println("getOutgoing");
        Vertex instance = v1;
        instance.getOutgoing().put(v1, e1);
        Map<Vertex<Section, Integer>, Edge<Section, Integer>> expResult = out;
        Map<Vertex<Section, Integer>, Edge<Section, Integer>> result = instance.getOutgoing();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Vertex.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object otherObj = v1;
        
        Vertex instance = new Vertex(1, section1);
        boolean expResult = true;
        boolean result = instance.equals(otherObj);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of clone method, of class Vertex.
     */
    @Test
    public void testClone() {
        System.out.println("clone");
        Vertex instance = new Vertex(1, section1);
        Vertex expResult = v1;
        Vertex result = instance.clone();
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Vertex.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Vertex instance = v1;
        String expResult = "Section{id=1, roadId=E01, beginningNode=Node node0, endingNode=Node node1, "
                + "typology=regular_road, direction=bidirectional, toll=0.0, windDirection=WindDirection{angle=20.0, "
                + "velocity=3.0}, segmentsList=[Segment{index=1, initial_Height=100.0, slope=1.5, lenght=3.2, "
                + "max_Velocity=90.0, min_Velocity=0.0, max_Vehicles=20}, Segment{index=2, initial_Height=148.0, "
                + "slope=1.5, lenght=3.2, max_Velocity=90.0, min_Velocity=0.0, max_Vehicles=20}]} (1): ";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
}
