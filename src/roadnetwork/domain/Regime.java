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

    /**
     *
     */
    public Regime() {
    }

    /**
     *
     * @param torque torque
     * @param rpmLow rpmLow
     * @param rpmHigh rpmHigh
     * @param sfc sfc
     */
    public Regime(double torque, double rpmLow, double rpmHigh, double sfc) {
        m_torque = torque;
        m_rpmLow = rpmLow;
        m_rpmHigh = rpmHigh;
        m_sfc = sfc;
    }
    
    /**
     *
     * @param pk pk
     * @param torque torque
     * @param rpmLow rpmLow
     * @param rpmHigh rpmHigh
     * @param sfc sfc
     */
    public Regime(int pk, double torque, double rpmLow, double rpmHigh, double sfc) {
        m_pk=pk;
        m_torque = torque;
        m_rpmLow = rpmLow;
        m_rpmHigh = rpmHigh;
        m_sfc = sfc;
    }
    
    /**
     *
     * @return PK
     */
    public int getPK() {
        return m_pk;
    }

    /**
     *
     * @return RPMLow
     */
    public double getRPMLow() {
        return m_rpmLow;
    }

    /**
     *
     * @return RPMHigh
     */
    public double getRPMHigh() {
        return m_rpmHigh;
    }

    /**
     *
     * @return Sfc
     */
    public double getSfc() {
        return m_sfc;
    }

    /**
     *
     * @return Torque
     */
    public double getTorque() {
        return m_torque;
    }

    /**
     *
     * @param pk pk
     */
    public void setPK(int pk) {
        m_pk = pk;
    }

    /**
     *
     * @param torque torque
     */
    public void setTorque(double torque) {
        m_torque = torque;
    }

    /**
     *
     * @param rpmLow rpmLow
     */
    public void setM_rpmLow(double rpmLow) {
        m_rpmLow = rpmLow;
    }

    /**
     *
     * @param rpmHigh rpmHigh
     */
    public void setM_rpmHigh(double rpmHigh) {
        m_rpmHigh = rpmHigh;
    }

    /**
     *
     * @param sfc sfc
     */
    public void setM_sfc(double sfc) {
        m_sfc = sfc;
    }

}
