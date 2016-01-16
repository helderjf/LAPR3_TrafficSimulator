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
    ArrayList<String> m_nodeOrig;
    ArrayList<String> m_nodeDest;
    ArrayList<String> m_vehicle;
    ArrayList<Double> m_arrivalRate;
    ArrayList<Double> m_avgConsumpion;

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
    
}
