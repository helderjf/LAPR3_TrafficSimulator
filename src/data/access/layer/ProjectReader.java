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
import roadnetwork.domain.Simulation;
import roadnetwork.domain.Throttle;
import roadnetwork.domain.TrafficPattern;
import roadnetwork.domain.Vehicle;
import roadnetwork.domain.Wind;
import roadnetwork.factory.StateFactory;
import roadnetwork.state.SimulationState;

/**
 *
 * @author André Pedrosa, Hélder Faria, José Miranda, Rubén Rosário
 */
public class ProjectReader {

    private DataAccessObject m_dao;
    private ArrayList<Integer> m_projectIDList;
    private ArrayList<String> m_projectNameList;
    private Project m_project;
    private StateFactory m_stateFactory;

    public ProjectReader(DataAccessObject dao, StateFactory sf) {
        m_dao = dao;
        m_stateFactory = sf;
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
                        segments.getInt("MAX_VEHICLES")//max number of vehicles
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
                HashMap<Integer, Double> gearList = getGearList(combustionVehicles.getInt("ID_VEHICLE"));
                HashMap<SectionTypology, Double> velocityLimits = getVelocityLimits(combustionVehicles.getInt("ID_VEHICLE"));
                if (throttleList == null
                        || gearList == null
                        || velocityLimits == null) {
                    return false;
                }

                combustionVehiclesList.add(new CombustionVehicle(
                        combustionVehicles.getInt("ID_VEHICLE"),
                        combustionVehicles.getString("NAME"),
                        combustionVehicles.getString("DESCRIPTION"),
                        combustionVehicles.getString("TYPE"),
                        combustionVehicles.getString("FUEL"),
                        combustionVehicles.getDouble("MASS"),
                        combustionVehicles.getDouble("LOAD"),
                        combustionVehicles.getDouble("DRAG_COEF"),
                        combustionVehicles.getDouble("FRONTAL_AREA"),
                        combustionVehicles.getDouble("RRC"),
                        combustionVehicles.getDouble("WHEEL_SIZE"),
                        velocityLimits,
                        combustionVehicles.getDouble("RPM_MIN"),
                        combustionVehicles.getDouble("RPM_MAX"),
                        combustionVehicles.getDouble("FINAL_DRIVE_RATIO"),
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

                ArrayList<Throttle> throttleList = getThrottleList(hybridVehicles.getInt("ID_VEHICLE"));
                HashMap<Integer, Double> gearList = getGearList(hybridVehicles.getInt("ID_VEHICLE"));
                HashMap<SectionTypology, Double> velocityLimits = getVelocityLimits(hybridVehicles.getInt("ID_VEHICLE"));

                hybridVehiclesList.add(new HybridVehicle(
                        hybridVehicles.getInt("ID_VEHICLE"),
                        hybridVehicles.getString("NAME"),
                        hybridVehicles.getString("DESCRIPTION"),
                        hybridVehicles.getString("TYPE"),
                        hybridVehicles.getString("FUEL"),
                        hybridVehicles.getDouble("MASS"),
                        hybridVehicles.getDouble("LOAD"),
                        hybridVehicles.getDouble("DRAG_COEF"),
                        hybridVehicles.getDouble("FRONTAL_AREA"),
                        hybridVehicles.getDouble("RRC"),
                        hybridVehicles.getDouble("WHEEL_SIZE"),
                        velocityLimits,
                        hybridVehicles.getDouble("RPM_MIN"),
                        hybridVehicles.getDouble("RPM_MAX"),
                        hybridVehicles.getDouble("FINAL_DRIVE_RATIO"),
                        gearList,
                        throttleList,
                        hybridVehicles.getDouble("ENERGY_REGENERATION_RATIO")
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

                ArrayList<Throttle> throttleList = getThrottleList(electricVehicles.getInt("ID_VEHICLE"));
                HashMap<Integer, Double> gearList = getGearList(electricVehicles.getInt("ID_VEHICLE"));
                HashMap<SectionTypology, Double> velocityLimits = getVelocityLimits(electricVehicles.getInt("ID_VEHICLE"));

                electricVehiclesList.add(new ElectricVehicle(
                        electricVehicles.getInt("ID_VEHICLE"),
                        electricVehicles.getString("NAME"),
                        electricVehicles.getString("DESCRIPTION"),
                        electricVehicles.getString("TYPE"),
                        electricVehicles.getString("FUEL"),
                        electricVehicles.getDouble("MASS"),
                        electricVehicles.getDouble("LOAD"),
                        electricVehicles.getDouble("DRAG_COEF"),
                        electricVehicles.getDouble("FRONTAL_AREA"),
                        electricVehicles.getDouble("RRC"),
                        electricVehicles.getDouble("WHEEL_SIZE"),
                        velocityLimits,
                        electricVehicles.getDouble("RPM_MIN"),
                        electricVehicles.getDouble("RPM_MAX"),
                        electricVehicles.getDouble("FINAL_DRIVE_RATIO"),
                        gearList,
                        throttleList,
                        electricVehicles.getDouble("ENERGY_REGENERATION_RATIO")
                ));
            }

            m_project.addVehicleList(electricVehiclesList);

            return true;

        } catch (SQLException ex) {
            System.out.println("Electric vehicles not retrieved");
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

    private HashMap<Integer, Double> getGearList(int vehiclePK) {
        try {
            ResultSet gears = m_dao.getVehicleGears(vehiclePK);
            if (gears == null) {
                return null;
            }

            HashMap<Integer, Double> gearList = new HashMap();
            while (gears.next()) {
                gearList.put(gears.getInt("ID_GEAR"), gears.getDouble("RATIO"));
            }
            return gearList;

        } catch (SQLException ex) {
            System.out.println("Gear list not retrieved");
            System.out.println(ex);
            return null;
        }
    }

    private HashMap<SectionTypology, Double> getVelocityLimits(int vehiclePK) {
        try {
            ResultSet limits = m_dao.getVehicleVelocityLimits(vehiclePK);
            if (limits == null) {
                return null;
            }

            HashMap<SectionTypology, Double> velocityLimits = new HashMap();
            while (limits.next()) {
                velocityLimits.put(SectionTypology.valueOf(limits.getString(1)), limits.getDouble(2));
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

    public boolean simulationExists(int projectPK, String simulationName) {
        if (m_dao.simulationExists(projectPK, simulationName) == 0) {
            return false;
        }
        return true;
    }

    public boolean projectHasSimulations(int projectPK) {
        if (m_dao.projectHasSimulations(projectPK) == 0) {
            return false;
        }
        return true;
    }

    public HashMap<String, Integer> getOrderedSimulationList(int projpk) {
        return m_dao.getOrderedSimulationList(projpk);
    }

    public Simulation getSimulation(Project project, int simPK) {
        try {
            ResultSet simResult = m_dao.getSimulation(simPK);
            ResultSet trafPatResult = m_dao.getTrafficPattern(simPK);

            simResult.next();
            String simName = simResult.getString("NAME");
            String simDesc = simResult.getString("DESCRIPTION");
            String state = simResult.getString("STATE");

            ArrayList<TrafficPattern> trafficPaternList = new ArrayList();
            while (trafPatResult.next()) {
                int tpPK = trafPatResult.getInt("ID_TRAFFIC_PATTERN");
                int bnodePK = trafPatResult.getInt("BEGIN_NODE_ID");
                int enodePK = trafPatResult.getInt("END_NODE_ID");
                int vehiclePK = trafPatResult.getInt("ID_VEHICLE");
                double arrivalRate = trafPatResult.getDouble("ARRIVAL_RATE");

                Junction beginNode = project.getRoadNetwork().getNodeByPK(bnodePK);
                Junction endNode = project.getRoadNetwork().getNodeByPK(enodePK);
                Vehicle vehicle = project.getVehicleByPK(vehiclePK);
                trafficPaternList.add(new TrafficPattern(tpPK, beginNode, endNode, vehicle, arrivalRate));

            }

            Simulation sim = new Simulation();
            sim.setPK(simPK);
            sim.setName(simName);
            sim.setDescription(simDesc);
            sim.setTrafficPatternList(trafficPaternList);
            SimulationState simstate = m_stateFactory.getSimulationState(state, sim);
            sim.setState(simstate);

            return sim;

        } catch (SQLException ex) {
            System.out.println("Simulation not retrieved");
            System.out.println(ex);

            return null;
        }

    }

    public boolean simulationHasRuns(int simpk) {
        if (m_dao.simulationHasruns(simpk) == 0) {
            return false;
        }
        return true;
    }

    public HashMap<String, Integer> getSimulationRunsOrderedList(int simpk) {
        return m_dao.getOrderedRunsList(simpk);
    }



}
