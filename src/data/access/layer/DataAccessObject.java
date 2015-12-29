/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.access.layer;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.jdbc.OracleTypes;
import roadnetwork.domain.SectionDirection;
import roadnetwork.domain.SectionTypology;

/**
 *
 * @author André Pedrosa, Hélder Faria, José Miranda, Rubén Rosário
 */
public class DataAccessObject {

    private final String m_dbUrl;
    private final String m_user;
    private final String m_pass;
    private Connection m_connection;
    private Statement m_statement;
    private ResultSet m_output;

    public DataAccessObject(String dbUrl, String user, String pass) {
        m_dbUrl = dbUrl;
        m_user = user;
        m_pass = pass;
    }

    public boolean connect() {

        try {
            m_connection = DriverManager.getConnection(m_dbUrl, m_user, m_pass);
            m_connection.setAutoCommit(false);
            System.out.println("Connection to database successfull");
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DataAccessObject.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    public boolean commit() {
        try {
            m_connection.commit();
            System.out.println("commit :-)");
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DataAccessObject.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean rollback() {
        try {
            m_connection.rollback();
            System.out.println("rollback :-(");
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DataAccessObject.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public ArrayList<String> getProjectsIDList() {
        ArrayList<String> pidl = new ArrayList<>();
        //TODO call procedure

        //dummy
        String p1 = "project 1 - Porto Centro";
        String p2 = "project 2 - Porto Norte";
        String p3 = "project 2 - Porto Este";
        pidl.add(p1);
        pidl.add(p2);
        pidl.add(p3);
        return pidl;
    }

    public int saveNewProject(String projectName, String projectDescription, String projectState) {
        try {
            if (m_connection == null) {
                if (!connect()) {
                    System.out.println("conexao saveproject not ok");
                    return -1;//returns -1 so the caller knows the connection failed
                }
            }

            //create statement
            System.out.println("prepara proc savenewproject");
            CallableStatement statement = m_connection.prepareCall("{call SAVE_NEW_PROJECT(?,?,?,?)}");
            statement.setString(1, projectName);
            statement.setString(2, projectDescription);
            statement.setString(3, projectState);
            statement.registerOutParameter(4, Types.INTEGER);

            //execute statement
            System.out.println("executa proc savenewproject");
            statement.execute();
            System.out.println("acabou execuçao");

            return statement.getInt(4);

        } catch (SQLException ex) {
            System.out.println(ex);
            //Logger.getLogger(ProjectWriter.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }

    public int saveNewRoadNetwork(int projectPK, String roadNetworkName, String roadNetworkDescription) {

        try {
            if (m_connection == null) {
                if (!connect()) {
                    return -1;//returns -1 so the caller knows the connection failed
                }
            }

            //create statement
            CallableStatement statement = m_connection.prepareCall("{call SAVE_NEW_ROAD_NETWORK(?,?,?,?)}");
            statement.setInt(1, projectPK);
            statement.setString(2, roadNetworkName);
            statement.setString(3, roadNetworkDescription);
            statement.registerOutParameter(4, Types.INTEGER);

            //execute statement
            statement.execute();

            return statement.getInt(4);

        } catch (SQLException ex) {
            Logger.getLogger(ProjectWriter.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }

    }

    public int saveNewNode(int roadNetworkPK, String nodeName) {
        try {
            if (m_connection == null) {
                if (!connect()) {
                    return -1;//returns -1 so the caller knows the connection failed
                }
            }

            //create statement
            CallableStatement statement = m_connection.prepareCall("{call SAVE_NEW_NODE(?,?,?)}");
            statement.setInt(1, roadNetworkPK);
            statement.setString(2, nodeName);
            statement.registerOutParameter(3, Types.INTEGER);

            //execute statement
            statement.execute();

            return statement.getInt(3);
        } catch (SQLException ex) {
            Logger.getLogger(ProjectWriter.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }

    }

    public int saveNewSection(
            int roadNetworkPK,
            String roadName,
            int beginningNode,
            int endingNode,
            SectionTypology typology,
            SectionDirection direction,
            double toll,
            double windDirection,
            double windVelocity) {

        try {
            if (m_connection == null) {
                if (!connect()) {
                    return -1;//returns -1 so the caller knows the connection failed
                }
            }

            //create statement
            CallableStatement statement = m_connection.prepareCall("{call SAVE_NEW_SECTION(?,?,?,?,?,?,?,?,?,?)}");
            statement.setInt(1, roadNetworkPK);
            statement.setString(2, roadName);
            statement.setInt(3, beginningNode);
            statement.setInt(4, endingNode);
            statement.setString(5, typology.toString());
            statement.setString(6, direction.toString());
            statement.setDouble(7, toll);
            statement.setDouble(8, windDirection);
            statement.setDouble(9, windVelocity);
            statement.registerOutParameter(10, Types.INTEGER);

            //execute statement
            statement.execute();

            return statement.getInt(10);

        } catch (SQLException ex) {
            Logger.getLogger(ProjectWriter.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }

    public int saveNewSegment(
            int sectionPK,
            int segmentIndex,
            double initialHeight,
            double slope,
            double lenght,
            double maxVelocity,
            double minVelocity,
            double maxVehicles) {

        try {
            if (m_connection == null) {
                if (!connect()) {
                    return -1;//returns -1 so the caller knows the connection failed
                }
            }

            CallableStatement statement = m_connection.prepareCall("{call SAVE_NEW_SEGMENT(?,?,?,?,?,?,?,?)}");
            statement.setInt(1, segmentIndex);
            statement.setInt(2, sectionPK);
            statement.setDouble(3, initialHeight);
            statement.setDouble(4, slope);
            statement.setDouble(5, lenght);
            statement.setDouble(6, maxVelocity);
            statement.setDouble(7, minVelocity);
            statement.setDouble(8, maxVehicles);

            statement.execute();

            return 1;

        } catch (SQLException ex) {
            Logger.getLogger(ProjectWriter.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }

    }

    int saveNewVehicle(
            int projectPK,
            String name,
            String description,
            String type,
            double mass,
            double load,
            double dragCoefficient,
            double frontalArea,
            double rcc,
            double wheelSize,
            double finalDriveRatio,
            double maxRPM,
            double minRPM) {
        try {
            if (m_connection == null) {
                if (!connect()) {
                    return -1;//returns -1 so the caller knows the connection failed
                }
            }

            //create statement
            CallableStatement statement = m_connection.prepareCall("{call SAVE_NEW_VEHICLE(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            statement.setInt(1, projectPK);
            statement.setString(2, name);
            statement.setString(3, description);
            statement.setString(4, type);
            statement.setDouble(5, mass);
            statement.setDouble(6, load);
            statement.setDouble(7, dragCoefficient);
            statement.setDouble(8, frontalArea);
            statement.setDouble(9, rcc);
            statement.setDouble(10, wheelSize);
            statement.setDouble(11, finalDriveRatio);
            statement.setDouble(12, maxRPM);
            statement.setDouble(13, minRPM);
            statement.registerOutParameter(14, Types.INTEGER);

            //execute statement
            statement.execute();

            return statement.getInt(14);

        } catch (SQLException ex) {
            Logger.getLogger(ProjectWriter.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }

    }

    int saveNewVehicleVelocityLimits(int vehiclePK, String typology, Double speed) {

        try {
            if (m_connection == null) {
                if (!connect()) {
                    return -1;//returns -1 so the caller knows the connection failed
                }
            }

            //create statement
            CallableStatement statement = m_connection.prepareCall("{call SAVE_NEW_VEHICLE_VEL_LIMIT(?,?,?,?)}");
            statement.setInt(1, vehiclePK);
            statement.setString(2, typology);
            statement.setDouble(3, speed);
            statement.registerOutParameter(4, Types.INTEGER);
            //execute statement
            statement.execute();

            if (statement.getInt(4) != 1) {
                return -1;
            }

            return 1;

        } catch (SQLException ex) {
            Logger.getLogger(ProjectWriter.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }

    }

    public int saveNewCombustionVehicle(int vehiclePK, String fuel) {
        try {
            if (m_connection == null) {
                if (!connect()) {
                    return -1;//returns -1 so the caller knows the connection failed
                }
            }

            CallableStatement statement = m_connection.prepareCall("{call SAVE_NEW_COMBUSTION_V(?,?)}");
            statement.setInt(1, vehiclePK);
            statement.setString(2, fuel);

            //execute statement
            statement.execute();

            return 1;

        } catch (SQLException ex) {
            Logger.getLogger(ProjectWriter.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }

    }

    public int saveNewHybridVehicle(int vehiclePK) {
        try {
            if (m_connection == null) {
                if (!connect()) {
                    return -1;//returns -1 so the caller knows the connection failed
                }
            }

            CallableStatement statement = m_connection.prepareCall("{call SAVE_NEW_HYBRID_V(?)}");
            statement.setInt(1, vehiclePK);

            //execute statement
            statement.execute();

            return 1;

        } catch (SQLException ex) {
            Logger.getLogger(ProjectWriter.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }

    }

    public int saveNewElectricVehicle(int vehiclePK) {
        try {
            if (m_connection == null) {
                if (!connect()) {
                    return -1;//returns -1 so the caller knows the connection failed
                }
            }

            CallableStatement statement = m_connection.prepareCall("{call SAVE_NEW_ELECTRIC_V(?)}");
            statement.setInt(1, vehiclePK);

            //execute statement
            statement.execute();

            return 1;

        } catch (SQLException ex) {
            Logger.getLogger(ProjectWriter.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }

    }

    public int saveNewVehicleGear(int vehiclePK, int gear, Double ratio) {
        try {
            if (m_connection == null) {
                if (!connect()) {
                    return -1;//returns -1 so the caller knows the connection failed
                }
            }

            CallableStatement statement = m_connection.prepareCall("{call SAVE_NEW_VEHICLE_GEAR(?,?,?)}");
            statement.setInt(1, vehiclePK);
            statement.setInt(2, gear);
            statement.setDouble(3, ratio);

            //execute statement
            statement.execute();

            return 1;

        } catch (SQLException ex) {
            Logger.getLogger(ProjectWriter.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }

    public int saveNewVehicleRegime(int vehiclePK,
            String throttleId,
            int regimeId,
            double torque,
            double rpmLow,
            double rpmHigh,
            double sfc) {
        try {
            if (m_connection == null) {
                if (!connect()) {
                    return -1;//returns -1 so the caller knows the connection failed
                }
            }

            CallableStatement statement = m_connection.prepareCall("{call SAVE_NEW_VEHICLE_THROTTLE(?,?,?,?,?,?,?)}");
            statement.setInt(1, vehiclePK);
            statement.setString(2, throttleId);
            statement.setInt(3, regimeId);
            statement.setDouble(4, torque);
            statement.setDouble(5, rpmLow);
            statement.setDouble(6, rpmHigh);
            statement.setDouble(7, sfc);

            //execute statement
            statement.execute();

            return 1;

        } catch (SQLException ex) {
            Logger.getLogger(ProjectWriter.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }

    int updateProject(int projectPK, String projectName, String projectDescription, String projectState) {
        try {
            if (m_connection == null) {
                if (!connect()) {
                    System.out.println("conexao not ok");
                    return -1;//returns -1 so the caller knows the connection failed
                }
            }

            //create statement
            CallableStatement statement = m_connection.prepareCall("{call UPDATE_PROJECT(?,?,?,?)}");
            statement.setInt(1, projectPK);
            statement.setString(2, projectName);
            statement.setString(3, projectDescription);
            statement.setString(4, projectState);

            //execute statement
            statement.execute();

            return 1;

        } catch (SQLException ex) {
            Logger.getLogger(ProjectWriter.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }

    int updateRoadNetwork(int roadNetworkPK, String roadNetworkName, String roadNetworkDescription) {

        try {
            if (m_connection == null) {
                if (!connect()) {
                    return -1;//returns -1 so the caller knows the connection failed
                }
            }

            //create statement
            CallableStatement statement = m_connection.prepareCall("{call UPDATE_ROAD_NETWORK(?,?,?)}");
            statement.setInt(1, roadNetworkPK);
            statement.setString(2, roadNetworkName);
            statement.setString(3, roadNetworkDescription);

            //execute statement
            statement.execute();

            return 1;

        } catch (SQLException ex) {
            Logger.getLogger(ProjectWriter.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }

    int updateNode(int nodePK, String nodeName) {
        try {
            if (m_connection == null) {
                if (!connect()) {
                    return -1;//returns -1 so the caller knows the connection failed
                }
            }

            //create statement
            CallableStatement statement = m_connection.prepareCall("{call UPDATE_NODE(?,?)}");
            statement.setInt(1, nodePK);
            statement.setString(2, nodeName);

            //execute statement
            statement.execute();

            return 1;
        } catch (SQLException ex) {
            Logger.getLogger(ProjectWriter.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }

    int updateSection(
            int sectionPK,
            String roadName,
            int beginningNode,
            int endingNode,
            SectionTypology typology,
            SectionDirection direction,
            double toll,
            double windDirection,
            double windVelocity) {
        try {
            if (m_connection == null) {
                if (!connect()) {
                    return -1;//returns -1 so the caller knows the connection failed
                }
            }

            //create statement
            CallableStatement statement = m_connection.prepareCall("{call UPDATE_SECTION(?,?,?,?,?,?,?,?,?)}");
            statement.setInt(1, sectionPK);
            statement.setString(2, roadName);
            statement.setInt(3, beginningNode);
            statement.setInt(4, endingNode);
            statement.setString(5, typology.toString());
            statement.setString(6, direction.toString());
            statement.setDouble(7, toll);
            statement.setDouble(8, windDirection);
            statement.setDouble(9, windVelocity);

            //execute statement
            statement.execute();

            return 1;

        } catch (SQLException ex) {
            Logger.getLogger(ProjectWriter.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }

    int updateSegment(int sectionPK,
            int segmentIndex,
            double initialHeight,
            double slope,
            double lenght,
            double maxVelocity,
            double minVelocity,
            double maxVehicles) {
        try {
            if (m_connection == null) {
                if (!connect()) {
                    return -1;//returns -1 so the caller knows the connection failed
                }
            }

            CallableStatement statement = m_connection.prepareCall("{call UPDATE_SEGMENT(?,?,?,?,?,?,?,?)}");
            statement.setInt(1, segmentIndex);
            statement.setInt(2, sectionPK);
            statement.setDouble(3, initialHeight);
            statement.setDouble(4, slope);
            statement.setDouble(5, lenght);
            statement.setDouble(6, maxVelocity);
            statement.setDouble(7, minVelocity);
            statement.setDouble(8, maxVehicles);

            statement.execute();

            return 1;

        } catch (SQLException ex) {
            Logger.getLogger(ProjectWriter.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }

    }

    ArrayList<String> getOrderedProjectList() {
        try {
            if (m_connection == null) {
                if (!connect()) {
                    return null;//returns -1 so the caller knows the connection failed
                }
            }

            //criar statement
            CallableStatement statement = m_connection.prepareCall("{call GET_ORDERED_PROJECT_LIST(?)}");
            statement.registerOutParameter(1, OracleTypes.CURSOR);

            //executar query
            statement.execute();

            m_output = (ResultSet) statement.getObject(1);

            ArrayList<String> projectList = new ArrayList();
            while (m_output.next()) {
                projectList.add(m_output.getString(2));
            }

            return projectList;

        } catch (SQLException ex) {
            Logger.getLogger(ProjectWriter.class.getName()).log(Level.SEVERE, null, ex);
            return null;//returns -1 so the caller knows the connection failed
        }

    }

    ResultSet getProjectProperties(String projectName) {
        try {
            if (m_connection == null) {
                if (!connect()) {
                    return null;
                }
            }

            //criar statement
            CallableStatement statement = m_connection.prepareCall("{call GET_PROJECT_PROPERTIES(?,?)}");
            statement.setString(1, projectName);
            statement.registerOutParameter(2, OracleTypes.CURSOR);

            //executar query
            statement.execute();

            ResultSet projectProperties = (ResultSet) statement.getObject(2);

            return projectProperties;
        } catch (SQLException ex) {
            Logger.getLogger(ProjectWriter.class.getName()).log(Level.SEVERE, null, ex);
            return null;//returns null so the caller knows the connection failed
        }

    }

    ResultSet getRoadNetwork(int projectPK) {
        try {
            if (m_connection == null) {
                if (!connect()) {
                    return null;
                }
            }

            //criar statement
            CallableStatement statement = m_connection.prepareCall("{call GET_PROJECT_ROAD_NETWORK(?,?)}");
            statement.setInt(1, projectPK);
            statement.registerOutParameter(2, OracleTypes.CURSOR);

            //executar query
            statement.execute();

            ResultSet roadNetwork = (ResultSet) statement.getObject(2);

            return roadNetwork;
        } catch (SQLException ex) {
            Logger.getLogger(ProjectWriter.class.getName()).log(Level.SEVERE, null, ex);
            return null;//returns null so the caller knows the connection failed
        }
    }

    ResultSet getRoadNetworkNodes(int roadNetworkPK) {
        try {
            if (m_connection == null) {
                if (!connect()) {
                    return null;
                }
            }

            //criar statement
            CallableStatement statement = m_connection.prepareCall("{call GET_ROAD_NETWORK_NODES(?,?)}");
            statement.setInt(1, roadNetworkPK);
            statement.registerOutParameter(2, OracleTypes.CURSOR);

            //executar query
            statement.execute();

            ResultSet nodes = (ResultSet) statement.getObject(2);

            return nodes;
        } catch (SQLException ex) {
            Logger.getLogger(ProjectWriter.class.getName()).log(Level.SEVERE, null, ex);
            return null;//returns null so the caller knows the connection failed
        }
    }

    ResultSet getRoadNetworkSections(int roadNetworkPK) {
        try {
            if (m_connection == null) {
                if (!connect()) {
                    return null;
                }
            }

            //criar statement
            CallableStatement statement = m_connection.prepareCall("{call GET_ROAD_NETWORK_SECTIONS(?,?)}");
            statement.setInt(1, roadNetworkPK);
            statement.registerOutParameter(2, OracleTypes.CURSOR);

            //executar query
            statement.execute();

            ResultSet sections = (ResultSet) statement.getObject(2);

            return sections;
        } catch (SQLException ex) {
            Logger.getLogger(ProjectWriter.class.getName()).log(Level.SEVERE, null, ex);
            return null;//returns null so the caller knows the connection failed
        }
    }

    ResultSet getSectionSegments(int sectionPK) {
        try {
            if (m_connection == null) {
                if (!connect()) {
                    return null;
                }
            }

            //criar statement
            CallableStatement statement = m_connection.prepareCall("{call GET_SECTION_SEGMENTS(?,?)}");
            statement.setInt(1, sectionPK);
            statement.registerOutParameter(2, OracleTypes.CURSOR);

            //executar query
            statement.execute();

            ResultSet segments = (ResultSet) statement.getObject(2);

            return segments;
        } catch (SQLException ex) {
            Logger.getLogger(ProjectWriter.class.getName()).log(Level.SEVERE, null, ex);
            return null;//returns null so the caller knows the connection failed
        }
    }

    ResultSet getCombustionVehicles(int projectPK) {
        try {
            if (m_connection == null) {
                if (!connect()) {
                    return null;
                }
            }

            //criar statement
            CallableStatement statement = m_connection.prepareCall("{call GET_PROJECT_COMB_VEHICLES(?,?)}");
            statement.setInt(1, projectPK);
            statement.registerOutParameter(2, OracleTypes.CURSOR);

            //executar query
            statement.execute();

            ResultSet combVehicles = (ResultSet) statement.getObject(2);

            return combVehicles;
        } catch (SQLException ex) {
            Logger.getLogger(ProjectWriter.class.getName()).log(Level.SEVERE, null, ex);
            return null;//returns null so the caller knows the connection failed
        }
    }

    ResultSet getHybridVehicles(int projectPK) {
        try {
            if (m_connection == null) {
                if (!connect()) {
                    return null;
                }
            }

            //criar statement
            CallableStatement statement = m_connection.prepareCall("{call GET_PROJECT_HYBR_VEHICLES(?,?)}");
            statement.setInt(1, projectPK);
            statement.registerOutParameter(2, OracleTypes.CURSOR);

            //executar query
            statement.execute();

            ResultSet hybrVehicles = (ResultSet) statement.getObject(2);

            return hybrVehicles;
        } catch (SQLException ex) {
            Logger.getLogger(ProjectWriter.class.getName()).log(Level.SEVERE, null, ex);
            return null;//returns null so the caller knows the connection failed
        }
    }

    ResultSet getElectricVehicles(int projectPK) {
        try {
            if (m_connection == null) {
                if (!connect()) {
                    return null;
                }
            }

            //criar statement
            CallableStatement statement = m_connection.prepareCall("{call GET_PROJECT_ELEC_VEHICLES(?,?)}");
            statement.setInt(1, projectPK);
            statement.registerOutParameter(2, OracleTypes.CURSOR);

            //executar query
            statement.execute();

            ResultSet elecVehicles = (ResultSet) statement.getObject(2);

            return elecVehicles;
        } catch (SQLException ex) {
            Logger.getLogger(ProjectWriter.class.getName()).log(Level.SEVERE, null, ex);
            return null;//returns null so the caller knows the connection failed
        }
    }

    ResultSet getVehicleThrottles(int vehiclePK) {
        try {
            if (m_connection == null) {
                if (!connect()) {
                    return null;
                }
            }

            //criar statement
            CallableStatement statement = m_connection.prepareCall("{call GET_VEHICLE_THROTTLES(?,?)}");
            statement.setInt(1, vehiclePK);
            statement.registerOutParameter(2, OracleTypes.CURSOR);

            //executar query
            statement.execute();

            ResultSet throttles = (ResultSet) statement.getObject(2);

            return throttles;
        } catch (SQLException ex) {
            Logger.getLogger(ProjectWriter.class.getName()).log(Level.SEVERE, null, ex);
            return null;//returns null so the caller knows the connection failed
        }
    }

    ResultSet getThrottleRegimes(int vehiclePK, String throttleID) {
        try {
            if (m_connection == null) {
                if (!connect()) {
                    return null;
                }
            }

            //criar statement
            CallableStatement statement = m_connection.prepareCall("{call GET_VEHICLE_THROTTLE_REGIMES(?,?,?)}");
            statement.setInt(1, vehiclePK);
            statement.setString(2, throttleID);
            statement.registerOutParameter(3, OracleTypes.CURSOR);

            //executar query
            statement.execute();

            ResultSet regimes = (ResultSet) statement.getObject(3);

            return regimes;
        } catch (SQLException ex) {
            Logger.getLogger(ProjectWriter.class.getName()).log(Level.SEVERE, null, ex);
            return null;//returns null so the caller knows the connection failed
        }
    }

    ResultSet getVehicleGears(int vehiclePK) {
        try {
            if (m_connection == null) {
                if (!connect()) {
                    return null;
                }
            }

            //criar statement
            CallableStatement statement = m_connection.prepareCall("{call GET_VEHICLE_GEARS(?,?)}");
            statement.setInt(1, vehiclePK);
            statement.registerOutParameter(2, OracleTypes.CURSOR);

            //executar query
            statement.execute();

            ResultSet gears = (ResultSet) statement.getObject(2);

            return gears;
        } catch (SQLException ex) {
            Logger.getLogger(ProjectWriter.class.getName()).log(Level.SEVERE, null, ex);
            return null;//returns null so the caller knows the connection failed
        }
    }

    ResultSet getVehicleVelocityLimits(int vehiclePK) {
        try {
            if (m_connection == null) {
                if (!connect()) {
                    return null;
                }
            }

            //criar statement
            CallableStatement statement = m_connection.prepareCall("{call GET_VEHICLE_VEL_LIMITS(?,?)}");
            statement.setInt(1, vehiclePK);
            statement.registerOutParameter(2, OracleTypes.CURSOR);

            //executar query
            statement.execute();

            ResultSet limits = (ResultSet) statement.getObject(2);

            return limits;
        } catch (SQLException ex) {
            Logger.getLogger(ProjectWriter.class.getName()).log(Level.SEVERE, null, ex);
            return null;//returns null so the caller knows the connection failed
        }
    }

    int projectNameExists(String projectName) {
        try {
            if (m_connection == null) {
                if (!connect()) {
                    return -1;
                }
            }

            //criar statement
            CallableStatement statement = m_connection.prepareCall("{call CHECK_PROJECT_EXISTS(?,?)}");
            statement.setString(1, projectName);
            statement.registerOutParameter(2, Types.INTEGER);

            //executar query
            statement.execute();

            return statement.getInt(2);

        } catch (SQLException ex) {
            Logger.getLogger(ProjectWriter.class.getName()).log(Level.SEVERE, null, ex);
            return -1;//returns null so the caller knows the connection failed
        }
    }

}
