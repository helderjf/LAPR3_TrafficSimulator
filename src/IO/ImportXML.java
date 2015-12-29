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
        xmlDocument = documentBuilder (inputFile);
        assert (xmlDocument != null);
    }
    
    @Override
    public ArrayList<String> importNodes() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
        Node rootNode = xmlDocument.getDocumentElement(); 
        NodeList nList = rootNode.getChildNodes();

        ArrayList<String> list = new ArrayList();
        
        for (int i = 0; i < nList.getLength(); i++) {
            Node childNode = nList.item(i);
            if (childNode.getNodeName().equals("node_list")) { //tratar lista de nos
                for (int j = 0; j < childNode.getChildNodes().getLength(); j++) {
                    list.add(childNode.getChildNodes().item(j).getNodeValue());
                }
            }
        }
        return list;
    }
    
    
    
    //Criar o documento no formato DOM apartir do ficheiro dado como argumento
    private Document documentBuilder (File file)
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
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        ArrayList<Section> list = new ArrayList();
        
        NodeList sectionList = xmlDocument.getElementsByTagName("section_list");
        
        for (int i = 0; i < sectionList.getLength(); i++) {
            Node childNode = sectionList.item(i);
            Section section  = getSection(childNode);
            list.add(section);
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
        section.setWind(new Wind(Double.parseDouble(wind_direction), Double.parseDouble(wind_speed)));  
        
        //definir lista de segment
        NodeList domSegmentList = domElement.getElementsByTagName("segment_list");
        ArrayList<Segment> segmentList = new ArrayList();
        for (int i = 0; i < domSegmentList.getLength(); i++) {
            Node segmentNode = domSegmentList.item(i);
            Segment segment = getSegment(segmentNode);
            segmentList.add(segment);
        }
        section.setSegmentsList(segmentList);
        return section;
    }

    private Segment getSegment(Node segmentNode) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Segment segment = new Segment();
        
        Element domElement = (Element) segmentNode;
        String segmentIndex = domElement.getAttribute("id");
        String segmentHeight = domElement.getElementsByTagName("height").item(0).getNodeValue();
        String segmentSlope = domElement.getElementsByTagName("slope").item(0).getNodeValue();
        segmentSlope = segmentSlope.split("%")[0];
        String segmentLength = domElement.getElementsByTagName("length").item(0).getNodeValue();
        segmentLength = segmentLength.split("km")[0];
        double  segmentRrc = Double.parseDouble(domElement.getElementsByTagName("rrc").item(0).getNodeValue());
        String maxVelocity = domElement.getElementsByTagName("max_velocity").item(0).getNodeValue(); 
        maxVelocity = maxVelocity.split("km/h")[0];
        String minVelocity = domElement.getElementsByTagName("min_velocity").item(0).getNodeValue(); 
        minVelocity = minVelocity.split("km/h")[0];
        int  numberVehicles = Integer.parseInt(domElement.getElementsByTagName("number_vehicles").item(0).getNodeValue());
        
        segment.setIndex(Integer.parseInt(segmentIndex));
        segment.setInitialHeight(Double.parseDouble(segmentHeight));
        segment.setSlope(Double.parseDouble(segmentSlope));
        segment.setLenght(Double.parseDouble(segmentLength));
        segment.setMax_Velocity(Double.parseDouble(maxVelocity));
        segment.setMin_Velocity(Double.parseDouble(minVelocity));
        //segment.setRcc(segmentRrc);
        segment.setNumber_vehicles(numberVehicles);
        
        return segment;
    }
    
    
    public ArrayList<Vehicle> importVehicle()
    {
        ArrayList<Vehicle> list = new ArrayList();
        
        //get vehicle list
        Node domVehicleList = xmlDocument.getElementsByTagName("vehicle_list").item(0);
        
        //get vehicles
        for (int i = 0; i < domVehicleList.getChildNodes().getLength(); i++) {
            
            Node nodeChild = domVehicleList.getChildNodes().item(i);
            Vehicle vehicle = imporVehicle(nodeChild);
            list.add(vehicle);
        }
        return list;
    }

    private Vehicle imporVehicle(Node nodeChild) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Vehicle vehicle;
        Element domElementVehicle = (Element)nodeChild;
        
        String name = domElementVehicle.getAttribute("name");
        String description = domElementVehicle.getAttribute("description");
        
        String type = domElementVehicle.getElementsByTagName("type").item(0).getNodeValue();
        String motorization = domElementVehicle.getElementsByTagName("motorization").item(0).getNodeValue();

        switch (motorization){
                case "electic":
                    vehicle = new ElectricVehicle();
                break;
                case "hybrid":
                    vehicle = new HybridVehicle();
                break;
                default:
                    String fuel = domElementVehicle.getElementsByTagName("fuel").item(0).getNodeValue();
                    vehicle  = new CombustionVehicle();
                break;
                }
        String mass = domElementVehicle.getElementsByTagName("mass").item(0).getNodeValue();
        mass = mass.split("kg")[0];
        String load = domElementVehicle.getElementsByTagName("load").item(0).getNodeValue();
        load = load.split("kg")[0];
        String drag = domElementVehicle.getElementsByTagName("drag").item(0).getNodeValue();
        String rrc = domElementVehicle.getElementsByTagName("rrc").item(0).getNodeValue();
        String wheelSize = domElementVehicle.getElementsByTagName("wheel_size").item(0).getNodeValue();
        
        Node velocityLimitList = domElementVehicle.getElementsByTagName("velocity_limit_list").item(0);
        for (int i = 0; i < velocityLimitList.getChildNodes().getLength(); i++) {
            Element velocityLimit = (Element)velocityLimitList.getChildNodes().item(i);
            String segment = velocityLimit.getElementsByTagName("segment").item(0).getNodeValue();
            String limit = velocityLimit.getElementsByTagName("limit").item(0).getNodeValue();
            HashMap<String,Double> hash = new HashMap();
            hash.put(segment, Double.parseDouble(limit));
            vehicle.setVelocityLimits(hash);
        }

        //GET ENERGY
        Element energy = (Element)domElementVehicle.getElementsByTagName("energy").item(0);
        String torque = energy.getElementsByTagName("torque").item(0).getNodeValue();
        String rpm = energy.getElementsByTagName("rpm").item(0).getNodeValue();
        String consumption = energy.getElementsByTagName("consumption").item(0).getNodeValue();
        String minRpm = energy.getElementsByTagName("min_rpm").item(0).getNodeValue();
        String maxRpm = energy.getElementsByTagName("max_rpm").item(0).getNodeValue();
        String finalDriveRation = energy.getElementsByTagName("final_drive_ratio").item(0).getNodeValue();
        
        Element domGearList = (Element)domElementVehicle.getElementsByTagName("gear_list").item(0);
        ArrayList<Double> gearList = new ArrayList();
        for (int i = 0; i < domGearList.getChildNodes().getLength(); i++) {
            Element domGear = (Element) domGearList.getChildNodes().item(i);
            //int gearId = Integer.parseInt(domGear.getAttribute("id"));
            double ratio = Double.parseDouble(domGear.getFirstChild().getNodeValue());

            gearList.add(ratio);
        }
        
        vehicle.setName(name);
        vehicle.setDescription(description);
        vehicle.setType(type);
        vehicle.setMass(Double.parseDouble(mass));
        vehicle.setLoad(Double.parseDouble(load));
        vehicle.setDragCoefficient(Double.parseDouble(drag));
        vehicle.setRcc(Double.parseDouble(rrc));
        vehicle.setWheelSize(Double.parseDouble(wheelSize));
        vehicle.setTorque(Double.parseDouble(torque));
        vehicle.setConsuption(Double.parseDouble(consumption));
        vehicle.setMinRPM(Double.parseDouble(minRpm));
        vehicle.setMaxRPM(Double.parseDouble(maxRpm));
        vehicle.setFinalDriveRatio(Double.parseDouble(finalDriveRation));
        //vehicle.setGearList(gearList);
        return vehicle;    
    }
}
