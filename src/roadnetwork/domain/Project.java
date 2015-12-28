/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadnetwork.domain;

import java.io.File;
import java.util.ArrayList;
import IO.*;
import org.w3c.dom.*;
import roadnetwork.state.ProjectState;
import roadnetwork.state.ProjectStateCreated;

/**
 *
 * @author André Pedrosa, Hélder Faria, José Miranda, Rubén Rosário
 */
public class Project {

    private int m_PK;
    private String m_name;
    private String m_description;
    private RoadNetwork m_roadNetwork;
    private ArrayList<Vehicle> m_vehicleList;
    private ProjectState m_state;

    public Project() {
        m_state = new ProjectStateCreated(this);
    }

    public Project(String name, String description) {
        m_name = name;
        m_description = description;
        m_state = new ProjectStateCreated(this);
    }

    public boolean setState(ProjectState newState) {
        m_state = newState;
        return true;
    }

    public void setName(String name) {
        m_name = name;
    }

    public void setDescription(String description) {
        m_description = description;
    }

    public String getName() {
        return m_name;
    }

    public String getDescription() {
        return m_description;
    }

    public ArrayList<Vehicle> getVehicleList() {
        return m_vehicleList;
    }

    public RoadNetwork getRoadNetwork() {
        return m_roadNetwork;
    }

    public void setRoadNetwork(RoadNetwork m_roadNetwork) {
        this.m_roadNetwork = m_roadNetwork;
    }

    public void setVehicleList(ArrayList<Vehicle> m_vehicleList) {
        this.m_vehicleList = m_vehicleList;
    }

    public Simulation newBestPathSimulation(RoadNetwork rn, Junction oj,
            Junction dj, BestPathAlgorithm alg, Vehicle v) {
        return new Simulation(rn, oj, dj, alg, v);
    }

    @Override
    public String toString() {
        return m_name + " - " + m_description;
    }

    public boolean projectCreated() {

        return (m_name != null && m_description != null)
                && m_state.projectCreated();
    }

    public boolean canSimulate() {
        return m_state.canSimulate();
    }

    public boolean canEditProperties() {
        return m_state.canEditProperties();
    }

    public boolean propertiesChanged() {
        return m_state.propertiesChanged();
    }

    public boolean isSaved() {
        return m_state.isSaved();
    }

    public boolean hasPK() {
        if (m_PK != 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean projectSaved() {
        return m_state.projectSaved();
    }

    public ProjectState getState() {
        return m_state;
    }

    public void setPK(int pk) {
        m_PK=pk;
    }
    
    public int getPK(){
        return m_PK;
    }
    

    public boolean hasRoadNetwork() {
        return m_state.hasRoadNetwork();
    }

    public boolean hasVehicles() {
        return m_state.hasVehicles();
    }

}
