/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadnetwork.domain;

import java.util.ArrayList;

/**
 *
 * @author André Pedrosa, Hélder Faria, José Miranda, Rubén Rosário
 */
public interface BestPathAlgorithm {
    
    
    public SimulationResult bestPath(RoadNetwork roadNetwork, 
            Node originNode, 
            Node destinyNode, 
            ArrayList<Vehicle> vehicleList);
    
    
}
