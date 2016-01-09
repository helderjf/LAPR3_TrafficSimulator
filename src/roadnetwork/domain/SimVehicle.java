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
public class SimVehicle {

    private Vehicle m_vehicle;
    private Junction m_originNode;
    private Junction m_destinyNode;
    private ArrayList<SimPathParcel> m_path;
    private SimPathParcel m_currentPos;
    private SimPathParcel m_nextPos;

    boolean willEndAt(double currentTime) {
        return m_nextPos != null
                && (m_currentPos.getSimInTime() + m_currentPos.getTheoreticalTravelTime() <= currentTime);
    }

    void endSimulation(double currentTime) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
