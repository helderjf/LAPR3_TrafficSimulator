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
    private ArrayList<PathParcel> m_bestPath;
    private ResultStaticAnalysis m_results;
    private ArrayList<Vehicle> m_vehiclesList;
    private ArrayList<Result> m_resultsComparison;
    
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
        m_resultsComparison = new ArrayList<>();
        for (Vehicle v: m_vehiclesList) {
            Result res = runEachVehicle(v);
            m_resultsComparison.add(res);
        }
        return m_resultsComparison;
    }
    
    
    public Result runSingleVehicle(){
        m_bestPath=m_algorithm.getBestPathResults(m_roaNetwork, m_originNode, m_destinyNode, m_vehicle);
        
        m_results= new ResultStaticAnalysis(m_originNode, m_destinyNode);
        m_results.setPath(m_bestPath);
        m_results.setVehicle(m_vehicle);
        m_results.setBestPathAlgorithm(m_algorithm);
        
        return m_results;
    }
    
    private Result runEachVehicle(Vehicle vehicle){
        m_bestPath=m_algorithm.getBestPathResults(m_roaNetwork, m_originNode, m_destinyNode, vehicle);
        
        m_results= new ResultStaticAnalysis(m_originNode, m_destinyNode);
        m_results.setPath(m_bestPath);
        m_results.setVehicle(vehicle);
        m_results.setBestPathAlgorithm(m_algorithm);
        
        return m_results;
    }
    
}
