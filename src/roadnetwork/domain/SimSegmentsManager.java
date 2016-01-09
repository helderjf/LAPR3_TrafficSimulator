/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadnetwork.domain;

import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author André Pedrosa, Hélder Faria, José Miranda, Rubén Rosário
 */
public class SimSegmentsManager {
    
    private RoadNetwork m_roadNetwork;
    
    public SimSegmentsManager(RoadNetwork roadNetwork){
        //TO DO
    }

    Collection<? extends SimVehicle> popEndingVehicles(double m_currentTime) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    void updateCrossingVehicles(double m_currentTime) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    Collection<? extends SimVehicle> injectNewVehicles(ArrayList<SimVehicle> nextStepVehicles) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
