/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IO;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import roadnetwork.domain.*;

/**
 *
 * @author ruben
 */
public class ImportXML implements Import {

    private final File inputFile;
    private final Document xmlDocument;

    public ImportXML(File file) {
        inputFile = file;
        xmlDocument = documentBuilder();
        assert (xmlDocument != null);
    }

    @Override
    public Vector<String> importRoadNetwork() {

        /*Node rootNode = xmlDocument.getDocumentElement(); 
         NodeList nList = rootNode.getChildNodes();
         */
        NodeList nList = xmlDocument.getElementsByTagName("Network");

        Vector<String> characteristics = new Vector<>();

        characteristics.add(((Element) nList.item(0)).getAttribute("id"));
        characteristics.add(((Element) nList.item(0)).getAttribute("description"));

        return characteristics;
    }

    @Override
    public ArrayList<Junction> importNodes() {

        Node rootNode = xmlDocument.getDocumentElement();
        NodeList nList = rootNode.getChildNodes();

        ArrayList<Junction> list = new ArrayList();

        for (int i = 0; i < nList.getLength(); i++) {
            Node childNode = nList.item(i);
            if (childNode.getNodeName().equals("node_list")) { //tratar lista de nos
                NodeList nodeL = childNode.getChildNodes();
                for (int j = 0; j < childNode.getChildNodes().getLength(); j++) {
                    Node node = nodeL.item(j);
                    if (node.hasAttributes()) {
                        list.add(new Junction(((Element) node).getAttribute("id")));
                    }
                }
            }
        }
        return list;
    }

    //Criar o documento no formato DOM apartir do ficheiro dado como argumento
    private Document documentBuilder() {
        try {
            //Create document builder
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(inputFile);
            return document;

        } catch (ParserConfigurationException ex) {
            Logger.getLogger(ImportXML.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(ImportXML.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ImportXML.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public ArrayList<Section> importSections(ArrayList<Junction> junctions) {

        ArrayList<Section> list = new ArrayList();

        Node root = xmlDocument.getDocumentElement();
        NodeList nList = root.getChildNodes();

        for (int i = 0; i < nList.getLength(); i++) {
            Node childNode = nList.item(i);
            if (childNode.getNodeName().equals("section_list")) { //tratar lista de nos
                NodeList nodeL = childNode.getChildNodes();
                for (int j = 0; j < childNode.getChildNodes().getLength(); j++) {
                    Node node = nodeL.item(j);
                    if (node.hasAttributes()) {
                        list.add(getSection(node, junctions));
                    }
                }
            }
        }

        return list;
    }

    /**
     * Recebe um Dom node com uma section de devolve um objeto Section
     *
     * @param node
     * @return section object
     */
    private Section getSection(Node node, ArrayList<Junction> junctions) {
        Section section = new Section();
        String begginingNode = node.getAttributes().getNamedItem("begin").getNodeValue();
        String endingNode = node.getAttributes().getNamedItem("end").getNodeValue();

        // definir begin e end junction
        for (Junction j : junctions) {
            if (j.getJunctionId().equals(begginingNode)) {
                section.setBeginningNode(j);
            }
            if (j.getJunctionId().equals(endingNode)) {
                section.setEndingNode(j);
            }
        }

        //definir road name
        Element domElement = (Element) node;
        String roadName = domElement.getElementsByTagName("road").item(0).getFirstChild().getNodeValue();
        section.setRoadName(roadName.replace("\"", ""));

        //definir typology
        String typology = domElement.getElementsByTagName("typology").item(0).getFirstChild().getNodeValue();
        typology = typology.replace(" ", "_");
        section.setTypology(SectionTypology.valueOf(typology));

        //definir typology
        String direction = domElement.getElementsByTagName("direction").item(0).getFirstChild().getNodeValue();
        section.setDirection(SectionDirection.valueOf(direction));

        //definir Toll
        String toll = domElement.getElementsByTagName("toll").item(0).getFirstChild().getNodeValue();
        section.setToll(Double.parseDouble(toll));

        //definir wind //HERE!!!
        String wind_direction = domElement.getElementsByTagName("wind_direction").item(0).getFirstChild().getNodeValue();
        String wind_speed = domElement.getElementsByTagName("wind_speed").item(0).getFirstChild().getNodeValue();
        wind_speed = wind_speed.toLowerCase().split("m/s")[0].replace(" ", "");
        double windDir = Double.parseDouble(wind_direction) * Math.PI / 180.0;
        section.setWind(new Wind(windDir, Double.parseDouble(wind_speed)));

        //definir lista de segment
        NodeList domSegmentList = domElement.getElementsByTagName("segment_list");
        ArrayList<Segment> segmentList = new ArrayList();

        NodeList nodeL = domSegmentList.item(0).getChildNodes();
        for (int i = 0; i < nodeL.getLength(); i++) {
            Node nodeSegment = nodeL.item(i);
            if (nodeSegment.hasAttributes()) {
                segmentList.add(getSegment(nodeSegment));
            }
        }
        section.setSegmentsList(segmentList);
        return section;
    }

    private Segment getSegment(Node segmentNode) {

        Segment segment = new Segment();

        Element domElement = (Element) segmentNode;
        String segmentIndex = domElement.getAttribute("id");
        String segmentHeight = domElement.getElementsByTagName("height").item(0).getFirstChild().getNodeValue();
        String segmentSlope = domElement.getElementsByTagName("slope").item(0).getFirstChild().getNodeValue();
        segmentSlope = segmentSlope.split("%")[0];
        double slope = Math.atan(Double.parseDouble(segmentSlope) / 100.0);
        /*if(segmentSlope.startsWith("-")){
         slope = slope * -1.0;
         }*/
        String segmentLength = domElement.getElementsByTagName("length").item(0).getFirstChild().getNodeValue();
        double length;
        if (segmentLength.toLowerCase().contains("km")) {
            segmentLength = segmentLength.toLowerCase().split("km")[0].replace(" ", "");
            length = Double.parseDouble(segmentLength) * 1000;
        } else {
            length = Double.parseDouble(segmentLength.toLowerCase().split("m")[0].replace(" ", ""));
        }
        String maxVelocity = domElement.getElementsByTagName("max_velocity").item(0).getFirstChild().getNodeValue();
        double max_velocity;
        if (maxVelocity.toLowerCase().contains("km/h")) {
            maxVelocity = maxVelocity.toLowerCase().split("km/h")[0].replace(" ", "");
            max_velocity = Double.parseDouble(maxVelocity) * 1000.0 / 3600.0;
        } else {
            max_velocity = Double.parseDouble(maxVelocity.toLowerCase().split("m/s")[0].replace(" ", ""));
        }
        String minVelocity = domElement.getElementsByTagName("min_velocity").item(0).getFirstChild().getNodeValue();
        double min_velocity;
        if (minVelocity.toLowerCase().contains("km/h")) {
            minVelocity = minVelocity.toLowerCase().split("km/h")[0].replace(" ", "");
            min_velocity = Double.parseDouble(minVelocity) * 1000.0 / 3600.0;
        } else {
            min_velocity = Double.parseDouble(minVelocity.toLowerCase().split("m/s")[0].replace(" ", ""));
        }
        String numberVehicles = domElement.getElementsByTagName("number_vehicles").item(0).getFirstChild().getNodeValue();

        segment.setIndex(Integer.parseInt(segmentIndex));
        segment.setInitialHeight(Double.parseDouble(segmentHeight));
        segment.setSlope(slope);
        segment.setLenght(length);
        segment.setMax_Velocity(max_velocity);
        segment.setMin_Velocity(min_velocity);
        segment.setMax_Vehicles(Integer.parseInt(numberVehicles));

        return segment;
    }

    @Override
    public ArrayList<Vehicle> importVehicles() {
        ArrayList<Vehicle> list = new ArrayList();

        NodeList root = xmlDocument.getElementsByTagName("vehicle_list");

        //NodeList nList = root.getChildNodes();
        NodeList nList = ((Element) root.item(0)).getElementsByTagName("vehicle");

        for (int i = 0; i < nList.getLength(); i++) {
            Node node = nList.item(i);
            list.add(getVehicle(node));
        }

        return list;
    }

    private Vehicle getVehicle(Node nodeChild) {

        Vehicle vehicle;
        Element domElementVehicle = (Element) nodeChild;

        String name = domElementVehicle.getAttribute("name");
        String description = domElementVehicle.getAttribute("description");

        String type = domElementVehicle.getElementsByTagName("type").item(0).getTextContent();
        String motorization = domElementVehicle.getElementsByTagName("motorization").item(0).getTextContent();

        switch (motorization) {
            case "electric":
                vehicle = new ElectricVehicle();
                break;
            case "hybrid":
                vehicle = new HybridVehicle();
                break;
            default:
                vehicle = new CombustionVehicle();
                break;
        }
        String fuel = domElementVehicle.getElementsByTagName("fuel").item(0).getTextContent();
        String mass = domElementVehicle.getElementsByTagName("mass").item(0).getTextContent();
        mass = mass.toLowerCase().split("kg")[0].replace(" ", "");
        String load = domElementVehicle.getElementsByTagName("load").item(0).getTextContent();
        load = load.toLowerCase().split("kg")[0].replace(" ", "");
        String drag = domElementVehicle.getElementsByTagName("drag").item(0).getTextContent();
        String frontalArea = domElementVehicle.getElementsByTagName("frontal_area").item(0).getTextContent();
        String rrc = domElementVehicle.getElementsByTagName("rrc").item(0).getTextContent();
        String wheelSize = domElementVehicle.getElementsByTagName("wheel_size").item(0).getTextContent();

        Node velocityLimit = domElementVehicle.getElementsByTagName("velocity_limit_list").item(0);
        if (velocityLimit != null) {
            NodeList velocityLimitList = ((Element) velocityLimit).getElementsByTagName("velocity_limit");

            for (int i = 0; i < velocityLimitList.getLength(); i++) {
                //String segment = velocityLimit.getElementsByTagName("segment_type").item(0).getNodeValue();
                String segment = ((Element) velocityLimitList.item(i)).getElementsByTagName("segment_type").item(0).getTextContent();
                //String limit = velocityLimit.getElementsByTagName("limit").item(0).getNodeValue();
                String limit = ((Element) velocityLimitList.item(i)).getElementsByTagName("limit").item(0).getTextContent();
                vehicle.addVelocityLimit(SectionTypology.valueOf(segment.replace("_", "").toLowerCase()), (double) Double.parseDouble(limit) * 1000 / 3600);
            }
        } else {
            vehicle.setVelocityLimit(new HashMap<>());
        }
        //GET ENERGY
        Element energy = (Element) domElementVehicle.getElementsByTagName("energy").item(0);
        String minRpm = energy.getElementsByTagName("min_rpm").item(0).getTextContent();
        String maxRpm = energy.getElementsByTagName("max_rpm").item(0).getTextContent();
        String finalDriveRation = energy.getElementsByTagName("final_drive_ratio").item(0).getTextContent();

        Element domGearElement = (Element) domElementVehicle.getElementsByTagName("gear_list").item(0);
        NodeList domGearList = domGearElement.getElementsByTagName("gear");
        HashMap<Integer, Double> gearList = new HashMap<>();
        for (int i = 0; i < domGearList.getLength(); i++) {
            Element domGear = (Element) domGearList.item(i);
            int gearId = Integer.parseInt(domGear.getAttribute("id"));
            double ratio = Double.parseDouble(domGear.getElementsByTagName("ratio").item(0).getTextContent());
            gearList.put(gearId, ratio);
        }
        Element domThrottle = (Element) domElementVehicle.getElementsByTagName("throttle_list").item(0);
        NodeList domThrottleList = domThrottle.getElementsByTagName("throttle");
        ArrayList<Throttle> throttleList = new ArrayList();
        for (int i = 0; i < domThrottleList.getLength(); i++) {
            Element domT = (Element) domThrottleList.item(i);
            Throttle throttle = new Throttle();
            throttle.setID(domT.getAttribute("id"));
            NodeList regimeList = domT.getElementsByTagName("regime");
            for (int j = 0; j < regimeList.getLength(); j++) {
                Regime regime = new Regime();
                regime.setTorque(Double.parseDouble(((Element) regimeList.item(j)).getElementsByTagName("torque").item(0).getTextContent()));
                regime.setM_rpmLow(Double.parseDouble(((Element) regimeList.item(j)).getElementsByTagName("rpm_low").item(0).getTextContent()));
                regime.setM_rpmHigh(Double.parseDouble(((Element) regimeList.item(j)).getElementsByTagName("rpm_high").item(0).getTextContent()));
                if (!motorization.equals("electric")) {
                    regime.setM_sfc(Double.parseDouble(((Element) regimeList.item(j)).getElementsByTagName("SFC").item(0).getTextContent()));
                }
                throttle.addRegime(regime);
            }
            throttleList.add(throttle);
        }

        vehicle.setName(name);
        vehicle.setDescription(description);
        vehicle.setType(type);
        vehicle.setMass(Double.parseDouble(mass));
        vehicle.setLoad(Double.parseDouble(load));
        vehicle.setDragCoefficient(Double.parseDouble(drag));
        vehicle.setFrontalArea(Double.parseDouble(frontalArea));
        vehicle.setRcc(Double.parseDouble(rrc));
        vehicle.setWheelSize(Double.parseDouble(wheelSize));
        vehicle.setMinRPM(Double.parseDouble(minRpm));
        vehicle.setMaxRPM(Double.parseDouble(maxRpm));
        vehicle.setFinalDriveRatio(Double.parseDouble(finalDriveRation));
        vehicle.setFuel(fuel);
        vehicle.setGearList(gearList);
        vehicle.setThrottleList(throttleList);
        return vehicle;
    }
}
