/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadnetwork.domain;

import java.util.ArrayList;
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
    private Simulation m_activeSimulation;//active simulation
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

    public StaticAnalysis newBestPathAnalysis(RoadNetwork rn, Junction oj,
            Junction dj, BestPathAlgorithm alg, Vehicle v) {
        return new StaticAnalysis(rn, oj, dj, alg, v);
    }

    public StaticAnalysis newComparisonStaticAnalysis(RoadNetwork rn, Junction oj,
            Junction dj, BestPathAlgorithm alg, ArrayList<Vehicle> vlst) {
        return new StaticAnalysis(rn, oj, dj, alg, vlst);
    }

    @Override
    public String toString() {
        return m_name + " - " + m_description;
    }

    public boolean projectCreated() {

        return (m_name != null && m_description != null)
                && m_state.projectCreated();
    }

    public boolean canAnalyse() {
        return m_state.canAnalyse();
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
        m_PK = pk;
    }

    public int getPK() {
        return m_PK;
    }

    public boolean hasRoadNetwork() {
        return m_state.hasRoadNetwork();
    }

    public boolean hasVehicles() {
        return m_state.hasVehicles();
    }

    public void addVehicleList(ArrayList<Vehicle> vehicleList) {
        if (m_vehicleList == null) {
            m_vehicleList = vehicleList;
        } else {
            m_vehicleList.addAll(vehicleList);
        }
    }

    public void setActiveSimulation(Simulation s) {
        m_activeSimulation = s;
    }

    public boolean canSimulate() {
        return m_state.canSimulate();
    }

    public boolean simulationExists(String simulationName) {
        if (m_activeSimulation == null) {
            return false;
        }

        return m_activeSimulation.getName().equals(simulationName);
    }

    public Simulation newSimulation(String simulationName, String description) {
        return new Simulation(simulationName, description);
    }

    public boolean canImportRoadNetwork() {
        return m_state.canImportRoadNetwork();
    }

    public boolean createRoadNetwork(String name, String description, ArrayList<Junction> junctions, ArrayList<Section> sectionList) {
        m_roadNetwork = new RoadNetwork(name, description, junctions, sectionList);

        if (!(m_roadNetwork.getNodeList().isEmpty() || m_roadNetwork.getSectionList().isEmpty())) {
            return m_state.roadNetworkAssigned();
        }
        return false;
    }

    public boolean canImportVehicles() {
        return m_state.canImportVehicles();
    }

    public boolean createVehicleList(ArrayList<Vehicle> vehicleList) {
        m_vehicleList = vehicleList;

        if (!vehicleList.isEmpty()) {
            return m_state.vehiclesAssigned();
        }
        return false;
    }

    public Simulation getCurrentSimulation() {
        return m_activeSimulation;
    }

    /**
     * Gets a vehicle by its name
     *
     * @param vehicleName as a String
     * @return returns the vehicle with that name as a Vehicle object
     */
    public Vehicle getVehicleByName(String vehicleName) {
        for (Vehicle v : m_vehicleList) {
            if (v.getName() == null ? vehicleName == null : v.getName().equals(vehicleName)) {
                return v;
            }
        }
        return null;
    }

    public void simulationCreated() {
        m_state.simulationCreated();
    }

    public boolean hasSimulation() {
        return m_activeSimulation != null;
    }

    public boolean simulationPropertiesChanged() {
        return m_state.simulationPropertiesChanged();
    }

    public boolean canOpenSimulation() {
        return m_state.canOpenSimulation();
    }

    public Vehicle getVehicleByPK(int vehiclePK) {
        for (Vehicle v : m_vehicleList) {
            if (v.getPK() == vehiclePK) {
                return v;
            }
        }
        return null;
    }

}
