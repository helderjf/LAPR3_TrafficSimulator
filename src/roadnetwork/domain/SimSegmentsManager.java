/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadnetwork.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author André Pedrosa, Hélder Faria, José Miranda, Rubén Rosário
 */

public class SimSegmentsManager {

    private RoadNetwork m_roadNetwork;
    private ArrayList<SimSegment> m_simSegmentsList;

    public SimSegmentsManager(RoadNetwork roadNetwork) {
        m_roadNetwork = roadNetwork;
        m_simSegmentsList = createSimSegmentsList(roadNetwork);
    }

    private ArrayList<SimSegment> createSimSegmentsList(RoadNetwork roadNetwork) {
        ArrayList<SimSegment> simSegmentsList = new ArrayList();
        ArrayList<Section> sectionList = roadNetwork.getSectionList();
        for (Section section : sectionList) {
            ArrayList<Segment> segmentList = section.getSegmentsList();
            for (Segment segment : segmentList) {

                if (section.getDirection() == SectionDirection.unidirectional) {
                    simSegmentsList.add(new SimSegment(section, segment, SimDirection.direct));
                } else {
                    simSegmentsList.add(new SimSegment(section, segment, SimDirection.direct));
                    simSegmentsList.add(new SimSegment(section, segment, SimDirection.reverse));
                }
            }
        }
        return simSegmentsList;
    }

    ArrayList<SimVehicle> popEndingVehicles(double currentTime) {

        ArrayList<SimVehicle> endedVehicles = new ArrayList();

        for (SimSegment simSeg : m_simSegmentsList) {
            endedVehicles.addAll(simSeg.updateEndingVehicles(currentTime));
        }
        return endedVehicles;
    }

    void updateCrossingVehicles(double currentTime) {
        int moovedVehicles = 0;
        ArrayList<SimSegment> simSegsOrderedByWaitingVehicle = new ArrayList();

        for (SimSegment simSeg : m_simSegmentsList) {
            if (simSeg.getFirstWaitingVehicle(currentTime) != null) {
                simSegsOrderedByWaitingVehicle.add(simSeg);
            }
        }

        Collections.sort(simSegsOrderedByWaitingVehicle, new WaitingTimeComparator());

        boolean vehicleUpdated = true;

        while (vehicleUpdated == true) {

            vehicleUpdated = false;

            SimVehicle currSegVehicle;
            SimPathParcel nextParcel;
            SimSegment nextSimSeg;
            for (SimSegment currSimSeg : simSegsOrderedByWaitingVehicle) {

                currSegVehicle = currSimSeg.getVehicleQueue().peek();
                nextParcel = currSegVehicle.getNextPos();
                nextSimSeg = getSimSegmentByParcel(nextParcel);

                if (nextSimSeg.canInjectVehicle()) {

                    currSimSeg.popCrossingVehicle(currentTime);
                    nextSimSeg.injectCrossingVehicle(currentTime, currSegVehicle);

                    if (currSimSeg.getFirstWaitingVehicle(currentTime) == null) {
                        simSegsOrderedByWaitingVehicle.remove(currSimSeg);
                    }

                    Collections.sort(simSegsOrderedByWaitingVehicle, new WaitingTimeComparator());
                    vehicleUpdated = true;
                    moovedVehicles++;
                }

            }

        }

    }

    ArrayList<SimVehicle> injectNewVehicles(ArrayList<SimVehicle> nextStepVehicles) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public class WaitingTimeComparator implements Comparator<SimSegment> {

        @Override
        public int compare(SimSegment ss1, SimSegment ss2) {

            if (ss1.getVehicleQueue().peek().getArrivalTime() >= ss2.getVehicleQueue().peek().getArrivalTime()) {
                return 1;
            }
            return -1;

        }

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
