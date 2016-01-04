/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadnetwork.domain;

import roadnetwork.state.SimulationState;
import roadnetwork.state.SimulationStateCreated;

/**
 *
 * @author André Pedrosa, Hélder Faria, José Miranda, Rubén Rosário
 */
public class Simulation {
    
    private int m_pk;
    private String m_name;
    private String m_description;
    private SimulationState m_state;

    public Simulation() {
        m_state = new SimulationStateCreated(this);
    }

    Simulation(String name, String description) {
        m_name = name;
        m_description=description;
        m_state = new SimulationStateCreated(this);
    }

    public String getName() {
        return m_name;
    }

    private SimulationState SimulationStateCreated(Simulation aThis) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
    
    
    
    
}
