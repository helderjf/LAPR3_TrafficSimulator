/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadnetwork.controllers;

import roadnetwork.domain.DataAccessLayer;
import roadnetwork.domain.Manager;
import roadnetwork.domain.Project;
import roadnetwork.domain.ProjectFactory;

/**
 *
 * @author André Pedrosa, Hélder Faria, José Miranda, Rubén Rosário
 */
public class NewProjectController {

    Manager m_manager;
    //ProjectFactory m_projectFactory;
    Project m_project;

    /**
     *
     * @param manager manager of project
     */
    public NewProjectController(Manager manager) {
        m_manager = manager;
    }
    
//    public Project newProject(){
//        
//        return m_manager.newProject
//    }
    
    

}
