/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadnetwork.domain;

/**
 *
 * @author Andre
 */
public class Wind {
    double angle;
    double velocity;

    /**
     * 
     */
    public Wind()
    {
        this.angle = 0;
        this.velocity = 0;
    }
    
    /**
     * 
     * @param angle angle
     * @param velocity velocity
     */
    public Wind(double angle, double velocity) {
        this.angle = angle;
        this.velocity = velocity;
    }
    
    /**
     * 
     * @param wd WindDirection
     */
    public Wind(Wind otherWind)
    {
        angle=otherWind.angle;
        velocity=otherWind.velocity;
    }

    /**
     * 
     * @return angle
     */
    public double getAngle() {
        return angle;
    }

    /**
     * 
     * @param angle angle
     */
    public void setAngle(double angle) {
        this.angle = angle;
    }

    /**
     * 
     * @return velocity
     */
    public double getVelocity() {
        return velocity;
    }

    /**
     * 
     * @param velocity velocity
     */
    public void setVelocity(double velocity) {
        this.velocity = velocity;
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
        final Wind other = (Wind) obj;
        if (Double.doubleToLongBits(this.angle) != Double.doubleToLongBits(other.angle)) {
            return false;
        }
        if (Double.doubleToLongBits(this.velocity) != Double.doubleToLongBits(other.velocity)) {
            return false;
        }
        return true;
    }

    /**
     * 
     * @return Wind string
     */
    @Override
    public String toString() {
        return "WindDirection{" + "angle=" + angle + ", velocity=" + velocity + '}';
    }
    
   
}
