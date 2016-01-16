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

    ArrayList<String> m_road;
    ArrayList<String> m_inNode;
    ArrayList<String> m_outNode;
    ArrayList<Integer> m_segIndex;
    ArrayList<String> m_direction;
    ArrayList<Double> m_avgConsumption;
    ArrayList<Double> m_avgTimeSpent;
    
    @Override
    public String getGlobalResultsHTMLCode() {
        String results
                = "<table border = 1>"
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
        for (int i = 0; i < m_road.size(); i++) {
            results
                    += "<td>"
                    + m_road.get(i)
                    + "</td>"
                    + "<td>"
                    + m_inNode.get(i)
                    + "</td>"
                    + "<td>"
                    + m_outNode.get(i)
                    + "</td>"
                    + "<td>"
                    + m_segIndex.get(i)
                    + "</td>"
                    + "<td>"
                    + m_direction.get(i)
                    + "</td>"
                    + "<td>"
                    + m_avgConsumption.get(i)
                    + "</td>"
                    + "<td>"
                    + m_avgTimeSpent.get(i)
                    + "</td>";
        }
        results += "</table>";

        return results;    
    }

    @Override
    public String toString() {
        return "Specific Traffic Pattern Results";
    }
    
    
}