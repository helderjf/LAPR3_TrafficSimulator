/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.access.layer;

import java.util.ArrayList;
import java.util.HashMap;
import roadnetwork.domain.CombustionVehicle;
import roadnetwork.domain.ElectricVehicle;
import roadnetwork.domain.HybridVehicle;
import roadnetwork.domain.Junction;
import roadnetwork.domain.Project;
import roadnetwork.domain.Regime;
import roadnetwork.domain.ResultSimulation;
import roadnetwork.domain.RoadNetwork;
import roadnetwork.domain.Section;
import roadnetwork.domain.SectionTypology;
import roadnetwork.domain.Segment;
import roadnetwork.domain.SimPathParcel;
import roadnetwork.domain.SimVehicle;
import roadnetwork.domain.Simulation;
import roadnetwork.domain.SimulationRun;
import roadnetwork.domain.Throttle;
import roadnetwork.domain.TrafficPattern;
import roadnetwork.domain.Vehicle;
import roadnetwork.state.SimulationState;

/**
 *
 * @author André Pedrosa, Hélder Faria, José Miranda, Rubén Rosário
 */
public class ProjectWriter {

    private DataAccessObject m_dao;
    private Project m_project;

    public ProjectWriter(DataAccessObject dao) {
        m_dao = dao;
    }

    public boolean saveNewProject(Project project) {

        m_project = project;

        if (!saveNewProjectProperties()) {
            m_dao.rollback();
            return false;
        }

        if (m_project.hasRoadNetwork()) {
            if (!saveNewProjectRoadNetwork()) {
                m_dao.rollback();
                return false;
            }
        }

        if (m_project.hasVehicles()) {
            if (!saveNewProjectVehicles()) {
                m_dao.rollback();
                return false;
            }
        }

        if (m_project.hasSimulation()) {
            if (!saveNewSimulation()) {
                m_dao.rollback();
                return false;
            }
        }

        m_dao.commit();
        return true;//TO DO fazer verificação
    }

    public boolean updateProject(Project project) {

        m_project = project;

        if (!updateProjectProperties()) {
            m_dao.rollback();
            return false;
        }

        if (m_project.hasRoadNetwork()) {
            if (!updateProjectRoadNetwork()) {
                m_dao.rollback();
                return false;
            }
        }

        if (m_project.hasVehicles()) {
            if (!updateProjectVehicles()) {
                m_dao.rollback();
                return false;
            }
        }

        if (m_project.hasSimulation()) {
            if (!updateProjectSimulation()) {
                m_dao.rollback();
                return false;
            }
        }

        m_dao.commit();
        return true;
    }

    private boolean saveNewProjectProperties() {
        String projectName = m_project.getName();
        String projectDescription = m_project.getDescription();
        String projectState = m_project.getState().getClass().getSimpleName();

        int projectPK = m_dao.saveNewProject(projectName, projectDescription, projectState);
        if (projectPK != -1) {
            m_project.setPK(projectPK);
            return true;
        } else {
            return false;
        }

    }

    private boolean saveNewProjectRoadNetwork() {
        RoadNetwork roadNetwork = m_project.getRoadNetwork();

        String roadNetworkName = roadNetwork.getName();
        String roadNetworkDescription = roadNetwork.getDescription();

        int roadNetworkPK = m_dao.saveNewRoadNetwork(m_project.getPK(), roadNetworkName, roadNetworkDescription);

        if (roadNetworkPK == 0) {
            return false;
        } else {
            roadNetwork.setPK(roadNetworkPK);
            return (saveNewProjectNodeList(roadNetwork)
                    && saveNewProjectRoadNetworkSections(roadNetwork));
        }
    }

    private boolean saveNewProjectNodeList(RoadNetwork roadNetwork) {
        ArrayList<Junction> nodeList = roadNetwork.getNodeList();
        for (Junction it : nodeList) {
            it.setPK(m_dao.saveNewNode(roadNetwork.getPK(), it.getJunctionId()));
            if (it.getPK() == 0) {
                return false;
            }
        }
        return true;
    }

    private boolean saveNewProjectRoadNetworkSections(RoadNetwork roadNetwork) {
        ArrayList<Section> sectionList = roadNetwork.getSectionList();
        for (Section section : sectionList) {
            section.setPK(m_dao.saveNewSection(
                    roadNetwork.getPK(),
                    section.getRoadName(),
                    section.getBeginningNode().getPK(),
                    section.getEndingNode().getPK(),
                    section.getTypology(),
                    section.getDirection(),
                    section.getToll(),
                    section.getWind().getAngle(),
                    section.getWind().getVelocity()));
            if (section.getPK() <= 0) {
                return false;
            }

            ArrayList<Segment> segmentList = section.getSegmentsList();
            if (!saveNewProjectSectionSegments(section.getPK(), segmentList)) {
                return false;
            }
        }
        return true;
    }

    private boolean saveNewProjectSectionSegments(int sectionPK, ArrayList<Segment> segmentList) {
        for (Segment segment : segmentList) {//TO DO verificação de que não ha index de segmentos repetidos para a mesma section
            if (m_dao.saveNewSegment(
                    sectionPK,
                    segment.getIndex(),
                    segment.getInitialHeight(),
                    segment.getSlope(),
                    segment.getLenght(),
                    segment.getMax_Velocity(),
                    segment.getMin_Velocity(),
                    segment.getMax_Vehicles()
            ) == -1) {
                return false;
            }
        }
        return true;
    }

    private boolean saveNewProjectVehicles() {
        ArrayList<Vehicle> vehicleList = m_project.getVehicleList();
        for (Vehicle vehicle : vehicleList) {
            if (!saveNewVehicle(vehicle)) {
                return false;
            }
        }
        return true;
    }

    private boolean saveNewVehicle(Vehicle vehicle) {
        int vehiclePK = m_dao.saveNewVehicle(
                m_project.getPK(),
                vehicle.getName(),
                vehicle.getDescription(),
                vehicle.getType(),
                vehicle.getFuel(),
                vehicle.getMass(),
                vehicle.getLoad(),
                vehicle.getDragCoefficient(),
                vehicle.getFrontalArea(),
                vehicle.getRrc(),
                vehicle.getWheelSize(),
                vehicle.getFinalDriveRatio(),
                vehicle.getMinRPM(),
                vehicle.getMaxRPM()
        );

        if (vehiclePK == -1) {
            return false;
        }

        vehicle.setPK(vehiclePK);
        if (!saveNewVehicleVelocityLimits(vehicle)) {
            return false;
        }

        if (vehicle instanceof CombustionVehicle) {
            if (!saveNewCombustionVehicle(vehicle)) {
                return false;
            }
        } else if (vehicle instanceof HybridVehicle) {
            if (!saveNewHybridVehicle(vehicle)) {
                return false;
            }
        } else if (vehicle instanceof ElectricVehicle) {
            if (!saveNewElectricVehicle(vehicle)) {
                return false;
            }
        }

        return true;
    }

    private boolean saveNewVehicleVelocityLimits(Vehicle vehicle) {

        HashMap<SectionTypology, Double> limits = vehicle.getVelocityLimits();

        for (SectionTypology typology : vehicle.getVelocityLimits().keySet()) {
            if (m_dao.saveNewVehicleVelocityLimits(vehicle.getPK(), typology, limits.get(typology)) != 1) {
                return false;
            }

        }
        return true;
    }

    private boolean saveNewCombustionVehicle(Vehicle vehicle) {

        if (m_dao.saveNewCombustionVehicle(vehicle.getPK()) != 1) {
            return false;
        }
        return (saveNewVehicleGears(vehicle)
                && saveNewVehicleThrottle(vehicle));
    }

    private boolean saveNewHybridVehicle(Vehicle vehicle) {
        if (m_dao.saveNewHybridVehicle(vehicle.getPK(), ((HybridVehicle) vehicle).getEnergyRegenerationRatio()) != 1) {
            return false;
        }
        return (saveNewVehicleGears(vehicle)
                && saveNewVehicleThrottle(vehicle));
    }

    private boolean saveNewElectricVehicle(Vehicle vehicle) {
        if (m_dao.saveNewElectricVehicle(vehicle.getPK(), ((ElectricVehicle) vehicle).getEnergyRegenerationRatio()) != 1) {
            return false;
        }
        return (saveNewVehicleGears(vehicle)
                && saveNewVehicleThrottle(vehicle));
    }

    private boolean saveNewVehicleGears(Vehicle vehicle) {
        HashMap<Integer, Double> gears = vehicle.getGearList();
        for (Integer gear : gears.keySet()) {
            if (m_dao.saveNewVehicleGear(vehicle.getPK(), gear, gears.get(gear)) != 1) {
                return false;
            }
        }
        return true;
    }

    private boolean saveNewVehicleThrottle(Vehicle vehicle) {
        ArrayList<Throttle> throttleList = ((CombustionVehicle) vehicle).getThrottleList();//TO DO alterar para a interface cmobustion, quando soublermos se o hybrid tambem vai ter gears
        for (Throttle throttle : throttleList) {
            ArrayList<Regime> regimeList = throttle.getRegimeList();
            for (Regime regime : regimeList) {
                if (m_dao.saveNewVehicleRegime(
                        vehicle.getPK(),
                        throttle.getID(),
                        regimeList.indexOf(regime) + 1,
                        regime.getTorque(),
                        regime.getRPMLow(),
                        regime.getRPMHigh(),
                        regime.getSfc()) != 1) {
                    return false;
                }

            }
        }
        return true;
    }

    private boolean saveNewSimulation() {
        Simulation sim = m_project.getCurrentSimulation();
        String simName = sim.getName();
        String simDesc = sim.getDescription();
        String simState = sim.getState().getClass().getSimpleName();

        int simPK = m_dao.saveNewSimulation(m_project.getPK(), simName, simDesc, simState);

        if (simPK <= 0) {
            return false;
        }

        sim.setPK(simPK);

        ArrayList<TrafficPattern> traffPatList = sim.getTrafficPatternList();

        int bNodePK;
        int eNodePK;
        int vPK;
        double aRate;

        for (TrafficPattern tp : traffPatList) {

            bNodePK = tp.getBeginNode().getPK();
            eNodePK = tp.getEndNode().getPK();
            vPK = tp.getVehicle().getPK();
            aRate = tp.getArrivalRate();

            int tpPK = m_dao.saveNewTrafficPattern(simPK, bNodePK, eNodePK, vPK, aRate);
            if (tpPK <= 0) {
                return false;
            }
            tp.setPK(tpPK);
        }
        return true;

    }

    public boolean saveSimulationRun() {
        Simulation sim = m_project.getCurrentSimulation();
        int simPK = sim.getPK();
        SimulationRun run = sim.getCurrentRun();
        ResultSimulation runResults = run.getResults();
        String runName = run.getName();
        double duration = run.getDuration();
        double timeStep = run.getTimeStep();
        String bpm = run.getBestPathMethod().toString();

        int runPK = m_dao.saveNewRun(simPK, runName, duration, timeStep, bpm);
        if (runPK <= 0) {
            return false;
        }
        run.setPK(runPK);

        ArrayList<SimVehicle> droppedVehiclesList = runResults.getDroppedVehicles();
        ArrayList<SimVehicle> endedVehiclesList = runResults.getEndedVehicles();
        //ArrayList<SimVehicle> cruisingVehiclesList = runResults.getCruisingVehicles();

        int[] droppedTrafPatList = new int[droppedVehiclesList.size()];
        double[] droppedIntantsList = new double[droppedVehiclesList.size()];
        fillDroppedArrays(droppedVehiclesList, droppedTrafPatList, droppedIntantsList);

        if (m_dao.saveRunDroppedVehicles(runPK, droppedTrafPatList, droppedIntantsList) == -1) {
            return false;
        }

        int[] injectedVTrafPatList = new int[endedVehiclesList.size()];
        int[] injectedVSection = new int[endedVehiclesList.size()];
        int[] injectedVSegment = new int[endedVehiclesList.size()];
        String[] injectedVTravelDirection = new String[endedVehiclesList.size()];
        double[] injectedVTimeIn = new double[endedVehiclesList.size()];
        double[] injectedVTimeOut = new double[endedVehiclesList.size()];
        double[] injectedVEnergy = new double[endedVehiclesList.size()];
        fillInjecttedArrays(endedVehiclesList,
                // cruisingVehiclesList,
                injectedVTrafPatList,
                injectedVSection,
                injectedVSegment,
                injectedVTravelDirection,
                injectedVTimeIn,
                injectedVTimeOut,
                injectedVEnergy);

        if (m_dao.saveRunInjectedVehicles(runPK,
                injectedVTrafPatList,
                injectedVSection,
                injectedVSegment,
                injectedVTravelDirection,
                injectedVTimeIn,
                injectedVTimeOut,
                injectedVEnergy) == -1) {
            return false;
        }
        return true;

    }

    private void fillDroppedArrays(ArrayList<SimVehicle> droppedVehiclesList, int[] droppedTrafPatList, double[] droppedInstantsList) {
        for (int i = 0; i < droppedVehiclesList.size(); i++) {
            droppedTrafPatList[i] = droppedVehiclesList.get(i).getTrafficPattern().getPK();
            droppedInstantsList[i] = droppedVehiclesList.get(i).getdroppedTime();
        }
    }

    private void fillInjecttedArrays(ArrayList<SimVehicle> endedVehiclesList, int[] injectedVTrafPatList, int[] injectedVSection, int[] injectedVSegment, String[] injectedVTravelDirection, double[] injectedVTimeIn, double[] injectedVTimeOut, double[] injectedVEnergy) {
        for (int i = 0; i < endedVehiclesList.size(); i++) {
            injectedVTrafPatList[i] = endedVehiclesList.get(i).getTrafficPattern().getPK();

            for (SimPathParcel pathParcel : endedVehiclesList.get(i).getPath()) {
                injectedVSection[i] = pathParcel.getSection().getPK();
                injectedVSegment[i] = pathParcel.getSegment().getIndex();
                injectedVTravelDirection[i] = pathParcel.getDirection().toString();
                injectedVTimeIn[i] = pathParcel.getSimInTime();
                injectedVTimeOut[i] = pathParcel.getSimExitTime();
                injectedVEnergy[i] = pathParcel.getSimEnergyConsumption();
            }
        }
//        for (int i = endedVehiclesList.size(); i < (endedVehiclesList.size() + cruisingVehiclesList.size()); i++) {
//            injectedVTrafPatList[i] = cruisingVehiclesList.get(i - endedVehiclesList.size()).getTrafficPattern().getPK();
//        }

    }

    private boolean updateProjectProperties() {
        int projectPK = m_project.getPK();
        String projectName = m_project.getName();
        String projectDescription = m_project.getDescription();
        String projectState = m_project.getState().getClass().getSimpleName();

        if (m_dao.updateProject(projectPK, projectName, projectDescription, projectState) == -1) {
            return false;
        }
        return true;
    }

    private boolean updateProjectRoadNetwork() {
        RoadNetwork roadNetwork = m_project.getRoadNetwork();

        if (!roadNetwork.hasPK()) {
            return saveNewProjectRoadNetwork();
        }

        //we can comment out this section, because a road network, once saved, will never be changed
        int roadNetworkPK = roadNetwork.getPK();
        String roadNetworkName = roadNetwork.getName();
        String roadNetworkDescription = roadNetwork.getDescription();

        if (m_dao.updateRoadNetwork(roadNetwork.getPK(), roadNetworkName, roadNetworkDescription) == -1) {
            return false;
        } else {
            return (updateNodeList(roadNetwork)
                    && updateRoadNetworkSections(roadNetwork));
        }
    }

    private boolean updateNodeList(RoadNetwork roadNetwork) {
        ArrayList<Junction> nodeList = roadNetwork.getNodeList();
        for (Junction it : nodeList) {
            if (m_dao.updateNode(it.getPK(), it.getJunctionId()) == -1) {
                return false;
            }
        }
        return true;
    }

    private boolean updateRoadNetworkSections(RoadNetwork roadNetwork) {
        ArrayList<Section> sectionList = roadNetwork.getSectionList();
        for (Section section : sectionList) {
            if (m_dao.updateSection(
                    section.getPK(),
                    section.getRoadName(),
                    section.getBeginningNode().getPK(),
                    section.getEndingNode().getPK(),
                    section.getTypology(),
                    section.getDirection(),
                    section.getToll(),
                    section.getWind().getAngle(),
                    section.getWind().getVelocity()) == -1) {
                return false;
            }

            ArrayList<Segment> segmentList = section.getSegmentsList();
            if (!updateSectionSegments(section.getPK(), segmentList)) {
                return false;
            }
        }
        return true;
    }

    private boolean updateSectionSegments(int sectionPK, ArrayList<Segment> segmentList) {
        for (Segment segment : segmentList) {//TO DO verificação de que não ha index de segmentos repetidos para a mesma section
            if (m_dao.updateSegment(
                    sectionPK,
                    segment.getIndex(),
                    segment.getInitialHeight(),
                    segment.getSlope(),
                    segment.getLenght(),
                    segment.getMax_Velocity(),
                    segment.getMin_Velocity(),
                    segment.getMax_Vehicles()
            ) == -1) {
                return false;
            }
        }
        return true;
    }

    private boolean updateProjectVehicles() {
        ArrayList<Vehicle> vehicleList = m_project.getVehicleList();
        for (Vehicle vehicle : vehicleList) {
            if (!vehicle.hasPK()) {
                if (!saveNewVehicle(vehicle)) {
                    return false;
                }
            }

        }
        return true;
    }

    private boolean updateProjectSimulation() {
        Simulation sim = m_project.getCurrentSimulation();

        if (!sim.hasPK()) {
            return saveNewSimulation();
        }

        int simpk = sim.getPK();
        String simName = sim.getName();
        String simDesc = sim.getDescription();
        String simState = sim.getState().getClass().getSimpleName();

        if (m_dao.updateSimulation(simpk, simName, simDesc, simState) == -1) {
            return false;
        }

        ArrayList<TrafficPattern> traffPatList = sim.getTrafficPatternList();
        int tpPK;
        int bNodePK;
        int eNodePK;
        int vPK;
        double aRate;

        for (TrafficPattern tp : traffPatList) {

            tpPK = tp.getPK();
            bNodePK = tp.getBeginNode().getPK();
            eNodePK = tp.getEndNode().getPK();
            vPK = tp.getVehicle().getPK();
            aRate = tp.getArrivalRate();

            if (m_dao.updateTrafficPattern(tpPK, bNodePK, eNodePK, vPK, aRate) == -1) {
                return false;
            }
        }
        return true;
    }
    
    
    
    public boolean projectCopy(Project project, String name, String description){
        
        //TODO
        
        return true;
    }

}
