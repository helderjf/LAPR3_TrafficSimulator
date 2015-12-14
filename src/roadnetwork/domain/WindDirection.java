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
public class WindDirection {
    double angle;
    double velocity;

    /**
     * 
     */
    public WindDirection()
    {
        this.angle = 0;
        this.velocity = 0;
    }
    
    /**
     * 
     * @param angle angle
     * @param velocity velocity
     */
    public WindDirection(double angle, double velocity) {
        this.angle = angle;
        this.velocity = velocity;
    }
    
    /**
     * 
     * @param wd WindDirection
     */
    public WindDirection(WindDirection wd)
    {
        wd.angle = this.angle;
        wd.velocity = this.velocity;
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
     * @return hash
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + (int) (Double.doubleToLongBits(this.angle) ^ (Double.doubleToLongBits(this.angle) >>> 32));
        hash = 83 * hash + (int) (Double.doubleToLongBits(this.velocity) ^ (Double.doubleToLongBits(this.velocity) >>> 32));
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
        final WindDirection other = (WindDirection) obj;
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
     * @return WindDirection string
     */
    @Override
    public String toString() {
        return "WindDirection{" + "angle=" + angle + ", velocity=" + velocity + '}';
    }
    
   
}
