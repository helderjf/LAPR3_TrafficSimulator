/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadnetwork.domain;

import java.util.ArrayList;
import java.util.Queue;

/**
 *
 * @author André Pedrosa, Hélder Faria, José Miranda, Rubén Rosário
 */
public class SimSegment {
    
    private Section m_section;
    private Segment m_segment;
    private SectionDirection m_direction;
    private Queue<SimVehicle> m_vehicleQueue;
    private ArrayList<TrafficRecord> m_log;
}
