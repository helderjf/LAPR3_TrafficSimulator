/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadnetwork.domain;


import java.util.ArrayList;
import javafx.util.Duration;

/**
 *
 * @author josemiranda
 */
public class ResultSimulation implements Result {

    private String m_runName;
    private double m_duration;
    private double m_timeStep;
    private BestPathAlgorithm m_bestPathMethod;
    private ArrayList<SimVehicle> m_endedVehicles;
    private ArrayList<SimVehicle> m_cruisingVehicles;
    private ArrayList<SimVehicle> m_droppedVehicles;

    public ResultSimulation(String runName, double duration, double timeStep, BestPathAlgorithm bpm, ArrayList<SimVehicle> endedVehicles, ArrayList<SimVehicle> cruisingVehicles, ArrayList<SimVehicle> droppedVehicles) {
        m_runName = runName;
        m_duration = duration;
        m_timeStep = timeStep;
        m_endedVehicles = endedVehicles;
        m_cruisingVehicles = cruisingVehicles;
        m_droppedVehicles = droppedVehicles;
        m_bestPathMethod=bpm;
    }

    @Override
    public String printResults() {
        int durationHours = (int) (m_duration / 3600);
        int durationMinutes = (int) ((m_duration - durationHours * 3600) / 60);
        int durationSeconds = (int) (m_duration - durationHours * 3600 - durationMinutes * 60);

        int timeStepHours = (int) (m_timeStep / 3600);
        int timeStepMinutes = (int) ((m_timeStep - timeStepHours * 3600) / 60);
        int timeStepSeconds = (int) (m_timeStep - timeStepHours * 3600 - timeStepMinutes * 60);
        
        int createdV = m_cruisingVehicles.size()+m_endedVehicles.size()+m_droppedVehicles.size();
        int injectedV = m_cruisingVehicles.size()+m_endedVehicles.size();
        
        
        String results = "-------  " + m_runName + "  -------\n"
                + "\n"
                + "Duration: "+durationHours+" hours "+durationMinutes+" minutes "+durationSeconds+" seconds\n"
                + "Time Step: "+timeStepHours+" hours "+timeStepMinutes+" minutes "+timeStepSeconds+" seconds\n"
                + "Best path method: "+m_bestPathMethod.toString()+"\n"
                + "Created Vehicles: "+createdV+"\n"
                + "Injected Vehicles: "+injectedV+"\n"
                + "Ended Vehicles: "+m_endedVehicles.size()+"\n"
                + "Cruising Vehicles: "+m_cruisingVehicles.size()+"\n"
                + "Dropped Vehicles: "+m_droppedVehicles.size()+"\n";

        return results;
    }

    public ArrayList<SimVehicle> getDroppedVehicles() {
        return m_droppedVehicles;
    }

    public ArrayList<SimVehicle> getEndedVehicles() {
        return m_endedVehicles;
    }

    public ArrayList<SimVehicle> getCruisingVehicles() {
        return m_cruisingVehicles;
    }
    
    
    @Override
    public String getGlobalResultsHTMLCode() {
        int durationHours = (int) (m_duration / 3600);
        int durationMinutes = (int) ((m_duration - durationHours * 3600) / 60);
        int durationSeconds = (int) (m_duration - durationHours * 3600 - durationMinutes * 60);

        int timeStepHours = (int) (m_timeStep / 3600);
        int timeStepMinutes = (int) ((m_timeStep - timeStepHours * 3600) / 60);
        int timeStepSeconds = (int) (m_timeStep - timeStepHours * 3600 - timeStepMinutes * 60);
        
        int createdV = m_cruisingVehicles.size()+m_endedVehicles.size()+m_droppedVehicles.size();
        int injectedV = m_cruisingVehicles.size()+m_endedVehicles.size();
        
        String results=
                "<b>-------  " + m_runName + "  -------</b>"+
                "<p>Duration: "+durationHours+" hours "+durationMinutes+" minutes "+durationSeconds+" seconds</p>"
                + "<p>Time Step: "+timeStepHours+" hours "+timeStepMinutes+" minutes "+timeStepSeconds+" seconds</p>"
                + "<p>Best path method: "+m_bestPathMethod.toString()+"</p>"
                + "<p>Created Vehicles: "+createdV+"</p>"
                + "<p>Injected Vehicles: "+injectedV+"</p>"
                + "<p>Ended Vehicles: "+m_endedVehicles.size()+"</p>"
                + "<p>Cruising Vehicles: "+m_cruisingVehicles.size()+"</p>"
                + "<p>Dropped Vehicles: "+m_droppedVehicles.size()+"</p>";
                
        return results;
    }

}
