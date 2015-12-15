/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadnetwork.domain;

/**
 *
 * @author André Pedrosa, Hélder Faria, José Miranda, Rubén Rosário
 */
public class Node {
    
    private int id;


    /**
     * 
     */
    public Node()
    {
        this.id = 0;
    }
    
    /**
     * 
     * @param id id of Node
     */
    public Node(int id) {
        this.id = id;
    }
    
    /**
     * 
     * @param n Node object
     */
    public Node(Node n)
    {
        n.id = this.id;
    }

    /**
     * 
     * @return id of Node
     */
    public int getId() {
        return id;
    }

    /**
     * 
     * @return hash
     */
    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    /**
     * 
     * @param obj object
     * @return result
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Node other = (Node) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    /**
     * 
     * @return Node string
     */
    @Override
    public String toString() {
        return "Node{" + "id=" + id + '}';
    }
  
}
