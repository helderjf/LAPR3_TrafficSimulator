/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.access.layer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import roadnetwork.domain.Junction;
import roadnetwork.domain.Project;
import roadnetwork.domain.RoadNetwork;
import roadnetwork.domain.Section;
import roadnetwork.state.ProjectState;

/**
 *
 * @author André Pedrosa, Hélder Faria, José Miranda, Rubén Rosário
 */
public class ProjectWriter {

    private DataAccessObject m_dao;
    private Project m_project;
    private int m_projectPK;
    private String m_projectState;
    private String m_projectName;
    private String m_projectDescription;

    private RoadNetwork m_roadNetwork;
    private String m_roadNetworkName;
    private String m_roadNetworkDescription;
    private int m_roadNetworkPK;

    private ArrayList<Junction> m_nodeList;
    private ArrayList<Section> m_sectionList;

    public ProjectWriter(DataAccessObject dao) {
        m_dao = dao;
    }

    public boolean saveNewProject(Project project) {
        try {
            m_project = project;
            m_projectName = m_project.getName();
            m_projectDescription = m_project.getDescription();
            m_projectState = m_project.getState().getClass().getSimpleName();
            m_projectPK = m_dao.saveNewProject(m_projectName, m_projectDescription, m_projectState);
            m_project.setPK(m_projectPK);

            
            m_roadNetwork = m_project.getRoadNetwork();
            m_roadNetworkName = m_roadNetwork.getName();
            m_roadNetworkDescription = m_roadNetwork.getDescription();
            m_roadNetworkPK = m_dao.saveNewRoadNetwork(m_roadNetworkName, m_roadNetworkDescription);
            m_roadNetwork.setPK(m_roadNetworkPK);

            m_nodeList = m_roadNetwork.getNodeList();
            for(Junction it: m_nodeList){
                
                
            }

            

        } catch (SQLException ex) {
            Logger.getLogger(ProjectWriter.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

}
