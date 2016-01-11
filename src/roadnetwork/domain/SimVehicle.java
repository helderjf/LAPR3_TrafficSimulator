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
    private boolean ended;

    public SimVehicle(Vehicle vehicle, Junction originNode, Junction destinyNode, ArrayList<SimPathParcel> simpath, TrafficPattern trafficPattern, double injectionTime) {
        m_vehicle = vehicle;
        m_originNode = originNode;
        m_destinyNode = destinyNode;
        m_trafficPattern = trafficPattern;
        m_path = simpath;
        m_injectionTime = injectionTime;
        m_currentPos = null;
        m_nextPos = null;
        ended = false;
    }

    boolean willEndAtThisTimeStep(double currentTime) {
        return m_nextPos != null
                && (m_currentPos.getPredictedExitTime() <= currentTime);
    }

    boolean endSimulation(double currentTime) {

        m_currentPos.setSimExitTime(currentTime);
        m_currentPos = null;
        ended = true;

        return true;
    }

    double getPredictedExitTime() {
        return m_currentPos.getPredictedExitTime();
    }

    double getWaitingTime(double currentTime) {
        return (currentTime - m_currentPos.getSimInTime() - m_currentPos.getTheoreticalTravelTime());
    }

    public double getArrivalTime() {
        return (m_currentPos.getSimInTime() + m_currentPos.getTheoreticalTravelTime());//TODO verificar se é mesmo isto (em princípio nao)
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

}
