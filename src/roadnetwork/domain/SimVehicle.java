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
public class SimVehicle {

    private Vehicle m_vehicle;
    private Junction m_originNode;
    private Junction m_destinyNode;
    private TrafficPattern m_trafficPattern;
    private ArrayList<SimPathParcel> m_path;
    private SimPathParcel m_currentPos;
    private SimPathParcel m_nextPos;
    private double m_injectionTime;
    private boolean m_ended;
    private boolean m_dropped;

    public SimVehicle(Vehicle vehicle, Junction originNode, Junction destinyNode, ArrayList<SimPathParcel> simpath, TrafficPattern trafficPattern, double injectionTime) {
        m_vehicle = vehicle;
        m_originNode = originNode;
        m_destinyNode = destinyNode;
        m_trafficPattern = trafficPattern;
        m_path = simpath;
        m_injectionTime = injectionTime;
        m_currentPos = null;
        m_nextPos = null;
        m_ended = false;
        m_dropped = false;
    }

    boolean willEndAtThisTimeStep(double currentTime) {
        return m_nextPos != null
                && (m_currentPos.getPredictedExitTime() <= currentTime);
    }

    boolean endSimulation(double currentTime) {

        m_currentPos.setSimExitTime(currentTime);
        double idleConsumption = m_vehicle.getIdleConsumption(getTimeIdle());
        m_currentPos.addToSimEnergyConsumption(idleConsumption);
        m_currentPos = null;
        m_ended = true;

        return true;//TO DO fazer validação
    }

    double getPredictedExitTime() {
        return m_currentPos.getPredictedExitTime();
    }

    SimPathParcel getNextPos() {
        return m_nextPos;
    }

    boolean crossToNextPos(double currentTime) {
        if (m_nextPos == null) {
            return false;
        }

        m_currentPos.setSimExitTime(currentTime);
        m_nextPos.setSimInTime(currentTime);
        double timeIdle = getTimeIdle();
        double idleConsumption = m_vehicle.getIdleConsumption(timeIdle);
        m_currentPos.addToSimEnergyConsumption(idleConsumption);

        m_currentPos = m_nextPos;
        if (m_path.indexOf(m_nextPos) == m_path.size()) {
            m_nextPos = null;
        } else {
            m_nextPos = m_path.get(m_path.indexOf(m_nextPos) + 1);
        }
        return true;

    }

    public void getStepInjectTime(double stepInjectTime) {
        m_injectionTime = stepInjectTime;
    }

    public double getInjectionTime() {
        return m_injectionTime;
    }

    public SimPathParcel getFirstSimPathParcel() {
        return m_path.get(0);
    }

    public void drop() {
        m_dropped = true;
    }

    public boolean isDropped() {
        return m_dropped;
    }

    public double getdroppedTime() {
        if (m_dropped) {
            return m_injectionTime;
        }
        return -1;
    }

    void setInjected(double time) {
        m_currentPos = m_path.get(0);
        m_nextPos = m_path.get(1);
        m_currentPos.initializePredictedExitTime(m_injectionTime);
    }

    private double getTimeIdle() {
        return (m_currentPos.getSimExitTime() - m_currentPos.getSimInTime() - m_currentPos.getTheoreticalTravelTime());
    }

    void updatePredictedExitTime(double time) {
        m_currentPos.setPredictedExitTime(time);
    }

}
