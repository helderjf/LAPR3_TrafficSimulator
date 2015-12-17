/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadnetwork.domain;

import java.io.File;
import java.util.ArrayList;
import IO.*;
import org.w3c.dom.*;

/**
 *
 * @author André Pedrosa, Hélder Faria, José Miranda, Rubén Rosário
 */
public class Project {

    private RoadNetwork m_roadNetwork;
    private ArrayList<Vehicle> m_vehicleList;
    
    
    
    
    public ArrayList<Vehicle> getVehicleList() {
        return m_vehicleList;
    }

    public RoadNetwork getRoadNetwork() {
        return m_roadNetwork;
    }
    

    public Simulation newBestPathSimulation(RoadNetwork rn, Junction oj, 
            Junction dj, BestPathAlgorithm alg, Vehicle v) {
        return new Simulation(rn, oj, dj, alg, v);
    }
    
    
    
}
