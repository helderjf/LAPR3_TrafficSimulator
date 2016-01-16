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
public class CombustionVehicleTest {
    
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
    
    public CombustionVehicleTest() {
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

        
        regime1 = new Regime(85, 1000, 2499, 8.2);
        regime2 = new Regime(95, 2500, 3999, 6.2);
        regime3 = new Regime(80, 4000, 5500, 10.2);
        regime4 = new Regime(135, 1000, 2499, 5.2);
        
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

        vehicle1 = new CombustionVehicle("dummy1", //name
                "descricao", //descricao
                "car",//type
                "gasoline",//fuel
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
                gearList1,
                throttleList1);
        
        vehicle2 = new CombustionVehicle("dummy2", //name
                "descricao2", //descricao
                "truck",//type
                "diesel",//fuel
                1200, //mass
                110,//load
                0.25,//drag
                1.9,//frontal area
                0.02,//rcc
                0.6,//wheel size
                velocityLimit1,//
                1200,//min rpm
                5900,//max rpm
                4.6,//final drive ratio
                gearList1,
                throttleList1);
        
        vehicle3 = new CombustionVehicle(1, //pk
                "dummy3", //name
                "descricao3", //descricao
                "moto",//type
                "gasoline",//fuel
                600, //mass
                70,//load
                0.12,//drag
                0.5,//frontal area
                0.01,//rcc
                0.3,//wheel size
                velocityLimit1,//
                900,//min rpm
                6900,//max rpm
                2.6,//final drive ratio
                gearList1,
                throttleList1);
        
        engineEfficiency1 = new EngineEfficiency();
        engineEfficiency2 = new EngineEfficiency();
        engineEfficiency3 = new EngineEfficiency();

        
        engineEfficiency1.setGear(1);
        engineEfficiency1.setGearRatio(10.0);
        engineEfficiency1.setM_rpmHigh(2499.0);
        engineEfficiency1.setM_rpmLow(1000.0);
        engineEfficiency1.setM_sfc(8.2);
        engineEfficiency1.setThrottleRatio("25");
        engineEfficiency1.setTorque(85.0);
        
        engineEfficiency2.setGear(1);
        engineEfficiency2.setGearRatio(10.0);
        engineEfficiency2.setM_rpmHigh(3999.0);
        engineEfficiency2.setM_rpmLow(2500.0);
        engineEfficiency2.setM_sfc(6.2);
        engineEfficiency2.setThrottleRatio("25");
        engineEfficiency2.setTorque(95.0);

        
        engineEfficiency3.setGear(1);
        engineEfficiency3.setGearRatio(10.0);
        engineEfficiency3.setM_rpmHigh(5500.0);
        engineEfficiency3.setM_rpmLow(4000.0);
        engineEfficiency3.setM_sfc(10.2);
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
     * Test of equals method, of class CombustionVehicle.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = vehicle1;
        CombustionVehicle instance = (CombustionVehicle) vehicle2;
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);

    }

    /**
     * Test of getEngineEfficiency method, of class CombustionVehicle.
     */
    @Test
    public void testGetEngineEfficiency() {
        System.out.println("getEngineEfficiency");
        CombustionVehicle instance = (CombustionVehicle) vehicle1;
        List<EngineEfficiency> expResult = lstEngineEfficiency;
        List<EngineEfficiency> result = instance.getEngineEfficiency();
        assertEquals(expResult, result);

    }
    
}
