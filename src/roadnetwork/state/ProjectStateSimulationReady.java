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
public class ProjectStateSimulationReady extends ProjectStateImpl {

    public ProjectStateSimulationReady(Project p) {
        super(p);
    }

    @Override
    public boolean canAnalyse() {
        return true;
    }

    @Override
    public boolean projectSaved() {

        ProjectState ns = new ProjectStateSimulationReadySaved(m_project);
        m_project.setState(ns);
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
    public boolean canSimulate() {
        return true;
    }

    @Override
    public boolean vehiclesAssigned() {
        return true;
    }

    @Override
    public boolean canOpenSimulation() {
        return true;
    }

    @Override
    public boolean canCopySimulation() {
        return true;
    }

    @Override
    public boolean canDeleteRun() {
        return true;
    }

}
