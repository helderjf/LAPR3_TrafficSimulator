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
public class SimulationStateCreated extends SimulationStateImpl{

    public SimulationStateCreated(Simulation simulation) {
        super(simulation);
    }
    
    public boolean setStateActive(Simulation simulation){
        SimulationState ns = new SimulationStateActive(simulation);
        simulation.setState(ns);
        return true;
    }
}
