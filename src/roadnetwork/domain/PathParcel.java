/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadnetwork.domain;

/**
 *
 * @author josemiranda
 */
public interface PathParcel {
    
    public Section getSection();
    public SimDirection getDirection();
    public void setDirection(SimDirection direction);
    public double getTheoreticalTravelTime();
    public void setTheoreticalTravelTime(double time);
    public double getTheoreticalEnergyConsumption();
    public void setTheoreticalEnergyConsumption(double energyConsumption);
    public double getTollCosts();
    public void setTollCosts(double tollCosts);
    public PathParcel createReversePP();
}
