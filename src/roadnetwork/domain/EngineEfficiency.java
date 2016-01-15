/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadnetwork.domain;

import java.util.Objects;

/**
 *
 * @author Andre
 */
public class EngineEfficiency {
    
    public EngineEfficiency()
    {
        
    }
    
    //Regime Data
    private double torque;
    private double m_rpmLow;
    private double m_rpmHigh;
    private double m_sfc;
    //Throttle Data
    private String throttleRatio;
    //Gear Data
    private int gear;
    private double gearRatio;

    //Results of gear ratio * torque in a certain gear and throttle ratio
    //private double result;
    
    /**
     *
     * @return gear
     */
    public int getGear() {
        return gear;
    }

    /**
     *
     * @param gear gear
     */
    public void setGear(int gear) {
        this.gear = gear;
    }

    /**
     *
     * @return throttleRatio
     */
    public String getThrottleRatio() {
        return throttleRatio;
    }

    /**
     *
     * @param throttleRatio throttleRatio
     */
    public void setThrottleRatio(String throttleRatio) {
        this.throttleRatio = throttleRatio;
    }

    /**
     *
     * @return torque
     */
    
    public double getTorque() {
        return torque;
    }

    /**
     *
     * @param torque torque
     */
    public void setTorque(double torque) {
        this.torque = torque;
    }

    /**
     *
     * @return m_rpmLow
     */
    public double getM_rpmLow() {
        return m_rpmLow;
    }

    /**
     *
     * @param m_rpmLow m_rpmLow
     */
    public void setM_rpmLow(double m_rpmLow) {
        this.m_rpmLow = m_rpmLow;
    }

    /**
     *
     * @return m_rpmHigh
     */
    public double getM_rpmHigh() {
        return m_rpmHigh;
    }

    /**
     *
     * @param m_rpmHigh m_rpmHigh
     */
    public void setM_rpmHigh(double m_rpmHigh) {
        this.m_rpmHigh = m_rpmHigh;
    }

    /**
     *
     * @return m_sfc
     */
    public double getM_sfc() {
        return m_sfc;
    }

    /**
     *
     * @param m_sfc m_sfc
     */
    public void setM_sfc(double m_sfc) {
        this.m_sfc = m_sfc;
    }

    /**
     *
     * @return gearRatio
     */ 
    public double getGearRatio() {
        return gearRatio;
    }

    /**
     *
     * @param gearRatio gearRatio
     */
    public void setGearRatio(double gearRatio) {
        this.gearRatio = gearRatio;
    }

    /**
     * 
     * @param obj EngineEfficiency
     * @return result
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final EngineEfficiency other = (EngineEfficiency) obj;
        if (Double.doubleToLongBits(this.torque) != Double.doubleToLongBits(other.torque)) {
            return false;
        }
        if (Double.doubleToLongBits(this.m_rpmLow) != Double.doubleToLongBits(other.m_rpmLow)) {
            return false;
        }
        if (Double.doubleToLongBits(this.m_rpmHigh) != Double.doubleToLongBits(other.m_rpmHigh)) {
            return false;
        }
        if (Double.doubleToLongBits(this.m_sfc) != Double.doubleToLongBits(other.m_sfc)) {
            return false;
        }
        if (!Objects.equals(this.throttleRatio, other.throttleRatio)) {
            return false;
        }
        if (this.gear != other.gear) {
            return false;
        }
        if (Double.doubleToLongBits(this.gearRatio) != Double.doubleToLongBits(other.gearRatio)) {
            return false;
        }
        return true;
    }

    /**
     * 
     * @return print EngineEfficiency obj
     */
    @Override
    public String toString() {
        return "EngineEfficiency{" + "torque=" + torque + ", m_rpmLow=" + m_rpmLow + ", m_rpmHigh=" + m_rpmHigh + ", m_sfc=" + m_sfc + ", throttleRatio=" + throttleRatio + ", gear=" + gear + ", gearRatio=" + gearRatio + '}';
    }


}
