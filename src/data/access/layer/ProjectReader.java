/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.access.layer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import roadnetwork.domain.CombustionVehicle;
import roadnetwork.domain.ElectricVehicle;
import roadnetwork.domain.HybridVehicle;
import roadnetwork.domain.SectionDirection;
import roadnetwork.domain.Junction;
import roadnetwork.domain.Project;
import roadnetwork.domain.Regime;
import roadnetwork.domain.RoadNetwork;
import roadnetwork.domain.Section;
import roadnetwork.domain.SectionTypology;
import roadnetwork.domain.Segment;
import roadnetwork.domain.Throttle;
import roadnetwork.domain.Vehicle;
import roadnetwork.domain.Wind;
import roadnetwork.factory.ProjectStateFactory;

/**
 *
 * @author André Pedrosa, Hélder Faria, José Miranda, Rubén Rosário
 */
public class ProjectReader {

    private DataAccessObject m_dao;
    private ArrayList<Integer> m_projectIDList;
    private ArrayList<String> m_projectNameList;
    private Project m_project;
    private ProjectStateFactory m_stateFactory;

    public ProjectReader(DataAccessObject dao, ProjectStateFactory psf) {
        m_dao = dao;
        m_stateFactory = psf;
    }

    public Project newProject() {
        return new Project();
    }

    public ArrayList<String> getOrderedProjectList() {
        m_projectNameList = m_dao.getOrderedProjectList();
        return m_projectNameList;
    }

    public Project getProjectByName(String projectName) {
        m_project = new Project();
        if (!getProjectProperties(projectName)) {
            return null;
        }

        if (m_project.hasRoadNetwork()) {
            if (!setProjectRoadNetwork()) {
                return null;
            }
        }

        if (m_project.hasVehicles()) {
            if (!setProjectVehicles()) {
                return null;
            }
        }

        return m_project;
    }

    private boolean getProjectProperties(String projectName) {
        try {
            ResultSet properties = m_dao.getProjectProperties(projectName);

            if (properties == null) {
                return false;
            }

            properties.next();
            m_project.setPK(properties.getInt(1));
            m_project.setName(properties.getString(2));
            m_project.setDescription(properties.getString(3));
            String s = properties.getString(4);
            m_project.setState(m_stateFactory.getProjectState(s, m_project));
            return true;
        } catch (SQLException ex) {
            System.out.println("Project properties not retrieved");
            System.out.println(ex);
            return false;
        }
    }

    private boolean setProjectRoadNetwork() {
        try {
            RoadNetwork roadNetwork = new RoadNetwork();

            ResultSet rnData = m_dao.getRoadNetwork(m_project.getPK());
            if (rnData == null) {
                return false;
            }

            rnData.next();
            roadNetwork.setPK(rnData.getInt(1));
            roadNetwork.setName(rnData.getString(2));
            roadNetwork.setDescription(rnData.getString(3));

            m_project.setRoadNetwork(roadNetwork);

            return setNodes(roadNetwork) && setSections(roadNetwork);

        } catch (SQLException ex) {
            System.out.println("Project Road Network not retrieved");
            System.out.println(ex);
            return false;
        }
    }

    private boolean setNodes(RoadNetwork roadNetwork) {

        try {
            ResultSet nodes = m_dao.getRoadNetworkNodes(roadNetwork.getPK());
            if (nodes == null) {
                return false;
            }

            ArrayList<Junction> nodeList = new ArrayList();
            while (nodes.next()) {
                nodeList.add(new Junction(nodes.getInt(1), nodes.getString(2)));
            }

            roadNetwork.setNodeList(nodeList);
            return true;

        } catch (SQLException ex) {
            System.out.println("Road Network nodes not retrieved");
            System.out.println(ex);
            return false;
        }

    }

    private boolean setSections(RoadNetwork roadNetwork) {
        try {

            ResultSet sections = m_dao.getRoadNetworkSections(roadNetwork.getPK());
            if (sections == null) {
                return false;
            }

            ArrayList<Section> sectionList = new ArrayList();
            while (sections.next()) {

                ArrayList<Segment> segmentList = getSegments(sections.getInt("ID_SECTION"));
                if (segmentList == null) {
                    return false;
                }

                sectionList.add(new Section(
                        sections.getInt("ID_SECTION"),
                        sections.getString("ROAD_NAME"),
                        roadNetwork.getNodeByPK(sections.getInt("BEGINING_NODE_ID")),
                        roadNetwork.getNodeByPK(sections.getInt("ENDING_NODE_ID")),
                        SectionTypology.valueOf(sections.getString("TYPOLOGY")),
                        SectionDirection.valueOf(sections.getString("DIRECTION")),
                        sections.getDouble("TOLL"),
                        new Wind(sections.getDouble("WIND_DIRECTION"), sections.getDouble("WIND_SPEED")),
                        segmentList
                ));
            }
            roadNetwork.setSectionList(sectionList);
            return true;

        } catch (SQLException ex) {
            System.out.println("Road Network sections not retrieved");
            System.out.println(ex);
            return false;
        }
    }

    private ArrayList<Segment> getSegments(int sectionPK) {
        try {
            ResultSet segments = m_dao.getSectionSegments(sectionPK);
            if (segments == null) {
                return null;
            }

            ArrayList<Segment> segmentList = new ArrayList();
            while (segments.next()) {
                segmentList.add(new Segment(
                        segments.getInt("SEGMENT_INDEX"),//index
                        segments.getDouble("INITIAL_HEIGHT"),//initial height
                        segments.getDouble("SLOPE"),//slope
                        segments.getDouble("LENGHT"),//lenght
                        segments.getDouble("MAX_VELOCITY"),// max velocity
                        segments.getDouble("MIN_VELOCITY"),//min velocity
                        segments.getDouble("MAX_VEHICLES")//max number of vehicles
                ));
            }

            return segmentList;
        } catch (SQLException ex) {
            System.out.println("Section segments not retrieved");
            System.out.println(ex);
            return null;
        }
    }

    private boolean setProjectVehicles() {

        if (!setCombustionVehicles(m_project.getPK())) {
            return false;
        }

        if (!setHybridVehicles(m_project.getPK())) {
            return false;
        }

        if (!setElectricVehicles(m_project.getPK())) {
            return false;
        }

        return true;

    }

    private boolean setCombustionVehicles(int projectPK) {
        try {
            ResultSet combustionVehicles = m_dao.getCombustionVehicles(projectPK);
            if (combustionVehicles == null) {
                return false;
            }

            ArrayList<Vehicle> combustionVehiclesList = new ArrayList();
            while (combustionVehicles.next()) {

                ArrayList<Throttle> throttleList = getThrottleList(combustionVehicles.getInt("ID_VEHICLE"));
                ArrayList<Double> gearList = getGearList(combustionVehicles.getInt("ID_VEHICLE"));
                HashMap<String, Double> velocityLimits = getVelocityLimits(combustionVehicles.getInt("ID_VEHICLE"));
                if (throttleList == null
                        || gearList == null
                        || velocityLimits == null) {
                    return false;
                }

                combustionVehiclesList.add(new CombustionVehicle(
                        combustionVehicles.getInt("ID_VEHICLE"),
                        combustionVehicles.getString("NAME"),
                        combustionVehicles.getString("DESCRIPTION"),
                        combustionVehicles.getDouble("MASS"),
                        combustionVehicles.getString("TYPE"),
                        combustionVehicles.getDouble("LOAD"),
                        combustionVehicles.getDouble("DRAG_COEF"),
                        combustionVehicles.getDouble("FRONTAL_AREA"),
                        combustionVehicles.getDouble("RRC"),
                        combustionVehicles.getDouble("WHEEL_SIZE"),
                        velocityLimits,
                        combustionVehicles.getDouble("RPM_MIN"),
                        combustionVehicles.getDouble("RPM_MAX"),
                        combustionVehicles.getDouble("FINAL_DRIVE_RATIO"),
                        combustionVehicles.getString("FUEL"),
                        gearList,
                        throttleList
                ));
            }

            m_project.addVehicleList(combustionVehiclesList);

            return true;
        } catch (SQLException ex) {
            System.out.println("Combustion vehicles not retrieved");
            System.out.println(ex);
            return false;
        }
    }

    private boolean setHybridVehicles(int projectPK) {
        try {
            ResultSet hybridVehicles = m_dao.getHybridVehicles(projectPK);
            if (hybridVehicles == null) {
                return false;
            }

            ArrayList<Vehicle> hybridVehiclesList = new ArrayList();
            while (hybridVehicles.next()) {

                //  ArrayList<Throttle> throttleList = getThrotleList(combustionVehicles.getInt("ID_VEHICLE"));
                //  ArrayList<Double> gearList = getGearList(combustionVehicles.getInt("ID_VEHICLE"));
                HashMap<String, Double> velocityLimits = getVelocityLimits(hybridVehicles.getInt("ID_VEHICLE"));

                hybridVehiclesList.add(new HybridVehicle(
                        hybridVehicles.getInt("ID_VEHICLE"),
                        hybridVehicles.getString("NAME"),
                        hybridVehicles.getString("DESCRIPTION"),
                        hybridVehicles.getDouble("MASS"),
                        hybridVehicles.getString("TYPE"),
                        hybridVehicles.getDouble("LOAD"),
                        hybridVehicles.getDouble("DRAG_COEF"),
                        hybridVehicles.getDouble("FRONTAL_AREA"),
                        hybridVehicles.getDouble("RRC"),
                        hybridVehicles.getDouble("WHEEL_SIZE"),
                        velocityLimits,
                        hybridVehicles.getDouble("RPM_MIN"),
                        hybridVehicles.getDouble("RPM_MAX"),
                        hybridVehicles.getDouble("FINAL_DRIVE_RATIO")
                //combustionVehicles.getString("FUEL"),
                //gearList,
                //throttleList
                ));
            }

            m_project.addVehicleList(hybridVehiclesList);

            return true;

        } catch (SQLException ex) {
            System.out.println("Hybrid vehicles not retrieved");
            System.out.println(ex);
            return false;
        }
    }

    private boolean setElectricVehicles(int projectPK) {
        try {
            ResultSet electricVehicles = m_dao.getElectricVehicles(projectPK);
            if (electricVehicles == null) {
                return false;
            }

            ArrayList<Vehicle> electricVehiclesList = new ArrayList();
            while (electricVehicles.next()) {

                //  ArrayList<Throttle> throttleList = getThrotleList(combustionVehicles.getInt("ID_VEHICLE"));
                //  ArrayList<Double> gearList = getGearList(combustionVehicles.getInt("ID_VEHICLE"));
                HashMap<String, Double> velocityLimits = getVelocityLimits(electricVehicles.getInt("ID_VEHICLE"));

                electricVehiclesList.add(new ElectricVehicle(
                        electricVehicles.getInt("ID_VEHICLE"),
                        electricVehicles.getString("NAME"),
                        electricVehicles.getString("DESCRIPTION"),
                        electricVehicles.getDouble("MASS"),
                        electricVehicles.getString("TYPE"),
                        electricVehicles.getDouble("LOAD"),
                        electricVehicles.getDouble("DRAG_COEF"),
                        electricVehicles.getDouble("FRONTAL_AREA"),
                        electricVehicles.getDouble("RRC"),
                        electricVehicles.getDouble("WHEEL_SIZE"),
                        velocityLimits,
                        electricVehicles.getDouble("RPM_MIN"),
                        electricVehicles.getDouble("RPM_MAX"),
                        electricVehicles.getDouble("FINAL_DRIVE_RATIO")
                //combustionVehicles.getString("FUEL"),
                //gearList,
                //throttleList
                ));
            }

            m_project.addVehicleList(electricVehiclesList);

            return true;

        } catch (SQLException ex) {
            System.out.println("Hybrid vehicles not retrieved");
            System.out.println(ex);
            return false;
        }

    }

    private ArrayList<Throttle> getThrottleList(int vehiclePK) {
        try {
            ResultSet throttles = m_dao.getVehicleThrottles(vehiclePK);
            if (throttles == null) {
                return null;
            }

            ArrayList<Throttle> throttleList = new ArrayList();
            while (throttles.next()) {
                throttleList.add(new Throttle(
                        throttles.getString("ID_THROTTLE"),
                        getThrottleRegimes(vehiclePK, throttles.getString("ID_THROTTLE")))
                );
            }

            return throttleList;

        } catch (SQLException ex) {
            System.out.println("Throttle list not retrieved");
            System.out.println(ex);
            return null;
        }

    }

    private ArrayList<Regime> getThrottleRegimes(int vehiclePK, String throttleID) {
        try {
            ResultSet regimes = m_dao.getThrottleRegimes(vehiclePK, throttleID);
            if (regimes == null) {
                return null;
            }

            ArrayList<Regime> regimesList = new ArrayList();
            while (regimes.next()) {
                regimesList.add(
                        new Regime(
                                regimes.getInt("ID_REGIME"),
                                regimes.getDouble("TORQUE"),
                                regimes.getDouble("RPM_LOW"),
                                regimes.getDouble("RPM_HIGH"),
                                regimes.getDouble("SFC")
                        ));
            }
            return regimesList;
        } catch (SQLException ex) {
            System.out.println("Regimes not retrieved");
            System.out.println(ex);
            return null;
        }
    }

    private ArrayList<Double> getGearList(int vehiclePK) {
        try {
            ResultSet gears = m_dao.getVehicleGears(vehiclePK);
            if (gears == null) {
                return null;
            }

            ArrayList<Double> gearList = new ArrayList();
            while (gears.next()) {
                gearList.add(gears.getDouble("RATIO"));
            }
            return gearList;

        } catch (SQLException ex) {
            System.out.println("Gear list not retrieved");
            System.out.println(ex);
            return null;
        }
    }

    private HashMap<String, Double> getVelocityLimits(int vehiclePK) {
        try {
            ResultSet limits = m_dao.getVehicleVelocityLimits(vehiclePK);
            if (limits == null) {
                return null;
            }

            HashMap<String, Double> velocityLimits = new HashMap();
            while (limits.next()) {
                velocityLimits.put(limits.getString(1), limits.getDouble(2));
            }

            return velocityLimits;

        } catch (SQLException ex) {
            System.out.println("Velocity Limits not retrieved");
            System.out.println(ex);
            return null;
        }
    }

    public boolean projectNameExists(String name) {
        if (m_dao.projectNameExists(name) == 0) {
            return false;
        }
        return true;
    }

    public boolean simulationExists(int pk, String simulationName) {
        
    }

}
