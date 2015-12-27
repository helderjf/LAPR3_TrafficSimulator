/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.access.layer;

import java.util.ArrayList;
import java.util.HashMap;
import roadnetwork.domain.CombustionVehicle;
import roadnetwork.domain.SectionDirection;
import roadnetwork.domain.Junction;
import roadnetwork.domain.Project;
import roadnetwork.domain.Regime;
import roadnetwork.domain.Road;
import roadnetwork.domain.RoadNetwork;
import roadnetwork.domain.Section;
import roadnetwork.domain.SectionTypology;
import static roadnetwork.domain.SectionTypology.*;
import roadnetwork.domain.Segment;
import roadnetwork.domain.Throttle;
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

    public Project getProjectByID(String pid) {
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

        Section section1 = new Section("E01", node0, node1, SectionTypology.regular_road, SectionDirection.bidirectional, 0, new Wind(20, 3), list1);

        Segment segment3 = new Segment(1, 100, 0, 10, 90, 0, 30);
        Segment segment4 = new Segment(2, 100, 0.5, 5, 90, 0, 20);
        ArrayList<Segment> list2 = new ArrayList();
        list2.add(segment3);
        list2.add(segment4);

        Section section2 = new Section("E01", node1, node2, SectionTypology.regular_road, SectionDirection.bidirectional, 0, new Wind(-5, 3), list2);
        ArrayList<Section> sectionlist1 = new ArrayList();
        sectionlist1.add(section1);
        sectionlist1.add(section2);

        Road road1 = new Road(1, "E01");
        road1.setSections(sectionlist1);

        Segment segment5 = new Segment(1, 100, 0, 100, 120, 50, 100);
        ArrayList<Segment> list3 = new ArrayList();
        list3.add(segment5);
        Section section3 = new Section("A01", node0, node2, SectionTypology.highway, SectionDirection.bidirectional, 12, new Wind(-5, 3), list3);
        ArrayList<Section> sectionlist2 = new ArrayList();
        sectionlist2.add(section3);

        Road road2 = new Road(2, "A01");
        road2.setSections(sectionlist2);

        Segment segment6 = new Segment(1, 100, 0.125, 10, 61, 50, 100);
        ArrayList<Segment> list4 = new ArrayList();
        list4.add(segment6);
        Section section4 = new Section("A03", node1, node2, SectionTypology.highway, SectionDirection.bidirectional, 4, new Wind(-5, 3), list4);
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
        roadNetwork1.setName("road network1");
        roadNetwork1.setDescription("road network de testes 1");
        roadNetwork1.setNodeList(nodeList);
        roadNetwork1.setSectionList(completeSectionList);

        HashMap velocityLimit = new HashMap();
        velocityLimit.put(highway, 60);
        ArrayList<Double> gearList = new ArrayList();
        gearList.add(3.5);
        gearList.add(2.5);
        gearList.add(1.25);
        gearList.add(0.9);

        Regime r1 = new Regime(85, 1000, 2499, 8.2);
        Regime r2 = new Regime(95, 2500, 3999, 6.2);
        Regime r3 = new Regime(80, 4000, 5500, 10.2);
        ArrayList<Regime> lr1 = new ArrayList();
        lr1.add(r1);
        lr1.add(r2);
        lr1.add(r3);
        Throttle t1 = new Throttle("25", lr1);
        
        Regime r4 = new Regime(135, 1000, 2499, 5.2);
        Regime r5 = new Regime(150, 2500, 3999, 3.2);
        Regime r6 = new Regime(140, 4000, 5500, 8.2);
        ArrayList<Regime> lr2 = new ArrayList();
        lr2.add(r4);
        lr2.add(r5);
        lr2.add(r6);
        Throttle t2 = new Throttle("50", lr2);
        
        Regime r7 = new Regime(200, 1000, 2499, 2.2);
        Regime r8 = new Regime(240, 2500, 3999, 1.2);
        Regime r9 = new Regime(190, 4000, 5500, 4.2);
        ArrayList<Regime> lr3 = new ArrayList();
        lr3.add(r7);
        lr3.add(r8);
        lr3.add(r9);
        Throttle t3 = new Throttle("100", lr3);
        
        ArrayList<Throttle> throttleList1 = new ArrayList();
        throttleList1.add(t1);
        throttleList1.add(t2);
        throttleList1.add(t3);
        
        Vehicle vehicle1 = new CombustionVehicle("dummy1", "descricao", 1400, "car", 120, 0.35, 150, 0.01, 0.5, velocityLimit,
                250, 2500, 8.2, 1000, 5500, 2.6, "gasoline", gearList,throttleList1);

        ArrayList<Vehicle> vehicleList = new ArrayList();
        vehicleList.add(vehicle1);

        Project projecto1 = new Project();
        projecto1.setName("projecto1");
        projecto1.setDescription("projecto de testes 1");
        projecto1.setRoadNetwork(roadNetwork1);
        projecto1.setVehicleList(vehicleList);
        projecto1.setState(new ProjectStateSimulationReady(projecto1));
        return projecto1;

    }

    public Project newProject() {
        return new Project();
    }

}
