/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadnetwork.domain;

/**
 *
 * @author André Pedrosa, Hélder Faria, José Miranda, Rubén Rosário
 */
public class ElectricVehicle extends Vehicle implements Electric{

    public ElectricVehicle(String id, String name, double mass, TypeOfVehicle type, double load, double drag_Coefficient, double maxSpeed) {
        super(id, name, mass, type, load, drag_Coefficient, maxSpeed);
    }

    @Override
    public double breakingEnergyRegeneration() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
    
}
