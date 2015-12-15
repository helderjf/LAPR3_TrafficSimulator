/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IO;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
/**
 *
 * @author ruben
 */
public class ImportXML implements Import {

    @Override
    public Node importNodes(String path) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

        File inputFile = new File(path);
        Document document = documentBuilder(inputFile);

        Node rootNode = document.getDocumentElement(); 
        NodeList nList = document.getDocumentElement().getChildNodes();

        for (int i = 0; i < nList.getLength(); i++) {
            Node childNode = nList.item(i);

            if (childNode.getNodeName().equals("node_list")) { //tratar lista de nos

                return childNode;
            }
        }
        return null;
    }
    
    
    
    //Criar o documento no formato DOM apartir do ficheiro dado como argumento
    private Document documentBuilder (File inputFile)
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
    public Node importSections(String path) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        File inputFile = new File(path);
        Document document = documentBuilder(inputFile);
        Node rootNode = document.getDocumentElement(); 
        NodeList nList = document.getDocumentElement().getChildNodes();

        for (int i = 0; i < nList.getLength(); i++) {
            Node childNode = nList.item(i);

            if (childNode.getNodeName().equals("node_list")) { //tratar lista de nos

                return childNode;
            }
        }
        return null;
    }
    
}
