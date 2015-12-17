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
    
    
    
    
}
