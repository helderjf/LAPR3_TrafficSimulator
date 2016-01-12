/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadnetwork.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author André Pedrosa, Hélder Faria, José Miranda, Rubén Rosário
 */
public class ElectricVehicle extends Vehicle implements Electric {

    protected double energyRegenerationRatio;

    public ElectricVehicle() {
        super();
    }

    public ElectricVehicle(int m_pk, String name, String description, String type, String fuel, double mass, double load, double dragCoefficient, double frontalArea, double rrc, double wheelSize, HashMap<SectionTypology, Double> velocityLimit, double minRPM, double maxRPM, double finalDriveRatio, HashMap<Integer, Double> gearList, ArrayList<Throttle> throttleList, double energyRegenerationRatio) {
        super(m_pk, name, description, type, fuel, mass, load, dragCoefficient, frontalArea, rrc, wheelSize, velocityLimit, minRPM, maxRPM, finalDriveRatio, gearList, throttleList);
        this.energyRegenerationRatio = energyRegenerationRatio;
    }

    public ElectricVehicle(String name, String description, String type, String fuel, double mass, double load, double dragCoefficient, double frontalArea, double rrc, double wheelSize, HashMap<SectionTypology, Double> velocityLimit, double minRPM, double maxRPM, double finalDriveRatio, HashMap<Integer, Double> gearList, ArrayList<Throttle> throttleList, double energyRegenerationRatio) {
        super(name, description, type, fuel, mass, load, dragCoefficient, frontalArea, rrc, wheelSize, velocityLimit, minRPM, maxRPM, finalDriveRatio, gearList, throttleList);
        this.energyRegenerationRatio = energyRegenerationRatio;
    }

    /**
     *
     * @return string ElectricVehicle
     */
    @Override
    public double breakingEnergyRegeneration(double force) {
        return energyRegenerationRatio*force;
    }

    @Override
    public List<EngineEfficiency> getEngineEfficiency() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
