/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadnetwork.domain;
import IO.*;
import org.w3c.dom.*;
/**
 *
 * @author André Pedrosa, Hélder Faria, José Miranda, Rubén Rosário
 */
public class Main {
    
    public static void main(String[] args){
        Import i = new ImportXML();
        Node n = i.importNodes("TestSet01_Network.xml");
        for (int j = 0; j < n.getChildNodes().getLength(); j++) {
            
            System.out.println(n.getChildNodes().item(j).getNodeName());
        }
    }
    
    
    
}
