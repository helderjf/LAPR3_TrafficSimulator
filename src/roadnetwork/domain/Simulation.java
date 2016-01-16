/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadnetwork.domain;

import java.util.ArrayList;
import roadnetwork.factory.StateFactory;
import roadnetwork.state.SimulationState;
import roadnetwork.state.SimulationStateCreated;

/**
 *
 * @author André Pedrosa, Hélder Faria, José Miranda, Rubén Rosário
 */
public class Simulation {

    private int m_pk;
    private String m_name;
    private String m_description;
    private SimulationState m_state;
    private ArrayList<TrafficPattern> m_trafficPatternList;

    private SimulationRun m_currentRun;

    public Simulation() {
        m_state = new SimulationStateCreated(this);
    }

    Simulation(String name, String description) {
        m_name = name;
        m_description = description;
        m_state = new SimulationStateCreated(this);
    }

    public Simulation(int m_pk, String m_name, String m_description, SimulationState m_state, ArrayList<TrafficPattern> m_trafficPatternList, SimulationRun m_currentRun) {
        this.m_pk = m_pk;
        this.m_name = m_name;
        this.m_description = m_description;
        this.m_state = m_state;
        this.m_trafficPatternList = m_trafficPatternList;
        this.m_currentRun = m_currentRun;
    }

    public Simulation(Simulation otherSimulation, StateFactory stateFactory) {
        m_name = otherSimulation.m_name;
        m_description = otherSimulation.m_description;
        m_trafficPatternList = new ArrayList();
        for (TrafficPattern tp : otherSimulation.m_trafficPatternList) {
            m_trafficPatternList.add(TrafficPattern.trafficPatternPseudoCopy(tp));
        }
        m_state = StateFactory.getSimulationState(otherSimulation.getState().getClass().getSimpleName(), this);
    }

    public String getName() {
        return m_name;
    }

    public int getPK() {
        return m_pk;
    }

    public String getDescription() {
        return m_description;
    }

    public boolean xmlImported() {

        return m_state.xmlImported();
    }

    public ArrayList<TrafficPattern> getTrafficPatternList() {
        return m_trafficPatternList;
    }

    public void setName(String m_name) {
        this.m_name = m_name;
    }

    public void setPK(int m_pk) {
        this.m_pk = m_pk;
    }

    public void setDescription(String m_description) {
        this.m_description = m_description;
    }

    public void setState(SimulationState m_state) {
        this.m_state = m_state;
    }

    public void setTrafficPatternList(ArrayList<TrafficPattern> m_trafficPatternList) {
        this.m_trafficPatternList = m_trafficPatternList;
    }

    private SimulationState SimulationStateCreated(Simulation aThis) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean canRunSimulation() {
        return m_state.canRunSimulation();
    }

    public SimulationRun newSimulationRun(RoadNetwork roadNetwork, String runName, double runDuration, double runTimeStep, BestPathAlgorithm bpm) {
        m_currentRun = null;
        SimulationRun run = new SimulationRun(runName, runDuration, runTimeStep, roadNetwork, m_trafficPatternList, bpm);
        m_currentRun = run;

        return m_currentRun;
    }

    public SimulationState getState() {
        return m_state;
    }

    public boolean hasPK() {
        return m_pk != 0;
    }

    public boolean canEditProperties() {
        return m_state.canEditProperties();
    }

    public boolean propertiesChanged() {
        return m_state.propertiesChanged();
    }

//    public void setCurrentRun(SimulationRun run){
//        m_currentRun=run;
//    }
    public SimulationRun getCurrentRun() {
        return m_currentRun;
    }

    public boolean canCopySimulation() {
        return m_state.canCopySimulation();
    }

    public TrafficPattern getTrafficPatternByPK(int pk) {
        for (TrafficPattern tp : m_trafficPatternList) {
            if (tp.getPK() == pk) {
                return tp;
            }
        }
        return null;
    }

}
