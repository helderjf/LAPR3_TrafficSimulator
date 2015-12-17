/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadnetwork.controllers;

import IO.ExportCSV;
import java.util.ArrayList;
import java.util.List;
import roadnetwork.domain.BestPathAlgorithm;
import roadnetwork.domain.Manager;
import roadnetwork.domain.Junction;
import roadnetwork.domain.Project;
import roadnetwork.domain.RoadNetwork;
import roadnetwork.domain.ResultFastestPath;
import roadnetwork.domain.Simulation;
import roadnetwork.domain.SimulationResult;
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
    private ArrayList<Junction> m_nodeList;
    private Junction m_originJunction;
    private Junction m_destinationJunction;
    private BestPathAlgorithm m_bpAlgorithm;
    private ResultFastestPath m_simulationResult;
    private ExportCSV m_csv;
    

    public BestPathSimulationContoller(Manager manager) {
        m_manager = manager;

    }

    public ArrayList<Vehicle> newBestPathSimulation() {

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

    public ArrayList<Junction> getNodeList() {
        m_nodeList=m_roadNetwork.getNodeList();
        return m_nodeList;
    }
    
    public void setSimulationNodes(Junction oj, Junction dj){
        m_originJunction=oj;
        m_destinationJunction=dj;
    }
    
    public List<BestPathAlgorithm> getBestPathAlgorithms(){
        return m_manager.getAlgorithmsList();
    }
 
    public void setAlgorithm(BestPathAlgorithm alg){
        m_bpAlgorithm=alg;
    }
    
    public SimulationResult runSimulation(){
        Simulation simulation = m_project.newBestPathSimulation(m_roadNetwork,
                                                                m_originJunction, 
                                                                m_destinationJunction, 
                                                                m_bpAlgorithm,
                                                                m_vehicle);
        return simulation.run();
    }
    
    public boolean exportResultsCSV(String fileName){
        m_csv=m_manager.newCSV(fileName);
        return m_csv.export(m_simulationResult);
    }
    
}
