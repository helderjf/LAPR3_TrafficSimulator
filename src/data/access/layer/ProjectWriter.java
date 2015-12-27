/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.access.layer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import roadnetwork.domain.Junction;
import roadnetwork.domain.Project;
import roadnetwork.domain.RoadNetwork;
import roadnetwork.domain.Section;
import roadnetwork.domain.Segment;
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
        try {
            m_project = project;
            m_projectName = m_project.getName();
            m_projectDescription = m_project.getDescription();
            m_projectState = m_project.getState().getClass().getSimpleName();
            System.out.println("começa a gravar proj");
            m_projectPK = m_dao.saveNewProject(m_projectName, m_projectDescription, m_projectState);
            System.out.println("proje pk =" + m_projectPK);
            m_project.setPK(m_projectPK);
            if (m_project.hasRoadNetwork()) {
                m_roadNetwork = m_project.getRoadNetwork();
                m_roadNetworkName = m_roadNetwork.getName();
                m_roadNetworkDescription = m_roadNetwork.getDescription();
                m_roadNetworkPK = m_dao.saveNewRoadNetwork(m_projectPK, m_roadNetworkName, m_roadNetworkDescription);
                m_roadNetwork.setPK(m_roadNetworkPK);

                m_nodeList = m_roadNetwork.getNodeList();
                for (Junction it : m_nodeList) {
                    it.setPK(m_dao.saveNewNode(m_roadNetworkPK, it.getJunctionId()));
                }

//TENTATIVA DE FAZER COM ARRAYS ORACLE
//            String[] nodeNames= new String[m_nodeList.size()];
//            for(int i =0; i<m_nodeList.size();i++){
//                nodeNames[i] = m_nodeList.get(i).getJunctionId();
//            }
//            
//            Integer[] nodesPK = new Integer[m_nodeList.size()];
//            nodesPK = m_dao.saveNewNodes(m_roadNetworkPK,nodeNames);
//            
//            for(int i = 0; i<m_nodeList.size();i++){
//                m_nodeList.get(i).setPK(nodesPK[i]);
//            }
//TENTATIVA DE FAZER COM ARRAYS ORACLE
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

                    ArrayList<Segment> segmentList = section.getSegmentsList();
                    for (Segment segment : segmentList) {//TO DO verificação de que não index de segmentos repetidos para a mesma section
                        m_dao.saveNewSegment(
                                section.getPK(),
                                segment.getIndex(),
                                segment.getInitialHeight(),
                                segment.getSlope(),
                                segment.getLenght(),
                                segment.getMax_Velocity(),
                                segment.getMin_Velocity(),
                                segment.getMax_Vehicles()
                        );
                    }

                }

            }

            if (m_project.hasVehicles()) {
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
                    
                    m_dao.saveNewVehicleVelocityLimits()
                    m_dao.saveNewVehicleType()
                    m_dao.saveNewVehicleGears();
                    m_dao.saveNewVehicleThrottle();
                    
                    

                    
                    
                }

            }

        } catch (SQLException ex) {
            Logger.getLogger(ProjectWriter.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;//TO DO fazer verificação
    }

}
