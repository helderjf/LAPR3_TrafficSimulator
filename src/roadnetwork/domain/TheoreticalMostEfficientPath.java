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
    
    Graph<Junction, StaticPathParcel> m_graph;
    RoadNetwork m_roadNetwork;
    Junction m_originNode;
    Junction m_destinyNode;
    Vehicle m_vehicle;
    ArrayList<StaticPathParcel> m_bestPath;
    ArrayList<Junction> m_bestPathNodes;
    double m_bestPathLength;
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
        m_bestPath = new ArrayList<>();
        m_bestPathNodes=new ArrayList<>();
        
        graphConstruction(m_roadNetwork, m_vehicle);
        
        m_bestPathLength = GraphAlgorithms.getShortestPathLength(m_graph, m_originNode, m_destinyNode, m_bestPath, m_bestPathNodes);

        calculateSectionTime();
        calculateSectionsEnergyConsumption();
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
            StaticPathParcel sppDirect=new StaticPathParcel(section, SimDirection.direct);
            m_graph.insertEdge(section.getBeginningNode(), section.getEndingNode(), sppDirect, calculateSectionEnergyConsumption(sppDirect));

        } else if (section.getDirection().equals(SectionDirection.bidirectional)) {
            StaticPathParcel sppDirect=new StaticPathParcel(section, SimDirection.direct);
            m_graph.insertEdge(section.getBeginningNode(), section.getEndingNode(), sppDirect, calculateSectionEnergyConsumption(sppDirect));
            
            StaticPathParcel sppReverse=new StaticPathParcel(section, SimDirection.reverse);
            m_graph.insertEdge(section.getEndingNode(), section.getBeginningNode(), sppReverse, calculateSectionEnergyConsumption(sppReverse));
        }
    }
    

    /**
     *
     * @param section section
     * @param vehicle vehicle
     * @return return total time by section
     */
    private double calculateSectionEnergyConsumption(StaticPathParcel spParcel) {

        ArrayList<Segment> segmentList = spParcel.getSection().getSegmentsList();
        
        double vehicleVelocity;
        double relativeVelocityWindInfluence;
        double resistanceForce;
        double work = 0;
        
        for (Segment segment : segmentList) 
        {
            vehicleVelocity = vehicleVelocity(spParcel.getSection(),segment);
            relativeVelocityWindInfluence = relativeVelocityWindInfluence(spParcel.getSection(), vehicleVelocity);
            resistanceForce = resistanceForce(section,segment,relativeVelocityWindInfluence, direction); 
            work += workCalculation(resistanceForce,segment);
        }

        return work;
    }
    

    private double workCalculation(double resistanceForce, Segment segment)
    {
        return resistanceForce * segment.getLenght();
    }
    
    
    private double resistanceForce(Section section, Segment segment, double relativeVelocityWindInfluence, boolean direction){
        double rrc = m_vehicle.getRcc();
        double mass = m_vehicle.getMass();
        double dragCoefficient = m_vehicle.getDragCoefficient();
        double frontalArea = m_vehicle.getFrontalArea();
        double f1;
        double f2;
        double f3;
        
        if (direction==true) {
            f1 = rrc * mass * gravity * Math.cos(segment.getSlope());
            f3 = mass * gravity * Math.sin(segment.getSlope());
        } else{
            f1 = rrc * mass * gravity * Math.cos(-1*segment.getSlope());
            f3 = mass * gravity * Math.sin(-1*segment.getSlope());
        }
        f2 = 0.5 * dragCoefficient * frontalArea * densityOfAir * Math.pow(relativeVelocityWindInfluence, 2);
        
        double resistanceForce = f1 + f2 + f3;
        
        return resistanceForce;
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
        for (Section s : m_bestPath) {
            m_sectionTime.add(calculateTravelTime(s));
        }
    }
    
    private double calculateTravelTime(Section section){
        ArrayList<Segment> segmentList = section.getSegmentsList();
        double time = 0; //in seconds
        
        for (Segment it : segmentList) {
            
            double lenght = it.getLenght();
            double travelSpeed;
            SectionTypology type = section.getSectionType();
            
            //determin if the vehicle maximum speed for this section is inferior to the section speed limit
            if (m_vehicle.getVelocityLimits().containsKey(type)
                    && m_vehicle.getVelocityLimit(type)< it.getMax_Velocity()) {
                travelSpeed = m_vehicle.getVelocityLimit(type);
            }else{
                travelSpeed= it.getMax_Velocity();
            }
            
            time += lenght * 3600 / travelSpeed;
        }
        return time;
    }
    
    private void calculateSectionsEnergyConsumption(){
        m_sectionEnergyConsumption=new ArrayList<>();
        for (Section sec : m_bestPath) {
        }
    }
    
    private void calculateSectionTollCosts(){
        m_sectionTollCosts=new ArrayList<>();
        for (Section s : m_bestPath) {
            m_sectionTollCosts.add(s.getToll());
        }
    }

    private ResultStaticAnalysis constructResults() {
        
        ResultStaticAnalysis simResult = new ResultStaticAnalysis(m_originNode, m_destinyNode);
        simResult.setPath(m_bestPath);
        simResult.setLength(m_bestPathLength);
        simResult.setSectionTravelTime(m_sectionTime);
        simResult.setPathNodes(m_bestPathNodes);
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
