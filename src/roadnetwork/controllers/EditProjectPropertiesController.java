/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadnetwork.controllers;

import roadnetwork.domain.Manager;
import roadnetwork.domain.Project;

/**
 *
 * @author André Pedrosa, Hélder Faria, José Miranda, Rubén Rosário
 */
public class EditProjectPropertiesController {

    Manager m_manager;
    Project m_project;

    /**
     *
     * @param manager manager of project
     */
    public EditProjectPropertiesController(Manager manager) {
        m_manager = manager;
    }

    public boolean projectActive() {
        m_project = m_manager.getCurrentProject();
        if (m_project != null) {
            return true;
        }
        return false;
    }

    public boolean editProperties() {

        return m_project.canEditProperties();

    }

    public String getProjectName() {
        return m_project.getName();
    }

    public String getProjectDescription() {
        return m_project.getDescription();
    }

    public boolean setNewProperties(String name, String description) {
        m_project.setName(name);
        m_project.setDescription(description);

        return m_project.propertiesChanged();
    }

    public boolean nameExists(String name) {
        return m_manager.getProjectReader().projectNameExists(name);
    }

}
