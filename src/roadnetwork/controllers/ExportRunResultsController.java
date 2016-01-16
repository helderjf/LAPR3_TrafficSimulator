/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadnetwork.controllers;

import IO.ExportHTML;
import data.access.layer.ProjectReader;
import java.util.ArrayList;
import java.util.HashMap;
import roadnetwork.domain.ImportedResult;
import roadnetwork.domain.Manager;
import roadnetwork.domain.Project;
import roadnetwork.domain.Simulation;
import roadnetwork.domain.SimulationRun;
import roadnetwork.domain.TrafficPattern;

/**
 *
 * @author josemiranda
 */
public class ExportRunResultsController {
    private Manager m_manager;
    private Project m_project;
    private Simulation m_simulation;
    private ProjectReader m_projectReader;
    private ArrayList<String> m_simulationRuns;
    private HashMap<String,Integer> m_runsMap;
    
    
    public ExportRunResultsController(Manager manager){
        m_manager=manager;
    }
    
    public int canExport(){
        if (m_manager.getCurrentProject()==null) {
            return -1;
        }
        m_project=m_manager.getCurrentProject();
        if (m_project.getCurrentSimulation()==null) {
            return -2;
        }
        m_simulation=m_project.getCurrentSimulation();
        m_projectReader=m_manager.getProjectReader();
        if(!m_projectReader.simulationExists(m_project.getPK(), m_simulation.getName())){
            return -3;
        }
        if (!m_projectReader.simulationHasRuns(m_simulation.getPK())) {
            return -4;
        }

        
        return 1;
    }
    
    
    
    public ArrayList<String> getSimulationRuns(){
                m_runsMap=m_projectReader.getSimulationRunsOrderedList(m_simulation.getPK());
        
       for(String rname : m_runsMap.keySet()){
           m_simulationRuns.add(rname);
       }
       return m_simulationRuns;
    }
    
    
    
    
    public ArrayList<ImportedResult> getImportedResultOptions(){
        return m_manager.createImportedResults();
    }
    
    public ArrayList<TrafficPattern> getTrafficPatternsList(){
        return m_simulation.getTrafficPatternList();
    }
    
    public boolean exportResults(String fileName, String run, ImportedResult impResult){
        //ToDo - construir impResult de acordo com a run recebida
        
        ExportHTML html =m_manager.newHTML(fileName);
        
        
        return html.exportDetailedResults(impResult);
    }
    
    public boolean exportResults(String fileName, String run, ImportedResult impResult, String tpattern){
        //ToDo - construir impResult de acordo com a run e tpattern recebidos
        
        ExportHTML html =m_manager.newHTML(fileName);
        return html.exportDetailedResults(impResult);
    }
}
