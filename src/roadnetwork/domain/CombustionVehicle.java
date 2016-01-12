/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadnetwork.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author André Pedrosa, Hélder Faria, José Miranda, Rubén Rosário
 */
public class CombustionVehicle extends Vehicle implements Combustion {



    public CombustionVehicle() {
        super();
    }

    public CombustionVehicle(int m_pk, String name, String description, String type, String fuel, double mass, double load, double dragCoefficient, double frontalArea, double rrc, double wheelSize, HashMap<SectionTypology, Double> velocityLimit, double minRPM, double maxRPM, double finalDriveRatio, HashMap<Integer, Double> gearList, ArrayList<Throttle> throttleList) {
        super(m_pk, name, description, type, fuel, mass, load, dragCoefficient, frontalArea, rrc, wheelSize, velocityLimit, minRPM, maxRPM, finalDriveRatio, gearList, throttleList);
    }

    public CombustionVehicle(String name, String description, String type, String fuel, double mass, double load, double dragCoefficient, double frontalArea, double rrc, double wheelSize, HashMap<SectionTypology, Double> velocityLimit, double minRPM, double maxRPM, double finalDriveRatio, HashMap<Integer, Double> gearList, ArrayList<Throttle> throttleList) {
        super(name, description, type, fuel, mass, load, dragCoefficient, frontalArea, rrc, wheelSize, velocityLimit, minRPM, maxRPM, finalDriveRatio, gearList, throttleList);
    }
    


    /**
     *
     * @param obj object
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CombustionVehicle other = (CombustionVehicle) obj;
        if (!Objects.equals(this.fuel, other.fuel)) {
            return false;
        }
        if (!Objects.equals(this.gearList, other.gearList)) {
            return false;
        }
        return true;
    }

    @Override
    public List<EngineEfficiency> getEngineEfficiency() {
        
        //EngineEfficiency engineEfficiency = new EngineEfficiency();
        ArrayList<EngineEfficiency> engineEfficiencyList = new ArrayList<>();
        
        
        //preenche a lista com todos as performances
        for (int key_idGear : gearList.keySet()) {  
        
            for(Throttle throttle : throttleList)
            {
                for(Regime regime : throttle.getRegimeList())
                {
                    EngineEfficiency engineEfficiency = new EngineEfficiency();
                    engineEfficiency.setGear(key_idGear);
                    engineEfficiency.setGearRatio(gearList.get(key_idGear));
                    engineEfficiency.setThrottleRatio(throttle.getID());
                    engineEfficiency.setTorque(regime.getTorque());
                    engineEfficiency.setM_sfc(regime.getSfc());
                    engineEfficiency.setM_rpmLow(regime.getRPMLow());
                    engineEfficiency.setM_rpmHigh(regime.getRPMHigh());
                      
                    engineEfficiency.setResult(gearList.get(key_idGear)*regime.getTorque());
                    engineEfficiencyList.add(engineEfficiency);
                }           
            }     
        }
        
        //ordena a lista em ordem crescente por performance
        Collections.sort(engineEfficiencyList, new Comparator<EngineEfficiency>() 
        {
             @Override
             public int compare(EngineEfficiency lhs, EngineEfficiency rhs) {

               return Double.valueOf(lhs.getResult()).compareTo(rhs.getResult());
              }
         });

        
        return engineEfficiencyList;
    }

}
