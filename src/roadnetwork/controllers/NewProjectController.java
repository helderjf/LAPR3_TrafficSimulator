/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadnetwork.controllers;

import roadnetwork.domain.Manager;
import roadnetwork.domain.Project;
import data.access.layer.ProjectReader;

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
    
    public Project newProject(){
        m_project = m_manager.getProjectReader().newProject();
        return m_project;
    }
    
    public void setProjectName(String name){
        m_project.setName(name);
    }
    
    public boolean setProjectDescription(String description){
        m_project.setDescription(description);
        return (m_manager.setCurrentProject(m_project)
                && m_project.projectCreated());
        
        
    }

    public boolean nameExists(String name) {
        return m_manager.getProjectReader().projectNameExists(name);
    }

}
