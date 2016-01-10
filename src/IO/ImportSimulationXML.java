/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IO;

import java.io.File;
import java.util.ArrayList;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import roadnetwork.domain.Junction;
import roadnetwork.domain.Project;
import roadnetwork.domain.TrafficPattern;
import roadnetwork.domain.Vehicle;

/**
 *
 * @author antonio
 */
public class ImportSimulationXML {

    private final String EXPECTED_FILE_EXTENSION = "xml";

    private File file;

    public ImportSimulationXML() {
    }

    /**
     * Validar se o Ficheiro existe e se a extensão é a pretendida
     */
    private File readFile(String filePath) {
        File f = new File(filePath);
        if (f.exists() && f.isFile()) {
            String filename = f.getName();
            String extension = filename.substring(filename.lastIndexOf(".") + 1, filename.length());
            if (extension.toLowerCase().equals(EXPECTED_FILE_EXTENSION)) {
                return f;
            }
        }
        return null;
    }

    /**
     * Inicializar o Builder para tratar do XML
     */
    private Document initializeDocumentBuilder(File file) {
        Document doc = null;
        try {
            DocumentBuilderFactory docBuildFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuildFactory.newDocumentBuilder();
            doc = docBuilder.parse(file);
            doc.getDocumentElement().normalize();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error initializing xml builder.");
            doc = null;
        }
        return doc;
    }

    /**
     *
     * @param filePath
     * @param project
     * @return
     */
    public boolean read(String filePath, Project project) {
        file = readFile(filePath);
        if (file == null) // FICHEIRO NÃO ENCONTRADO
        {
            return false;
        }
        Document doc = initializeDocumentBuilder(file);
        if (doc == null) // FALHA A INICIALIZAR O DOCUMENT BUILDER
        {
            return false;
        }

        NodeList nodeSimList = doc.getElementsByTagName("Simulation");

        if (nodeSimList == null) //NÃO ENCONTROU NADA COM TAG SIMULATION
        {
            return false;
        }

        for (int i = 0; i < nodeSimList.getLength(); i++) {

            ArrayList<TrafficPattern> ltp = new ArrayList<>();
            String simID;
            String simDesc;

            //Vai buscar a Simulation
            Node nodeSim = nodeSimList.item(i);
            if (nodeSim.getNodeType() != Node.ELEMENT_NODE) {
                return false;
            }
            Element simElement = (Element) nodeSim;

            //Vai buscar ID e Description, não usados, utilizador define os seus
            simID = simElement.getAttribute("id");
            simDesc = simElement.getAttribute("description");

            //Abre a Traffic List
            Node trafficList = simElement.getElementsByTagName("traffic_list").item(0);
            if (trafficList.getNodeType() != Node.ELEMENT_NODE) {
                return false;
            }
            Element trafElement = (Element) trafficList;

            NodeList patternNodeList = trafElement.getElementsByTagName("traffic_pattern");

            //Para cada Traffic Pattern que está na Traffic List
            for (int j = 0; j < patternNodeList.getLength(); j++) {

                Node patternNode = patternNodeList.item(j);

                if (patternNode.getNodeType() == Node.ELEMENT_NODE) {
                    
                    TrafficPattern tp = new TrafficPattern();
                    Element patternElement = (Element) patternNode;
                    
                    //Verifica se existe RoadNetwork
                    if(project.getRoadNetwork()==null)
                        return false; //Caso nao existe o o import para
                    //Vai buscar os nodes
                    Junction beginNode = project.getRoadNetwork().getNodeByID(
                            patternElement.getAttribute("begin"));
                    tp.setBeginNode(beginNode);
                    Junction endNode = project.getRoadNetwork().getNodeByID(
                            patternElement.getAttribute("end"));
                    tp.setEndNode(endNode);
                    
                    //Verifica se existe VehicleList
                    if(project.getVehicleList()==null)
                        return false; //Caso nao existe o o import para
                    //Vai buscar Vehicle
                    String vehicleName = 
                            patternElement.getElementsByTagName("vehicle").item(0).getTextContent();
                    Vehicle v = project.getVehicleByName(vehicleName);
                    tp.setVehicle(v);
                    
                    //ArrivalRate
                    String arrivalRate = 
                            patternElement.getElementsByTagName("arrival_rate").item(0).getTextContent();
                    
                    tp.setArrivalRate(arrivalRate);
                    if(tp.validate()){
                        ltp.add(tp);
                    }
                    //
                }

            }

        }
        return true;
    }
}
