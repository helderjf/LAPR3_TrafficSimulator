/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IO;

import java.util.ArrayList;
import org.w3c.dom.*;

/**
 *
 * @author ruben
 */
public interface Import {
    
    /**
     * Import nodes from a file
     * @param path for the file
     * @return ArrayList of nodes names 
     */
    public ArrayList<String> importNodes();
    
    /**
     * import sections from an xml file 
     * @param path
     * @return Dom Node type with all sections
     */
    public Node importSections();
}
