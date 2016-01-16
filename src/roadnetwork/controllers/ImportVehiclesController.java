/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadnetwork.controllers;

import IO.ImportXML;
import java.io.File;
import java.util.ArrayList;
import roadnetwork.domain.Manager;
import roadnetwork.domain.Vehicle;

/**
 *
 * @author André Pedrosa, Hélder Faria, José Miranda, Rubén Rosário
 */
public class ImportVehiclesController {

    private Manager m_manager;

    public ImportVehiclesController(Manager manager) {
        m_manager = manager;
    }

    public boolean importVehicles(File file) {
        ImportXML importXML = new ImportXML(file);

        ArrayList<Vehicle> vehicleList = importXML.importVehicles();

        return m_manager.getCurrentProject().createVehicleList(vehicleList);
    }

}
