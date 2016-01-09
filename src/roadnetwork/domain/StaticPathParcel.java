/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadnetwork.domain;

/**
 *
 * @author josemiranda
 */
public class StaticPathParcel implements PathParcel{
    
    private Section m_section;
    private SimDirection m_direction;
    
    public StaticPathParcel(Section section){
        m_section=section;
    }
    
    public StaticPathParcel(Section section, SimDirection direction){
        m_section=section;
        m_direction=direction;
    }
    private double m_theoreticalTravelTime;
    private double m_simInTime;
    private double m_simExitTime;

    /**
     * @return the m_section
     */
    @Override
    public Section getSection() {
        return m_section;
    }
    
    /**
     * @return the m_direction
     */
    @Override
    public SimDirection getDirection() {
        return m_direction;
    }

    /**
     * @return the m_theoreticalExitTime
     */
    public Double getTheoreticalTravelTime() {
        return m_theoreticalTravelTime;
    }

    /**
     * @param m_section the m_section to set
     */
    public void setSection(Section m_section) {
        this.m_section = m_section;
    }
    
    /**
     * @param m_direction the m_direction to set
     */
    @Override
    public void setDirection(SimDirection m_direction) {
        this.m_direction = m_direction;
    }

    @Override
    public PathParcel createReversePP() {
        return new StaticPathParcel(this.getSection());
    /**
     * @param m_exitTime the m_theoreticalExitTime to set
     */
    public void setTheoreticalExitTime(Double m_exitTime) {
        this.m_theoreticalTravelTime = m_exitTime;
    }

    double getSimInTime() {
        return m_simInTime;
    }
    
}
