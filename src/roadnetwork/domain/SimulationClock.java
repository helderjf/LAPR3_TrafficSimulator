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
public class SimulationClock {

    private RoadNetwork m_roadNetwork;
    private ArrayList<SimSegment> m_simSegmentsList;

    public SimulationClock(RoadNetwork roadNetwork) {
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

        for (SimSegment simSeg : m_simSegmentsList) {//Construct list of all segments containing vehicles that have arrive to the end of segment during this time step
            if (simSeg.getVehicleQueue().peek() != null
                    && simSeg.getVehicleQueue().peek().getPredictedExitTime() < currentTime) {

                simSegsOrderedByWaitingVehicle.add(simSeg);
            }
        }

        SimVehicle currSegVehicle;
        SimPathParcel nextParcel;
        double currSegVehiclePredictedExitTime;
        SimSegment nextSimSeg;
        boolean vehicleUpdated = true;

        while (vehicleUpdated == true) {
            vehicleUpdated = false;

            Collections.sort(simSegsOrderedByWaitingVehicle, new WaitingTimeComparator());//order list by vehicle arrival time

            for (SimSegment currSimSeg : simSegsOrderedByWaitingVehicle) {

                currSegVehicle = currSimSeg.getVehicleQueue().peek();//get first vehicel of the queue
                currSegVehiclePredictedExitTime = currSegVehicle.getPredictedExitTime();//save for comparing with next vehicle

                if (currSegVehicle.willEndAtThisTimeStep(currentTime)) {//is vehicle ending it's simulation

                    currSimSeg.popCrossingVehicle();//remove vehicle from segment
                    currSegVehicle.endSimulation(currentTime);//instruct vehicle to end its simulation
                    endedVehicles.add(currSegVehicle);//add veicle to ended vehicles container

                    if (currSimSeg.getVehicleQueue().peek() != null) {
                        if (currSimSeg.getVehicleQueue().peek().getPredictedExitTime() >= currentTime) {//will the next vehicle of the queue also arrive to the end of the segment in this timestep?
                            simSegsOrderedByWaitingVehicle.remove(currSimSeg);//if not --> remove segment from list
                        } else if (currSimSeg.getVehicleQueue().peek().getPredictedExitTime() < currSegVehiclePredictedExitTime) {//is the predicted exit time earlier than the previous vehicle
                            currSimSeg.getVehicleQueue().peek().updatePredictedExitTime(currSegVehiclePredictedExitTime);//if it is it will be updated to the same as the previous vehicle
                        }
                    } else {
                        simSegsOrderedByWaitingVehicle.remove(currSimSeg);//if not --> remove segment from list
                    }
                    vehicleUpdated = true;
                    break;
                }

                nextParcel = currSegVehicle.getNextPos();//get vehicle's next path position
                nextSimSeg = getSimSegmentByParcel(nextParcel);//determine which SimSegment corresponds to the SimPathParcel retrieved

                if (nextSimSeg.canAddVehicle()) {

                    if (nextSimSeg.pushCrossingVehicle(currSegVehicle)) {
                        currSimSeg.popCrossingVehicle();
                        currSegVehicle.crossToNextPos(currentTime);
                    }
                    vehicleUpdated = true;

                    if (currSimSeg.getVehicleQueue().peek() != null) {
                        if (currSimSeg.getVehicleQueue().peek().getPredictedExitTime() >= currentTime) {//will the next vehicle of the queue also arrive to the end of the segment in this timestep?
                            simSegsOrderedByWaitingVehicle.remove(currSimSeg);//if not --> remove segment from list
                        } else if (currSimSeg.getVehicleQueue().peek().getPredictedExitTime() < currSegVehiclePredictedExitTime) {//is the predicted exit time earlier than the previous vehicle
                            currSegVehicle.updatePredictedExitTime(currSegVehiclePredictedExitTime);//if it is it will be updated to the same as the previous vehicle
                        }
                    } else {
                        simSegsOrderedByWaitingVehicle.remove(currSimSeg);//if not --> remove segment from list
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
                firstSegment.injectCreatedVehicle(simV);
                simV.setInjected();
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
