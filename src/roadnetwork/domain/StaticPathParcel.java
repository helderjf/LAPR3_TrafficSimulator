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
    private double m_theoreticalTravelTime;
    private double m_theoreticalEnergyConsumption;
    private double m_tollCosts;
    
    
    public StaticPathParcel(Section section){
        m_section=section;
    }
    
    public StaticPathParcel(Section section, SimDirection direction){
        m_section=section;
        m_direction=direction;
    }
    

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
     * @return the m_travelTime
     */
    @Override
    public double getTheoreticalTravelTime() {
        return m_theoreticalTravelTime;
    }

    /**
     * @return the m_energyConsumption
     */
    public double getTheoreticalEnergyConsumption() {
        return m_theoreticalEnergyConsumption;
    }

    /**
     * @return the m_tollCosts
     */
    public double getTollCosts() {
        return m_tollCosts;
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
    
    /**
     * @param m_travelTime the m_travelTime to set
     */
    @Override
    public void setTheoreticalTravelTime(double m_travelTime) {
        this.m_theoreticalTravelTime = m_travelTime;
    }

    /**
     * @param energyConsumption the energyConsumption to set
     */
    public void setTheoreticalEnergyConsumption(double energyConsumption) {
        this.m_theoreticalEnergyConsumption = energyConsumption;
    }

    /**
     * @param tollCosts the tollCosts to set
     */
    public void setTollCosts(double tollCosts) {
        this.m_tollCosts = tollCosts;
    }

    @Override
    public PathParcel createReversePP() {
        return new StaticPathParcel(this.getSection());
    }

}
