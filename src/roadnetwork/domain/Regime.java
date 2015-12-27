/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadnetwork.domain;

/**
 *
 * @author André Pedrosa, Hélder Faria, José Miranda, Rubén Rosário
 */
public class Regime {

    private int m_pk;
    private double m_torque;
    private double m_rpmLow;
    private double m_rpmHigh;
    private double m_sfc;

    public Regime() {
    }

    public Regime(double torque, double rpmLow, double rpmHigh, double sfc) {
        m_torque = torque;
        m_rpmLow = rpmLow;
        m_rpmHigh = rpmHigh;
        m_sfc = sfc;
    }

    public int getPK() {
        return m_pk;
    }

    public double getRPMLow() {
        return m_rpmLow;
    }

    public double getRPMHigh() {
        return m_rpmHigh;
    }

    public double getSfc() {
        return m_sfc;
    }

    public double getTorque() {
        return m_torque;
    }

    public void setPK(int pk) {
        m_pk = pk;
    }

    public void setTorque(double torque) {
        m_torque = torque;
    }

    public void setM_rpmLow(double rpmLow) {
        m_rpmLow = rpmLow;
    }

    public void setM_rpmHigh(double rpmHigh) {
        m_rpmHigh = rpmHigh;
    }

    public void setM_sfc(double sfc) {
        m_sfc = sfc;
    }

}
