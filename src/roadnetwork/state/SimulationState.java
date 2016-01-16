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
public interface SimulationState {

    public boolean canRunSimulation();
    
    public boolean xmlImported();

    public boolean canEditProperties();

    public boolean propertiesChanged();

    public boolean canCopySimulation();
    
}
