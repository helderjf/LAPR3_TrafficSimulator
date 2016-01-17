/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadnetwork.controllers;

import java.util.ArrayList;
import roadnetwork.domain.Manager;
import roadnetwork.domain.Project;
import roadnetwork.domain.Vehicle;

/**
 *
 * @author josemiranda
 */
public class ShowVehiclesController {
    
    Manager m_manager;
    Project m_project;
    
    public ShowVehiclesController(Manager manager){
        m_manager=manager;
    }
    
    public boolean projectActive() {
        m_project = m_manager.getCurrentProject();
        if (m_project != null) {
            return true;
        }
        return false;
    } 
    
    public ArrayList<Vehicle> getVehiclesList(){
        return m_project.getVehicleList();
    }
    
    
}
