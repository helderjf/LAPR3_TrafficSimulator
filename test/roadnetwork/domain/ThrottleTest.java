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
public class ThrottleTest {
    
    Regime r1;
    Regime r2;
    Regime r3;
    ArrayList<Regime> lr1;
    Throttle t1;
    Regime r4;

    public ThrottleTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        r1 = new Regime(85, 1000, 2499, 8.2);
        r2 = new Regime(95, 2500, 3999, 6.2);
        r3 = new Regime(80, 4000, 5500, 10.2);
        lr1 = new ArrayList();
        lr1.add(r1);
        lr1.add(r2);
        lr1.add(r3);
        t1 = new Throttle("25", lr1);
        
        r4 = new Regime(135, 1000, 2499, 5.2);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getID method, of class Throttle.
     */
    @Test
    public void testGetID() {
        System.out.println("getID");
        Throttle instance = t1;
        String expResult = "25";
        String result = instance.getID();
        assertEquals(expResult, result);
    }

    /**
     * Test of getRegimeList method, of class Throttle.
     */
    @Test
    public void testGetRegimeList() {
        System.out.println("getRegimeList");
        Throttle instance = t1;
        ArrayList<Regime> expResult = new ArrayList<>();
        expResult.add(r1);
        expResult.add(r2);
        expResult.add(r3);
        ArrayList<Regime> result = instance.getRegimeList();
        assertEquals(expResult, result);
    }

    /**
     * Test of setID method, of class Throttle.
     */
    @Test
    public void testSetID() {
        System.out.println("setID");
        String id = "50";
        Throttle instance = t1;
        instance.setID(id);
        String expResult="50";
        String result=instance.getID();
        assertEquals(expResult, result);
    }

    /**
     * Test of setRegimeList method, of class Throttle.
     */
    @Test
    public void testSetRegimeList() {
        System.out.println("setRegimeList");
        ArrayList<Regime> regimeList = lr1;
        Throttle instance = t1;
        instance.setRegimeList(regimeList);
        ArrayList<Regime> expResult=new ArrayList<>();
        expResult.add(r1);
        expResult.add(r2);
        expResult.add(r3);
        ArrayList<Regime> result=instance.getRegimeList();
        assertEquals(expResult, result);
    }

    /**
     * Test of addRegime method, of class Throttle.
     */
    @Test
    public void testAddRegime() {
        System.out.println("addRegime");
        Regime regime = r4;
        Throttle instance = t1;
        instance.addRegime(regime);
        ArrayList<Regime> expResult=new ArrayList<>();
        expResult.add(r1);
        expResult.add(r2);
        expResult.add(r3);
        expResult.add(r4);
        ArrayList<Regime> result=instance.getRegimeList();
        assertEquals(expResult, result);
    }
    
}
