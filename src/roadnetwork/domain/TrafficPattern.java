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
public class TrafficPattern implements TimeUnit {

    private int m_pk;
    private Junction beginNode;
    private Junction endNode;
    private Vehicle vehicle;
    private double arrivalRate; //in vehicles/seconds

    
    public TrafficPattern(){
        
    }
    
    public TrafficPattern(int m_pk, Junction beginNode, Junction endNode, Vehicle vehicle, double arrivalRate) {
        this.m_pk = m_pk;
        this.beginNode = beginNode;
        this.endNode = endNode;
        this.vehicle = vehicle;
        this.arrivalRate = arrivalRate;
    }

    
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

    public void setBeginNode(Junction beginNode) {
        this.beginNode = beginNode;
    }

    public void setEndNode(Junction endNode) {
        this.endNode = endNode;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public void setArrivalRate(double arrivalRate) {
        this.arrivalRate = arrivalRate;
    }
    
    /**
     * Sets arrivalRate from a string with the format: X Y/TimeUnit
     * @param arrivalRate 
     */
    public void setArrivalRate(String arrivalRate) {
        //Se for -1 é porque houve um erro de parsing
        if(arrivalRateInVehiclesPerSeconds(arrivalRate)!=-1)
            this.arrivalRate = arrivalRateInVehiclesPerSeconds(arrivalRate);
    }

    /**
     * converts given arrival rate (in any time unit) to vehicles per second
     *
     * @param arrivalString - is the read value from the data file
     * @return return - the number of vehicles to be injected into a node per
     * second -1 in case of and error
     */
    public double arrivalRateInVehiclesPerSeconds(String arrivalString) {
        String[] split = arrivalString.split(" ");
        if (split.length != 2) {
            return -1;
        }
        int vehicleValue = Integer.parseInt(split[0]);
        String[] split2 = split[1].split("/");
        if (split2.length != 2) {
            return -1;
        }
        String timeUnit = split2[1];
        int rate = Integer.parseInt(split2[0]);
        return vehicleValue / (double) (rate * TIME_UNIT_IN_SECONDS.get(timeUnit));
    }

    public boolean validate() {
        return beginNode != null && endNode != null && vehicle != null && arrivalRate > 0;
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
