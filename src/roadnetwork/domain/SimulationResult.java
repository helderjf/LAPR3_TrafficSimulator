/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadnetwork.domain;

import java.util.ArrayList;

/**
 *
 * @author André Pedrosa, Hélder Faria, José Miranda, Rubén Rosário
 */
public class SimulationResult {
    
    private ArrayList<Section> m_path;
    private double m_length;
    
    public SimulationResult(){
        
    }

    /**
     * @return the m_path
     */
    public ArrayList<Section> getM_path() {
        return m_path;
    }

    /**
     * @return the m_length
     */
    public double getM_length() {
        return m_length;
    }

    /**
     * @param m_path the m_path to set
     */
    public void setM_path(ArrayList<Section> m_path) {
        this.m_path = m_path;
    }

    /**
     * @param m_length the m_length to set
     */
    public void setM_length(double m_length) {
        this.m_length = m_length;
    }
    
    
    
}
