/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadnetwork.domain;

import graphutils.Graph;
import graphutils.GraphAlgorithms;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

/**
 *
 * @author André Pedrosa, Hélder Faria, José Miranda, Rubén Rosário
 */
public class FastestPathAlgorithm implements BestPathAlgorithm{

    SimulationResult m_simulationResult;
    Graph<Junction,Section> m_graph;
    
    public FastestPathAlgorithm(RoadNetwork rn, Vehicle vehicle){
        m_graph=new Graph<>(true);
        graphConstruction(rn,vehicle);
    }
    
    private void graphConstruction(RoadNetwork rn,Vehicle vehicle){
        for (Section sec : rn.getSectionList()) {
            addConection(sec,vehicle);
        }
    }
    
    private void addConection(Section section, Vehicle vehicle){
        if (section.getDirection().equals(Direction.direct)) {
            m_graph.insertEdge(section.getBeginningNode(), section.getEndingNode(), section, calculateTravelTime(section,vehicle));
            
        } else if (section.getDirection().equals(Direction.reverse)) {
            m_graph.insertEdge(section.getEndingNode(), section.getBeginningNode(), section, calculateTravelTime(section,vehicle));
            
        }else if (section.getDirection().equals(Direction.bidirectional)) {
             m_graph.insertEdge(section.getBeginningNode(), section.getEndingNode(), section, calculateTravelTime(section,vehicle));
             m_graph.insertEdge(section.getEndingNode(), section.getBeginningNode(), section, calculateTravelTime(section,vehicle));
        }
    
    }
    
    @Override
    public SimulationResult bestPath(RoadNetwork roadNetwork, Node originNode, Node destinyNode, ArrayList<Vehicle> vehicleList) {
        SimulationResult simResult=new SimulationResult();
        Deque<Node> fastestPath = new ArrayDeque<>();
        GraphAlgorithms.shortestPath(m_graph, originNode, destinyNode, fastestPath);
        
        //TODO
        //simResult.setPath(fastestPath);
        //set outros parametros da simulacao
        
        return simResult;
    }
    
    
    
    
    
    public double calculateTravelTime(Section section, Vehicle vehicle){
        

        ArrayList<Segment> segmentList = section.getSegmentsList();
        double time=0; //in seconds
        for(Segment it : segmentList){
            double maxVel;
            
            if(it.getMax_Velocity() <= vehicle.getMaximumSpeed()){
                maxVel = it.getMax_Velocity();
            }else{
                maxVel = vehicle.getMaximumSpeed();
            }
            double lenght = it.getLenght();
            time+=lenght*3600/maxVel;
        }
        return time;
        
    }
    
    
    
}
