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

    ArrayList<String> m_nodeOrig;
    ArrayList<String> m_nodeDest;
    ArrayList<String> m_vehicle;
    ArrayList<Double> m_arrivalRate;
    
    ArrayList<String> m_segRoad;
    ArrayList<String> m_segInNode;
    ArrayList<String> m_segOutNode;
    ArrayList<Integer> m_segIndex;
    ArrayList<String> m_segDirection;
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
        for (int i = 0; i < m_nodeOrig.size(); i++) {
            results
                    += "<td>"
                    + m_nodeOrig.get(i)
                    + "</td>"
                    + "<td>"
                    + m_nodeDest.get(i)
                    + "</td>"
                    + "<td>"
                    + m_vehicle.get(i)
                    + "</td>"
                    + "<td>"
                    + m_arrivalRate.get(i)
                    + "</td>"
                    + "<td>"
                    + m_segRoad.get(i)
                    + "</td>"
                    + "<td>"
                    + m_segInNode.get(i)
                    + "</td>"
                    + "<td>"
                    + m_segOutNode.get(i)
                    + "</td>"
                    + "<td>"
                    + m_segIndex.get(i)
                    + "</td>"
                    + "<td>"
                    + m_segDirection.get(i)
                    + "</td>"
                    + "<td>"
                    + m_segAVGConsumption.get(i)
                    + "</td>";
        }
        results += "</table>";

        return results;    
    }

    @Override
    public String toString() {
        return "Traffic Patterns Results per segment";
    }
    
    
}
