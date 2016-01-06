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
public class FastestPathAlgorithm implements BestPathAlgorithm {

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

        //calculateSectionEnergyConsumption();
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
            m_graph.insertEdge(section.getBeginningNode(), section.getEndingNode(), section, calculateTravelTime(section));

        } else if (section.getDirection().equals(SectionDirection.bidirectional)) {
            m_graph.insertEdge(section.getBeginningNode(), section.getEndingNode(), section, calculateTravelTime(section));
            m_graph.insertEdge(section.getEndingNode(), section.getBeginningNode(), section, calculateTravelTime(section));
        }
    }

    /**
     *
     * @param section section
     * @param vehicle vehicle
     * @return return total time by section
     */
    private double calculateTravelTime(Section section) {

        ArrayList<Segment> segmentList = section.getSegmentsList();
        double time = 0; //in seconds
        
        for (Segment it : segmentList) {
            
            double lenght = it.getLenght();
            double travelSpeed;
            SectionTypology type = section.getSectionType();
            
            //determin if the vehicle maximum speed for this section is inferior to the section speed limit
            if (m_vehicle.getVelocityLimits().containsKey(String.valueOf(type))
                    && m_vehicle.getVelocityLimit(type)< it.getMax_Velocity()) {
                travelSpeed = m_vehicle.getVelocityLimit(type);
            }else{
                travelSpeed= it.getMax_Velocity();
            }
            
            time += lenght * 3600 / travelSpeed;
        }
        return time;

    }
    
//    private void calculateSectionEnergyConsumption(){
//        m_sectionEnergyConsumption = new ArrayList<>();
//        //TODO
//        
//    }
    
    private void calculateSectionTime(){
        m_sectionTime=new ArrayList<>();
        for (Section s : m_fastestPath) {
            m_sectionTime.add(calculateTravelTime(s));
        }
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
        return "Fastest path";
    }

}
