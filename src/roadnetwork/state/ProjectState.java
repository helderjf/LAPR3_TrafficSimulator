/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadnetwork.state;

/**
 *
 * @author André Pedrosa, Hélder Faria, José Miranda, Rubén Rosário
 */
public interface ProjectState {
    
    public boolean canImportRoadNetwork();
    
    public boolean canImportVehicles();
    
    public boolean canSaveProject();
    
    public boolean canCopyProject();
    
    public boolean canEditProperties();
    
    public boolean canAnalyse();
    
    public boolean vadidate();        

    public boolean projectCreated();

    public boolean propertiesChanged();

    public boolean isSaved();

    public boolean projectSaved();

    public boolean hasRoadNetwork();

    public boolean hasVehicles();

    public boolean canSimulate();
    
    public boolean roadNetworkAssigned();
    
    public boolean vehiclesAssigned();

    public boolean simulationCreated();

    public boolean simulationPropertiesChanged();

    public boolean canOpenSimulation();

}
    