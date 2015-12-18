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
public class ResultFastestPath implements SimulationResult{
    
    private ArrayList<Section> m_path;
    private double m_length;
    private ArrayList<Double> m_sectionWeight;
    
    public ResultFastestPath(){
        
    }

    /**
     * @return the m_path
     */
    public ArrayList<Section> getPath() {
        return m_path;
    }

    /**
     * @return the m_length
     */
    public double getLength() {
        return m_length;
    }
    
    /**
     * @return the m_sectionWeight
     */
    public ArrayList<Double> getSectionWeight() {
        return m_sectionWeight;
    }

    /**
     * @param m_path the m_path to set
     */
    public void setPath(ArrayList<Section> m_path) {
        this.m_path = m_path;
    }

    /**
     * @param m_length the m_length to set
     */
    public void setLength(double m_length) {
        this.m_length = m_length;
    }
    
    /**
     * @param m_sectionWeight the m_sectionWeight to set
     */
    public void setSectionWeight(ArrayList<Double> m_sectionWeight) {
        this.m_sectionWeight = m_sectionWeight;
    }

    @Override
    public String toString() {
        return "ResultFastestPath{" + '}';
    }
    
    
    
    
    
}
