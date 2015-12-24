/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.access.layer;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import roadnetwork.domain.Project;
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

  
    public ProjectWriter(DataAccessObject dao){
        m_dao=dao;
    }
    
    
    public boolean saveNewProject(Project project) {
        m_project=project;
        m_projectName=m_project.getName();
        m_projectDescription=m_project.getDescription();
        m_projectState=m_project.getState().getClass().getSimpleName();
        try {        
            m_projectPK=m_dao.saveNewProject(m_projectName,m_projectDescription,m_projectState);
        } catch (SQLException ex) {
            Logger.getLogger(ProjectWriter.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
        m_project.setPK(m_projectPK);
        
        return true;
        
    }
    
}
