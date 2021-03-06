/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roadnetwork.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Andre
 */
public class HybridVehicleTest {
    
        Vehicle vehicle1;
    Vehicle vehicle2;
    Vehicle vehicle3;
    
    SectionTypology stype1;
    SectionTypology stype2;
    SectionTypology stype3;
    SectionTypology stype4;
    
    HashMap<SectionTypology, Double> velocityLimit1;
    HashMap<SectionTypology, Double> velocityLimit2;
    HashMap<SectionTypology, Double> velocityLimit3;
    
    HashMap<Integer, Double> gearList1;
    HashMap<Integer, Double> gearList2;
    
    ArrayList<Throttle> throttleList1;
    ArrayList<Throttle> throttleList2;
    
    Regime regime1;
    Regime regime2;
    Regime regime3;
    Regime regime4;
    
    ArrayList<Regime> lstregime1;
    ArrayList<Regime> lstregime2;
    ArrayList<Regime> lstregime3;
    
    Throttle throttle1;
    Throttle throttle2;
    Throttle throttle3;
    
    EngineEfficiency engineEfficiency1;
    EngineEfficiency engineEfficiency2;
    EngineEfficiency engineEfficiency3;
    
    ArrayList<EngineEfficiency> lstEngineEfficiency;
    
    public HybridVehicleTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        
        stype1 = SectionTypology.regular_road;
        stype2 = SectionTypology.highway;
        stype3 = SectionTypology.street;
        stype4 = SectionTypology.urban_street;
        
        velocityLimit1 = new HashMap<>();
        velocityLimit1.put(stype1, 90.0);
        velocityLimit1.put(stype2, 80.0);
        
        velocityLimit2 = null;
        
        velocityLimit3 = new HashMap<>();
        velocityLimit3.put(stype3, 100.0);
        velocityLimit3.put(stype4, 200.0);
        
        gearList1 = new HashMap<>();
        gearList1.put(1, 10.0);

        
        regime1 = new Regime(85, 1000, 2499, 0.0);
        regime2 = new Regime(95, 2500, 3999, 0.0);
        regime3 = new Regime(80, 4000, 5500, 0.0);
        regime4 = new Regime(135, 1000, 2499, 0.0);
        
        lstregime1 = new ArrayList();
        lstregime1.add(regime1);
        lstregime1.add(regime2);
        throttle1 = new Throttle("25", lstregime1);
        
        lstregime2 = new ArrayList();
        lstregime2.add(regime3);
        throttle2 = new Throttle("50", lstregime2);
        
        lstregime3 = new ArrayList();
        lstregime3.add(regime4);
        throttle3 = new Throttle("100", lstregime3);
        
        throttleList1 = new ArrayList();
        throttleList1.add(throttle1);
        throttleList1.add(throttle2);
        
        throttleList2 = new ArrayList();
        throttleList2.add(throttle3);
        
//        public ElectricVehicle(int m_pk, String name, String description, String type, String fuel, 
//                double mass, double load, double dragCoefficient, double frontalArea, double rrc, 
//                double wheelSize, HashMap<SectionTypology, Double> velocityLimit, double minRPM, double maxRPM, 
//                double finalDriveRatio, HashMap<Integer, Double> gearList, ArrayList<Throttle> throttleList, 
//                double energyRegenerationRatio) {

    

        vehicle1 = new HybridVehicle(1, //pk
                "dummy1", //name
                "descricao1", //descricao
                "car",//type
                "null",//fuel
                1400, //mass
                120,//load
                0.35,//drag
                1.8,//frontal area
                0.01,//rcc
                0.5,//wheel size
                velocityLimit1,//
                1000,//min rpm
                5500,//max rpm
                2.6,//final drive ratio
                gearList1, //gearList
                throttleList1, //throttleList
                0.97); //energyRegenerationRatio
        
        vehicle2 = new HybridVehicle(2, //pk
                "dummy2", //name
                "descricao2", //descricao
                "moto",//type
                "null",//fuel
                500, //mass
                60,//load
                0.15,//drag
                0.8,//frontal area
                0.02,//rcc
                0.3,//wheel size
                velocityLimit1,//
                900,//min rpm
                5800,//max rpm
                1.6,//final drive ratio
                gearList1, //gearList
                throttleList1, //throttleList
                0.87); //energyRegenerationRatio
        
        vehicle3 = new HybridVehicle("dummy3", //name
                "descricao3", //descricao
                "car",//type
                "null",//fuel
                1500, //mass
                90,//load
                0.18,//drag
                1.6,//frontal area
                0.01,//rcc
                0.9,//wheel size
                velocityLimit1,//
                1000,//min rpm
                7800,//max rpm
                1.8,//final drive ratio
                gearList1, //gearList
                throttleList1, //throttleList
                0.85); //energyRegenerationRatio
        
        
        engineEfficiency1 = new EngineEfficiency();
        engineEfficiency2 = new EngineEfficiency();
        engineEfficiency3 = new EngineEfficiency();

        
        engineEfficiency1.setGear(1);
        engineEfficiency1.setGearRatio(10.0);
        engineEfficiency1.setM_rpmHigh(2499.0);
        engineEfficiency1.setM_rpmLow(1000.0);
        engineEfficiency1.setM_sfc(0.0);
        engineEfficiency1.setThrottleRatio("25");
        engineEfficiency1.setTorque(85.0);
        
        engineEfficiency2.setGear(1);
        engineEfficiency2.setGearRatio(10.0);
        engineEfficiency2.setM_rpmHigh(3999.0);
        engineEfficiency2.setM_rpmLow(2500.0);
        engineEfficiency2.setM_sfc(0.0);
        engineEfficiency2.setThrottleRatio("25");
        engineEfficiency2.setTorque(95.0);

        
        engineEfficiency3.setGear(1);
        engineEfficiency3.setGearRatio(10.0);
        engineEfficiency3.setM_rpmHigh(5500.0);
        engineEfficiency3.setM_rpmLow(4000.0);
        engineEfficiency3.setM_sfc(0.0);
        engineEfficiency3.setThrottleRatio("50");
        engineEfficiency3.setTorque(80.0);
        
        
        lstEngineEfficiency = new ArrayList<>();
        lstEngineEfficiency.add(engineEfficiency1);
        lstEngineEfficiency.add(engineEfficiency2);
        lstEngineEfficiency.add(engineEfficiency3);
    }
    
    @After
    public void tearDown() {
    }


    /**
     * Test of getEngineEfficiency method, of class HybridVehicle.
     */
    @Test
    public void testGetEngineEfficiency() {
        System.out.println("getEngineEfficiency");
        HybridVehicle instance = (HybridVehicle) vehicle1;
        List<EngineEfficiency> expResult = lstEngineEfficiency;
        List<EngineEfficiency> result = instance.getEngineEfficiency();
        
        System.out.println(expResult);
        System.out.println(result);
        
        assertEquals(expResult, result);

    }

    /**
     * Test of getEnergyRegenerationRatio method, of class HybridVehicle.
     */
    @Test
    public void testGetEnergyRegenerationRatio() {
        System.out.println("getEnergyRegenerationRatio");
        HybridVehicle instance = (HybridVehicle) vehicle1;
        double expResult = 0.97;
        double result = instance.getEnergyRegenerationRatio();
        assertEquals(expResult, result, 0.0);

    }
    
}
