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
    
    public ImportXML(File file){
        inputFile = file;
        xmlDocument = documentBuilder();
        assert (xmlDocument != null);
    }
    

    @Override
    public String[] importRoadNetwork() {
        
        Node rootNode = xmlDocument.getDocumentElement(); 
        NodeList nList = rootNode.getChildNodes();
        
        String[] characteristics = new String[2];
        
        characteristics[0] = nList.item(0).getAttributes().getNamedItem("id").getNodeValue();
        characteristics[1] = nList.item(0).getAttributes().getNamedItem("description").getNodeValue();
        
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
                for (int j = 0; j < childNode.getChildNodes().getLength(); j++) {
                    list.add(new Junction(childNode.getChildNodes().item(j).getAttributes().getNamedItem("id").getNodeValue()));
                }
            }
        }
        return list;
    }
    
    
    
    //Criar o documento no formato DOM apartir do ficheiro dado como argumento
    private Document documentBuilder ()
    {
        try
        { 
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
    public ArrayList<Section> importSections() {
        
        ArrayList<Section> list = new ArrayList();
        
        NodeList sectionList = xmlDocument.getElementsByTagName("section_list");
        
        for (int i = 0; i < sectionList.getLength(); i++) {
            Node childNode = sectionList.item(i);
            list.add(getSection(childNode));
        }
        return list;
    }
    
    /**
     * Recebe um Dom node com uma section de devolve um objeto Section
     * @param node
     * @return section object
     */
    private Section getSection(Node node){
        Section section = new Section();
        String begginingNode = node.getAttributes().getNamedItem("begin").getNodeValue();
        String endingNode = node.getAttributes().getNamedItem("end").getNodeValue();
        
        // definir begin e end junction
        section.setBeginningNode(new Junction(begginingNode));
        section.setEndingNode(new Junction(endingNode));
        
        //definir road name
        Element domElement = (Element)node;
        String roadName = domElement.getElementsByTagName("road").item(0).getNodeValue();
        section.setRoadName(roadName);
        
        //definir typology
        String typology = domElement.getElementsByTagName("typology").item(0).getNodeValue();
        section.setTypology(SectionTypology.valueOf(typology));
        
        //definir typology
        String direction = domElement.getElementsByTagName("direction").item(0).getNodeValue();
        section.setDirection(SectionDirection.valueOf(direction));
        
        //definir Toll
        String toll = domElement.getElementsByTagName("toll").item(0).getNodeValue();
        section.setToll(Double.parseDouble(toll));
        
        //definir wind
        String wind_direction = domElement.getElementsByTagName("wind_direction").item(0).getNodeValue();
        String wind_speed = domElement.getElementsByTagName("wind_speed").item(0).getNodeValue();
        wind_speed = wind_speed.toLowerCase().split("m/s")[0].replace(" ", "");
        section.setWind(new Wind(Double.parseDouble(wind_direction), Double.parseDouble(wind_speed)));  
        
        //definir lista de segment
        NodeList domSegmentList = domElement.getElementsByTagName("segment_list");
        ArrayList<Segment> segmentList = new ArrayList();
        for (int i = 0; i < domSegmentList.getLength(); i++) {
            Node segmentNode = domSegmentList.item(i);
            segmentList.add(getSegment(segmentNode));
        }
        section.setSegmentsList(segmentList);
        return section;
    }

    private Segment getSegment(Node segmentNode) {
        
        Segment segment = new Segment();
        
        Element domElement = (Element) segmentNode;
        String segmentIndex = domElement.getAttribute("id");
        String segmentHeight = domElement.getElementsByTagName("height").item(0).getNodeValue();
        String segmentSlope = domElement.getElementsByTagName("slope").item(0).getNodeValue();
        segmentSlope = segmentSlope.split("%")[0];
        String segmentLength = domElement.getElementsByTagName("length").item(0).getNodeValue();
        segmentLength = segmentLength.toLowerCase().split("km")[0].replace(" ", "");
        String maxVelocity = domElement.getElementsByTagName("max_velocity").item(0).getNodeValue(); 
        maxVelocity = maxVelocity.toLowerCase().split("km/h")[0].replace(" ", "");
        String minVelocity = domElement.getElementsByTagName("min_velocity").item(0).getNodeValue(); 
        minVelocity = minVelocity.toLowerCase().split("km/h")[0].replace(" ", "");
        String numberVehicles = domElement.getElementsByTagName("number_vehicles").item(0).getNodeValue();
        
        segment.setIndex(Integer.parseInt(segmentIndex));
        segment.setInitialHeight(Double.parseDouble(segmentHeight));
        segment.setSlope(Double.parseDouble(segmentSlope));
        segment.setLenght(Double.parseDouble(segmentLength));
        segment.setMax_Velocity(Double.parseDouble(maxVelocity));
        segment.setMin_Velocity(Double.parseDouble(minVelocity));
        segment.setMax_Vehicles(Double.parseDouble(numberVehicles));
        
        return segment;
    }
    
    
    @Override
    public ArrayList<Vehicle> importVehicles()
    {
        ArrayList<Vehicle> list = new ArrayList();
        
        //get vehicle list
        Node domVehicleList = xmlDocument.getElementsByTagName("vehicle_list").item(0);
        
        //get vehicles
        for (int i = 0; i < domVehicleList.getChildNodes().getLength(); i++) {
            
            Node nodeChild = domVehicleList.getChildNodes().item(i);
            list.add(getVehicle(nodeChild));
        }
        return list;
    }

    private Vehicle getVehicle(Node nodeChild) {
        
        Vehicle vehicle;
        Element domElementVehicle = (Element)nodeChild;
        
        String name = domElementVehicle.getAttribute("name");
        String description = domElementVehicle.getAttribute("description");
        
        String type = domElementVehicle.getElementsByTagName("type").item(0).getNodeValue();
        String motorization = domElementVehicle.getElementsByTagName("motorization").item(0).getNodeValue();

        switch (motorization){
                case "electric":
                    vehicle = new ElectricVehicle();
                break;
                case "hybrid":
                    vehicle = new HybridVehicle();
                break;
                default:
                    String fuel = domElementVehicle.getElementsByTagName("fuel").item(0).getNodeValue();
                    vehicle = new CombustionVehicle(fuel);
                break;
                }
        String mass = domElementVehicle.getElementsByTagName("mass").item(0).getNodeValue();
        mass = mass.toLowerCase().split("kg")[0].replace(" ", "");
        String load = domElementVehicle.getElementsByTagName("load").item(0).getNodeValue();
        load = load.toLowerCase().split("kg")[0].replace(" ", "");
        String drag = domElementVehicle.getElementsByTagName("drag").item(0).getNodeValue();
        String frontalArea = domElementVehicle.getElementsByTagName("frontal_Area").item(0).getNodeValue();
        String rrc = domElementVehicle.getElementsByTagName("rrc").item(0).getNodeValue();
        String wheelSize = domElementVehicle.getElementsByTagName("wheel_size").item(0).getNodeValue();
        
        Node velocityLimitList = domElementVehicle.getElementsByTagName("velocity_limit_list").item(0);
        for (int i = 0; i < velocityLimitList.getChildNodes().getLength(); i++) {
            Element velocityLimit = (Element)velocityLimitList.getChildNodes().item(i);
            //String segment = velocityLimit.getElementsByTagName("segment_type").item(0).getNodeValue();
            String segment = ((Element)velocityLimit.getChildNodes().item(0)).getElementsByTagName("segment_type").item(0).getNodeValue();
            //String limit = velocityLimit.getElementsByTagName("limit").item(0).getNodeValue();
            String limit = ((Element)velocityLimit.getChildNodes().item(0)).getElementsByTagName("limit").item(0).getNodeValue();
            vehicle.addVelocityLimit(SectionTypology.valueOf(segment), Double.parseDouble(limit));
        }

        //GET ENERGY
        Element energy = (Element)domElementVehicle.getElementsByTagName("energy").item(0);
        String minRpm = energy.getElementsByTagName("min_rpm").item(0).getNodeValue();
        String maxRpm = energy.getElementsByTagName("max_rpm").item(0).getNodeValue();
        String finalDriveRation = energy.getElementsByTagName("final_drive_ratio").item(0).getNodeValue();
        
        Element domGearList = (Element)domElementVehicle.getElementsByTagName("gear_list").item(0);
        HashMap<Integer, Double> gearList = new HashMap<>();
        for (int i = 0; i < domGearList.getChildNodes().getLength(); i++) {
            Element domGear = (Element) domGearList.getChildNodes().item(i);
            int gearId = Integer.parseInt(domGear.getAttribute("id"));
            double ratio = Double.parseDouble(domGear.getFirstChild().getNodeValue());
            gearList.put(gearId, ratio);
        }
        Element domThrottleList = (Element)domElementVehicle.getElementsByTagName("throttle_list").item(0);
        ArrayList<Throttle> throttleList = new ArrayList();
        for(int i = 0; i < domThrottleList.getChildNodes().getLength(); i++){
            Element domThrottle = (Element)domThrottleList.getElementsByTagName("throttle").item(0);
            Throttle throttle = new Throttle();
            throttle.setID(domThrottle.getAttribute("id"));
            for(int j = 0; j < domThrottle.getChildNodes().getLength(); j++){
                Regime regime = new Regime();
                regime.setTorque(Double.parseDouble(domThrottle.getElementsByTagName("torque").item(0).getNodeValue()));
                regime.setM_rpmLow(Double.parseDouble(domThrottle.getElementsByTagName("rpm_low").item(0).getNodeValue()));
                regime.setM_rpmHigh(Double.parseDouble(domThrottle.getElementsByTagName("rpm_high").item(0).getNodeValue()));
                regime.setM_sfc(Double.parseDouble(domThrottle.getElementsByTagName("SFC").item(0).getNodeValue()));
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
        if(vehicle instanceof CombustionVehicle){
            ((CombustionVehicle)vehicle).setGearList(gearList);
            ((CombustionVehicle)vehicle).setThrottleList(throttleList);
        }
        return vehicle;
    }
}
