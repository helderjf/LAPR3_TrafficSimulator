/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadnetwork.domain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author André Pedrosa, Hélder Faria, José Miranda, Rubén Rosário
 */
public class DataAccessLayer {

    private String m_dbUrl;
    private String m_user;
    private String m_pass;
    private Connection m_connection;
    private Statement m_statement;
    private ResultSet m_output;

    public DataAccessLayer(String dbUrl, String user, String pass) {
        m_dbUrl = dbUrl;
        m_user = user;
        m_pass = pass;

    }

    public boolean connect() {

        try {
            m_connection = DriverManager.getConnection(m_dbUrl, m_user, m_pass);
            System.out.println("Connection to database successfull");
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(DataAccessLayer.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }
    
    
    
    
    

    public ArrayList<String> getProjectsIDList() {
        ArrayList<String> pidl = new ArrayList<>();
        //TODO call procedure

        //dummy
        String p1 = "project 1 - Porto Centro";
        String p2 = "project 2 - Porto Norte";
        String p3 = "project 2 - Porto Este";
        pidl.add(p1);
        pidl.add(p2);
        pidl.add(p3);
        return pidl;
    }

    public boolean makeProjectActive() {
        //TODO load project

        return true;
    }

}
