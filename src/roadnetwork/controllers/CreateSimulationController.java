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

    public boolean newSimulation() {
        m_project = m_manager.getCurrentProject();
        if (m_project != null) {
            return m_project.canSimulate();
        }
        return false;
    }

    public boolean simulationExists(String simulationName) {
        //TODO
        /*if (m_project.simulationExists(simulationName)) {
         return true;
         }
         if (m_project.hasPK()) {
         return m_manager.getProjectReader().simulationExists(m_project.getPK(), simulationName);
         }
         return false;*/
        return true;
    }

    public void setSimulation(String simulationName, String description) {//to do descomentar isto e mudar o método para boolean
//        if (m_manager.getProjectReader().simulationExists(m_project.getPK(), simulationName)
//                || m_project.simulationExists(simulationName)) {
//            return false;
//        }
        m_simulation = m_project.newSimulation(simulationName, description);
    }

    public boolean setTrafficFile(String filepath) {
        System.out.println("SOUT");
        if (filepath == null) {
            System.out.println("File path null");
            return false;
        }
        ImportSimulationXML r = new ImportSimulationXML();
        ArrayList<TrafficPattern> list = r.read(filepath, m_project);
        if (list == null) {
            System.out.println("list null");
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
