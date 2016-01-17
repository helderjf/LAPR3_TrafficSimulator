/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadnetwork.controllers;

import data.access.layer.ProjectReader;
import data.access.layer.ProjectWriter;
import java.sql.SQLRecoverableException;
import roadnetwork.domain.Manager;
import roadnetwork.domain.Project;

/**
 *
 * @author Tiago
 */
public class CopyProjectController {
    private Manager m_manager;
    private Project m_activeProject;
    private ProjectReader m_projectReader;
    private ProjectWriter m_projectWriter;
    
    public CopyProjectController(Manager manager){
        m_manager=manager;
        
    }
    
        public boolean canCopyProject() {
        m_activeProject=m_manager.getCurrentProject();
        return m_activeProject.canCopyProject();
    }
        
        
    public boolean projectExists(String name) throws SQLRecoverableException{
        m_projectReader=m_manager.getProjectReader();
        return m_projectReader.projectNameExists(name);
        
    }

    public boolean copyProject(String name, String description) throws SQLRecoverableException {
        m_projectWriter=m_manager.getProjectWriter();
        Project copyProject = new Project(m_activeProject, m_manager.getStateFactory());
        copyProject.setName(name);
        copyProject.setDescription(description);
        return m_projectWriter.saveNewProject(copyProject);
    }


    
    
    
    
}
