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
    public Node importSections() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

        Document document = documentBuilder(inputFile);
        Node rootNode = document.getDocumentElement(); 
        NodeList nList = rootNode.getChildNodes();

        for (int i = 0; i < nList.getLength(); i++) {
            Node childNode = nList.item(i);

            if (childNode.getNodeName().equals("section_list")) {

                return childNode;
            }
        }
        return null;
    }
    
}
