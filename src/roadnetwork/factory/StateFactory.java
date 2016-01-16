/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadnetwork.factory;

import roadnetwork.domain.Project;
import roadnetwork.domain.Simulation;
import roadnetwork.state.ProjectState;
import roadnetwork.state.ProjectStateCreated;
import roadnetwork.state.ProjectStateEmpty;
import roadnetwork.state.ProjectStateEmptySaved;
import roadnetwork.state.ProjectStateRoadNetworkAssigned;
import roadnetwork.state.ProjectStateRoadNetworkAssignedSaved;
import roadnetwork.state.ProjectStateSimulationReady;
import roadnetwork.state.ProjectStateSimulationReadySaved;
import roadnetwork.state.ProjectStateVehiclesAssigned;
import roadnetwork.state.ProjectStateVehiclesAssignedSaved;
import roadnetwork.state.SimulationState;
import roadnetwork.state.SimulationStateActive;
import roadnetwork.state.SimulationStateCreated;

/**
 *
 * @author André Pedrosa, Hélder Faria, José Miranda, Rubén Rosário
 */
public class StateFactory {
    
    
    public static ProjectState getProjectState(String state,Project project){
        
        switch (state){
            case "ProjectStateCreated": return new ProjectStateCreated(project);
            case "ProjectStateEmpty": return new ProjectStateEmpty(project);
            case "ProjectStateEmptySaved": return new ProjectStateEmptySaved(project);
            case "ProjectStateRoadNetworkAssigned": return new ProjectStateRoadNetworkAssigned(project);
            case "ProjectStateRoadNetworkAssignedSaved": return new ProjectStateRoadNetworkAssignedSaved(project);
            case "ProjectStateVehiclesAssigned": return new ProjectStateVehiclesAssigned(project);
            case "ProjectStateVehiclesAssignedSaved": return new ProjectStateVehiclesAssignedSaved(project);
            case "ProjectStateSimulationReady": return new ProjectStateSimulationReady(project);
            case "ProjectStateSimulationReadySaved": return new ProjectStateSimulationReadySaved(project);
                
            default: return null;
                
                
        }
    }
    
    
        public static SimulationState getSimulationState(String state,Simulation simulation){
        
        switch (state){
            case "SimulationStateActive": return new SimulationStateActive(simulation);
            case "SimulationStateCreated": return new SimulationStateCreated(simulation);
 
                
            default: return null;
                
                
        }
    }
    
    
}
