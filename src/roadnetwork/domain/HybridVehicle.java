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
public class HybridVehicle extends Vehicle implements Electric , Combustion{

    public HybridVehicle(String id, String name, double mass, String type, double load, double drag_Coefficient, double maxSpeed,
        double rrc, double wheelSize, HashMap<String,Double> velocityLimit, double torque, double mostEfficientRPM,
        double consuption, double minRPM, double maxRPM, double finalDriveRatio, String fuel, ArrayList<Double> gearRatio) {
        super(id, name, mass, type, load, drag_Coefficient, maxSpeed, rrc, wheelSize, velocityLimit, torque, mostEfficientRPM, consuption,
                minRPM, maxRPM, finalDriveRatio);
    }
    
    public HybridVehicle(){};
    
    
    @Override
    public double breakingEnergyRegeneration() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
