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
public class ProjectStateSimulationReadySaved extends ProjectStateImpl {

    public ProjectStateSimulationReadySaved(Project p) {
        super(p);
    }

    @Override
    public boolean canAnalyse() {
        return true;
    }

    @Override
    public boolean isSaved() {
        return true;
    }

    @Override
    public boolean hasRoadNetwork() {
        return true;
    }

    @Override
    public boolean hasVehicles() {
        return true;
    }

    @Override
    public boolean propertiesChanged() {
        ProjectState ns = new ProjectStateSimulationReady(m_project);
        return m_project.setState(ns);
    }

    @Override
    public boolean canSimulate() {
        return true;
    }

    @Override
    public boolean simulationCreated() {
        ProjectState ns = new ProjectStateSimulationReady(m_project);
        m_project.setState(ns);
        return true;
        
    }
    
    @Override
    public boolean simulationPropertiesChanged() {
        ProjectState ns = new ProjectStateSimulationReady(m_project);
        m_project.setState(ns);
        return true;
    }    
    
    @Override
    public boolean canOpenSimulation() {
        return true;
    }    
    
    public boolean vehiclesAssigned(){
        return true;
    }
    
    @Override
    public boolean canCopySimulation() {
        return true;
    }    

}
