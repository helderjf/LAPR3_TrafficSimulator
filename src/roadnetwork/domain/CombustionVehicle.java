/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadnetwork.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

/**
 *
 * @author André Pedrosa, Hélder Faria, José Miranda, Rubén Rosário
 */
public class CombustionVehicle extends Vehicle implements Combustion {

    private String fuel;
    private ArrayList<Double> gearList;
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
     * @param torque torque of CombustionVehicle
     * @param fuel fuel of CombustionVehicle
     * @param consuption consuption of CombustionVehicle
     * @param mostEfficientRPM mostEfficientRPM of CombustionVehicle
     * @param minRPM minRPM of CombustionVehicle
     * @param maxRPM maxRPM of CombustionVehicle
     * @param finalDriveRatio finalDriveRatio of CombustionVehicle
     * @param gearList
     * @param throttleList
     */
    public CombustionVehicle(String name, String description, double mass, String type, double load,
            double drag_Coefficient, double frontalArea, double rrc, double wheelSize, HashMap<String, Double> velocityLimit,
            double torque, double mostEfficientRPM, double consuption, double minRPM, double maxRPM,
            double finalDriveRatio, String fuel, ArrayList<Double> gearList, ArrayList<Throttle> throttleList) {
        super(name, description, mass, type, load, drag_Coefficient, frontalArea, rrc, wheelSize, velocityLimit,
                minRPM, maxRPM, finalDriveRatio);
        this.fuel = fuel;
        this.gearList = gearList;
        this.throttleList = throttleList;
    }

    
    public CombustionVehicle() {
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
    public ArrayList<Double> getGearList() {
        return gearList;
    }

    /**
     *
     * @param gearList
     */
    public void setGearList(ArrayList<Double> gearList) {
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

}
