/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadnetwork.controllers;

import IO.ExportHTML;
import data.access.layer.ProjectReader;
import data.access.layer.ProjectWriter;
import java.util.ArrayList;
import roadnetwork.domain.BestPathAlgorithm;
import roadnetwork.domain.Manager;
import roadnetwork.domain.Project;
import roadnetwork.domain.Result;
import roadnetwork.domain.ResultSimulation;
import roadnetwork.domain.RoadNetwork;
import roadnetwork.domain.Simulation;
import roadnetwork.domain.SimulationRun;

/**
 *
 * @author André Pedrosa, Hélder Faria, José Miranda, Rubén Rosário
 */
public class RunSimulationController {

    private Manager m_manager;
    private Project m_project;
    private Simulation m_simulation;
    private RoadNetwork m_roadNetwork;
    private SimulationRun m_simulationRun;
    private ResultSimulation m_runResults;
    private ProjectReader m_projectReader;
    private ProjectWriter m_projectWriter;

    public RunSimulationController(Manager manager) {

        m_manager = manager;

    }

    public boolean newSimulationRun() {
        if (m_manager.getCurrentProject() == null) {
            return false;
        }
        m_project = m_manager.getCurrentProject();

        if (m_project.canSimulate()) {
            if (m_project.getCurrentSimulation() != null) {
                m_simulation = m_project.getCurrentSimulation();
                return m_simulation.canRunSimulation();
            }
        }
        return false;

    }

    public ArrayList<BestPathAlgorithm> getBestPathMethods() {
        return m_manager.getAlgorithmsList();

    }

    public ResultSimulation setRunData(String runName, double runDuration, double runTimeStep, BestPathAlgorithm bpm) {
        m_roadNetwork = m_project.getRoadNetwork();
        m_simulationRun = m_simulation.newSimulationRun(m_roadNetwork, runName, runDuration, runTimeStep, bpm);

        m_simulationRun.start();

        m_runResults = m_simulationRun.getResults();

        return m_runResults;
    }

    public int saveRun() {

        if (m_manager.getProjectReader().simulationExists(m_project.getPK(), m_simulation.getName())!=0) {
            return -2;
        }

        m_projectWriter = m_manager.getProjectWriter();
        if (!m_projectWriter.saveSimulationRun(m_simulation)) {
            return -3;
        }
        return 1;
    }

}
