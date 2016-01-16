/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadnetwork.controllers;

import IO.ImportSimulationXML;
import java.util.ArrayList;
import roadnetwork.domain.Manager;
import roadnetwork.domain.Project;
import roadnetwork.domain.Simulation;
import roadnetwork.domain.TrafficPattern;

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

    /**
     * Creates new simulation
     * @return 
     */
    public boolean newSimulation() {
        m_project = m_manager.getCurrentProject();
        if (m_project != null) {
            return m_project.canSimulate();
        }
        return false;
    }

    /**
     * Checks if there is already a simulation
     * @param simulationName
     * @return 0 if doesn't exist, 1 if it exist, -1 if there is a connection error
     */
    public int simulationExists(String simulationName) {
        if (m_project.simulationExists(simulationName)) {
            return 1;
        }
        if (m_project.hasPK()) {
            return m_manager.getProjectReader().simulationExists(m_project.getPK(), simulationName);
        }
        return 0;
    }

    /**
     * Sets the simulation to a new simulation
     * @param simulationName
     * @param description 
     */
    public void setSimulation(String simulationName, String description) {
        m_simulation = m_project.newSimulation(simulationName, description);
    }

    /**
     * Sets traffic file and calls the parser
     * @param filepath
     * @return 
     */
    public boolean setTrafficFile(String filepath) {
        if (filepath == null) {
            System.out.println("File path null");
            return false;
        }
        ImportSimulationXML r = new ImportSimulationXML();
        ArrayList<TrafficPattern> list = r.read(filepath, m_project);
        if (list == null) {
            return false;
        }
        m_simulation.setTrafficPatternList(list);
        m_project.setActiveSimulation(m_simulation);
        if (m_simulation.xmlImported()) {
            m_project.simulationCreated();
            System.out.println("true");
            return true;
        } else {
            System.out.println("false");
            return false;
        }
    }
}
