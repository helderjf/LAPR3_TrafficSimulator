/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadnetwork.state;

import roadnetwork.domain.Project;

/**
 *
 * @author André Pedrosa, Hélder Faria, José Miranda, Rubén Rosário
 */
public class ProjectStateVehiclesAssignedSaved extends ProjectStateImpl {

    public ProjectStateVehiclesAssignedSaved(Project p) {
        super(p);
    }

    @Override
    public boolean canImportRoadNetwork() {
        return true;
    }

    @Override
    public boolean isSaved() {
        return true;
    }

    @Override
    public boolean hasVehicles() {
        return true;
    }

    @Override
    public boolean propertiesChanged() {
        ProjectState ns = new ProjectStateVehiclesAssigned(m_project);
        return m_project.setState(ns);
    }
    
    @Override
    public boolean roadNetworkAssigned(){
        ProjectState ns = new ProjectStateSimulationReady(m_project);
        m_project.setState(ns);
        return true;
    }
    
    @Override
    public boolean vehiclesAssigned(){
        return true;
    }

}
