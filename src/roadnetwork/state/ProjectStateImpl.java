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
public class ProjectStateImpl implements ProjectState{
    
    protected Project m_project;
    
    public ProjectStateImpl(Project p){
        m_project = p;
    }
    
    public Project getProject(){
        return m_project;
    }

    @Override
    public boolean canImportRoadNetwork() {
        return false;
    }

    @Override
    public boolean canImportVehicles() {
        return false;
    }

    @Override
    public boolean canSaveProject() {
        return false;
    }

    @Override
    public boolean canCopyProject() {
        return false;
    }

    @Override
    public boolean canEditProperties() {
        return false;
    }

    @Override
    public boolean canSimulate() {
        return false;
    }

    @Override
    public boolean vadidate() {
        return false;
    }
    
    
    
    
}
