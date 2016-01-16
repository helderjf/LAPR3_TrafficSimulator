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
public class ImportedResultSingleTrafficPatternTest {
    
    ImportedResultSingleTrafficPattern importedResultSingleTrafficPattern;
    
    ArrayList<String> m_road;
    ArrayList<String> m_inNode;
    ArrayList<String> m_outNode;
    ArrayList<Integer> m_segIndex;
    ArrayList<String> m_direction;
    ArrayList<Double> m_avgConsumption;
    ArrayList<Double> m_avgTimeSpent;
    
    public ImportedResultSingleTrafficPatternTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        
        importedResultSingleTrafficPattern = new ImportedResultSingleTrafficPattern();
        
        m_road = new ArrayList<>();
        m_inNode = new ArrayList<>();
        m_outNode = new ArrayList<>();
        m_segIndex = new ArrayList<>();
        m_direction = new ArrayList<>();
        m_avgConsumption = new ArrayList<>();
        m_avgTimeSpent = new ArrayList<> ();
        
        m_road.add("R1");
        m_inNode.add("A");
        m_outNode.add("B");
        m_segIndex.add(1);
        m_direction.add("directed");
        m_avgConsumption.add(1.0);
        m_avgTimeSpent.add(2.0);
        
        importedResultSingleTrafficPattern.m_avgConsumption = m_avgConsumption;
        importedResultSingleTrafficPattern.m_avgTimeSpent = m_avgTimeSpent;
        importedResultSingleTrafficPattern.m_direction = m_direction;
        importedResultSingleTrafficPattern.m_inNode = m_inNode;
        importedResultSingleTrafficPattern.m_outNode = m_outNode;
        importedResultSingleTrafficPattern.m_road = m_road;
        importedResultSingleTrafficPattern.m_segIndex = m_segIndex;
 
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getGlobalResultsHTMLCode method, of class ImportedResultSingleTrafficPattern.
     */
    @Test
    public void testGetGlobalResultsHTMLCode() {
        System.out.println("getGlobalResultsHTMLCode");
        ImportedResultSingleTrafficPattern instance = importedResultSingleTrafficPattern;
        String result = instance.getGlobalResultsHTMLCode();
        String expResult = "";
        assertEquals(expResult, result);

    }
    
}
