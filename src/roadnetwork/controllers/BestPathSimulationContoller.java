/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadnetwork.controllers;

import java.util.ArrayList;
import roadnetwork.domain.Manager;
import roadnetwork.domain.Node;
import roadnetwork.domain.Project;
import roadnetwork.domain.RoadNetwork;
import roadnetwork.domain.Vehicle;

/**
 *
 * @author André Pedrosa, Hélder Faria, José Miranda, Rubén Rosário
 */
public class BestPathSimulationContoller {

    private Manager m_manager;
    private Project m_project;
    private Vehicle m_vehicle;
    private RoadNetwork m_roadNetwork;
    private ArrayList<Node> m_nodeList;
    

    public BestPathSimulationContoller(Manager manager) {
        m_manager = manager;

    }

    public ArrayList<Vehicle> newBestPathSimulati() {

        m_project = m_manager.getCurrentProject();

        return m_project.getVehicleList();

    }

    public void setVehicle(Vehicle v) {
        m_vehicle=v;
    }

    public RoadNetwork getRoadNetwork() {
        m_roadNetwork=m_project.getRoadNetwork();
        return m_roadNetwork;
    }

    public ArrayList<Node> getNodeList() {
        m_nodeList=m_roadNetwork.getNodeList();
        return m_nodeList;
    }
    
    
 
}
