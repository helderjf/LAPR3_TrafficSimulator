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
    private ArrayList<TrafficPattern> m_trafficPatternList;
    private SimSegmentsManager m_simSegmentsManager;
    private SimVehiclesGenerator m_simVehiclesGenerator;

    private double m_currentTime;
    
    private ArrayList<SimVehicle> m_endedVehicles;
    private ArrayList<SimVehicle> m_droppedVehicles;
    private ArrayList<SimVehicle> m_cruisingVehicles;
    
    private ResultSimulation m_runResults;

    
    public SimulationRun(String name, double duration, double timeStep, RoadNetwork roadNetwork, ArrayList<TrafficPattern> trafficPattern, BestPathAlgorithm bpMethod) {

        m_name=name;
        m_duration=duration;
        m_timeStep=timeStep;
        
        m_roadNetwork = roadNetwork;
        m_trafficPatternList=trafficPattern;
        m_bestPathMethod = bpMethod;

        m_simSegmentsManager = new SimSegmentsManager(roadNetwork);
        m_simVehiclesGenerator = new SimVehiclesGenerator(m_roadNetwork, timeStep, trafficPattern, m_bestPathMethod);

        m_currentTime = 0;
        m_endedVehicles = new ArrayList();
        m_cruisingVehicles = new ArrayList();
        m_droppedVehicles = new ArrayList();

    }

    public void start() {

        while (m_currentTime <= m_duration) {


            //update position of vehicles currently on the simulation
            //this will remove ending vehicles from the network
            //and move cruising vehicles to the next segment when possible
            m_endedVehicles.addAll(m_simSegmentsManager.updateCurrentVehicles(m_currentTime));

            //generate vehicles to inject in the network on this time step
            ArrayList<SimVehicle> nextStepVehicles = m_simVehiclesGenerator.generateNextStepVehicles(m_currentTime);

            //inject generated vehicles on the network and log the ones that were not able to be injected
            m_droppedVehicles.addAll(m_simSegmentsManager.injectCreatedVehicles(m_currentTime, nextStepVehicles));

            m_currentTime += m_timeStep;

        }
        
        m_cruisingVehicles = m_simSegmentsManager.endSimulation(m_duration);
        
        createResults();
        
    }

    public ResultSimulation getResults() {
        return m_runResults;
    }

    private void createResults() {
        m_runResults = new ResultSimulation(m_name, m_duration, m_timeStep, m_endedVehicles, m_cruisingVehicles, m_droppedVehicles);
        
        
    }

}
