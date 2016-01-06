/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadnetwork.domain;

import graphutils.Graph;
import graphutils.GraphAlgorithms;
import java.util.ArrayList;

/**
 *
 * @author André Pedrosa, Hélder Faria, José Miranda, Rubén Rosário
 */
public class MostEfficientPath implements BestPathAlgorithm{
    
    Graph<Junction, Section> m_graph;
    RoadNetwork m_roadNetwork;
    Junction m_originNode;
    Junction m_destinyNode;
    Vehicle m_vehicle;
    ArrayList<Section> m_fastestPath;
    ArrayList<Junction> m_fastestPathNodes;
    double m_fastestPathLength;
    ArrayList<Double> m_sectionEnergyConsumption;
    ArrayList<Double> m_sectionTime;
    ArrayList<Double> m_sectionTollCosts;
    
    private final double gravity = 9.81; // m^2
    private final double densityOfAir = 1.225; // kg/m3


    @Override
    public ResultStaticAnalysis bestPath(RoadNetwork roadNetwork, Junction originNode, Junction destinyNode, Vehicle vehicle) {
        m_graph = new Graph<>(true);
        m_roadNetwork=roadNetwork;
        m_originNode=originNode;
        m_destinyNode=destinyNode;
        m_vehicle=vehicle;
        m_fastestPath = new ArrayList<>();
        m_fastestPathNodes=new ArrayList<>();
        
        graphConstruction(m_roadNetwork, m_vehicle);
        
        m_fastestPathLength = GraphAlgorithms.getShortestPathLength(
                m_graph, m_originNode, m_destinyNode, m_fastestPath, m_fastestPathNodes);

        calculateSectionEnergyConsumption();
        calculateSectionTime();
        calculateSectionTollCosts();

        return constructResults();
    }

    private void graphConstruction(RoadNetwork rn, Vehicle vehicle) {
        for (Section sec : rn.getSectionList()) {
            addConection(sec);
        }
    }

    private void addConection(Section section) {
        if (section.getDirection().equals(SectionDirection.unidirectional)) {
            m_graph.insertEdge(section.getBeginningNode(), section.getEndingNode(), section, calculateEnergyConsumption(section));

        } else if (section.getDirection().equals(SectionDirection.bidirectional)) {
            m_graph.insertEdge(section.getBeginningNode(), section.getEndingNode(), section, calculateEnergyConsumption(section));
            m_graph.insertEdge(section.getEndingNode(), section.getBeginningNode(), section, calculateEnergyConsumption(section));
        }
    }

    /**
     *
     * @param section section
     * @param vehicle vehicle
     * @return return total time by section
     */
    private double calculateEnergyConsumption(Section section) {

        //TODO
        return 0;
    }
    
    private void calculateSectionEnergyConsumption(){
        m_sectionEnergyConsumption = new ArrayList<>();
        for (Section s : m_fastestPath) {
            m_sectionEnergyConsumption.add(calculateEnergyConsumption(s));
        }
    }
    
    private double gravitationalForce(double targetThrottle, double targetRegime, int targetGear)
    {
        
        //estruturas de auxilio:
        ArrayList<Throttle> throttleList = null;
        ArrayList<Regime> regimeList = null;
        
        Throttle actualThrottle = null;
        Regime actualRegime = null;
        
                for(Throttle t : throttleList)
        {
            if(t.equals(targetThrottle))
            {
                actualThrottle = t;
            }
        }
        
        for(Regime r : regimeList)
        {
            if(r.equals(targetRegime))
            {
                actualRegime = r;
            }
        }
        
        CombustionVehicle cv = (CombustionVehicle) m_vehicle;
        
        
        
        
        
        //Variaveis necessárias para o calculo da Força Gravitacional:
        double torque = actualRegime.getTorque();
        double finalDriveRatio = m_vehicle.getFinalDriveRatio();
        

        
        
        
        

        
        
        
        return 0;
    }
    
    private void calculateSectionTime(){
        m_sectionTime=new ArrayList<>();
        
        //TODO
        
    }
    
    
    private void calculateSectionTollCosts(){
        m_sectionTollCosts=new ArrayList<>();
        for (Section s : m_fastestPath) {
            m_sectionTollCosts.add(s.getToll());
        }
    }

    private ResultStaticAnalysis constructResults() {
        
        ResultStaticAnalysis simResult = new ResultStaticAnalysis(m_originNode, m_destinyNode);
        simResult.setPath(m_fastestPath);
        simResult.setLength(m_fastestPathLength);
        simResult.setSectionTravelTime(m_sectionTime);
        simResult.setPathNodes(m_fastestPathNodes);
        simResult.setVehicle(m_vehicle);
        simResult.setEnergyConsumption(m_sectionEnergyConsumption);
        simResult.setTollCosts(m_sectionTollCosts);
        return simResult;
    }

    @Override
    public String toString() {
        return "Most efficient path";
    }
    
    
    
    
}
