/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadnetwork.domain;

import java.util.Objects;

/**
 *
 * @author André Pedrosa, Hélder Faria, José Miranda, Rubén Rosário
 */
public class CombustionVehicle extends Vehicle implements Combustion {
    
    private String typeOfCombustion;


    /**
     * 
     * @param id id of CombustionVehicle
     * @param name name of CombustionVehicle
     * @param mass mass of CombustionVehicle
     * @param type type of CombustionVehicle
     * @param load load of CombustionVehicle
     * @param drag_Coefficient drag_Coefficient of CombustionVehicle
     * @param maxSpeed maximum speed
     * @param typeOfCombustion typeOfCombustion of CombustionVehicle
     */
    public CombustionVehicle(String id, String name, double mass, TypeOfVehicle type, double load, double drag_Coefficient, double maxSpeed, String typeOfCombustion) {
        super(id, name, mass, type, load, maxSpeed, drag_Coefficient);
        this.typeOfCombustion = typeOfCombustion;
    }

    /**
     * 
     * @return typeOfCombustion of CombustionVehicle
     */
    public String getTypeOfCombustion() {
        return typeOfCombustion;
    }

    /**
     * 
     * @param typeOfCombustion typeOfCombustion
     */
    public void setTypeOfCombustion(String typeOfCombustion) {
        this.typeOfCombustion = typeOfCombustion;
    }

    /**
     * 
     * @return hash
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.typeOfCombustion);
        return hash;
    }

    /**
     * 
     * @param obj object of CombustionVehicle
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
        if (!Objects.equals(this.typeOfCombustion, other.typeOfCombustion)) {
            return false;
        }
        return true;
    }

    /**
     * 
     * @return CombustionVehicle string
     */
    @Override
    public String toString() {
        return "CombustionVehicle{" + super.toString() + "typeOfCombustion=" + typeOfCombustion + '}';
    }
    
    
}
