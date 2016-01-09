/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadnetwork.domain;

import java.util.HashMap;

/**
 *
 * @author Andre
 */
public class PhysicsCalcs {
    
    Vehicle m_vehicle;
    private final double gravity = 9.81; // m^2
    private final double densityOfAir = 1.225; // kg/m3  
            
    public PhysicsCalcs(Vehicle vehicle)
    {
        m_vehicle = vehicle;
    }

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
    
    private double powerCalculation(EngineEfficiency engineEfficiency)
    {

        double torque = engineEfficiency.getTorque();
        
        double mediaRPM = (engineEfficiency.getM_rpmHigh() + engineEfficiency.getM_rpmLow()) / 2;
        
        double power = 2 * Math.PI * torque * (mediaRPM / 60);
        
        return power;
    }   
    
}
