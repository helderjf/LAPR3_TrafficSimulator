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
public class SimulationStateCreated extends SimulationStateImpl {

    public SimulationStateCreated(Simulation simulation) {
        super(simulation);
    }

    @Override
    public boolean xmlImported() {
        SimulationState ns = new SimulationStateActive(m_simulation);
        m_simulation.setState(ns);
        return true;
    }

    @Override
    public boolean canEditProperties() {
        return true;
    }

}
