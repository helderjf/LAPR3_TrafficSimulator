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

    public ArrayList<SimVehicle> updateCurrentVehicles(double currentTime) {
        ArrayList<SimVehicle> endedVehicles = new ArrayList();
        ArrayList<SimSegment> simSegsOrderedByWaitingVehicle = new ArrayList();

        for (SimSegment simSeg : m_simSegmentsList) {
            if (simSeg.getFirstWaitingVehicle(currentTime) != null) {
                simSegsOrderedByWaitingVehicle.add(simSeg);
            }
        }

        SimVehicle currSegVehicle;
        SimPathParcel nextParcel;
        SimSegment nextSimSeg;
        boolean vehicleUpdated = true;

        while (vehicleUpdated == true) {
            vehicleUpdated = false;

            Collections.sort(simSegsOrderedByWaitingVehicle, new WaitingTimeComparator());

            for (SimSegment currSimSeg : simSegsOrderedByWaitingVehicle) {

                currSegVehicle = currSimSeg.getVehicleQueue().peek();

                if (currSegVehicle.willEndAtThisTimeStep(currentTime)) {
                    endedVehicles.add(currSimSeg.updateEndingVehicle(currentTime));

                    vehicleUpdated = true;

                    if (currSimSeg.getFirstWaitingVehicle(currentTime) == null) {
                        simSegsOrderedByWaitingVehicle.remove(currSimSeg);
                    }

                    break;
                }

                currSegVehicle = currSimSeg.getVehicleQueue().peek();
                nextParcel = currSegVehicle.getNextPos();
                nextSimSeg = getSimSegmentByParcel(nextParcel);

                if (nextSimSeg.canAddVehicle()) {

                    currSimSeg.popCrossingVehicle(currentTime);
                    nextSimSeg.pushCrossingVehicle(currentTime, currSegVehicle);
                    vehicleUpdated = true;

                    if (currSimSeg.getFirstWaitingVehicle(currentTime) != null) {
                        currSimSeg.getVehicleQueue().peek().updatePredictedExitTime(currSegVehicle.getPredictedExitTime());
                    } else {
                        simSegsOrderedByWaitingVehicle.remove(currSimSeg);
                    }

                    break;
                }

            }

        }
        return endedVehicles;
    }

    ArrayList<SimVehicle> injectCreatedVehicles(double currentTime, ArrayList<SimVehicle> nextStepVehicles) {

        ArrayList<SimVehicle> droppedVehiclesList = new ArrayList();

        for (SimVehicle simV : nextStepVehicles) {

            SimPathParcel firstParcel = simV.getFirstSimPathParcel();
            SimSegment firstSegment = getSimSegmentByParcel(firstParcel);

            if (firstSegment.canAddVehicle()) {
                firstSegment.injectCreatedVehicle(currentTime, simV);
            } else {
                simV.drop();
                droppedVehiclesList.add(simV);
            }
        }

        return droppedVehiclesList;

    }

    ArrayList<SimVehicle> endSimulation(double time) {
        ArrayList<SimVehicle> cruisingVehicles = new ArrayList();
        for (SimSegment simSeg : m_simSegmentsList) {
            cruisingVehicles.addAll(simSeg.getCruisingVehicles());
        }
        return cruisingVehicles;
    }

    public class WaitingTimeComparator implements Comparator<SimSegment> {

        @Override
        public int compare(SimSegment ss1, SimSegment ss2) {

            if (ss1.getVehicleQueue().peek().getPredictedExitTime() > ss2.getVehicleQueue().peek().getPredictedExitTime()) {
                return 1;
            } else if (ss1.getVehicleQueue().peek().getPredictedExitTime() < ss2.getVehicleQueue().peek().getPredictedExitTime()) {
                return -1;
            }
            return 0;
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
