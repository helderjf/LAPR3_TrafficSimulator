
package graphutils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author DEI-ESINF
 * @param <V> vertice
 * @param <E> edge
 */

public class Vertex<V,E> {
    
    private int key ;       //Vertex key number
    private V  element ;   //Vertex information
    private Map<Vertex<V,E>, ArrayList<Edge<V,E>>> outVerts; //adjacent vertices
       
    public Vertex () { } 
    
    public Vertex (int k, V vInf) {
        key = k; element = vInf; outVerts = new HashMap<>(); } 
  
    public int getKey() { return key; }	 
    public void setKey(int k) { key = k; }	
    
    public V getElement() {
        return element; 
    }	 
    public void setElement(V vInf) { element = vInf; }		

    public Map<Vertex<V,E>, ArrayList<Edge<V,E>>> getOutgoing() { return outVerts ; } 
         
    /**
     * 
     * @param otherObj object
     * @return result
     */
    @Override
    public boolean equals(Object otherObj) {
        if (this == otherObj){
            return true;
        }
        if (otherObj == null || this.getClass() != otherObj.getClass()){
            return false;
        }
        Vertex<V,E> otherVertex = (Vertex<V,E>) otherObj;
        
        return (this.key == otherVertex.key && 
                this.element != null && otherVertex.element != null   && 
                this.element.equals(otherVertex.element));
    }
    
    /**
     * 
     * @return copy object
     */
    @Override
    public Vertex<V,E> clone() {
        
        Vertex<V,E> newVertex = new Vertex<>();
        
        newVertex.key = key;
        newVertex.element = element;
        
        Map<Vertex<V,E>, ArrayList<Edge<V,E>>> newMap = new HashMap<>();
        
        Iterator<Map.Entry<Vertex<V,E>,ArrayList<Edge<V,E>>>> itmap;
        itmap = this.outVerts.entrySet().iterator();
        while (itmap.hasNext()) {
            Map.Entry<Vertex<V,E>,ArrayList<Edge<V,E>>> entry = itmap.next();
            newMap.put(entry.getKey(),entry.getValue());
        }    
        newVertex.outVerts=newMap;
        
        return newVertex;
    }
    
    /**
     * 
     * @return string
     */
    @Override
    public String toString() {
       String result = element + " (" + key + "): ";
       return  result; 
    }   

}





