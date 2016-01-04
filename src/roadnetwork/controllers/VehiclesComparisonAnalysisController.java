/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadnetwork.controllers;

import java.util.ArrayList;
import roadnetwork.domain.BestPathAlgorithm;
import roadnetwork.domain.Junction;
import roadnetwork.domain.Manager;
import roadnetwork.domain.Project;
import roadnetwork.domain.RoadNetwork;
import roadnetwork.domain.Vehicle;

/**
 *
 * @author josemiranda
 */
public class VehiclesComparisonAnalysisController {
    
    Manager m_manager;
    Project m_project;
    ArrayList<Vehicle> m_vehiclesList;
    RoadNetwork m_roadNetwork;
    ArrayList<Junction> m_nodeList;
    Junction m_originJunction;
    Junction m_destinyJunction;
    BestPathAlgorithm m_bpAlgorithm;
    
    
    public VehiclesComparisonAnalysisController(Manager manager){
        m_manager=manager;
    }
    
    public boolean projectActive(){
        m_project=m_manager.getCurrentProject();
        if (m_project==null) {
            return false;
        }
        return true;
    }
    
    public boolean newAnalysis(){
        return m_project.canAnalyse();
    }
    
    public ArrayList<Vehicle> newVehicleComparisonAnalysis(){
        return m_project.getVehicleList();
    }
    
    public void setVehiclesList(ArrayList<Vehicle> vlst){
        m_vehiclesList=vlst;
    }
    
    public ArrayList<Junction> getNodeList(){
        m_roadNetwork=m_project.getRoadNetwork();
        m_nodeList=m_roadNetwork.getNodeList();
        return m_nodeList;
    }
    
    public void setAnalysisNodes(Junction on, Junction dn){
        m_originJunction=on;
        m_destinyJunction=dn;
    }
    
    public ArrayList<BestPathAlgorithm> getBestPathAlgorithms(){
        return m_manager.getAlgorithmsList();
    }
    
    public void setBestPathAlgorithm(BestPathAlgorithm bpa){
        m_bpAlgorithm=bpa;
    }
    
    public void runAnalysis(){
        
    
    }
    
}
