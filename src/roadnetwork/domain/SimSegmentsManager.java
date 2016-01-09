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

    public class WaitingTimeComparator implements Comparator {

        @Override
        public int compare(Object o1, Object o2) {

            SimSegment s1 = (SimSegment) o1;
            SimSegment s2 = (SimSegment) o2;
            if (s1.getVehicleQueue().peek().getArrivalTime() >= s2.getVehicleQueue().peek().getArrivalTime()) {
                return 1;
            }
            return -1;

        }

    }

    private ArrayList<SimSegment> orderSegmentsByWaitingVehicles(ArrayList<SimSegment> simSegsWithWaitingVehicle) {

        SimSegment[] simSegmentsArr = (SimSegment[]) simSegsWithWaitingVehicle.toArray();
        Arrays.sort(simSegmentsArr, new WaitingTimeComparator());

        ArrayList<SimSegment> orderedSimSegments = new ArrayList();

        int j = 0;
        if (simSegmentsArr.length != 0) {
            j = simSegmentsArr.length;
        }

        for (int i = j; i < simSegmentsArr.length; i++) {
            orderedSimSegments.add(simSegmentsArr[i]);
        }

        return orderedSimSegments;
    }

    private SimSegment getSimSegmentByParcel(SimPathParcel parcel) {

        for (SimSegment simSeg : m_simSegmentsList) {
            if (simSeg.getSegment() == parcel.getSegment()
                    && simSeg.getDirection() == parcel.getDirection()) {
                return simSeg;
            }
        }
        return null;
    }

}
