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
public class ProjectStateEmpty extends ProjectStateImpl {

    public ProjectStateEmpty(Project p) {
        super(p);
    }

    @Override
    public boolean canImportRoadNetwork() {
        return true;
    }

    @Override
    public boolean projectSaved() {
        
        ProjectState ns = new ProjectStateEmptySaved(m_project);
        m_project.setState(ns);
        return true;
    }
    
    @Override
    public boolean roadNetworkAssigned(){
        ProjectState ns = new ProjectStateRoadNetworkAssigned(m_project);
        m_project.setState(ns);
        return true;
    }

}
