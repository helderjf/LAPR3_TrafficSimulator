/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadnetwork.domain;

import java.io.File;
import java.util.ArrayList;
import IO.*;
import org.w3c.dom.*;

/**
 *
 * @author André Pedrosa, Hélder Faria, José Miranda, Rubén Rosário
 */
public class Project {

    private RoadNetwork m_roadNetwork;
    private ArrayList<Vehicle> m_vehicleList;
    
    
    
    
    public ArrayList<Vehicle> getVehicleList() {
        return m_vehicleList;
    }

    public RoadNetwork getRoadNetwork() {
        return m_roadNetwork;
    }
    
    /**
     * recebe um ficheiro xml e cria a road network
     * @param file input file 
     */
    public void importRoadNetworkXML(File file)
    {
        Import importObject = new ImportXML(file);
        
        ArrayList<String> list = importObject.importNodes();
        org.w3c.dom.Node domNode = importObject.importSections();

        //criar sections e adiciona-los a lista da road network
        for (int i = 0; i < domNode.getChildNodes().getLength(); i++) {
            Section section = createRoadSection(domNode.getChildNodes().item(i));
            m_roadNetwork.getSectionList().add(section);
        }
    }
    
    /**
     * create a road section
     * @param node dom 
     */
    private Section createRoadSection(org.w3c.dom.Node node){
        Section section = new Section();
        String beginNode = node.getAttributes().getNamedItem("begin").getNodeValue();
        String endNode = node.getAttributes().getNamedItem("end").getNodeValue();
        //set section beggining node
        //set section ending node
        //set outros atributos
        
        return section;
    }
    
}
