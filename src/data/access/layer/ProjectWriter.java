/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.access.layer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import roadnetwork.domain.CombustionVehicle;
import roadnetwork.domain.ElectricVehicle;
import roadnetwork.domain.HybridVehicle;
import roadnetwork.domain.Junction;
import roadnetwork.domain.Project;
import roadnetwork.domain.Regime;
import roadnetwork.domain.RoadNetwork;
import roadnetwork.domain.Section;
import roadnetwork.domain.Segment;
import roadnetwork.domain.Throttle;
import roadnetwork.domain.Vehicle;

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
            if (section.getPK() == 0) {
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
                vehicle.getMass(),
                vehicle.getLoad(),
                vehicle.getDragCoefficient(),
                vehicle.getFrontalArea(),
                vehicle.getRcc(),
                vehicle.getWheelSize(),
                vehicle.getFinalDriveRatio(),
                vehicle.getMaxRPM(),
                vehicle.getMinRPM()
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

        HashMap<String, Double> limits = vehicle.getVelocityLimits();
        Iterator it = limits.keySet().iterator();
        while (it.hasNext()) {
            String typology = it.next().toString();
            if (m_dao.saveNewVehicleVelocityLimits(vehicle.getPK(), typology, limits.get(typology)) != 1) {
                return false;
            }
        }
        return true;
    }

    private boolean saveNewCombustionVehicle(Vehicle vehicle) {

        if (m_dao.saveNewCombustionVehicle(vehicle.getPK(), ((CombustionVehicle) vehicle).getFuel()) != 1) {
            return false;
        }
        return (saveNewVehicleGears(vehicle)
                && saveNewVehicleThrottle(vehicle));
    }

    private boolean saveNewHybridVehicle(Vehicle vehicle) {
        if (m_dao.saveNewHybridVehicle(vehicle.getPK()) != 1) {
            return false;
        }
        return true;
    }

    private boolean saveNewElectricVehicle(Vehicle vehicle) {
        if (m_dao.saveNewElectricVehicle(vehicle.getPK()) != 1) {
            return false;
        }
        return true;
    }

    private boolean saveNewVehicleGears(Vehicle vehicle) {
        ArrayList<Double> gears = ((CombustionVehicle) vehicle).getGearList();//TO DO alterar para a interface cmobustion, quando soublermos se o hybrid tambem vai ter gears
        for (Double ratio : gears) {
            if (m_dao.saveNewVehicleGear(vehicle.getPK(), gears.indexOf(ratio) + 1, ratio) != 1) {
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

}
