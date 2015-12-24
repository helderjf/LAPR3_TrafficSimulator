
package graphutils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author DEI-ESINF
 * @param <V>
 * @param <E>
 */

public class Graph<V,E> implements GraphInterface<V,E> {
    
    private int numVert;
    private int numEdge;
    private boolean isDirected;
    private ArrayList<Vertex<V,E>> listVert;  //Vertice list
    private ArrayList<Edge<V,E>> listEdge;    //Edges list
    
    // Constructs an empty graph (either undirected or directed)
    public Graph(boolean directed) { 
        numVert=0; 
        numEdge=0;
        isDirected=directed;
        listVert = new ArrayList<>();
        listEdge= new ArrayList<>();
    }
    
    @Override
    public int numVertices(){ return numVert; }
      
    @Override
    public Iterable<Vertex<V,E>> vertices() { return listVert; }
      
    @Override
    public int numEdges(){ return numEdge; }
    
    /**
     * Returns all the edges of the graph as an iterable collection
     * @return Iterable Eddge
     */
    @Override
    public Iterable<Edge<V,E>> edges() {
        return listEdge;
    }
    
    

    
    /* Returns the edge from vorig to vdest, or null if vertices are not adjacent
   * @param vorig
   * @param vdest
   * @return the edge or null if vertices are not adjacent or don't exist 
   */
    @Override
    public ArrayList<Edge<V,E>> getEdge(Vertex<V,E> vorig, Vertex<V,E> vdest){
       
        if (listVert.contains(vorig) && listVert.contains(vdest))
           return vorig.getOutgoing().get(vdest);

        return null;
    }
    
    /* Returns the vertices of edge e as an array of length two 
   * If the graph is directed, the first vertex is the origin, and
   * the second is the destination.  If the graph is undirected, the
   * order is arbitrary.
   * @param e
   * @return array of two vertices or null if edge doesn't exist 
   */
    @Override
    public Vertex<V,E>[] endVertices(Edge<V,E> e)
    { 
        
	for (Edge<V,E> edge: edges())
	{
            if (edge.equals(e))
                return edge.getEndpoints();
	}
	return null;
 
    }

    
    /* Returns the vertex that is opposite vertex v on edge e. 
   * @param v
   * @param e
   * @return opposite vertex, or null if vertex or edge don't exist
   */
    @Override
    public Vertex<V,E> opposite(Vertex<V,E> vert, Edge<V,E> e){
        
        if(!listVert.contains(vert))
            return null;
 
        if(vert.equals(e.getVOrig()))
        {
            return e.getVDest();
        }
        else if(vert.equals(e.getVDest()))
        {
            return e.getVOrig();
        }
        else
        {
            return null;
        } 
    }
   
    /**
   * Returns the number of edges leaving vertex v
   * For an undirected graph, this is the same result returned by inDegree
   * @param v 
   * @return number of edges leaving vertex v, -1 if vertex doesn't exist  
   */
    @Override
    public int outDegree(Vertex<V,E> v){
 
        if (listVert.contains(v))
            return v.getOutgoing().size();
        else
            return -1;
    }
     
    /**
   * Returns the number of edges for which vertex v is the destination 
   * For an undirected graph, this is the same result returned by outDegree
   * @param v 
   * @return number of edges leaving vertex v, -1 if vertex doesn't exist  
   */
    @Override
    public int inDegree(Vertex<V,E> v){
       
        if(!listVert.contains(v))
            return -1;
       
        Iterable<Edge<V, E>> it =  this.edges();
        int cont=0;
        Iterator<Edge<V, E>> ite =  it.iterator();
       
        while(ite.hasNext())
        {
            if(ite.next().getVDest().equals(v))
            {
                cont++;
            }
        }
       
        return cont;
       
    }
     
    /* Returns an iterable collection of edges for which vertex v is the origin 
  * for an undirected graph, this is the same result returned by incomingEdges
  * @param v
  * @return iterable collection of edges, null if vertex doesn't exist
  */
    @Override
    public Iterable<Edge<V,E>> outgoingEdges(Vertex<V,E> v){
 
        if (!listVert.contains(v))
            return null;
        
        ArrayList<Edge<V,E>> edges = new ArrayList<>();
        
        Map<Vertex<V,E>,ArrayList<Edge<V,E>>>  map = v.getOutgoing();          
        Iterator<Map.Entry<Vertex<V,E>,ArrayList<Edge<V,E>>>> it = map.entrySet().iterator();
        while(it.hasNext()){
            for(Edge<V,E> e : it.next().getValue()){
                edges.add(e);
            }
        }
       
        return edges ;
    }
    
    /* Returns an iterable collection of edges for which vertex v is the destination
  * For an undirected graph this is the same result as returned by incomingEdges
  * @param v
  * @return iterable collection of edges reaching vertex, null if vertex doesn't exist
  */  
    @Override
    public Iterable<Edge<V,E>> incomingEdges(Vertex<V,E> v){
 
        if(!listVert.contains(v))
            return null;
        
        Iterable<Edge<V, E>> it =  this.edges();
        Iterator<Edge<V, E>> ite =  it.iterator();
        
        ArrayList<Edge<V,E>> listEdge = new ArrayList<>();
        
    
        while(ite.hasNext())
        {
            Edge<V,E> e=ite.next();
            if(e.getVDest().equals(v))
            {
                listEdge.add(e);
            }
        }
        
        return listEdge;
    }
            
    /* Inserts and returns a new vertex with some specific comparable type
   * @param element the vertex contents
   * @return a new vertex
   */
    @Override
    public Vertex<V,E> insertVertex(V vInf){
         
        Vertex<V,E> vert = getVertex(vInf);
        if (vert == null){
            Vertex<V,E> newvert = new Vertex<>(numVert,vInf);
            listVert.add(newvert);
            numVert++;
            return newvert;
        }
        return vert;  
    }
    
    /* Adds and returns a new edge between vertices u and v, with some 
   * specific comparable type. If vertices u, v don't exist in the graph they  
   * are inserted  
   * @param vorigInf Information of vertex source
   * @param vdestInf Information of vertex destination
   * @param eInf edge information
   * @param eWeight edge weight
   * @return new edge, or null if an edge already exists between the two verts.
   */
    @Override
    public Edge<V,E> insertEdge(V vOrig, V vDest, E eInf, double eWeight){
        
        Vertex<V,E> vorig = getVertex(vOrig) ;
        if (vorig == null)
            vorig = insertVertex(vOrig);
        
        Vertex<V,E> vdest = getVertex(vDest) ;
        if (vdest == null)
             vdest = insertVertex(vDest);
        
        //if (getEdge(vorig,vdest) == null) {               
            Edge<V,E> newedge = new Edge<>(eInf,eWeight,vorig,vdest);
            ArrayList<Edge<V,E>> lst;
            if (getEdge(vorig,vdest) == null) {
                lst= new ArrayList<>();
            } else{
                lst = vorig.getOutgoing().get(vdest);
            }
            lst.add(newedge);
            vorig.getOutgoing().put(vdest,lst);
            numEdge++;
            listEdge.add(newedge);
             
            //if graph is not direct insert other edge in the opposite direction 
//            if (!isDirected)
//                if (getEdge(vdest,vorig) == null) { 
//                    Edge<V,E> otheredge = new Edge<>(eInf,eWeight,vdest,vorig);
//                    vdest.getOutgoing().put(vorig,otheredge);
//                    numEdge++;
//                }       
            
            return newedge ;
//        }
//           return null ;
    }
    
    /* Removes the edge between with source vertex vorigInf and destination vertex
  * vdestInf
  * @param vorigInf Information of vertex source
  * @param vdestInf Information of vertex destination 
  */  
    @Override
    public void removeEdge(Edge<V,E> edge) {
        
        Vertex<V,E>[] endpoints = endVertices(edge);
        
        Vertex<V,E> vorig = endpoints[0] ;
        Vertex<V,E> vdest = endpoints[1] ;
        
        if (vorig != null && vdest != null) 
            if (edge.equals(getEdge(vorig,vdest))){
               vorig.getOutgoing().remove(vdest);
               listEdge.remove(edge);
               numEdge--;
            } 
    }
           
    public Vertex<V,E> getVertex(V vInf){
        
        for (Vertex<V,E> vert : this.listVert)  
            if (vInf.equals(vert.getElement()))
                return vert;
    
        return null;
    }
    
    public Vertex<V,E> getVertex(int vKey){
   
        if (vKey < listVert.size())
            return listVert.get(vKey);
        
        return null;
    }
    
    public Vertex<V,E> getVOrig(){
   
        for (Vertex<V,E> vert : this.listVert){  
            if(this.inDegree(vert)==0)
                return vert;
        } 
        return null;
    }
    
    public Vertex<V,E> getVLast(){
   
        for (Vertex<V,E> vert : this.listVert)  
            if(this.outDegree(vert)==0)
                return vert;
        
        return null;
    }
    
    //Returns a clone of the graph 
    @Override
    public Graph<V,E> clone() {
        
        Graph<V,E> newObject = new Graph<>(this.isDirected);

        newObject.numVert = this.numVert;
        
        newObject.listVert = new ArrayList<>();        
        for (Vertex<V,E> v : this.listVert)   
            newObject.listVert.add(new Vertex<V,E>(v.getKey(), v.getElement()));
        
        for (Vertex<V,E> v1 : this.listVert) 
            for (Edge<V,E> e : this.outgoingEdges(v1))  
                if (e != null){
                   Vertex<V,E> v2=this.opposite(v1,e);
                   newObject.insertEdge(v1.getElement(), v2.getElement(),
                                        e.getElement(), e.getWeight());
                }
        return newObject;
    }

    /* equals implementation
     * @param the other graph to test for equality
     * @return true if both objects represent the same graph
     */
    @Override
    public boolean equals(Object oth) {

        if (oth == null) return false;

        if (this == oth) return true;

        if (!(oth instanceof Graph<?,?>))
            return false;

        Graph<?, ?> other = (Graph<?, ?>) oth;

        if (numVert != other.numVert || numEdge != other.numEdge) 
            return false;

        //graph must have same vertices
        boolean eqvertex;
        for (Vertex<V,E> v1 : this.vertices()){
            eqvertex=false;
            for (Vertex<?,?> v2 : other.vertices())
                if (v1.equals(v2))
                    eqvertex=true;
            
            if (!eqvertex)
              return false;
        }
        
         //graph must have same edges
        boolean eqedge;
        for (Edge<V,E> e1 : this.edges()){
            eqedge=false;
            for (Edge<?,?> e2 : other.edges())
                if (e1.equals(e2))
                    eqedge=true;
            
            if (!eqedge)
              return false;
        }

        return true;
    }

    
//string representation
    @Override
    public String toString() {
        String s="" ;
        if (numVert == 0) {
            s = "\nGraph not defined!!";
        } 
        else {
            s = "Graph: "+ numVert + " vertices, " + numEdge + " edges\n";
            for (Vertex<V,E> vert : listVert) {
                s += vert + "\n" ;
                if (!vert.getOutgoing().isEmpty()){
                   for (Map.Entry<Vertex<V,E>, ArrayList<Edge<V,E>>> entry : vert.getOutgoing().entrySet()){
                       s += entry.getValue() + "\n" ;
                   }
                }
                else
                   s += "\n" ; 
            }
        }
        return s ;
    }
    
    /* Removes a vertex and all its incident edges from the graph 
  * @param vInf Information of vertex source
  */
    @Override
    public void removeVertex(V vInf) {
        
        
        Vertex<V,E> v = this.getVertex(vInf);
        
        for(Edge<V,E> eo : this.outgoingEdges(v))
        {
            removeEdge(eo);
        }
        
        for(Edge<V,E> ei : this.incomingEdges(v))
        {
            removeEdge(ei);
        }
        
        for(Vertex <V,E> vv : this.vertices())
        {
            if(vv.getKey()>v.getKey())
            {
                vv.setKey(vv.getKey()-1);
            }
        }
        listVert.remove(v);
        numVert--;
    }
    
   
}
