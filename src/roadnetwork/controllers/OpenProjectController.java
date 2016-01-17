/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadnetwork.controllers;

import java.util.ArrayList;
import roadnetwork.domain.Manager;
import roadnetwork.domain.Project;
import data.access.layer.ProjectReader;
import java.sql.SQLRecoverableException;

/**
 *
 * @author André Pedrosa, Hélder Faria, José Miranda, Rubén Rosário
 */
public class OpenProjectController {

    Manager m_manager;
    ProjectReader m_projectReader;
    ArrayList<String> m_projectNameList;
    Project m_project;

    /**
     *
     * @param manager manager of project
     */
    public OpenProjectController(Manager manager) {
        m_manager = manager;
    }

    /**
     *
     * @return ProjectsIDList
     */
    public ArrayList<String> getExistentProjects() throws SQLRecoverableException {
        m_projectReader = m_manager.getProjectReader();
        return m_projectReader.getOrderedProjectList();
    }

    /**
     *
     * @param projectName Project Name
     * @return make ProjectActive
     */
    public boolean selectProject(String projectName) {

        m_project = m_projectReader.getProjectByName(projectName);
        if (m_project == null) {
            return false;
        }
        return m_manager.setCurrentProject(m_project);
    }

}
