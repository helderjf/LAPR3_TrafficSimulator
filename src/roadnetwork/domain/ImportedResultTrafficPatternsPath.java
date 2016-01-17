/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadnetwork.domain;

import java.util.ArrayList;

/**
 *
 * @author josemiranda
 */
public class ImportedResultTrafficPatternsPath implements ImportedResult{

    ArrayList<TrafficPattern> m_trafficPatternList;
    ArrayList<Section> m_sectionList;
    ArrayList<Segment> m_segmentList;
    ArrayList<SimDirection> m_directionList;
    ArrayList<Double> m_segAVGConsumption;
    
    
    @Override
    public String getGlobalResultsHTMLCode() {
    String results
               = "<table border = 1>"
                + "<tr>"
                    + "<th colspan=4> Traffic Pattern</th>"
                    +"<th colspan=6> Segment</th>"
                + "</tr>"
                + "<tr>"
                    + "<th>Origin Node</th>"
                    + "<th>Destiny Node</th>"
                    + "<th>Vehicle</th>"
                    + "<th>Arrival Rate (s)</th>"
                    + "<th>Road</th>"
                    + "<th>Node In</th>"
                    + "<th>Node Out</th>"
                    + "<th>Index</th>"
                    + "<th>Direction</th>"
                    + "<th>Average Consumption</th>"
                + "</tr>";
        for (int i = 0; i < m_trafficPatternList.size(); i++) {
            results
                    += "<tr>"
                    + "<td>"
                    + m_trafficPatternList.get(i).getBeginNode().getJunctionId()
                    + "</td>"
                    + "<td>"
                    + m_trafficPatternList.get(i).getEndNode().getJunctionId()
                    + "</td>"
                    + "<td>"
                    + m_trafficPatternList.get(i).getVehicle().getName()
                    + "</td>"
                    + "<td>"
                    + String.format("%.2f",m_trafficPatternList.get(i).getArrivalRate())
                    + "</td>"
                    + "<td>"
                    + m_sectionList.get(i).getRoadName()
                    + "</td>"
                    + "<td>"
                    + m_sectionList.get(i).getBeginningNode().getJunctionId()
                    + "</td>"
                    + "<td>"
                    + m_sectionList.get(i).getEndingNode().getJunctionId()
                    + "</td>"
                    + "<td>"
                    + m_segmentList.get(i).getIndex()
                    + "</td>"
                    + "<td>"
                    + m_directionList.get(i)
                    + "</td>"
                    + "<td>"
                    + String.format("%.1f",m_segAVGConsumption.get(i));
                    if (m_trafficPatternList.get(i).getVehicle() instanceof CombustionVehicle) {
                        results+=" g";
                    }else{
                        results+=" J";
                    }
            results
                    += "</td>"
                    + "</tr>";
        }
        results += "</table>";

        return results;    
    }

    @Override
    public String toString() {
        return "Traffic Patterns Results per segment";
    }

    public void setTrafficPatternList(ArrayList<TrafficPattern> trafficPatternList) {
        this.m_trafficPatternList = trafficPatternList;
    }

    public void setSectionList(ArrayList<Section> sectionList) {
        this.m_sectionList = sectionList;
    }

    public void setSegmentList(ArrayList<Segment> segmentList) {
        this.m_segmentList = segmentList;
    }

    public void setDirectionList(ArrayList<SimDirection> directionList) {
        this.m_directionList = directionList;
    }

    public void setSegAVGConsumption(ArrayList<Double> segAVGConsumption) {
        this.m_segAVGConsumption = segAVGConsumption;
    }
    
    
    
    
    
}
