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
public class HybridVehicle extends Vehicle implements Electric , Combustion{

    private int m_pk;

    public HybridVehicle(String name, String description, double mass, String type, double load, 
            double drag_Coefficient, double maxSpeed, double rrc, double wheelSize, HashMap<SectionTypology, Double> velocityLimit, 
            double torque, double mostEfficientRPM, double consuption, double minRPM, double maxRPM, 
            double finalDriveRatio) {
        super(name, description, mass, type, load, drag_Coefficient, maxSpeed, rrc, wheelSize, velocityLimit, 
                 minRPM, maxRPM, finalDriveRatio);
    }
    
    
    
    public HybridVehicle(){};
    
    
    @Override
    public double breakingEnergyRegeneration() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
