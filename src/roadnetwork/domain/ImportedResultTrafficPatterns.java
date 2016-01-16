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
public class ImportedResultTrafficPatterns implements ImportedResult{
    ArrayList<TrafficPattern> m_trafficPatternList;
    ArrayList<Double> m_averageConsumptionList;


    @Override
    public String getGlobalResultsHTMLCode() {
        String results
                = "<table border = 1>"
                + "<tr>"
                    + "<th colspan=4> Traffic Pattern</th>"
                + "</tr>"
                + "<tr>"
                    + "<th>Origin Node</th>"
                    + "<th>Destiny Node</th>"
                    + "<th>Vehicle</th>"
                    + "<th>Arrival Rate (s)</th>"
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
                    + m_avgConsumpion.get(i)
                    + "</td>";
        }
        results += "</table>";

        return results;
    }

    @Override
    public String toString() {
        return "Traffic Patterns Results";
    }

    public void setTrafficPatternList(ArrayList<TrafficPattern> trafficPatternsList) {
        m_trafficPatternList=trafficPatternsList;
    }

    public void setAverageConsumptionList(ArrayList<Double> avgConsumptionsList) {
        m_averageConsumptionList=avgConsumptionsList;
    }
    
    
    
}
