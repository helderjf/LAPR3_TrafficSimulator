/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadnetwork.domain;

import java.util.ArrayList;

/**
 *
 * @author André Pedrosa, Hélder Faria, José Miranda, Rubén Rosário
 */
public class ResultStaticAnalysis implements Result {

    private Junction m_originNode;
    private Junction m_destinyNode;
    private ArrayList<Section> m_path;
    private double m_length;
    private ArrayList<Double> m_sectionTravelTime;
    private ArrayList<Junction> m_pathNodes;
    private Vehicle m_vehicle;
    private ArrayList<Double> m_sectionEnergyConsumption;
    private ArrayList<Double> m_sectionTollCosts;

    public ResultStaticAnalysis(Junction origin, Junction destiny) {
        m_originNode = origin;
        m_destinyNode = destiny;
    }

    /**
     * @return the m_path
     */
    public ArrayList<Section> getPath() {
        return m_path;
    }

    /**
     * @return the m_length
     */
    public double getLength() {
        return m_length;
    }

    /**
     * @return the m_sectionTravelTime
     */
    public ArrayList<Double> getSectionWeight() {
        return m_sectionTravelTime;
    }
    
    public Junction getOriginNode() {
        return m_originNode;
    }

    public Junction getDestinyNode() {
        return m_destinyNode;
    }
    
    public ArrayList<Junction> getPathNodes(){
        return m_pathNodes;
    }
    
    public Vehicle getVehicle(){
        return m_vehicle;
    }
    
    public ArrayList<Double> getEnergyConsumption(){
        return m_sectionEnergyConsumption;
    }
    
    public ArrayList<Double> getTollCosts(){
        return m_sectionTollCosts;
    }

    /**
     * @param m_path the m_path to set
     */
    public void setPath(ArrayList<Section> m_path) {
        this.m_path = m_path;
    }

    /**
     * @param m_length the m_length to set
     */
    public void setLength(double m_length) {
        this.m_length = m_length;
    }
    
    public void setPathNodes(ArrayList<Junction> pathNodes){
        m_pathNodes=pathNodes;
    }
    
    public void setVehicle(Vehicle vehicle){
        m_vehicle=vehicle;
    }
    
    public void setEnergyConsumption(ArrayList<Double> energyConsumption){
        m_sectionEnergyConsumption=energyConsumption;
    }
    
    public void setTollCosts(ArrayList<Double> sectionTollCosts){
        m_sectionTollCosts=sectionTollCosts;
    }

    /**
     * @param sectionTravelTime the m_sectionTravelTime to set
     */
    public void setSectionTravelTime(ArrayList<Double> sectionTravelTime) {
        this.m_sectionTravelTime = sectionTravelTime;
    }


    @Override
    public String toString() {
        return "Fastest path between " + m_originNode.toString() + " and " + m_destinyNode.toString() + " is:\n"
                + "COMPLETAR ESTE MÉTODO!!!";
    }

    @Override
    public String printResults() {
        StringBuilder results = new StringBuilder();
        results.append("#Vehicle: ");
        results.append(m_vehicle.getName());
        results.append("#\n");
        for(Section it : m_path){
            int i = m_path.indexOf(it);
            
            results.append(m_pathNodes.get(i).toString());
            results.append(" ---> ");
            results.append(m_pathNodes.get(i+1).toString());
            results.append("  @  ");
            results.append(it.getRoadName());
            results.append(" Travel time: ");
            
            results.append(m_sectionTravelTime.get(i)/60);
            results.append(" minutes");
            
            results.append(" " + "(Toll: "+m_sectionTollCosts.get(i).toString() + ")");
            results.append("\n");
        }
        return results.toString();
    }

}
