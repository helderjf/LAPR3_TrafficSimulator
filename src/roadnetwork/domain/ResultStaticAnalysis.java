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
    private BestPathAlgorithm m_bestPathAlgorithm;
    private double m_length;
    private Vehicle m_vehicle;

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
     * @return Vehicle
     */
    public Vehicle getVehicle(){
        return m_vehicle;
    }
    
    /**
     *
     * @return BestPathAlgorithm
     */
    public BestPathAlgorithm getBestPathAlgorithm(){
        return m_bestPathAlgorithm;
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
     * @param vehicle vehicle
     */
    public void setVehicle(Vehicle vehicle){
        m_vehicle=vehicle;
    }
    
    public void setBestPathAlgorithm(BestPathAlgorithm bpa){
        m_bestPathAlgorithm=bpa;
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
            if (it.getDirection().equals(SimDirection.direct)) {
                results.append(it.getSection().getBeginningNode().getJunctionId());
            } else{
                results.append(it.getSection().getEndingNode().getJunctionId());
            }
            results.append(" ---> ");
            if (it.getDirection().equals(SimDirection.direct)) {
                results.append(it.getSection().getEndingNode().getJunctionId());
            } else{
                results.append(it.getSection().getBeginningNode().getJunctionId());
            }
            results.append("  @  ");
            results.append(it.getSection().getRoadName());
            results.append(" | Travel time: ");
            
            results.append(String.format("%.0f",it.getTheoreticalTravelTime()));
            results.append("s ");
            results.append("| Energy Consumption: ");
            results.append(String.format("%.1f",it.getTheoreticalEnergyConsumption()));
            if (m_vehicle instanceof CombustionVehicle) {
                results.append("g");
            } else{
                results.append("J");
            }
            results.append(" | Toll: ");
            results.append(String.format("%.1f",it.getTollCosts()));
            results.append(" |\n");
        }
        return results.toString();
    }
    
    @Override
    public String getResultsHTMLCode(){
        String results=
            "<b>-----Static Analysis-----</b>" +
            "<p>Best Path Algorithm: "+ m_bestPathAlgorithm+"</p>"+
            "<p>Vehicle: "+m_vehicle.getName()+"</p>"+
            "<p>Start Node: " +m_originNode.getJunctionId()+"</p>"+
            "<p>End Node: "+m_destinyNode.getJunctionId()+"</p>"+
            "<table border = 1>" +
                "<tr>"+
                    "<th>Origin Node</th>"+
                    "<th>Destiny Node</th>"+
                    "<th>Road</th>"+
                    "<th>Travel Time (s)</th>";
        if (m_vehicle instanceof CombustionVehicle) {
            results +="<th>Energy Consumption (g)</th>";
        } else{
            results+="<th>Energy Consumption (J)</th>";
        }
 
        results+=
                    "<th>Toll Costs</th>"+
                "</tr>";
                
        for (PathParcel pp : m_path) {
            results+=
                "<tr>";
                    if (pp.getDirection().equals(SimDirection.direct)) {
                        results+="<td>"+
                                pp.getSection().getBeginningNode().getJunctionId()+
                                "</td>";
                    } else{
                        results+=
                                "<td>"+
                                pp.getSection().getEndingNode().getJunctionId()+
                                "</td>";
                    }
            
                    if (pp.getDirection().equals(SimDirection.direct)) {
                        results+="<td>"+
                                pp.getSection().getEndingNode().getJunctionId()+
                                "</td>";
                    } else{
                        results+=
                                "<td>"+
                                pp.getSection().getBeginningNode().getJunctionId()+
                                "</td>";
                    }
                results+=
                    "<td>"+pp.getSection().getRoadName()+"</td>"+
                    "<td>"+String.format("%.0f",pp.getTheoreticalTravelTime())+"</td>"+
                    "<td>"+String.format("%.1f",pp.getTheoreticalEnergyConsumption())+"</td>"+
                    "<td>"+String.format("%.1f",pp.getTollCosts())+"</td>"+
                "</tr>";
        }
        results+="</table>";
        
        return results;
    }

}
