/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadnetwork.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author André Pedrosa, Hélder Faria, José Miranda, Rubén Rosário
 */
public class CombustionVehicle extends Vehicle implements Combustion {

    private String fuel;
    private HashMap<Integer,Double> gearList;
    private ArrayList<Throttle> throttleList;

    /**
     *
     * @param name name of CombustionVehicle
     * @param description description of Vehicle
     * @param mass mass of CombustionVehicle
     * @param type type of CombustionVehicle
     * @param load load of CombustionVehicle
     * @param drag_Coefficient drag_Coefficient of CombustionVehicle
     * @param frontalArea
     * @param rrc rrc of CombustionVehicle
     * @param wheelSize wheelSize of CombustionVehicle
     * @param velocityLimit velocityLimit of CombustionVehicle
     * @param fuel fuel of CombustionVehicle
     * @param minRPM minRPM of CombustionVehicle
     * @param maxRPM maxRPM of CombustionVehicle
     * @param finalDriveRatio finalDriveRatio of CombustionVehicle
     * @param gearList
     * @param throttleList
     */
    public CombustionVehicle(String name, String description, double mass, String type, double load,
            double drag_Coefficient, double frontalArea, double rrc, double wheelSize, HashMap<SectionTypology, Double> velocityLimit,
            double minRPM, double maxRPM,
            double finalDriveRatio, String fuel, HashMap<Integer,Double> gearList, ArrayList<Throttle> throttleList) {
        super(name, description, mass, type, load, drag_Coefficient, frontalArea, rrc, wheelSize, velocityLimit,
                minRPM, maxRPM, finalDriveRatio);
        this.fuel = fuel;
        this.gearList = gearList;
        this.throttleList = throttleList;
    }

    public CombustionVehicle(
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
            double finalDriveRatio,
            String fuel,
            HashMap<Integer,Double> gearList,
            ArrayList<Throttle> throttleList) {

        super(pk, name, description, mass, type, load, drag_Coefficient, frontalArea, rrc, wheelSize, velocityLimit,
                minRPM, maxRPM, finalDriveRatio);
        this.fuel = fuel;
        this.gearList = gearList;
        this.throttleList = throttleList;
    }

    public CombustionVehicle(String fuel) {
        this.fuel = fuel;
    }

    /**
     *
     * @return fuel of CombustionVehicle
     */
    public String getFuel() {
        return fuel;
    }

    /**
     *
     * @param fuel fuel
     */
    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    /**
     *
     * @return gearList
     */
    public HashMap<Integer,Double> getGearList() {
        return gearList;
    }

    /**
     *
     * @param gearList
     */
    public void setGearList(HashMap<Integer,Double> gearList) {
        this.gearList = gearList;
    }

    public ArrayList<Throttle> getThrottleList() {
        return throttleList;
    }

    public void setThrottleList(ArrayList<Throttle> throttleList) {
        this.throttleList = throttleList;
    }
    
    

    /**
     *
     * @param obj object
     * @return
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

    @Override
    public List<EngineEfficiency> getEngineEfficiency() {
        
        EngineEfficiency engineEfficiency = new EngineEfficiency();
        List<EngineEfficiency> engineEfficiencyList = new ArrayList<>();
        
        
        //preenche a lista com todos as performances
        for (int key_idGear : gearList.keySet()) {  
        
            for(Throttle throttle : throttleList)
            {
                for(Regime regime : throttle.getRegimeList())
                {
                    engineEfficiency.setGear(key_idGear);
                    engineEfficiency.setGearRatio(gearList.get(key_idGear));
                    engineEfficiency.setThrottleRatio(throttle.getID());
                    engineEfficiency.setTorque(regime.getTorque());
                    engineEfficiency.setM_sfc(regime.getSfc());
                    engineEfficiency.setM_rpmLow(regime.getRPMLow());
                    engineEfficiency.setM_rpmHigh(regime.getRPMHigh());
                      
                    engineEfficiency.setResult(gearList.get(key_idGear)*regime.getTorque());
                           
                }
                engineEfficiencyList.add(engineEfficiency);
            }     
        }
        
        //ordena a lista em ordem crescente por performance
        Collections.sort(engineEfficiencyList, new Comparator<EngineEfficiency>() 
        {
             @Override
             public int compare(EngineEfficiency lhs, EngineEfficiency rhs) {

               return Double.valueOf(lhs.getResult()).compareTo(rhs.getResult());
              }
         });

        
        return engineEfficiencyList;
    }

}
