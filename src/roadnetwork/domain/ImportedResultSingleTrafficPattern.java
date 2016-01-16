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
    
    @Override
    public String getGlobalResultsHTMLCode() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
