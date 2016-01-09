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
import java.util.List;

/**
 *
 * @author josemiranda
 */
public class MostEfficientPathRealConditions implements BestPathAlgorithm{

    Graph<Junction, PathParcel> m_graph;
    RoadNetwork m_roadNetwork;
    Junction m_originNode;
    Junction m_destinyNode;
    Vehicle m_vehicle;
    ArrayList<PathParcel> m_bestPath;
    ArrayList<Junction> m_bestPathNodes;
    double m_bestPathLength;
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
        m_bestPath = new ArrayList<>();
        m_bestPathNodes=new ArrayList<>();
        
        staticGraphConstruction(m_roadNetwork, m_vehicle);
        
        m_bestPathLength = GraphAlgorithms.getShortestPathLength(m_graph, m_originNode, m_destinyNode, m_bestPath, m_bestPathNodes);

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
            m_graph.insertEdge(section.getBeginningNode(), section.getEndingNode(), pp, calculateSectionEnergyConsumption(pp));

        } else if (section.getDirection().equals(SectionDirection.bidirectional)) {
            pp.setDirection(SimDirection.direct);
            m_graph.insertEdge(section.getBeginningNode(), section.getEndingNode(), pp, calculateSectionEnergyConsumption(pp));
            
            PathParcel ppRev = pp.createReversePP();
            ppRev.setDirection(SimDirection.reverse);
            m_graph.insertEdge(section.getEndingNode(), section.getBeginningNode(), ppRev, calculateSectionEnergyConsumption(ppRev));
        }
    }
    
    private double calculateSectionEnergyConsumption(PathParcel pathParcel) {

        ArrayList<Segment> segmentList = pathParcel.getSection().getSegmentsList();

        double vehicleVelocity = 0;
        double relativeVelocityWindInfluence = 0;
        double resistanceForce = 0;
        double vehicleForce = 0;
        double consumption = 0;

        if (m_vehicle instanceof CombustionVehicle) {
            CombustionVehicle combustionVehicle = (CombustionVehicle) m_vehicle;

            List<EngineEfficiency> engineEfficiencyList = combustionVehicle.getEngineEfficiency();

            EngineEfficiency engineEfficiency = new EngineEfficiency();
            boolean forceFlag = false;
            //vai percorrer todas as performances ordenadas de motor calculadas para cada Throttle Ratio e Gear Ratio

            for (Segment segment : segmentList) {
                for (EngineEfficiency engineEfficiencyTemporary : engineEfficiencyList) {
                    vehicleVelocity = vehicleVelocity(engineEfficiencyTemporary);
                    relativeVelocityWindInfluence = relativeVelocityWindInfluence(pathParcel.getSection(), vehicleVelocity);
                    resistanceForce = resistanceForces(segment, relativeVelocityWindInfluence);
                    vehicleForce = vehicleForces(engineEfficiencyTemporary);

                    //se verdadeiro o veiculo anda
                    if (vehicleForce > resistanceForce) {
                        forceFlag = true;
                        engineEfficiency=engineEfficiencyTemporary;
                        break;
                    }
                }
                if (forceFlag = false) {
                    consumption = Double.MAX_VALUE;
                } else {

                    consumption += segmentPowerCalculation(engineEfficiency)*
                            3.6*engineEfficiency.getM_sfc()*calculateTravelTime(pathParcel.getSection(),vehicleVelocity);
                    
                }
            }
        }
        
        return consumption;
    }
    
    private double calculateTravelTime(Section section, double vehicleVelocity){
        ArrayList<Segment> segmentList = section.getSegmentsList();
        double time = 0; //in seconds
        
        for (Segment it : segmentList) {
            
            double lenght = it.getLenght();
            SectionTypology type = section.getSectionType();
            
            time += lenght * 3600 / relativeVelocityWindInfluence(section,vehicleVelocity);
        }
        return time;
    }
       
    
    private void calculateSectionsEnergyConsumption(){
        m_sectionEnergyConsumption = new ArrayList<>();
        for (PathParcel pp : m_bestPath) {
            m_sectionEnergyConsumption.add(calculateSectionEnergyConsumption(pp));
        }
    }
    
    ///////////////////****Physics Formulas ***********//////
    
    
    //private double vehicleForces(int gearIndex, Throttle throttle, Regime regime, Section section, Segment segment, double relativeVelocityWindInfluence)
    private double vehicleForces(EngineEfficiency engineEfficiency)
    {

        double vehicleForce = 0;
        
        if(m_vehicle instanceof CombustionVehicle)
        {
            CombustionVehicle combustionVehicle = (CombustionVehicle) m_vehicle;        
           
            double torque = engineEfficiency.getTorque();
            double finalDriveRatio = m_vehicle.getFinalDriveRatio();
            double gearRatio = engineEfficiency.getGearRatio();
            double radiusTire = m_vehicle.getRadiusOfTire();
           
            vehicleForce = (torque * finalDriveRatio * gearRatio) / radiusTire;
  
        }
        
        return vehicleForce;
    }
    

    private double resistanceForces(Segment segment, double relativeVelocityWindInfluence)
    {

        double rrc = m_vehicle.getRcc();
        double mass = m_vehicle.getMass();
        double dragCoefficient = m_vehicle.getDragCoefficient();
        double frontalArea = m_vehicle.getFrontalArea();
        
        double resistanceForcesPart1 = rrc * mass * gravity * Math.cos(segment.getSlope());
        double resistanceForcesPart2 = 0.5 * dragCoefficient * frontalArea * densityOfAir * Math.pow(relativeVelocityWindInfluence, 2);
        double resistanceForcesPart3 = mass * gravity * Math.sin(segment.getSlope());
        
        double resistanceForces = resistanceForcesPart1 + resistanceForcesPart2 + resistanceForcesPart3;
        
        return resistanceForces;
    }
    
    
    //The vehicle will travel at the maximum speed allowed in the road or for the vehicle
    private double vehicleVelocity(EngineEfficiency engineEfficiency) {

        double mediaRPM = (engineEfficiency.getM_rpmHigh() + engineEfficiency.getM_rpmLow()) / 2;
        
        double vehicleVelocity = 0;
        
        if(m_vehicle instanceof CombustionVehicle)
        {
            CombustionVehicle combustionVehicle = (CombustionVehicle) m_vehicle;
            
            HashMap<Integer,Double> actualGearList = combustionVehicle.getGearList();

            double gearRatio = actualGearList.get(engineEfficiency.getGear());
            
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
    
    private double workCalculation(double resistanceForce, Segment segment)
    {
        return resistanceForce * segment.getLenght();
    }
    
    private double segmentPowerCalculation(EngineEfficiency engineEfficiency)
    {

        double torque = engineEfficiency.getTorque();
        
        double mediaRPM = (engineEfficiency.getM_rpmHigh() + engineEfficiency.getM_rpmLow()) / 2;
        
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
        for (PathParcel pp : m_bestPath) {
            m_sectionTollCosts.add(pp.getSection().getToll());
        }
    }
    
    private void calculatePathParcelList(){
        m_pathParcelList = new ArrayList<>();
        if (m_originNode.equals(m_bestPath.get(0).getSection().getBeginningNode())) {
            for (PathParcel pp : m_bestPath) {
                calculatePathParcel(pp.getSection(), pp.getSection().getSegmentsList());
            }
        } else {
            for (PathParcel pp : m_bestPath) {
                ArrayList<Segment> segmentsList=pp.getSection().getSegmentsList();
                Collections.reverse(segmentsList);
                calculatePathParcel(pp.getSection(), segmentsList);
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
            if (m_vehicle.getVelocityLimits().containsKey((type))
                    && m_vehicle.getVelocityLimit(type)< it.getMax_Velocity()) {
                travelSpeed = m_vehicle.getVelocityLimit(type);
            }else{
                travelSpeed= it.getMax_Velocity();
            }
            
            time += lenght * 3600 / travelSpeed;
            if (m_pathParcelList!=null) {
                SimPathParcel pp = new SimPathParcel(section);
                
                if (section.getDirection().equals(SectionDirection.unidirectional)) {
                    pp.setDirection(SimDirection.direct);
                } else{
                    if (m_originNode.equals(m_bestPath.get(0).getSection().getBeginningNode())) {
                        pp.setDirection(SimDirection.direct);
                    } else{
                        pp.setDirection(SimDirection.reverse);
                    }
                }
                pp.setSection(section);
                pp.setSegment(it);
                pp.setTheoreticalTravelTime(lenght * 3600 / travelSpeed);
                m_pathParcelList.add(pp);
            }
            
        }
    }

    private ResultStaticAnalysis constructResults() {
        
        ResultStaticAnalysis simResult = new ResultStaticAnalysis(m_originNode, m_destinyNode);
        simResult.setPath(m_bestPath);
        simResult.setLength(m_bestPathLength);
        simResult.setPathNodes(m_bestPathNodes);
        simResult.setVehicle(m_vehicle);
        simResult.setEnergyConsumption(m_sectionEnergyConsumption);
        return simResult;
    }

    @Override
    public String toString() {
        return "Most Efficient Path in Real Conditions";
    }
    
    
}
