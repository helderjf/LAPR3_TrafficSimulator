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
public class RoadIT {
    
    public RoadIT() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
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
        Road instance = new Road();
        int expResult = 0;
        int result = instance.getId();
        assertEquals(expResult, result);

    }

    /**
     * Test of setId method, of class Road.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        int id = 0;
        Road instance = new Road();
        instance.setId(id);

    }

    /**
     * Test of getName method, of class Road.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        Road instance = new Road();
        String expResult = "";
        String result = instance.getName();
        assertEquals(expResult, result);

    }

    /**
     * Test of setName method, of class Road.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String name = "";
        Road instance = new Road();
        instance.setName(name);

    }

    /**
     * Test of getSections method, of class Road.
     */
    @Test
    public void testGetSections() {
        System.out.println("getSections");
        Road instance = new Road();
        ArrayList<Section> expResult = null;
        ArrayList<Section> result = instance.getSections();
        assertEquals(expResult, result);

    }

    /**
     * Test of setSections method, of class Road.
     */
    @Test
    public void testSetSections() {
        System.out.println("setSections");
        ArrayList<Section> sections = null;
        Road instance = new Road();
        instance.setSections(sections);
    }


    /**
     * Test of equals method, of class Road.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = null;
        Road instance = new Road();
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    
}
