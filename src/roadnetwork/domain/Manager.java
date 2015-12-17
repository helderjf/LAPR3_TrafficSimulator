/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadnetwork.domain;

import java.util.ArrayList;

/**
 *
 * @author André Pedrosa, Hélder Faria, José Miranda, Rubén Rosário
 */
public class Manager {
    
    private String m_name;
    private Project m_currentProject;
    private DataAccessLayer m_dataAccessLayer;
    
    private ArrayList<Project> projectList; //TO DO TEMPORÁRIO / MOCK
    
    public ArrayList<Project> getProjectList(){
        return projectList;
    }
    
    
    public Manager(String name){
        m_name=name;
        m_dataAccessLayer=new DataAccessLayer();
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
    
    
    
}
