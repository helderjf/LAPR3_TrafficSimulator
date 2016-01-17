/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphutils;

import java.util.ArrayList;
import java.util.Iterator;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import roadnetwork.domain.Section;

/**
 *
 * @author Andre
 */
public class GraphTest {
    
    Graph<String, String> instance = new Graph<>(true) ;
    
    public GraphTest() {
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
     * Test of numVertices method, of class Graph.
     */
    @Test
    public void testNumVertices() {
        System.out.println("Test numVertices");
                
        assertTrue("result should be zero", (instance.numVertices()==0));
        
        Vertex<String,String> vert=instance.insertVertex("A");
        assertTrue("result should be one", (instance.numVertices()==1));       
        vert=instance.insertVertex("B");
        assertTrue("result should be two", (instance.numVertices()==2));
        
        instance.removeVertex("A");
        assertTrue("result should be one", (instance.numVertices()==1));
        instance.removeVertex("B");
        assertTrue("result should be zero", (instance.numVertices()==0));
    }

    /**
     * Test of vertices method, of class Graph.
     */
    @Test
    public void testVertices() {
        System.out.println("Test vertices");

        Iterator<Vertex<String,String>> itVerts = instance.vertices().iterator();
        
        assertTrue("vertices should be empty", itVerts.hasNext()==false);
        
        Vertex<String,String> vert1=instance.insertVertex("A");
        Vertex<String,String> vert2=instance.insertVertex("B");
        	
        itVerts = instance.vertices().iterator();
                
        assertTrue("first vertice should be vert1", (itVerts.next().equals(vert1)==true));
        assertTrue("second vertice should be vert2",(itVerts.next().equals(vert2)==true));

        instance.removeVertex("A");
		
        itVerts = instance.vertices().iterator();
        assertTrue("first vertice should now be vert2",(itVerts.next().equals(vert2))==true);

	instance.removeVertex("B");
		
        itVerts = instance.vertices().iterator();
	assertTrue("vertices should now be empty", (itVerts.hasNext()==false));	
    }






    /**
     * Test of endVertices method, of class Graph.
     */
    @Test
    public void testEndVertices() {
        System.out.println("Test endVertices");
        		
        Vertex<String,String> vert1=instance.insertVertex("A");
        Vertex<String,String> vert2=instance.insertVertex("B");
        Vertex<String,String> vert3=instance.insertVertex("C");
        Vertex<String,String> vert4=instance.insertVertex("D");
        Vertex<String,String> vert5=instance.insertVertex("E");
        
        Edge<String,String> edge1=instance.insertEdge("A","B","Edge1",6);
        Edge<String,String> edge2=instance.insertEdge("A","C","Edge2",1);
        Edge<String,String> edge3=instance.insertEdge("B","D","Edge3",3);
        Edge<String,String> edge4=instance.insertEdge("C","D","Edge4",4);
        Edge<String,String> edge5=instance.insertEdge("C","E","Edge5",1);
        Edge<String,String> edge6=instance.insertEdge("D","A","Edge6",2);
        Edge<String,String> edge7=instance.insertEdge("E","D","Edge7",1);
        Edge<String,String> edge8=instance.insertEdge("E","E","Edge8",1);
		
        Edge<String,String> edge0 = new Edge<>();
        
        Vertex<String,String>[] vertices = instance.endVertices(edge0);

        assertTrue("endVertices should be null", vertices==null);

        vertices = instance.endVertices(edge5);

        String v1 = vertices[0].getElement();
        String v2 = vertices[1].getElement();

        assertTrue("first vertex should be C", v1.compareTo("C")==0);
        assertTrue("second vertex should be E", v2.compareTo("E")==0);
    }

    /**
     * Test of opposite method, of class Graph.
     */
    @Test
    public void testOpposite() {
        System.out.println("Test opposite");
        		
        Vertex<String,String> vert1=instance.insertVertex("A");
        Vertex<String,String> vert2=instance.insertVertex("B");
        Vertex<String,String> vert3=instance.insertVertex("C");
        Vertex<String,String> vert4=instance.insertVertex("D");
        Vertex<String,String> vert5=instance.insertVertex("E");
        
        Edge<String,String> edge1=instance.insertEdge("A","B","Edge1",6);
        Edge<String,String> edge2=instance.insertEdge("A","C","Edge2",1);
        Edge<String,String> edge3=instance.insertEdge("B","D","Edge3",3);
        Edge<String,String> edge4=instance.insertEdge("C","D","Edge4",4);
        Edge<String,String> edge5=instance.insertEdge("C","E","Edge5",1);
        Edge<String,String> edge6=instance.insertEdge("D","A","Edge6",2);
        Edge<String,String> edge7=instance.insertEdge("E","D","Edge7",1);
        Edge<String,String> edge8=instance.insertEdge("E","E","Edge8",1);
		     
        Vertex<String,String> vert = instance.opposite(vert1, edge5);
        assertTrue("opposite should be null", vert==null);
        
        vert = instance.opposite(vert1, edge1);
        assertTrue("opposite should be vert2", vert.equals(vert2)==true);
        
        vert = instance.opposite(vert5, edge8);
        assertTrue("opposite should be vert5", vert.equals(vert5)==true);
    }

    /**
     * Test of outDegree method, of class Graph.
     */
    @Test
    public void testOutDegree() {
        System.out.println("Test outDegree");
        		
        Vertex<String,String> vert1=instance.insertVertex("A");
        Vertex<String,String> vert2=instance.insertVertex("B");
        Vertex<String,String> vert3=instance.insertVertex("C");
        Vertex<String,String> vert4=instance.insertVertex("D");
        Vertex<String,String> vert5=instance.insertVertex("E");
        
        Edge<String,String> edge1=instance.insertEdge("A","B","Edge1",6);
        Edge<String,String> edge2=instance.insertEdge("A","C","Edge2",1);
        Edge<String,String> edge3=instance.insertEdge("B","D","Edge3",3);
        Edge<String,String> edge4=instance.insertEdge("C","D","Edge4",4);
        Edge<String,String> edge5=instance.insertEdge("C","E","Edge5",1);
        Edge<String,String> edge6=instance.insertEdge("D","A","Edge6",2);
        Edge<String,String> edge7=instance.insertEdge("E","D","Edge7",1);
        Edge<String,String> edge8=instance.insertEdge("E","E","Edge8",1);
		    
        Vertex<String,String> vert = new Vertex<>();
        int outdeg = instance.outDegree(vert);    
        assertTrue("degree should be -1", outdeg==-1);
        
        outdeg = instance.outDegree(vert1);
        assertTrue("degree should be 2", outdeg==2);
        
        outdeg = instance.outDegree(vert2);
        assertTrue("degree should be 1", outdeg==1);
         
        outdeg = instance.outDegree(vert5);
        assertTrue("degree should be 2", outdeg==2);  
    }

    /**
     * Test of inDegree method, of class Graph.
     */
    @Test
    public void testInDegree() {
        System.out.println("Test inDegree");
        
        Vertex<String,String> vert1=instance.insertVertex("A");
        Vertex<String,String> vert2=instance.insertVertex("B");
        Vertex<String,String> vert3=instance.insertVertex("C");
        Vertex<String,String> vert4=instance.insertVertex("D");
        Vertex<String,String> vert5=instance.insertVertex("E");
        
        Edge<String,String> edge1=instance.insertEdge("A","B","Edge1",6);
        Edge<String,String> edge2=instance.insertEdge("A","C","Edge2",1);
        Edge<String,String> edge3=instance.insertEdge("B","D","Edge3",3);
        Edge<String,String> edge4=instance.insertEdge("C","D","Edge4",4);
        Edge<String,String> edge5=instance.insertEdge("C","E","Edge5",1);
        Edge<String,String> edge6=instance.insertEdge("D","A","Edge6",2);
        Edge<String,String> edge7=instance.insertEdge("E","D","Edge7",1);
        Edge<String,String> edge8=instance.insertEdge("E","E","Edge8",1);
		       
        Vertex<String,String> vert = new Vertex<>();
        int indeg = instance.inDegree(vert);    
        assertTrue("in degree should be -1", indeg==-1);
        
        indeg = instance.inDegree(vert1);
        assertTrue("in degree should be 1", indeg==1);
        
        indeg = instance.inDegree(vert4);
        assertTrue("in degree should be 3", indeg==3);
         
        indeg = instance.inDegree(vert5);
        assertTrue("in degree should be 2", indeg==2);  
    }




    /**
     * Test of insertVertex method, of class Graph.
     */
    @Test
    public void testInsertVertex() {
        System.out.println("Test insertVertex");
     
        assertTrue("num. vertices should be zero", (instance.numVertices()==0));
        
        Vertex<String,String> vert1=instance.insertVertex("A");
        assertTrue("Num vertices should be one", (instance.numVertices()==1));
        Vertex<String,String> vert2=instance.insertVertex("B");
        assertTrue("Num vertices should be two", (instance.numVertices()==2));
        Vertex<String,String> vert3=instance.insertVertex("C");
        assertTrue("Num vertices should be three", (instance.numVertices()==3));
        Vertex<String,String> vert4=instance.insertVertex("D");
        assertTrue("Num vertices should be four", (instance.numVertices()==4));
        Vertex<String,String> vert5=instance.insertVertex("E");
        assertTrue("Num vertices should be five", (instance.numVertices()==5));
        
        Iterator <Vertex<String,String>> itVert = instance.vertices().iterator();
		
        assertTrue("first vertex should be vert1", (itVert.next().equals(vert1)==true));
        assertTrue("second vertex should be vert2",(itVert.next().equals(vert2)==true));
        assertTrue("third vertex should be vert3", (itVert.next().equals(vert3)==true));
        assertTrue("fourth vertex should be vert4",(itVert.next().equals(vert4)==true));
        assertTrue("fifth vertex should be vert5", (itVert.next().equals(vert5)==true));
    }

    /**
     * Test of insertEdge method, of class Graph.
     */
    @Test
    public void testInsertEdge() {
        System.out.println("Test insertEdge");
        
        assertTrue("num. edges should be zero", (instance.numEdges()==0));

        Edge<String,String> edge1=instance.insertEdge("A","B","Edge1",6);
        assertTrue("num. edges should be 1", (instance.numEdges()==1));      
        
        Edge<String,String> edge2=instance.insertEdge("A","C","Edge2",1);
        assertTrue("num. edges should be 2", (instance.numEdges()==2));
        
        Edge<String,String> edge3=instance.insertEdge("B","D","Edge3",3);
        assertTrue("num. edges should be 3", (instance.numEdges()==3));
        
        Edge<String,String> edge4=instance.insertEdge("C","D","Edge4",4);
        assertTrue("num. edges should be 4", (instance.numEdges()==4));
        
        Edge<String,String> edge5=instance.insertEdge("C","E","Edge5",1);
        assertTrue("num. edges should be 5", (instance.numEdges()==5));
        
        Edge<String,String> edge6=instance.insertEdge("D","A","Edge6",2);
        assertTrue("num. edges should be 6", (instance.numEdges()==6));
        
        Edge<String,String> edge7=instance.insertEdge("E","D","Edge7",1);
        assertTrue("num. edges should be 7", (instance.numEdges()==7));
        
        Edge<String,String> edge8=instance.insertEdge("E","E","Edge8",1);
        assertTrue("num. edges should be 8", (instance.numEdges()==8));
        
        Iterator <Edge<String,String>> itEd = instance.edges().iterator();
		
        itEd.next(); itEd.next();
        assertTrue("third edge should be edge3", (itEd.next().equals(edge3)==true));
        itEd.next(); itEd.next();
        assertTrue("sixth edge should be edge6",(itEd.next().equals(edge6)==true));
    }

    
    /**
     * Test of getVertex method, of class Graph.
     */
    @Test
    public void testGetVertex_GenericType() {
        System.out.println("Test getVertex Generic");
        
        assertTrue("vert should be null", (instance.getVertex("C")==null));
        
        Vertex<String,String> vert1=instance.insertVertex("A");
        Vertex<String,String> vert2=instance.insertVertex("B");
        Vertex<String,String> vert3=instance.insertVertex("C");
        Vertex<String,String> vert4=instance.insertVertex("D");
        Vertex<String,String> vert5=instance.insertVertex("E");
        
        assertTrue("vert should be vert1", (instance.getVertex("A")==vert1));
        assertTrue("vert should be vert2", (instance.getVertex("B")==vert2));
        assertTrue("vert should be vert5", (instance.getVertex("E")==vert5));
        assertTrue("vert should be vert3", (instance.getVertex("C")==vert3));
        assertTrue("vert should be vert4", (instance.getVertex("D")==vert4)); 

    }








    /**
     * Test of toString method, of class Graph.
     */
    @Test
    public void testToString() {
        System.out.println(instance);
    }

   
    
}
