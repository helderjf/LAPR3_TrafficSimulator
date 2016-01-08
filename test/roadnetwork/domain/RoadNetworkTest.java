/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadnetwork.domain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author antonio
 */
public class RoadNetworkTest {

    Junction n1;
    Junction n2;
    Junction n3;
    Section s1;
    Section s2;
    Section s3;

    public RoadNetworkTest() {
    }

    @Before
    public void setUp() {
        //N1
        n1 = new Junction("N1");
        n1.setPK(1);
        //N2
        n2 = new Junction("N2");
        n2.setPK(2);
        //N3
        n3 = new Junction("N3");
        n3.setPK(3);
        
        //S
        s1 = new Section();
        s2 = new Section();
        s3 = new Section();

    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getNodeByPK method, of class RoadNetwork.
     */
    @Test
    public void testGetNodeByPK() {
        System.out.println("getNodeByPK");
        int pk = 1;
        RoadNetwork instance = new RoadNetwork();
         //RoadNetworkSetup
        instance.getNodeList().add(n1);
        
        Junction expResult = n1;
        Junction result = instance.getNodeByPK(pk);
        assertEquals(expResult, result);
    }

    /**
     * Test of getNodeByID method, of class RoadNetwork.
     */
    @Test
    public void testGetNodeByID() {
        System.out.println("getNodeByID");
        
        RoadNetwork instance = new RoadNetwork();
        //RoadNetworkSetup
        instance.getNodeList().add(n1);
        
        //For null case
        String id = null;
        Junction expResult = null;
        Junction result = instance.getNodeByID(id);
        assertEquals(expResult, result);
        
        //For expeted case
        id = "N1";
        expResult = n1;
        result = instance.getNodeByID(id);
        assertEquals(expResult, result);
        
        //Not found case
        id = "N2";
        expResult = null;
        result = instance.getNodeByID(id);
        assertEquals(expResult, result);
    }

}
