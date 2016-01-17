/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadnetwork.controllers;

import data.access.layer.DataAccessObject;
import data.access.layer.ProjectWriter;
import java.sql.SQLRecoverableException;
import roadnetwork.domain.Manager;
import roadnetwork.domain.Project;

/**
 *
 * @author André Pedrosa, Hélder Faria, José Miranda, Rubén Rosário
 */
public class SaveProjectController {

    Manager m_manager;
    DataAccessObject m_dataAccessLayer;
    ProjectWriter m_writer;
    Project m_project;

    public SaveProjectController(Manager manager) {
        m_manager = manager;
    }

    public boolean checkProjectSaved() {
        m_project = m_manager.getCurrentProject();
        return m_project.isSaved();
    }

    public boolean saveProject() throws SQLRecoverableException {
        m_writer = m_manager.getProjectWriter();

        boolean aux;

        if (m_project.hasPK()) {
           aux = m_writer.updateProject(m_project);
        } else {
            aux = m_writer.saveNewProject(m_project);
        }

        if (aux) {
            return m_project.projectSaved();
        } else {
            return false;
        }

    }

}
