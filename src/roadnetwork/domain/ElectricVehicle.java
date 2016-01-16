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
    
    public ElectricVehicle (ElectricVehicle otherElectricVehicle){
        super(otherElectricVehicle);
        energyRegenerationRatio=otherElectricVehicle.energyRegenerationRatio;
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

        //EngineEfficiency engineEfficiency = new EngineEfficiency();
        ArrayList<EngineEfficiency> engineEfficiencyList = new ArrayList<>();
        
        //obter a ultima mudanca
        int lastGear = gearList.size();
        
        //preenche a lista com todos as performances
        while(lastGear > 0)
        {
            for (Throttle throttle : throttleList) 
            {
                for (Regime regime : throttle.getRegimeList()) 
                {
                    EngineEfficiency engineEfficiency = new EngineEfficiency();
                    engineEfficiency.setGear(lastGear);
                    engineEfficiency.setGearRatio(gearList.get(lastGear));
                    engineEfficiency.setThrottleRatio(throttle.getID());
                    engineEfficiency.setTorque(regime.getTorque());
                    engineEfficiency.setM_rpmLow(regime.getRPMLow());
                    engineEfficiency.setM_rpmHigh(regime.getRPMHigh());

                    engineEfficiencyList.add(engineEfficiency);
                }
            }
            lastGear--;
        }

//        //ordena a lista em ordem crescente por performance
//        Collections.sort(engineEfficiencyList, new Comparator<EngineEfficiency>() {
//            @Override
//            public int compare(EngineEfficiency lhs, EngineEfficiency rhs) {
//
//                return Double.valueOf(lhs.getGearRatio()*lhs.getTorque()).compareTo(rhs.getGearRatio()*rhs.getTorque());
//            }
//        });

        return engineEfficiencyList;
    }

    public double getEnergyRegenerationRatio() {
        return energyRegenerationRatio;
    }
    
    
    
    

}
