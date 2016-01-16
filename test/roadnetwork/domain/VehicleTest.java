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
public class VehicleTest {
    
    Vehicle vehicle1;
    Vehicle vehicle2;
    Vehicle vehicle3;
    Vehicle vehicle4;
    
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
    
    public VehicleTest() {
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
        velocityLimit1.put(stype1, 90.00);
        velocityLimit1.put(stype2, 80.00);
        
        velocityLimit2 = null;
        
        velocityLimit3 = new HashMap<>();
        velocityLimit3.put(stype3, 100.00);
        velocityLimit3.put(stype4, 200.00);
        
        gearList1 = new HashMap<>();
        gearList1.put(1, 10.00);
        gearList1.put(2, 20.00);
        gearList1.put(3, 30.00);
        gearList1.put(4, 40.00);
        
        gearList2 = new HashMap<>();
        gearList2.put(1, 10.00);

        
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
        
        vehicle4 = new CombustionVehicle("dummy4", //name
                "descricao2", //descricao
                "truck",//type
                "diesel",//fuel
                1100, //mass
                100,//load
                0.21,//drag
                1.1,//frontal area
                0.02,//rcc
                0.1,//wheel size
                velocityLimit1,//
                1200,//min rpm
                5900,//max rpm
                4.6,//final drive ratio
                gearList2,
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
     * Test of getName method, of class Vehicle.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        Vehicle instance = vehicle1;
        String expResult = "dummy1";
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of setName method, of class Vehicle.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String name = "teste1";
        Vehicle instance = vehicle1;
        instance.setName(name);
        String expResult = "teste1";
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getMass method, of class Vehicle.
     */
    @Test
    public void testGetMass() {
        System.out.println("getMass");
        Vehicle instance = vehicle1;
        double expResult = 1400;
        double result = instance.getMass();
        assertEquals(expResult, result, 0);
    }

    /**
     * Test of setMass method, of class Vehicle.
     */
    @Test
    public void testSetMass() {
        System.out.println("setMass");
        double mass = 1000;
        Vehicle instance = vehicle1;
        instance.setMass(mass);
        double expResult = 1000;
        double result = instance.getMass();
        assertEquals(expResult, result, 0);
    }

    /**
     * Test of getType method, of class Vehicle.
     */
    @Test
    public void testGetType() {
        System.out.println("getType");
        Vehicle instance = vehicle1;
        String expResult = "car";
        String result = instance.getType();
        assertEquals(expResult, result);
    }

    /**
     * Test of getLoad method, of class Vehicle.
     */
    @Test
    public void testGetLoad() {
        System.out.println("getLoad");
        Vehicle instance = vehicle1;
        double expResult = 120;
        double result = instance.getLoad();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setLoad method, of class Vehicle.
     */
    @Test
    public void testSetLoad() {
        System.out.println("setLoad");
        double load = 100;
        Vehicle instance = vehicle1;
        instance.setLoad(load);
        double expResult = 100;
        double result = instance.getLoad();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getDragCoefficient method, of class Vehicle.
     */
    @Test
    public void testGetDragCoefficient() {
        System.out.println("getDragCoefficient");
        Vehicle instance = vehicle1;
        double expResult = 0.35;
        double result = instance.getDragCoefficient();
        assertEquals(expResult, result, 0.0); 
    }

    /**
     * Test of setDragCoefficient method, of class Vehicle.
     */
    @Test
    public void testSetDragCoefficient() {
        System.out.println("setDragCoefficient");
        double drag_Coefficient = 0.2;
        Vehicle instance = vehicle1;
        instance.setDragCoefficient(drag_Coefficient);
        double expResult = 0.2;
        double result = instance.getDragCoefficient();
        assertEquals(expResult, result, 0.0); 
    }

    /**
     * Test of getRcc method, of class Vehicle.
     */
    @Test
    public void testGetRcc() {
        System.out.println("getRcc");
        Vehicle instance = vehicle1;
        double expResult = 0.01;
        double result = instance.getRrc();
        assertEquals(expResult, result, 0.0);

    }

    /**
     * Test of setRcc method, of class Vehicle.
     */
    @Test
    public void testSetRcc() {
        System.out.println("setRcc");
        double rrc = 0.3;
        Vehicle instance = vehicle1;
        instance.setRcc(rrc);
        double expResult = 0.3;
        double result = instance.getRrc();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getWheelSize method, of class Vehicle.
     */
    @Test
    public void testGetWheelSize() {
        System.out.println("getWheelSize");
        Vehicle instance = vehicle1;
        double expResult = 0.5;
        double result = instance.getWheelSize();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setWheelSize method, of class Vehicle.
     */
    @Test
    public void testSetWheelSize() {
        System.out.println("setWheelSize");
        double wheelSize = 0.2;
        Vehicle instance = vehicle1;
        instance.setWheelSize(wheelSize);
        double expResult = 0.2;
        double result = instance.getWheelSize();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getVelocityLimits method, of class Vehicle.
     */
    @Test
    public void testGetVelocityLimits() {
        System.out.println("getVelocityLimits");
        Vehicle instance = vehicle1;
        HashMap<SectionTypology, Double> expResult = velocityLimit1;
        HashMap<SectionTypology, Double> result = instance.getVelocityLimits();
        assertEquals(expResult, result);
    }

    /**
     * Test of setVelocityLimits method, of class Vehicle.
     */
    @Test
    public void testSetVelocityLimits() {
        System.out.println("setVelocityLimits");
        HashMap<SectionTypology, Double> velocityLimit = velocityLimit1;
        Vehicle instance = vehicle1;
        instance.setVelocityLimits(velocityLimit);
        HashMap<SectionTypology, Double> expResult = velocityLimit1;
        HashMap<SectionTypology, Double> result = instance.getVelocityLimits();
        assertEquals(expResult, result);
    }

    /**
     * Test of getVelocityLimit method, of class Vehicle.
     */
    @Test
    public void testGetVelocityLimit_SectionTypology() {
        System.out.println("getVelocityLimit");
        SectionTypology typology = stype1;
        Vehicle instance = vehicle1;
        double expResult = 90.00;
        double result = instance.getVelocityLimit(typology);
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getMinRPM method, of class Vehicle.
     */
    @Test
    public void testGetMinRPM() {
        System.out.println("getMinRPM");
        Vehicle instance = vehicle1;
        double expResult = 1000;
        double result = instance.getMinRPM();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setMinRPM method, of class Vehicle.
     */
    @Test
    public void testSetMinRPM() {
        System.out.println("setMinRPM");
        double minRPM = 1200;
        Vehicle instance = vehicle1;
        instance.setMinRPM(minRPM);
        double expResult = 1200;
        double result = instance.getMinRPM();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getMaxRPM method, of class Vehicle.
     */
    @Test
    public void testGetMaxRPM() {
        System.out.println("getMaxRPM");
        Vehicle instance = vehicle1;
        double expResult = 5500;
        double result = instance.getMaxRPM();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setMaxRPM method, of class Vehicle.
     */
    @Test
    public void testSetMaxRPM() {
        System.out.println("setMaxRPM");
        double maxRPM = 9000;
        Vehicle instance = vehicle1;
        instance.setMaxRPM(maxRPM);
        double expResult = 9000;
        double result = instance.getMaxRPM();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getFinalDriveRatio method, of class Vehicle.
     */
    @Test
    public void testGetFinalDriveRatio() {
        System.out.println("getFinalDriveRatio");
        Vehicle instance = vehicle1;
        double expResult = 2.6;
        double result = instance.getFinalDriveRatio();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setFinalDriveRatio method, of class Vehicle.
     */
    @Test
    public void testSetFinalDriveRatio() {
        System.out.println("setFinalDriveRatio");
        double finalDriveRatio = 2.8;
        Vehicle instance = vehicle1;
        instance.setFinalDriveRatio(finalDriveRatio);
        double expResult = 2.8;
        double result = instance.getFinalDriveRatio();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getDescription method, of class Vehicle.
     */
    @Test
    public void testGetDescription() {
        System.out.println("getDescription");
        Vehicle instance = vehicle1;
        String expResult = "descricao";
        String result = instance.getDescription();
        assertEquals(expResult, result);

    }

    /**
     * Test of setDescription method, of class Vehicle.
     */
    @Test
    public void testSetDescription() {
        System.out.println("setDescription");
        String description = "teste";
        Vehicle instance = vehicle1;
        instance.setDescription(description);
        String expResult = "teste";
        String result = instance.getDescription();
        assertEquals(expResult, result);
    }

    /**
     * Test of setType method, of class Vehicle.
     */
    @Test
    public void testSetType() {
        System.out.println("setType");
        String type = "moto";
        Vehicle instance = vehicle1;
        instance.setType(type);
        String expResult = "moto";
        String result = instance.getType();
        assertEquals(expResult, result);
    }

    /**
     * Test of getFrontalArea method, of class Vehicle.
     */
    @Test
    public void testGetFrontalArea() {
        System.out.println("getFrontalArea");
        Vehicle instance = vehicle1;
        double expResult = 1.8;
        double result = instance.getFrontalArea();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of setFrontalArea method, of class Vehicle.
     */
    @Test
    public void testSetFrontalArea() {
        System.out.println("setFrontalArea");
        double frontalArea = 2.0;
        Vehicle instance = vehicle1;
        instance.setFrontalArea(frontalArea);
        double expResult = 2.0;
        double result = instance.getFrontalArea();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getFuel method, of class Vehicle.
     */
    @Test
    public void testGetFuel() {
        System.out.println("getFuel");
        Vehicle instance = vehicle1;
        String expResult = "gasoline";
        String result = instance.getFuel();
        assertEquals(expResult, result);
    }

    /**
     * Test of setFuel method, of class Vehicle.
     */
    @Test
    public void testSetFuel() {
        System.out.println("setFuel");
        String fuel = "diesel";
        Vehicle instance = vehicle1;
        instance.setFuel(fuel);
        String expResult = "diesel";
        String result = instance.getFuel();
        assertEquals(expResult, result);
    }

    /**
     * Test of getVelocityLimit method, of class Vehicle.
     */
    @Test
    public void testGetVelocityLimit_0args() {
        System.out.println("getVelocityLimit");
        Vehicle instance = vehicle1;
        instance.setVelocityLimit(velocityLimit2);
        HashMap<SectionTypology, Double> expResult = null;
        HashMap<SectionTypology, Double> result = instance.getVelocityLimit();
        assertEquals(expResult, result);
    }

    /**
     * Test of setVelocityLimit method, of class Vehicle.
     */
    @Test
    public void testSetVelocityLimit() {
        System.out.println("setVelocityLimit");
        HashMap<SectionTypology, Double> velocityLimit = velocityLimit3;
        Vehicle instance = vehicle1;
        instance.setVelocityLimit(velocityLimit);
        HashMap<SectionTypology, Double> expResult = velocityLimit3;
        HashMap<SectionTypology, Double> result = instance.getVelocityLimit();
        assertEquals(expResult, result);
    }

    /**
     * Test of getGearList method, of class Vehicle.
     */
    @Test
    public void testGetGearList() {
        System.out.println("getGearList");
        Vehicle instance = vehicle1;
        HashMap<Integer, Double> expResult = gearList1;
        HashMap<Integer, Double> result = instance.getGearList();
        assertEquals(expResult, result);
    }

    /**
     * Test of setGearList method, of class Vehicle.
     */
    @Test
    public void testSetGearList() {
        System.out.println("setGearList");
        HashMap<Integer, Double> gearList = gearList1;
        Vehicle instance = vehicle1;
        instance.setGearList(gearList);
        HashMap<Integer, Double> expResult = gearList1;
        HashMap<Integer, Double> result = instance.getGearList();
        assertEquals(expResult, result);
    }

    /**
     * Test of getThrottleList method, of class Vehicle.
     */
    @Test
    public void testGetThrottleList() {
        System.out.println("getThrottleList");
        Vehicle instance = vehicle1;
        ArrayList<Throttle> expResult = throttleList1;
        ArrayList<Throttle> result = instance.getThrottleList();
        assertEquals(expResult, result);
    }

    /**
     * Test of setThrottleList method, of class Vehicle.
     */
    @Test
    public void testSetThrottleList() {
        System.out.println("setThrottleList");
        ArrayList<Throttle> throttleList = throttleList2;
        Vehicle instance = vehicle1;
        instance.setThrottleList(throttleList);
        ArrayList<Throttle> expResult = throttleList2;
        ArrayList<Throttle> result = instance.getThrottleList();
        assertEquals(expResult, result);
    }

    /**
     * Test of getEngineEfficiency method, of class Vehicle.
     */
    @Test
    public void testGetEngineEfficiency() {
        System.out.println("getEngineEfficiency");
        Vehicle instance = vehicle4;
        List<EngineEfficiency> expResult = lstEngineEfficiency;
        List<EngineEfficiency> result = instance.getEngineEfficiency();
        assertEquals(expResult, result);

    }

    /**
     * Test of equals method, of class Vehicle.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = vehicle2;
        Vehicle instance = vehicle1;
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }


    /**
     * Test of addVelocityLimit method, of class Vehicle.
     */
    @Test
    public void testAddVelocityLimit() {
        System.out.println("addVelocityLimit");
        SectionTypology segment_type = stype3;
        double limit = 50.0;
        Vehicle instance = vehicle1;
        instance.addVelocityLimit(segment_type, limit);
        double expResult = 50.0;
        double result = instance.getVelocityLimit(stype3);
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of toString method, of class Vehicle.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Vehicle instance = vehicle3;
        String expResult = "Vehicle " + instance.getPK() + " - " + instance.getName();
        String result = instance.toString();
        assertEquals(expResult, result);

    }

    /**
     * Test of showData method, of class Vehicle.
     */
    @Test
    public void testShowData() {
        System.out.println("showData");
        Vehicle instance = vehicle3;
        
        String expResult =  "Vehicle: \n"
                + "id= " + 1 + "\n"
                + "name= " + "dummy3" + "\n"
                + "mass= " + 600.0 + "\n"
                + "type= " + "moto" + "\n"
                + "load= " + 70.0 + "\n"
                + "drag_Coefficient= " + 0.12 + "\n"
                + "rrc=" + 0.01 + "\n"
                + "wheelSize=" + 0.3 + "\n"
                + "velocityLimit=" + velocityLimit1 + "\n"
                + "minRPM=" + 900.0 + "\n"
                + "maxRPM=" + 6900.0 + "\n"
                + "finalDriveRatio=" + 2.6;
        
        String result = instance.showData();
        
        System.out.println("YYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYY");
        System.out.println(result);
        System.out.println(result.length());
        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
        System.out.println(expResult);
        System.out.println(expResult.length());
        
        
        assertEquals(expResult, result);
    }

    /**
     * Test of hasPK method, of class Vehicle.
     */
    @Test
    public void testHasPK() {
        System.out.println("hasPK");
        Vehicle instance = vehicle3;
        instance.setPK(0);
        boolean expResult = false;
        boolean result = instance.hasPK();
        assertEquals(expResult, result);

    }

    /**
     * Test of getRadiusOfTire method, of class Vehicle.
     */
    @Test
    public void testGetRadiusOfTire() {
        System.out.println("getRadiusOfTire");
        Vehicle instance = vehicle1;
        double expResult = instance.getWheelSize() / 2;
        double result = instance.getRadiusOfTire();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of getIdleConsumption method, of class Vehicle.
     */
    @Test
    public void testGetIdleConsumption() {
        System.out.println("getIdleConsumption");
        double timeIdle = 2;
        Vehicle instance = vehicle1;
        double expResult = 145975.03333333335;
        double result = instance.getIdleConsumption(timeIdle);
        assertEquals(expResult, result, 0.0);

    }

    /**
     * Test of getPK method, of class Vehicle.
     */
    @Test
    public void testGetPK() {
        System.out.println("getPK");
        Vehicle instance = vehicle3;
        int expResult = 1;
        int result = instance.getPK();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPK method, of class Vehicle.
     */
    @Test
    public void testSetPK() {
        System.out.println("setPK");
        int pk = 1;
        Vehicle instance = vehicle3;
        instance.setPK(pk);
        int expResult = 1;
        int result = instance.getPK();
        assertEquals(expResult, result);

    }

    /**
     * Test of getRrc method, of class Vehicle.
     */
    @Test
    public void testGetRrc() {
        System.out.println("getRrc");
        Vehicle instance = vehicle3;
        double expResult = 0.01;
        double result = instance.getRrc();
        assertEquals(expResult, result, 0.0);

    }

    /**
     * Test of setRrc method, of class Vehicle.
     */
    @Test
    public void testSetRrc() {
        System.out.println("setRrc");
        double rrc = 0.01;
        Vehicle instance = vehicle3;
        instance.setRrc(rrc);
        double expResult = 0.01;
        double result = instance.getRrc();
        assertEquals(expResult, result, 0.0);
    }

    public class VehicleImpl extends Vehicle {

        public List<EngineEfficiency> getEngineEfficiency() {
            return null;
        }
    }
  
}
