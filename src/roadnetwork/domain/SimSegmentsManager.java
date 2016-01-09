/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadnetwork.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

/**
 *
 * @author André Pedrosa, Hélder Faria, José Miranda, Rubén Rosário
 */
public class SimSegmentsManager {

    private RoadNetwork m_roadNetwork;
    private ArrayList<SimSegment> m_simSegmentsList;

    public SimSegmentsManager(RoadNetwork roadNetwork) {
        //TO DO
    }

    ArrayList<SimVehicle> popEndingVehicles(double currentTime) {

        ArrayList<SimVehicle> endedVehicles = new ArrayList();

        for (SimSegment simSeg : m_simSegmentsList) {
            endedVehicles.addAll(simSeg.updateEndingVehicles(currentTime));
        }
        return endedVehicles;
    }

    void updateCrossingVehicles(double currentTime) {

        HashMap<SimSegment, SimVehicle> waitingVehicles = new HashMap();
        ArrayList<SimSegment> simSegsWithWaitingVehicle=new ArrayList();
        
        for (SimSegment simSeg : m_simSegmentsList) {
            if (simSeg.getFirstWaitingVehicle(currentTime) != null) {
                waitingVehicles.put(simSeg, simSeg.getFirstWaitingVehicle(currentTime));
                simSegsWithWaitingVehicle.add(simSeg);
            }
        }

        ArrayList<SimSegment> simSegsOrderedByWaitingVehicle = orderSegmentsByWaitingVehicles(simSegsWithWaitingVehicle);

        boolean vehicleUpdated = true;

        while (vehicleUpdated == true) {

            vehicleUpdated = false;

            SimVehicle currSegVehicle;
            SimPathParcel nextParcel;
            SimSegment nextSimSeg;
            for (SimSegment currSimSeg: simSegsOrderedByWaitingVehicle) {
                
                currSegVehicle = currSimSeg.getVehicleQueue().peek();
                nextParcel = currSegVehicle.getNextPos();

                nextSimSeg = getSimSegmentByParcel(nextParcel);
                if (nextSimSeg.canInjectVehicle()) {
                    currSimSeg.popCrossingVehicle(currentTime);
                    nextSimSeg.injectCrossingVehicle(currentTime,currSegVehicle);
                    if(currSimSeg.getFirstWaitingVehicle(currentTime)==null){
                    simSegsOrderedByWaitingVehicle.remove(currSimSeg);
                    }
                    
                    vehicleUpdated=true;
                    simSegsOrderedByWaitingVehicle=orderSegmentsByWaitingVehicles(simSegsWithWaitingVehicle);
                }

            }

        }

    }

    ArrayList<SimVehicle> injectNewVehicles(ArrayList<SimVehicle> nextStepVehicles) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}
