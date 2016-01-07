/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadnetwork.controllers;

import IO.ImportXML;
import java.io.File;
import java.util.ArrayList;
import roadnetwork.domain.Junction;
import roadnetwork.domain.Manager;
import roadnetwork.domain.Section;

/**
 *
 * @author Tiago
 */
public class ImportRoadNetworkController {
    
    private Manager m_manager;
    
    public ImportRoadNetworkController(Manager manager){
        m_manager = manager;
    }
    
    public boolean canImportRoadNetwork(){
        
        return m_manager.getCurrentProject().canImportRoadNetwork();
    }
    
    public boolean importRoadNetwork(File file){
        ImportXML importXML = new ImportXML(file);
        
        String [] characteristics = importXML.importRoadNetwork();
        ArrayList<Junction> junctions = importXML.importNodes();
        ArrayList<Section> sections = importXML.importSections();
        
        return m_manager.getCurrentProject().createRoadNetwork(characteristics[0], characteristics[1], junctions, sections);
    }
    
}
