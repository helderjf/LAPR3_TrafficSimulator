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
    
    private int m_pk;
    private String junctionId;

    
    /**
     * 
     * @param pk pk 
     * @param name name
     */
    public Junction(int pk, String name) {
        this.m_pk = pk;
        this.junctionId = name;
    }
    
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
     * @param junctionId the junctionId to set
     */
    public void setJunctionId(String junctionId) {
        this.junctionId = junctionId;
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
     * @return m_pk

     */
    public int getPK() {
        return m_pk;
    }

    /**
     * 
     * @param pk pk
     */
    public void setPK(int pk) {
        m_pk = pk;
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
