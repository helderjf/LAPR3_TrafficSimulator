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
public class TypeOfVehicle {
    
    int id;
    private String type;
    
    /**
     * 
     */
    public TypeOfVehicle()
    {
        id = 0;
        type = "";
    }

    /**
     * 
     * @param id id
     * @param type type (truck,	tractor	and semi-trailer, car, motorcycle, etc)
     */
    public TypeOfVehicle(int id,String type) {
        this.id = id;
        this.type = type;
    }
    
    /**
     * 
     * @param t TypeOfVehicle copy
     */
    public TypeOfVehicle(TypeOfVehicle t)
    {
        t.id = this.id;
        t.type = this.type;
    }

    /**
     * 
     * @return id
     */
    public int getId() {
        return id;
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
     * @param type type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 
     * @return hash
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + this.id;
        hash = 53 * hash + Objects.hashCode(this.type);
        return hash;
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
        final TypeOfVehicle other = (TypeOfVehicle) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        return true;
    }

    /**
     * 
     * @return string
     */
    @Override
    public String toString() {
        return "TypeOfVehicle{" + "id=" + id + ", type=" + type + '}';
    }
    
    
    
}
