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
public class MostEfficientPathRealConditions implements BestPathAlgorithm {

    Graph<Junction, PathParcel> m_graph;
    RoadNetwork m_roadNetwork;
    Junction m_originNode;
    Junction m_destinyNode;
    Vehicle m_vehicle;
    ArrayList<PathParcel> m_bestPath;
    ArrayList<Junction> m_bestPathNodes;
    double m_bestPathLength;
    ArrayList<SimPathParcel> m_simPathParcelList;

    private final double gravity = 9.81; // m^2
    private final double densityOfAir = 1.225; // kg/m3

    @Override
    public ResultStaticAnalysis getBestPathResults(RoadNetwork roadNetwork, Junction originNode, Junction destinyNode, Vehicle vehicle) {
        m_graph = new Graph<>(true);
        m_roadNetwork = roadNetwork;
        m_originNode = originNode;
        m_destinyNode = destinyNode;
        m_vehicle = vehicle;
        m_bestPath = new ArrayList<>();
        m_bestPathNodes = new ArrayList<>();

        staticGraphConstruction(m_roadNetwork, m_vehicle);

        m_bestPathLength = GraphAlgorithms.getShortestPathLength(m_graph, m_originNode, m_destinyNode, m_bestPath, m_bestPathNodes);

        claculateStaticData();

        return constructResults();
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

    //---Data Calculation---
    private void claculateStaticData() {
        for (PathParcel pp : m_bestPath) {
            //Section's Travel Time
            pp.setTheoreticalTravelTime(calculateSectionTravelTime(pp.getSection()));
            //Section's Toll Costs
            pp.setTollCosts(calculateSectionTollCosts(pp.getSection()));
            //Section's Energy Consumption
            pp.setTheoreticalEnergyConsumption(calculateSectionEnergyConsumption(pp));
        }
    }

    private double calculateSectionEnergyConsumption(PathParcel pp) {

        ArrayList<Segment> segmentList = pp.getSection().getSegmentsList();

        double consumption = 0;

        double sectionConsuption = 0;
        List<EngineEfficiency> engineEfficiencyList=m_vehicle.getEngineEfficiency();
        
        for (Segment segment : segmentList) {
            sectionConsuption += calculateSegmentEnergyConsumption(engineEfficiencyList, pp, segment);
        }

        return sectionConsuption;
    }

    private double calculateSegmentEnergyConsumption(List<EngineEfficiency> engineEfficiencyList, PathParcel pp, Segment segment) {
        double travelSpeed;
        double relativeVelocityWindInfluence;
        double resistanceForce;
        double vehicleForce=0;
        double segmentTotalForce;
        double segmentEnergyConsumption = 0;
        EngineEfficiency engineEfficiency=null;
        double rpm=0;
        //vai percorrer todas as performances ordenadas de motor calculadas para cada Throttle Ratio e Gear Ratio
        for (EngineEfficiency engineEfficiencyTemporary : engineEfficiencyList) {
            travelSpeed = travelSpeed(pp.getSection(), segment);
            relativeVelocityWindInfluence = relativeVelocityWindInfluence(pp, travelSpeed);
            
            resistanceForce = resistanceForces(pp, segment, relativeVelocityWindInfluence);
            rpm = rpmCalculation(engineEfficiencyTemporary, travelSpeed);
            vehicleForce = vehicleForces(engineEfficiencyTemporary, travelSpeed, rpm);
            if (vehicleForce > resistanceForce) {
                engineEfficiency=engineEfficiencyTemporary;
                break;
            }
        }
        
        if (m_vehicle instanceof CombustionVehicle) {
            segmentEnergyConsumption=calculateSegmentEnergyConsumptionCombustionVehicle(vehicleForce, engineEfficiency, segment);
        
        } else if (m_vehicle instanceof ElectricVehicle) {
            segmentEnergyConsumption=calculateSegmentEnergyConsumptionElectricVehicle(vehicleForce, engineEfficiency, segment, rpm);
        
        }else if ((m_vehicle instanceof HybridVehicle)) {
            segmentEnergyConsumption=calculateSegmentEnergyConsumptionHybridVehicle(vehicleForce, engineEfficiency, segment, rpm);
        }
        return segmentEnergyConsumption;
    }
    
    private double calculateSegmentEnergyConsumptionCombustionVehicle(double vehicleForce, EngineEfficiency engineEfficiency, Segment segment) {
        double segmentEnergyConsumption;
        if (vehicleForce<0) {
            segmentEnergyConsumption=0;
        } else{
            segmentEnergyConsumption = vehicleForce * engineEfficiency.getM_sfc() / 3600000 * segment.getLenght();
        }
        return segmentEnergyConsumption;
    }
    
    private double calculateSegmentEnergyConsumptionElectricVehicle(double vehicleForce, EngineEfficiency engineEfficiency, Segment segment, double rpm){
        double segmentEnergyConsumption;
        ElectricVehicle electricVehicle = (ElectricVehicle)m_vehicle;
        if (vehicleForce<0) {
            //ToDo calcular força maxima do motor
            double vehicleMaxForce=calculateMaxForce(engineEfficiency, rpm);
            if (vehicleForce>vehicleMaxForce) {
                vehicleForce=vehicleMaxForce;
            }
            segmentEnergyConsumption= vehicleForce * electricVehicle.getEnergyRegenerationRatio()*segment.getLenght();
        } else {
            segmentEnergyConsumption=vehicleForce * segment.getLenght();
        }
        return segmentEnergyConsumption;
    }
    
    private double calculateSegmentEnergyConsumptionHybridVehicle(double vehicleForce, EngineEfficiency engineEfficiency, Segment segment, double rpm){
        double segmentEnergyConsumption;
        HybridVehicle hybridVehicle = (HybridVehicle)m_vehicle;
        if (vehicleForce<0) {
            //ToDo calcular força maxima do motor
            double vehicleMaxForce=calculateMaxForce(engineEfficiency, rpm);
            if (vehicleForce>vehicleMaxForce) {
                vehicleForce=vehicleMaxForce;
            }
            segmentEnergyConsumption= vehicleForce * hybridVehicle.getEnergyRegenerationRatio()*segment.getLenght();
        } else{
            segmentEnergyConsumption = vehicleForce * engineEfficiency.getM_sfc() / 3600000 * segment.getLenght();
        }
        return segmentEnergyConsumption;
    }
    
    private double calculateMaxForce(EngineEfficiency engineEfficiency, double rpm){
        
        double finalDriveRatio=m_vehicle.getFinalDriveRatio();
        double tireRadious=m_vehicle.getRadiusOfTire();
        double torque=0;
        double gearRatio=0;
        
        for (EngineEfficiency ee : m_vehicle.getEngineEfficiency()) {
            if (ee.getThrottleRatio().equals("100")) {
                if (rpm > engineEfficiency.getM_rpmLow() && rpm < engineEfficiency.getM_rpmHigh()){
                    torque=ee.getTorque();
                    gearRatio=ee.getGearRatio();
                    break;
                }
            }
        }
        
        return torque*finalDriveRatio*gearRatio/tireRadious;
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

    //The vehicle will travel at the maximum speed allowed in the road or for the vehicle
    private double travelSpeed(Section section, Segment segment) {
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

    private double calculateSectionTollCosts(Section section) {
        return section.getToll();
    }

    //private double vehicleForces(int gearIndex, Throttle throttle, Regime regime, Section section, Segment segment, double vehicleVelocity)
    private double vehicleForces(EngineEfficiency engineEfficiency, double vehicleVelocity, double rpm) {

        double vehicleForce = Double.MIN_VALUE;

        double torque;
        double finalDriveRatio;
        double gearRatio;
        double radiusTire;

        if (rpm > engineEfficiency.getM_rpmLow() && rpm < engineEfficiency.getM_rpmHigh()) {
            torque = engineEfficiency.getTorque();
            finalDriveRatio = m_vehicle.getFinalDriveRatio();
            gearRatio = engineEfficiency.getGearRatio();
            radiusTire = m_vehicle.getRadiusOfTire();

            vehicleForce = (torque * finalDriveRatio * gearRatio) / radiusTire;
        }
        return vehicleForce;
    }

    private double resistanceForces(PathParcel pp, Segment segment, double relativeVelocityWindInfluence) {
        double rrc = m_vehicle.getRrc();
        double mass = m_vehicle.getMass() + m_vehicle.getLoad();
        double dragCoefficient = m_vehicle.getDragCoefficient();
        double frontalArea = m_vehicle.getFrontalArea();
        double f1;
        double f2;
        double f3;

        if (pp.getDirection().equals(SimDirection.direct)) {
            f1 = rrc * mass * gravity * Math.cos(segment.getSlope());
            f3 = mass * gravity * Math.sin(segment.getSlope());
        } else {
            f1 = rrc * mass * gravity * Math.cos(-1 * segment.getSlope());
            f3 = mass * gravity * Math.sin(-1 * segment.getSlope());
        }
        f2 = 0.5 * dragCoefficient * frontalArea * densityOfAir * Math.pow(relativeVelocityWindInfluence, 2);

        double resistanceForce = f1 + f2 + f3;

        return resistanceForce;
    }

    //The vehicle will travel at the maximum speed allowed in the road or for the vehicle
    private double rpmCalculation(EngineEfficiency engineEfficiency, double vehicleVelocity) {

        double rpm = 0;


            HashMap<Integer, Double> actualGearList = m_vehicle.getGearList();

            double gearRatio = actualGearList.get(engineEfficiency.getGear());

            rpm = (vehicleVelocity * 60 * m_vehicle.getFinalDriveRatio() * gearRatio)
                    / (2 * Math.PI * m_vehicle.getRadiusOfTire());


        return rpm;
    }

    //Influence of Wind Velocity
    private double relativeVelocityWindInfluence(PathParcel pp, double vehicleVelocity) {
        Wind w = pp.getSection().getWind();
        double windSpeed = w.getVelocity();
        double windAngle = w.getAngle();
        double relativeVelocity;

        if (pp.getDirection().equals(SimDirection.direct)) {
            relativeVelocity = vehicleVelocity + windSpeed * Math.cos(windAngle);
        } else {
            relativeVelocity = vehicleVelocity - windSpeed * Math.cos(windAngle);
        }

        if ((pp.getDirection().equals(SimDirection.direct) && Math.cos(windAngle) < 0
                || pp.getDirection().equals(SimDirection.reverse) && Math.cos(windAngle) > 0)
                && relativeVelocity <= 30) {
            relativeVelocity = vehicleVelocity;
        }
        return relativeVelocity;
    }

//    private double segmentPowerCalculation(EngineEfficiency engineEfficiency)
//    {
//
//        double torque = engineEfficiency.getTorque();
//        
//        double mediaRPM = (engineEfficiency.getM_rpmHigh() + engineEfficiency.getM_rpmLow()) / 2;
//        
//        double power = 2 * Math.PI * torque * (mediaRPM / 60);
//        
//        return power;
//    }
    private ResultStaticAnalysis constructResults() {

        ResultStaticAnalysis simResult = new ResultStaticAnalysis(m_originNode, m_destinyNode);
        simResult.setPath(m_bestPath);
        simResult.setLength(m_bestPathLength);
        simResult.setPathNodes(m_bestPathNodes);
        simResult.setVehicle(m_vehicle);
        return simResult;
    }

    @Override
    public String toString() {
        return "Most Efficient Path in Real Conditions";
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
        } else {
            for (PathParcel pp : m_bestPath) {
                ArrayList<Segment> segmentsList = pp.getSection().getSegmentsList();
                Collections.reverse(segmentsList);
                calculatePathParcel(pp.getSection(), segmentsList);
            }
        }
    }

    private void calculatePathParcel(Section section, ArrayList<Segment> segmentsList) {
        double time = 0; //in seconds
        for (Segment it : segmentsList) {
            //creates new SimPathParcel
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
            pp.setTollCosts(section.getToll() / segmentsList.size());
            //get vehicle's velocity on the current segment
            double vehicleVelocity = travelSpeed(section, it);
            //set the segment's energy consumption
            if (m_vehicle instanceof CombustionVehicle) {
                CombustionVehicle combustionVehicle = (CombustionVehicle) m_vehicle;
                List<EngineEfficiency> engineEfficiencyList = combustionVehicle.getEngineEfficiency();
                pp.setTheoreticalEnergyConsumption(calculateSegmentEnergyConsumption(engineEfficiencyList, pp, it));
            }
            //adds the SimPathParcel to the list
            m_simPathParcelList.add(pp);
        }

    }
}
    

