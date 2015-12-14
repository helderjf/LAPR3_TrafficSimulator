/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadnetwork.domain;

/**
 *
 * @author André Pedrosa, Hélder Faria, José Miranda, Rubén Rosário
 */
public class Segment {
    
    private int id;
    private int index;
    private double initial_Height;
    private double slope;
    private double lenght;
    private double rrc;
    private double max_Velocity;
    private double min_Velocity;
    private double max_Vehicles;


    /**
     * 
     */
    public Segment()
    {
        this.id = 0;
        this.index = 0;
        this.initial_Height = 0;
        this.lenght = 0;
        this.max_Vehicles = 0;
        this.min_Velocity = 0;
        this.max_Velocity = 0;
        this.slope = 0;
        this.rrc = 0;
    }
    
    /**
     * 
     * @param id id
     * @param index index
     * @param initial_Height initial_Height
     * @param slope slope
     * @param lenght lenght
     * @param rrc Rolling Resistance Coefficient
     * @param max_Velocity max_Velocity
     * @param min_Velocity min_Velocity
     * @param max_Vehicles max_Vehicles
     */
    public Segment(int id, int index, double initial_Height, double slope, double lenght, double rrc, double max_Velocity, double min_Velocity, double max_Vehicles) {
        this.id = id;
        this.index = index;
        this.initial_Height = initial_Height;
        this.slope = slope;
        this.lenght = lenght;
        this.rrc = rrc;
        this.max_Velocity = max_Velocity;
        this.min_Velocity = min_Velocity;
        this.max_Vehicles = max_Vehicles;
    }
    
    /**
     * 
     * @param s Segment copy
     */
    public Segment(Segment s)
    {
        s.id = this.id;
        s.index = this.index;
        s.initial_Height = this.initial_Height;
        s.lenght = this.lenght;
        s.max_Vehicles = this.max_Vehicles;
        s.max_Velocity = this.max_Velocity;
        s.min_Velocity = this.min_Velocity;
        s.rrc = this.rrc;
        s.slope = this.slope;
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
     * @return return an index
     */
    public int getIndex() {
        return index;
    }

    /**
     * 
     * @param index index 
     */
    public void setIndex(int index) {
        this.index = index;
    }

    /**
     * 
     * @return returns initial height
     */
    public double getInitial_Height() {
        return initial_Height;
    }

    /**
     * 
     * @param initial_Height receives initial_Height
     */
    public void setInitial_Height(double initial_Height) {
        this.initial_Height = initial_Height;
    }

    /**
     * 
     * @return returns slope
     */
    public double getSlope() {
        return slope;
    }

    /**
     * 
     * @param slope receives slope
     */
    public void setSlope(double slope) {
        this.slope = slope;
    }

    /**
     * 
     * @return returns lenght
     */
    public double getLenght() {
        return lenght;
    }

    /**
     * 
     * @param lenght receives lenght
     */
    public void setLenght(double lenght) {
        this.lenght = lenght;
    }

    /**
     * 
     * @return returns Rolling Resistance Coefficient
     */
    public double getRrc() {
        return rrc;
    }

    /**
     * 
     * @param rrc receives Rolling Resistance Coefficient
     */
    public void setRrc(double rrc) {
        this.rrc = rrc;
    }

    /**
     * 
     * @return return Max_Velocity
     */
    public double getMax_Velocity() {
        return max_Velocity;
    }

    /**
     * 
     * @param max_Velocity receive Max_Velocity
     */
    public void setMax_Velocity(double max_Velocity) {
        this.max_Velocity = max_Velocity;
    }

    /**
     * 
     * @return return Min_Velocity
     */
    public double getMin_Velocity() {
        return min_Velocity;
    }

    /**
     * 
     * @param min_Velocity receives min_Velocity
     */
    public void setMin_Velocity(double min_Velocity) {
        this.min_Velocity = min_Velocity;
    }

    /**
     * 
     * @return return Max_Vehicles
     */
    public double getMax_Vehicles() {
        return max_Vehicles;
    }

    /**
     * 
     * @param max_Vehicles max_vehicles
     */
    public void setMax_Vehicles(double max_Vehicles) {
        this.max_Vehicles = max_Vehicles;
    }

    /**
     * 
     * @return hash
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + this.id;
        hash = 17 * hash + this.index;
        hash = 17 * hash + (int) (Double.doubleToLongBits(this.initial_Height) ^ (Double.doubleToLongBits(this.initial_Height) >>> 32));
        hash = 17 * hash + (int) (Double.doubleToLongBits(this.slope) ^ (Double.doubleToLongBits(this.slope) >>> 32));
        hash = 17 * hash + (int) (Double.doubleToLongBits(this.lenght) ^ (Double.doubleToLongBits(this.lenght) >>> 32));
        hash = 17 * hash + (int) (Double.doubleToLongBits(this.rrc) ^ (Double.doubleToLongBits(this.rrc) >>> 32));
        hash = 17 * hash + (int) (Double.doubleToLongBits(this.max_Velocity) ^ (Double.doubleToLongBits(this.max_Velocity) >>> 32));
        hash = 17 * hash + (int) (Double.doubleToLongBits(this.min_Velocity) ^ (Double.doubleToLongBits(this.min_Velocity) >>> 32));
        hash = 17 * hash + (int) (Double.doubleToLongBits(this.max_Vehicles) ^ (Double.doubleToLongBits(this.max_Vehicles) >>> 32));
        return hash;
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
        final Segment other = (Segment) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.index != other.index) {
            return false;
        }
        if (Double.doubleToLongBits(this.initial_Height) != Double.doubleToLongBits(other.initial_Height)) {
            return false;
        }
        if (Double.doubleToLongBits(this.slope) != Double.doubleToLongBits(other.slope)) {
            return false;
        }
        if (Double.doubleToLongBits(this.lenght) != Double.doubleToLongBits(other.lenght)) {
            return false;
        }
        if (Double.doubleToLongBits(this.rrc) != Double.doubleToLongBits(other.rrc)) {
            return false;
        }
        if (Double.doubleToLongBits(this.max_Velocity) != Double.doubleToLongBits(other.max_Velocity)) {
            return false;
        }
        if (Double.doubleToLongBits(this.min_Velocity) != Double.doubleToLongBits(other.min_Velocity)) {
            return false;
        }
        if (Double.doubleToLongBits(this.max_Vehicles) != Double.doubleToLongBits(other.max_Vehicles)) {
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
        return "Segment{" + "id=" + id + ", index=" + index + ", initial_Height=" + initial_Height + ", slope=" + slope + ", lenght=" + lenght + ", rrc=" + rrc + ", max_Velocity=" + max_Velocity + ", min_Velocity=" + min_Velocity + ", max_Vehicles=" + max_Vehicles + '}';
    }

    
}
