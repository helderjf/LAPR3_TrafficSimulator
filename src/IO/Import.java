/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IO;

import java.util.ArrayList;
import roadnetwork.domain.*;

/**
 *
 * @author ruben
 */
public interface Import {
    
    public String[] importRoadNetwork();
    
    /**
     * Import nodes from a file
     * @param path for the file
     * @return ArrayList of nodes names 
     */
    public ArrayList<Junction> importNodes();
    
    /**
     * import sections from an xml file 
     * @param path
     * @return Dom Node type with all sections
     */
    public ArrayList<Section> importSections();
    
    /**
     * import vehicles from an xml file
     * @return ArrayList of vehicles
     */
    public ArrayList<Vehicle> importVehicles();
}
