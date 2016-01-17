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
    private Junction m_beginNode;
    private Junction m_endNode;
    private Vehicle m_vehicle;
    private double m_arrivalRate; //in vehicles/seconds

    
    public TrafficPattern(){
        
    }
    
    public TrafficPattern(int m_pk, Junction beginNode, Junction endNode, Vehicle vehicle, double arrivalRate) {
        this.m_pk = m_pk;
        this.m_beginNode = beginNode;
        this.m_endNode = endNode;
        this.m_vehicle = vehicle;
        this.m_arrivalRate = arrivalRate;
    }
    
    public static TrafficPattern trafficPatternPseudoCopy(TrafficPattern otherTrafficPatern){
        TrafficPattern pseudoCopy = new TrafficPattern();
        pseudoCopy.m_beginNode=otherTrafficPatern.m_beginNode;
        pseudoCopy.m_endNode=otherTrafficPatern.m_endNode;
        pseudoCopy.m_vehicle=otherTrafficPatern.m_vehicle;
        pseudoCopy.m_arrivalRate=otherTrafficPatern.m_arrivalRate;
        return pseudoCopy;
    }

    
    public Junction getBeginNode() {
        return m_beginNode;
    }

    public Junction getEndNode() {
        return m_endNode;
    }

    public Vehicle getVehicle() {
        return m_vehicle;
    }

    public double getArrivalRate() {
        return m_arrivalRate;
    }

    public void setBeginNode(Junction beginNode) {
        this.m_beginNode = beginNode;
    }

    public void setEndNode(Junction endNode) {
        this.m_endNode = endNode;
    }

    public void setVehicle(Vehicle vehicle) {
        this.m_vehicle = vehicle;
    }

    public void setArrivalRate(double arrivalRate) {
        this.m_arrivalRate = arrivalRate;
    }
    
    /**
     * Sets m_arrivalRate from a string with the format: X Y/TimeUnit
     * @param arrivalRate 
     */
    public void setArrivalRate(String arrivalRate) {
        //Se for -1 é porque houve um erro de parsing
        if(arrivalRateInVehiclesPerSeconds(arrivalRate)!=-1)
            this.m_arrivalRate = arrivalRateInVehiclesPerSeconds(arrivalRate);
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
        return m_beginNode != null && m_endNode != null && m_vehicle != null && m_arrivalRate > 0;
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

    @Override
    public String toString() {
        return "Traffic Pattern - Node In: " + m_beginNode + " - Node Out: " + m_endNode
                + " - Vehicle: " +m_vehicle.getName()+ " - Arrival Rate: " + String.format("%.1f",m_arrivalRate);
                
    }

    
    


}
