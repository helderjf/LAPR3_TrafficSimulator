/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadnetwork.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author André Pedrosa, Hélder Faria, José Miranda, Rubén Rosário
 */
public class CombustionVehicle extends Vehicle implements Combustion {

    private final double dieselEnergy = 48000; //J/g
    private final double gasolineEnergy = 44400; //J/g
    
    /**
     *
     */
    public CombustionVehicle() {
        super();
    }

    /**
     *
     * @param m_pk m_pk
     * @param name name
     * @param description description
     * @param type type
     * @param fuel fuel
     * @param mass mass
     * @param load load
     * @param dragCoefficient dragCoefficient
     * @param frontalArea frontalArea
     * @param rrc rrc
     * @param wheelSize wheelSize
     * @param velocityLimit velocityLimit
     * @param minRPM minRPM
     * @param maxRPM maxRPM
     * @param finalDriveRatio finalDriveRatio
     * @param gearList gearList
     * @param throttleList throttleList
     */
    public CombustionVehicle(int m_pk, String name, String description, String type, String fuel, double mass, double load, double dragCoefficient, double frontalArea, double rrc, double wheelSize, HashMap<SectionTypology, Double> velocityLimit, double minRPM, double maxRPM, double finalDriveRatio, HashMap<Integer, Double> gearList, ArrayList<Throttle> throttleList) {
        super(m_pk, name, description, type, fuel, mass, load, dragCoefficient, frontalArea, rrc, wheelSize, velocityLimit, minRPM, maxRPM, finalDriveRatio, gearList, throttleList);
    }

    /**
     *
     * @param name name
     * @param description description
     * @param type type
     * @param fuel fuel
     * @param mass mass
     * @param load load
     * @param dragCoefficient dragCoefficient
     * @param frontalArea frontalArea
     * @param rrc rrc
     * @param wheelSize wheelSize
     * @param velocityLimit velocityLimit
     * @param minRPM minRPM
     * @param maxRPM maxRPM
     * @param finalDriveRatio finalDriveRatio
     * @param gearList gearList
     * @param throttleList throttleList
     */
    public CombustionVehicle(String name, String description, String type, String fuel, double mass, double load, double dragCoefficient, double frontalArea, double rrc, double wheelSize, HashMap<SectionTypology, Double> velocityLimit, double minRPM, double maxRPM, double finalDriveRatio, HashMap<Integer, Double> gearList, ArrayList<Throttle> throttleList) {
        super(name, description, type, fuel, mass, load, dragCoefficient, frontalArea, rrc, wheelSize, velocityLimit, minRPM, maxRPM, finalDriveRatio, gearList, throttleList);
    }
    
    public CombustionVehicle(CombustionVehicle otherCombustionVehicle){
        super(otherCombustionVehicle);
    }


    /**
     *
     * @param obj object
     * @return result
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CombustionVehicle other = (CombustionVehicle) obj;
        if (!Objects.equals(this.fuel, other.fuel)) {
            return false;
        }
        if (!Objects.equals(this.gearList, other.gearList)) {
            return false;
        }
        return true;
    }
    
    

    /**
     * 
     * @return engineEfficiencyList
     */
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
                    engineEfficiency.setM_sfc(regime.getSfc());
                    engineEfficiency.setM_rpmLow(regime.getRPMLow());
                    engineEfficiency.setM_rpmHigh(regime.getRPMHigh());

                    //engineEfficiency.setResult(gearList.get(key_idGear) * regime.getTorque());
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
    
    
    @Override
    public  double getIdleConsumption(double timeIdle){
        double rpmLow = 999999;
        double torque = 0;
        double sfc = 0;
        double result = 0;

        for (Throttle t : throttleList) {
            if (t.getID().startsWith("25")) {
                for (Regime r : t.getRegimeList()) {
                    if (r.getRPMLow() < rpmLow) {
                        rpmLow = r.getRPMLow();
                        torque = r.getTorque();
                        sfc = r.getSfc();
                    }
                }
            }
        }
        result = (2 * 3.1415 * torque * (rpmLow / 60) * sfc * timeIdle)/3600000;

        return result;

    }

    /**
     * @return the dieselEnergy
     */
    public double getDieselEnergy() {
        return dieselEnergy;
    }

    /**
     * @return the gasolineEnergy
     */
    public double getGasolineEnergy() {
        return gasolineEnergy;
    }

}
