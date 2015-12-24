/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.access.layer;

import java.util.ArrayList;
import java.util.HashMap;
import roadnetwork.domain.CombustionVehicle;
import roadnetwork.domain.Direction;
import roadnetwork.domain.Junction;
import roadnetwork.domain.Project;
import roadnetwork.domain.Road;
import roadnetwork.domain.RoadNetwork;
import roadnetwork.domain.Section;
import roadnetwork.domain.SectionTypology;
import roadnetwork.domain.Segment;
import roadnetwork.domain.Vehicle;
import roadnetwork.domain.Wind;
import roadnetwork.state.ProjectStateSimulationReady;

/**
 *
 * @author André Pedrosa, Hélder Faria, José Miranda, Rubén Rosário
 */
public class ProjectReader {

    public ProjectReader() {
        
    }
 
    public Project getProjectByID(String pid){
               //CREATE MOCK OBJECTS
        Junction node0 = new Junction("node0");
        Junction node1 = new Junction("node1");
        Junction node2 = new Junction("node2");
        ArrayList<Junction> nodeList = new ArrayList();
        nodeList.add(node0);
        nodeList.add(node1);
        nodeList.add(node2);

        Segment segment1 = new Segment(1, 100, 1.5, 3.2, 90, 0, 20);
        Segment segment2 = new Segment(2, 148, 1.5, 3.2, 90, 0, 20);
        ArrayList<Segment> list1 = new ArrayList();
        list1.add(segment1);
        list1.add(segment2);

        Section section1 = new Section(1, "E01", node0, node1, SectionTypology.regularroad, Direction.bidirectional, 0, new Wind(20, 3), list1);

        Segment segment3 = new Segment(1, 100, 0, 10, 90, 0, 30);
        Segment segment4 = new Segment(2, 100, 0.5, 5, 90, 0, 20);
        ArrayList<Segment> list2 = new ArrayList();
        list2.add(segment3);
        list2.add(segment4);

        Section section2 = new Section(2, "E01", node1, node2, SectionTypology.regularroad, Direction.bidirectional, 0, new Wind(-5, 3), list2);
        ArrayList<Section> sectionlist1 = new ArrayList();
        sectionlist1.add(section1);
        sectionlist1.add(section2);

        Road road1 = new Road(1, "E01");
        road1.setSections(sectionlist1);

        Segment segment5 = new Segment(1, 100, 0, 100, 120, 50, 100);
        ArrayList<Segment> list3 = new ArrayList();
        list3.add(segment5);
        Section section3 = new Section(3, "A01", node0, node2, SectionTypology.highway, Direction.bidirectional, 12, new Wind(-5, 3), list3);
        ArrayList<Section> sectionlist2 = new ArrayList();
        sectionlist2.add(section3);

        Road road2 = new Road(2, "A01");
        road2.setSections(sectionlist2);

        Segment segment6 = new Segment(1, 100, 0.125, 10, 61, 50, 100);
        ArrayList<Segment> list4 = new ArrayList();
        list4.add(segment6);
        Section section4 = new Section(4, "A03", node1, node2, SectionTypology.highway, Direction.bidirectional, 4, new Wind(-5, 3), list4);
        ArrayList<Section> sectionlist3 = new ArrayList();
        sectionlist3.add(section4);

        Road road3 = new Road(3, "A03");
        road3.setSections(sectionlist3);

        ArrayList<Section> completeSectionList = new ArrayList();
        completeSectionList.add(section1);
        completeSectionList.add(section2);
        completeSectionList.add(section3);
        completeSectionList.add(section4);

        RoadNetwork roadNetwork1 = new RoadNetwork();
        roadNetwork1.setNodeList(nodeList);
        roadNetwork1.setSectionList(completeSectionList);

        HashMap velocityLimit = new HashMap();
        velocityLimit.put("Highway", 60);
        ArrayList<Double> gearList = new ArrayList();
        gearList.add(3.5);
        gearList.add(2.5);
        gearList.add(1.25);
        gearList.add(0.9);

        Vehicle vehicle1 = new CombustionVehicle("v1", "dummy1", 1400, "car", 120, 0.35, 150, 0.01, 0.5, velocityLimit, 250, 2500, 8.2, 1000, 5500, 2.6, "gasoline", gearList);

        ArrayList<Vehicle> vehicleList = new ArrayList();
        vehicleList.add(vehicle1);

        Project projecto1 = new Project();
        projecto1.setRoadNetwork(roadNetwork1);
        projecto1.setVehicleList(vehicleList);
        projecto1.setState(new ProjectStateSimulationReady(projecto1));
        return projecto1;

    }

    public Project newProject() {
        return new Project();
    }
    
    
    
}
