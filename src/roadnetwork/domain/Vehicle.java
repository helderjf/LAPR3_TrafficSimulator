/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadnetwork.domain;

import java.util.HashMap;
import java.util.Objects;

/**
 *
 * @author André Pedrosa, Hélder Faria, José Miranda, Rubén Rosário
 */
public class Vehicle {

    private int m_pk;
    private String name;
    private String description;
    private String type;
    private double mass;
    private double load;
    private double dragCoefficient;
    private double frontalArea;
    private double rrc;
    private double wheelSize;
    private HashMap<SectionTypology, Double> velocityLimit;
    private double minRPM;
    private double maxRPM;
    private double finalDriveRatio;

    /**
     *
     */
    public Vehicle() {
        this.m_pk = 0;
        this.name = "";
        this.description = "";
        this.mass = 0;
        this.type = "";
        this.load = 0;
        this.dragCoefficient = 0;
        this.frontalArea = 0;
        this.rrc = 0;
        this.wheelSize = 0;
        this.velocityLimit = new HashMap<>();
        this.minRPM = 0;
        this.maxRPM = 0;
        this.finalDriveRatio = 0;
    }

    /**
     *
     * @param name name
     * @param description description
     * @param mass mass
     * @param type type
     * @param load load
     * @param drag_Coefficient drag coefficient
     * @param frontalArea
     * @param rrc rrc
     * @param wheelSize wheelSize diameter
     * @param velocityLimit
     * @param minRPM minRPM
     * @param maxRPM maxRPM
     * @param finalDriveRatio finalDriveRatio
     */
    public Vehicle(String name, String description, double mass, String type, double load, double drag_Coefficient,
            double frontalArea, double rrc, double wheelSize, HashMap<SectionTypology, Double> velocityLimit,
            double minRPM, double maxRPM, double finalDriveRatio) {

        this.m_pk = 0;
        this.name = name;
        this.description = description;
        this.mass = mass;
        this.type = type;
        this.load = load;
        this.dragCoefficient = drag_Coefficient;
        this.frontalArea = frontalArea;
        this.rrc = rrc;
        this.wheelSize = wheelSize;
        this.velocityLimit = velocityLimit;
        this.minRPM = minRPM;
        this.maxRPM = maxRPM;
        this.finalDriveRatio = finalDriveRatio;

    }

    public Vehicle(int pk, String name, String description, double mass, String type, double load, double drag_Coefficient,
            double frontalArea, double rrc, double wheelSize, HashMap<SectionTypology, Double> velocityLimit,
            double minRPM, double maxRPM, double finalDriveRatio) {

        this.m_pk = pk;
        this.name = name;
        this.description = description;
        this.mass = mass;
        this.type = type;
        this.load = load;
        this.dragCoefficient = drag_Coefficient;
        this.frontalArea = frontalArea;
        this.rrc = rrc;
        this.wheelSize = wheelSize;
        this.velocityLimit = velocityLimit;
        this.minRPM = minRPM;
        this.maxRPM = maxRPM;
        this.finalDriveRatio = finalDriveRatio;

    }

    /**
     *
     * @param v Vechicle copy
     */
    public Vehicle(Vehicle v)//TO DO alterar isto. está ao contrário
    {

        v.name = this.name;
        v.description = this.description;
        v.mass = this.mass;
        v.type = this.type;
        v.load = this.load;
        v.dragCoefficient = this.dragCoefficient;
        v.frontalArea = this.frontalArea;
        v.rrc = this.rrc;
        v.wheelSize = this.wheelSize;
        v.velocityLimit = this.velocityLimit;
        v.minRPM = this.minRPM;
        v.maxRPM = this.maxRPM;
        v.finalDriveRatio = this.finalDriveRatio;
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
    
    public double getVelocityLimit(SectionTypology typology){
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
    
    public void addVelocityLimit(SectionTypology segment_type, double limit){
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
    
    public double getRadiusOfTire()
    {
        return wheelSize / 2;
    }

}
