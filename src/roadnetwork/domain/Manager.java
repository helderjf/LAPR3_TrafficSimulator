/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadnetwork.domain;

import data.access.layer.ProjectReader;
import data.access.layer.DataAccessObject;
import java.util.ArrayList;
import IO.ExportCSV;
import data.access.layer.ProjectWriter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author André Pedrosa, Hélder Faria, José Miranda, Rubén Rosário
 */
public class Manager {
    
    private String m_name;
    private Project m_currentProject;
    private DataAccessObject m_dataAccessObject;
    private ArrayList<BestPathAlgorithm> m_algorithmsList;
    private ProjectReader m_projectReader;
    private ProjectWriter m_projectWriter;
    
    
    
    public Manager(String name){
        m_name=name;
        m_dataAccessObject=new DataAccessObject("jdbc:oracle:thin:@localhost:1521:XE", "grupo60", "pass60");
        m_algorithmsList= new ArrayList<>();
        m_projectReader = new ProjectReader();
        m_projectWriter = new ProjectWriter(m_dataAccessObject);
        m_algorithmsList.add(new FastestPathAlgorithm());
        m_algorithmsList.add(new MostEfficientPath());
        
    }

    public String getM_name() {
        return m_name;
    }

    public Project getCurrentProject() {
        return m_currentProject;
    }

    /**
     * @return the m_dataAccessLayer
     */
    public DataAccessObject getdataAccessLayer() {
        return m_dataAccessObject;
    }
    
    public ArrayList<BestPathAlgorithm> getAlgorithmsList(){
        return m_algorithmsList;
    }
    
    public void addAlgorithm(BestPathAlgorithm alg){
        m_algorithmsList.add(alg);
    }

    public ExportCSV newCSV(String fileName) {
        
        return new ExportCSV(fileName);
    }

    public ProjectReader getProjectReader() {
        return m_projectReader;
    }

    public boolean setCurrentProject(Project project) {
        m_currentProject=project;
        return true;
    }

    public ProjectWriter getProjectWriter() {
        return m_projectWriter;
    }
    
    
    
}
