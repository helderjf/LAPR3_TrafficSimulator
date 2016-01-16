/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadnetwork.controllers;

import data.access.layer.ProjectReader;
import java.util.ArrayList;
import java.util.HashMap;
import roadnetwork.domain.Manager;
import roadnetwork.domain.Project;
import roadnetwork.domain.Simulation;

/**
 *
 * @author André Pedrosa, Hélder Faria, José Miranda, Rubén Rosário
 */
public class OpenSimulationController {

    private Manager m_manager;
    private Project m_project;
    private HashMap<String, Integer> m_simulationMap;
    private Simulation m_simulation;
    private ProjectReader m_projectReader;

    public OpenSimulationController(Manager manager) {
        m_manager = manager;
    }

    public int canOpenSimulation() {

        if (m_manager.getCurrentProject() == null) {
            return -1;
        }
        m_project = m_manager.getCurrentProject();
        if (!m_project.canOpenSimulation()) {
            return -2;
        }
        m_projectReader = m_manager.getProjectReader();
        if (!m_projectReader.projectHasSimulations(m_project.getPK())) {
            return -3;
        }
        return 1;

    }

    public ArrayList<String> getProjectSimulations() {

        m_simulationMap = m_projectReader.getOrderedSimulationList(m_project.getPK());
        ArrayList<String> simulations = new ArrayList();
        for (String name : m_simulationMap.keySet()) {
            simulations.add(name);
        }
        return simulations;
    }

    public boolean selectSimulation(String simulationName) {
        int simPK = m_simulationMap.get(simulationName);
        m_simulation = m_projectReader.getSimulation(m_project,simPK);
        if (m_simulation == null) {
            return false;
        }
        m_project.setActiveSimulation(m_simulation);
        return true;
    }

}
