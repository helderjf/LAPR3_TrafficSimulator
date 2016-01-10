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
    
    
    
    
    
    
    
    
}
