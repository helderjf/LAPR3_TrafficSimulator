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
        m_regimeList = new ArrayList<>();
    }

    public Throttle(String id, ArrayList<Regime> regimeList) {
        m_id = id;
        m_regimeList = regimeList;
    }
    
    public Throttle(Throttle otherThrottle){
        m_id = otherThrottle.m_id;
        m_regimeList = new ArrayList();
        for(Regime r : otherThrottle.m_regimeList){
            m_regimeList.add(new Regime(r));
        }
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

    public void addRegime(Regime regime) {
        m_regimeList.add(regime);
    }
    
    
    
    
    
}
