/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadnetwork.domain;

import static java.lang.Math.log;
import static java.lang.Math.random;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author André Pedrosa, Hélder Faria, José Miranda, Rubén Rosário
 */
public class SimVehiclesGenerator {

    private RoadNetwork m_roadNetwork;
    private double m_timeStep;
    private ArrayList<TrafficPattern> m_trafPatternList;
    private BestPathAlgorithm m_bestPathAlg;

    public SimVehiclesGenerator(RoadNetwork roadNetwork, double timeStep, ArrayList<TrafficPattern> trafficPatternList, BestPathAlgorithm bestPathAlgorithm) {
        m_roadNetwork = roadNetwork;
        m_timeStep = timeStep;
        m_trafPatternList = trafficPatternList;
        m_bestPathAlg = bestPathAlgorithm;

    }

    ArrayList<SimVehicle> generateNextStepVehicles(double currentTime) {

        ArrayList<SimVehicle> simVehicleList = new ArrayList();

        for (TrafficPattern tp : m_trafPatternList) {

            Vehicle vehicle = tp.getVehicle();
            Junction originNode = tp.getBeginNode();
            Junction destinyNode = tp.getEndNode();
            double arrivalRate = tp.getArrivalRate();//units:  1/m
            ArrayList<SimPathParcel> simPath = m_bestPathAlg.getBestPath(m_roadNetwork, originNode, destinyNode, vehicle);

            int numberVehiclesToInject = (int) (arrivalRate * m_timeStep);
            double injectionTime = currentTime;
            for (int i = 0; i < numberVehiclesToInject; i++) {
                injectionTime += (-log(1 - random()) / (arrivalRate));
                if (injectionTime < (currentTime + m_timeStep)) {
                    simVehicleList.add(new SimVehicle(vehicle, originNode, destinyNode, simPath, tp, injectionTime));
                } else {
                    break;
                }
            }

        }

        Collections.sort(simVehicleList, new ArrivingVehiclesComparator());

        return simVehicleList;
    }

    public class ArrivingVehiclesComparator implements Comparator<SimVehicle> {

        @Override
        public int compare(SimVehicle sv1, SimVehicle sv2) {

            if (sv1.getInjectionTime() > sv2.getInjectionTime()) {
                return 1;
            } else if (sv1.getInjectionTime() < sv2.getInjectionTime()) {
                return -1;
            }
            return 0;

        }

    }

}
