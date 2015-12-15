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
public class FastestPathAlgorithm implements BestPathAlgorithm{

    
    @Override
    public SimulationResult bestPath(RoadNetwork roadNetwork, Node originNode, Node destinyNode, ArrayList<Vehicle> vehicleList) {
        //TO DO implementar
        return null;
    }
    
    
    
    
    
    public double calculateTravelTime(Section section, Vehicle vehicle){
        
        ArrayList<Segment> segmentList = section.getSegmentsList();
        double time=0; //in seconds
        for(Segment it : segmentList){
            double maxVel;
            
            if(it.getMax_Velocity() <= vehicle.getMaximumSpeed()){
                maxVel = it.getMax_Velocity();
            }else{
                maxVel = vehicle.getMaximumSpeed();
            }
            double lenght = it.getLenght();
            time+=lenght*3600/maxVel;
        }
        return time;
        
    }
    
    
    
}
