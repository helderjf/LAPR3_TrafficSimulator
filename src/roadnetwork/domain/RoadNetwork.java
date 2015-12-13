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
public class RoadNetwork {
    
    private ArrayList<Node> m_nodeList;
    private ArrayList<Section> m_sectionList;

    public ArrayList<Node> getNodeList() {
        return m_nodeList;
    }
    
}
