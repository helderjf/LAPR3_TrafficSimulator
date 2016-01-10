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
    private double m_injectionTime;
    private boolean ended;

    public SimVehicle(Vehicle vehicle, Junction originNode, Junction destinyNode, ArrayList<SimPathParcel> path, double injectionTime) {
        m_vehicle = vehicle;
        m_originNode = originNode;
        m_destinyNode = destinyNode;
        m_path = path;
        m_injectionTime = injectionTime;
        m_currentPos=null;
        m_nextPos=null;
        ended = false;
    }
    
    

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
    
    double getWaitingTime(double currentTime){
        return (currentTime-m_currentPos.getSimInTime()-m_currentPos.getTheoreticalTravelTime());
    }
    
    public double getArrivalTime(){
        return (m_currentPos.getSimInTime()+m_currentPos.getTheoreticalTravelTime());//TODO verificar se é mesmo isto (em princípio nao)
    }

    SimPathParcel getNextPos() {
        return m_nextPos;
    }

    void crossToNextPos(double currentTime) {
        m_currentPos.setSimExitTime(currentTime);
        m_nextPos.setSimInTime(currentTime);
        
        
        m_currentPos = m_nextPos;
        if(m_path.indexOf(m_nextPos)==m_path.size()){
            m_nextPos=null;
        }else{
            m_nextPos=m_path.get(m_path.indexOf(m_nextPos)+1);
        }
        
    }

    public void getStepInjectTime(double stepInjectTime) {
        m_injectionTime = stepInjectTime;
    }

    public double getInjectionTime() {
        return m_injectionTime;
    }

    
    
    
    

}
