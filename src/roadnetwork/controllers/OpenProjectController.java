/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadnetwork.controllers;

import java.util.ArrayList;
import data.access.layer.DataAccessObject;
import roadnetwork.domain.Manager;
import roadnetwork.domain.Project;
import data.access.layer.ProjectReader;

/**
 *
 * @author André Pedrosa, Hélder Faria, José Miranda, Rubén Rosário
 */
public class OpenProjectController {
    
    Manager m_manager;
    DataAccessObject m_dataAccessLayer;
    ProjectReader m_projectFactory;
    Project m_project;
    
    
    /**
     * 
     * @param manager manager of project
     */
    public OpenProjectController(Manager manager){
        m_manager=manager;
    }
    
    /**
     * 
     * @return ProjectsIDList
     */
    public ArrayList<String> getProjectsIDList(){
        m_dataAccessLayer=m_manager.getdataAccessLayer();
        return m_dataAccessLayer.getProjectsIDList();
    }
    
    /**
     * 
     * @param pid project id
     * @return make ProjectActive
     */
    public boolean selectProject(String pid){
        m_projectFactory = m_manager.getProjectReader();
        
        m_project=m_projectFactory.getProjectByID(pid);
        
        
        return m_manager.setCurrentProject(m_project);
    }
    
}
