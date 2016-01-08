/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadnetwork.state;

import roadnetwork.domain.Simulation;

/**
 *
 * @author André Pedrosa, Hélder Faria, José Miranda, Rubén Rosário
 */
public class SimulationStateActive extends SimulationStateImpl{

    public SimulationStateActive(Simulation simulation) {
        super(simulation);
    }

    @Override
    public boolean canRunSimulation() {
        return true;
    }
    
    
    
    
}
