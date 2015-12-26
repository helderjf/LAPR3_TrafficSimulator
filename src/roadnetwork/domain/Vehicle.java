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

public class Vehicle {
      
    private String id;
    private String name;
    private String description;
    private double mass;
    private String type;
    private double load;
    private double drag_Coefficient;
    private double maxSpeed;
    private double rrc;
    private double wheelSize;
    private HashMap<String,Double> velocityLimit;
    private double torque;
    private double mostEfficientRPM;
    private double consuption;
    private double minRPM;
    private double maxRPM;
    private double finalDriveRatio;
    //private ArrayList<Double> gearList;

    
            

    /**
     * 
     */
    public Vehicle()
    {
        this.id = "";
        this.name = "";
        this.description = "";
        this.mass = 0;
        this.type = "";
        this.load = 0;
        this.drag_Coefficient = 0;
        this.maxSpeed=0;
        this.rrc = 0;
        this.wheelSize = 0;
        this.velocityLimit = null;
        this.torque = 0;
        this.mostEfficientRPM = 0;
        this.consuption = 0;
        this.minRPM = 0;
        this.maxRPM = 0;
        this.finalDriveRatio = 0;
        //this.gearList = null;
    }

    /**
     * 
     * @param id id
     * @param name name
     * @param description description
     * @param mass mass
     * @param type type
     * @param load load
     * @param drag_Coefficient drag coefficient 
     * @param maxSpeed maximum speed
     * @param rrc rrc
     * @param wheelSize wheelSize diameter
     * @param velocityLimit velocityLimit
     * @param torque torque
     * @param mostEfficientRPM mostEfficientRPM
     * @param consuption consuption
     * @param minRPM minRPM
     * @param maxRPM maxRPM
     * @param finalDriveRatio finalDriveRatio 
     //* @param gearList gearList 
     */
    public Vehicle(String id, String name, String description, double mass, String type, double load, double drag_Coefficient, double maxSpeed,
                    double rrc, double wheelSize, HashMap<String,Double> velocityLimit, double torque, double mostEfficientRPM,
                    double consuption, double minRPM, double maxRPM, double finalDriveRatio) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.mass = mass;
        this.type = type;
        this.load = load;
        this.drag_Coefficient = drag_Coefficient;
        this.maxSpeed=maxSpeed;
        this.rrc = rrc;
        this.wheelSize = wheelSize;
        this.velocityLimit = velocityLimit;
        this.torque = torque;
        this.mostEfficientRPM = mostEfficientRPM;
        this.consuption = consuption;
        this.minRPM = minRPM;
        this.maxRPM = maxRPM;
        this.finalDriveRatio = finalDriveRatio;
        //this.gearList = gearList;
   
    }
    
    /**
     * 
     * @param v Vechicle copy 
     */
    public Vehicle(Vehicle v)
    {
        v.id = this.id;
        v.name = this.name;
        v.description = this.description;
        v.mass = this.mass;
        v.type = this.type;
        v.load = this.load;
        v.drag_Coefficient = this.drag_Coefficient;
        v.maxSpeed = this.maxSpeed;
        v.rrc = this.rrc;
        v.wheelSize = this.wheelSize;
        v.velocityLimit = this.velocityLimit;
        v.torque = this.torque;
        v.mostEfficientRPM = this.mostEfficientRPM;
        v.consuption = this.consuption;
        v.minRPM = this.minRPM;
        v.maxRPM = this.maxRPM;
        v.finalDriveRatio = this.finalDriveRatio;
        
    }

    /**
     * 
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * 
     * @param id id
     */
    public void setId(String id) {
        this.id = id;
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
    public double getDrag_Coefficient() {
        return drag_Coefficient;
    }

    /**
     * 
     * @param drag_Coefficient drag_Coefficient
     */
    public void setDrag_Coefficient(double drag_Coefficient) {
        this.drag_Coefficient = drag_Coefficient;
    }

    /**
     * 
     * @return maxSpeed
     */
    public double getMaximumSpeed() {
        return maxSpeed;
    }

    /**
     * 
     * @param maxSpeed maxSpeed 
     */
    public void setMaximumSpeed(double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    /**
     * 
     * @return maxSpeed
     */
    public double getMaxSpeed() {
        return maxSpeed;
    }

    /**
     * 
     * @param maxSpeed maxSpeed
     */
    public void setMaxSpeed(double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    /**
     * 
     * @return rrc
     */
    public double getRrc() {
        return rrc;
    }

    /**
     * 
     * @param rrc rrc
     */
    public void setRrc(double rrc) {
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
    public HashMap<String, Double> getVelocityLimit() {
        return velocityLimit;
    }

    /**
     * 
     * @param velocityLimit velocityLimit
     */
    public void setVelocityLimit(HashMap<String, Double> velocityLimit) {
        this.velocityLimit = velocityLimit;
    }

    /**
     * 
     * @return torque
     */
    public double getTorque() {
        return torque;
    }

    /**
     * 
     * @param torque torque
     */
    public void setTorque(double torque) {
        this.torque = torque;
    }

    /**
     * 
     * @return MostEfficientRPM
     */
    public double getMostEfficientRPM() {
        return mostEfficientRPM;
    }

    /**
     * 
     * @param mostEfficientRPM MostEfficientRPM
     */
    public void setMostEfficientRPM(double mostEfficientRPM) {
        this.mostEfficientRPM = mostEfficientRPM;
    }

    /**
     * 
     * @return consuption
     */
    public double getConsuption() {
        return consuption;
    }

    /**
     * 
     * @param consuption consuption
     */
    public void setConsuption(double consuption) {
        this.consuption = consuption;
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
//
//    /**
//     * @return the gearList
//     */
//    public ArrayList<Double> getGearList() {
//        return gearList;
//    }
//
//    /**
//     * @param gearList the gearList to set
//     */
//    public void setGearList(ArrayList<Double> gearList) {
//        this.gearList = gearList;
//    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
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
        final Vehicle other = (Vehicle) obj;
        if (!Objects.equals(this.id, other.id)) {
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
        if (Double.doubleToLongBits(this.drag_Coefficient) != Double.doubleToLongBits(other.drag_Coefficient)) {
            return false;
        }
        if (Double.doubleToLongBits(this.maxSpeed) != Double.doubleToLongBits(other.maxSpeed)) {
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
        if (Double.doubleToLongBits(this.torque) != Double.doubleToLongBits(other.torque)) {
            return false;
        }
        if (Double.doubleToLongBits(this.mostEfficientRPM) != Double.doubleToLongBits(other.mostEfficientRPM)) {
            return false;
        }
        if (Double.doubleToLongBits(this.consuption) != Double.doubleToLongBits(other.consuption)) {
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

    public double Energy()
    {
        
        return 0;
    }
    
    
    /**
     * 
     * @return Vehicle string
     */
    @Override
    public String toString() {
        return "Vehicle " + id + " - " + name;
    }
    
     /**
     * 
     * @return Vehicle data
     */
    public String showData() {
        return "Vehicle: \n"
                + "id= " + id + "\n"
                + "name= " + name + "\n"
                + "mass= " + mass + "\n"
                + "type= " + getType() + "\n"
                + "load= " + load + "\n"
                + "drag_Coefficient= " + drag_Coefficient + "\n"
                + "maxSpeed=" + maxSpeed + "\n"
                + "rrc=" + rrc + "\n"
                + "wheelSize=" + wheelSize + "\n"
                + "velocityLimit=" + velocityLimit + "\n"
                + "torque=" + torque + "\n"
                + "mostEfficientRPM=" + mostEfficientRPM + "\n"
                + "consuption=" + consuption + "\n"
                + "minRPM=" + minRPM + "\n"
                + "maxRPM=" + maxRPM + "\n"
                + "finalDriveRatio=" + finalDriveRatio;
    }
    
    


  
}
