/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadnetwork.controllers;

import data.access.layer.ProjectReader;
import data.access.layer.ProjectWriter;
import roadnetwork.domain.Manager;
import roadnetwork.domain.Project;
import roadnetwork.domain.Simulation;

/**
 *
 * @author André Pedrosa, Hélder Faria, José Miranda, Rubén Rosário
 */
public class CopySimulationController {

    private Manager m_manager;
    private Project m_project;
    private Simulation m_activeSimulation;
    private ProjectReader m_projectReader;
    private ProjectWriter m_projectWriter;

    public CopySimulationController(Manager manager) {
        m_manager = manager;
    }

    public int canCopySimulation() {
        if (m_manager.getCurrentProject() == null) {
            return -1;
        }
        m_project = m_manager.getCurrentProject();
        if (!m_project.canCopySimulation()) {
            return -2;
        }
        if (m_project.getCurrentSimulation() == null) {
            return -3;
        }
        m_activeSimulation = m_project.getCurrentSimulation();
        if (!m_activeSimulation.canCopySimulation()) {
            return -4;
        }
        return 1;
    }

    public int simulationExists(String name) {
        m_projectReader = m_manager.getProjectReader();
        return m_projectReader.simulationExists(m_project.getPK(), name);
    }

    public boolean copySimulation(String name, String description) {
        m_projectWriter = m_manager.getProjectWriter();
        Simulation copySimulation = new Simulation(m_activeSimulation, m_manager.getStateFactory());
        copySimulation.setName(name);
        copySimulation.setDescription(description);
        return m_projectWriter.saveSimulationCopy(m_project,copySimulation);
    }

}
