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

/**
 *
 * @author André Pedrosa, Hélder Faria, José Miranda, Rubén Rosário
 */
public class TheoreticalMostEfficientPath implements BestPathAlgorithm{
    
    Graph<Junction, PathParcel> m_graph;
    RoadNetwork m_roadNetwork;
    Junction m_originNode;
    Junction m_destinyNode;
    Vehicle m_vehicle;
    ArrayList<PathParcel> m_bestPath;
    ArrayList<SimPathParcel> m_simPathParcelList;
    
    private final double gravity = 9.81; // m^2
    private final double densityOfAir = 1.225; // kg/m3


    //@Override
    @Override
    public ArrayList<PathParcel> getBestPathResults(RoadNetwork roadNetwork, Junction originNode, Junction destinyNode, Vehicle vehicle) {
        m_graph = new Graph<>(true);
        m_roadNetwork=roadNetwork;
        m_originNode=originNode;
        m_destinyNode=destinyNode;
        m_vehicle=vehicle;
        m_bestPath = new ArrayList<>();
        
        staticGraphConstruction(m_roadNetwork, m_vehicle);
        
        GraphAlgorithms.calculateShortestPath(m_graph, m_originNode, m_destinyNode, m_bestPath);

        calculateStaticData();
        
        return m_bestPath;
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
            m_graph.insertEdge(section.getBeginningNode(), section.getEndingNode(), pp, calculateSectionWork(pp));

        } else if (section.getDirection().equals(SectionDirection.bidirectional)) {
            pp.setDirection(SimDirection.direct);
            m_graph.insertEdge(section.getBeginningNode(), section.getEndingNode(), pp, calculateSectionWork(pp));
            
            PathParcel ppRev = pp.createReversePP();
            ppRev.setDirection(SimDirection.reverse);
            m_graph.insertEdge(section.getEndingNode(), section.getBeginningNode(), ppRev, calculateSectionWork(ppRev));
        }
    }
    
    //---Data Calculation---
    private void calculateStaticData(){
        for (PathParcel pp : m_bestPath) {
            //Section's Travel Time
            pp.setTheoreticalTravelTime(calculateSectionTravelTime(pp.getSection()));
            //Section's Toll Costs
            pp.setTollCosts(calculateSectionTollCosts(pp.getSection()));
            //Section's Energy Consumption
            pp.setTheoreticalEnergyConsumption(calculateSectionEnergyConsumption(pp));
        }
    }
    
    private double calculateSectionWork(PathParcel pp){
        double sectionWork=0;
        for (Segment segment : pp.getSection().getSegmentsList()) {
            double vehicleVelocity = travelSpeed(pp.getSection(),segment);
            sectionWork=calculateSegmentWork(pp, segment, vehicleVelocity);
        }
        return sectionWork;
    }
    
    private double calculateSegmentWork(PathParcel pp,Segment segment, double vehicleVelocity){
        double relativeVelocityWindInfluence = relativeVelocityWindInfluence(pp, vehicleVelocity);
        double resistanceForce = resistanceForce(pp,segment,relativeVelocityWindInfluence); 
        double work = resistanceForce * segment.getLenght();
        return work;
    }
    

    private double calculateSectionEnergyConsumption(PathParcel pp){
        double sectionEnergyConsumption=0;
        for (Segment segment : pp.getSection().getSegmentsList()) {
            double vehicleVelocity = travelSpeed(pp.getSection(),segment);
            sectionEnergyConsumption+=calculateSegmentEnergyConsumption(pp, segment, vehicleVelocity);
        }
        return sectionEnergyConsumption;
    }
    
    private double calculateSegmentEnergyConsumption(PathParcel pp,Segment segment, double vehicleVelocity){
        double relativeVelocityWindInfluence = relativeVelocityWindInfluence(pp, vehicleVelocity);
        double resistanceForce = resistanceForce(pp,segment,relativeVelocityWindInfluence); 
        double work = resistanceForce * segment.getLenght();
        double segmentEnergyConsumption=calculateSegmentWork(pp,segment, vehicleVelocity);
        
        if (m_vehicle instanceof CombustionVehicle) {
            CombustionVehicle combustionVehicle = (CombustionVehicle)m_vehicle;
            if (segmentEnergyConsumption<0) {
                segmentEnergyConsumption=0;
            } else if (combustionVehicle.getFuel().equalsIgnoreCase("Gasoline")) {
                //segmentEnergyConsumption in grams
                segmentEnergyConsumption=segmentEnergyConsumption/(combustionVehicle.getGasolineEnergy());
            }else if (combustionVehicle.getFuel().equalsIgnoreCase("Diesel")) {
                //segmentEnergyConsumption in grams
                segmentEnergyConsumption=segmentEnergyConsumption/(combustionVehicle.getDieselEnergy());
            }
        
        } else if ((m_vehicle instanceof ElectricVehicle) && segmentEnergyConsumption<0) {
            ElectricVehicle electricVehicle = (ElectricVehicle)m_vehicle;
            segmentEnergyConsumption= segmentEnergyConsumption * electricVehicle.getEnergyRegenerationRatio();
        
        }else if ((m_vehicle instanceof HybridVehicle) && segmentEnergyConsumption<0) {
            HybridVehicle hybridVehicle = (HybridVehicle)m_vehicle;
            segmentEnergyConsumption=segmentEnergyConsumption * hybridVehicle.getEnergyRegenerationRatio();
        }
        return segmentEnergyConsumption;
    }
    

    private double resistanceForce(PathParcel pp, Segment segment, double relativeVelocityWindInfluence){
        double rrc = m_vehicle.getRrc();
        double mass = m_vehicle.getMass()+m_vehicle.getLoad();
        double dragCoefficient = m_vehicle.getDragCoefficient();
        double frontalArea = m_vehicle.getFrontalArea();
        double f1;
        double f2;
        double f3;
        
        if (pp.getDirection().equals(SimDirection.direct)) {
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

    
    //Influence of Wind Velocity
    private double relativeVelocityWindInfluence(PathParcel pp, double vehicleVelocity)
    {
        Wind w = pp.getSection().getWind();
        double windSpeed = w.getVelocity();
        double windAngle = w.getAngle();
        double relativeVelocity;

        if (pp.getDirection().equals(SimDirection.direct)) {
            relativeVelocity = vehicleVelocity + windSpeed * Math.cos(windAngle);
        } else {
            relativeVelocity = vehicleVelocity - windSpeed * Math.cos(windAngle);
        }
        
        if ((pp.getDirection().equals(SimDirection.direct) && Math.cos(windAngle)<0 
                || pp.getDirection().equals(SimDirection.reverse) && Math.cos(windAngle)>0)
                &&relativeVelocity<=30) {
            relativeVelocity=0;
        }
        return relativeVelocity;
        
    }
    
    private double calculateSectionTravelTime(Section section) {

        ArrayList<Segment> segmentList = section.getSegmentsList();
        double time = 0; //in seconds
        
        for (Segment it : segmentList) {
            time += calculateSegmentTravelTime(section, it);
        }
        return time;
    }
    
   private double calculateSegmentTravelTime(Section section, Segment segment) {
        double time;
        double lenght = segment.getLenght();
        double travelSpeed = travelSpeed(section, segment);

        time = lenght / travelSpeed;
        return time;
    }

    private double travelSpeed(Section section, Segment segment) {
        //determin if the vehicle maximum speed for this section is inferior to the section speed limit
        double travelSpeed;
        SectionTypology type = section.getTypology();
        if (m_vehicle.getVelocityLimits().containsKey(type)
                && m_vehicle.getVelocityLimit(type) < segment.getMax_Velocity()) {
            travelSpeed = m_vehicle.getVelocityLimit(type);
        } else {
            travelSpeed = segment.getMax_Velocity();
        }
        return travelSpeed;
    }
    
    private void calculateSectionsEnergyConsumption(){
        for (PathParcel sec : m_bestPath) {
        }
    }
    
    private double calculateSectionTollCosts(Section section){
        return section.getToll();
    }

    @Override
    public String toString() {
        return "Theoretical Most Efficient Path";
    }
    

    //---Simulation Path Parcel calculations---
    @Override
    public ArrayList<SimPathParcel> getBestPath(RoadNetwork roadNetwork, Junction originNode, Junction destinyNode, Vehicle vehicle) {
        getBestPathResults(roadNetwork, originNode, destinyNode, vehicle);
        calculatePathParcelList();
        return m_simPathParcelList;
    }

    private void calculatePathParcelList() {
        m_simPathParcelList = new ArrayList<>();
        if (m_originNode.equals(m_bestPath.get(0).getSection().getBeginningNode())) {
        for (PathParcel pp : m_bestPath) {
            calculatePathParcel(pp.getSection(), pp.getSection().getSegmentsList());
        }
        } else{
            for (PathParcel pp : m_bestPath) {
            ArrayList<Segment> segmentsList=pp.getSection().getSegmentsList();
                Collections.reverse(segmentsList);
                calculatePathParcel(pp.getSection(), segmentsList);
            }
        }
    }

    private void calculatePathParcel(Section section, ArrayList<Segment> segmentsList) {
        for (Segment it : segmentsList) {
            
            SimPathParcel pp = new SimPathParcel(section);
            
            //set the segment's direction
            if (section.getDirection().equals(SectionDirection.unidirectional)) {
                pp.setDirection(SimDirection.direct);
            } else {
                if (m_originNode.equals(m_bestPath.get(0).getSection().getBeginningNode())) {
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
            //get vehicle's velocity on the current segment
            double vehicleVelocity=travelSpeed(section, it);
            //set the segment's energy consumption
            pp.setTheoreticalEnergyConsumption(calculateSegmentEnergyConsumption(pp, it, vehicleVelocity));
            
            //adds the SimPathParcel to the list
            m_simPathParcelList.add(pp);
        }
    }
   
}
