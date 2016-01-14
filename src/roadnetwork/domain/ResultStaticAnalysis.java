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
    private ArrayList<PathParcel> m_path;
    private double m_length;
    private ArrayList<Junction> m_pathNodes;
    private Vehicle m_vehicle;
    private ArrayList<Double> m_sectionEnergyConsumption;

    /**
     *
     * @param origin origin
     * @param destiny destiny
     */
    public ResultStaticAnalysis(Junction origin, Junction destiny) {
        m_originNode = origin;
        m_destinyNode = destiny;
    }

    /**
     * @return the m_path
     */
    public ArrayList<PathParcel> getPath() {
        return m_path;
    }

    /**
     * @return the m_length
     */
    public double getLength() {
        return m_length;
    }

    /**
     *
     * @return OriginNode
     */
    public Junction getOriginNode() {
        return m_originNode;
    }

    /**
     *
     * @return DestinyNode
     */
    public Junction getDestinyNode() {
        return m_destinyNode;
    }
    
    /**
     *
     * @return PathNodes
     */
    public ArrayList<Junction> getPathNodes(){
        return m_pathNodes;
    }
    
    /**
     *
     * @return Vehicle
     */
    public Vehicle getVehicle(){
        return m_vehicle;
    }
    
    /**
     *
     * @return EnergyConsumption
     */
    public ArrayList<Double> getEnergyConsumption(){
        return m_sectionEnergyConsumption;
    }
    
    /**
     * @param path the m_path to set
     */
    public void setPath(ArrayList<PathParcel> path) {
        this.m_path = path;
    }

    /**
     * @param m_length the m_length to set
     */
    public void setLength(double m_length) {
        this.m_length = m_length;
    }
    
    /**
     *
     * @param pathNodes pathNodes
     */
    public void setPathNodes(ArrayList<Junction> pathNodes){
        m_pathNodes=pathNodes;
    }
    
    /**
     *
     * @param vehicle vehicle
     */
    public void setVehicle(Vehicle vehicle){
        m_vehicle=vehicle;
    }
    
    /**
     *
     * @param energyConsumption energyConsumption
     */
    public void setEnergyConsumption(ArrayList<Double> energyConsumption){
        m_sectionEnergyConsumption=energyConsumption;
    }
    
    /**
     * 
     * @return print of ResultStaticAnalysis
     */
    @Override
    public String toString() {
        return "Fastest path between " + m_originNode.toString() + " and " + m_destinyNode.toString() + " is:\n"
                + "COMPLETAR ESTE MÉTODO!!!";
    }

    /**
     *
     * @return ResultStaticAnalysis
     */
    @Override
    public String printResults() {
        StringBuilder results = new StringBuilder();
        results.append("#Vehicle: ");
        results.append(m_vehicle.getName());
        results.append("#\n");
        for(PathParcel it : m_path){
            int i = m_path.indexOf(it);
            
            results.append(m_pathNodes.get(i).toString());
            results.append(" ---> ");
            results.append(m_pathNodes.get(i+1).toString());
            results.append("  @  ");
            results.append(it.getSection().getRoadName());
            results.append(" Travel time: ");
            
            results.append(it.getTheoreticalTravelTime()/60);
            results.append(" minutes ");
            results.append("Energy Consumption");
            results.append(it.getTheoreticalEnergyConsumption());
            results.append("(Toll: ");
                   results.append(it.getTollCosts());
            results.append(")\n");
        }
        return results.toString();
    }

}
