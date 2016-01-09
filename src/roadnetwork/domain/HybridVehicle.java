/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadnetwork.domain;

import java.util.HashMap;
import java.util.List;

/**
 *
 * @author André Pedrosa, Hélder Faria, José Miranda, Rubén Rosário
 */
public class HybridVehicle extends Vehicle implements Electric, Combustion {

    public HybridVehicle(
            String name, 
            String description,
            double mass, 
            String type, 
            double load,
            double drag_Coefficient,
            double frontalArea, 
            double rrc, 
            double wheelSize,
            HashMap<SectionTypology, Double> velocityLimit,
            double minRPM,
            double maxRPM,
            double finalDriveRatio) {
        super(name, description, mass, type, load, drag_Coefficient, frontalArea, rrc, wheelSize, velocityLimit,
                minRPM, maxRPM, finalDriveRatio);
    }

    public HybridVehicle(
            int pk,
            String name,
            String description,
            double mass,
            String type,
            double load,
            double drag_Coefficient,
            double frontalArea,
            double rrc,
            double wheelSize,
            HashMap<SectionTypology, Double> velocityLimit,
            double minRPM,
            double maxRPM,
            double finalDriveRatio) {
        super(pk, name, description, mass, type, load, drag_Coefficient, frontalArea, rrc, wheelSize, velocityLimit,
                minRPM, maxRPM, finalDriveRatio);
    }

    public HybridVehicle() {
    }

    ;
    
    
    @Override
    public double breakingEnergyRegeneration() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<EngineEfficiency> getEngineEfficiency() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
