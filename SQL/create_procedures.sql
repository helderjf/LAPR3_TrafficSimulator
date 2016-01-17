
------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------
-------------------------PROCEDURES
------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------


create or replace PROCEDURE SAVE_NEW_PROJECT(
	  nam IN PROJECTS.NAME%TYPE, 
	  descr IN PROJECTS.DESCRIPTION%TYPE, 
	  stat IN PROJECTS.STATE%TYPE, 
	  projpk OUT PROJECTS.ID_PROJECT%TYPE) AS

newpk PROJECTS.ID_PROJECT%TYPE;

BEGIN

newpk := SEQ_PROJECT.NEXTVAL;
insert INTO PROJECTS (
					  ID_PROJECT, 
					  NAME, 
					  DESCRIPTION,
					  STATE) 
					  values (
							  newpk, 
							  nam, 
							  descr,
							  stat);


projpk := newpk;

END SAVE_NEW_PROJECT;

------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------


create or replace PROCEDURE SAVE_NEW_ROAD_NETWORK(
		projpk IN ROAD_NETWORKS.ID_PROJECT%TYPE,
		nam IN ROAD_NETWORKS.NAME%TYPE, 
		descr IN ROAD_NETWORKS.DESCRIPTION%TYPE, 
		rnpk OUT ROAD_NETWORKS.ID_ROAD_NETWORK%TYPE) AS

newpk ROAD_NETWORKS.ID_ROAD_NETWORK%TYPE;

BEGIN

newpk := SEQ_ROAD_NETWORK.NEXTVAL;
insert INTO ROAD_NETWORKS(ID_ROAD_NETWORK, ID_PROJECT, NAME, DESCRIPTION) values (newpk, projpk, nam, descr);


rnpk := newpk;

END SAVE_NEW_ROAD_NETWORK;





------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------



create or replace PROCEDURE SAVE_NEW_NODE(
		rnpk IN ROAD_NETWORKS.ID_ROAD_NETWORK%TYPE,
		nam IN NODES.NAME%TYPE, 
		nopk OUT NODES.ID_NODE%TYPE) AS

newpk NODES.ID_NODE%TYPE;


BEGIN

newpk := SEQ_NODE.NEXTVAL;

insert INTO NODES(ID_NODE, ID_ROAD_NETWORK, NAME) values (newpk, rnpk, nam);
nopk := newpk;

END SAVE_NEW_NODE;

------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------



create or replace PROCEDURE SAVE_NEW_SECTION(
		rnpk IN ROAD_NETWORKS.ID_ROAD_NETWORK%TYPE,
		road IN SECTIONS.ROAD_NAME%TYPE,
	onode IN SECTIONS.BEGINING_NODE_ID%TYPE,
	dnode IN SECTIONS.ENDING_NODE_ID%TYPE,
	typology IN VARCHAR2,
	direction IN SECTIONS.DIRECTION%TYPE,
	toll IN SECTIONS.TOLL%TYPE,
	winddir IN SECTIONS.WIND_DIRECTION%TYPE,
	windvel IN SECTIONS.WIND_SPEED%TYPE,
		secpk OUT SECTIONS.ID_SECTION%TYPE) AS

newpk SECTIONS.ID_SECTION%TYPE;
typo SECTIONS.ID_TYPOLOGY%TYPE;

BEGIN

newpk := SEQ_SECTION.NEXTVAL;
select ID_TYPOLOGY into typo from SECTION_TYPOLOGIES
  where NAME = typology;



insert INTO SECTIONS(ID_SECTION, 
	ID_ROAD_NETWORK, 
	ROAD_NAME, 
	BEGINING_NODE_ID, 
	ENDING_NODE_ID, 
	ID_TYPOLOGY, 
	DIRECTION, 
	TOLL, 
	WIND_DIRECTION, 
	WIND_SPEED) 
  values (newpk, rnpk, road, onode, dnode, typo, direction, toll, winddir,windvel);
secpk := newpk;

END SAVE_NEW_SECTION;



------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------

create or replace PROCEDURE SAVE_NEW_SEGMENT(
		segindex IN SEGMENTS.SEGMENT_INDEX%TYPE,
	secpk IN SEGMENTS.ID_SECTION%TYPE,
	iheight IN SEGMENTS.INITIAL_HEIGHT%TYPE,
	slo IN SEGMENTS.SLOPE%TYPE,
	len IN SEGMENTS.LENGHT%TYPE,
	maxvel IN SEGMENTS.MAX_VELOCITY%TYPE,
	minvel IN SEGMENTS.MIN_VELOCITY%TYPE,
	maxvehicles IN SEGMENTS.MAX_VEHICLES%TYPE) AS


BEGIN


insert INTO SEGMENTS(
  Segment_Index, 
	ID_Section, 
	Initial_Height, 
	Slope, 
	Lenght, 
	Max_Velocity, 
	Min_Velocity, 
	Max_Vehicles)
  values (segindex, secpk, iheight, slo, len, maxvel, minvel, maxvehicles);


END SAVE_NEW_SEGMENT;







------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------



create or replace PROCEDURE SAVE_NEW_VEHICLE(
	projpk IN VEHICLES.ID_PROJECT%TYPE,
	nam IN VEHICLES.NAME%TYPE, 
	descr IN VEHICLES.DESCRIPTION%TYPE, 
	typ IN VEHICLES.TYPE%TYPE,
	fuel IN VEHICLES.FUEL%TYPE,
	mass IN VEHICLES.MASS%TYPE,
	load IN VEHICLES.LOAD%TYPE,
	drag IN VEHICLES.DRAG_COEF%TYPE,
	fronta IN VEHICLES.FRONTAL_AREA%TYPE,
	rrc IN VEHICLES.RRC%TYPE,
	wheelsize IN VEHICLES.WHEEL_SIZE%TYPE,
	fdr IN VEHICLES.FINAL_DRIVE_RATIO%TYPE,
	rpmmin IN VEHICLES.RPM_MIN%TYPE,
	rpmmax IN VEHICLES.RPM_MAX%TYPE,
	vpk OUT VEHICLES.ID_VEHICLE%TYPE) AS

newpk VEHICLES.ID_VEHICLE%TYPE;

BEGIN

newpk := SEQ_VEHICLE.NEXTVAL;

insert INTO VEHICLES(ID_VEHICLE, ID_PROJECT, NAME, DESCRIPTION, TYPE, FUEL,
					  MASS, LOAD, DRAG_COEF, FRONTAL_AREA, RRC, WHEEL_SIZE, 
					  FINAL_DRIVE_RATIO, RPM_MIN, RPM_MAX) 
					  values (newpk, projpk, nam, descr,typ,fuel,mass,load,drag,fronta,rrc,wheelsize,fdr,rpmmin,rpmmax);


vpk := newpk;

END SAVE_NEW_VEHICLE;






------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------


CREATE OR REPLACE PROCEDURE SAVE_NEW_COMBUSTION_V (vpk IN COMBUSTION_VEHICLES.ID_VEHICLE%TYPE) AS
BEGIN

  INSERT INTO COMBUSTION_VEHICLES(ID_VEHICLE) VALUES (vpk);

END SAVE_NEW_COMBUSTION_V;




------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------

CREATE OR REPLACE PROCEDURE SAVE_NEW_HYBRID_V (vpk IN HYBRID_VEHICLES.ID_VEHICLE%TYPE, err IN HYBRID_VEHICLES.ENERGY_REGENERATION_RATIO%TYPE) AS

BEGIN

  INSERT INTO HYBRID_VEHICLES(ID_VEHICLE,ENERGY_REGENERATION_RATIO) VALUES (vpk,err);

END SAVE_NEW_HYBRID_V;



------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------

CREATE OR REPLACE PROCEDURE SAVE_NEW_ELECTRIC_V (vpk IN ELECTRIC_VEHICLES.ID_VEHICLE%TYPE, err IN ELECTRIC_VEHICLES.ENERGY_REGENERATION_RATIO%TYPE) AS

BEGIN

  INSERT INTO ELECTRIC_VEHICLES(ID_VEHICLE, ENERGY_REGENERATION_RATIO) VALUES (vpk,err);

END SAVE_NEW_ELECTRIC_V;




------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------


CREATE OR REPLACE PROCEDURE SAVE_NEW_VEHICLE_GEAR (vpk IN GEARS.ID_VEHICLE%TYPE, gearn IN GEARS.ID_GEAR%TYPE, ratio GEARS.RATIO%TYPE) AS

BEGIN

  INSERT INTO GEARS(ID_VEHICLE, ID_GEAR, RATIO) VALUES (vpk, gearn, ratio);

END SAVE_NEW_VEHICLE_GEAR;




------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------


CREATE OR REPLACE PROCEDURE SAVE_NEW_VEHICLE_VEL_LIMIT (vpk IN VEHICLES_VELOCITY_LIMITS.ID_VEHICLE%TYPE,
														typ IN VARCHAR2,
														vlimit IN VEHICLES_VELOCITY_LIMITS.VEL_LIMIT%TYPE,
														confirm OUT INTEGER) AS

typology VEHICLES_VELOCITY_LIMITS.ID_TYPOLOGY%TYPE;

BEGIN

select count(ID_Typology) into confirm from SECTION_TYPOLOGIES  where name=typ;
select ID_Typology  into typology from SECTION_TYPOLOGIES WHERE name=typ;


  INSERT INTO VEHICLES_VELOCITY_LIMITS(ID_VEHICLE, ID_Typology, Vel_Limit) VALUES (vpk, typology, vlimit);

END SAVE_NEW_VEHICLE_VEL_LIMIT;




------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------

CREATE OR REPLACE PROCEDURE SAVE_NEW_VEHICLE_THROTTLE (vpk IN THROTTLES.ID_VEHICLE%TYPE,
														throtpk IN THROTTLES.ID_THROTTLE%TYPE,
														regpk IN THROTTLES.ID_REGIME%TYPE,
														torq IN THROTTLES.TORQUE%TYPE,
														rpml IN THROTTLES.RPM_LOW%TYPE,
														rpmh IN THROTTLES.RPM_HIGH%TYPE,
														sfc IN THROTTLES.SFC%TYPE) AS



BEGIN


  INSERT INTO THROTTLES(ID_VEHICLE, ID_THROTTLE, ID_REGIME, TORQUE, RPM_LOW, RPM_HIGH, SFC) 
			  VALUES (vpk, throtpk, regpk, torq, rpml, rpmh, sfc);

END SAVE_NEW_VEHICLE_THROTTLE;




------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------


create or replace PROCEDURE SAVE_NEW_SIMULATION(
	  projpk IN SIMULATIONS.ID_PROJECT%TYPE,
	  nam IN SIMULATIONS.NAME%TYPE, 
	  descr IN SIMULATIONS.DESCRIPTION%TYPE, 
	  stat IN SIMULATIONS.STATE%TYPE, 
	  simpk OUT PROJECTS.ID_PROJECT%TYPE) AS

newpk SIMULATIONS.ID_SIMULATION%TYPE;

BEGIN

newpk := SEQ_SIMULATION.NEXTVAL;
insert INTO SIMULATIONS (
					  ID_SIMULATION,
					  ID_PROJECT, 
					  NAME, 
					  DESCRIPTION,
					  STATE) 
					  values (
							  newpk,
							  projpk,
							  nam, 
							  descr,
							  stat);


simpk := newpk;

END SAVE_NEW_SIMULATION;


------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------



create or replace PROCEDURE SAVE_NEW_TRAFFIC_PATTERN(
	  simpk IN TRAFFIC_PATTERNS.ID_SIMULATION%TYPE,
	  onodepk IN TRAFFIC_PATTERNS.BEGIN_NODE_ID%TYPE,
	  enodepk IN TRAFFIC_PATTERNS.END_NODE_ID%TYPE,	
	  vpk IN TRAFFIC_PATTERNS.ID_VEHICLE%TYPE,
	  arate IN TRAFFIC_PATTERNS.ARRIVAL_RATE%TYPE,
	  trafpatpk OUT TRAFFIC_PATTERNS.ID_TRAFFIC_PATTERN%TYPE) AS

newpk TRAFFIC_PATTERNS.ID_TRAFFIC_PATTERN%TYPE;

BEGIN

newpk := seq_traffic_pattern.NEXTVAL;
insert INTO TRAFFIC_PATTERNS (ID_TRAFFIC_PATTERN, ID_SIMULATION, BEGIN_NODE_ID, END_NODE_ID, ID_VEHICLE, ARRIVAL_RATE) 
					  values (newpk, simpk, onodepk, enodepk, vpk, arate);
trafpatpk := newpk;

END SAVE_NEW_TRAFFIC_PATTERN;

------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------



create or replace PROCEDURE SAVE_NEW_SIMULATION_RUN(
	  simpk IN SIMULATION_RUNS.ID_SIMULATION%TYPE,
	  name IN SIMULATION_RUNS.NAME%TYPE,
	  duration IN SIMULATION_RUNS.DURATION%TYPE,	
	  tstep IN SIMULATION_RUNS.TIME_STEP%TYPE,
	  bpm IN SIMULATION_RUNS.BEST_PATH_METHOD%TYPE,
	  runpk OUT SIMULATION_RUNS.ID_RUN%TYPE) AS

newpk SIMULATION_RUNS.ID_RUN%TYPE;

BEGIN

newpk := seq_simulation_run.NEXTVAL;
insert INTO SIMULATION_RUNS (ID_RUN, ID_SIMULATION, NAME, DURATION, TIME_STEP, BEST_PATH_METHOD) 
					  values (newpk, simpk, name, duration, tstep, bpm);
runpk := newpk;

END SAVE_NEW_SIMULATION_RUN;

------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------


create or replace PROCEDURE SAVE_DROPPED_VEHICLES(
	  runpk IN DROPPED_VEHICLES.ID_RUN%TYPE,
	  trafpats IN INTEGER_T,
	  droptimes IN FLOAT_T,
	  o_errors OUT INTEGER_T) AS

dml_errors EXCEPTION;
PRAGMA EXCEPTION_INIT(dml_errors, -24381);


BEGIN

FORALL i in 1 .. trafpats.LAST save exceptions

insert INTO DROPPED_VEHICLES (ID_DROPPED_VEHICLE, ID_RUN, ID_TRAFFIC_PATTERN, INSTANT_DROPPED) 
					  values (seq_dropped_vehicle.NEXTVAL, runpk, trafpats(i), droptimes(i));

exception
  when dml_errors then
    for i in 1 .. SQL%bulk_exceptions.count loop
      --if SQL%BULK_EXCEPTIONS(i).ERROR_CODE != 00001 then
      --  debug.f( '  ... An unexpected exception occurred (%s)', SQLERRM );
      --  raise;
      --end if;
      o_errors.extend(1);
      o_errors(o_errors.count) := SQL%bulk_exceptions(i).ERROR_INDEX;
    end loop;



END SAVE_DROPPED_VEHICLES;





------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------



create or replace PROCEDURE SAVE_INJECTED_VEHICLES(
	  runpk IN INJECTED_VEHICLES.ID_RUN%TYPE,
	  trafpats IN INTEGER_T, 
    injpk OUT INTEGER_T) AS



BEGIN
injpk:= INTEGER_T();
injpk.EXTEND(trafpats.count);

FOR i in 1..trafpats.LAST loop

injpk(i):=seq_injected_vehicle.NEXTVAL;
  
	insert INTO INJECTED_VEHICLES (ID_INJECTED_VEHICLE, ID_RUN, ID_TRAFFIC_PATTERN) 
					  values (injpk(i), runpk, trafpats(i));
	

	
end loop;


END SAVE_INJECTED_VEHICLES;




------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------




create or replace PROCEDURE SAVE_INJECTED_V_BEHAVIOURS(
	  injpk IN INTEGER_T,
	  sections IN INTEGER_T,
	  segments IN INTEGER_T,
	  directions IN VARCHAR2_T,
	  intimes IN FLOAT_T,
	  outtimes IN FLOAT_T,
	  consumptions IN FLOAT_T,
	  o_errors OUT INTEGER_T) AS

dml_errors EXCEPTION;
PRAGMA EXCEPTION_INIT(dml_errors, -24381);


	
BEGIN

o_errors:= INTEGER_T();

FORALL i in 1 .. injpk.LAST  save exceptions
	
	insert INTO INJECTED_VEHICLES_BEHAVIOURS (ID_INJECTED_VEHICLE,ID_SECTION,SEGMENT_INDEX,DIRECTION,INSTANT_IN,INSTANT_OUT,ENERGY_SPENT) 
					  values(injpk(i),sections(i),segments(i),directions(i),intimes(i),outtimes(i),consumptions(i));
	

exception
  when dml_errors then
    for i in 1 .. SQL%bulk_exceptions.count loop
      --if SQL%BULK_EXCEPTIONS(i).ERROR_CODE != 00001 then
      --  debug.f( '  ... An unexpected exception occurred (%s)', SQLERRM );
      --  raise;
      --end if;
      o_errors.extend(1);
      o_errors(o_errors.count) := SQL%bulk_exceptions(i).ERROR_INDEX;	

end loop;


END SAVE_INJECTED_V_BEHAVIOURS;












------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------
-- UPDATE PROCEDURES
------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------

create or replace PROCEDURE UPDATE_PROJECT(
	projpk IN PROJECTS.ID_PROJECT%TYPE,	
	  nam IN PROJECTS.NAME%TYPE, 
	  descr IN PROJECTS.DESCRIPTION%TYPE, 
	  stat IN PROJECTS.STATE%TYPE) AS


BEGIN

UPDATE PROJECTS 
  set NAME = nam, 
	  DESCRIPTION = descr,
					  STATE = stat 
  where ID_PROJECT = projpk;


END UPDATE_PROJECT;






------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------


create or replace PROCEDURE UPDATE_ROAD_NETWORK(
	rnpk IN ROAD_NETWORKS.ID_ROAD_NETWORK%TYPE,
	  nam IN ROAD_NETWORKS.NAME%TYPE, 
	  descr IN ROAD_NETWORKS.DESCRIPTION%TYPE) AS


BEGIN

UPDATE ROAD_NETWORKS 
  set NAME = nam, 
	  DESCRIPTION = descr
  where ID_ROAD_NETWORK = rnpk;


END UPDATE_ROAD_NETWORK;


------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------

create or replace PROCEDURE UPDATE_NODE(
	nodepk IN NODES.ID_NODE%TYPE,
	  nam IN NODES.NAME%TYPE) AS


BEGIN

UPDATE NODES 
  set NAME = nam
  where ID_NODE = nodepk;

END UPDATE_NODE;



------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------


create or replace PROCEDURE UPDATE_SECTION(
		secpk IN SECTIONS.ID_SECTION%TYPE,
		road IN SECTIONS.ROAD_NAME%TYPE,
	onode IN SECTIONS.BEGINING_NODE_ID%TYPE,
	dnode IN SECTIONS.ENDING_NODE_ID%TYPE,
	typology IN VARCHAR2,
	direction IN SECTIONS.DIRECTION%TYPE,
	toll IN SECTIONS.TOLL%TYPE,
	winddir IN SECTIONS.WIND_DIRECTION%TYPE,
	windvel IN SECTIONS.WIND_SPEED%TYPE) AS


typo SECTIONS.ID_TYPOLOGY%TYPE;

BEGIN


select ID_TYPOLOGY into typo from SECTION_TYPOLOGIES
  where NAME = typology;


UPDATE SECTIONS
	SET ROAD_NAME = road, 
		BEGINING_NODE_ID = onode, 
		ENDING_NODE_ID = dnode, 
		ID_TYPOLOGY = typo, 
		DIRECTION = direction, 
		TOLL = toll, 
		WIND_DIRECTION = winddir, 
		WIND_SPEED = windvel
	WHERE ID_SECTION = secpk;


END UPDATE_SECTION;



------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------

create or replace PROCEDURE UPDATE_SEGMENT(
		segindex IN SEGMENTS.SEGMENT_INDEX%TYPE,
	secpk IN SEGMENTS.ID_SECTION%TYPE,
	iheight IN SEGMENTS.INITIAL_HEIGHT%TYPE,
	slo IN SEGMENTS.SLOPE%TYPE,
	len IN SEGMENTS.LENGHT%TYPE,
	maxvel IN SEGMENTS.MAX_VELOCITY%TYPE,
	minvel IN SEGMENTS.MIN_VELOCITY%TYPE,
	maxvehicles IN SEGMENTS.MAX_VEHICLES%TYPE) AS


BEGIN

UPDATE SEGMENTS
	SET Initial_Height = iheight, 
		Slope = slo, 
		Lenght = len, 
		Max_Velocity = maxvel, 
		Min_Velocity = minvel, 
		Max_Vehicles = maxvehicles
	WHERE ID_SECTION = secpk AND SEGMENT_INDEX = segindex;



END UPDATE_SEGMENT;


------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------

create or replace PROCEDURE UPDATE_SIMULATION(
	simpk IN SIMULATIONS.ID_SIMULATION%TYPE,	
	  nam IN SIMULATIONS.NAME%TYPE, 
	  descr IN SIMULATIONS.DESCRIPTION%TYPE, 
	  stat IN SIMULATIONS.STATE%TYPE) AS


BEGIN

UPDATE SIMULATIONS 
  set NAME = nam, 
	  DESCRIPTION = descr,
		STATE = stat 
  where ID_SIMULATION = simpk;


END UPDATE_SIMULATION;



------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------

create or replace PROCEDURE UPDATE_TRAFFIC_PATTERN(
	trafpk IN TRAFFIC_PATTERNS.ID_TRAFFIC_PATTERN%TYPE,	
	  bnode IN TRAFFIC_PATTERNS.BEGIN_NODE_ID%TYPE, 
	  enode IN TRAFFIC_PATTERNS.END_NODE_ID%TYPE, 
	  vpk IN TRAFFIC_PATTERNS.ID_VEHICLE%TYPE,
	arate IN TRAFFIC_PATTERNS.ARRIVAL_RATE%TYPE) AS


BEGIN

UPDATE TRAFFIC_PATTERNS 
  set BEGIN_NODE_ID = bnode, 
	  END_NODE_ID = enode,
	  ID_VEHICLE=vpk,
		ARRIVAL_RATE = arate
  where ID_TRAFFIC_PATTERN = trafpk;


END UPDATE_TRAFFIC_PATTERN;










------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------
-- READ PROCEDURES
------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------

CREATE OR REPLACE PROCEDURE GET_ORDERED_PROJECT_LIST(outcursor OUT SYS_REFCURSOR) AS

BEGIN

OPEN outcursor FOR
	select ID_PROJECT, NAME
		from PROJECTS
		order by name asc;		

END GET_ORDERED_PROJECT_LIST;



------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------


CREATE OR REPLACE PROCEDURE GET_PROJECT_PROPERTIES(pname IN PROJECTS.NAME%TYPE, outcursor OUT SYS_REFCURSOR) AS

BEGIN

OPEN outcursor FOR
	select ID_PROJECT, NAME, DESCRIPTION, STATE
		from PROJECTS
		WHERE NAME=pname;		

END GET_PROJECT_PROPERTIES;




------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------

CREATE OR REPLACE PROCEDURE GET_PROJECT_ROAD_NETWORK(projpk IN PROJECTS.ID_PROJECT%TYPE, outcursor OUT SYS_REFCURSOR) AS

BEGIN

OPEN outcursor FOR
	select ID_ROAD_NETWORK, NAME, DESCRIPTION
		from ROAD_NETWORKS
		WHERE ID_PROJECT = projpk;		

END GET_PROJECT_ROAD_NETWORK;



------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------


CREATE OR REPLACE PROCEDURE GET_ROAD_NETWORK_NODES(rnpk IN NODES.ID_ROAD_NETWORK%TYPE, outcursor OUT SYS_REFCURSOR) AS

BEGIN

OPEN outcursor FOR
	select ID_NODE, NAME
		from NODES
		WHERE ID_ROAD_NETWORK = rnpk;		

END GET_ROAD_NETWORK_NODES;



------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------


CREATE OR REPLACE PROCEDURE GET_ROAD_NETWORK_SECTIONS(rnpk IN SECTIONS.ID_ROAD_NETWORK%TYPE, outcursor OUT SYS_REFCURSOR) AS

BEGIN

OPEN outcursor FOR
	select s.ID_SECTION,s.ROAD_NAME,s.BEGINING_NODE_ID,s.ENDING_NODE_ID,st.NAME as TYPOLOGY, s.DIRECTION, s.TOLL, s.WIND_DIRECTION, s.WIND_SPEED
		from SECTIONS s, SECTION_TYPOLOGIES st
		WHERE s.ID_ROAD_NETWORK = rnpk
	  AND s.ID_TYPOLOGY=st.ID_TYPOLOGY;		

END GET_ROAD_NETWORK_SECTIONS;




------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------
CREATE OR REPLACE PROCEDURE GET_SECTION_SEGMENTS(secpk IN SEGMENTS.ID_SECTION%TYPE, outcursor OUT SYS_REFCURSOR) AS

BEGIN

OPEN outcursor FOR
	select SEGMENT_INDEX,INITIAL_HEIGHT, SLOPE, LENGHT, MAX_VELOCITY, MIN_VELOCITY, MAX_VEHICLES
		from SEGMENTS
		WHERE ID_SECTION = secpk;

END GET_SECTION_SEGMENTS;




------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------
CREATE OR REPLACE PROCEDURE GET_PROJECT_COMB_VEHICLES(projpk IN VEHICLES.ID_PROJECT%TYPE, outcursor OUT SYS_REFCURSOR) AS

BEGIN

OPEN outcursor FOR
	select v.ID_VEHICLE, 
		  v.NAME, 
		  v.DESCRIPTION, 
		  v.TYPE, 
		  v.FUEL,
		  v.MASS, 
		  v.LOAD,
		  v.DRAG_COEF,
		  v.FRONTAL_AREA,
		  v.RRC, 
		  v.WHEEL_SIZE,
		  v.RPM_MIN,
		  v.RPM_MAX,
		  v.FINAL_DRIVE_RATIO
		from VEHICLES v, COMBUSTION_VEHICLES cv
		WHERE v.ID_PROJECT=projpk
	  and v.ID_VEHICLE=cv.ID_VEHICLE;

END GET_PROJECT_COMB_VEHICLES;




------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------
CREATE OR REPLACE PROCEDURE GET_PROJECT_HYBR_VEHICLES(projpk IN VEHICLES.ID_PROJECT%TYPE, outcursor OUT SYS_REFCURSOR) AS

BEGIN

OPEN outcursor FOR
	select v.ID_VEHICLE, 
		  v.NAME, 
		  v.DESCRIPTION, 
		  v.TYPE,
		  v.FUEL,
		  v.MASS, 
		  v.LOAD,
		  v.DRAG_COEF,
		  v.FRONTAL_AREA,
		  v.RRC, 
		  v.WHEEL_SIZE,
		  v.RPM_MIN,
		  v.RPM_MAX,
		  v.FINAL_DRIVE_RATIO,
		  hv.ENERGY_REGENERATION_RATIO

		from VEHICLES v, HYBRID_VEHICLES hv
		WHERE v.ID_PROJECT=projpk
	  and v.ID_VEHICLE=hv.ID_VEHICLE;

END GET_PROJECT_HYBR_VEHICLES;




------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------
CREATE OR REPLACE PROCEDURE GET_PROJECT_ELEC_VEHICLES(projpk IN VEHICLES.ID_PROJECT%TYPE, outcursor OUT SYS_REFCURSOR) AS

BEGIN

OPEN outcursor FOR
	select v.ID_VEHICLE, 
		  v.NAME, 
		  v.DESCRIPTION, 
		  v.TYPE,
		  v.FUEL,		  
		  v.MASS, 
		  v.LOAD,
		  v.DRAG_COEF,
		  v.FRONTAL_AREA,
		  v.RRC, 
		  v.WHEEL_SIZE,
		  v.RPM_MIN,
		  v.RPM_MAX,
		  v.FINAL_DRIVE_RATIO,
		  ev.ENERGY_REGENERATION_RATIO
	
		from VEHICLES v, ELECTRIC_VEHICLES ev
		WHERE v.ID_PROJECT=projpk
	  and v.ID_VEHICLE=ev.ID_VEHICLE;

END GET_PROJECT_ELEC_VEHICLES;

------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------
CREATE OR REPLACE PROCEDURE GET_VEHICLE_THROTTLES(vehpk IN THROTTLES.ID_VEHICLE%TYPE, outcursor OUT SYS_REFCURSOR) AS

BEGIN

OPEN outcursor FOR
	select ID_THROTTLE
		from THROTTLES
		WHERE ID_VEHICLE=vehpk
	group by ID_THROTTLE;

END GET_VEHICLE_THROTTLES;




------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------
CREATE OR REPLACE PROCEDURE GET_VEHICLE_THROTTLE_REGIMES(vehpk IN THROTTLES.ID_VEHICLE%TYPE, throtid IN THROTTLES.ID_THROTTLE%TYPE, outcursor OUT SYS_REFCURSOR) AS

BEGIN

OPEN outcursor FOR
	select ID_REGIME, TORQUE, RPM_LOW, RPM_HIGH, SFC
		from THROTTLES
		WHERE ID_VEHICLE=vehpk
			AND ID_THROTTLE = throtid
		ORDER BY RPM_LOW;

END GET_VEHICLE_THROTTLE_REGIMES;




------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------

CREATE OR REPLACE PROCEDURE GET_VEHICLE_GEARS(vehpk IN GEARS.ID_VEHICLE%TYPE, outcursor OUT SYS_REFCURSOR) AS

BEGIN

OPEN outcursor FOR
	select ID_GEAR, RATIO
		from GEARS
		WHERE ID_VEHICLE=vehpk
	ORDER BY ID_GEAR;

END GET_VEHICLE_GEARS;



------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------
CREATE OR REPLACE PROCEDURE GET_VEHICLE_VEL_LIMITS(vehpk IN VEHICLES_VELOCITY_LIMITS.ID_VEHICLE%TYPE, outcursor OUT SYS_REFCURSOR) AS

BEGIN

OPEN outcursor FOR
	select st.NAME, vvl.VEL_LIMIT
		from VEHICLES_VELOCITY_LIMITS vvl, SECTION_TYPOLOGIES st
		WHERE vvl.ID_VEHICLE=3
			AND vvl.ID_TYPOLOGY = st.ID_TYPOLOGY;

END GET_VEHICLE_VEL_LIMITS;


------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------

CREATE OR REPLACE PROCEDURE GET_ORDERED_SIMULATION_LIST(projpk in SIMULATIONS.ID_PROJECT%TYPE, outcursor OUT SYS_REFCURSOR) AS

BEGIN

OPEN outcursor FOR
	select ID_SIMULATION, NAME
		from SIMULATIONS
		where ID_PROJECT = projpk
		order by name asc;		

END GET_ORDERED_SIMULATION_LIST;

------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------


CREATE OR REPLACE PROCEDURE GET_SIMULATION(simpk in SIMULATIONS.ID_SIMULATION%TYPE, simcursor OUT SYS_REFCURSOR) AS

BEGIN

OPEN simcursor FOR
	select ID_SIMULATION, NAME,DESCRIPTION,STATE
		from SIMULATIONS
		where ID_SIMULATION = simpk;		

	
		
END GET_SIMULATION;


------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------


CREATE OR REPLACE PROCEDURE GET_TRAFFIC_PATTERN_LIST(simpk in TRAFFIC_PATTERNS.ID_SIMULATION%TYPE, tpcursor OUT SYS_REFCURSOR) AS

BEGIN

OPEN tpcursor FOR
	select ID_TRAFFIC_PATTERN, BEGIN_NODE_ID,END_NODE_ID,ID_VEHICLE,ARRIVAL_RATE
	from TRAFFIC_PATTERNS
	where ID_SIMULATION=simpk;
		
END GET_TRAFFIC_PATTERN_LIST;













------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------

CREATE OR REPLACE PROCEDURE GET_ORDERED_SIM_RUNS_LIST(simpk in SIMULATION_RUNS.ID_SIMULATION%TYPE, outcursor OUT SYS_REFCURSOR) AS

BEGIN

OPEN outcursor FOR
	select ID_RUN, NAME
		from SIMULATION_RUNS
		where ID_SIMULATION = simpk
		order by name asc;		

END GET_ORDERED_SIM_RUNS_LIST;












------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------

CREATE OR REPLACE PROCEDURE GET_TRAF_PATS_AVG_CONSUMPTION (runpk IN INJECTED_VEHICLES.ID_RUN%TYPE, outcursor OUT SYS_REFCURSOR) as

BEGIN
OPEN outcursor FOR

SELECT tp.ID_TRAFFIC_PATTERN, tp.BEGIN_NODE_ID as BeginNode, tp.END_NODE_ID as EndNode, v.name, tp.arrival_rate, avg(ivb.ENERGY_SPENT) as AVG_CONSUMPTION
FROM TRAFFIC_PATTERNS tp,INJECTED_VEHICLES iv, INJECTED_VEHICLES_BEHAVIOURS ivb, VEHICLES v
WHERE iv.ID_RUN = runpk
  and iv.ID_INJECTED_VEHICLE = ivb.ID_INJECTED_VEHICLE
	and iv.ID_TRAFFIC_PATTERN = tp.ID_TRAFFIC_PATTERN
	and tp.ID_VEHICLE = v.ID_VEHICLE
	
GROUP BY iv.ID_TRAFFIC_PATTERN, tp.ID_TRAFFIC_PATTERN, tp.BEGIN_NODE_ID, tp.END_NODE_ID, v.name, 
tp.arrival_rate;

END GET_TRAF_PATS_AVG_CONSUMPTION;


------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------


CREATE OR REPLACE PROCEDURE GET_TRAF_PATS_SEGS_AVG_CONS (runpk IN INJECTED_VEHICLES.ID_RUN%TYPE, outcursor OUT SYS_REFCURSOR) as

BEGIN
OPEN outcursor FOR

SELECT tp.ID_TRAFFIC_PATTERN, tp.BEGIN_NODE_ID as BeginNode, tp.END_NODE_ID as EndNode, v.name, tp.arrival_rate, ivb.ID_SECTION, ivb.SEGMENT_INDEX, ivb.Direction, avg(ivb.ENERGY_SPENT) as AVG_CONSUMPTION
FROM TRAFFIC_PATTERNS tp,INJECTED_VEHICLES iv, INJECTED_VEHICLES_BEHAVIOURS ivb, VEHICLES v

WHERE iv.ID_RUN = runpk
  and iv.ID_INJECTED_VEHICLE = ivb.ID_INJECTED_VEHICLE
	and iv.ID_TRAFFIC_PATTERN = tp.ID_TRAFFIC_PATTERN
	and tp.ID_VEHICLE = v.ID_VEHICLE

	
GROUP BY iv.ID_TRAFFIC_PATTERN, tp.ID_TRAFFIC_PATTERN, tp.BEGIN_NODE_ID, tp.END_NODE_ID, v.name, 
tp.arrival_rate, ivb.ID_SECTION, ivb.SEGMENT_INDEX, ivb.Direction;

END GET_TRAF_PATS_SEGS_AVG_CONS;




------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------


CREATE OR REPLACE PROCEDURE GET_SEGS_AVG_CONS_FOR_TRAFPAT (runpk IN INJECTED_VEHICLES.ID_RUN%TYPE, tppk IN INJECTED_VEHICLES.ID_TRAFFIC_PATTERN%TYPE, outcursor OUT SYS_REFCURSOR) as

BEGIN

OPEN outcursor FOR



SELECT tp.ID_TRAFFIC_PATTERN, 
        tp.BEGIN_NODE_ID as BeginNode, 
        tp.END_NODE_ID as EndNode, 
        v.name, 
        tp.arrival_rate, 
        ivb.ID_SECTION, 
        ivb.SEGMENT_INDEX, 
        ivb.Direction, 
        avg(ivb.ENERGY_SPENT) as AVG_CONSUMPTION,
        avg(ivb.INSTANT_OUT - ivb.INSTANT_IN) as AVG_TIME_SPENT
FROM TRAFFIC_PATTERNS tp,INJECTED_VEHICLES iv, INJECTED_VEHICLES_BEHAVIOURS ivb, VEHICLES v

WHERE iv.ID_RUN = runpk
  and iv.ID_TRAFFIC_PATTERN=tppk
  and iv.ID_INJECTED_VEHICLE = ivb.ID_INJECTED_VEHICLE
	and iv.ID_TRAFFIC_PATTERN = tp.ID_TRAFFIC_PATTERN
	and tp.ID_VEHICLE = v.ID_VEHICLE

	
GROUP BY iv.ID_TRAFFIC_PATTERN, tp.ID_TRAFFIC_PATTERN, tp.BEGIN_NODE_ID, tp.END_NODE_ID, v.name, 
tp.arrival_rate, ivb.ID_SECTION, ivb.SEGMENT_INDEX, ivb.Direction

ORDER BY ivb.ID_SECTION, ivb.SEGMENT_INDEX;

END GET_SEGS_AVG_CONS_FOR_TRAFPAT;





------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------


CREATE OR REPLACE PROCEDURE GET_ORDERED_RUNS_LIST(simpk IN SIMULATION_RUNS.ID_SIMULATION%TYPE, outcursor OUT SYS_REFCURSOR) AS

BEGIN

OPEN outcursor FOR
	select ID_RUN, NAME
		from SIMULATION_RUNS 
    where ID_SIMULATION = simpk
		order by name asc;		

END GET_ORDERED_RUNS_LIST;


------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------


















------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------
-- CHECK PROCEDURES
------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------
CREATE OR REPLACE PROCEDURE CHECK_PROJECT_EXISTS(pname IN PROJECTS.NAME%TYPE, n OUT integer) AS

BEGIN

	select count(Name) into n
		from PROJECTS
		WHERE NAME = pname;

END CHECK_PROJECT_EXISTS;

------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------

create or replace PROCEDURE CHECK_SIMULATION_EXISTS(projpk IN SIMULATIONS.ID_PROJECT%TYPE,
													sname IN SIMULATIONS.NAME%TYPE, 
													n OUT integer) AS

BEGIN

	select count(Name) into n
		from SIMULATIONS
		WHERE NAME = sname
	  and ID_PROJECT=projpk;

END CHECK_SIMULATION_EXISTS;
------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------

create or replace PROCEDURE CHECK_SIMULATION_RUN_EXISTS(simpk IN SIMULATION_RUNS.ID_SIMULATION%TYPE,
													rname IN SIMULATION_RUNS.NAME%TYPE, 
													n OUT integer) AS

BEGIN

	select count(Name) into n
		from SIMULATION_RUNS
		WHERE NAME = rname
	  and ID_SIMULATION=simpk;

END CHECK_SIMULATION_RUN_EXISTS;



------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------

create or replace PROCEDURE CHECK_PROJECT_HAS_SIMULATIONS(projpk IN SIMULATIONS.ID_PROJECT%TYPE,
													n OUT integer) AS

BEGIN

	select count(Name) into n
		from SIMULATIONS
		WHERE ID_PROJECT=projpk;

END CHECK_PROJECT_HAS_SIMULATIONS;






------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------




create or replace PROCEDURE CHECK_SIMULATION_HAS_RUNS(simpk IN SIMULATION_RUNS.ID_SIMULATION%TYPE,
													n OUT integer) AS

BEGIN

	select count(Name) into n
		from SIMULATION_RUNS
		WHERE ID_SIMULATION=simpk;

END CHECK_SIMULATION_HAS_RUNS;




------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------




create or replace PROCEDURE DELETE_SIMULATION_RUN(runpk IN SIMULATION_RUNS.ID_RUN%TYPE) AS

BEGIN

	delete from SIMULATION_RUNS
		WHERE ID_RUN=runpk;

END DELETE_SIMULATION_RUN;























