/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadnetwork.controllers;

import data.access.layer.ProjectReader;
import data.access.layer.ProjectWriter;
import java.sql.SQLRecoverableException;
import java.util.ArrayList;
import java.util.HashMap;
import roadnetwork.domain.Manager;
import roadnetwork.domain.Project;
import roadnetwork.domain.Simulation;

/**
 *
 * @author André Pedrosa, Hélder Faria, José Miranda, Rubén Rosário
 */
public class DeleteRunController {

    private Manager m_manager;
    private Project m_project;
    private Simulation m_simulation;
    private ProjectReader m_projectReader;
    private ProjectWriter m_projectWriter;
    private HashMap<String, Integer> m_runsMap;

    public DeleteRunController(Manager manager) {
        m_manager = manager;
    }

    public int canDeleteRun() throws SQLRecoverableException {
        if (m_manager.getCurrentProject() == null) {
            return -1;
        }
        m_project = m_manager.getCurrentProject();
        if (!m_project.canDeleteRun()) {
            return -2;
        }
        if (m_project.getCurrentSimulation() == null) {
            return -3;
        }
        m_simulation = m_project.getCurrentSimulation();
        if (!m_simulation.canDeleteRun()) {
            return -4;
        }
        m_projectReader = m_manager.getProjectReader();
        if (!m_projectReader.simulationExists(m_project.getPK(), m_simulation.getName())) {
            return -5;
        }
        if (!m_projectReader.simulationHasRuns(m_simulation.getPK())) {
            return -6;
        }
        return 1;
    }

    public ArrayList<String> getSimulationRuns() throws SQLRecoverableException {
        m_runsMap = m_projectReader.getSimulationRunsOrderedList(m_simulation.getPK());
        ArrayList<String> runNames = new ArrayList();
        for (String rname : m_runsMap.keySet()) {
            runNames.add(rname);
        }
        return runNames;
    }

    public boolean deleteRun(String runName) throws SQLRecoverableException {

        m_projectWriter = m_manager.getProjectWriter();
        int runPK = m_runsMap.get(runName);
        return m_projectWriter.deleteSimulationRun(runPK);

    }

}
