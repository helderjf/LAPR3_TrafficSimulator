/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadnetwork.domain;

import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 *
 * @author André Pedrosa, Hélder Faria, José Miranda, Rubén Rosário
 */
public class SimSegment {

    private Section m_section;
    private Segment m_segment;
    private SimDirection m_direction;
    private Queue<SimVehicle> m_vehicleQueue;
    private ArrayList<TrafficRecord> m_log;

    public SimSegment(Section section, Segment segment, SimDirection direction) {
        m_section = section;
        m_segment = segment;
        m_direction = direction;
        m_vehicleQueue = new ArrayBlockingQueue(segment.getMax_Vehicles());
        m_log = new ArrayList();

    }

    ArrayList<SimVehicle> updateEndingVehicles(double currentTime) {
        ArrayList<SimVehicle> endedVehicles = new ArrayList();

        while (m_vehicleQueue.peek() != null && m_vehicleQueue.peek().willEndAtThisTimeStep(currentTime)) {

            m_vehicleQueue.peek().endSimulation(currentTime);
            endedVehicles.add(m_vehicleQueue.poll());
        }
        return endedVehicles;
    }

    SimVehicle getFirstWaitingVehicle(double currentTime) {
        if (m_vehicleQueue.peek().getPredictedExitTime() <= currentTime) {
            return m_vehicleQueue.peek();
        }
        return null;
    }

    public Queue<SimVehicle> getVehicleQueue() {
        return m_vehicleQueue;
    }

    Segment getSegment() {
        return m_segment;
    }

    SimDirection getDirection() {
        return m_direction;
    }

    boolean canInjectVehicle() {
        return m_vehicleQueue.size() < m_segment.getMax_Vehicles();
    }

    SimVehicle popCrossingVehicle(double currentTime) {
        SimVehicle sv = m_vehicleQueue.poll();
        sv.crossToNextPos(currentTime);
        return sv;
    }

    void injectCrossingVehicle(double currentTime, SimVehicle segVehicle) {
        m_vehicleQueue.add(segVehicle);
    }
}
