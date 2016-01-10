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
    
    //private int id;
    private int index;
    private double initialHeight;
    private double slope;
    private double lenght;
    private double max_Velocity;
    private double min_Velocity;
    private int max_Vehicles;


    /**
     * 
     */
    public Segment()
    {
        this.index = 0;
        this.initialHeight = 0;
        this.lenght = 0;
        this.max_Vehicles = 0;
        this.min_Velocity = 0;
        this.max_Velocity = 0;
        this.slope = 0;
    }
    
    /**
     * 
     * @param index index
     * @param initial_Height initialHeight
     * @param slope slope
     * @param lenght lenght
     * @param max_Velocity max_Velocity
     * @param min_Velocity min_Velocity
     * @param max_Vehicles max_Vehicles
     */
    public Segment(int index, double initial_Height, double slope, double lenght, double max_Velocity, double min_Velocity, int max_Vehicles) {

        this.index = index;
        this.initialHeight = initial_Height;
        this.slope = slope;
        this.lenght = lenght;
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
        s.index = this.index;
        s.initialHeight = this.initialHeight;
        s.lenght = this.lenght;
        s.max_Vehicles = this.max_Vehicles;
        s.max_Velocity = this.max_Velocity;
        s.min_Velocity = this.min_Velocity;
        s.slope = this.slope;
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
    public double getInitialHeight() {
        return initialHeight;
    }

    /**
     * 
     * @param initial_Height receives initialHeight
     */
    public void setInitialHeight(double initial_Height) {
        this.initialHeight = initial_Height;
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
    public int getMax_Vehicles() {
        return max_Vehicles;
    }

    /**
     * 
     * @param max_Vehicles max_vehicles
     */
    public void setMax_Vehicles(int max_Vehicles) {
        this.max_Vehicles = max_Vehicles;
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
//        if (this.id != other.id) {
//            return false;
//        }
        if (this.index != other.index) {
            return false;
        }
        if (Double.doubleToLongBits(this.initialHeight) != Double.doubleToLongBits(other.initialHeight)) {
            return false;
        }
        if (Double.doubleToLongBits(this.slope) != Double.doubleToLongBits(other.slope)) {
            return false;
        }
        if (Double.doubleToLongBits(this.lenght) != Double.doubleToLongBits(other.lenght)) {
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

    @Override
    public String toString() {
        return "Segment{" + "index=" + index + ", initial_Height=" + initialHeight + ", slope=" + slope + ", lenght=" + lenght + ", max_Velocity=" + max_Velocity + ", min_Velocity=" + min_Velocity + ", max_Vehicles=" + max_Vehicles  + '}';
    }


  
}
