/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadnetwork.domain;

import java.util.Objects;

/**
 *
 * @author André Pedrosa, Hélder Faria, José Miranda, Rubén Rosário
 */
public class SimPathParcel implements PathParcel {

    private Section m_section;
    private Segment m_segment;
    private SimDirection m_direction;

    private double m_theoreticalTravelTime;
    private double m_simInTime;
    private double m_predictedExitTime;
    private double m_simExitTime;

    private double m_theoreticalEnergyConsumption;
    private double m_simEnergyConsumption;

    private double m_tollCosts;

    public SimPathParcel(Section section) {
        m_section = section;
    }

    /**
     * @return the m_section
     */
    @Override
    public Section getSection() {
        return m_section;
    }

    /**
     * @return the m_segment
     */
    public Segment getSegment() {
        return m_segment;
    }

    /**
     * @return the m_direction
     */
    @Override
    public SimDirection getDirection() {
        return m_direction;
    }

    /**
     * @return the m_theoreticalTravelTime
     */
    @Override
    public double getTheoreticalTravelTime() {
        return m_theoreticalTravelTime;
    }

    /**
     * @param m_section the m_section to set
     */
    public void setSection(Section m_section) {
        this.m_section = m_section;
    }

    /**
     * @param m_segment the m_segment to set
     */
    public void setSegment(Segment m_segment) {
        this.m_segment = m_segment;
    }

    /**
     * @param m_direction the m_direction to set
     */
    @Override
    public void setDirection(SimDirection m_direction) {
        this.m_direction = m_direction;
    }

    /**
     * @param time
     */
    @Override
    public void setTheoreticalTravelTime(double time) {
        this.m_theoreticalTravelTime = time;
    }

    public double getSimInTime() {
        return m_simInTime;
    }

    public void setSimInTime(double time) {
        m_simInTime = time;
        m_predictedExitTime=time+m_theoreticalTravelTime;
    }

    public double getSimExitTime() {
        return m_simExitTime;
    }

    public void setSimExitTime(double time) {
        m_simExitTime = time;
    }

    @Override
    public PathParcel createReversePP() {
        return new SimPathParcel(this.getSection());
    }

    @Override
    public double getTheoreticalEnergyConsumption() {
        return m_theoreticalEnergyConsumption;
    }

    @Override
    public void setTheoreticalEnergyConsumption(double energyConsumption) {
        m_theoreticalEnergyConsumption = energyConsumption;
    }

    @Override
    public double getTollCosts() {
        return m_tollCosts;
    }

    @Override
    public void setTollCosts(double tollCosts) {
        m_tollCosts = tollCosts;
    }

    public double getPredictedExitTime() {
        return m_predictedExitTime;
    }

    public void setPredictedExitTime(double time) {
        m_predictedExitTime = time;
    }

    public double getSimEnergyConsumption() {
        return m_simEnergyConsumption;
    }

    public void addToSimEnergyConsumption(double idleCosumption) {
        m_simEnergyConsumption = m_theoreticalEnergyConsumption+idleCosumption;
    }

    void initializePredictedExitTime(double injectionTime) {
        m_predictedExitTime=m_theoreticalTravelTime+injectionTime;
    }


    public String toString2() {
        return "SimPathParcel{" + "m_section=" + m_section + ", m_segment=" + m_segment + ", m_direction=" + m_direction + ", m_theoreticalTravelTime=" + m_theoreticalTravelTime + ", m_simInTime=" + m_simInTime + ", m_predictedExitTime=" + m_predictedExitTime + ", m_simExitTime=" + m_simExitTime + ", m_theoreticalEnergyConsumption=" + m_theoreticalEnergyConsumption + ", m_simEnergyConsumption=" + m_simEnergyConsumption + ", m_tollCosts=" + m_tollCosts + '}';
    }

    

}
