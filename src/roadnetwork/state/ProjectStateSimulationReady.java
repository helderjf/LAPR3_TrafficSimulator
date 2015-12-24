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
    public boolean canSimulate() {
        return true;
    }
    
    @Override
    public boolean projectSaved() {
        
        ProjectState ns = new ProjectStateSimulationReadySaved(m_project);
        m_project.setState(ns);
        return true;
    }    
    
    

}
