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
public class CreateSimulationController {

    private Manager m_manager;
    private Project m_project;
    private Simulation m_simulation;

    public CreateSimulationController(Manager manager) {
        m_manager = manager;
    }

    public boolean projectActive() {
        m_project = m_manager.getCurrentProject();
        if (m_project != null) {
            return true;
        }
        return false;
    }

    public boolean newSimulation() {
        return m_project.canSimulate();
    }

    public boolean simulationExists(String simulationName) {
        if (m_project.simulationExists(simulationName)) {
            return true;
        }
        if (m_project.hasPK()) {
            return m_manager.getProjectReader().simulationExists(m_project.getPK(), simulationName);
        }
        return false;
    }
    
    public void setSimulation(String simulationName, String description){
        m_simulation=m_project.newSimulation(simulationName, description);
    }
    

}
