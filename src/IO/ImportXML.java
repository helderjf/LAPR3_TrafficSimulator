/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IO;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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
        section.setRoadId(roadName);
        
        //definir typology
        String typology = domElement.getElementsByTagName("typology").item(0).getNodeValue();
        section.setTypology(typology);
        
        //definir typology
        String direction = domElement.getElementsByTagName("direction").item(0).getNodeValue();
        section.setDirection(Direction.valueOf(direction));
        
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
        segment.setInitial_Height(Double.parseDouble(segmentHeight));
        segment.setSlope(Double.parseDouble(segmentSlope));
        segment.setLenght(Double.parseDouble(segmentLength));
        segment.setMax_Velocity(Double.parseDouble(maxVelocity));
        segment.setMin_Velocity(Double.parseDouble(minVelocity));
        segment.setRrc(segmentRrc);
        segment.setNumber_vehicles(numberVehicles);
        
        return segment;
    }
    
}
