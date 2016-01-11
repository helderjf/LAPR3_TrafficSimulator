/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadnetwork.domain;

/**
 *
 * @author antonio
 */
public class TrafficPattern {
    
    private int m_pk;
    private Junction beginNode;
    private Junction endNode;
    private Vehicle vehicle;
    private double arrivalRate; //in vehicles/seconds

    
    
    public Junction getBeginNode() {
        return beginNode;
    }

    public Junction getEndNode() {
        return endNode;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public double getArrivalRate() {
        return arrivalRate;
    }

    public int getPK() {
        return m_pk;
    }

    public void setPK(int pk) {
        m_pk = pk;
    }
    
    public boolean hasPK(){
        return m_pk!=0;
    }
    
    
    
    
    
    
    
}
