/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadnetwork.domain;

import graphutils.Graph;
import java.util.ArrayList;

/**
 *
 * @author André Pedrosa, Hélder Faria, José Miranda, Rubén Rosário
 */
public class FastestPathAlgorithm implements BestPathAlgorithm{

    SimulationResult m_simulationResult;
    Graph<Node,Section> m_graph;
    
    public FastestPathAlgorithm(RoadNetwork rn){
        m_graph=new Graph<>(true);
        graphConstruction(rn);
    }
    
    private void graphConstruction(RoadNetwork rn){
        for (Section sec : rn.getSectionList()) {
            addConection(sec);
        }
    }
    
    private void addConection(Section section){
        if (section.getDirection().equals(Direction.direct)) {
            m_graph.insertEdge(section.getBeginningNode(), section.getEndingNode(), section, calculateTravelTime(section));
            
        } else if (section.getDirection().equals(Direction.reverse)) {
            m_graph.insertEdge(section.getEndingNode(), section.getBeginningNode(), section, calculateTravelTime(section));
            
        }else if (section.getDirection().equals(Direction.bidirectional)) {
             m_graph.insertEdge(section.getBeginningNode(), section.getEndingNode(), section, calculateTravelTime(section));
             m_graph.insertEdge(section.getEndingNode(), section.getBeginningNode(), section, calculateTravelTime(section));
        }
    
    }
    
    @Override
    public SimulationResult bestPath(RoadNetwork roadNetwork, Node originNode, Node destinyNode, ArrayList<Vehicle> vehicleList) {
        
        return null;
    }
    
    
    
    
    
    private double calculateTravelTime(Section section){
        /*
        ArrayList<Segment> segmentList = section.getSegments();
        double time=0; //in seconds
        for(Segment it : segmentList){
            double maxVel = it.getMax_Velocity();
            double lenght = it.getLenght();
            time+=lenght*3600/maxVel;
        }
        return time;
        */
        return 0;
    }
    
    
    
}
