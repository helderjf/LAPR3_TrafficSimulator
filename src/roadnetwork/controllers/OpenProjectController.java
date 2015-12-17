/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadnetwork.controllers;

import java.util.ArrayList;
import roadnetwork.domain.DataAccessLayer;
import roadnetwork.domain.Manager;
import roadnetwork.domain.Project;
import roadnetwork.domain.ProjectFactory;

/**
 *
 * @author André Pedrosa, Hélder Faria, José Miranda, Rubén Rosário
 */
public class OpenProjectController {
    
    Manager m_manager;
    DataAccessLayer m_dataAccessLayer;
    ProjectFactory m_projectFactory;
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
        m_projectFactory = m_manager.getProjectFactory();
        
        m_project=m_projectFactory.getProjectByID(pid);
        
        
        return m_manager.setCurrentProject(m_project);
    }
    
}
