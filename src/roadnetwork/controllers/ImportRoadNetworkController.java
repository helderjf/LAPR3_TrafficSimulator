/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadnetwork.controllers;

import IO.ImportXML;
import java.io.File;
import java.util.ArrayList;
import java.util.Vector;
import roadnetwork.domain.Junction;
import roadnetwork.domain.Manager;
import roadnetwork.domain.Section;

/**
 *
 * @author Tiago
 */
public class ImportRoadNetworkController {

    private Manager m_manager;

    public ImportRoadNetworkController(Manager manager) {
        m_manager = manager;
    }

    public boolean canImportRoadNetwork() {
        if (m_manager.getCurrentProject() == null) {
            return false;
        }
        return m_manager.getCurrentProject().canImportRoadNetwork();
    }

    public boolean importRoadNetwork(File file) {
        ImportXML importXML = new ImportXML(file);

        Vector<String> characteristics = importXML.importRoadNetwork();
        ArrayList<Junction> junctions = importXML.importNodes();
        ArrayList<Section> sections = importXML.importSections(junctions);

        return m_manager.getCurrentProject().createRoadNetwork(characteristics.elementAt(0), characteristics.elementAt(1), junctions, sections);
    }

}
