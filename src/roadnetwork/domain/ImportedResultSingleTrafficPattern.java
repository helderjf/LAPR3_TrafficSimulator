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
public class ImportedResultSingleTrafficPattern implements ImportedResult{

    TrafficPattern m_trafficPattern;
    ArrayList<Section> m_sectionList;
    ArrayList<Segment> m_segmentList;
    ArrayList<SimDirection> m_directionList;
    ArrayList<Double> m_avgConsumption;
    ArrayList<Double> m_avgTimeSpent;
    
    @Override
    public String getGlobalResultsHTMLCode() {
        String results
                ="<p><b> Traffic Pattern:<b></p>"
                + "<p> "
                + "Node Origin: "
                    +m_trafficPattern.getBeginNode().getJunctionId()
                +"</p>"
                + "<p> "
                + "Node Destiny: "
                    +m_trafficPattern.getEndNode().getJunctionId()
                +"</p>"
                + "<p> "
                + "Vehicle: "
                    +m_trafficPattern.getVehicle().getName()
                +"</p>"
                + "<p> "
                + "Arrival Rate: "
                    +m_trafficPattern.getArrivalRate()
                +"</p>"
                + "<table border = 1>"
                + "<tr>"
                    + "<th colspan=7> Segment</th>"
                + "</tr>"
                + "<tr>"
                    + "<th>Road</th>"
                    + "<th>Node In</th>"
                    + "<th>Node Out</th>"
                    + "<th>Index</th>"
                    + "<th>Direction</th>"
                    + "<th>Average Consumption</th>"
                    + "<th>Time spent (s)</th>"
                + "</tr>";
        for (int i = 0; i < m_sectionList.size(); i++) {
            results
                    += "<tr>"
                    +"<td>"
                    + m_sectionList.get(i).getRoadName()
                    + "</td>"
                    + "<td>"
                    + m_sectionList.get(i).getBeginningNode()
                    + "</td>"
                    + "<td>"
                    + m_sectionList.get(i).getEndingNode()
                    + "</td>"
                    + "<td>"
                    + m_segmentList.get(i).getIndex()
                    + "</td>"
                    + "<td>"
                    + m_directionList.get(i)
                    + "</td>"
                    + "<td>"
                    + m_avgConsumption.get(i)
                    + "</td>"
                    + "<td>"
                    + m_avgTimeSpent.get(i)
                    + "</td>"
                    +"</tr>";
        }
        results += "</table>";

        return results;    
    }

    @Override
    public String toString() {
        return "Specific Traffic Pattern Results";
    }

    public void setTrafficPattern(TrafficPattern trafficPattern) {
        this.m_trafficPattern = trafficPattern;
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

    public void setAvgConsumption(ArrayList<Double> avgConsumption) {
        this.m_avgConsumption = avgConsumption;
    }

    public void setAvgTimeSpent(ArrayList<Double> avgTimeSpent) {
        this.m_avgTimeSpent = avgTimeSpent;
    }
    
    
    
    
    
}
