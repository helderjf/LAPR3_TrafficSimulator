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

    @Override
    public ResultFastestPath bestPath(RoadNetwork roadNetwork, Junction originNode, Junction destinyNode, Vehicle vehicle) {
        m_graph = new Graph<>(true);
        graphConstruction(roadNetwork, vehicle);
        ArrayList<Section> fastestPath = new ArrayList<>();
        double fastestPathLength = GraphAlgorithms.getShortestPathLength(m_graph, originNode, destinyNode, fastestPath);

        ArrayList<Double> sectionTime = new ArrayList<>();
        for (Section s : fastestPath) {
            sectionTime.add(calculateTravelTime(s, vehicle));
        }

        return constructResults(originNode, destinyNode, fastestPath,fastestPathLength,sectionTime);
    }

    private void graphConstruction(RoadNetwork rn, Vehicle vehicle) {
        for (Section sec : rn.getSectionList()) {
            addConection(sec, vehicle);
        }
    }

    private void addConection(Section section, Vehicle vehicle) {
        if (section.getDirection().equals(SectionDirection.direct)) {
            m_graph.insertEdge(section.getBeginningNode(), section.getEndingNode(), section, calculateTravelTime(section, vehicle));

        } else if (section.getDirection().equals(SectionDirection.reverse)) {
            m_graph.insertEdge(section.getEndingNode(), section.getBeginningNode(), section, calculateTravelTime(section, vehicle));

        } else if (section.getDirection().equals(SectionDirection.bidirectional)) {
            m_graph.insertEdge(section.getBeginningNode(), section.getEndingNode(), section, calculateTravelTime(section, vehicle));
            m_graph.insertEdge(section.getEndingNode(), section.getBeginningNode(), section, calculateTravelTime(section, vehicle));
        }
    }

    /**
     * 
     * @param section section
     * @param vehicle vehicle
     * @return return total time by section
     */
    private double calculateTravelTime(Section section, Vehicle vehicle) {

        ArrayList<Segment> segmentList = section.getSegmentsList();
        double time = 0; //in seconds
        for (Segment it : segmentList) {
            double maxVel;
            SectionTypology type = section.getSectionType();
            double vehicleSpeedLimit = 500;
            if (vehicle.getVelocityLimit().get(String.valueOf(type)) != null) {
            double teste = vehicle.getVelocityLimit().get(String.valueOf(type));
                vehicleSpeedLimit = vehicle.getVelocityLimit().get(String.valueOf(type));
            }

            if (it.getMax_Velocity() <= vehicleSpeedLimit) {
                maxVel = it.getMax_Velocity();
            } else {
                maxVel = vehicleSpeedLimit;
            }
            double lenght = it.getLenght();
            time += lenght * 3600 / maxVel;
        }
        return time;

    }

    private ResultFastestPath constructResults(Junction origin, Junction destiny, ArrayList<Section> fastestPath, double fastestPathLength, ArrayList<Double> sectionTime) {
        ResultFastestPath simResult = new ResultFastestPath(origin, destiny);
        simResult.setPath(fastestPath);
        simResult.setLength(fastestPathLength);
        simResult.setSectionWeight(sectionTime);
        return simResult;
    }

    @Override
    public String toString() {
        return "Fastest path";
    }

}
