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

    private int m_PK;
    private String m_name;
    private String m_description;
    private ArrayList<Junction> m_nodeList;
    private ArrayList<Section> m_sectionList;

    public RoadNetwork(){
    }
    
    public RoadNetwork(String name, String description, ArrayList<Junction> junctions, ArrayList<Section> sectionList) {
        m_name = name;
        m_description = description;
        m_nodeList = junctions;
        m_sectionList = sectionList;
    }

    public ArrayList<Junction> getNodeList() {
        return m_nodeList;
    }

    public ArrayList<Section> getSectionList() {
        return m_sectionList;
    }

    public void setNodeList(ArrayList<Junction> m_nodeList) {
        this.m_nodeList = m_nodeList;
    }

    public void setSectionList(ArrayList<Section> m_sectionList) {
        this.m_sectionList = m_sectionList;
    }

    public String getName() {
        return m_name;
    }

    public String getDescription() {
        return m_description;
    }

    public void setPK(int pk) {
        m_PK = pk;
    }

    public int getPK() {
        return m_PK;
    }

    public void setName(String name) {
        m_name = name;
    }

    public void setDescription(String description) {
        m_description = description;
    }

    public boolean hasPK() {
        return m_PK != 0;
    }

    public Junction getNodeByPK(int pk) {
        for (Junction it : m_nodeList) {
            if (it.getPK() == pk) {
                return it;
            }
        }
        return null;
    }

}
