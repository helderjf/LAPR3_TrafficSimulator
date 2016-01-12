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
public abstract class Vehicle {

    protected int m_pk;
    protected String name;
    protected String description;
    protected String type;
    protected String fuel;
    protected double mass;
    protected double load;
    protected double dragCoefficient;
    protected double frontalArea;
    protected double rrc;
    protected double wheelSize;
    protected HashMap<SectionTypology, Double> velocityLimit;
    protected double minRPM;
    protected double maxRPM;
    protected double finalDriveRatio;
    protected HashMap<Integer, Double> gearList;
    protected ArrayList<Throttle> throttleList;

    /**
     *
     */
    public Vehicle() {
        this.m_pk = 0;
        this.name = "";
        this.description = "";
        this.type = "";
        this.fuel = "";
        this.mass = 0;
        this.load = 0;
        this.dragCoefficient = 0;
        this.frontalArea = 0;
        this.rrc = 0;
        this.wheelSize = 0;
        this.velocityLimit = new HashMap<>();
        this.minRPM = 0;
        this.maxRPM = 0;
        this.finalDriveRatio = 0;
        this.gearList = new HashMap();
        this.throttleList = new ArrayList();
    }

    /**
     *
     * @param name
     */
    public Vehicle(String name) {
        this();
        this.name = name;
    }

    /**
     *
     * @param m_pk
     * @param name
     * @param description
     * @param type
     * @param fuel
     * @param mass
     * @param load
     * @param dragCoefficient
     * @param frontalArea
     * @param rrc
     * @param wheelSize
     * @param velocityLimit
     * @param minRPM
     * @param maxRPM
     * @param finalDriveRatio
     * @param gearList
     * @param throttleList
     */
    public Vehicle(int m_pk, String name, String description, String type, String fuel, double mass, double load, double dragCoefficient, double frontalArea, double rrc, double wheelSize, HashMap<SectionTypology, Double> velocityLimit, double minRPM, double maxRPM, double finalDriveRatio, HashMap<Integer, Double> gearList, ArrayList<Throttle> throttleList) {
        this.m_pk = m_pk;
        this.name = name;
        this.description = description;
        this.type = type;
        this.fuel = fuel;
        this.mass = mass;
        this.load = load;
        this.dragCoefficient = dragCoefficient;
        this.frontalArea = frontalArea;
        this.rrc = rrc;
        this.wheelSize = wheelSize;
        this.velocityLimit = velocityLimit;
        this.minRPM = minRPM;
        this.maxRPM = maxRPM;
        this.finalDriveRatio = finalDriveRatio;
        this.gearList = gearList;
        this.throttleList = throttleList;
    }

    /**
     *
     * @param name
     * @param description
     * @param type
     * @param fuel
     * @param mass
     * @param load
     * @param dragCoefficient
     * @param frontalArea
     * @param rrc
     * @param wheelSize
     * @param velocityLimit
     * @param minRPM
     * @param maxRPM
     * @param finalDriveRatio
     * @param gearList
     * @param throttleList
     */
    public Vehicle(String name, String description, String type, String fuel, double mass, double load, double dragCoefficient, double frontalArea, double rrc, double wheelSize, HashMap<SectionTypology, Double> velocityLimit, double minRPM, double maxRPM, double finalDriveRatio, HashMap<Integer, Double> gearList, ArrayList<Throttle> throttleList) {
        this.m_pk = 0;
        this.name = name;
        this.description = description;
        this.type = type;
        this.fuel = fuel;
        this.mass = mass;
        this.load = load;
        this.dragCoefficient = dragCoefficient;
        this.frontalArea = frontalArea;
        this.rrc = rrc;
        this.wheelSize = wheelSize;
        this.velocityLimit = velocityLimit;
        this.minRPM = minRPM;
        this.maxRPM = maxRPM;
        this.finalDriveRatio = finalDriveRatio;
        this.gearList = gearList;
        this.throttleList = throttleList;
    }

    /**
     *
     * @return m_pk
     */
    public int getPK() {
        return m_pk;
    }

    /**
     *
     * @param pk m_pk
     */
    public void setPK(int pk) {
        this.m_pk = pk;
    }

    /**
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return mass
     */
    public double getMass() {
        return mass;
    }

    /**
     *
     * @param mass mass
     */
    public void setMass(double mass) {
        this.mass = mass;
    }

    /**
     *
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     *
     * @return load
     */
    public double getLoad() {
        return load;
    }

    /**
     *
     * @param load load
     */
    public void setLoad(double load) {
        this.load = load;
    }

    /**
     *
     * @return frag coefficient
     */
    public double getDragCoefficient() {
        return dragCoefficient;
    }

    /**
     *
     * @param drag_Coefficient dragCoefficient
     */
    public void setDragCoefficient(double drag_Coefficient) {
        this.dragCoefficient = drag_Coefficient;
    }

    /**
     *
     * @return rrc
     */
    public double getRcc() {
        return rrc;
    }

    /**
     *
     * @param rrc rrc
     */
    public void setRcc(double rrc) {
        this.rrc = rrc;
    }

    /**
     *
     * @return wheelsize
     */
    public double getWheelSize() {
        return wheelSize;
    }

    /**
     *
     * @param wheelSize wheelsize
     */
    public void setWheelSize(double wheelSize) {
        this.wheelSize = wheelSize;
    }

    /**
     *
     * @return velocitylimit
     */
    public HashMap<SectionTypology, Double> getVelocityLimits() {
        return velocityLimit;
    }

    /**
     *
     * @param velocityLimit velocityLimit
     */
    public void setVelocityLimits(HashMap<SectionTypology, Double> velocityLimit) {
        this.velocityLimit = velocityLimit;
    }

    public double getVelocityLimit(SectionTypology typology) {
        return velocityLimit.get(typology);
    }

    /**
     *
     * @return minrpm
     */
    public double getMinRPM() {
        return minRPM;
    }

    /**
     *
     * @param minRPM minrpm
     */
    public void setMinRPM(double minRPM) {
        this.minRPM = minRPM;
    }

    /**
     *
     * @return maxrpm
     */
    public double getMaxRPM() {
        return maxRPM;
    }

    /**
     *
     * @param maxRPM maxrpm
     */
    public void setMaxRPM(double maxRPM) {
        this.maxRPM = maxRPM;
    }

    /**
     *
     * @return FinalDriveRatio
     */
    public double getFinalDriveRatio() {
        return finalDriveRatio;
    }

    /**
     *
     * @param finalDriveRatio FinalDriveRatio
     */
    public void setFinalDriveRatio(double finalDriveRatio) {
        this.finalDriveRatio = finalDriveRatio;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    public double getFrontalArea() {
        return frontalArea;
    }

    public void setFrontalArea(double frontalArea) {
        this.frontalArea = frontalArea;
    }

    public int getM_pk() {
        return m_pk;
    }

    public void setM_pk(int m_pk) {
        this.m_pk = m_pk;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public double getRrc() {
        return rrc;
    }

    public void setRrc(double rrc) {
        this.rrc = rrc;
    }

    public HashMap<SectionTypology, Double> getVelocityLimit() {
        return velocityLimit;
    }

    public void setVelocityLimit(HashMap<SectionTypology, Double> velocityLimit) {
        this.velocityLimit = velocityLimit;
    }

    public HashMap<Integer, Double> getGearList() {
        return gearList;
    }

    public void setGearList(HashMap<Integer, Double> gearList) {
        this.gearList = gearList;
    }

    public ArrayList<Throttle> getThrottleList() {
        return throttleList;
    }

    public void setThrottleList(ArrayList<Throttle> throttleList) {
        this.throttleList = throttleList;
    }

    public List<EngineEfficiency> getEngineEfficiency() {

        //EngineEfficiency engineEfficiency = new EngineEfficiency();
        ArrayList<EngineEfficiency> engineEfficiencyList = new ArrayList<>();

        //preenche a lista com todos as performances
        for (int key_idGear : gearList.keySet()) {

            for (Throttle throttle : throttleList) {
                for (Regime regime : throttle.getRegimeList()) {
                    EngineEfficiency engineEfficiency = new EngineEfficiency();
                    engineEfficiency.setGear(key_idGear);
                    engineEfficiency.setGearRatio(gearList.get(key_idGear));
                    engineEfficiency.setThrottleRatio(throttle.getID());
                    engineEfficiency.setTorque(regime.getTorque());
                    engineEfficiency.setM_sfc(regime.getSfc());
                    engineEfficiency.setM_rpmLow(regime.getRPMLow());
                    engineEfficiency.setM_rpmHigh(regime.getRPMHigh());

                    engineEfficiency.setResult(gearList.get(key_idGear) * regime.getTorque());
                    engineEfficiencyList.add(engineEfficiency);
                }
            }
        }

        //ordena a lista em ordem crescente por performance
        Collections.sort(engineEfficiencyList, new Comparator<EngineEfficiency>() {
            @Override
            public int compare(EngineEfficiency lhs, EngineEfficiency rhs) {

                return Double.valueOf(lhs.getTorque()*lhs.getGearRatio()).compareTo((rhs.getTorque()*rhs.getGearRatio()));
            }
        });

        return engineEfficiencyList;
    }

    /**
     *
     * @param obj object
     * @return
     */
    @Override
    public boolean equals(Object obj) {//to do completar para os novos atributos
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Vehicle other = (Vehicle) obj;
        if (!Objects.equals(this.m_pk, other.m_pk)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (Double.doubleToLongBits(this.mass) != Double.doubleToLongBits(other.mass)) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        if (Double.doubleToLongBits(this.load) != Double.doubleToLongBits(other.load)) {
            return false;
        }
        if (Double.doubleToLongBits(this.dragCoefficient) != Double.doubleToLongBits(other.dragCoefficient)) {
            return false;
        }
        if (Double.doubleToLongBits(this.rrc) != Double.doubleToLongBits(other.rrc)) {
            return false;
        }
        if (Double.doubleToLongBits(this.wheelSize) != Double.doubleToLongBits(other.wheelSize)) {
            return false;
        }
        if (!Objects.equals(this.velocityLimit, other.velocityLimit)) {
            return false;
        }
        if (Double.doubleToLongBits(this.minRPM) != Double.doubleToLongBits(other.minRPM)) {
            return false;
        }
        if (Double.doubleToLongBits(this.maxRPM) != Double.doubleToLongBits(other.maxRPM)) {
            return false;
        }
        if (Double.doubleToLongBits(this.finalDriveRatio) != Double.doubleToLongBits(other.finalDriveRatio)) {
            return false;
        }
        return true;
    }

    public double Energy() {

        return 0;
    }

    public void addVelocityLimit(SectionTypology segment_type, double limit) {
        velocityLimit.put(segment_type, limit);
    }

    /**
     *
     * @return Vehicle string
     */
    @Override
    public String toString() {
        return "Vehicle " + m_pk + " - " + name;
    }

    /**
     *
     * @return Vehicle data
     */
    public String showData() {//completar para os novos atributos
        return "Vehicle: \n"
                + "id= " + m_pk + "\n"
                + "name= " + name + "\n"
                + "mass= " + mass + "\n"
                + "type= " + getType() + "\n"
                + "load= " + load + "\n"
                + "drag_Coefficient= " + dragCoefficient + "\n"
                + "rrc=" + rrc + "\n"
                + "wheelSize=" + wheelSize + "\n"
                + "velocityLimit=" + velocityLimit + "\n"
                + "minRPM=" + minRPM + "\n"
                + "maxRPM=" + maxRPM + "\n"
                + "finalDriveRatio=" + finalDriveRatio;
    }

    public boolean hasPK() {
        return m_pk != 0;
    }

    public double getRadiusOfTire() {
        return wheelSize / 2;
    }

    double getIdleConsumption(double timeIdle) {
        double rpmLow=999999;
        double torque=0;
        double sfc=0;

        for (Throttle t : throttleList) {
            if (t.getID().startsWith("25")) {
                for (Regime r : t.getRegimeList()) {
                    if(r.getRPMLow()<rpmLow){
                        rpmLow=r.getRPMLow();
                        torque=r.getTorque();
                        sfc=r.getSfc();
                    }
                }
            }
        }
        
        return 2*3.1415*torque*(rpmLow/60)*sfc*timeIdle;

    }

}
