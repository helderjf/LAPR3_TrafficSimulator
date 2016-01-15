/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadnetwork.controllers;

import roadnetwork.domain.Manager;
import roadnetwork.domain.Project;
import roadnetwork.domain.Simulation;

/**
 *
 * @author André Pedrosa, Hélder Faria, José Miranda, Rubén Rosário
 */
public class EditSimulationPropertiesController {

    private Manager m_manager;
    private Project m_project;
    private Simulation m_simulation;

    public EditSimulationPropertiesController(Manager manager) {
        m_manager = manager;
    }

    public int editSimulationProperties() {
        if (m_manager.getCurrentProject() == null) {
            return -1;
        }
        m_project = m_manager.getCurrentProject();
        if (m_project.getCurrentSimulation() == null) {
            return -2;
        }
        m_simulation = m_project.getCurrentSimulation();
        if (!m_simulation.canEditProperties()) {
            return -3;
        }
        return 1;

    }

    public String getOldName() {
        return m_simulation.getName();
    }

    public String getOldDescription() {
        return m_simulation.getDescription();
    }

    public boolean setNewProperties(String newName, String newDescription) {
        int projPK = m_project.getPK();
        if (!m_simulation.getName().equals(newName)
                && (m_manager.getProjectReader().simulationExists(projPK, newName))) {
            return false;
        }

        m_simulation.setName(newName);
        m_simulation.setDescription(newDescription);
        m_simulation.propertiesChanged();
        m_project.simulationPropertiesChanged();
        return true;

    }

}
