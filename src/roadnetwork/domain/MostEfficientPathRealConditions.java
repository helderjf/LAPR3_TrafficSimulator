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

/**
 *
 * @author josemiranda
 */
public class MostEfficientPathRealConditions implements BestPathAlgorithm{

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
    ArrayList<SimPathParcel> m_pathParcelList;
    
    private final double gravity = 9.81; // m^2
    private final double densityOfAir = 1.225; // kg/m3
    
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

        //calculateSectionEnergyConsumption();
        calculateSectionTime();
        calculateSectionTollCosts();

        return constructResults();
    }
    
    @Override
    public ArrayList<SimPathParcel> getBestPath(RoadNetwork roadNetwork, Junction originNode, Junction destinyNode, Vehicle vehicle){
        getBestPathResults(roadNetwork, originNode, destinyNode, vehicle);
        calculatePathParcelList();
        return m_pathParcelList;
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
    
    private double calculateSectionEnergyConsumption(Section section) {
        
        ArrayList<Segment> segmentList = section.getSegmentsList();
        
        double vehicleVelocity;
        double relativeVelocityWindInfluence;
        double gravitationalForce;
        double workCalculation = 0;
        
        if(m_vehicle instanceof CombustionVehicle)
        {
            CombustionVehicle combustionVehicle = (CombustionVehicle) m_vehicle;
            
            HashMap<Integer,Double> gearList = combustionVehicle.getGearList();
            int lastGear = gearList.size();

            
            for (Segment segment : segmentList) 
            {
                for(int gear = lastGear; gear > 0; gear++)
                {
                    
                }
            }
            
            
        }
        


        return 0;

    }
       
    
    private void calculateSectionsEnergyConsumption(){
        m_sectionEnergyConsumption = new ArrayList<>();
        for (Section s : m_fastestPath) {
            m_sectionEnergyConsumption.add(calculateSectionEnergyConsumption(s));
        }
    }
    
    ///////////////////****Physics Formulas ***********//////
    
    
    private double vehicleForce(int gearIndex, Throttle throttle, Regime regime, Section section, Segment segment, double relativeVelocityWindInfluence)
    {
        
        ArrayList<Throttle> throttleList = null;
        ArrayList<Regime> regimeList = null;
        
        Throttle actualThrottle = null;
        Regime actualRegime = null;
        
        for(Throttle t : throttleList)
        {
            if(t.equals(throttle))
            {
                actualThrottle = t;
            }
        }
        
        for(Regime r : regimeList)
        {
            if(r.equals(regime))
            {
                actualRegime = r;
            }
        }
        
        double vehicleForce = 0;
        
        if(m_vehicle instanceof CombustionVehicle)
        {
            CombustionVehicle combustionVehicle = (CombustionVehicle) m_vehicle;
            
                       
            HashMap<Integer,Double> actuaGearList = combustionVehicle.getGearList();
           
            double torque = actualRegime.getTorque();
            double finalDriveRatio = m_vehicle.getFinalDriveRatio();
            double gearRatio = actuaGearList.get(gearIndex);
            double radiusTire = m_vehicle.getRadiusOfTire();
           
            vehicleForce = (torque * finalDriveRatio * gearRatio) / radiusTire;
  
        }
        
        return vehicleForce;
    }
    

    private double resistanceForces(Section section, Segment segment, double relativeVelocityWindInfluence)
    {
        
        ArrayList<Double> actuaGearList = null;
        
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
    private double vehicleVelocity(int gearIndex, Regime regime, Section section, Segment segment) {

        double mediaRPM = (regime.getRPMHigh() + regime.getRPMLow()) / 2;
        
        double vehicleVelocity = 0;
        
        if(m_vehicle instanceof CombustionVehicle)
        {
            CombustionVehicle combustionVehicle = (CombustionVehicle) m_vehicle;
            
            HashMap<Integer,Double> actuaGearList = combustionVehicle.getGearList();

            double gearRatio = actuaGearList.get(gearIndex);
            
            vehicleVelocity = (2 * Math.PI * mediaRPM) / (60 * m_vehicle.getFinalDriveRatio() * gearRatio);

        }
 
        return vehicleVelocity;

    }
    
    //Influence of Wind Velocity
    private double relativeVelocityWindInfluence(Section section, double vehicleVelocity)
    {
        Wind w = section.getWind();
        double windSpeed = w.getVelocity();
        double windAngle = w.getAngle();
        
        return vehicleVelocity + windSpeed * Math.cos(windAngle);      
    }
    
    private double powerCalculation(int gearIndex, Throttle throttle, Regime regime)
    {
        ArrayList<Throttle> throttleList = null;
        ArrayList<Regime> regimeList = null;
        
        Throttle actualThrottle = null;
        Regime actualRegime = null;
        
        for(Throttle t : throttleList)
        {
            if(t.equals(throttle))
            {
                actualThrottle = t;
            }
        }
        
        for(Regime r : regimeList)
        {
            if(r.equals(regime))
            {
                actualRegime = r;
            }
        }
        
        
        
        double torque = actualRegime.getTorque();
        
        double mediaRPM = (regime.getRPMHigh() + regime.getRPMLow()) / 2;
        
        double power = 2 * Math.PI * torque * (mediaRPM / 60);
        
        return power;
    }

    /////*******//////
    


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
    
    private void calculatePathParcelList(){
        m_pathParcelList = new ArrayList<>();
        if (m_originNode.equals(m_fastestPath.get(0).getBeginningNode())) {
            for (Section section : m_fastestPath) {
                calculatePathParcel(section, section.getSegmentsList());
            }
        } else {
            for (Section section : m_fastestPath) {
                ArrayList<Segment> segmentsList=section.getSegmentsList();
                Collections.reverse(segmentsList);
                calculatePathParcel(section, segmentsList);
            }
        }
    }
    
    private void calculatePathParcel(Section section, ArrayList<Segment> segmentsList){
        double time = 0; //in seconds
        for (Segment it : segmentsList) {
            
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
            if (m_pathParcelList!=null) {
                SimPathParcel pp = new SimPathParcel();
                
                if (section.getDirection().equals(SectionDirection.unidirectional)) {
                    pp.setDirection(SimDirection.direct);
                } else{
                    if (m_originNode.equals(m_fastestPath.get(0).getBeginningNode())) {
                        pp.setDirection(SimDirection.direct);
                    } else{
                        pp.setDirection(SimDirection.reverse);
                    }
                }
                pp.setSection(section);
                pp.setSegment(it);
                pp.setTheoreticalExitTime(lenght * 3600 / travelSpeed);
                m_pathParcelList.add(pp);
            }
            
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
}
