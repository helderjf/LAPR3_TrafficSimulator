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
public class ProjectStateSimulationReadySaved extends ProjectStateImpl{

    public ProjectStateSimulationReadySaved(Project p) {
        super(p);
    }

    @Override
    public boolean canSimulate() {
        return true;
    }
    
    
    
}
