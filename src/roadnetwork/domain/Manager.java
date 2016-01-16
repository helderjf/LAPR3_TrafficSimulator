/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadnetwork.domain;

import data.access.layer.ProjectReader;
import data.access.layer.DataAccessObject;
import IO.ExportCSV;
import IO.ExportHTML;
import data.access.layer.ProjectWriter;
import java.util.ArrayList;
import roadnetwork.factory.ProjectStateFactory;

/**
 *
 * @author André Pedrosa, Hélder Faria, José Miranda, Rubén Rosário
 */
public class Manager {
    
    private String m_name;
    private Project m_currentProject;
    private DataAccessObject m_dataAccessObject;
    private ArrayList<BestPathAlgorithm> m_algorithmsList;
    //private ProjectReader m_projectReader;
    //private ProjectWriter m_projectWriter;
    
    
    /**
     * 
     * @param name name
     */
    public Manager(String name){
        m_name=name;
        m_dataAccessObject=new DataAccessObject("jdbc:oracle:thin:@//gandalf.dei.isep.ipp.pt:1521/pdborcl", "LAPR3_60", "lapr32015g60");
        m_algorithmsList= new ArrayList<>();
        //m_projectReader = new ProjectReader();
        m_algorithmsList.add(new FastestPathAlgorithm());
        m_algorithmsList.add(new TheoreticalMostEfficientPath());
        m_algorithmsList.add(new MostEfficientPathRealConditions());
        
    }

    /**
     * 
     * @return name
     */
    public String getM_name() {
        return m_name;
    }

    /**
     * 
     * @return CurrentProject
     */
    public Project getCurrentProject() {
        return m_currentProject;
    }

    /**
     * @return the dataAccessLayer
     */
    public DataAccessObject getdataAccessLayer() {
        return m_dataAccessObject;
    }
    
    /**
     * 
     * @return AlgorithmsList
     */
    public ArrayList<BestPathAlgorithm> getAlgorithmsList(){
        return m_algorithmsList;
    }
    
    /**
     * 
     * @param alg BestPathAlgorithm
     */
    public void addAlgorithm(BestPathAlgorithm alg){
        m_algorithmsList.add(alg);
    }

    /**
     * 
     * @param fileName filename
     * @return new ExportCSV
     */
    public ExportCSV newCSV(String fileName) {
        
        return new ExportCSV(fileName);
    }

    /**
     * 
     * @return ProjectReader
     */
    public ProjectReader getProjectReader() {
        return new ProjectReader(m_dataAccessObject, new ProjectStateFactory());
    }

    /**
     * 
     * @param project project
     * @return operation result
     */
    public boolean setCurrentProject(Project project) {
        m_currentProject=project;
        return true;
    }

    /**
     * 
     * @return ProjectWriter
     */
    public ProjectWriter getProjectWriter() {
        return new ProjectWriter(m_dataAccessObject);

    }
    
    /**
     * 
     * @param fileName filename
     * @return ExportHTML
     */
    public ExportHTML newHTML(String fileName){
        return new ExportHTML(fileName);
    }
    
    
    
}
