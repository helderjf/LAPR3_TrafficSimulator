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
import java.sql.SQLRecoverableException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import roadnetwork.domain.Project;
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

    public boolean connect() throws SQLRecoverableException {

        try {
            m_connection = DriverManager.getConnection(m_dbUrl, m_user, m_pass);
            m_connection.setAutoCommit(false);
            System.out.println("Connection to database successfull");
            return true;
        } catch (SQLRecoverableException ex) {
            throw ex;
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

    public int saveNewProject(String projectName, String projectDescription, String projectState) throws SQLRecoverableException {
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

        } catch (SQLRecoverableException ex) {
            throw ex;
        } catch (SQLException ex) {
            System.out.println(ex);
            //Logger.getLogger(ProjectWriter.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }

    public int saveNewRoadNetwork(int projectPK, String roadNetworkName, String roadNetworkDescription) throws SQLRecoverableException {

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

        } catch (SQLRecoverableException ex) {
            throw ex;
        } catch (SQLException ex) {
            Logger.getLogger(ProjectWriter.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }

    }

    public int saveNewNode(int roadNetworkPK, String nodeName) throws SQLRecoverableException {
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
        } catch (SQLRecoverableException ex) {
            throw ex;
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
            double windVelocity) throws SQLRecoverableException {

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

        } catch (SQLRecoverableException ex) {
            throw ex;
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
            double maxVehicles) throws SQLRecoverableException {

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

        } catch (SQLRecoverableException ex) {
            throw ex;
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
            String fuel,
            double mass,
            double load,
            double dragCoefficient,
            double frontalArea,
            double rcc,
            double wheelSize,
            double finalDriveRatio,
            double minRPM,
            double maxRPM) throws SQLRecoverableException {
        try {
            if (m_connection == null) {
                if (!connect()) {
                    return -1;//returns -1 so the caller knows the connection failed
                }
            }

            //create statement
            CallableStatement statement = m_connection.prepareCall("{call SAVE_NEW_VEHICLE(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            statement.setInt(1, projectPK);
            statement.setString(2, name);
            statement.setString(3, description);
            statement.setString(4, type);
            statement.setString(5, fuel);
            statement.setDouble(6, mass);
            statement.setDouble(7, load);
            statement.setDouble(8, dragCoefficient);
            statement.setDouble(9, frontalArea);
            statement.setDouble(10, rcc);
            statement.setDouble(11, wheelSize);
            statement.setDouble(12, finalDriveRatio);
            statement.setDouble(13, minRPM);
            statement.setDouble(14, maxRPM);
            statement.registerOutParameter(15, Types.INTEGER);

            //execute statement
            statement.execute();

            return statement.getInt(15);

        } catch (SQLRecoverableException ex) {
            throw ex;
        } catch (SQLException ex) {
            Logger.getLogger(ProjectWriter.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }

    }

    int saveNewVehicleVelocityLimits(int vehiclePK, SectionTypology typology, Double speed) throws SQLRecoverableException {

        try {
            if (m_connection == null) {
                if (!connect()) {
                    return -1;//returns -1 so the caller knows the connection failed
                }
            }

            //create statement
            CallableStatement statement = m_connection.prepareCall("{call SAVE_NEW_VEHICLE_VEL_LIMIT(?,?,?,?)}");
            statement.setInt(1, vehiclePK);
            statement.setString(2, typology.toString());
            statement.setDouble(3, speed);
            statement.registerOutParameter(4, Types.INTEGER);
            //execute statement
            statement.execute();

            if (statement.getInt(4) != 1) {
                return -1;
            }

            return 1;

        } catch (SQLRecoverableException ex) {
            throw ex;
        } catch (SQLException ex) {
            Logger.getLogger(ProjectWriter.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }

    }

    public int saveNewCombustionVehicle(int vehiclePK) throws SQLRecoverableException {
        try {
            if (m_connection == null) {
                if (!connect()) {
                    return -1;//returns -1 so the caller knows the connection failed
                }
            }

            CallableStatement statement = m_connection.prepareCall("{call SAVE_NEW_COMBUSTION_V(?)}");
            statement.setInt(1, vehiclePK);

            //execute statement
            statement.execute();

            return 1;

        } catch (SQLRecoverableException ex) {
            throw ex;
        } catch (SQLException ex) {
            Logger.getLogger(ProjectWriter.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }

    }

    public int saveNewHybridVehicle(int vehiclePK, double energyRegenerationRatio) throws SQLRecoverableException {
        try {
            if (m_connection == null) {
                if (!connect()) {
                    return -1;//returns -1 so the caller knows the connection failed
                }
            }

            CallableStatement statement = m_connection.prepareCall("{call SAVE_NEW_HYBRID_V(?,?)}");
            statement.setInt(1, vehiclePK);
            statement.setDouble(2, energyRegenerationRatio);

            //execute statement
            statement.execute();

            return 1;

        } catch (SQLRecoverableException ex) {
            throw ex;
        } catch (SQLException ex) {
            Logger.getLogger(ProjectWriter.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }

    }

    public int saveNewElectricVehicle(int vehiclePK, double energyRegenerationRatio) throws SQLRecoverableException {
        try {
            if (m_connection == null) {
                if (!connect()) {
                    return -1;//returns -1 so the caller knows the connection failed
                }
            }

            CallableStatement statement = m_connection.prepareCall("{call SAVE_NEW_ELECTRIC_V(?,?)}");
            statement.setInt(1, vehiclePK);
            statement.setDouble(2, energyRegenerationRatio);

            //execute statement
            statement.execute();

            return 1;

        } catch (SQLRecoverableException ex) {
            throw ex;
        } catch (SQLException ex) {
            Logger.getLogger(ProjectWriter.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }

    }

    public int saveNewVehicleGear(int vehiclePK, int gear, Double ratio) throws SQLRecoverableException {
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

        } catch (SQLRecoverableException ex) {
            throw ex;
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
            double sfc) throws SQLRecoverableException {
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

        } catch (SQLRecoverableException ex) {
            throw ex;
        } catch (SQLException ex) {
            Logger.getLogger(ProjectWriter.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }

    int saveNewSimulation(int projPK, String simName, String simDesc, String simState) throws SQLRecoverableException {
        try {
            if (m_connection == null) {
                if (!connect()) {
                    return -1;//returns -1 so the caller knows the connection failed
                }
            }
            //create statement
            CallableStatement statement = m_connection.prepareCall("{call SAVE_NEW_SIMULATION(?,?,?,?,?)}");
            statement.setInt(1, projPK);
            statement.setString(2, simName);
            statement.setString(3, simDesc);
            statement.setString(4, simState);

            statement.registerOutParameter(5, Types.INTEGER);

            //execute statement
            statement.execute();
            return statement.getInt(5);

        } catch (SQLRecoverableException ex) {
            throw ex;
        } catch (SQLException ex) {
            Logger.getLogger(ProjectWriter.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }

    }

    int saveNewTrafficPattern(int simPK, int bNodePK, int eNodePK, int vPK, double aRate) throws SQLRecoverableException {
        try {
            if (m_connection == null) {
                if (!connect()) {
                    return -1;//returns -1 so the caller knows the connection failed
                }
            }
            //create statement
            CallableStatement statement = m_connection.prepareCall("{call SAVE_NEW_TRAFFIC_PATTERN(?,?,?,?,?,?)}");
            statement.setInt(1, simPK);
            statement.setInt(2, bNodePK);
            statement.setInt(3, eNodePK);
            statement.setInt(4, vPK);
            statement.setDouble(5, aRate);

            statement.registerOutParameter(6, Types.INTEGER);

            //execute statement
            statement.execute();
            return statement.getInt(6);

        } catch (SQLRecoverableException ex) {
            throw ex;
        } catch (SQLException ex) {
            Logger.getLogger(ProjectWriter.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }

    int saveNewRun(int simPK, String name, double duration, double timeStep, String bestPathMehod) throws SQLRecoverableException {
        try {
            if (m_connection == null) {
                if (!connect()) {
                    return -1;//returns -1 so the caller knows the connection failed
                }
            }
            //create statement
            CallableStatement statement = m_connection.prepareCall("{call SAVE_NEW_SIMULATION_RUN(?,?,?,?,?,?)}");
            statement.setInt(1, simPK);
            statement.setString(2, name);
            statement.setDouble(3, duration);
            statement.setDouble(4, timeStep);
            statement.setString(5, bestPathMehod);

            statement.registerOutParameter(6, Types.INTEGER);

            //execute statement
            statement.execute();
            return statement.getInt(6);

        } catch (SQLRecoverableException ex) {
            throw ex;
        } catch (SQLException ex) {
            Logger.getLogger(ProjectWriter.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }

    int saveRunDroppedVehicles(int runPK, int[] droppedTrafPatList, double[] droppedIntantsList) throws SQLRecoverableException {
        try {
            if (m_connection == null) {
                if (!connect()) {
                    return -1;//returns -1 so the caller knows the connection failed
                }
            }

            ArrayDescriptor oracleIntArray = ArrayDescriptor.createDescriptor("INTEGER_T", m_connection);
            ArrayDescriptor oracleFloatArray = ArrayDescriptor.createDescriptor("FLOAT_T", m_connection);

            ARRAY trafficpaterns = new ARRAY(oracleIntArray, m_connection, droppedTrafPatList);
            ARRAY dropInstants = new ARRAY(oracleFloatArray, m_connection, droppedTrafPatList);

            //create statement
            CallableStatement statement = m_connection.prepareCall("{call SAVE_DROPPED_VEHICLES(?,?,?,?)}");
            statement.setInt(1, runPK);
            statement.setObject(2, trafficpaterns);
            statement.setObject(3, dropInstants);

            statement.registerOutParameter(4, OracleTypes.ARRAY, "INTEGER_T");

            //execute statement
            statement.execute();

            //int[] errors = new int[droppedTrafPatList.length];//to do tratar erros
            //ARRAY ora_errors = ((OracleCallableStatement) statement).getARRAY(4);//to do tratar erros
            //errors = ora_errors.getIntArray();//to do tratar erros
            return 1;

        } catch (SQLRecoverableException ex) {
            throw ex;
        } catch (SQLException ex) {
            Logger.getLogger(ProjectWriter.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }

    int[] saveRunInjectedVehicles(int runPK, int[] injectedVTrafPatList) throws SQLRecoverableException {
        try {
            if (m_connection == null) {
                if (!connect()) {
                    return null;//returns -1 so the caller knows the connection failed
                }
            }

            ArrayDescriptor oracleIntArray = ArrayDescriptor.createDescriptor("INTEGER_T", m_connection);

            ARRAY trafficpaterns = new ARRAY(oracleIntArray, m_connection, injectedVTrafPatList);

            //create statement
            CallableStatement statement = m_connection.prepareCall("{call SAVE_INJECTED_VEHICLES(?,?,?)}");
            statement.setInt(1, runPK);
            statement.setObject(2, trafficpaterns);
            statement.registerOutParameter(3, OracleTypes.ARRAY, "INTEGER_T");

            //execute statement
            statement.execute();

            ARRAY output = ((OracleCallableStatement) statement).getARRAY(3);

            int[] injectedVehiclesPK = output.getIntArray();

            //int[] errors = new int[injectedVehiclesPK.length];//to do tratar erros
            //ARRAY ora_errors = ((OracleCallableStatement) statement).getARRAY(4);//to do tratar erros
            //errors = ora_errors.getIntArray();//to do tratar erros
            return injectedVehiclesPK;

        } catch (SQLRecoverableException ex) {
            throw ex;
        } catch (SQLException ex) {
            Logger.getLogger(ProjectWriter.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    int saveRunInjectedVehiclesBehaviours(int[] injectedVehiclesPKsExtended,
            int[] injectedVTrafPatList,
            int[] injectedVSection,
            int[] injectedVSegment,
            String[] injectedVTravelDirection,
            double[] injectedVTimeIn,
            double[] injectedVTimeOut,
            double[] injectedVEnergy) throws SQLRecoverableException {

        try {
            if (m_connection == null) {
                if (!connect()) {
                    return -1;//returns -1 so the caller knows the connection failed
                }
            }

            ArrayDescriptor oracleIntArray = ArrayDescriptor.createDescriptor("INTEGER_T", m_connection);
            ArrayDescriptor oracleFloatArray = ArrayDescriptor.createDescriptor("FLOAT_T", m_connection);
            ArrayDescriptor oracleVarchar2Array = ArrayDescriptor.createDescriptor("VARCHAR2_T", m_connection);

            ARRAY injVehiclePKs = new ARRAY(oracleIntArray, m_connection, injectedVehiclesPKsExtended);
            ARRAY sections = new ARRAY(oracleIntArray, m_connection, injectedVSection);
            ARRAY segments = new ARRAY(oracleIntArray, m_connection, injectedVSegment);
            ARRAY directions = new ARRAY(oracleVarchar2Array, m_connection, injectedVTravelDirection);
            ARRAY inTimes = new ARRAY(oracleFloatArray, m_connection, injectedVTimeIn);
            ARRAY exitTimes = new ARRAY(oracleFloatArray, m_connection, injectedVTimeOut);
            ARRAY energyConsumptions = new ARRAY(oracleFloatArray, m_connection, injectedVEnergy);

            //create statement
            CallableStatement statement = m_connection.prepareCall("{call SAVE_INJECTED_V_BEHAVIOURS(?,?,?,?,?,?,?,?)}");
            statement.setObject(1, injVehiclePKs);
            statement.setObject(2, sections);
            statement.setObject(3, segments);
            statement.setObject(4, directions);
            statement.setObject(5, inTimes);
            statement.setObject(6, exitTimes);
            statement.setObject(7, energyConsumptions);

            statement.registerOutParameter(8, OracleTypes.ARRAY, "INTEGER_T");

            //execute statement
            statement.execute();

            //int[] errors = new int[injectedVehiclesPK.length];//to do tratar erros
            //ARRAY ora_errors = ((OracleCallableStatement) statement).getARRAY(4);//to do tratar erros
            //errors = ora_errors.getIntArray();//to do tratar erros
            return 1;

        } catch (SQLRecoverableException ex) {
            throw ex;
        } catch (SQLException ex) {
            Logger.getLogger(ProjectWriter.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }

    int updateProject(int projectPK, String projectName, String projectDescription, String projectState) throws SQLRecoverableException {
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
        } catch (SQLRecoverableException ex) {
            throw ex;
        } catch (SQLException ex) {
            Logger.getLogger(ProjectWriter.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }

    int updateRoadNetwork(int roadNetworkPK, String roadNetworkName, String roadNetworkDescription) throws SQLRecoverableException {

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
        } catch (SQLRecoverableException ex) {
            throw ex;
        } catch (SQLException ex) {
            Logger.getLogger(ProjectWriter.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }

    int updateNode(int nodePK, String nodeName) throws SQLRecoverableException {
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

        } catch (SQLRecoverableException ex) {
            throw ex;
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
            double windVelocity) throws SQLRecoverableException {
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
        } catch (SQLRecoverableException ex) {
            throw ex;
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
            double maxVehicles) throws SQLRecoverableException {
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
        } catch (SQLRecoverableException ex) {
            throw ex;
        } catch (SQLException ex) {
            Logger.getLogger(ProjectWriter.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }

    }

    int updateSimulation(int simpk, String simName, String simDesc, String simState) throws SQLRecoverableException {
        try {
            if (m_connection == null) {
                if (!connect()) {
                    return -1;//returns -1 so the caller knows the connection failed
                }
            }

            CallableStatement statement = m_connection.prepareCall("{call UPDATE_SIMULATION(?,?,?,?)}");
            statement.setInt(1, simpk);
            statement.setString(2, simName);
            statement.setString(3, simDesc);
            statement.setString(4, simState);

            statement.execute();

            return 1;
        } catch (SQLRecoverableException ex) {
            throw ex;
        } catch (SQLException ex) {
            Logger.getLogger(ProjectWriter.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }

    int updateTrafficPattern(int tpPK, int bNodePK, int eNodePK, int vPK, double aRate) throws SQLRecoverableException {
        try {
            if (m_connection == null) {
                if (!connect()) {
                    return -1;//returns -1 so the caller knows the connection failed
                }
            }

            CallableStatement statement = m_connection.prepareCall("{call UPDATE_TRAFFIC_PATTERN(?,?,?,?,?)}");
            statement.setInt(1, tpPK);
            statement.setInt(2, bNodePK);
            statement.setInt(3, eNodePK);
            statement.setInt(4, vPK);
            statement.setDouble(5, aRate);

            statement.execute();

            return 1;
        } catch (SQLRecoverableException ex) {
            throw ex;
        } catch (SQLException ex) {
            Logger.getLogger(ProjectWriter.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }

    ArrayList<String> getOrderedProjectList() throws SQLRecoverableException {
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
        } catch (SQLRecoverableException ex) {
            throw ex;
        } catch (SQLException ex) {
            Logger.getLogger(ProjectWriter.class.getName()).log(Level.SEVERE, null, ex);
            return null;//returns -1 so the caller knows the connection failed
        }

    }

    ResultSet getProjectProperties(String projectName) throws SQLRecoverableException {
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

        } catch (SQLRecoverableException ex) {
            throw ex;
        } catch (SQLException ex) {
            Logger.getLogger(ProjectWriter.class.getName()).log(Level.SEVERE, null, ex);
            return null;//returns null so the caller knows the connection failed
        }

    }

    ResultSet getRoadNetwork(int projectPK) throws SQLRecoverableException {
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

        } catch (SQLRecoverableException ex) {
            throw ex;
        } catch (SQLException ex) {
            Logger.getLogger(ProjectWriter.class.getName()).log(Level.SEVERE, null, ex);
            return null;//returns null so the caller knows the connection failed
        }
    }

    ResultSet getRoadNetworkNodes(int roadNetworkPK) throws SQLRecoverableException {
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
        } catch (SQLRecoverableException ex) {
            throw ex;
        } catch (SQLException ex) {
            Logger.getLogger(ProjectWriter.class.getName()).log(Level.SEVERE, null, ex);
            return null;//returns null so the caller knows the connection failed
        }
    }

    ResultSet getRoadNetworkSections(int roadNetworkPK) throws SQLRecoverableException {
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

        } catch (SQLRecoverableException ex) {
            throw ex;
        } catch (SQLException ex) {
            Logger.getLogger(ProjectWriter.class.getName()).log(Level.SEVERE, null, ex);
            return null;//returns null so the caller knows the connection failed
        }
    }

    ResultSet getSectionSegments(int sectionPK) throws SQLRecoverableException {
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
        } catch (SQLRecoverableException ex) {
            throw ex;
        } catch (SQLException ex) {
            Logger.getLogger(ProjectWriter.class.getName()).log(Level.SEVERE, null, ex);
            return null;//returns null so the caller knows the connection failed
        }
    }

    ResultSet getCombustionVehicles(int projectPK) throws SQLRecoverableException {
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
        } catch (SQLRecoverableException ex) {
            throw ex;
        } catch (SQLException ex) {
            Logger.getLogger(ProjectWriter.class.getName()).log(Level.SEVERE, null, ex);
            return null;//returns null so the caller knows the connection failed
        }
    }

    ResultSet getHybridVehicles(int projectPK) throws SQLRecoverableException {
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
        } catch (SQLRecoverableException ex) {
            throw ex;
        } catch (SQLException ex) {
            Logger.getLogger(ProjectWriter.class.getName()).log(Level.SEVERE, null, ex);
            return null;//returns null so the caller knows the connection failed
        }
    }

    ResultSet getElectricVehicles(int projectPK) throws SQLRecoverableException {
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
        } catch (SQLRecoverableException ex) {
            throw ex;
        } catch (SQLException ex) {
            Logger.getLogger(ProjectWriter.class.getName()).log(Level.SEVERE, null, ex);
            return null;//returns null so the caller knows the connection failed
        }
    }

    ResultSet getVehicleThrottles(int vehiclePK) throws SQLRecoverableException {
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
        } catch (SQLRecoverableException ex) {
            throw ex;
        } catch (SQLException ex) {
            Logger.getLogger(ProjectWriter.class.getName()).log(Level.SEVERE, null, ex);
            return null;//returns null so the caller knows the connection failed
        }
    }

    ResultSet getThrottleRegimes(int vehiclePK, String throttleID) throws SQLRecoverableException {
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
        } catch (SQLRecoverableException ex) {
            throw ex;
        } catch (SQLException ex) {
            Logger.getLogger(ProjectWriter.class.getName()).log(Level.SEVERE, null, ex);
            return null;//returns null so the caller knows the connection failed
        }
    }

    ResultSet getVehicleGears(int vehiclePK) throws SQLRecoverableException {
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
        } catch (SQLRecoverableException ex) {
            throw ex;
        } catch (SQLException ex) {
            Logger.getLogger(ProjectWriter.class.getName()).log(Level.SEVERE, null, ex);
            return null;//returns null so the caller knows the connection failed
        }
    }

    ResultSet getVehicleVelocityLimits(int vehiclePK) throws SQLRecoverableException {
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
        } catch (SQLRecoverableException ex) {
            throw ex;
        } catch (SQLException ex) {
            Logger.getLogger(ProjectWriter.class.getName()).log(Level.SEVERE, null, ex);
            return null;//returns null so the caller knows the connection failed
        }
    }

    int projectNameExists(String projectName) throws SQLRecoverableException {
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

        } catch (SQLRecoverableException ex) {
            throw ex;
        } catch (SQLException ex) {
            Logger.getLogger(ProjectWriter.class.getName()).log(Level.SEVERE, null, ex);
            return -1;//returns null so the caller knows the connection failed
        }
    }

    int simulationExists(int projectPK, String simulationName) throws SQLRecoverableException {
        try {
            if (m_connection == null) {
                if (!connect()) {
                    return -1;
                }
            }

            //criar statement
            CallableStatement statement = m_connection.prepareCall("{call CHECK_SIMULATION_EXISTS(?,?,?)}");
            statement.setInt(1, projectPK);
            statement.setString(2, simulationName);
            statement.registerOutParameter(3, Types.INTEGER);

            //executar query
            statement.execute();

            return statement.getInt(3);

        } catch (SQLRecoverableException ex) {
            throw ex;
        } catch (SQLException ex) {
            Logger.getLogger(ProjectWriter.class.getName()).log(Level.SEVERE, null, ex);
            return -1;//returns null so the caller knows the connection failed
        }
    }

    int projectHasSimulations(int projectPK) throws SQLRecoverableException {
        try {
            if (m_connection == null) {
                if (!connect()) {
                    return -1;
                }
            }

            //criar statement
            CallableStatement statement = m_connection.prepareCall("{call CHECK_PROJECT_HAS_SIMULATIONS(?,?)}");
            statement.setInt(1, projectPK);
            statement.registerOutParameter(2, Types.INTEGER);

            //executar query
            statement.execute();

            return statement.getInt(2);

        } catch (SQLRecoverableException ex) {
            throw ex;
        } catch (SQLException ex) {
            Logger.getLogger(ProjectWriter.class.getName()).log(Level.SEVERE, null, ex);
            return -1;//returns null so the caller knows the connection failed
        }
    }

    HashMap<String, Integer> getOrderedSimulationList(int projpk) throws SQLRecoverableException {
        try {
            if (m_connection == null) {
                if (!connect()) {
                    return null;//returns -1 so the caller knows the connection failed
                }
            }

            //criar statement
            CallableStatement statement = m_connection.prepareCall("{call GET_ORDERED_SIMULATION_LIST(?,?)}");
            statement.setInt(1, projpk);
            statement.registerOutParameter(2, OracleTypes.CURSOR);

            //executar query
            statement.execute();

            m_output = (ResultSet) statement.getObject(2);

            HashMap<String, Integer> simulationMap = new HashMap();
            while (m_output.next()) {
                simulationMap.put(m_output.getString(2), m_output.getInt(1));
            }

            return simulationMap;

        } catch (SQLRecoverableException ex) {
            throw ex;
        } catch (SQLException ex) {
            Logger.getLogger(ProjectWriter.class.getName()).log(Level.SEVERE, null, ex);
            return null;//returns -1 so the caller knows the connection failed
        }
    }

    ResultSet getSimulation(int simPK) throws SQLRecoverableException {
        try {
            if (m_connection == null) {
                if (!connect()) {
                    return null;
                }
            }

            //criar statement
            CallableStatement statement = m_connection.prepareCall("{call GET_SIMULATION(?,?)}");
            statement.setInt(1, simPK);
            statement.registerOutParameter(2, OracleTypes.CURSOR);

            //executar query
            statement.execute();

            ResultSet simulation = (ResultSet) statement.getObject(2);

            return simulation;
        } catch (SQLRecoverableException ex) {
            throw ex;
        } catch (SQLException ex) {
            Logger.getLogger(ProjectWriter.class.getName()).log(Level.SEVERE, null, ex);
            return null;//returns null so the caller knows the connection failed
        }
    }

    ResultSet getTrafficPattern(int simPK) throws SQLRecoverableException {
        try {
            if (m_connection == null) {
                if (!connect()) {
                    return null;
                }
            }

            //criar statement
            CallableStatement statement = m_connection.prepareCall("{call GET_TRAFFIC_PATTERN_LIST(?,?)}");
            statement.setInt(1, simPK);
            statement.registerOutParameter(2, OracleTypes.CURSOR);

            //executar query
            statement.execute();

            ResultSet trafficPatternList = (ResultSet) statement.getObject(2);

            return trafficPatternList;
        } catch (SQLRecoverableException ex) {
            throw ex;
        } catch (SQLException ex) {
            Logger.getLogger(ProjectWriter.class.getName()).log(Level.SEVERE, null, ex);
            return null;//returns null so the caller knows the connection failed
        }
    }

    int simulationHasruns(int simpk) throws SQLRecoverableException {
        try {
            if (m_connection == null) {
                if (!connect()) {
                    return -1;
                }
            }

            //criar statement
            CallableStatement statement = m_connection.prepareCall("{call CHECK_SIMULATION_HAS_RUNS(?,?)}");
            statement.setInt(1, simpk);
            statement.registerOutParameter(2, Types.INTEGER);

            //executar query
            statement.execute();

            return statement.getInt(2);

        } catch (SQLRecoverableException ex) {
            throw ex;
        } catch (SQLException ex) {
            Logger.getLogger(ProjectWriter.class.getName()).log(Level.SEVERE, null, ex);
            return -1;//returns null so the caller knows the connection failed
        }
    }

    HashMap<String, Integer> getOrderedRunsList(int simpk) throws SQLRecoverableException {
        try {
            if (m_connection == null) {
                if (!connect()) {
                    return null;//returns -1 so the caller knows the connection failed
                }
            }

            //criar statement
            CallableStatement statement = m_connection.prepareCall("{call GET_ORDERED_RUNS_LIST(?,?)}");
            statement.setInt(1, simpk);
            statement.registerOutParameter(2, OracleTypes.CURSOR);

            //executar query
            statement.execute();

            m_output = (ResultSet) statement.getObject(2);

            HashMap<String, Integer> runsMap = new HashMap();
            while (m_output.next()) {
                runsMap.put(m_output.getString(2), m_output.getInt(1));
            }

            return runsMap;

        } catch (SQLRecoverableException ex) {
            throw ex;
        } catch (SQLException ex) {
            Logger.getLogger(ProjectWriter.class.getName()).log(Level.SEVERE, null, ex);
            return null;//returns -1 so the caller knows the connection failed
        }
    }

    ResultSet getRunResultsByTrafficPattern(Integer runPK) throws SQLRecoverableException {
        try {
            if (m_connection == null) {
                if (!connect()) {
                    return null;
                }
            }

            //criar statement
            CallableStatement statement = m_connection.prepareCall("{call GET_TRAF_PATS_AVG_CONSUMPTION(?,?)}");
            statement.setInt(1, runPK);
            statement.registerOutParameter(2, OracleTypes.CURSOR);

            //executar query
            statement.execute();

            ResultSet output = (ResultSet) statement.getObject(2);

            return output;

        } catch (SQLRecoverableException ex) {
            throw ex;
        } catch (SQLException ex) {
            Logger.getLogger(ProjectWriter.class.getName()).log(Level.SEVERE, null, ex);
            return null;//returns null so the caller knows the connection failed
        }
    }

    ResultSet getRunResultsByTrafficPatternAndSegments(Integer runPK) throws SQLRecoverableException {
        try {
            if (m_connection == null) {
                if (!connect()) {
                    return null;
                }
            }

            //criar statement
            CallableStatement statement = m_connection.prepareCall("{call GET_TRAF_PATS_SEGS_AVG_CONS(?,?)}");
            statement.setInt(1, runPK);
            statement.registerOutParameter(2, OracleTypes.CURSOR);

            //executar query
            statement.execute();

            ResultSet output = (ResultSet) statement.getObject(2);

            return output;

        } catch (SQLRecoverableException ex) {
            throw ex;
        } catch (SQLException ex) {
            Logger.getLogger(ProjectWriter.class.getName()).log(Level.SEVERE, null, ex);
            return null;//returns null so the caller knows the connection failed
        }
    }

    ResultSet getRunResultsForATrafficPattern(int runPK, int trafPatPK) throws SQLRecoverableException {
        try {
            if (m_connection == null) {
                if (!connect()) {
                    return null;
                }
            }

            //criar statement
            CallableStatement statement = m_connection.prepareCall("{call GET_SEGS_AVG_CONS_FOR_TRAFPAT(?,?,?)}");
            statement.setInt(1, runPK);
            statement.setInt(2, trafPatPK);
            statement.registerOutParameter(3, OracleTypes.CURSOR);

            //executar query
            statement.execute();

            ResultSet output = (ResultSet) statement.getObject(3);

            return output;
        } catch (SQLRecoverableException ex) {
            throw ex;
        } catch (SQLException ex) {
            Logger.getLogger(ProjectWriter.class.getName()).log(Level.SEVERE, null, ex);
            return null;//returns null so the caller knows the connection failed
        }
    }

    boolean deleteSimulationRun(int runPK) throws SQLRecoverableException {
        try {
            if (m_connection == null) {
                if (!connect()) {
                    return false;//returns -1 so the caller knows the connection failed
                }
            }

            //criar statement
            CallableStatement statement = m_connection.prepareCall("{call DELETE_SIMULATION_RUN(?)}");
            statement.setInt(1, runPK);

            //executar query
            statement.execute();

            return true;
        } catch (SQLRecoverableException ex) {
            throw ex;
        } catch (SQLException ex) {
            Logger.getLogger(ProjectWriter.class.getName()).log(Level.SEVERE, null, ex);
            return false;//returns -1 so the caller knows the connection failed
        }
    }

    public String getM_dbUrl() {
        return m_dbUrl;
    }
    
    

}
