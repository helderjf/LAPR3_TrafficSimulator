/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.access.layer;

import java.sql.SQLException;
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
import roadnetwork.domain.SectionTypology;
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
    private int m_projectPK;
    private String m_projectState;
    private String m_projectName;
    private String m_projectDescription;

    private RoadNetwork m_roadNetwork;
    private String m_roadNetworkName;
    private String m_roadNetworkDescription;
    private int m_roadNetworkPK;

    private ArrayList<Junction> m_nodeList;
    private ArrayList<Section> m_sectionList;
    private ArrayList<Vehicle> m_vehicleList;

    public ProjectWriter(DataAccessObject dao) {
        m_dao = dao;
    }

    public boolean saveNewProject(Project project) {

        m_project = project;

        if (!saveNewProjectProperties()) {
            return false;
        }

        if (m_project.hasRoadNetwork()) {
            if (!saveNewProjectRoadNetwork()) {
                return false;
            }
        }

        if (m_project.hasVehicles()) {
            if (!saveNewProjectVehicles()) {
                return false;
            }

        }

        return true;//TO DO fazer verificação
    }

    private boolean saveNewProjectProperties() {
        m_projectName = m_project.getName();
        m_projectDescription = m_project.getDescription();
        m_projectState = m_project.getState().getClass().getSimpleName();

        m_projectPK = m_dao.saveNewProject(m_projectName, m_projectDescription, m_projectState);
        if (m_projectPK != 0) {
            m_project.setPK(m_projectPK);
            return true;
        } else {
            return false;
        }

    }

    private boolean saveNewProjectRoadNetwork() {
        m_roadNetwork = m_project.getRoadNetwork();

        m_roadNetworkName = m_roadNetwork.getName();
        m_roadNetworkDescription = m_roadNetwork.getDescription();

        m_roadNetworkPK = m_dao.saveNewRoadNetwork(m_projectPK, m_roadNetworkName, m_roadNetworkDescription);

        if (m_roadNetworkPK == 0) {
            return false;
        } else {
            m_roadNetwork.setPK(m_roadNetworkPK);
            return (saveNewProjectNodeList()
                    && saveNewProjectRoadNetworkSections());
        }
    }

    private boolean saveNewProjectNodeList() {
        m_nodeList = m_roadNetwork.getNodeList();
        for (Junction it : m_nodeList) {
            it.setPK(m_dao.saveNewNode(m_roadNetworkPK, it.getJunctionId()));
            if (it.getPK() == 0) {
                return false;
            }
        }
        return true;
    }

    private boolean saveNewProjectRoadNetworkSections() {
        m_sectionList = m_roadNetwork.getSectionList();
        for (Section section : m_sectionList) {
            section.setPK(m_dao.saveNewSection(
                    m_roadNetworkPK,
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
        for (Segment segment : segmentList) {//TO DO verificação de que não index de segmentos repetidos para a mesma section
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
        m_vehicleList = m_project.getVehicleList();
        for (Vehicle vehicle : m_vehicleList) {
            int vehiclePK = m_dao.saveNewVehicle(
                    m_projectPK,
                    vehicle.getName(),
                    vehicle.getDescription(),
                    vehicle.getType(),
                    vehicle.getMass(),
                    vehicle.getLoad(),
                    vehicle.getDragCoefficient(),
                    vehicle.getRcc(),
                    vehicle.getWheelSize(),
                    vehicle.getFinalDriveRatio(),
                    vehicle.getMaxRPM(),
                    vehicle.getMinRPM()
            );

            vehicle.setPK(vehiclePK);

            saveNewVehicleVelocityLimits(vehicle);

            if (vehicle instanceof CombustionVehicle) {
                saveNewCombustionVehicle(vehicle);
            }else if(vehicle instanceof HybridVehicle){
                saveNewHybridVehicle(vehicle);
            }else if (vehicle instanceof ElectricVehicle){
                saveNewElectricVehicle(vehicle);
            }



        }
    }

    private boolean saveNewVehicleVelocityLimits(Vehicle vehicle) {

        HashMap<String, Double> limits = vehicle.getVelocityLimit();
        Iterator it = limits.keySet().iterator();
        while (it.hasNext()) {
            String typology = it.next().toString();
            if (m_dao.saveNewVehicleVelocityLimits(vehicle.getPK(), typology, limits.get(typology)) == -1) {
                return false;
            }
        }
        return true;
    }

    private boolean saveNewCombustionVehicle(Vehicle vehicle) {
        
        
            m_dao.saveNewCombustionVehicle(vehicle.getPK(),((CombustionVehicle)vehicle).getFuel());
            saveNewVehicleGears(vehicle);
            saveNewVehicleThrottle(vehicle);
    }

    private boolean saveNewHybridVehicle(Vehicle vehicle) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private boolean saveNewElectricVehicle(Vehicle vehicle) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void saveNewVehicleGears(Vehicle vehicle) {
        ArrayList<Double> gears = ((CombustionVehicle)vehicle).getGearList();//TO DO alterar para a interface cmobustion, quando soublermos se o hybrid tambem vai ter gears
        for(Double ratio : gears){
            m_dao.saveNewVehicleGear(vehicle.getPK(),gears.indexOf(ratio)+1,ratio);
        }
    }

    private void saveNewVehicleThrottle(Vehicle vehicle) {
        ArrayList<Throttle> throttleList = ((CombustionVehicle)vehicle).getThrottleList();//TO DO alterar para a interface cmobustion, quando soublermos se o hybrid tambem vai ter gears
        for(Throttle throttle : throttleList){
            ArrayList<Regime> regimeList = throttle.getRegimeList();
            for(Regime regime : regimeList){
                m_dao.saveNewVehicleRegime(
                        vehicle.getPK(),
                        throttleList.indexOf(throttle)+1,
                        regimeList.indexOf(regime)+1,
                        regime.getTorque(),
                        regime.getRPMLow(),
                        regime.getRPMHigh(),
                        regime.getSfc());
            }
        }
    }

}
