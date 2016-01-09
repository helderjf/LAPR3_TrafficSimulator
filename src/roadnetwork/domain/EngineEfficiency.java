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
public class EngineEfficiency {
    
    private int gear;
    private int throttleRacio;
    private double result;
    

    public int getGear() {
        return gear;
    }

    public void setGear(int gear) {
        this.gear = gear;
    }

    public int getThrottleRacio() {
        return throttleRacio;
    }

    public void setThrottleRacio(int throttleRacio) {
        this.throttleRacio = throttleRacio;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

}
