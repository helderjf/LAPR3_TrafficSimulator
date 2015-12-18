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
    
    public boolean canSimulate();
    
    public boolean vadidate();        
}
    