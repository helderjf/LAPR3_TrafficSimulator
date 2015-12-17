/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadnetwork.domain;

import IO.ExportCSV;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author André Pedrosa, Hélder Faria, José Miranda, Rubén Rosário
 */
public class Manager {
    
    private String m_name;
    private Project m_currentProject;
    private DataAccessLayer m_dataAccessLayer;
    private List<BestPathAlgorithm> m_algorithmsList;
    
    public Manager(String name){
        m_name=name;
        m_dataAccessLayer=new DataAccessLayer();
        m_algorithmsList= new ArrayList<>();
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
    public DataAccessLayer getdataAccessLayer() {
        return m_dataAccessLayer;
    }
    
    public List<BestPathAlgorithm> getAlgorithmsList(){
        return m_algorithmsList;
    }
    
    public void addAlgorithm(BestPathAlgorithm alg){
        m_algorithmsList.add(alg);
    }

    public ExportCSV newCSV(String fileName) {
        return new ExportCSV();
    }
}
