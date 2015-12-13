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
    }
    
    
    
    
    
    public double calculateTravelTime(Section section){
        ArrayList<Segment> segmentList = section.getSegments();
        double time=0; //in seconds
        for(Segment it : segmentList){
            double maxVel = it.getMax_Velocity();
            double lenght = it.getLenght();
            time+=lenght*3600/maxVel;
        }
        return time;
    }
    
    
    
}
