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
public class SimulationRun {

    private String m_name;
    private double m_duration;
    private double m_timeStep;
    private RoadNetwork m_roadNetwork;
    private BestPathAlgorithm m_bestPathMethod;
    private TrafficPattern m_trafficPattern;
    private SimSegmentsManager m_simSegmentsManager;
    private SimVehiclesGenerator m_simVehiclesGenerator;

    private double m_currentTime;
    private ArrayList<SimVehicle> m_endedVehicles;
    private ArrayList<SimVehicle> m_rejectedVehicles;

    public SimulationRun(String name, double duration, double timeStep, RoadNetwork roadNetwork, ArrayList<TrafficPattern> trafficPattern, BestPathAlgorithm bpMethod) {

        m_roadNetwork = roadNetwork;
        m_bestPathMethod = bpMethod;

        m_simSegmentsManager = new SimSegmentsManager(roadNetwork);
        m_simVehiclesGenerator = new SimVehiclesGenerator(m_roadNetwork, timeStep, trafficPattern, m_bestPathMethod);

        m_currentTime = 0;
        m_endedVehicles = new ArrayList();
        m_rejectedVehicles = new ArrayList();

    }

    public void start() {

        while (m_currentTime <= m_duration) {

            //remove ending vehicles from the simulation
            m_endedVehicles.addAll(m_simSegmentsManager.popEndingVehicles(m_currentTime));

            //update position of vehicles currntly on the simulation
            m_simSegmentsManager.updateCrossingVehicles(m_currentTime);

            //generate vehicles to inject in the network on this time step
            ArrayList<SimVehicle> nextStepVehicles = m_simVehiclesGenerator.generateNextStepVehicles();

            //inject generated vehicles on the network and log the ones tha were not able to be injected
            m_rejectedVehicles.addAll(m_simSegmentsManager.injectNewVehicles(nextStepVehicles));

            m_currentTime += m_timeStep;

        }

    }

}
