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
public class Junction {
    
    private String junctionId;
    


    /**
     * 
     */
    public Junction()
    {
        this.junctionId = "";
    }
    
    /**
     * 
     * @param id junctionId of Node
     */
    public Junction(String id) {
        this.junctionId = id;
    }
    
    /**
     * 
     * @param n Node object
     */
    public Junction(Junction n)
    {
        n.junctionId = this.junctionId;
    }

    /**
     * 
     * @return junctionId of Junction
     */
    public String getJunctionId() {
        return junctionId;
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
        final Junction other = (Junction) obj;
        if (this.junctionId != other.junctionId) {
            return false;
        }
        return true;
    }

    /**
     * 
     * @return Junction string
     */
    @Override
    public String toString() {
        return "Node " + junctionId;
    }
  
}
