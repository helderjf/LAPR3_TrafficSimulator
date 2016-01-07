/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IO;

import java.io.File;
import javax.xml.parsers.*;
import org.w3c.dom.*;

/**
 *
 * @author antonio
 */
public class ImportSimulationXML {

    private final String EXPECTED_FILE_EXTENSION = "xml";

    //private Project project;
    //private Simulation simulation;
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

    public boolean read(String filePath) {
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

            String simID;
            String simDesc;

            Node nodeSim = nodeSimList.item(i);
            if (nodeSim.getNodeType() != Node.ELEMENT_NODE) {
                return false;
            }
            Element simElement = (Element) nodeSim;

            simID = simElement.getAttribute("id");
            simDesc = simElement.getAttribute("description");

            System.out.println(simID);
            System.out.println(simDesc);

            Node trafficList = simElement.getElementsByTagName("traffic_list").item(0);
            if (trafficList.getNodeType() != Node.ELEMENT_NODE) {
                return false;
            }
            Element trafElement = (Element) trafficList;

            NodeList patternNodeList = trafElement.getElementsByTagName("traffic_pattern");

            for (int j = 0; j < patternNodeList.getLength(); j++) {

                Node patternNode = patternNodeList.item(j);

                if (patternNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element patternElement = (Element) patternNode;

                    String beginNode = patternElement.getAttribute("begin");
                    String endNode = patternElement.getAttribute("end");

                    System.out.println(j + " pattern b:" + beginNode + "e:" + endNode);

                    String vehicleName = patternElement.getElementsByTagName("vehicle").item(0).getTextContent();
                    System.out.println(j + " vehicle:" + vehicleName);
                    String arrivalRate = patternElement.getElementsByTagName("arrival_rate").item(0).getTextContent();
                    System.out.println(j + " arrival_rate:" + arrivalRate);

                }

            }

        }
        return true;
    }
}
