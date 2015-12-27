/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadnetwork.domain;

import java.util.HashMap;

/**
 *
 * @author André Pedrosa, Hélder Faria, José Miranda, Rubén Rosário
 */
public class ElectricVehicle extends Vehicle implements Electric{

    
    /**
     * 
     * @param name name of Electric Vehicle
     * @param description description of Electric Vehicle
     * @param mass mass of Electric Vehicles
     * @param type type of Electric Vehicles
     * @param load load of Electric Vehicles
     * @param drag_Coefficient drag_Coefficient of Electric Vehicles
     * @param frontalArea
     * @param rrc rrc of Electric Vehicles
     * @param wheelSize wheelSize of Electric Vehicles
     * @param velocityLimit velocityLimit of Electric Vehicles
     * @param minRPM minRPM of Electric Vehicles
     * @param maxRPM maxRPM of Electric Vehicles
     * @param finalDriveRatio finalDriveRatio of Electric Vehicles
     */

    
    public ElectricVehicle(String name, String description, double mass, String type, double load, 
            double drag_Coefficient, double frontalArea, double rrc, double wheelSize, HashMap<SectionTypology, Double> velocityLimit, 
            double minRPM, double maxRPM, 
            double finalDriveRatio){
        super(name, description, mass, type, load, drag_Coefficient, frontalArea, rrc, wheelSize, velocityLimit, 
                minRPM, maxRPM, finalDriveRatio);
 };

    public ElectricVehicle() {
    }

    /**
     * 
     * @return string ElectricVehicle
     */
    @Override
    public double breakingEnergyRegeneration() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
    
}
