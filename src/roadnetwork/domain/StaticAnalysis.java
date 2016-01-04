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
public class StaticAnalysis {
    
    private RoadNetwork m_roaNetwork;
    private Junction m_originNode;
    private Junction m_destinyNode;
    private Vehicle m_vehicle;
    private BestPathAlgorithm m_algorithm;
    private Result m_results;
    private ArrayList<Vehicle> m_vehiclesList;
    
    public StaticAnalysis(RoadNetwork rn, Junction oj, 
            Junction dj, BestPathAlgorithm alg, Vehicle v){
        
        m_roaNetwork=rn;
        m_originNode=oj;
        m_destinyNode=dj;
        m_algorithm=alg;
        m_vehicle=v;
    }
    
    public StaticAnalysis(RoadNetwork rn, Junction oj, 
            Junction dj, BestPathAlgorithm alg, ArrayList<Vehicle> vlst){
        
        m_roaNetwork=rn;
        m_originNode=oj;
        m_destinyNode=dj;
        m_algorithm=alg;
        m_vehiclesList=vlst;
    }
    
    public ArrayList<Result> runComparison(){
        ArrayList<Result> resultsList = new ArrayList<>();
        for (Vehicle v: m_vehiclesList) {
            Result res = runEachVehicle(v);
            resultsList.add(res);
        }
        return resultsList;
    }
    
    
    public Result runSingleVehicle(){
        return m_algorithm.bestPath(m_roaNetwork, m_originNode, m_destinyNode, m_vehicle);
    }
    
    private Result runEachVehicle(Vehicle vehicle){
        return m_algorithm.bestPath(m_roaNetwork, m_originNode, m_destinyNode, vehicle);
    }
    
}
