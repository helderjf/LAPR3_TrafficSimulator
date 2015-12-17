/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadnetwork.controllers;

import java.util.ArrayList;
import roadnetwork.domain.DataAccessLayer;
import roadnetwork.domain.Manager;

/**
 *
 * @author André Pedrosa, Hélder Faria, José Miranda, Rubén Rosário
 */
public class OpenProjectController {
    
    Manager m_manager;
    DataAccessLayer m_dataAccessLayer;
    
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
        return m_dataAccessLayer.makeProjectActive();
    }
    
}
