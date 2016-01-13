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

//    SimVehicle updateEndingVehicle(double currentTime) {
//        double previousExitTime = 0;
//
//        m_vehicleQueue.peek().endSimulation(currentTime);
//
//        return m_vehicleQueue.poll();
//    }

//    SimVehicle getFirstWaitingVehicle(double currentTime) {
//        if (m_vehicleQueue.peek() == null) {
//            return null;
//        }
//        if (m_vehicleQueue.peek().getPredictedExitTime() <= currentTime) {
//            return m_vehicleQueue.peek();
//        }
//        return null;
//    }

    public Queue<SimVehicle> getVehicleQueue() {
        return m_vehicleQueue;
    }

    Segment getSegment() {
        return m_segment;
    }

    SimDirection getDirection() {
        return m_direction;
    }

    boolean canAddVehicle() {
        return m_vehicleQueue.size() < m_segment.getMax_Vehicles();
    }

    SimVehicle popCrossingVehicle() {
        
        return  m_vehicleQueue.poll();
    }

    boolean pushCrossingVehicle(SimVehicle vehicle) {
       return  m_vehicleQueue.add(vehicle);
    }

    void injectCreatedVehicle(SimVehicle vehicle) {
        m_vehicleQueue.add(vehicle);
    }

    Queue<SimVehicle> getCruisingVehicles() {
        return m_vehicleQueue;
    }
}
