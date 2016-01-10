/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadnetwork.domain;

import graphutils.Graph;
import graphutils.GraphAlgorithms;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author André Pedrosa, Hélder Faria, José Miranda, Rubén Rosário
 */
public class FastestPathAlgorithm implements BestPathAlgorithm {

    Graph<Junction, PathParcel> m_graph;
    RoadNetwork m_roadNetwork;
    Junction m_originNode;
    Junction m_destinyNode;
    Vehicle m_vehicle;
    ArrayList<PathParcel> m_fastestPath;
    ArrayList<Junction> m_fastestPathNodes;
    double m_fastestPathLength;
    ArrayList<SimPathParcel> m_simPathParcelList;
    
    
    //---Static Analysis
    @Override
    public ResultStaticAnalysis getBestPathResults(RoadNetwork roadNetwork, Junction originNode, Junction destinyNode, Vehicle vehicle) {
        m_graph = new Graph<>(true);
        m_roadNetwork=roadNetwork;
        m_originNode=originNode;
        m_destinyNode=destinyNode;
        m_vehicle=vehicle;
        m_fastestPath = new ArrayList<>();
        m_fastestPathNodes=new ArrayList<>();
        
        staticGraphConstruction(m_roadNetwork, m_vehicle);
        
        m_fastestPathLength = GraphAlgorithms.getShortestPathLength(
                m_graph, m_originNode, m_destinyNode, m_fastestPath, m_fastestPathNodes);

        //calculateSectionEnergyConsumption();
        calculateSectionTime();
        calculateSectionTollCosts();
        
        //Teste apagar
        calculateSimPathParcelList();
        //
        return constructResults();
    }
    

    //---Graph Construction---
    private void staticGraphConstruction(RoadNetwork rn, Vehicle vehicle) {
        for (Section sec : rn.getSectionList()) {
            StaticPathParcel spp = new StaticPathParcel(sec);
            addConection(spp);
        }
    }

    private void addConection(PathParcel pp) {
        Section section = pp.getSection();
        if (section.getDirection().equals(SectionDirection.unidirectional)) {
            pp.setDirection(SimDirection.direct);
            m_graph.insertEdge(section.getBeginningNode(), section.getEndingNode(), pp, calculateSectionTravelTime(pp.getSection()));

        } else if (section.getDirection().equals(SectionDirection.bidirectional)) {
            pp.setDirection(SimDirection.direct);
            m_graph.insertEdge(section.getBeginningNode(), section.getEndingNode(), pp, calculateSectionTravelTime(pp.getSection()));
            PathParcel ppRev = pp.createReversePP();
            ppRev.setDirection(SimDirection.reverse);
            m_graph.insertEdge(section.getEndingNode(), section.getBeginningNode(), ppRev, calculateSectionTravelTime(ppRev.getSection()));
        }
    }

    //---Data Calculation---
    /**
     *
     * @param section section
     * @param vehicle vehicle
     * @return return total time by section
     */
    private double calculateSectionTravelTime(Section section) {

        ArrayList<Segment> segmentList = section.getSegmentsList();
        double time = 0; //in seconds
        
        for (Segment it : segmentList) {
            time += calculateSegmentTravelTime(section, it);
        }
        return time;

    }
    
   private double calculateSegmentTravelTime(Section section, Segment segment){
       double time;
       double lenght = segment.getLenght();
            double travelSpeed;
            SectionTypology type = section.getSectionType();
            
            //determin if the vehicle maximum speed for this section is inferior to the section speed limit
            if (m_vehicle.getVelocityLimits().containsKey(type)
                    && m_vehicle.getVelocityLimit(type)< segment.getMax_Velocity()) {
                travelSpeed = m_vehicle.getVelocityLimit(type);
            }else{
                travelSpeed= segment.getMax_Velocity();
            }
            
            time = lenght * 3600 / travelSpeed;
            return time;
   }
    
//    private void calculateSectionEnergyConsumption(){
//        m_sectionEnergyConsumption = new ArrayList<>();
//        //TODO
//        
//    }
    
    private void calculateSectionTime(){
        for (PathParcel pp : m_fastestPath) {
            pp.setTheoreticalTravelTime(calculateSectionTravelTime(pp.getSection()));
        }
    }
    
    private void calculateSectionTollCosts(){
        for (PathParcel pp : m_fastestPath) {
            pp.setTollCosts(pp.getSection().getToll());
        }
    }
    
    //---Static Analysis Results---
    private ResultStaticAnalysis constructResults() {
        
        ResultStaticAnalysis simResult = new ResultStaticAnalysis(m_originNode, m_destinyNode);
        simResult.setPath(m_fastestPath);
        simResult.setLength(m_fastestPathLength);
        simResult.setPathNodes(m_fastestPathNodes);
        simResult.setVehicle(m_vehicle);
        return simResult;
    }
    
    
    //---Simulation Path Parcel calculations---
    @Override
    public ArrayList<SimPathParcel> getBestPath(RoadNetwork roadNetwork, Junction originNode, Junction destinyNode, Vehicle vehicle){
        getBestPathResults(roadNetwork, originNode, destinyNode, vehicle);
        calculateSimPathParcelList();
        return m_simPathParcelList;
    }
    
    private void calculateSimPathParcelList(){
        m_simPathParcelList = new ArrayList<>();
        if (m_originNode.equals(m_fastestPath.get(0).getSection().getBeginningNode())) {
            for (PathParcel pp : m_fastestPath) {
                calculateSimPathParcel(pp.getSection(), pp.getSection().getSegmentsList());
            }
        } else {
            for (PathParcel pp : m_fastestPath) {
                ArrayList<Segment> segmentsList=pp.getSection().getSegmentsList();
                Collections.reverse(segmentsList);
                calculateSimPathParcel(pp.getSection(), segmentsList);
            }
        }
    }
   
    private void calculateSimPathParcel(Section section, ArrayList<Segment> segmentsList) {
        for (Segment it : segmentsList) {
            
            SimPathParcel pp = new SimPathParcel(section);

            if (section.getDirection().equals(SectionDirection.unidirectional)) {
                pp.setDirection(SimDirection.direct);
            } else {
                if (m_originNode.equals(m_fastestPath.get(0).getSection().getBeginningNode())) {
                    pp.setDirection(SimDirection.direct);
                } else {
                    pp.setDirection(SimDirection.reverse);
                }
            }
            //set the segment
            pp.setSegment(it);
            //set the segment's theoretical travel time 
            pp.setTheoreticalTravelTime(calculateSegmentTravelTime(section, it));
            //set the segment's toll costs
            pp.setTollCosts(section.getToll()/segmentsList.size());
            //set the segment's energy consumption
            //pp.setTheoreticalEnergyConsumption();
            
            //adds the SimPathParcel to the list
            m_simPathParcelList.add(pp);
        }
    }


    @Override
    public String toString() {
        return "Fastest path";
    }

}
