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
public class Throttle {
    
    private String m_id;
    private ArrayList<Regime> m_regimeList;

    public Throttle() {
    }

    public Throttle(String id, ArrayList<Regime> regimeList) {
        m_id = id;
        m_regimeList = regimeList;
    }
    

    public String getID() {
        return m_id;
    }

    public ArrayList<Regime> getRegimeList() {
        return m_regimeList;
    }


    public void setID(String id) {
        m_id = id;
    }

    public void setRegimeList(ArrayList<Regime> regimeList) {
        m_regimeList = regimeList;
    }
    
    
    
    
    
}
