/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadnetwork.domain;

/**
 *
 * @author Andre
 */
public class EngineEfficiency {
    


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
    

    public int getGear() {
        return gear;
    }

    public void setGear(int gear) {
        this.gear = gear;
    }

    public String getThrottleRatio() {
        return throttleRatio;
    }

    public void setThrottleRatio(String throttleRatio) {
        this.throttleRatio = throttleRatio;
    }

//    public double getResult() {
//        return result;
//    }

//    public void setResult(double result) {
//        this.result = result;
//    }

    public double getTorque() {
        return torque;
    }

    public void setTorque(double torque) {
        this.torque = torque;
    }

    public double getM_rpmLow() {
        return m_rpmLow;
    }

    public void setM_rpmLow(double m_rpmLow) {
        this.m_rpmLow = m_rpmLow;
    }

    public double getM_rpmHigh() {
        return m_rpmHigh;
    }

    public void setM_rpmHigh(double m_rpmHigh) {
        this.m_rpmHigh = m_rpmHigh;
    }

    public double getM_sfc() {
        return m_sfc;
    }

    public void setM_sfc(double m_sfc) {
        this.m_sfc = m_sfc;
    }

    public double getGearRatio() {
        return gearRatio;
    }

    public void setGearRatio(double gearRatio) {
        this.gearRatio = gearRatio;
    }
    
    

}
