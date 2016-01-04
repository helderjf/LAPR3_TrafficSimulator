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
    
    /**
     * 
     * @param roadNetwork roadNetwork
     * @param originNode originNode
     * @param destinyNode destinyNode
     * @param vehicle vehicle
     * @return bestPath
     */
    public Result bestPath(RoadNetwork roadNetwork, 
            Junction originNode, 
            Junction destinyNode, 
            Vehicle vehicle);
    
    
}
