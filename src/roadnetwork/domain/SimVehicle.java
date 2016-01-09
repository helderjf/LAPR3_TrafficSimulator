/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadnetwork.domain;

import java.util.ArrayList;
import java.util.Comparator;

/**
 *
 * @author André Pedrosa, Hélder Faria, José Miranda, Rubén Rosário
 */
public class SimVehicle {

    private Vehicle m_vehicle;
    private Junction m_originNode;
    private Junction m_destinyNode;
    private ArrayList<SimPathParcel> m_path;
    private SimPathParcel m_currentPos;
    private SimPathParcel m_nextPos;
    private boolean ended;
    
    

    boolean willEndAt(double currentTime) {
        return m_nextPos != null
                && (m_currentPos.getSimInTime() + m_currentPos.getTheoreticalTravelTime() <= currentTime);
    }

    boolean endSimulation(double currentTime) {
        
        m_currentPos.setSimExitTime(currentTime);
        m_currentPos=null;
        ended = true;
        
        return true;
    }


}
