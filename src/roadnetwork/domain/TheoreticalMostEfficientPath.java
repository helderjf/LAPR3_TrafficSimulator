/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadnetwork.domain;

import graphutils.Graph;
import graphutils.GraphAlgorithms;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author André Pedrosa, Hélder Faria, José Miranda, Rubén Rosário
 */
public class TheoreticalMostEfficientPath implements BestPathAlgorithm{
    
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


    //@Override
    @Override
    public ResultStaticAnalysis getBestPathResults(RoadNetwork roadNetwork, Junction originNode, Junction destinyNode, Vehicle vehicle) {
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
            m_graph.insertEdge(section.getBeginningNode(), section.getEndingNode(), section, calculateSectionEnergyConsumption(section));

        } else if (section.getDirection().equals(SectionDirection.bidirectional)) {
            m_graph.insertEdge(section.getBeginningNode(), section.getEndingNode(), section, calculateSectionEnergyConsumption(section));
            m_graph.insertEdge(section.getEndingNode(), section.getBeginningNode(), section, calculateSectionEnergyConsumption(section));
        }
    }
    

    /**
     *
     * @param section section
     * @param vehicle vehicle
     * @return return total time by section
     */
    private double calculateSectionEnergyConsumption(Section section) {

        ArrayList<Segment> segmentList = section.getSegmentsList();
        
        double vehicleVelocity;
        double relativeVelocityWindInfluence;
        double gravitationalForce;
        double workCalculation = 0;
        
        for (Segment segment : segmentList) 
        {
            vehicleVelocity = vehicleVelocity(section,segment);
            relativeVelocityWindInfluence = relativeVelocityWindInfluence(section, vehicleVelocity);
            gravitationalForce = gravitationalForce(section,segment,relativeVelocityWindInfluence); 
            workCalculation += workCalculation(gravitationalForce,segment);
        }

        return workCalculation;
    }
    

    public double workCalculation(double gravitationalForce, Segment segment)
    {
        return gravitationalForce * segment.getLenght();
    }
    
    //07-01-2016
    private double gravitationalForce(Section section, Segment segment, double relativeVelocityWindInfluence)
    {
        
        //estruturas de auxilio:
//        ArrayList<Throttle> throttleList = null;
//        ArrayList<Regime> regimeList = null;
//        
//        Throttle actualThrottle = null;
//        Regime actualRegime = null;
//        
//        for(Throttle t : throttleList)
//        {
//            if(t.equals(throttle))
//            {
//                actualThrottle = t;
//            }
//        }
//        
//        for(Regime r : regimeList)
//        {
//            if(r.equals(regime))
//            {
//                actualRegime = r;
//            }
//        }
        
//        ArrayList<Double> actuaGearList = null;
        
        //Variaveis necessárias para o calculo da Força Gravitacional:
        //double torque = actualRegime.getTorque();
        //double finalDriveRatio = m_vehicle.getFinalDriveRatio();
        //double gearRatio = actuaGearList.get(gearIndex);
        //double radiusTire = m_vehicle.getRadiusOfTire();
        double rrc = m_vehicle.getRcc();
        double mass = m_vehicle.getMass();
        double dragCoefficient = m_vehicle.getDragCoefficient();
        double frontalArea = m_vehicle.getFrontalArea();
        
        double gravitationalForcePart1 = rrc * mass * gravity * Math.cos(segment.getSlope());
        double gravitationalForcePart2 = 0.5 * dragCoefficient * frontalArea * densityOfAir * Math.pow(relativeVelocityWindInfluence, 2);
        double gravitationalForcePart3 = mass * gravity * Math.sin(segment.getSlope());
        
        double gravitationalForce = gravitationalForcePart1 + gravitationalForcePart2 + gravitationalForcePart3;
        
        return gravitationalForce;
    }
    
    
    
    
    //The vehicle will travel at the maximum speed allowed in the road or for the vehicle
    private double vehicleVelocity(Section section, Segment segment) {

        double travelSpeed;
        SectionTypology type = section.getSectionType();

        //determin if the vehicle maximum speed for this section is inferior to the section speed limit
        if (m_vehicle.getVelocityLimits().containsKey(type)
                && m_vehicle.getVelocityLimit(type) < segment.getMax_Velocity()) {
            travelSpeed = m_vehicle.getVelocityLimit(type);
        } else {
            travelSpeed = segment.getMax_Velocity();
        }

        return travelSpeed;

    }
    
    //Influence of Wind Velocity
    private double relativeVelocityWindInfluence(Section section, double vehicleVelocity)
    {
        Wind w = section.getWind();
        double windSpeed = w.getVelocity();
        double windAngle = w.getAngle();
        
        return vehicleVelocity + windSpeed * Math.cos(windAngle);      
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

    @Override
    public ArrayList<PathParcel> getBestPath(RoadNetwork roadNetwork, Junction originNode, Junction destinyNode, Vehicle vehicle) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
    
}
