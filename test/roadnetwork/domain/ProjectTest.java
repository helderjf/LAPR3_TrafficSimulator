/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadnetwork.domain;

import java.util.ArrayList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author antonio
 */
public class ProjectTest {

    Vehicle v1;
    Vehicle v2;
    Vehicle v3;

    public ProjectTest() {
    }

    @Before
    public void setUp() {
        v1 = new CombustionVehicle();
        v1.setName("Clio");
        v2 = new ElectricVehicle();
        v2.setName("Model S");
        v3 = new HybridVehicle();
        v3.setName("Prius");
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getVehicleByName method, of class Project.
     */
    @Test
    public void testGetVehicleByName() {
        System.out.println("getVehicleByName");
        

        //Project Setup
        Project instance = new Project();
        instance.setVehicleList(new ArrayList<>());
        instance.getVehicleList().add(v1);
        instance.getVehicleList().add(v2);
        instance.getVehicleList().add(v3);

        //Test
        String vehicleName = "Model S";
        Vehicle expResult = v2;
        Vehicle result = instance.getVehicleByName(vehicleName);
        assertEquals(expResult, result);
        
        //Test null
        vehicleName = null;
        expResult = null;
        result = instance.getVehicleByName(vehicleName);
        assertEquals(expResult, result);
        
        //Test not found
        vehicleName = "F430";
        expResult = null;
        result = instance.getVehicleByName(vehicleName);
        assertEquals(expResult, result);
    }

}
