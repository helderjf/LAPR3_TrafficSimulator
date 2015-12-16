/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadnetwork.domain;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author André Pedrosa, Hélder Faria, José Miranda, Rubén Rosário
 */
public class ElectricVehicle extends Vehicle implements Electric{

    
    /**
     * 
     * @param id id of Electric Vehicles
     * @param name name of Electric Vehicles
     * @param mass mass of Electric Vehicles
     * @param type type of Electric Vehicles
     * @param load load of Electric Vehicles
     * @param drag_Coefficient drag_Coefficient of Electric Vehicles
     * @param maxSpeed maxSpeed of Electric Vehicles
     * @param rrc rrc of Electric Vehicles
     * @param wheelSize wheelSize of Electric Vehicles
     * @param velocityLimit velocityLimit of Electric Vehicles
     * @param torque torque of Electric Vehicles
     * @param mostEfficientRPM mostEfficientRPM of Electric Vehicles
     * @param consuption consuption of Electric Vehicles
     * @param minRPM minRPM of Electric Vehicles
     * @param maxRPM maxRPM of Electric Vehicles
     * @param finalDriveRatio finalDriveRatio of Electric Vehicles
     * @param fuel fuel of Electric Vehicles
     * @param gearRatio gearRatio of Electric Vehicles
     */
    public ElectricVehicle(String id, String name, double mass, TypeOfVehicle type, double load, double drag_Coefficient, double maxSpeed,
        double rrc, double wheelSize, HashMap<Segment,Double> velocityLimit, double torque, double mostEfficientRPM,
        double consuption, double minRPM, double maxRPM, double finalDriveRatio, String fuel, ArrayList<Double> gearRatio) {
        super(id, name, mass, type, load, drag_Coefficient, maxSpeed, rrc, wheelSize, velocityLimit, torque, mostEfficientRPM, consuption,
                minRPM, maxRPM, finalDriveRatio);
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
