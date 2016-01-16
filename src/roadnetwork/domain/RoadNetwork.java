/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadnetwork.domain;

import java.util.ArrayList;
import java.util.List;

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

    /**
     *
     */
    public RoadNetwork() {
        m_PK = 0;
        m_name = "";
        m_description = "";
        m_nodeList = new ArrayList<>();
        m_sectionList = new ArrayList<>();
    }

    /**
     *
     * @param name
     * @param description
     * @param junctions
     * @param sectionList
     */
    public RoadNetwork(String name, String description, ArrayList<Junction> junctions, ArrayList<Section> sectionList) {
        this();
        m_name = name;
        m_description = description;
        m_nodeList = junctions;
        m_sectionList = sectionList;
    }

    public RoadNetwork(RoadNetwork otherRoadNetwork) {
        m_name = otherRoadNetwork.m_name;
        m_description = otherRoadNetwork.m_description;
        m_nodeList = new ArrayList();
        for (Junction j : otherRoadNetwork.m_nodeList) {
            m_nodeList.add(new Junction(j));
        }
        m_sectionList = new ArrayList();
        for (Section s : otherRoadNetwork.m_sectionList) {
            m_sectionList.add(new Section(s));
        }

    }

    /**
     *
     * @return
     */
    public ArrayList<Junction> getNodeList() {
        return m_nodeList;
    }

    /**
     *
     * @return
     */
    public ArrayList<Section> getSectionList() {
        return m_sectionList;
    }

    /**
     *
     * @param m_nodeList
     */
    public void setNodeList(ArrayList<Junction> m_nodeList) {
        this.m_nodeList = m_nodeList;
    }

    /**
     *
     * @param m_sectionList
     */
    public void setSectionList(ArrayList<Section> m_sectionList) {
        this.m_sectionList = m_sectionList;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return m_name;
    }

    /**
     *
     * @return
     */
    public String getDescription() {
        return m_description;
    }

    /**
     *
     * @param pk
     */
    public void setPK(int pk) {
        m_PK = pk;
    }

    /**
     *
     * @return
     */
    public int getPK() {
        return m_PK;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        m_name = name;
    }

    /**
     *
     * @param description
     */
    public void setDescription(String description) {
        m_description = description;
    }

    /**
     *
     * @return
     */
    public boolean hasPK() {
        return m_PK != 0;
    }

    /**
     *
     * @param pk
     * @return
     */
    public Junction getNodeByPK(int pk) {
        for (Junction it : m_nodeList) {
            if (it.getPK() == pk) {
                return it;
            }
        }
        return null;
    }

    /**
     * Gets a node base on his ID
     *
     * @param id
     * @return the node with the ID
     */
    public Junction getNodeByID(String id) {
        for (Junction m_nodeList1 : m_nodeList) {
            if (m_nodeList1.getJunctionId() == null ? id == null : m_nodeList1.getJunctionId().equals(id)) {
                return m_nodeList1;
            }
        }
        return null;
    }

    public Section getSectionByPK(int pk) {
        for (Section s : m_sectionList) {
            if (s.getPK() == pk) {
                return s;
            }
        }
        return null;
    }

}
