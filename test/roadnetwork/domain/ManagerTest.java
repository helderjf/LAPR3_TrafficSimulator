/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadnetwork.domain;

import IO.ExportCSV;
import IO.ExportHTML;
import data.access.layer.DataAccessObject;
import data.access.layer.ProjectReader;
import data.access.layer.ProjectWriter;
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
public class ManagerTest {
    
    Manager manager1;
    DataAccessObject dao1;
    ExportCSV exportCSV1;
    
    Project project1;
    
    public ManagerTest() {
       dao1 = new DataAccessObject("jdbc:oracle:thin:@//gandalf.dei.isep.ipp.pt:1521/pdborcl", "LAPR3_60", "lapr32015g60"); 

    }
    
    @BeforeClass
    public static void setUpClass() {

    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        
        manager1 = new Manager("Manager1");
        
        
        project1 = new Project();
        project1.setName("Project1");
        
        exportCSV1 = new ExportCSV("test");
        
        
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getM_name method, of class Manager.
     */
    @Test
    public void testGetM_name() {
        System.out.println("getM_name");
        Manager instance = manager1;
        String expResult = "Manager1";
        String result = instance.getM_name();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCurrentProject method, of class Manager.
     */
    @Test
    public void testGetCurrentProject() {
        System.out.println("getCurrentProject");
        Manager instance = manager1;
        instance.setCurrentProject(project1);
        String result1 = instance.getM_name();
        Project result = instance.getCurrentProject();
        Project expResult = project1;
        String expResult1 = manager1.getM_name();
        assertEquals(expResult1, result1);
       

    }

    /**
     * Test of getdataAccessLayer method, of class Manager.
     */
    @Test
    public void testGetdataAccessLayer() {
        System.out.println("getdataAccessLayer");
        Manager instance = manager1;
        DataAccessObject expResult = dao1;
        String expResult1 = expResult.getM_dbUrl();
        
        DataAccessObject result = instance.getdataAccessLayer();
        String result1 = result.getM_dbUrl();
        
        assertEquals(expResult1, result1);
        
    }







    
}
