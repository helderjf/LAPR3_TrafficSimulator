/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadnetwork.domain;

import roadnetwork.gui.JanelaPrincipal;

/**
 *
 * @author André Pedrosa, Hélder Faria, José Miranda, Rubén Rosário
 */
public class Main {
    
    public static void main(String[] args){
        
                try {
            Manager manager = new Manager("Road Network Simulation");

            JanelaPrincipal janela=new JanelaPrincipal(manager);
            
            
            //CREATE MOCK OBJECTS
            Project projecto1 = new Project();
            RoadNetwork roadNetwork1 = new RoadNetwork();
            
            Junction node1 = new Junction();
            Junction node2 = new Junction();
            //...
            Road road1 = new Road();
            //...
            
            Section section1 = new Section();
            //...
            
            Segment segment1 = new Segment();
            //...
            
           // Vehicle vehicle1 = new Vehicle("v1", "vehiculo1", 1000, new TypeOfVehicle(), 0, 0.03,150);
            //...
            


        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
    }
    
    
}
