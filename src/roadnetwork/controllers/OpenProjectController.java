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
 * @author josemiranda
 */
public class OpenProjectController {
    
    Manager m_manager;
    DataAccessLayer m_dataAccessLayer;
    
    public OpenProjectController(Manager manager){
        m_manager=manager;
    }
    
    public ArrayList<String> getProjectsIDList(){
        m_dataAccessLayer=m_manager.getdataAccessLayer();
        return m_dataAccessLayer.getProjectsIDList();
    }
    
    public boolean selectProject(String pid){
        return m_dataAccessLayer.makeProjectActive();
    }
    
}
